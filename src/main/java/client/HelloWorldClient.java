package client;

import com.example.grpc.helloworld.GreeterGrpc;
import com.example.grpc.helloworld.HelloRequest;
import com.example.grpc.helloworld.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {
    public static void main(String[] args) {
        // Create a gRPC channel to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext() // For testing purposes only, do not use in production
                .build();

        try {
            // Create a gRPC client stub
            GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);

            // Create a request
            HelloRequest request = HelloRequest.newBuilder().setName("Peter").build();

            // Call the RPC and get the response
            HelloResponse response = blockingStub.sayHello(request);

            // Print the response
            System.out.println("Response from server: " + response.getMessage());
        } finally {
            // Shutdown the channel
            channel.shutdown();
        }
    }
}