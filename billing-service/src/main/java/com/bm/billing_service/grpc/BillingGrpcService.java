package com.bm.billing_service.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * При старте благодаря аннотации начинает обслуживать вызовы. Реализует контракт, сгенерированный в .proto
 */
@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    /**
     * Принимает сообщение-запрос от сервиса client-service из OrderService и реализует логику, связанную с
     * финансовым оформлением заказа и созданием счёта для оплаты.
     * @param billingRequest запрос, список стоимостей всех блюд в заказе
     * @param responseObserver ответ, статус создания счёта заказа
     */
    @Override
    public void createBilling(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBilling request received {}", billingRequest.toString());

        // Business logic here
        int totalCost = countTotalCost(billingRequest.getCostsList());
        String responseStatus = someStrongLogicWithCreatingBillingAndSendingIt(totalCost);

        BillingResponse response = BillingResponse.newBuilder().setStatus(responseStatus).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     * Подсчитывает общую стоимость заказа.
     * @param costs список стоимостей блюд в заказе
     * @return общая стоимость заказа
     */
    public int countTotalCost(List<String> costs) {
        int totalCost = 0;
        for (String cost : costs) {
            totalCost += Integer.parseInt(cost);
        }
        return totalCost;
    }

    /**
     * Имитация бизнес-логики, связанной с созданием счёта. Заглушка для практики работы с взаимодействием между сервисами при помощи gRPC
     * @param totalCost общая стоимость заказа
     * @return статус создания счёта
     */
    public String someStrongLogicWithCreatingBillingAndSendingIt(int totalCost) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.format("Success with total cost: %d", totalCost);
    }
}
