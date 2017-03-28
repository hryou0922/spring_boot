package com.hry.spring.async;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 通过@EnableAsync启动异步方法
 * @author hry
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@EnableAsync // 启动异步调用
public class AsyncApplicationWithAnnotation {
	private static final Logger log = LoggerFactory.getLogger(AsyncApplicationWithAnnotation.class);
	
	/**
	 * 自定义异步线程池
	 * @return
	 */
    @Bean  
    public AsyncTaskExecutor taskExecutor() {  
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); 
        executor.setThreadNamePrefix("Annot-Executor");
        executor.setMaxPoolSize(10);  
        
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				// .....
			}
		});
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        
        return executor;  
    } 
    
	public static void main(String[] args) {
		log.info("Start AsyncApplication.. ");
		SpringApplication.run(AsyncApplicationWithAnnotation.class, args);
	}
}
