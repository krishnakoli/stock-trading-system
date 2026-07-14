package com.krishna.stockordersystem.producer;

import com.krishna.stockordersystem.constants.KafkaTopics;
import com.krishna.stockordersystem.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void publish(OrderPlacedEvent event) {

        log.info("Sending event to Kafka topic for orderId: {}", event.getOrderId());
        kafkaTemplate.send(
                KafkaTopics.TRADE_ORDER,
                event
        );

        log.info("Event sent successfully for orderId: {}", event.getOrderId());
    }
}