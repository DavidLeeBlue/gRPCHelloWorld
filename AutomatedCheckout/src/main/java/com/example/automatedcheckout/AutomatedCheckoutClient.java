package com.example.automatedcheckout;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AutomatedCheckoutClient {
    private final ManagedChannel channel;
    private final AutomatedCheckoutServiceGrpc.AutomatedCheckoutServiceBlockingStub blockingStub;

    public AutomatedCheckoutClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // For simplicity, use plaintext communication
                .build();
        this.blockingStub = AutomatedCheckoutServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void checkOut(String roomNumber) {
        CheckOutRequest request = CheckOutRequest.newBuilder()
                .setRoomNumber(roomNumber)
                .build();

        CheckOutResponse response = blockingStub.checkOut(request);

        if (response.getSuccess()) {
            System.out.println("Check-out successful. Message from server: " + response.getMessage());
        } else {
            System.out.println("Check-out failed. Message from server: " + response.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AutomatedCheckoutClient client = new AutomatedCheckoutClient("localhost", 50052);
        try {
            // Perform check-out for room number "Room 101"
            client.checkOut("Room 88");

            // Perform check-out for room number "Room 102"
            client.checkOut("Room 102");
        } finally {
            client.shutdown();
        }
    }
}
