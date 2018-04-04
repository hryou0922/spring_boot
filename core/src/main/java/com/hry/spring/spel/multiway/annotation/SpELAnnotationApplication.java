package com.hry.spring.spel.multiway.annotation;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hry.spring.spel.multiway.People;

@SpringBootApplication
@Configurable
public class SpELAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpELAnnotationApplication.class, args);
	}
	
	
	/**
	 * 初始化对象
	 * @return
	 */
	@Bean("people")
	public People getPeople(){
		People people = new People();
		people.setName("hry");
		return people;
	}
}
