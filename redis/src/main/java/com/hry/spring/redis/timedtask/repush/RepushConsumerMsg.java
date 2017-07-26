package com.hry.spring.redis.timedtask.repush;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消费重推任务
 * 
 * @author hry
 *
 */
@Service
public class RepushConsumerMsg {
	private static final Logger logger = LoggerFactory.getLogger(RepushRunConsumerJob.class);
	
	@Autowired
	private IRetryPushService retryPushService;
	
	// 每次重推送的间隔：单位s
	private List<Integer> retryIntervalSecondsList;
	
	// 最大的重推次数
	private Integer maxPushTime;
		
	@PostConstruct
	public void init(){
		// 每次重推送的间隔：单位s
		retryIntervalSecondsList = new ArrayList<Integer>();
		retryIntervalSecondsList.add(5);
		retryIntervalSecondsList.add(10);
		retryIntervalSecondsList.add(15);
		
		// 最大推送次数
		maxPushTime = 4;
	}
	
	
	
	/**
	 * 模拟推送
	 * 	1. 随机设置本次请求成功，如果推送成功，则此请求结束
	 *  2. 如果推送失败，则
	 *  	a. 如果重推超过maxPushTime，则不在推送
	 *  	b. 如果重推不超过maxPushTime，则设置下次推送时间，则储存到redis中，等待下次执行
	 * @param model
	 */
	public void execute(RetryPushModel model){
		int time = model.getPushTime() == null ? 1: (model.getPushTime() < 1 ? 1 : model.getPushTime());
		String url = model.getUrl();
		// 这里使用随机函数进行判定如果 > 5，则认为发送到url成功
		boolean isNeedRetry = true;
		if(ThreadLocalRandom.current().nextInt(10) > 5){
			// 模拟调用第三方url
			logger.info("模拟调用[{}]成功，内容如下:[{}]", url, model);
			isNeedRetry = false;
		}else if(time > maxPushTime){
			isNeedRetry = false;
			logger.info("重推[{}]仍然失败，不再重推，接受者url[{}],内容信息如下[{}]", time, url, model);
		}
		if(isNeedRetry){
			// 失败需要重推送
			generateRetryPush(url, time, model.getContent(), model.getType());
		}
		
	}
	
	
	/**
	 * 获取下次执行的时间
	 * @param time
	 * @return
	 */
	protected Date getPushNextTime(int time) {
		long delayTime;
		int size = retryIntervalSecondsList.size();
		// 次数从1开始，索引从0开始
		if(size >= time){
			delayTime =  retryIntervalSecondsList.get(time-1) * 1000;
		}else if(size > 0){
			// 超过配置的次数，则延迟时间使用最后一次
			delayTime = retryIntervalSecondsList.get(retryIntervalSecondsList.size() - 1) * 1000;
		}else{
			delayTime = 300 * 1000; // 延迟时间，默认5分钟
		}
		Date rtnDate = new Date(System.currentTimeMillis() + delayTime);   	
		return rtnDate;
	}
	
    /**
     * 生成重推消息
     * 	如果超过重推最大次数，则重推结束
     * @param url
     * @param time
     * @param jsonBody
     * @param sessionId
     */
	protected void generateRetryPush(String url, int time, String jsonBody, int type) {
		time++; //  // 推送次数+1
		RetryPushModel model = new RetryPushModel();
		model.setContent(time + "_" + jsonBody);
		model.setType(type);
		model.setUrl(url);
		model.setPushTime(time+1); 
		model.setPushNextTime(getPushNextTime(time));
		retryPushService.save(model);
	}
}
