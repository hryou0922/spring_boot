package com.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// 导入redis依赖；使用fastjson做为HttpMessageConverters
@EnableCaching
@SpringBootApplication
public class InterfaceApplication {
    private static final Logger logger = LoggerFactory.getLogger(InterfaceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(InterfaceApplication.class, args);
    }
}
