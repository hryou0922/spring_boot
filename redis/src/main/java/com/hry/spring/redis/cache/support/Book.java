package com.hry.spring.redis.cache.support;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Book implements Serializable {
	private static final long serialVersionUID = 2629983876059197650L;
	
	private String id;
	private String name; // 书名
	private Integer price; // 价格
	private Date update; // 

	public Book(String id, String name, Integer price, Date update) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.update = update;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
