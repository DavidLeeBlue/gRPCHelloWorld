package com.smarthotel.server;

import com.smarthotel.server.roomservice.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class RoomServiceServer {
    private Server server;
    // Build a grpc server
    public void start() throws IOException {
        int port = 50090;
        server = ServerBuilder.forPort(port)
                .addService(new RoomServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on port " + port);

        registerToConsul();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            try {
                RoomServiceServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private void registerToConsul() {
        System.out.println("Registering server to Consul...");

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/RoomService.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String consulHost = props.getProperty("roomservice.host");
        int consulPort = Integer.parseInt(props.getProperty("roomservice.port"));
        String serviceName = props.getProperty("roomservice.service.name");
        int servicePort = Integer.parseInt(props.getProperty("roomservice.service.port"));
        String healthCheckInterval = props.getProperty("roomservice.service.healthCheckInterval");

        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }

        ConsulClient consulClient = new ConsulClient(consulHost, consulPort);

        NewService newService = new NewService();
        newService.setName(serviceName);
        newService.setPort(servicePort);
        newService.setAddress(hostAddress);

        consulClient.agentServiceRegister(newService);

        System.out.println("Server registered to Consul successfully. Host: " + hostAddress);
    }

    private static class RoomServiceImpl extends RoomServiceGrpc.RoomServiceImplBase {
        @Override
        public StreamObserver<RoomServiceRequest> roomServiceStream(StreamObserver<RoomServiceResponse> responseObserver) {
            return new StreamObserver<RoomServiceRequest>() {
                @Override
                public void onNext(RoomServiceRequest request) {
                    System.out.println("Received room service request from guest: " + request.getGuestName());
                    System.out.println("Room Number: " + request.getRoomNumber());
                    System.out.println("Request: " + request.getRequest());

                    RoomServiceResponse response = RoomServiceResponse.newBuilder()
                            .setRoomNumber(request.getRoomNumber())
                            .setStatus("Received")
                            .build();
                    responseObserver.onNext(response);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in room service request: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Room service request completed");
                    responseObserver.onCompleted();
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        RoomServiceServer server = new RoomServiceServer(); // Create a new instance of the server
        server.start(); // Build a grpc server
        server.blockUntilShutdown();
    }
}