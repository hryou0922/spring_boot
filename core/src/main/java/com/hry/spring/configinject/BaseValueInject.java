package com.hry.spring.configinject;

 
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * 通过@Value注入bean的属性
 * @author hry
 *
 */
@Component
public class BaseValueInject {
	private static final Logger log = LoggerFactory.getLogger(BaseValueInject.class);
	
	@Value("normal")
	private String normal; // 注入普通字符串
	
	@Value("#{systemProperties['os.name']}")
	private String systemPropertiesName; // 注入操作系统属性
	
	@Value("#{ T(java.lang.Math).random() * 100.0 }")
	private double randomNumber; //注入表达式结果
	
	@Value("#{beanInject.another}")
	private String fromAnotherBean; // 注入其他Bean属性：注入beanInject对象的属性another
	
	@Value("classpath:com/hry/spring/configinject/config.txt")
	private Resource resourceFile; // 注入文件资源
	
	@Value("http://www.baidu.com")
	private Resource testUrl; // 注入URL资源
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("normal=").append(normal).append("\r\n")
			.append("systemPropertiesName=").append(systemPropertiesName).append("\r\n")
			.append("randomNumber=").append(randomNumber).append("\r\n")
			.append("fromAnotherBean=").append(fromAnotherBean).append("\r\n")
			.append("resourceFile=").append(IOUtils.toString(resourceFile.getInputStream())).append("\r\n")
			.append("testUrl=").append(IOUtils.toString(testUrl.getInputStream())).append("\r\n");
		} catch (IOException e) {
			log.error("e={}", e);
		}
		return sb.toString();
	}	
	
}
