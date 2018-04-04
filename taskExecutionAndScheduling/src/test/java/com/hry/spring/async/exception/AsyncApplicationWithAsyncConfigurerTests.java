package com.hry.spring.async.exception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hry.spring.async.exception.AsyncApplicationWithAsyncConfigurer;
import com.hry.spring.async.exception.AsyncExceptionDemo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AsyncApplicationWithAsyncConfigurer.class)
public class AsyncApplicationWithAsyncConfigurerTests {
	@Autowired
	private AsyncExceptionDemo asyncDemo;
	
	@Test
	public void contextLoads() throws InterruptedException, ExecutionException {
		asyncDemo.asyncInvokeSimplest();
		asyncDemo.asyncInvokeWithException("test");
		Future<String> future = asyncDemo.asyncInvokeReturnFuture(100);
		System.out.println(future.get());
	}
}
