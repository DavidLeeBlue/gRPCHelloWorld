package main.com.example.grpc.helloworld;

import com.example.grpc.helloworld.GreeterGrpc;
import com.example.grpc.helloworld.HelloRequest;
import com.example.grpc.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;

public class HelloWorldServiceImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // Implement your logic here
        String greeting = "Hello, " + request.getName() + "!";
        HelloResponse reply = HelloResponse.newBuilder().setMessage(greeting).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

