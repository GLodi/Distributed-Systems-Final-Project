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
    comments = "Source: CheckAliveService.proto")
public final class CheckAliveServiceGrpc {

  private CheckAliveServiceGrpc() {}

  public static final String SERVICE_NAME = "com.progetto.grpc.CheckAliveService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest,
      com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse> getBeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "beat",
      requestType = com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest.class,
      responseType = com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest,
      com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse> getBeatMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest, com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse> getBeatMethod;
    if ((getBeatMethod = CheckAliveServiceGrpc.getBeatMethod) == null) {
      synchronized (CheckAliveServiceGrpc.class) {
        if ((getBeatMethod = CheckAliveServiceGrpc.getBeatMethod) == null) {
          CheckAliveServiceGrpc.getBeatMethod = getBeatMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest, com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "beat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckAliveServiceMethodDescriptorSupplier("beat"))
              .build();
        }
      }
    }
    return getBeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckAliveServiceStub newStub(io.grpc.Channel channel) {
    return new CheckAliveServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckAliveServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CheckAliveServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckAliveServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CheckAliveServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CheckAliveServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void beat(com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBeatMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest,
                com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse>(
                  this, METHODID_BEAT)))
          .build();
    }
  }

  /**
   */
  public static final class CheckAliveServiceStub extends io.grpc.stub.AbstractStub<CheckAliveServiceStub> {
    private CheckAliveServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CheckAliveServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckAliveServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CheckAliveServiceStub(channel, callOptions);
    }

    /**
     */
    public void beat(com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getBeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CheckAliveServiceBlockingStub extends io.grpc.stub.AbstractStub<CheckAliveServiceBlockingStub> {
    private CheckAliveServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CheckAliveServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckAliveServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CheckAliveServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse> beat(
        com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getBeatMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CheckAliveServiceFutureStub extends io.grpc.stub.AbstractStub<CheckAliveServiceFutureStub> {
    private CheckAliveServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CheckAliveServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckAliveServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CheckAliveServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_BEAT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CheckAliveServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CheckAliveServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BEAT:
          serviceImpl.beat((com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse>) responseObserver);
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

  private static abstract class CheckAliveServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckAliveServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.progetto.grpc.CheckAliveServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CheckAliveService");
    }
  }

  private static final class CheckAliveServiceFileDescriptorSupplier
      extends CheckAliveServiceBaseDescriptorSupplier {
    CheckAliveServiceFileDescriptorSupplier() {}
  }

  private static final class CheckAliveServiceMethodDescriptorSupplier
      extends CheckAliveServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CheckAliveServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CheckAliveServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckAliveServiceFileDescriptorSupplier())
              .addMethod(getBeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
