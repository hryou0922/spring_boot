package com.hry.spring.mvc.pojo;

import java.util.Date;

public class ModelAttributeVO {
	private String name;
	private String value;
	private Date date;

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name).append("\r\n")
			.append("value=").append(value).append("\r\n")
			.append("date=").append(date).append("\r\n")
		;
		return sb.toString();
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
