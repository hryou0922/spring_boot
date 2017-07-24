package com.hry.spring.redis.timedtask;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.hry.spring.redis.timedtask.simple.TimedTaskSimpleApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TimedTaskSimpleApplication.class)
public class TimedTaskServiceTest {
	@Autowired
	private ITimedTaskService timedTaskService;
	
	private String keySuffix = "test";
	private Date executeTime = new Date();
	private TestModel testModel = new TestModel();
	
	@Before
	public void init(){
		testModel.setId(ThreadLocalRandom.current().nextInt(1000) + "");
	}
	
	@Test
	public void add(){
		// add
		timedTaskService.add(keySuffix, executeTime, testModel);
//		// query
//		List<TestModel> testModelList = timedTaskService.getTimedTaskContent(keySuffix, TestModel.class);
//		// del
//		for(TestModel model : testModelList){
//			System.out.println("--" + JSON.toJSONString(model));
//			timedTaskService.bathDel(keySuffix, model.getId());
//		}
	}
	
	@Test
	public void getTimedTaskContent(){
		// query
		List<TestModel> testModelList = timedTaskService.getTimedTaskContent(keySuffix, TestModel.class);
		for(TestModel model : testModelList){
			System.out.println(JSON.toJSONString(model));
		}
	}
	
	
	@Test
	public void bathDel(){
		// bathDel
		String ids = "0a6df2c6-4963-42cd-94d0-840e58801d01";
		timedTaskService.bathDel(keySuffix, ids);
		 
	}
	
	@Test
	public void fullProcess(){
		// add
		timedTaskService.add(keySuffix, executeTime, testModel);
		// query
		List<TestModel> testModelList = timedTaskService.getTimedTaskContent(keySuffix, TestModel.class);
		// del
		for(TestModel model : testModelList){
			System.out.println("--" + JSON.toJSONString(model));
			timedTaskService.bathDel(keySuffix, model.getId());
		}
	}
}

class TestModel implements ITimedTaskModel{
	private String id;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
}
