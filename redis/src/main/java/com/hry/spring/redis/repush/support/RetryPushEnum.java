package com.hry.spring.redis.repush.support;

public enum RetryPushEnum {
	CDRS(1, "话单"), SMS(2, "短信");
	
	private int type; // 类型
	private String name; // 名称
	
	private RetryPushEnum(int type, String name){
		this.type = type;
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
