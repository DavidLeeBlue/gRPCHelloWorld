package com.smarthotel.server;

import com.smarthotel.server.lightwitch.*;
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

public class LightSwitchServer {
    private Server server;

    public void start() throws IOException {
        int port = 50052;
        server = ServerBuilder.forPort(port)
                .addService(new LightSwitchServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on port " + port);

        registerToConsul();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            try {
                LightSwitchServer.this.stop();
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
        try (FileInputStream fis = new FileInputStream("src/main/resources/lightswitch.properties")) {
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

    private static class LightSwitchServiceImpl extends LightSwitchServiceGrpc.LightSwitchServiceImplBase {
        @Override
        public void turnOn(TurnOnRequest request, StreamObserver<TurnOnResponse> responseObserver) {
            System.out.println("Received turn on request");
            TurnOnResponse response = TurnOnResponse.newBuilder()
                    .setStatus("Light turned on")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void turnOff(TurnOffRequest request, StreamObserver<TurnOffResponse> responseObserver) {
            System.out.println("Received turn off request");
            TurnOffResponse response = TurnOffResponse.newBuilder()
                    .setStatus("Light turned off")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LightSwitchServer server = new LightSwitchServer();
        server.start();
        server.blockUntilShutdown();
    }
}