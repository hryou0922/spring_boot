package com.hry.spring.schedule.xml;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时类
 * 	不同异步方法：定时方法只能返回void且不能接受任务参数 
 * 
 * @author hry
 *
 */
@Component
public class ScheduleDemo {
	private static final Logger log = Logger.getLogger(ScheduleDemo.class);
	
	/**
	 * 每次方法执行完毕后，等待5s再执行此方法。
	 * 	同时只能有个线程运行此方法
	 */
	@Scheduled(fixedDelay=5000)
	public void fixedDelay() {
		try {
			// 执行方法需要10s
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
		}
		log.info("fixedDelay--");
	}
	
	/**
	 * 每隔5s调用一次此方法，无论之前的方法是否执行完毕
	 * 	同时可能有N个线程执行此方法
	 * 	
	 */
	@Scheduled(fixedRate=5000)
	public void fixedRate() {
		try {
			// 执行方法需要10s
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
		}
		log.info("fixedRate--");
	}
	
	/***
	 * initialDelay: 第一次调用此方法前的等待时间
	 * 
	 */ 
	@Scheduled(initialDelay=1000, fixedRate=5000)
	public void initialDelayAndfixedRate() {
		log.info("initialDelayAndfixedRate--");
	}
	
	/**
	 * 支持cron语法：
	 * 每个参数的意义分别是： second, minute, hour, day of month, month, day of week
	 * 
	 * 如下：周一至周五，每隔5s执行一次方法
	 */
	@Scheduled(cron="*/5 * * * * SUN-MON")
	public void cron() {
		log.info("cron--");
	}
}
