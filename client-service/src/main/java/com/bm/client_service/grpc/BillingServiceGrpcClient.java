package com.bm.client_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort
    ) {
        log.info("Connecting to billing service GRPC service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build(); // Устанавливает TCP соединение usePlaintext отключает TLS, данные передаются нешифрованно

        blockingStub =  BillingServiceGrpc.newBlockingStub(channel); // Инкапсулирует сериализацию и десериализацию и сетевое взаимодействие
    }

    public BillingResponse createBillingAccount(String clientId, String name, String email) {

        BillingRequest request = BillingRequest.newBuilder().setClientId(clientId).setName(name).setEmail(email).build();

        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Received response from billing service via GRPC: {}", response);

        return response;
    }
}
