package com.hry.spring.redis.timedtask.repush;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TimedTaskRepushApplication.class)
public class RepushRunTest {

	@Autowired
	private IRetryPushService retryPushService;
	
	
	
	/**
	 * 生成生推话单的任务
	 */
	@Test
	public void producerCdrs(){
		// 发送数量
		int sendMaxCount = 10;
		int newCount = 1;
		while(newCount++ < sendMaxCount){
			Date executeTime = new Date();
		
			
			RetryPushModel model = new RetryPushModel();
			model.setId(String.valueOf(newCount));
			model.setContent("这是第"+ newCount + "次执行定时任务 CDRS！");
			model.setPushNextTime(executeTime);
			model.setType(RetryPushEnum.CDRS.getType());
			model.setUrl("http://127.0.0.1:consumer");
			model.setPushTime(0);
			
			retryPushService.save(model);
		}
	}
	
	/**
	 * 生成生推短信的任务
	 * 
	 */
	@Test
	public void producerSms(){
		// 发送数量
		int sendMaxCount = 10;
		int newCount = 1;
		while(newCount++ < sendMaxCount){
			Date executeTime = new Date();
			
			RetryPushModel model = new RetryPushModel();
			model.setId(String.valueOf(newCount));
			model.setContent("这是第"+ newCount + "次执行定时任务 CDRS！");
			model.setPushNextTime(executeTime);
			model.setType(RetryPushEnum.SMS.getType());
			model.setUrl("http://127.0.0.1:consumer");
			model.setPushTime(0);
			
			retryPushService.save(model);
		}
	}
	
}
