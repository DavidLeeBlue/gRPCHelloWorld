package com.example.smartcheckin;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SmartCheckinClient {
    private final ManagedChannel channel;
    private final SmartCheckinServiceGrpc.SmartCheckinServiceBlockingStub blockingStub;

    public SmartCheckinClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // For simplicity, use plaintext communication
                .build();
        this.blockingStub = SmartCheckinServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void checkIn(String guestName, String guestID, String paymentDetails) {
        CheckInRequest request = CheckInRequest.newBuilder()
                .setGuestName(guestName)
                .setGuestID(guestID)
                .setPaymentDetails(paymentDetails)
                .build();

        CheckInResponse response = blockingStub.checkIn(request);

        if (response.getSuccess()) {
            System.out.println("Check-in successful. Message from server: " + response.getMessage());
        } else {
            System.out.println("Check-in failed. Message from server: " + response.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SmartCheckinClient client = new SmartCheckinClient("localhost", 50051);
        try {
            // Perform check-in with correct guest ID and name
            client.checkIn("John Doe", "123456789", "Credit Card: XXXX-XXXX-XXXX-XXXX");

            // Perform check-in with incorrect guest ID and name
            client.checkIn("Jane Smith", "123456789", "Credit Card: XXXX-XXXX-XXXX-XXXX");
        } finally {
            client.shutdown();
        }
    }
}
