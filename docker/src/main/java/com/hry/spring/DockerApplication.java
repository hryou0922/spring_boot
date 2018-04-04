package com.hry.spring;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockerApplication {

	@RequestMapping("/")
	public String home() {
		File  f = new File("");	
		return "Hello Docker World";
	}

	public static void main(String[] args) {
		SpringApplication.run(DockerApplication.class, args);
	}
}
