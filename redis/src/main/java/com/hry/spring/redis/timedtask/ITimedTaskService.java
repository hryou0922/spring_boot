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
	<T extends ITimedTaskModel> T add(String keySuffix, Date executeTime, T value);

	/**
	 * 批量删除已经执行的定时任务
	 * @param keySuffix
	 * @param zSetValueAndHashKeys
	 */
	void bathDel(String keySuffix,
			String... zSetValueAndHashKeys);

	/**
	 * 获取当前需要执行的定时任务
	 * @param keySuffix
	 * @return
	 */
	<T extends ITimedTaskModel> List<T> getTimedTaskContent(String keySuffix,
			Class<T> cls);
//
//	/**
//	 * 获取所有的定时任务
//	 * @param keySuffix
//	 * @param cls
//	 * @return
//	 */
//	<T extends ITimedTaskModel> List<T> queryAllTimedTaskContent(String keySuffix,
//			Class<T> cls);

//	<T extends ITimedTaskModel> T queryTimedTaskContentByKey(String keySuffix, String zSetValueAndHashKeys, Class<T> cls);
}