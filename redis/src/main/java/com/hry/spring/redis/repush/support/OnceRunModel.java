package com.hry.spring.redis.repush.support;

public class OnceRunModel implements IModel {
	private String id;
	private String content;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
