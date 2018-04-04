package com.hry.spring.redis.timedtask.repush;

import java.util.List;

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
