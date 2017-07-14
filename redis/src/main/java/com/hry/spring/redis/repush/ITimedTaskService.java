package com.hry.spring.redis.repush;

import java.util.Date;
import java.util.List;

import com.hry.spring.redis.repush.support.IModel;

public interface ITimedTaskService{
	// 作为定时器 存储到key的后缀
	static final String BATCH_BIND_KEY_SUFFIX = "batchBind";
	static final String RETRY_PUSH_KEY_SUFFIX = "retryPush";
	
	/**
	 * 添加需要定时执行的任务
	 * @param keySuffix
	 * @param executeTime 执行的时间
	 * @param value
	 */
	<T extends IModel> T add(String keySuffix, Date executeTime, T value);

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
	<T extends IModel> List<T> getTimedTaskContent(String keySuffix,
			Class<T> cls);

	/**
	 * 获取所有的定时任务
	 * @param keySuffix
	 * @param cls
	 * @return
	 */
	<T extends IModel> List<T> queryAllTimedTaskContent(String keySuffix,
			Class<T> cls);

	<T extends IModel> T queryTimedTaskContentByKey(String keySuffix, String zSetValueAndHashKeys, Class<T> cls);
}