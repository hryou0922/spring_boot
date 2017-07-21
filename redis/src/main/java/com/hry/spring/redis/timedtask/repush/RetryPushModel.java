package com.hry.spring.redis.timedtask.repush;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.hry.spring.redis.timedtask.ITimedTaskModel;

public class RetryPushModel implements ITimedTaskModel{
	private String id; //  
	private String url; // 推送地址
	private String content; // 内容
	private Date pushNextTime; // 下次推送时间
	private Integer pushTime; // 已经推送次数，值从1开始
	private Integer type ; // 类型
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPushNextTime() {
		return pushNextTime;
	}
	public void setPushNextTime(Date pushNextTime) {
		this.pushNextTime = pushNextTime;
	}
	public Integer getPushTime() {
		return pushTime;
	}
	public void setPushTime(Integer pushTime) {
		this.pushTime = pushTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
