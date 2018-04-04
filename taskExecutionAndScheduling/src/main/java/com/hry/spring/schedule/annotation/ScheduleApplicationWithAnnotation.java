package com.hry.spring.schedule.annotation;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 通过@EnableScheduling启动异步方法
 * @author hry
 * 
 */
@SpringBootApplication
@EnableScheduling // 启动定时任务
public class ScheduleApplicationWithAnnotation {
	private static final Logger log = LoggerFactory.getLogger(ScheduleApplicationWithAnnotation.class);
    
	/**
	 * 自定义定时任务线程池
	 * 	如果没有，则使用默认定时任务池
	 * @return
	 */
	@Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return  new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
        	private AtomicInteger max = new AtomicInteger(0);
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "mySchedulAnno-" + max.incrementAndGet());
			}
		});
    }
	
	public static void main(String[] args) {
		log.info("Start ScheduleApplicationWithAnnotation.. ");
		SpringApplication.run(ScheduleApplicationWithAnnotation.class, args);
	}
}
