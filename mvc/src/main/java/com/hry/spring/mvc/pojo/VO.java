package com.hry.spring.mvc.pojo;

import java.util.Date;

public class VO {
	private String name;
	private String value;
	private Date date;
	
	public VO(){
		this.name = "name";
		this.value = "value";
		this.date = new Date();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
