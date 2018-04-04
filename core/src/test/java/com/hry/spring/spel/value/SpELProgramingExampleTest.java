package com.hry.spring.spel.value;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hry.spring.spel.value.SpELAnnotationApplication;
import com.hry.spring.spel.value.SpELAnnotationExample;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpELAnnotationApplication.class)
public class SpELProgramingExampleTest {
	@Autowired
	private SpELAnnotationExample spELAnnotationExample;
	
	@Test
	public void spELAnnotationExample(){
		System.out.println(spELAnnotationExample.toString());
	}
}
