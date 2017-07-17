package com.hry.spring.redis.timedtask.simple;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OneRunTestJob {
	private static final Logger logger = LoggerFactory.getLogger(OneRunTestJob.class);
	
	@Autowired
	private IOnceRunService onceRunService;
	
	// 发送数量
	private int sendCount = 0;
	
	// 接受数量
	private int receiveCount = 0;
	
	@Scheduled(initialDelay=1000, fixedRate=1000)
	public void producer(){
		Date executeTime = new Date();
		int newCount = sendCount++;
		
		OnceRunModel model = new OnceRunModel();
		model.setId(String.valueOf(newCount));
		model.setContent("这是第"+ newCount + "次执行定时任务！");
		
		onceRunService.save(model, executeTime);
	}
	
	@Scheduled(initialDelay=500, fixedRate=5000)
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
