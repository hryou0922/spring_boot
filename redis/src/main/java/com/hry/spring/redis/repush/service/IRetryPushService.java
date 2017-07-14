package com.hry.spring.redis.repush.service;

import java.util.List;

import com.hry.spring.redis.repush.support.RetryPushModel;

/**
 * 重新推送
 * @author Administrator
 *
 */
public interface IRetryPushService {
	void save(RetryPushModel model);

	void delete(String id);

	List<RetryPushModel> queryAll();
}
