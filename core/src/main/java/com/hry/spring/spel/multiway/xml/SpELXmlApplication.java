package com.hry.spring.spel.multiway.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/com/hry/spring/spel/multiway/xml/spel.xml")
public class SpELXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpELXmlApplication.class, args);
	}
}
