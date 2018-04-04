package com.hry.spring.schedule.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 通过XML启动异步方法
 * @author hry
 * 
 */
@SpringBootApplication
@ImportResource("classpath:/schedule/spring_schedule.xml")
public class ScheduleApplicationWithXML {
	private static final Logger log = LoggerFactory.getLogger(ScheduleApplicationWithXML.class);
    
	public static void main(String[] args) {
		log.info("Start ScheduleApplicationWithXML.. ");
		SpringApplication.run(ScheduleApplicationWithXML.class, args);
	}
}
