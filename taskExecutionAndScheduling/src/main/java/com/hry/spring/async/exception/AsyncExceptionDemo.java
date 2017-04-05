package com.hry.spring.async.exception;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 异步方法调用
 * 
 * @author hry
 *
 */
@Component
public class AsyncExceptionDemo {
	private static final Logger log = LoggerFactory.getLogger(AsyncExceptionDemo.class);

	/**
	 * 最简单的异步调用，返回值为void
	 */
	@Async
	public void asyncInvokeSimplest() {
		log.info("asyncSimplest");
	}

	/**
	 * 带参数的异步调用 异步方法可以传入参数
	 * 	对于返回值是void，异常会被AsyncUncaughtExceptionHandler处理掉
	 * @param s
	 */
	@Async
	public void asyncInvokeWithException(String s) {
		log.info("asyncInvokeWithParameter, parementer={}", s);
		throw new IllegalArgumentException(s);
	}

	/**
	 * 异常调用返回Future
	 * 	对于返回值是Future，不会被AsyncUncaughtExceptionHandler处理，需要我们在方法中捕获异常并处理
	 *  或者在调用方在调用Futrue.get时捕获异常进行处理
	 * 
	 * @param i
	 * @return
	 */
	@Async
	public Future<String> asyncInvokeReturnFuture(int i) {
		log.info("asyncInvokeReturnFuture, parementer={}", i);
		Future<String> future;
		try {
			Thread.sleep(1000 * 1);
			future = new AsyncResult<String>("success:" + i);
			throw new IllegalArgumentException("a");
		} catch (InterruptedException e) {
			future = new AsyncResult<String>("error");
		} catch(IllegalArgumentException e){
			future = new AsyncResult<String>("error-IllegalArgumentException");
		}
		return future;
	}

}
