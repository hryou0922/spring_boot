package com.hry.spring.redis.repush.service;

import java.util.List;

import com.hry.spring.redis.repush.support.RetryPushModel;

/**
 * 一些定时任务，只执行一次
 * 	
 * @author Administrator
 *
 */
public interface IOnceRunService {
	void save(RetryPushModel model);

	void delete(String id);

	List<RetryPushModel> queryAll();
}
