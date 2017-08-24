package com.hry.spring.mvc.json;

import com.alibaba.fastjson.annotation.JSONField;

public class JsonVo {
	// 如果使用json,则不返回此字段
	@JSONField(serialize=false)
	private String id;
	private String name;
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
