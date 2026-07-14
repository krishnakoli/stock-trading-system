package com.krishna.riskconsumer.producer;

import com.krishna.riskconsumer.event.RiskApprovedEvent;
import com.krishna.riskconsumer.event.RiskRejectedEvent;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;


@Component
@RequiredArgsConstructor
public class RiskEventProducer {

    private final KafkaTemplate<String, RiskApprovedEvent> kafkaTemplate1;
    private final KafkaTemplate<String, RiskRejectedEvent> kafkaTemplate2;


    public void publishApproved(
            RiskApprovedEvent event) {

        kafkaTemplate1.send(
                "risk-approved",
                event);
    }

    public void publishRejected(
            RiskRejectedEvent event) {

        kafkaTemplate2.send(
                "risk-rejected",
                event);
    }
}