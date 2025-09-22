package com.bm.client_service.kafka;

import client.events.BookingEvent;
import com.bm.client_service.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * Класс-сервис, содержит логику работы producer kafka.
 * @author Paul Makarenko
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Booking booking) {
        BookingEvent bookingEvent = BookingEvent.newBuilder()
                .setId(String.valueOf(booking.getId()))
                .setClientId(String.valueOf(booking.getClient().getId()))
                .setTableId(String.valueOf(booking.getTable().getId()))
                .setBookDate(String.valueOf(booking.getBookDate()))
                .setRegisterDate(String.valueOf(booking.getRegisterDate()))
                .build();

        try {
            kafkaTemplate.send("booking", bookingEvent.toByteArray());
        } catch (Exception e) {
            log.error("Error sending BookingEvent even: {}", bookingEvent);
        }
    }

}
