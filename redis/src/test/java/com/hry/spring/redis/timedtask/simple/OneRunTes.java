package com.hry.spring.redis.timedtask.simple;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TimedTaskSimpleApplication.class)
public class OneRunTes {
	
	@Autowired
	private IOnceRunService onceRunService;
	
	@Test
	public void producer(){
		// 发送数量
		int sendMaxCount = 10;
		int newCount = 0;
		while(newCount++ < sendMaxCount){
			Date executeTime = new Date();
			
			
			OnceRunModel model = new OnceRunModel();
			model.setId(String.valueOf(newCount));
			model.setContent("这是第"+ newCount + "次执行定时任务！");
			
			onceRunService.save(model, executeTime);
		}
		
	}
	
}
