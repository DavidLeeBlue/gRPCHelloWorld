package com.smarthotel.client;

import com.smarthotel.server.lightwitch.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LightSwitchClient {

    private final ManagedChannel channel;
    private final LightSwitchServiceGrpc.LightSwitchServiceBlockingStub stub;
    private final ConsulClient consulClient;
    private final String consulServiceName;
    private String serverHost;
    private int serverPort;

    public LightSwitchClient(String consulHost, int consulPort, String consulServiceName) {
        this.consulClient = new ConsulClient(consulHost, consulPort);
        this.consulServiceName = consulServiceName;

        getServerDetailsFromConsul();

        this.channel = ManagedChannelBuilder.forAddress(serverHost, serverPort)
                .usePlaintext()
                .build();
        this.stub = LightSwitchServiceGrpc.newBlockingStub(channel);
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

    public void turnOn() {
        TurnOnRequest request = TurnOnRequest.newBuilder().build();
        TurnOnResponse response = stub.turnOn(request);
        System.out.println("Server response: " + response.getStatus());
    }

    public void turnOff() {
        TurnOffRequest request = TurnOffRequest.newBuilder().build();
        TurnOffResponse response = stub.turnOff(request);
        System.out.println("Server response: " + response.getStatus());
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        String consulHost = "localhost";
        int consulPort = 8500;
        String consulServiceName = "LightSwitch-service";

        LightSwitchClient client = new LightSwitchClient(consulHost, consulPort, consulServiceName);
        client.turnOn();
        client.turnOff();
        client.shutdown();
    }
}