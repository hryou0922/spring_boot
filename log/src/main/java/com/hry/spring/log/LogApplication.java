package com.hry.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApplication {
	private static final Logger log = LoggerFactory.getLogger(LogApplication.class);
	public static void main(String[] args) {
		log.info("Begin Start ...");
		SpringApplication.run(LogApplication.class, args);
		log.info("Stop ...");
	}
}
