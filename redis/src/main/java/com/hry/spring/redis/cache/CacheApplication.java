package com.hry.spring.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;


@SpringBootApplication
@EnableCaching // 启动缓存
public class CacheApplication {
	private static final Logger log = LoggerFactory.getLogger(CacheApplication.class);
    
	public static void main(String[] args) {
		log.info("Start CacheApplication.. ");
		SpringApplication.run(CacheApplication.class, args);
		
	}
 
	/**
	 * 重新配置RedisCacheManager
	 * @param rd
	 */
	@Autowired
	public void configRedisCacheManger(RedisCacheManager rd){
		rd.setDefaultExpiration(100L);
	}
}
