package com.hry.spring.redis.distributedlock.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

/**
 * 通过redis实现分布锁
 * @author hry
 *
 */
public class DistributeLock implements ILock {
	private static final int LOCK_MAX_EXIST_TIME = 5;  // 单位s，一个线程持有锁的最大时间
	private static final String LOCK_PREX = "lock_"; // 作为锁的key的前缀
	
	private StringRedisTemplate redisTemplate;
	private String lockPrex; // 做为锁key的前缀
	private int lockMaxExistTime; // 单位s，一个线程持有锁的最大时间
	
	private ThreadLocal<String> keyId = new ThreadLocal<String>();  // 线程变量
	
	public DistributeLock(StringRedisTemplate redisTemplate){
		this(redisTemplate, LOCK_PREX, LOCK_MAX_EXIST_TIME);
	}
	
	public DistributeLock(StringRedisTemplate redisTemplate, String lockPrex, int lockMaxExistTime){
		this.redisTemplate = redisTemplate;
		this.lockPrex = lockPrex;
		this.lockMaxExistTime = lockMaxExistTime;
	}
	
	
	@Override
	public void lock(String lock2){
		Assert.notNull(lock2, "lock2 can't be null!");
		String lockKey = getLockKey(lock2);
		BoundValueOperations<String,String> keyBoundValueOperations = redisTemplate.boundValueOps(lockKey);		
		while(true){
			// 如果上次拿到锁的是自己，则本次也可以拿到锁：实现可重入
			String value = keyBoundValueOperations.get();
			// 根据传入的值，判断用户是否持有这个锁
			if(value != null && value.equals(String.valueOf(keyId.get()))){
				// 重置过期时间
				keyBoundValueOperations.expire(lockMaxExistTime, TimeUnit.SECONDS);
				break;
			}
			
			if(keyBoundValueOperations.setIfAbsent(lockKey)){
				// 每次获取锁时，必须重新生成id值
				String keyUniqueId = UUID.randomUUID().toString(); // 生成key的唯一值
				keyId.set(keyUniqueId);
				// 显设置value，再设置过期日期，否则过期日期无效
				keyBoundValueOperations.set(String.valueOf(keyUniqueId));
				// 为了避免一个用户拿到锁后，进行过程中没有正常释放锁，这里设置一个默认过期实际，这段非常重要，如果没有，则会造成死锁
				keyBoundValueOperations.expire(lockMaxExistTime, TimeUnit.SECONDS);
				// 拿到锁后，跳出循环
				break;
			}else{
				try {
					// 短暂休眠，nano避免出现活锁 
					Thread.sleep(10, (int)(Math.random() * 500));
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
	

	@Override
	public void unlock(final String lock) {
		final String lockKey = getLockKey(lock);
		
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] rawKey=redisTemplate.getStringSerializer().serialize(lockKey);
				boolean rtn = connection.del(rawKey) > 0;					
				return rtn;
			}
		},true);
	}

	/**
	 * 生成key
	 * @param lock
	 * @return
	 */
	private String getLockKey(String lock){
		StringBuilder sb = new StringBuilder();
		sb.append(lockPrex).append(lock);
		return sb.toString();
	}
	
}
