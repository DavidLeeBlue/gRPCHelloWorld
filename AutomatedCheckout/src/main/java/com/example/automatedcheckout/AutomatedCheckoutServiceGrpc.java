package com.example.automatedcheckout;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: AutomatedCheckout.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AutomatedCheckoutServiceGrpc {

  private AutomatedCheckoutServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "AutomatedCheckoutService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.automatedcheckout.CheckOutRequest,
      com.example.automatedcheckout.CheckOutResponse> getCheckOutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkOut",
      requestType = com.example.automatedcheckout.CheckOutRequest.class,
      responseType = com.example.automatedcheckout.CheckOutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.automatedcheckout.CheckOutRequest,
      com.example.automatedcheckout.CheckOutResponse> getCheckOutMethod() {
    io.grpc.MethodDescriptor<com.example.automatedcheckout.CheckOutRequest, com.example.automatedcheckout.CheckOutResponse> getCheckOutMethod;
    if ((getCheckOutMethod = AutomatedCheckoutServiceGrpc.getCheckOutMethod) == null) {
      synchronized (AutomatedCheckoutServiceGrpc.class) {
        if ((getCheckOutMethod = AutomatedCheckoutServiceGrpc.getCheckOutMethod) == null) {
          AutomatedCheckoutServiceGrpc.getCheckOutMethod = getCheckOutMethod =
              io.grpc.MethodDescriptor.<com.example.automatedcheckout.CheckOutRequest, com.example.automatedcheckout.CheckOutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkOut"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.automatedcheckout.CheckOutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.automatedcheckout.CheckOutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AutomatedCheckoutServiceMethodDescriptorSupplier("checkOut"))
              .build();
        }
      }
    }
    return getCheckOutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AutomatedCheckoutServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AutomatedCheckoutServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AutomatedCheckoutServiceStub>() {
        @java.lang.Override
        public AutomatedCheckoutServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AutomatedCheckoutServiceStub(channel, callOptions);
        }
      };
    return AutomatedCheckoutServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AutomatedCheckoutServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AutomatedCheckoutServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AutomatedCheckoutServiceBlockingStub>() {
        @java.lang.Override
        public AutomatedCheckoutServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AutomatedCheckoutServiceBlockingStub(channel, callOptions);
        }
      };
    return AutomatedCheckoutServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AutomatedCheckoutServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AutomatedCheckoutServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AutomatedCheckoutServiceFutureStub>() {
        @java.lang.Override
        public AutomatedCheckoutServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AutomatedCheckoutServiceFutureStub(channel, callOptions);
        }
      };
    return AutomatedCheckoutServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkOut(com.example.automatedcheckout.CheckOutRequest request,
        io.grpc.stub.StreamObserver<com.example.automatedcheckout.CheckOutResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckOutMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AutomatedCheckoutService.
   */
  public static abstract class AutomatedCheckoutServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AutomatedCheckoutServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AutomatedCheckoutService.
   */
  public static final class AutomatedCheckoutServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AutomatedCheckoutServiceStub> {
    private AutomatedCheckoutServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AutomatedCheckoutServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AutomatedCheckoutServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkOut(com.example.automatedcheckout.CheckOutRequest request,
        io.grpc.stub.StreamObserver<com.example.automatedcheckout.CheckOutResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AutomatedCheckoutService.
   */
  public static final class AutomatedCheckoutServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AutomatedCheckoutServiceBlockingStub> {
    private AutomatedCheckoutServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AutomatedCheckoutServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AutomatedCheckoutServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.automatedcheckout.CheckOutResponse checkOut(com.example.automatedcheckout.CheckOutRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckOutMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AutomatedCheckoutService.
   */
  public static final class AutomatedCheckoutServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AutomatedCheckoutServiceFutureStub> {
    private AutomatedCheckoutServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AutomatedCheckoutServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AutomatedCheckoutServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.automatedcheckout.CheckOutResponse> checkOut(
        com.example.automatedcheckout.CheckOutRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckOutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_OUT = 0;

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
        case METHODID_CHECK_OUT:
          serviceImpl.checkOut((com.example.automatedcheckout.CheckOutRequest) request,
              (io.grpc.stub.StreamObserver<com.example.automatedcheckout.CheckOutResponse>) responseObserver);
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
          getCheckOutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.automatedcheckout.CheckOutRequest,
              com.example.automatedcheckout.CheckOutResponse>(
                service, METHODID_CHECK_OUT)))
        .build();
  }

  private static abstract class AutomatedCheckoutServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AutomatedCheckoutServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.automatedcheckout.AutomatedCheckoutProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AutomatedCheckoutService");
    }
  }

  private static final class AutomatedCheckoutServiceFileDescriptorSupplier
      extends AutomatedCheckoutServiceBaseDescriptorSupplier {
    AutomatedCheckoutServiceFileDescriptorSupplier() {}
  }

  private static final class AutomatedCheckoutServiceMethodDescriptorSupplier
      extends AutomatedCheckoutServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AutomatedCheckoutServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AutomatedCheckoutServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AutomatedCheckoutServiceFileDescriptorSupplier())
              .addMethod(getCheckOutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
