package com.hry.spring.spel.multiway.programing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
/**
 * 通过注解使用Spring EL
 * 
 * @author hry
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpELProgramingApplication.class)
public class SpELProgramingExampleTest {
	@Autowired
	private SpELProgramingExample spELProgramingExample;
	
	@Test
	public void println(){
		spELProgramingExample.init();
		System.out.println(spELProgramingExample.toString());
	}
	
}


