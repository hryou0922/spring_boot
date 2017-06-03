package com.hry.spring.spel.multiway.xml;

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
@SpringBootTest(classes=SpELXmlApplication.class)
public class SpELAnnotationExampleTest {
	@Autowired
	private SpELXMLExample spELXMLExample;
	
	@Test
	public void println(){
		System.out.println(spELXMLExample.toString());
	}
	
}


