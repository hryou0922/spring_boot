package com.hry.spring.redis.distributedlock.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.Assert;

/**
 * 通过redis实现分布锁
 * 
 * 
 * @author hry
 *
 */
public class LuaDistributeLock implements ILock {
	private static final int LOCK_MAX_EXIST_TIME = 5;  // 单位s，一个线程持有锁的最大时间
	private static final String LOCK_PREX = "lock_"; // 作为锁的key的前缀
	
	private StringRedisTemplate redisTemplate;
	private String lockPrex; // 做为锁key的前缀
	private int lockMaxExistTime; // 单位s，一个线程持有锁的最大时间
	private DefaultRedisScript<Long> lockScript; // 锁脚本
	private DefaultRedisScript<Long> unlockScript; // 解锁脚本
	
	// 线程变量
	private ThreadLocal<String> threadKeyId = new ThreadLocal<String>(){
		@Override
		protected String initialValue() {
			return UUID.randomUUID().toString();
		}
	};  
	
	public LuaDistributeLock(StringRedisTemplate redisTemplate){
		this(redisTemplate, LOCK_PREX, LOCK_MAX_EXIST_TIME);
	}
	
	public LuaDistributeLock(StringRedisTemplate redisTemplate, String lockPrex, int lockMaxExistTime){
		this.redisTemplate = redisTemplate;
		this.lockPrex = lockPrex;
		this.lockMaxExistTime = lockMaxExistTime;
		// init
		init();
	}
	
	/**
	 * 生成
	 */
	public void init() {
		// Lock script
		lockScript = new DefaultRedisScript<Long>();
		lockScript.setScriptSource(
				new ResourceScriptSource(new ClassPathResource("com/hry/spring/redis/distributedlock/lock/lock.lua")));
		lockScript.setResultType(Long.class);
		// unlock script
		unlockScript = new DefaultRedisScript<Long>();
		unlockScript.setScriptSource(
				new ResourceScriptSource(new ClassPathResource("com/hry/spring/redis/distributedlock/lock/unlock.lua")));
		unlockScript.setResultType(Long.class);
	}
	
	@Override
	public void lock(String lock2){
		Assert.notNull(lock2, "lock2 can't be null!");
		String lockKey = getLockKey(lock2);
		while(true){
			List<String> keyList = new ArrayList<String>();
			keyList.add(lockKey);
			keyList.add(threadKeyId.get());
			if(redisTemplate.execute(lockScript, keyList, String.valueOf(lockMaxExistTime * 1000)) > 0){
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
	

	/**
	 * 释放锁，同时要考虑当前锁是否为自己所有，以下情况会导致当前线程失去锁：线程执行的时间超过超时的时间，导致此锁被其它线程拿走; 此时用户不可以执行删除
	 * 
	 */
	@Override
	public void unlock(final String lock) {
		final String lockKey = getLockKey(lock);
		List<String> keyList = new ArrayList<String>();
		keyList.add(lockKey);
		keyList.add(threadKeyId.get());
		redisTemplate.execute(unlockScript, keyList);
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
