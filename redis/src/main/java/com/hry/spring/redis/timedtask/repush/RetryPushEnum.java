package com.hry.spring.redis.timedtask.repush;

/**
 * 重推：向第三方发送信息，如果推送失败，则需要重新推送。每次重推需要间隔一段时间，最后推送N次
 * 	这里定义了重推的几种类型
 * @author Administrator
 *
 */
public enum RetryPushEnum {
	SMS(1, "sms"), CDRS(2, "cdrs");
	
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
	
	public static RetryPushEnum getRetryPushEnum(int type){
		switch(type){
		case 1 : return SMS;
		case 2 : return CDRS;
		default : throw new IllegalArgumentException("unkow enum type = " + type);
		}
	}
}
