package com.hry.spring.redis.timedtask.repush;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RepushRunTestJob {
	private static final Logger logger = LoggerFactory.getLogger(RepushRunTestJob.class);
	
	@Autowired
	private RepushConsumerMsg repushConsumerMsg;
	
	@Autowired
	private IRetryPushService retryPushService;
	
	// 发送数量
	private int sendCount = 0;
	
	// 接受数量
	private int receiveCount = 0;
	
	/**
	 * 生成生推话单的任务
	 */
	@Scheduled(initialDelay=1000, fixedRate=1000)
	public void producerCdrs(){
		Date executeTime = new Date();
		int newCount = sendCount++;
		
		RetryPushModel model = new RetryPushModel();
		model.setId(String.valueOf(newCount));
		model.setContent("这是第"+ newCount + "次执行定时任务 CDRS！");
		model.setPushNextTime(executeTime);
		model.setType(RetryPushEnum.CDRS.getType());
		model.setUrl("http://127.0.0.1:test");
		model.setPushTime(0);
		
		retryPushService.save(model);
	}
	
	/**
	 * 生成生推短信的任务
	 * 
	 */
	@Scheduled(initialDelay=1000, fixedRate=1000)
	public void producerSms(){
		Date executeTime = new Date();
		int newCount = sendCount++;
		
		RetryPushModel model = new RetryPushModel();
		model.setId(String.valueOf(newCount));
		model.setContent("这是第"+ newCount + "次执行定时任务 CDRS！");
		model.setPushNextTime(executeTime);
		model.setType(RetryPushEnum.SMS.getType());
		model.setUrl("http://127.0.0.1:test");
		model.setPushTime(0);
		
		retryPushService.save(model);
	}
	
	@Scheduled(initialDelay=500, fixedRate=5000)
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
