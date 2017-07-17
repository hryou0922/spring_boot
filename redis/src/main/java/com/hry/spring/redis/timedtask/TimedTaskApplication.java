package com.hry.spring.redis.timedtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 启动定时任务
public class TimedTaskApplication {
	private static final Logger log = LoggerFactory.getLogger(TimedTaskApplication.class);

	public static void main(String[] args) {
		log.info("Start FirstApplication.. ");
		SpringApplication.run(TimedTaskApplication.class, args);
	}
}
