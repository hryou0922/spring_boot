package com.hry.spring.redis.timedtask;

import java.util.Date;
import java.util.List;

public interface ITimedTaskService{	
	/**
	 * 添加需要定时执行的任务
	 * @param keySuffix
	 * @param executeTime 执行的时间
	 * @param value
	 */
	<T extends ITimedTaskModel> T add(String keySuffix, final Date executeTime, final T value);

	/**
	 * 批量删除已经执行的定时任务
	 * @param keySuffix
	 * @param relationValues
	 */
	void bathDel(String keySuffix, final String... ids);

	/**
	 * 获取当前需要执行的定时任务
	 * @param keySuffix
	 * @return
	 */
	<T extends ITimedTaskModel> List<T> getTimedTaskContent(String keySuffix, Class<T> cls);
}