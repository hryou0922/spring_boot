package com.hry.spring.redis.distributedlock.lockmsg;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.hry.spring.redis.distributedlock.lock.LuaDistributeLock;

@Component
public class LuaLockRedisLockManager extends SimpleRedisLockManager {
	@PostConstruct
	public void init(){
		// 初始化锁
		distributeLock = new LuaDistributeLock(redisTemplate, "mylock_", 5);
	}
}
