package com.hry.spring.redis.timedtask.simple;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时执行消费
 * @author hry
 *
 */
@Component
public class OneRunConsumerJob {
	private static final Logger logger = LoggerFactory.getLogger(OneRunConsumerJob.class);
	
	@Autowired
	private IOnceRunService onceRunService;
	
	// 接受数量
	private int receiveCount = 0;
	
	@Scheduled(initialDelay=3000, fixedRate=5000)
	public void consumer(){
		List<OnceRunModel> list = onceRunService.queryAll();
		for(OnceRunModel model : list){
			int newReceiverCount = receiveCount++;
			logger.info("{}, 处理请求 :{}", newReceiverCount, model);
			// 处理完后，删除记录
			onceRunService.delete(model.getId());
		}
	}
}
