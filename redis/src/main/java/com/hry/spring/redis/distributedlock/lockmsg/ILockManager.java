package com.hry.spring.redis.distributedlock.lockmsg;

/**
 * 分布式锁MSG：  封装分布锁使用
 * @author hry
 *
 */
public interface ILockManager {
	/**
	 * 通过加锁安全执行程序，无返回的数据
	 * @param lockKeyName key名称
	 * @param callback	
	 */
	void lockCallBack(String lockKeyName, SimpleCallBack callback);
	/**
	 * 通过加锁安全执行程序，有返回数据
	 * @param lockKeyName
	 * @param callback
	 * @return
	 */
	<T> T lockCallBackWithRtn(String lockKeyName, ReturnCallBack<T> callback);
}
