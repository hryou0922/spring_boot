package com.hry.spring.async.annotation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hry.spring.async.annotation.AsyncApplicationWithAnnotation;
import com.hry.spring.async.annotation.AsyncDemo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AsyncApplicationWithAnnotation.class)
public class AsyncApplicationWithAnnotationTests {
	@Autowired
	private AsyncDemo asyncDemo;
	
	@Test
	public void contextLoads() throws InterruptedException, ExecutionException {
		asyncDemo.asyncInvokeSimplest();
		asyncDemo.asyncInvokeWithParameter("test");
		Future<String> future = asyncDemo.asyncInvokeReturnFuture(100);
		System.out.println(future.get());
	}
}
