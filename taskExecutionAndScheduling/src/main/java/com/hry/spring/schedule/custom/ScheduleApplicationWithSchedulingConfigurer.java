package com.hry.spring.schedule.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 通过@EnableScheduling启动定时方法
 * 配置
 * 
 * @author hry
 * 
 */
@SpringBootApplication
@EnableScheduling // 启动定时任务
public class ScheduleApplicationWithSchedulingConfigurer {
	private static final Logger log = LoggerFactory.getLogger(ScheduleApplicationWithSchedulingConfigurer.class);
	
	public static void main(String[] args) {
		log.info("Start ScheduleApplicationWithSchedulingConfigurer.. ");
		SpringApplication.run(ScheduleApplicationWithSchedulingConfigurer.class, args);
	}
}
