package com.hry.spring.async.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 自定义异常类：
 * 	AsyncConfigurer
 * @author hry
 *
 */
@SpringBootApplication
@EnableAsync // 启动异步调用
public class AsyncApplicationWithAsyncConfigurer {
	private static final Logger log = LoggerFactory.getLogger(AsyncApplicationWithAsyncConfigurer.class);
	
	public static void main(String[] args) {
		log.info("Start AsyncApplication.. ");
		SpringApplication.run(AsyncApplicationWithAsyncConfigurer.class, args);
	}
	

}


