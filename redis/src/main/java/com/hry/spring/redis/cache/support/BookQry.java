package com.hry.spring.redis.cache.support;

import com.alibaba.fastjson.JSON;

/**
 * 封装请求
 * @author hry
 *
 */
public class BookQry {
	private String id;
	private String name; // 书名
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
