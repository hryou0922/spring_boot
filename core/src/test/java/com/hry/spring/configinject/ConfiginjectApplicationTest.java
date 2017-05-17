package com.hry.spring.configinject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class ConfiginjectApplicationTest {
	@Autowired
	private BaseValueInject baseValueInject;
	
	@Autowired
	private ConfigurationFileInject configurationFileInject;
	
	@Autowired
	private AdvanceValueInject advanceValueInject;
	
	@Test
	public void baseValueInject(){
		System.out.println(baseValueInject.toString());
	}
	
	@Test
	public void configurationFileInject(){
		System.out.println(configurationFileInject.toString());
	}
	
	@Test
	public void advanceValueInject(){
		System.out.println(advanceValueInject.toString());
	}
}
