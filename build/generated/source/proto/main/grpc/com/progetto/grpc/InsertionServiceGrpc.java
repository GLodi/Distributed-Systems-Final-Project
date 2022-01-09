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
    comments = "Source: InsertionService.proto")
public final class InsertionServiceGrpc {

  private InsertionServiceGrpc() {}

  public static final String SERVICE_NAME = "com.progetto.grpc.InsertionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest,
      com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse> getInsertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "insert",
      requestType = com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest.class,
      responseType = com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest,
      com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse> getInsertMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest, com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse> getInsertMethod;
    if ((getInsertMethod = InsertionServiceGrpc.getInsertMethod) == null) {
      synchronized (InsertionServiceGrpc.class) {
        if ((getInsertMethod = InsertionServiceGrpc.getInsertMethod) == null) {
          InsertionServiceGrpc.getInsertMethod = getInsertMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest, com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "insert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new InsertionServiceMethodDescriptorSupplier("insert"))
              .build();
        }
      }
    }
    return getInsertMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InsertionServiceStub newStub(io.grpc.Channel channel) {
    return new InsertionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InsertionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InsertionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InsertionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InsertionServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class InsertionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void insert(com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInsertMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInsertMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest,
                com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse>(
                  this, METHODID_INSERT)))
          .build();
    }
  }

  /**
   */
  public static final class InsertionServiceStub extends io.grpc.stub.AbstractStub<InsertionServiceStub> {
    private InsertionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InsertionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InsertionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InsertionServiceStub(channel, callOptions);
    }

    /**
     */
    public void insert(com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInsertMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class InsertionServiceBlockingStub extends io.grpc.stub.AbstractStub<InsertionServiceBlockingStub> {
    private InsertionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InsertionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InsertionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InsertionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse insert(com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest request) {
      return blockingUnaryCall(
          getChannel(), getInsertMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class InsertionServiceFutureStub extends io.grpc.stub.AbstractStub<InsertionServiceFutureStub> {
    private InsertionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InsertionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InsertionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InsertionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse> insert(
        com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInsertMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InsertionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InsertionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INSERT:
          serviceImpl.insert((com.progetto.grpc.InsertionServiceOuterClass.InsertionRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.InsertionServiceOuterClass.InsertionResponse>) responseObserver);
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

  private static abstract class InsertionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InsertionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.progetto.grpc.InsertionServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InsertionService");
    }
  }

  private static final class InsertionServiceFileDescriptorSupplier
      extends InsertionServiceBaseDescriptorSupplier {
    InsertionServiceFileDescriptorSupplier() {}
  }

  private static final class InsertionServiceMethodDescriptorSupplier
      extends InsertionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InsertionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (InsertionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InsertionServiceFileDescriptorSupplier())
              .addMethod(getInsertMethod())
              .build();
        }
      }
    }
    return result;
  }
}
