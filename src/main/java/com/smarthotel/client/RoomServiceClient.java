package com.smarthotel.client;

import com.smarthotel.server.roomservice.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RoomServiceClient {

    private final ManagedChannel channel;
    private final RoomServiceGrpc.RoomServiceStub stub;
    private final ConsulClient consulClient;
    private final String consulServiceName;
    private String serverHost;
    private int serverPort;

    public RoomServiceClient(String consulHost, int consulPort, String consulServiceName) {
        this.consulClient = new ConsulClient(consulHost, consulPort);
        this.consulServiceName = consulServiceName;

        getServerDetailsFromConsul();

        this.channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        this.stub = RoomServiceGrpc.newStub(channel);
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

    public void roomServiceStream(String guestName, String roomNumber, String request) {
        StreamObserver<RoomServiceRequest> requestObserver = stub.roomServiceStream(new StreamObserver<RoomServiceResponse>() {
            @Override
            public void onNext(RoomServiceResponse response) {
                System.out.println("Server response: " + response.getStatus());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in room service request: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Room service request completed");
            }
        });

        RoomServiceRequest roomServiceRequest = RoomServiceRequest.newBuilder()
                .setGuestName(guestName)
                .setRoomNumber(roomNumber)
                .setRequest(request)
                .build();
        requestObserver.onNext(roomServiceRequest);
        requestObserver.onCompleted();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        String consulHost = "localhost";
        int consulPort = 8500;
        String consulServiceName = "RoomService-service";
        String guestName = "John Doe";
        String roomNumber = "101";
        String request = "Need more towels";

        RoomServiceClient client = new RoomServiceClient(consulHost, consulPort, consulServiceName);
        client.roomServiceStream(guestName, roomNumber, request);
        client.shutdown();
    }
}