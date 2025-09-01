package com.bm.analytics_service.kafka;


import client.events.ClientEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "client", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            ClientEvent clientEvent = ClientEvent.parseFrom(event);
            // any analytics logic

            log.info("Receive client event: [ClientId = {}, ClientName = {}, ClientEmail = {}]", clientEvent.getClientId(), clientEvent.getName(), clientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error while deserializing event {}", e.getMessage());
        }
    }
}
