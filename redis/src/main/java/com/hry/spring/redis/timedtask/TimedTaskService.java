package com.hry.spring.redis.timedtask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 用于对定时任务的管理
 * 
 * 变量定义：
 * 	unique_keySuffix; // 每个不同的定时任务，需要定义唯一的后缀, 如“batchBind”，“retryPush”
 *  relationValue = UUID; //将ZSet和Hash里相应记录关联起来的值
 *  
 * 定义两个key
 * 	ZSet
 * 		key：timedTask_#{unique_keySuffix}
 * 		value：#{relationValue}
 * 		score： 执行时间  // 分值计算
 * 	Hash
 * 		key：timedTaskContent_#{unique_keySuffix}
 * 		sub_key： #{relationValue}
 * 		value: 执行定时任务所需要的参数
 *	
 *	添加任务：
 *		在ZSet和Hash里根据以上规则各自添加1条新的记录
 *	获取需要执行的任务：
 *		先从ZSet里面获取所有score <= 当前时间 的记录，然后逐个从Hash里面获取任务的详细内容，并返回
 *	删除记录
 * 		根据传入#{relationValue}值，从ZSet和Hash删除记录
 * 
 *  
 *  	
 *
 */
@Service
public class TimedTaskService implements ITimedTaskService{
	private static final Logger logger = LoggerFactory.getLogger(TimedTaskService.class);
	private final String TIMED_TASK_KEY_PREFIX = "timedTask_"; // 所有定时任务的前缀都是此值
	private final String TIMED_TASK_KEY_CONTENT_PREFIX = "timedTaskContent_"; // 所有定时任务的具体内容的前缀
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/* (non-Javadoc)
	 * @see im.yixin.api.cache.impl.ITimedTaskService#add(java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public <T extends ITimedTaskModel> T add(String keySuffix, final Date executeTime,final T value){
		final String zSetsortKey = generateTimedTaskSortKey(keySuffix);
		final String zHashKey = generateTimedTaskContentKey(keySuffix);
		final String keyId = UUID.randomUUID().toString() ; // 此值作为zset里的value，但是作为hash里的key值
		value.setId(keyId);
		
		int maxNum = 100;
		while(maxNum-- > 0){
			List<Object> txResults = redisTemplate.execute(new SessionCallback<List<Object>>() {
				@Override
				public List<Object> execute(RedisOperations operations)
						throws DataAccessException {
					operations.multi();
					// 添加定时任务内容表中 
					operations.opsForHash().put(zHashKey, keyId, JSON.toJSONString(value));
					// 添加到定时任务排序表
					operations.opsForZSet().add(zSetsortKey, keyId, executeTime.getTime());
					return operations.exec();
				}
			});
			if(txResults == null){
				logger.warn("zSetsortKey={},zHashKey={} 添加redis失败!",zSetsortKey,zHashKey);
			}else{
				break;
			}
		}
		if(maxNum <= 0){
			logger.error("zSetsortKey={},zHashKey={} 添加记录，重复次数超过100次，请注意排除原因",zSetsortKey,zHashKey);
		}
		return value;
	}
	
	/**
	 * 获取定时任务排序的key值
	 * @param keySuffix
	 * @return
	 */
	private String generateTimedTaskSortKey(String keySuffix){
		StringBuilder sb = new StringBuilder();
		sb.append(TIMED_TASK_KEY_PREFIX);
		sb.append(keySuffix);
		return sb.toString();
	}
	
	/**
	 * 获取定时任务排序的保存内容的值
	 * @param keySuffix
	 * @return
	 */
	private String generateTimedTaskContentKey(String keySuffix){
		StringBuilder sb = new StringBuilder();
		sb.append(TIMED_TASK_KEY_CONTENT_PREFIX);
		sb.append(keySuffix);
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see im.yixin.api.cache.impl.ITimedTaskService#bathDel(java.lang.String, java.lang.String)
	 */
	@Override
	public void bathDel(String keySuffix, final String... keyId){
		final String zSetsortKey = generateTimedTaskSortKey(keySuffix);
		final String zHashKey = generateTimedTaskContentKey(keySuffix);
		
		int maxNum = 100;
		while(maxNum-- > 0){
			List<Object> txResults = redisTemplate.execute(new SessionCallback<List<Object>>() {
				@Override
				public List<Object> execute(RedisOperations operations)
						throws DataAccessException {
					operations.multi();
					// 删除定时任务内容表中 
					operations.opsForHash().delete(zHashKey, keyId);
					// 删除到定时任务排序表
					operations.opsForZSet().remove(zSetsortKey, keyId);
					return operations.exec();
				}
			});
			if(txResults == null){
				logger.warn("zSetsortKey={},zHashKey={} 删除redis失败!",zSetsortKey,zHashKey);
			}else{
				break;
			}
		}
		if(maxNum <= 0){
			logger.error("zSetsortKey={},zHashKey={} 删除记录，重复次数超过100次，请注意排除原因",zSetsortKey,zHashKey);
		}
	}
	
	/* (non-Javadoc)
	 * @see im.yixin.api.cache.impl.ITimedTaskService#getTimedTaskContent(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T extends ITimedTaskModel> List<T> getTimedTaskContent(String keySuffix, Class<T> cls){
		List<T> rtnList = new ArrayList<T>();
		final String zSetsortKey = generateTimedTaskSortKey(keySuffix);
		final String zHashKey = generateTimedTaskContentKey(keySuffix);
		// 获取所有已经到了需要执行的定时任务
	//	Set<String> zSetValues = redisTemplate.opsForZSet().rangeByScore(zSetsortKey, Integer.MIN_VALUE, 0);
		Set<String> zSetValues = redisTemplate.opsForZSet().rangeByScore(zSetsortKey, Long.MIN_VALUE, System.currentTimeMillis());
		
		HashOperations<String,String,String> hashOperation = redisTemplate.opsForHash();
		for(String key : zSetValues){
			// 获取需要执行任务的详细信息, zset的value是Hash的key值
			String hashValue = hashOperation.get(zHashKey, key);
			rtnList.add(JSON.parseObject(hashValue, cls));
			logger.info("获取需要执行定时任务={}",hashValue);
		}
		return rtnList;
	}

	@Override
	public <T extends ITimedTaskModel> List<T> queryAllTimedTaskContent(
			String keySuffix, Class<T> cls) {
		List<T> rtnList = new ArrayList<T>();
		final String zSetsortKey = generateTimedTaskSortKey(keySuffix);
		final String zHashKey = generateTimedTaskContentKey(keySuffix);
		// 获取所有已经到了需要执行的定时任务
		Set<String> zSetValues = redisTemplate.opsForZSet().range(zSetsortKey, 0, -1);
		HashOperations<String,String,String> hashOperation = redisTemplate.opsForHash();
		for(String key : zSetValues){
			// 获取需要执行任务的详细信息, zset的value是Hash的key值
			String hashValue = hashOperation.get(zHashKey, key);
			rtnList.add(JSON.parseObject(hashValue, cls));
		}
		return rtnList;
	}
	
	public <T extends ITimedTaskModel> T queryTimedTaskContentByKey(String keySuffix, String id, Class<T> cls){
		final String zHashKey = generateTimedTaskContentKey(keySuffix);
		// 获取所有已经到了需要执行的定时任务
		Object hashValue = redisTemplate.opsForHash().get(zHashKey, id);
		T rtn = null;
		if(hashValue != null){
			rtn = JSON.parseObject((String)(hashValue), cls);
			// CommonJsonUtils.parseObject((String)(hashValue), cls);
		}
		return rtn;
	}
}

