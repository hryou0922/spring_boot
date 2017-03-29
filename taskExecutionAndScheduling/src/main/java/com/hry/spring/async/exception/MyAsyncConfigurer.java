package com.hry.spring.async.exception;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 通过实现AsyncConfigurer自定义异常线程池，包含异常处理
 * 
 * @author hry
 *
 */
@Service
public class MyAsyncConfigurer implements AsyncConfigurer{
	private static final Logger log = LoggerFactory.getLogger(MyAsyncConfigurer.class);
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();  
        threadPool.setCorePoolSize(1);  
        threadPool.setMaxPoolSize(1);  
        threadPool.setWaitForTasksToCompleteOnShutdown(true);  
        threadPool.setAwaitTerminationSeconds(60 * 15);  
        threadPool.setThreadNamePrefix("MyAsync-");
        threadPool.initialize();
        return threadPool;  
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		 return new MyAsyncExceptionHandler();  
	}
	
	/**
	 * 自定义异常处理类
	 * @author hry
	 *
	 */
	class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {  
		   
	    @Override  
	    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {  
	        log.info("Exception message - " + throwable.getMessage());  
	        log.info("Method name - " + method.getName());  
	        for (Object param : obj) {  
	        	log.info("Parameter value - " + param);  
	        }  
	    }  
	       
	} 
	
}

