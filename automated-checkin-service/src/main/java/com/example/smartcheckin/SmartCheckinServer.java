package com.example.smartcheckin;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmartCheckinServer {
    private final int port;
    private final Server server;
    private final List<GuestRecord> guestRecords;

    public SmartCheckinServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new SmartCheckinServiceImpl())
                .build();
        this.guestRecords = new ArrayList<>();
        // Initialize guest records (dummy data)
        guestRecords.add(new GuestRecord("John Doe", "123456789"));
        guestRecords.add(new GuestRecord("Jane Smith", "987654321"));
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Smart Check-in Kiosk server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down Smart Check-in Kiosk server since JVM is shutting down");
            SmartCheckinServer.this.stop();
            System.err.println("*** Smart Check-in Kiosk server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SmartCheckinServer server = new SmartCheckinServer(50051);
        server.start();
        server.blockUntilShutdown();
    }

    static class SmartCheckinServiceImpl extends SmartCheckinServiceGrpc.SmartCheckinServiceImplBase {
        private final List<GuestRecord> guestRecords;

        public SmartCheckinServiceImpl() {
            this.guestRecords = new ArrayList<>();
            // Initialize guest records (dummy data)
            guestRecords.add(new GuestRecord("John Doe", "123456789"));
            guestRecords.add(new GuestRecord("Jane Smith", "987654321"));
        }

        @Override
        public void checkIn(CheckInRequest request, StreamObserver<CheckInResponse> responseObserver) {
            // Check if the provided ID and name match any guest records
            for (GuestRecord guestRecord : guestRecords) {
                if (guestRecord.getGuestID().equals(request.getGuestID()) &&
                        guestRecord.getGuestName().equals(request.getGuestName())) {
                    // If match found, simulate successful check-in
                    System.out.println("Received check-in request for guest: " + request.getGuestName());
                    CheckInResponse response = CheckInResponse.newBuilder()
                            .setSuccess(true)
                            .setMessage("Check-in successful")
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                    return;
                }
            }
            // If no match found, return failure response
            System.out.println("Failed check-in request for guest: " + request.getGuestName());
            CheckInResponse response = CheckInResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Check-in failed. Guest ID or name incorrect.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    static class GuestRecord {
        private final String guestName;
        private final String guestID;

        public GuestRecord(String guestName, String guestID) {
            this.guestName = guestName;
            this.guestID = guestID;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getGuestID() {
            return guestID;
        }
    }
}
