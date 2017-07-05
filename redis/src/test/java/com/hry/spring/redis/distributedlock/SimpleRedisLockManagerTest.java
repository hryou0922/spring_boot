package com.hry.spring.redis.distributedlock;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hry.spring.redis.distributedlock.lockmsg.ReturnCallBack;
import com.hry.spring.redis.distributedlock.lockmsg.SimpleCallBack;
import com.hry.spring.redis.distributedlock.lockmsg.SimpleRedisLockManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DistributedLockApplication.class)
public class SimpleRedisLockManagerTest {
	@Autowired
	private SimpleRedisLockManager simpleRedisLockManager;
	
	private String lockKeyName = "lockKeyName";
	
	@Test
	public void lockCallBack(){
		// 多个线程同时
		simpleRedisLockManager.lockCallBack(lockKeyName, new SimpleCallBack() {
			@Override
			public void execute() {
				System.out.println("lockCallBack");
			}
		});
	}
	
	@Test
	public void lockCallBackWithRtn(){
		String lockKeyName = "lockKeyName";
		// 多个线程同时
		boolean rtn = simpleRedisLockManager.lockCallBackWithRtn(lockKeyName, new ReturnCallBack<Boolean>() {
			@Override
			public Boolean execute() {
				System.out.println("lockCallBackWithRtn");
				return true;
			}
		});
		assertTrue(rtn);
	}
}
