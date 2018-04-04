package com.hry.spring.grpc.mystream;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: mystream/hello_stream.proto")
public final class HelloStreamGrpc {

  private HelloStreamGrpc() {}

  public static final String SERVICE_NAME = "HelloStream";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Simple,
      SimpleFeature> METHOD_SIMPLE_RPC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "HelloStream", "simpleRpc"),
          io.grpc.protobuf.ProtoUtils.marshaller(Simple.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(SimpleFeature.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<SimpleList,
      SimpleFeature> METHOD_SERVER2CLIENT_RPC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "HelloStream", "server2ClientRpc"),
          io.grpc.protobuf.ProtoUtils.marshaller(SimpleList.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(SimpleFeature.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Simple,
      SimpleSummary> METHOD_CLIENT2SERVER_RPC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "HelloStream", "client2ServerRpc"),
          io.grpc.protobuf.ProtoUtils.marshaller(Simple.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(SimpleSummary.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Simple,
      Simple> METHOD_BINDIRECTIONAL_STREAM_RPC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "HelloStream", "bindirectionalStreamRpc"),
          io.grpc.protobuf.ProtoUtils.marshaller(Simple.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Simple.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloStreamStub newStub(io.grpc.Channel channel) {
    return new HelloStreamStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloStreamBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HelloStreamBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static HelloStreamFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HelloStreamFutureStub(channel);
  }

  /**
   */
  public static abstract class HelloStreamImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * A simple2 RPC.
     * </pre>
     */
    public void simpleRpc(Simple request,
        io.grpc.stub.StreamObserver<SimpleFeature> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SIMPLE_RPC, responseObserver);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC.
     * </pre>
     */
    public void server2ClientRpc(SimpleList request,
        io.grpc.stub.StreamObserver<SimpleFeature> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SERVER2CLIENT_RPC, responseObserver);
    }

    /**
     * <pre>
     * A client-to-server streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<Simple> client2ServerRpc(
        io.grpc.stub.StreamObserver<SimpleSummary> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_CLIENT2SERVER_RPC, responseObserver);
    }

    /**
     * <pre>
     * A Bidirectional streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<Simple> bindirectionalStreamRpc(
        io.grpc.stub.StreamObserver<Simple> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_BINDIRECTIONAL_STREAM_RPC, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SIMPLE_RPC,
            asyncUnaryCall(
              new MethodHandlers<
                Simple,
                SimpleFeature>(
                  this, METHODID_SIMPLE_RPC)))
          .addMethod(
            METHOD_SERVER2CLIENT_RPC,
            asyncServerStreamingCall(
              new MethodHandlers<
                SimpleList,
                SimpleFeature>(
                  this, METHODID_SERVER2CLIENT_RPC)))
          .addMethod(
            METHOD_CLIENT2SERVER_RPC,
            asyncClientStreamingCall(
              new MethodHandlers<
                Simple,
                SimpleSummary>(
                  this, METHODID_CLIENT2SERVER_RPC)))
          .addMethod(
            METHOD_BINDIRECTIONAL_STREAM_RPC,
            asyncBidiStreamingCall(
              new MethodHandlers<
                Simple,
                Simple>(
                  this, METHODID_BINDIRECTIONAL_STREAM_RPC)))
          .build();
    }
  }

  /**
   */
  public static final class HelloStreamStub extends io.grpc.stub.AbstractStub<HelloStreamStub> {
    private HelloStreamStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloStreamStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloStreamStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloStreamStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple2 RPC.
     * </pre>
     */
    public void simpleRpc(Simple request,
        io.grpc.stub.StreamObserver<SimpleFeature> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SIMPLE_RPC, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC.
     * </pre>
     */
    public void server2ClientRpc(SimpleList request,
        io.grpc.stub.StreamObserver<SimpleFeature> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_SERVER2CLIENT_RPC, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * A client-to-server streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<Simple> client2ServerRpc(
        io.grpc.stub.StreamObserver<SimpleSummary> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_CLIENT2SERVER_RPC, getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * A Bidirectional streaming RPC.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<Simple> bindirectionalStreamRpc(
        io.grpc.stub.StreamObserver<Simple> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_BINDIRECTIONAL_STREAM_RPC, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class HelloStreamBlockingStub extends io.grpc.stub.AbstractStub<HelloStreamBlockingStub> {
    private HelloStreamBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloStreamBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloStreamBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloStreamBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple2 RPC.
     * </pre>
     */
    public SimpleFeature simpleRpc(Simple request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SIMPLE_RPC, getCallOptions(), request);
    }

    /**
     * <pre>
     * A server-to-client streaming RPC.
     * </pre>
     */
    public java.util.Iterator<SimpleFeature> server2ClientRpc(
        SimpleList request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_SERVER2CLIENT_RPC, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HelloStreamFutureStub extends io.grpc.stub.AbstractStub<HelloStreamFutureStub> {
    private HelloStreamFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloStreamFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloStreamFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloStreamFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * A simple2 RPC.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<SimpleFeature> simpleRpc(
        Simple request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SIMPLE_RPC, getCallOptions()), request);
    }
  }

  private static final int METHODID_SIMPLE_RPC = 0;
  private static final int METHODID_SERVER2CLIENT_RPC = 1;
  private static final int METHODID_CLIENT2SERVER_RPC = 2;
  private static final int METHODID_BINDIRECTIONAL_STREAM_RPC = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloStreamImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloStreamImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SIMPLE_RPC:
          serviceImpl.simpleRpc((Simple) request,
              (io.grpc.stub.StreamObserver<SimpleFeature>) responseObserver);
          break;
        case METHODID_SERVER2CLIENT_RPC:
          serviceImpl.server2ClientRpc((SimpleList) request,
              (io.grpc.stub.StreamObserver<SimpleFeature>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLIENT2SERVER_RPC:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.client2ServerRpc(
              (io.grpc.stub.StreamObserver<SimpleSummary>) responseObserver);
        case METHODID_BINDIRECTIONAL_STREAM_RPC:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.bindirectionalStreamRpc(
              (io.grpc.stub.StreamObserver<Simple>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class HelloStreamDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.hry.spring.grpc.mystream.HelloStreamEntity.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloStreamGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloStreamDescriptorSupplier())
              .addMethod(METHOD_SIMPLE_RPC)
              .addMethod(METHOD_SERVER2CLIENT_RPC)
              .addMethod(METHOD_CLIENT2SERVER_RPC)
              .addMethod(METHOD_BINDIRECTIONAL_STREAM_RPC)
              .build();
        }
      }
    }
    return result;
  }
}
