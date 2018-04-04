package com.hry.spring.redis.distributedlock;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hry.spring.redis.distributedlock.lockmsg.LuaLockRedisLockManager;
import com.hry.spring.redis.distributedlock.lockmsg.SimpleCallBack;
import com.hry.spring.redis.distributedlock.lockmsg.SimpleRedisLockManager;

@Controller
public class TestCtrl {
	
	@Autowired
	private SimpleRedisLockManager simpleRedisLockManager;
	
	@Autowired
	private LuaLockRedisLockManager luaLockRedisLockManager;
	
	@ResponseBody
	@RequestMapping("/distributeLock")
	public String distributeLock(){
		
		simpleRedisLockManager.lockCallBack("distributeLock" + ThreadLocalRandom.current().nextInt(1000), new SimpleCallBack() {
			@Override
			public void execute() {
				System.out.println("lockCallBack");
			}
		});
		return "distributeLock";
	}
	
	@ResponseBody
	@RequestMapping("/distributeLock2")
	public String distributeLock2(){
		luaLockRedisLockManager.lockCallBack("distributeLock2" + ThreadLocalRandom.current().nextInt(1000), new SimpleCallBack() {
			@Override
			public void execute() {
				System.out.println("distributeLock2");
			}
		});
		return "distributeLock2";
	}
}
