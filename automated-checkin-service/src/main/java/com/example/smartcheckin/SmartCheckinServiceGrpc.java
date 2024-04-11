package com.example.smartcheckin;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: SmartCheckin.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SmartCheckinServiceGrpc {

  private SmartCheckinServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SmartCheckinService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.smartcheckin.CheckInRequest,
      com.example.smartcheckin.CheckInResponse> getCheckInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkIn",
      requestType = com.example.smartcheckin.CheckInRequest.class,
      responseType = com.example.smartcheckin.CheckInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.smartcheckin.CheckInRequest,
      com.example.smartcheckin.CheckInResponse> getCheckInMethod() {
    io.grpc.MethodDescriptor<com.example.smartcheckin.CheckInRequest, com.example.smartcheckin.CheckInResponse> getCheckInMethod;
    if ((getCheckInMethod = SmartCheckinServiceGrpc.getCheckInMethod) == null) {
      synchronized (SmartCheckinServiceGrpc.class) {
        if ((getCheckInMethod = SmartCheckinServiceGrpc.getCheckInMethod) == null) {
          SmartCheckinServiceGrpc.getCheckInMethod = getCheckInMethod =
              io.grpc.MethodDescriptor.<com.example.smartcheckin.CheckInRequest, com.example.smartcheckin.CheckInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.smartcheckin.CheckInRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.smartcheckin.CheckInResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SmartCheckinServiceMethodDescriptorSupplier("checkIn"))
              .build();
        }
      }
    }
    return getCheckInMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SmartCheckinServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartCheckinServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SmartCheckinServiceStub>() {
        @java.lang.Override
        public SmartCheckinServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SmartCheckinServiceStub(channel, callOptions);
        }
      };
    return SmartCheckinServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartCheckinServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartCheckinServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SmartCheckinServiceBlockingStub>() {
        @java.lang.Override
        public SmartCheckinServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SmartCheckinServiceBlockingStub(channel, callOptions);
        }
      };
    return SmartCheckinServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SmartCheckinServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartCheckinServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SmartCheckinServiceFutureStub>() {
        @java.lang.Override
        public SmartCheckinServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SmartCheckinServiceFutureStub(channel, callOptions);
        }
      };
    return SmartCheckinServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkIn(com.example.smartcheckin.CheckInRequest request,
        io.grpc.stub.StreamObserver<com.example.smartcheckin.CheckInResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckInMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SmartCheckinService.
   */
  public static abstract class SmartCheckinServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SmartCheckinServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SmartCheckinService.
   */
  public static final class SmartCheckinServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SmartCheckinServiceStub> {
    private SmartCheckinServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartCheckinServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartCheckinServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkIn(com.example.smartcheckin.CheckInRequest request,
        io.grpc.stub.StreamObserver<com.example.smartcheckin.CheckInResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckInMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SmartCheckinService.
   */
  public static final class SmartCheckinServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SmartCheckinServiceBlockingStub> {
    private SmartCheckinServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartCheckinServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartCheckinServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.smartcheckin.CheckInResponse checkIn(com.example.smartcheckin.CheckInRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckInMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SmartCheckinService.
   */
  public static final class SmartCheckinServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SmartCheckinServiceFutureStub> {
    private SmartCheckinServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartCheckinServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartCheckinServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.smartcheckin.CheckInResponse> checkIn(
        com.example.smartcheckin.CheckInRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckInMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_IN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_IN:
          serviceImpl.checkIn((com.example.smartcheckin.CheckInRequest) request,
              (io.grpc.stub.StreamObserver<com.example.smartcheckin.CheckInResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckInMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.smartcheckin.CheckInRequest,
              com.example.smartcheckin.CheckInResponse>(
                service, METHODID_CHECK_IN)))
        .build();
  }

  private static abstract class SmartCheckinServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartCheckinServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.smartcheckin.SmartCheckinProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartCheckinService");
    }
  }

  private static final class SmartCheckinServiceFileDescriptorSupplier
      extends SmartCheckinServiceBaseDescriptorSupplier {
    SmartCheckinServiceFileDescriptorSupplier() {}
  }

  private static final class SmartCheckinServiceMethodDescriptorSupplier
      extends SmartCheckinServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SmartCheckinServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SmartCheckinServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SmartCheckinServiceFileDescriptorSupplier())
              .addMethod(getCheckInMethod())
              .build();
        }
      }
    }
    return result;
  }
}
