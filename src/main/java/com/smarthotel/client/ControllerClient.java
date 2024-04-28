package com.smarthotel.client;


import com.smarthotel.server.guestfeedback.*;
import com.smarthotel.server.roomservice.*;
import com.smarthotel.server.lightwitch.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class ControllerClient {

    private final ManagedChannel guestFeedbackChannel;
    private final ManagedChannel roomServiceChannel;
    private final ManagedChannel lightSwitchChannel;
    private final GuestFeedbackServiceGrpc.GuestFeedbackServiceStub guestFeedbackStub;
    private final RoomServiceGrpc.RoomServiceStub roomServiceStub;
    private final LightSwitchServiceGrpc.LightSwitchServiceBlockingStub lightSwitchStub;
    private final ConsulClient consulClient;
    private String serverHost;
    private int serverPort;

    public ControllerClient(String consulHost, int consulPort, String guestFeedbackServiceName, String roomServiceName, String lightSwitchServiceName) {
        this.consulClient = new ConsulClient(consulHost, consulPort);

        getServerDetailsFromConsul(guestFeedbackServiceName);
        this.guestFeedbackChannel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        this.guestFeedbackStub = GuestFeedbackServiceGrpc.newStub(guestFeedbackChannel);

        getServerDetailsFromConsul(roomServiceName);
        this.roomServiceChannel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        this.roomServiceStub = RoomServiceGrpc.newStub(roomServiceChannel);

        getServerDetailsFromConsul(lightSwitchServiceName);
        this.lightSwitchChannel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        this.lightSwitchStub = LightSwitchServiceGrpc.newBlockingStub(lightSwitchChannel);
    }

    private void getServerDetailsFromConsul(String serviceName) {
        List<HealthService> healthServices = consulClient.getHealthServices(serviceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            System.err.println("No healthy instances of " + serviceName + " found in Consul.");
            return;
        }

        HealthService healthService = healthServices.get(0);
        this.serverHost = healthService.getService().getAddress();
        this.serverPort = healthService.getService().getPort();
    }

    public void submitFeedback(String guestName, String feedback) {
        FeedbackRequest request = FeedbackRequest.newBuilder()
                .setGuestName(guestName)
                .setFeedback(feedback)
                .build();

        StreamObserver<FeedbackResponse> responseObserver = new StreamObserver<FeedbackResponse>() {
            @Override
            public void onNext(FeedbackResponse response) {
                System.out.println("Received response from server: " + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error occurred: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Feedback submission completed");
            }
        };

        StreamObserver<FeedbackRequest> requestObserver = guestFeedbackStub.submitFeedback(responseObserver);
        requestObserver.onNext(request);
        requestObserver.onCompleted();
    }

    public void roomServiceStream(String guestName, String roomNumber, String request, Consumer<String> onServerResponse) {
        RoomServiceRequest serviceRequest = RoomServiceRequest.newBuilder()
                .setGuestName(guestName)
                .setRoomNumber(roomNumber)
                .setRequest(request)
                .build();

        StreamObserver<RoomServiceResponse> responseObserver = new StreamObserver<RoomServiceResponse>() {
            @Override
            public void onNext(RoomServiceResponse response) {
                onServerResponse.accept("Received response from server: " + response.getStatus());
            }

            @Override
            public void onError(Throwable t) {
                onServerResponse.accept("Error occurred: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                onServerResponse.accept("Room service request completed");
            }
        };

        StreamObserver<RoomServiceRequest> requestObserver = roomServiceStub.roomServiceStream(responseObserver);
        requestObserver.onNext(serviceRequest);
        requestObserver.onCompleted();
    }

    public void turnOn() {
        TurnOnRequest request = TurnOnRequest.newBuilder().build();
        TurnOnResponse response = lightSwitchStub.turnOn(request);
        System.out.println("Server response: " + response.getStatus());
    }

    public void turnOff() {
        TurnOffRequest request = TurnOffRequest.newBuilder().build();
        TurnOffResponse response = lightSwitchStub.turnOff(request);
        System.out.println("Server response: " + response.getStatus());
    }

    public void shutdown() throws InterruptedException {
        guestFeedbackChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        roomServiceChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        lightSwitchChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        String consulHost = "localhost";
        int consulPort = 8500;
        String guestFeedbackServiceName = "GuestFeedbackService-service";
        String roomServiceName = "RoomService-service";
        String lightSwitchServiceName = "LightSwitch-service";

        ControllerClient client = new ControllerClient(consulHost, consulPort, guestFeedbackServiceName, roomServiceName, lightSwitchServiceName);

        client.submitFeedback("Guest1", "Great service!");
//        client.roomServiceStream("Guest1", "Room101", "Need more towels");
        client.turnOn();
        client.turnOff();
        client.shutdown();
    }
}