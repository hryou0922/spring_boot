package com.hry.spring.redis.timedtask.repush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.hry.spring.redis.timedtask","com.hry.spring.redis.timedtask.repush"})
@EnableScheduling // 启动定时任务
public class TimedTaskRepushApplication {
	private static final Logger log = LoggerFactory.getLogger(TimedTaskRepushApplication.class);

	public static void main(String[] args) {
		log.info("Start FirstApplication.. ");
		SpringApplication.run(TimedTaskRepushApplication.class, args);
	}
}
