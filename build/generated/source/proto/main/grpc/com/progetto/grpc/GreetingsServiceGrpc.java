package com.progetto.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: GreetingsService.proto")
public final class GreetingsServiceGrpc {

  private GreetingsServiceGrpc() {}

  public static final String SERVICE_NAME = "com.progetto.grpc.GreetingsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest,
      com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse> getGreetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "greet",
      requestType = com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest.class,
      responseType = com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest,
      com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse> getGreetMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest, com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse> getGreetMethod;
    if ((getGreetMethod = GreetingsServiceGrpc.getGreetMethod) == null) {
      synchronized (GreetingsServiceGrpc.class) {
        if ((getGreetMethod = GreetingsServiceGrpc.getGreetMethod) == null) {
          GreetingsServiceGrpc.getGreetMethod = getGreetMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest, com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "greet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingsServiceMethodDescriptorSupplier("greet"))
              .build();
        }
      }
    }
    return getGreetMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreetingsServiceStub newStub(io.grpc.Channel channel) {
    return new GreetingsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreetingsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreetingsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreetingsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GreetingsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void greet(com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGreetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGreetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest,
                com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse>(
                  this, METHODID_GREET)))
          .build();
    }
  }

  /**
   */
  public static final class GreetingsServiceStub extends io.grpc.stub.AbstractStub<GreetingsServiceStub> {
    private GreetingsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingsServiceStub(channel, callOptions);
    }

    /**
     */
    public void greet(com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGreetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreetingsServiceBlockingStub extends io.grpc.stub.AbstractStub<GreetingsServiceBlockingStub> {
    private GreetingsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse greet(com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getGreetMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreetingsServiceFutureStub extends io.grpc.stub.AbstractStub<GreetingsServiceFutureStub> {
    private GreetingsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse> greet(
        com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGreetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GREET = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreetingsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreetingsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GREET:
          serviceImpl.greet((com.progetto.grpc.GreetingsServiceOuterClass.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.GreetingsServiceOuterClass.HelloResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreetingsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreetingsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.progetto.grpc.GreetingsServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GreetingsService");
    }
  }

  private static final class GreetingsServiceFileDescriptorSupplier
      extends GreetingsServiceBaseDescriptorSupplier {
    GreetingsServiceFileDescriptorSupplier() {}
  }

  private static final class GreetingsServiceMethodDescriptorSupplier
      extends GreetingsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreetingsServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreetingsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreetingsServiceFileDescriptorSupplier())
              .addMethod(getGreetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
