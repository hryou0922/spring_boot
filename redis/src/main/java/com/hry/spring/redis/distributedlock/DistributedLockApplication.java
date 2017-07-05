package com.hry.spring.redis.distributedlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DistributedLockApplication {
	private static final Logger log = LoggerFactory.getLogger(DistributedLockApplication.class);
    
	public static void main(String[] args) {
		log.info("Start DistributedLockApplication.. ");
		SpringApplication.run(DistributedLockApplication.class, args);
	}
}
