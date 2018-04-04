package com.hry.spring.schedule.custom;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * 通过实现SchedulingConfigurer对定时任务线程池进行更细致配置
 * @author hry
 *
 */
@Component
@Configuration
public class MySchedulingConfigurer implements SchedulingConfigurer  {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
		return  new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
        	private AtomicInteger max = new AtomicInteger(0);
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "myScheConfig-" + max.incrementAndGet());
			}
		});
    }
}
