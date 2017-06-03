package com.hry.spring.spel.multiway.xml;

import com.alibaba.fastjson.JSON;

public class SpELXMLExample {
	private String stringMethod; // 调用String的concat方法
	private String javaBeanProperties; // 调用JavaBean的属性，如这里实际是调用getBytes()方法
	private long publicAttr; // 访问对象公共属性
	private String objName; // 获取People的name值，比较name值是不是hry
	private boolean objNamecmp;  // 比较name值是不是hry人结果
	
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


