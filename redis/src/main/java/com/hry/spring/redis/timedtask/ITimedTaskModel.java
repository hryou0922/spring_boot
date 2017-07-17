package com.hry.spring.redis.timedtask;

/**
 * 定时任务的mode必须实现此接口
 * @author Administrator
 *
 */
public interface ITimedTaskModel {
	
	String getId();
	
	void setId(String id);
}
