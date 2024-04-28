package com.smarthotel.client;

import com.smarthotel.server.guestfeedback.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GuestFeedbackClient {

    private final ManagedChannel channel;
    private final GuestFeedbackServiceGrpc.GuestFeedbackServiceStub stub;
    private final ConsulClient consulClient;
    private final String consulServiceName;
    private String serverHost;
    private int serverPort;

    public GuestFeedbackClient(String consulHost, int consulPort, String consulServiceName) {
        this.consulClient = new ConsulClient(consulHost, consulPort);
        this.consulServiceName = consulServiceName;

        getServerDetailsFromConsul();

        this.channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        this.stub = GuestFeedbackServiceGrpc.newStub(channel);
    }

    private void getServerDetailsFromConsul() {
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            System.err.println("No healthy instances of " + consulServiceName + " found in Consul.");
            return;
        }

        HealthService healthService = healthServices.get(0);
        this.serverHost = healthService.getService().getAddress();
        this.serverPort = healthService.getService().getPort();
    }

    public void submitFeedback(String guestName, String feedback) {
        StreamObserver<FeedbackRequest> requestObserver = stub.submitFeedback(new StreamObserver<FeedbackResponse>() {
            @Override
            public void onNext(FeedbackResponse response) {
                System.out.println("Server response: " + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in feedback submission: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Feedback submission completed");
            }
        });

        FeedbackRequest request = FeedbackRequest.newBuilder()
                .setGuestName(guestName)
                .setFeedback(feedback)
                .build();
        requestObserver.onNext(request);
        requestObserver.onCompleted();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        String consulHost = "localhost";
        int consulPort = 8500;
        String consulServiceName = "GuestFeedbackService-service";
        String guestName = "John Doe";
        String feedback = "Great service!";

        GuestFeedbackClient client = new GuestFeedbackClient(consulHost, consulPort, consulServiceName);
        client.submitFeedback(guestName, feedback);
        client.shutdown();
    }
}