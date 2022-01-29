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
    comments = "Source: RechargeService.proto")
public final class RechargeServiceGrpc {

  private RechargeServiceGrpc() {}

  public static final String SERVICE_NAME = "com.progetto.grpc.RechargeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest,
      com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse> getBroadcastRechargeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "broadcastRecharge",
      requestType = com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest.class,
      responseType = com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest,
      com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse> getBroadcastRechargeMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest, com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse> getBroadcastRechargeMethod;
    if ((getBroadcastRechargeMethod = RechargeServiceGrpc.getBroadcastRechargeMethod) == null) {
      synchronized (RechargeServiceGrpc.class) {
        if ((getBroadcastRechargeMethod = RechargeServiceGrpc.getBroadcastRechargeMethod) == null) {
          RechargeServiceGrpc.getBroadcastRechargeMethod = getBroadcastRechargeMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest, com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "broadcastRecharge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RechargeServiceMethodDescriptorSupplier("broadcastRecharge"))
              .build();
        }
      }
    }
    return getBroadcastRechargeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest,
      com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse> getInformMasterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "informMaster",
      requestType = com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest.class,
      responseType = com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest,
      com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse> getInformMasterMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest, com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse> getInformMasterMethod;
    if ((getInformMasterMethod = RechargeServiceGrpc.getInformMasterMethod) == null) {
      synchronized (RechargeServiceGrpc.class) {
        if ((getInformMasterMethod = RechargeServiceGrpc.getInformMasterMethod) == null) {
          RechargeServiceGrpc.getInformMasterMethod = getInformMasterMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest, com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "informMaster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RechargeServiceMethodDescriptorSupplier("informMaster"))
              .build();
        }
      }
    }
    return getInformMasterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest,
      com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse> getSendOkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendOk",
      requestType = com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest.class,
      responseType = com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest,
      com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse> getSendOkMethod() {
    io.grpc.MethodDescriptor<com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest, com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse> getSendOkMethod;
    if ((getSendOkMethod = RechargeServiceGrpc.getSendOkMethod) == null) {
      synchronized (RechargeServiceGrpc.class) {
        if ((getSendOkMethod = RechargeServiceGrpc.getSendOkMethod) == null) {
          RechargeServiceGrpc.getSendOkMethod = getSendOkMethod =
              io.grpc.MethodDescriptor.<com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest, com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendOk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RechargeServiceMethodDescriptorSupplier("sendOk"))
              .build();
        }
      }
    }
    return getSendOkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RechargeServiceStub newStub(io.grpc.Channel channel) {
    return new RechargeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RechargeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RechargeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RechargeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RechargeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RechargeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void broadcastRecharge(com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBroadcastRechargeMethod(), responseObserver);
    }

    /**
     */
    public void informMaster(com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInformMasterMethod(), responseObserver);
    }

    /**
     */
    public void sendOk(com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendOkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBroadcastRechargeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest,
                com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse>(
                  this, METHODID_BROADCAST_RECHARGE)))
          .addMethod(
            getInformMasterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest,
                com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse>(
                  this, METHODID_INFORM_MASTER)))
          .addMethod(
            getSendOkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest,
                com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse>(
                  this, METHODID_SEND_OK)))
          .build();
    }
  }

  /**
   */
  public static final class RechargeServiceStub extends io.grpc.stub.AbstractStub<RechargeServiceStub> {
    private RechargeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RechargeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RechargeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RechargeServiceStub(channel, callOptions);
    }

    /**
     */
    public void broadcastRecharge(com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBroadcastRechargeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void informMaster(com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInformMasterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendOk(com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest request,
        io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendOkMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RechargeServiceBlockingStub extends io.grpc.stub.AbstractStub<RechargeServiceBlockingStub> {
    private RechargeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RechargeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RechargeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RechargeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse broadcastRecharge(com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest request) {
      return blockingUnaryCall(
          getChannel(), getBroadcastRechargeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse informMaster(com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest request) {
      return blockingUnaryCall(
          getChannel(), getInformMasterMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse sendOk(com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendOkMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RechargeServiceFutureStub extends io.grpc.stub.AbstractStub<RechargeServiceFutureStub> {
    private RechargeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RechargeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RechargeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RechargeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse> broadcastRecharge(
        com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBroadcastRechargeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse> informMaster(
        com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInformMasterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse> sendOk(
        com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendOkMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BROADCAST_RECHARGE = 0;
  private static final int METHODID_INFORM_MASTER = 1;
  private static final int METHODID_SEND_OK = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RechargeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RechargeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BROADCAST_RECHARGE:
          serviceImpl.broadcastRecharge((com.progetto.grpc.RechargeServiceOuterClass.RechargeRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.RechargeResponse>) responseObserver);
          break;
        case METHODID_INFORM_MASTER:
          serviceImpl.informMaster((com.progetto.grpc.RechargeServiceOuterClass.InformMasterRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.InformMasterResponse>) responseObserver);
          break;
        case METHODID_SEND_OK:
          serviceImpl.sendOk((com.progetto.grpc.RechargeServiceOuterClass.SendOkRequest) request,
              (io.grpc.stub.StreamObserver<com.progetto.grpc.RechargeServiceOuterClass.SendOkResponse>) responseObserver);
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

  private static abstract class RechargeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RechargeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.progetto.grpc.RechargeServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RechargeService");
    }
  }

  private static final class RechargeServiceFileDescriptorSupplier
      extends RechargeServiceBaseDescriptorSupplier {
    RechargeServiceFileDescriptorSupplier() {}
  }

  private static final class RechargeServiceMethodDescriptorSupplier
      extends RechargeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RechargeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RechargeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RechargeServiceFileDescriptorSupplier())
              .addMethod(getBroadcastRechargeMethod())
              .addMethod(getInformMasterMethod())
              .addMethod(getSendOkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
