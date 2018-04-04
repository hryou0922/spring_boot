package com.hry.spring.redis.distributedlock.lockmsg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hry.spring.redis.distributedlock.lock.DistributeLock;
import com.hry.spring.redis.distributedlock.lock.ILock;

/**
 * 简单分布锁管理，
 * 	适用于管理竞争不是很激烈的锁
 * 	支持重入功能
 *  
 * @author Administrator
 *
 */
@Component
public class SimpleRedisLockManager implements ILockManager {	
	
	@Autowired
	protected StringRedisTemplate redisTemplate;
	
	protected ILock distributeLock; // 分布锁
	
	@PostConstruct
	public void init(){
		// 初始化锁
		distributeLock = new DistributeLock(redisTemplate, "mylock_", 5);
	}
	
	@Override
	public void lockCallBack(String lockKeyName, SimpleCallBack callback){
		Assert.notNull("lockKeyName","lockKeyName 不能为空");
		Assert.notNull("callback","callback 不能为空");
		try{
			// 获取锁
			distributeLock.lock(lockKeyName);
			callback.execute();
		}finally{
			// 必须释放锁
			distributeLock.unlock(lockKeyName);
		}
	}
	
	@Override
	public <T> T lockCallBackWithRtn(String lockKeyName, ReturnCallBack<T> callback){
		Assert.notNull("lockKeyName","lockKeyName 不能为空");
		Assert.notNull("callback","callback 不能为空");
		try{
			// 获取锁
			distributeLock.lock(lockKeyName);
			return callback.execute();
		}finally{
			// 必须释放锁
			distributeLock.unlock(lockKeyName);
		}
	}
}
