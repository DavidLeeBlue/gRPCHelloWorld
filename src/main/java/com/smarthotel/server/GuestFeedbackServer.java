package com.smarthotel.server;

import com.smarthotel.server.guestfeedback.*;
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

public class GuestFeedbackServer {
    private Server server;

    public void start() throws IOException {
        int port = 50062;
        server = ServerBuilder.forPort(port)
                .addService(new GuestFeedbackServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on port " + port);

        registerToConsul();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            try {
                GuestFeedbackServer.this.stop();
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
        try (FileInputStream fis = new FileInputStream("src/main/resources/GuestFeedbackService.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String consulHost = props.getProperty("consul.host");
        int consulPort = Integer.parseInt(props.getProperty("consul.port"));
        String serviceName = props.getProperty("consul.service.name");
        int servicePort = Integer.parseInt(props.getProperty("consul.service.port"));
        String healthCheckInterval = props.getProperty("consul.service.healthCheckInterval");

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

    private static class GuestFeedbackServiceImpl extends GuestFeedbackServiceGrpc.GuestFeedbackServiceImplBase {
        @Override
        public StreamObserver<FeedbackRequest> submitFeedback(StreamObserver<FeedbackResponse> responseObserver) {
            return new StreamObserver<FeedbackRequest>() {
                @Override
                public void onNext(FeedbackRequest request) {
                    System.out.println("Received feedback from guest: " + request.getGuestName());
                    System.out.println("Feedback: " + request.getFeedback());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in feedback submission: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Feedback submission completed");
                    FeedbackResponse response = FeedbackResponse.newBuilder()
                            .setMessage("Feedback submission completed")
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GuestFeedbackServer server = new GuestFeedbackServer();
        server.start();
        server.blockUntilShutdown();
    }
}