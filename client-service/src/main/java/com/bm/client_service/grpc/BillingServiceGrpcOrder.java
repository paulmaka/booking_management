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

import java.util.List;

/**
 * Класс-сервис, содержит логику взаимодействия с billing-service.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class BillingServiceGrpcOrder {
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcOrder.class);

    public BillingServiceGrpcOrder(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort
    ) {
        log.info("Connecting to billing service GRPC service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build(); // Устанавливает TCP соединение usePlaintext отключает TLS, данные передаются нешифрованно

        blockingStub =  BillingServiceGrpc.newBlockingStub(channel); // Инкапсулирует сериализацию и десериализацию и сетевое взаимодействие (blockingStub синхронный)
    }

    /**
     * Создаёт счёт в billing-service и возвращает статус создания счёта.
     * @param costs список стоимостей всех блюд в заказе
     * @return статус создания счёта
     */
    public BillingResponse createBilling(List<String> costs) {

        BillingRequest request = BillingRequest.newBuilder().addAllCosts(costs).build();

        BillingResponse response = blockingStub.createBilling(request);
        log.info("Received response from billing service via GRPC: {}", response);

        return response;
    }
}
