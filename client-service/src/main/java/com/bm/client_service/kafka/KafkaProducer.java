package com.bm.client_service.kafka;

import client.events.ClientEvent;
import com.bm.client_service.model.Client;
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

    public void sendEvent(Client client) {
        ClientEvent clientEvent = ClientEvent.newBuilder()
                .setClientId(client.getId().toString())
                .setName(client.getName())
                .setEmail(client.getEmail())
                .setEventType("CLIENT_CREATED")
                .build();

        try {
            kafkaTemplate.send("client", clientEvent.toByteArray());
        } catch (Exception e) {
            log.error("Error sending ClientEvent even: {}", clientEvent);
        }
    }

}
