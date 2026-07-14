package com.krishna.portfolioservice.consumer;

import com.krishna.portfolioservice.enums.PortfolioStats;
import com.krishna.portfolioservice.event.PortfolioEvent;
import com.krishna.portfolioservice.event.RiskApprovedEvent;
import com.krishna.portfolioservice.producer.PortfolioProducer;
import com.krishna.portfolioservice.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PortfolioConsumer {

    private final PortfolioService service;

    private final PortfolioProducer publisher;

    @KafkaListener(
            topics = "risk-approved",
            groupId = "portfolio-group")
    public void consume(
            RiskApprovedEvent event) {

        try {
            log.info("ConsumingEvent: {}",event);
            service.process(event);
            publisher.publish(
                    PortfolioEvent.builder()
                            .orderId(event.getOrderId())
                            .status(PortfolioStats.UPDATED)
                            .build()
            );
        }catch (Exception e){
            publisher.publish(
                    PortfolioEvent.builder()
                            .orderId(event.getOrderId())
                            .status(PortfolioStats.NOTUPDATED)
                            .msg(e.getMessage())
                            .build()
            );
        }
        log.info(
                "Portfolio updated {}",
                event.getOrderId());
    }
}