package com.hry.spring.grpc.mystream.custom;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hry.spring.grpc.mystream.HelloStreamGrpc;
import com.hry.spring.grpc.mystream.HelloStreamGrpc.HelloStreamBlockingStub;
import com.hry.spring.grpc.mystream.HelloStreamGrpc.HelloStreamStub;
import com.hry.spring.grpc.mystream.Simple;
import com.hry.spring.grpc.mystream.SimpleFeature;
import com.hry.spring.grpc.mystream.SimpleList;
import com.hry.spring.grpc.mystream.SimpleSummary;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class HelloStreamClient {
	private static final Logger logger = LoggerFactory.getLogger(HelloStreamClient.class);

	private final ManagedChannel channel;
	private final HelloStreamBlockingStub blockingStub;
	private final HelloStreamStub asyncStub;

	private Random random = new Random();

	public HelloStreamClient(String host, int port) {
		ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true);
		channel = channelBuilder.build();
		// 创建一个阻塞客户端，支持简单一元服务和流输出调用服务
		blockingStub = HelloStreamGrpc.newBlockingStub(channel);
		// 创建一个异步客户端，支持所有类型调用
		asyncStub = HelloStreamGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	/**
	 * 一元服务调用
	 */
	public void simpleRpc(int num) {
		logger.info("request simpleRpc: num={}", num);
		Simple simple = Simple.newBuilder().setName("simpleRpc").setNum(num).build();
		SimpleFeature feature;
		try {
			feature = blockingStub.simpleRpc(simple);
		} catch (StatusRuntimeException e) {
			logger.info("RPC failed: {0}", e.getStatus());
			return;
		}
		logger.info("simpleRpc end called {}", feature);
	}

	/**
	 * 阻塞服务器流
	 */
	public void server2ClientRpc(int num1, int num2) {
		logger.info("request server2ClientRpc num1={}, num2={}", num1, num2);
		Simple simple = Simple.newBuilder().setName("simple2" + num1).setNum(num1).build();
		Simple simple2 = Simple.newBuilder().setName("simple2" + num2).setNum(num2).build();

		SimpleList simpleList = SimpleList.newBuilder().addSimpleList(simple).addSimpleList(simple2).build();
		Iterator<SimpleFeature> simpleFeatureIter = blockingStub.server2ClientRpc(simpleList);
		for (int i = 1; simpleFeatureIter.hasNext(); i++) {
			SimpleFeature feature = simpleFeatureIter.next();
			logger.info("Result {} : {}", i, feature);
		}
	}

	/**
	 * 异步客户端流
	 * 
	 */
	public void client2ServerRpc(int count) throws InterruptedException {
		logger.info("request client2ServerRpc {}", count);
		final CountDownLatch finishLatch = new CountDownLatch(1);

		StreamObserver<SimpleSummary> responseObserver = new StreamObserver<SimpleSummary>() {
			@Override
			public void onNext(SimpleSummary value) {
				// 返回SimpleSummary
				logger.info("client2ServerRpc onNext : {}", value);
			}

			@Override
			public void onError(Throwable t) {
				logger.error("client2ServerRpc error : {}", t);
				finishLatch.countDown();
			}

			@Override
			public void onCompleted() {
				logger.error("client2ServerRpc finish");
				finishLatch.countDown();
			}
		};
		StreamObserver<Simple> requestObserver = asyncStub.client2ServerRpc(responseObserver);
		try {
			for (int i = 0; i < count; i++) {
				logger.info("simple2 : {}", i);
				Simple simple = Simple.newBuilder().setName("client2ServerRpc" + i).setNum(i).build();
				requestObserver.onNext(simple);
				Thread.sleep(random.nextInt(200) + 50);
			}
		} catch (RuntimeException e) {
			// Cancel RPC
			requestObserver.onError(e);
			throw e;
		}
		// 结束请求
		requestObserver.onCompleted();

		// Receiving happens asynchronously
		if (!finishLatch.await(1, TimeUnit.MINUTES)) {
			logger.error("client2ServerRpc can not finish within 1 minutes");
		}
	}

	/**
	 * 双向流
	 * 
	 * @throws InterruptedException
	 */
	public void bindirectionalStreamRpc() throws InterruptedException {
		logger.info("request bindirectionalStreamRpc");
		final CountDownLatch finishLatch = new CountDownLatch(1);
		StreamObserver<Simple> requestObserver = asyncStub.bindirectionalStreamRpc(new StreamObserver<Simple>() {

			@Override
			public void onNext(Simple value) {
				logger.info("bindirectionalStreamRpc receive message : {}", value);
			}

			@Override
			public void onError(Throwable t) {
				logger.error("bindirectionalStreamRpc Failed: {0}", Status.fromThrowable(t));
				finishLatch.countDown();
			}

			@Override
			public void onCompleted() {
				logger.info("Finished bindirectionalStreamRpc");
				finishLatch.countDown();
			}
		});

		try {
			Simple[] requests = { newSimple(1), newSimple(2), newSimple(3), newSimple(4) };

			for (Simple request : requests) {
				logger.info("Sending message {}", request);
				requestObserver.onNext(request);
			}
		} catch (RuntimeException e) {
			// Cancel RPC
			requestObserver.onError(e);
			throw e;
		}
		requestObserver.onCompleted();

		if (!finishLatch.await(1, TimeUnit.MINUTES)) {
			logger.error("routeChat can not finish within 1 minutes");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		HelloStreamClient client = new HelloStreamClient("localhost", 8980);
	//	java.util.logging.Logger.getGlobal().setLevel(java.util.logging.Level.OFF); 
//		SLF4JBridgeHandler.removeHandlersForRootLogger();
//		SLF4JBridgeHandler.install();
		logger.debug("--------------------sssssssssss-------------");
		try {
			// simple2 rpc
			client.simpleRpc(1);

			// server2ClientRpc
			client.server2ClientRpc(1, 2);

			// client2ServerRpc
			client.client2ServerRpc(2);

			// bindirectionalStreamRpc
			client.bindirectionalStreamRpc();

		} finally {
			client.shutdown();
		}
	}

	// 创建Simple对象
	private Simple newSimple(int num) {
		return Simple.newBuilder().setName("simple2" + num).setNum(num).build();
	}

}
