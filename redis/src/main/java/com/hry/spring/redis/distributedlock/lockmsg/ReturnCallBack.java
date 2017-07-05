package com.hry.spring.redis.distributedlock.lockmsg;

/**
 * 有返回数据的回调函数
 * 
 * @author hry
 *
 * @param <T>
 */
public interface ReturnCallBack<T> {
	T execute();
}
