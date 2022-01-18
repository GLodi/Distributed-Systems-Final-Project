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
    comments = "Source: CheckMasterAliveService.proto")
public final class CheckMasterAliveServiceGrpc {

  private CheckMasterAliveServiceGrpc() {}

  public static final String SERVICE_NAME = "com.progetto.grpc.CheckMasterAliveService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest,
      com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse> getBeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "beat",
      requestType = com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest.class,
      responseType = com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest,
      com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse> getBeatMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest, com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse> getBeatMethod;
    if ((getBeatMethod = CheckMasterAliveServiceGrpc.getBeatMethod) == null) {
      synchronized (CheckMasterAliveServiceGrpc.class) {
        if ((getBeatMethod = CheckMasterAliveServiceGrpc.getBeatMethod) == null) {
          CheckMasterAliveServiceGrpc.getBeatMethod = getBeatMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest, com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "beat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckMasterAliveServiceMethodDescriptorSupplier("beat"))
              .build();
        }
      }
    }
    return getBeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckMasterAliveServiceStub newStub(io.grpc.Channel channel) {
    return new CheckMasterAliveServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckMasterAliveServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CheckMasterAliveServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckMasterAliveServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CheckMasterAliveServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CheckMasterAliveServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void beat(com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBeatMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest,
                com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse>(
                  this, METHODID_BEAT)))
          .build();
    }
  }

  /**
   */
  public static final class CheckMasterAliveServiceStub extends io.grpc.stub.AbstractStub<CheckMasterAliveServiceStub> {
    private CheckMasterAliveServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CheckMasterAliveServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckMasterAliveServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CheckMasterAliveServiceStub(channel, callOptions);
    }

    /**
     */
    public void beat(com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getBeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CheckMasterAliveServiceBlockingStub extends io.grpc.stub.AbstractStub<CheckMasterAliveServiceBlockingStub> {
    private CheckMasterAliveServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CheckMasterAliveServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckMasterAliveServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CheckMasterAliveServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse> beat(
        com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getBeatMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CheckMasterAliveServiceFutureStub extends io.grpc.stub.AbstractStub<CheckMasterAliveServiceFutureStub> {
    private CheckMasterAliveServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CheckMasterAliveServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckMasterAliveServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CheckMasterAliveServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_BEAT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CheckMasterAliveServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CheckMasterAliveServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BEAT:
          serviceImpl.beat((com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse>) responseObserver);
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

  private static abstract class CheckMasterAliveServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckMasterAliveServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.progetto.grpc.CheckMasterAliveServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CheckMasterAliveService");
    }
  }

  private static final class CheckMasterAliveServiceFileDescriptorSupplier
      extends CheckMasterAliveServiceBaseDescriptorSupplier {
    CheckMasterAliveServiceFileDescriptorSupplier() {}
  }

  private static final class CheckMasterAliveServiceMethodDescriptorSupplier
      extends CheckMasterAliveServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CheckMasterAliveServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CheckMasterAliveServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckMasterAliveServiceFileDescriptorSupplier())
              .addMethod(getBeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
