package com.bm.analytics_service.kafka;


import client.events.BookingEvent;
import client.events.ClientEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сервис, содержит логику работы consumer kafka.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * Принимает сообщения топика booking и обрабатывает
     * @param event событие
     */
    @KafkaListener(topics = "booking", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            BookingEvent bookingEvent = BookingEvent.parseFrom(event);

            // any analytics logic
            String clientForAnalytics = bookingEvent.getClientId();
            String tableForAnalytics = bookingEvent.getTableId();
            String bookDateForAnalytics = bookingEvent.getBookDate();
            String registerDateForAnalytics = bookingEvent.getRegisterDate();

            log.info("Receive client event: [bookingId = {}, clientForAnalytics = {}, tableForAnalytics = {}, " +
                            "bookDateForAnalytics = {}, registerDateForAnalytics = {}]",
                            bookingEvent.getId() ,clientForAnalytics, tableForAnalytics,
                            bookDateForAnalytics, registerDateForAnalytics);

            generateClientsPrefers(clientForAnalytics, registerDateForAnalytics);
            updateTableStatistics(tableForAnalytics);
            updateBookTimeStatistics(bookDateForAnalytics);

        } catch (InvalidProtocolBufferException e) {
            log.error("Error while deserializing event {}", e.getMessage());
        }
    }

    /**
     * Определяет предпочтения клиента в заказах в соответствии с предыдущими заказами
     * @param clientForAnalytics идентификатор клиента для анализа
     */
    public void generateClientsPrefers(String clientForAnalytics, String registerDateForAnalytics) {
        log.info("Generating client prefers for analytics service");
    }

    /**
     * Обновляет статистику бронирования тех или иных столов
     * @param tableForAnalytics идентификатор стола для анализа
     */
    public void updateTableStatistics(String tableForAnalytics) {
        log.info("Updating table statistics for analytics service");
    }

    /**
     * Обновляет статистику времени бронирования столиков
     * @param bookDateForAnalytics время очередной брони
     */
    public void updateBookTimeStatistics(String bookDateForAnalytics) {
        log.info("Updating book time statistics for analytics service");
    }
}
