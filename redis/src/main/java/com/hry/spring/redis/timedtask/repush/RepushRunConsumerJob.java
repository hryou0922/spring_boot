package com.hry.spring.redis.timedtask.repush;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时消费任务
 * @author hry
 *
 */
@Component
public class RepushRunConsumerJob {
	private static final Logger logger = LoggerFactory.getLogger(RepushRunConsumerJob.class);
	
	@Autowired
	private RepushConsumerMsg repushConsumerMsg;
	
	@Autowired
	private IRetryPushService retryPushService;
	
	// 接受数量
	private int receiveCount = 0;
	
	
	@Scheduled(initialDelay=3000, fixedRate=5000)
	public void consumer(){
		List<RetryPushModel> list = retryPushService.queryAll();
		for(RetryPushModel model : list){
			int newReceiverCount = receiveCount++;
			// 根据不同的重推对象，进行不同的逻辑处理
			RetryPushEnum retryPushEnum = RetryPushEnum.getRetryPushEnum(model.getType());
			switch (retryPushEnum){
			case SMS: repushConsumerMsg.execute(model); break;
			case CDRS:repushConsumerMsg.execute(model); break;
			default :
				logger.error("类型[{}]没有处理对象，内容如下[{}]", model.getType(), retryPushEnum);
			}
			// 处理完后，删除记录
			retryPushService.delete(model.getId());
			logger.info("已经处理[{}]条记录", newReceiverCount);
		}
	}
	
}
