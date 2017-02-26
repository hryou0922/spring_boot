package com.hry.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApplication {
	private static final Logger log = LoggerFactory.getLogger(LogApplication.class);
	public static void main(String[] args) {
		String str1 = "string1";
		String str2 = "string2";
		log.info("Begin Start {}...{}", str1, str2);
	//	log.info("Begin Start " + str1 +"..." + str2);
		SpringApplication.run(LogApplication.class, args);
		log.info("Stop ...");
	}
}
