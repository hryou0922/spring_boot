package com.hry.spring.grpc.mystream.custom;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hry.spring.grpc.mystream.HelloStreamGrpc;
import com.hry.spring.grpc.mystream.Simple;
import com.hry.spring.grpc.mystream.SimpleFeature;
import com.hry.spring.grpc.mystream.SimpleList;
import com.hry.spring.grpc.mystream.SimpleSummary;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloStreamServer {
	private static final Logger logger = LoggerFactory.getLogger(HelloStreamServer.class);

	private final int port;
	private final Server server;

	public HelloStreamServer(int port) throws IOException {
		this.port = port;
		this.server = ServerBuilder.forPort(port).addService(new HelloStreamService(HelloUtil.parseFeatures())).build();
	}

	// 启动服务
	public void start() throws IOException {
		server.start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				HelloStreamServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	// 启动服务
	public void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon
	 * threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	public static void main(String[] args) throws Exception {
		java.util.logging.Logger.getGlobal().setLevel(java.util.logging.Level.OFF);
		HelloStreamServer server = new HelloStreamServer(8980);
		server.start();
		server.blockUntilShutdown();
	}

	/**
	 * 服务端类的实现
	 *
	 */
	private static class HelloStreamService extends HelloStreamGrpc.HelloStreamImplBase {
		private final List<SimpleFeature> features;

		public HelloStreamService(List<SimpleFeature> features) {
			this.features = features;
		}

		@Override
		public void simpleRpc(Simple request, StreamObserver<SimpleFeature> responseObserver) {
			SimpleFeature rtn = SimpleFeature.newBuilder().setName(request.getName() + "simpleRpc").setLocation(request)
					.build();
			logger.info("recevier simpleRpc ： {}", request);
			responseObserver.onNext(rtn);
			responseObserver.onCompleted();
		}

		@Override
		public void server2ClientRpc(SimpleList request, StreamObserver<SimpleFeature> responseObserver) {
			logger.info("recevier server2ClientRpc ： {}", request);
			for (SimpleFeature feature : this.features) {
				Simple simpleLocation = feature.getLocation();
				for (Simple o : request.getSimpleListList()) {
					if (o.getNum() == simpleLocation.getNum()) {
						// 推送记录
						responseObserver.onNext(feature);
					}
				}
			}
			responseObserver.onCompleted();
		}

		/**
		 * 接收完所有的请求后，才返回一个对象
		 */
		@Override
		public StreamObserver<Simple> client2ServerRpc(StreamObserver<SimpleSummary> responseObserver) {
			
			return new StreamObserver<Simple>() {
				int feature_count = 0;

				@Override
				public void onNext(Simple value) {
					// 接收请求
					logger.info("num={}, client2ServerRpc, content={} ", feature_count, value);
					feature_count++;
				}

				@Override
				public void onError(Throwable t) {
					logger.error("Simple cancelled, e={}", t);
				}

				@Override
				public void onCompleted() {
					logger.info("onCompleted");
					// 接收所有请求后，返回总数
					SimpleSummary summary = SimpleSummary.newBuilder().setFeatureCount(feature_count).build();
					responseObserver.onNext(summary);
					// 结束请求
					responseObserver.onCompleted();
				}
			};
		}

		/**
		 * 每接收一个请求，立即返回一个对象
		 */
		@Override
		public StreamObserver<Simple> bindirectionalStreamRpc(StreamObserver<Simple> responseObserver) {
			return new StreamObserver<Simple>() {
				@Override
				public void onNext(Simple value) {
					logger.info("bindirectionalStreamRpc receive {}", value);
					for (SimpleFeature feature : features) {
						Simple simpleLocation = feature.getLocation();
						if (value.getNum() == simpleLocation.getNum()) {
							// 接收请求后，马上推送记录
							Simple rtn = Simple.newBuilder().setName(feature.getName() + "rtn")
									.setNum(feature.getLocation().getNum()).build();
							responseObserver.onNext(rtn);
						}
					}
				}

				@Override
				public void onError(Throwable t) {
					logger.error("bindirectionalStreamRpc cancelled, e={}", t);
				}

				@Override
				public void onCompleted() {
					responseObserver.onCompleted();
				}
			};
		}
	}
}
