package com.hry.spring.redis.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstApplication {
	private static final Logger log = LoggerFactory.getLogger(FirstApplication.class);

	public static void main(String[] args) {
		log.info("Start FirstApplication.. ");
		SpringApplication.run(FirstApplication.class, args);
	}
}
