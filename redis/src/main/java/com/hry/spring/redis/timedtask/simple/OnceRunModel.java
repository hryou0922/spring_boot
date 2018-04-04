package com.hry.spring.redis.timedtask.simple;

import com.alibaba.fastjson.JSON;
import com.hry.spring.redis.timedtask.ITimedTaskModel;

public class OnceRunModel implements ITimedTaskModel {
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

	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
