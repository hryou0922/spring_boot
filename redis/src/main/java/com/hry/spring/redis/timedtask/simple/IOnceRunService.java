package com.hry.spring.redis.timedtask.simple;

import java.util.Date;
import java.util.List;

/**
 * 一些定时任务，只执行一次
 * 	
 * @author Administrator
 *
 */
public interface IOnceRunService {
	/**
	 * 保存记录
	 * @param model
	 * @param executeTime :下次执行的时间
	 */
	void save(OnceRunModel model, Date executeTime);

	void delete(String id);

	List<OnceRunModel> queryAll();
}
