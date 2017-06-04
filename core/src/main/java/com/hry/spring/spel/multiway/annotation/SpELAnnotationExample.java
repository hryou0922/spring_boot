package com.hry.spring.spel.multiway.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 通过注解使用Spring EL
 * 
 * @author hry
 *
 */
@Component
public class SpELAnnotationExample {
	@Value("#{ 'Hello World'.concat('!') }")
	private String stringMethod; // 调用String的concat方法
	@Value("#{ T(java.util.Arrays).toString('Hello World'.bytes) }")
	private String javaBeanProperties; // 调用JavaBean的属性，如这里实际是调用getBytes()方法
	@Value("#{ 'Hello World'.bytes.length }")
	private long publicAttr; // 访问对象公共属性
	@Value("#{ people.name }")
	private String objName; // 获取People的name值，比较name值是不是hry
	@Value("#{ people.name=='hry' }")
	private boolean objNamecmp;  // 比较name值是不是hry的结果

	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
	
	public String getStringMethod() {
		return stringMethod;
	}

	public void setStringMethod(String stringMethod) {
		this.stringMethod = stringMethod;
	}

	public String getJavaBeanProperties() {
		return javaBeanProperties;
	}

	public void setJavaBeanProperties(String javaBeanProperties) {
		this.javaBeanProperties = javaBeanProperties;
	}

	public long getPublicAttr() {
		return publicAttr;
	}

	public void setPublicAttr(long publicAttr) {
		this.publicAttr = publicAttr;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public boolean isObjNamecmp() {
		return objNamecmp;
	}

	public void setObjNamecmp(boolean objNamecmp) {
		this.objNamecmp = objNamecmp;
	}
	
	
}


