package com.hry.spring.configinject;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 比较复杂的注入应用
 * @author hry
 *
 */
@Component
@PropertySource({"classpath:com/hry/spring/configinject/advance_value_inject.properties"})
public class AdvanceValueInject {
	// SpEL：调用字符串Hello World的concat方法
	@Value("#{'Hello World'.concat('!')}")
	private String helloWorld;
	
	// SpEL: 调用字符串的getBytes方法，然后调用length属性
	@Value("#{'Hello World'.bytes.length}")
	private String helloWorldbytes;
	
	// SpEL: 传入一个字符串，根据","切分后插入列表中， #{}和${}配置使用(注意单引号，注意不能反过来${}在外面，#{}在里面)
	@Value("#{'${server.name}'.split(',')}")
	private List<String> servers;
	
//	// SpEL: 注意不能反过来${}在外面，#{}在里面，这个会执行失败
//	@Value("${#{'HelloWorld'.concat('_')}}")
//	private List<String> servers2;
	
	// 使用default.value设置值，如果不存在则使用默认值
	@Value("${spelDefault.value:127.0.0.1}")
	private String spelDefault;
	
	// 如果属性文件没有spelDefault.value，则会报错
//	@Value("${spelDefault.value}")
//	private String spelDefault2;
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("helloWorld=").append(helloWorld).append("\r\n")
		.append("helloWorldbytes=").append(helloWorldbytes).append("\r\n")
		.append("servers=").append(servers).append("\r\n")
		.append("spelDefault=").append(spelDefault).append("\r\n")
	//	.append("spelDefault2=").append(spelDefault2).append("\r\n")
		;
		return sb.toString();
	}
}
