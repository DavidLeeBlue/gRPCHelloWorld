package com.example.automatedcheckout;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutomatedCheckoutServer {
    private final int port;
    private final Server server;
    private final List<String> availableRooms;

    public AutomatedCheckoutServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new AutomatedCheckoutServiceImpl())
                .build();
        this.availableRooms = new ArrayList<>();
        // Initialize available rooms (dummy data)
        for (int i = 1; i <= 100; i++) {
            availableRooms.add("Room " + i);
        }
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Automated Check-out Service server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down Automated Check-out Service server since JVM is shutting down");
            AutomatedCheckoutServer.this.stop();
            System.err.println("*** Automated Check-out Service server shut down");
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
        AutomatedCheckoutServer server = new AutomatedCheckoutServer(50052);
        server.start();
        server.blockUntilShutdown();
    }

    static class AutomatedCheckoutServiceImpl extends AutomatedCheckoutServiceGrpc.AutomatedCheckoutServiceImplBase {
        private final List<String> availableRooms;

        public AutomatedCheckoutServiceImpl() {
            this.availableRooms = new ArrayList<>();
            // Initialize available rooms (dummy data)
            for (int i = 1; i <= 100; i++) {
                availableRooms.add("Room " + i);
            }
        }

        @Override
        public void checkOut(CheckOutRequest request, StreamObserver<CheckOutResponse> responseObserver) {
            String roomNumber = request.getRoomNumber();
            // Simulate check-out process
            if (availableRooms.contains(roomNumber)) {
                availableRooms.remove(roomNumber);
                System.out.println("Room " + roomNumber + " checked out successfully");
                CheckOutResponse response = CheckOutResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Room " + roomNumber + " checked out successfully")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                System.out.println("Room " + roomNumber + " not found or already checked out");
                CheckOutResponse response = CheckOutResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Room " + roomNumber + " not found or already checked out")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        }
    }
}
