package com.hry.spring.mvc.json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
public class JsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonApplication.class, args);
	}
	
	/**
	 * 使用fastjson做为spring mvc的json序列化
	 * @return
	 */
	@Bean  
	public HttpMessageConverters fastJsonHttpMessageConverters() {  
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();  
		FastJsonConfig fastJsonConfig = new FastJsonConfig();  
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);  
		fastConverter.setFastJsonConfig(fastJsonConfig);  
		HttpMessageConverter<?> converter = fastConverter;  
		return new HttpMessageConverters(converter);  
	} 
}
