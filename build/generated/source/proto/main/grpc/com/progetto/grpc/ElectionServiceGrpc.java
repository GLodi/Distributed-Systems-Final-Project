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
    comments = "Source: ElectionService.proto")
public final class ElectionServiceGrpc {

  private ElectionServiceGrpc() {}

  public static final String SERVICE_NAME = "com.progetto.grpc.ElectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest,
      com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse> getForwardElectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "forwardElection",
      requestType = com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest.class,
      responseType = com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest,
      com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse> getForwardElectionMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest, com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse> getForwardElectionMethod;
    if ((getForwardElectionMethod = ElectionServiceGrpc.getForwardElectionMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getForwardElectionMethod = ElectionServiceGrpc.getForwardElectionMethod) == null) {
          ElectionServiceGrpc.getForwardElectionMethod = getForwardElectionMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest, com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "forwardElection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("forwardElection"))
              .build();
        }
      }
    }
    return getForwardElectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest,
      com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse> getForwardElectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "forwardElected",
      requestType = com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest.class,
      responseType = com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest,
      com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse> getForwardElectedMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest, com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse> getForwardElectedMethod;
    if ((getForwardElectedMethod = ElectionServiceGrpc.getForwardElectedMethod) == null) {
      synchronized (ElectionServiceGrpc.class) {
        if ((getForwardElectedMethod = ElectionServiceGrpc.getForwardElectedMethod) == null) {
          ElectionServiceGrpc.getForwardElectedMethod = getForwardElectedMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest, com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "forwardElected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ElectionServiceMethodDescriptorSupplier("forwardElected"))
              .build();
        }
      }
    }
    return getForwardElectedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ElectionServiceStub newStub(io.grpc.Channel channel) {
    return new ElectionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ElectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ElectionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ElectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ElectionServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ElectionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void forwardElection(com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getForwardElectionMethod(), responseObserver);
    }

    /**
     */
    public void forwardElected(com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getForwardElectedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getForwardElectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest,
                com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse>(
                  this, METHODID_FORWARD_ELECTION)))
          .addMethod(
            getForwardElectedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest,
                com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse>(
                  this, METHODID_FORWARD_ELECTED)))
          .build();
    }
  }

  /**
   */
  public static final class ElectionServiceStub extends io.grpc.stub.AbstractStub<ElectionServiceStub> {
    private ElectionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElectionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElectionServiceStub(channel, callOptions);
    }

    /**
     */
    public void forwardElection(com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getForwardElectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void forwardElected(com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getForwardElectedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ElectionServiceBlockingStub extends io.grpc.stub.AbstractStub<ElectionServiceBlockingStub> {
    private ElectionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElectionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElectionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse forwardElection(com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest request) {
      return blockingUnaryCall(
          getChannel(), getForwardElectionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse forwardElected(com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest request) {
      return blockingUnaryCall(
          getChannel(), getForwardElectedMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ElectionServiceFutureStub extends io.grpc.stub.AbstractStub<ElectionServiceFutureStub> {
    private ElectionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ElectionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ElectionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ElectionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse> forwardElection(
        com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getForwardElectionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse> forwardElected(
        com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getForwardElectedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FORWARD_ELECTION = 0;
  private static final int METHODID_FORWARD_ELECTED = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ElectionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ElectionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FORWARD_ELECTION:
          serviceImpl.forwardElection((com.progetto.grpc.ElectionServiceOuterClass.ElectionRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.ElectionServiceOuterClass.ElectionResponse>) responseObserver);
          break;
        case METHODID_FORWARD_ELECTED:
          serviceImpl.forwardElected((com.progetto.grpc.ElectionServiceOuterClass.ElectedRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.ElectionServiceOuterClass.ElectedResponse>) responseObserver);
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

  private static abstract class ElectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ElectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.progetto.grpc.ElectionServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ElectionService");
    }
  }

  private static final class ElectionServiceFileDescriptorSupplier
      extends ElectionServiceBaseDescriptorSupplier {
    ElectionServiceFileDescriptorSupplier() {}
  }

  private static final class ElectionServiceMethodDescriptorSupplier
      extends ElectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ElectionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ElectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ElectionServiceFileDescriptorSupplier())
              .addMethod(getForwardElectionMethod())
              .addMethod(getForwardElectedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
