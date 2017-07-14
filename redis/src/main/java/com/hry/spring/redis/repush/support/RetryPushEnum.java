package com.hry.spring.redis.repush.support;

public enum RetryPushEnum {
	ONCE_RUN(1, "OnceRun", "cdrs"), SMS(2, "短信", "sms");
	
	private int type; // 类型
	private String name; // 名称
	private String keySuffix; // 存储到redis中key的后缀
	
	private RetryPushEnum(int type, String name, String keySuffix){
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
	public String getKeySuffix() {
		return keySuffix;
	}

	public void setKeySuffix(String keySuffix) {
		this.keySuffix = keySuffix;
	}	
}
