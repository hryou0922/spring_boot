package com.hry.spring.grpc.simple;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class HelloWorldClient {
	private static final Logger logger =  LoggerFactory.getLogger(HelloWorldClient.class);
	private final ManagedChannel channel;
	private final GreeterGrpc.GreeterBlockingStub blockingStub;

	/**
	 * Construct client connecting to HelloWorld server at {@code host:port}.
	 */
	public HelloWorldClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port)
				// Channels are secure by default (via SSL/TLS). For the example
				// we disable TLS to avoid
				// needing certificates.
				.usePlaintext(true));
	}

	/**
	 * Construct client for accessing RouteGuide server using the existing
	 * channel.
	 */
	HelloWorldClient(ManagedChannelBuilder<?> channelBuilder) {
		channel = channelBuilder.build();
		blockingStub = GreeterGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	/** Say hello to server. */
	public void greet(String name) {
		logger.info("Will try to greet " + name + " ...");
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloReply response;
		try {
			response = blockingStub.sayHello(request);
		} catch (StatusRuntimeException e) {
			logger.error("RPC failed: {0}", e.getStatus());
			return;
		}
		logger.info("Greeting: " + response.getMessage());
	}
	
	public static void main(String[] args) throws Exception {
		HelloWorldClient client = new HelloWorldClient("localhost", 50051);
		try {
			String user = "world";
			if (args.length > 0) {
				user = args[0]; 
			}
			client.greet(user);
		} finally {
			client.shutdown();
		}
	}
}