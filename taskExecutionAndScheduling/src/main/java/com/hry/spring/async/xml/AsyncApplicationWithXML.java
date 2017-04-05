package com.hry.spring.async.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * 通过XML启动异步
 * @author hry
 *
 */
@SpringBootApplication
@ImportResource("classpath:/async/spring_async.xml")
public class AsyncApplicationWithXML {
	private static final Logger log = LoggerFactory.getLogger(AsyncApplicationWithXML.class);
	
	public static void main(String[] args) {
		log.info("Start AsyncApplication.. ");
		SpringApplication.run(AsyncApplicationWithXML.class, args);
	}
}
