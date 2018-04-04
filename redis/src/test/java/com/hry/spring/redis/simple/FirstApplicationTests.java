package com.hry.spring.redis.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FirstApplication.class)
public class FirstApplicationTests {
	@Autowired
	private FirstExample firstExample;
	
	@Test
	public void save(){
		firstExample.save("name", "value");
	}
	
	@Test
	public void luaScript(){
		firstExample.luaScript("expectedValue", "newValue");		
	}
}
