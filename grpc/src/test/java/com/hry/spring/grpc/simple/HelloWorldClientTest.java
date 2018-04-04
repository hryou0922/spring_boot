package com.hry.spring.grpc.simple;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;

import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Unit tests for {@link HelloWorldClient}.
 * For demonstrating how to write gRPC unit consumer only.
 * Not intended to provide a high code coverage or to consumer every major usecase.
 *
 * <p>For more unit consumer examples see {@link io.grpc.examples.routeguide.RouteGuideClientTest} and
 * {@link io.grpc.examples.routeguide.RouteGuideServerTest}.
 */
@RunWith(JUnit4.class)
public class HelloWorldClientTest {
  private final GreeterGrpc.GreeterImplBase serviceImpl = spy(new GreeterGrpc.GreeterImplBase() {});

  private Server fakeServer;
  private HelloWorldClient client;

  /**
   * Creates and starts a fake in-process server, and creates a client with an in-process channel.
   */
  @Before
  public void setUp() throws Exception {
    String uniqueServerName = "fake server for " + getClass();
    fakeServer = InProcessServerBuilder
        .forName(uniqueServerName).directExecutor().addService(serviceImpl).build().start();
    InProcessChannelBuilder channelBuilder =
        InProcessChannelBuilder.forName(uniqueServerName).directExecutor();
    client = new HelloWorldClient(channelBuilder);
  }

  /**
   * Shuts down the client and server.
   */
  @After
  public void tearDown() throws Exception {
    client.shutdown();
    fakeServer.shutdownNow();
  }

  /**
   * To consumer the client, call from the client against the fake server, and verify behaviors or state
   * changes from the server side.
   */
  @Test
  public void greet_messageDeliveredToServer() {
    ArgumentCaptor<HelloRequest> requestCaptor = ArgumentCaptor.forClass(HelloRequest.class);
    String testName = "consumer name";

    client.greet(testName);

    verify(serviceImpl)
        .sayHello(requestCaptor.capture(), Matchers.<StreamObserver<HelloReply>>any());
    assertEquals(testName, requestCaptor.getValue().getName());
  }
}