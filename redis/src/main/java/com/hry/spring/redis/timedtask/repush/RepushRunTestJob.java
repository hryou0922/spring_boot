package com.hry.spring.redis.timedtask.repush;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RepushRunTestJob {
	private static final Logger logger = LoggerFactory.getLogger(RepushRunTestJob.class);
	
	@Autowired
	private IRetryPushService retryPushService;
	
	// 发送数量
	private int sendCount = 0;
	
	// 接受数量
	private int receiveCount = 0;
	
	// 每次重推送的间隔：单位s
	private List<Integer> retryIntervalSecondsList;
	
	// 最后的重推次数
	private Integer maxPushTime;
	
	@PostConstruct
	public void init(){
		
	}
	
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
			case SMS: logger.info("{}, 处理SMS请求 :{}", newReceiverCount, model); break;
			case CDRS:logger.info("{}, 处理CDRS请求 :{}", newReceiverCount, model); break;
			default :
				
			}
			// 处理完后，删除记录
			retryPushService.delete(model.getId());
		}
	}
	
	private void execute(RetryPushModel model){
		String url = model.getUrl();
		// 这里使用随机函数进行判定如果 > 5，则认为发送到url成功
		boolean isSuc = false;
		if(ThreadLocalRandom.current().nextInt(10) > 5){
			isSuc = true;
		}
		
	}
}
