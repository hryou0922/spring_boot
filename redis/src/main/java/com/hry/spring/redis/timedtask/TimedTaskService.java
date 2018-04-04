package com.hry.spring.redis.timedtask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

/**
 * 定时任务的服务类
 * 
 * 变量定义：
 * 	unique_keySuffix; 
 * 		此任务的定时任务可以被多种定时任务共用，为了区分不同定时任务，所以不同任务的key后缀不同
 * 		每个不同的定时任务，需要定义唯一的后缀, 如"cdrs","repush"
 *  id = UUID; //将ZSet和Hash里相应记录关联起来的值
 *  
 * 主要定义两个key来保存定时任务的信息
 * 	ZSet:
 * 		各个参数值的说明
 * 			key：timedTask_#{unique_keySuffix} // timedTask_
 * 			member：#{id}
 * 			score： 执行时间  // 分值计算
 * 	Hash：
 * 		各个参数值的说明
 * 			key：timedTaskContent_#{unique_keySuffix}
 * 			field： #{id}
 * 			fieldValue: 执行定时任务所需要的参数
 *		
 *	添加任务：
 *		一个任务需要同时在zset和hash中添加一条记录，两条记录通过id值关联在一起
 *		在ZSet和Hash里根据以上规则各自添加1条新的记录
 *	获取需要执行的任务：
 *		ZSet使用score保存任务执行时间，先从ZSet里面获取所有score <= 当前时间 的记录，
 *		然后逐个根据zset的member值从hash中获取field和zset的member相同的fieldValue值（member和fieldValue都是id值），fieldValue存储本次需要执行任务的详细内容
 *	删除记录
 * 		根据传入#{id}值，从ZSet和Hash删除记录
 * 
 *  使用lua脚本：
 *  	由于同时操作两个key，为了需要保证，需要使用脚本
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
	
	// 添加操作
	private DefaultRedisScript<Long> addScript;
	
	// 删除操作
	private DefaultRedisScript<Long> batchDelScript;
	
	// 查询
	private DefaultRedisScript<List> querycontentsScript;
	
	@PostConstruct	
	public void init() {
		// Lock script
		addScript = new DefaultRedisScript<Long>();
		addScript.setScriptSource(
				new ResourceScriptSource(new ClassPathResource("com/hry/spring/redis/timedtask/add.lua")));
		addScript.setResultType(Long.class);
		// unlock script
		batchDelScript = new DefaultRedisScript<Long>();
		batchDelScript.setScriptSource(
				new ResourceScriptSource(new ClassPathResource("com/hry/spring/redis/timedtask/batchdel.lua")));
		batchDelScript.setResultType(Long.class);
		// query script
		querycontentsScript = new DefaultRedisScript<List>();
		querycontentsScript.setScriptSource(
				new ResourceScriptSource(new ClassPathResource("com/hry/spring/redis/timedtask/querycontents.lua")));
		querycontentsScript.setResultType(List.class);
	}
	
	@Override
	public <T extends ITimedTaskModel> T add(String keySuffix, final Date executeTime,final T value){
		Assert.notNull(keySuffix,"keySuffix can't be null!");
		Assert.notNull(executeTime, "executeTime can't be null!");
		Assert.notNull(value, "value can't be null!");
		// 生成zset和hash的key值
		final String zSetKey = generateTimedTaskZsetKey(keySuffix);
		final String hashKey = generateTimedTaskHashContentKey(keySuffix);
		// keyId将zset和hash关联起来，此值作为zset里的value，但是作为hash里的key值
		final String id = UUID.randomUUID().toString() ; 
		value.setId(id);
		// 封装参数
		List<String> keyList = new ArrayList<String>();
		// hash的操作参数
		keyList.add(hashKey); // hash key
		keyList.add(id); // hash Field
		keyList.add(JSON.toJSONString(value)); // hash Field Value
		// zset的操作参数
		keyList.add(zSetKey); // zSetKey
		keyList.add(String.valueOf(executeTime.getTime())); // zSetScore
		keyList.add(id); // zSetMember
		Long result = redisTemplate.execute(addScript, keyList);
		logger.info("add 执行[{}]，返回[{}]", JSON.toJSONString(value), result);
		return value;
	}

	@Override
	public void bathDel(String keySuffix, final String... ids){
		final String zSetKey = generateTimedTaskZsetKey(keySuffix);
		final String hashKey = generateTimedTaskHashContentKey(keySuffix);
		
		List<String> keyList = new ArrayList<String>();
		for(String id : ids){
			// hash
			keyList.add(hashKey);
			keyList.add(id);
			// zset
			keyList.add(zSetKey);
			keyList.add(id);
		}
		if(keyList.size() > 0){
			Long result = redisTemplate.execute(batchDelScript, keyList);
			logger.info("bathDel 执行keySuffix[{}],value[{}]，返回[{}]", keySuffix, Arrays.toString(ids), result);
		}
	}
	
	@Override
	public <T extends ITimedTaskModel> List<T> getTimedTaskContent(String keySuffix, Class<T> cls){
		List<T> rtnList = new ArrayList<T>();
		final String zSetKey = generateTimedTaskZsetKey(keySuffix);
		final String hashKey = generateTimedTaskHashContentKey(keySuffix);
		// 获取所有已经到了需要执行的定时任务
		List<String> keyList = new ArrayList<String>();
		// zset
		keyList.add(zSetKey);
		keyList.add(String.valueOf(Long.MIN_VALUE));
		keyList.add(String.valueOf(System.currentTimeMillis()));
		// hashkey
		keyList.add(hashKey);
		
		if(keyList.size() > 0){
		List resultList = redisTemplate.execute(querycontentsScript, keyList);
			for(Object o : resultList){
				logger.info("read content = {}", o.toString());
				rtnList.add(JSON.parseObject(o.toString(), cls));
			}
		}
		return rtnList;
	}
	
	
	/**
	 * 获取定时任务排序的key值
	 * @param keySuffix
	 * @return
	 */
	private String generateTimedTaskZsetKey(String keySuffix){
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
	private String generateTimedTaskHashContentKey(String keySuffix){
		StringBuilder sb = new StringBuilder();
		sb.append(TIMED_TASK_KEY_CONTENT_PREFIX);
		sb.append(keySuffix);
		return sb.toString();
	}
	
}

