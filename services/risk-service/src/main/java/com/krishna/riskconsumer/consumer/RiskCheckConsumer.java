package com.krishna.riskconsumer.consumer;

import com.krishna.riskconsumer.constants.KafkaTopics;
import com.krishna.riskconsumer.enums.RiskStatus;
import com.krishna.riskconsumer.event.OrderPlacedEvent;
import com.krishna.riskconsumer.event.RiskApprovedEvent;
import com.krishna.riskconsumer.event.RiskRejectedEvent;
import com.krishna.riskconsumer.producer.RiskEventProducer;
import com.krishna.riskconsumer.service.RiskValidateServiceImpl;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class RiskCheckConsumer {

    private final RiskValidateServiceImpl riskVailidateService;
    private final RiskEventProducer riskEventProducer;

    @KafkaListener(
            topics = KafkaTopics.TRADE_ORDER,
            groupId = "risk-group")
    public void consume(
            OrderPlacedEvent event) {
        try {
            log.info("ConsumingEvent: {}",event);
            riskVailidateService.validate(event);

            riskEventProducer.publishApproved(
                    RiskApprovedEvent.builder()
                            .orderId(event.getOrderId())
                            .orderId(event.getOrderId())
                            .symbol(event.getSymbol())
                            .quantity(event.getQuantity())
                            .price(event.getPrice())
                            .orderType(event.getOrderType())
                            .status(RiskStatus.RISKAPPROVED)
                            .build());

        } catch (Exception ex) {

            riskEventProducer.publishRejected(
                    RiskRejectedEvent.builder()
                            .orderId(event.getOrderId())
                            .status(RiskStatus.RISKREJECTED)
                            .msg(ex.getMessage())
                            .build());
        }
    }
}
