package com.krishna.portfolioservice.producer;

import com.krishna.portfolioservice.event.PortfolioEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PortfolioProducer {

    private final KafkaTemplate<String, PortfolioEvent> kafkatemplate;

    public void publish(PortfolioEvent event){
        kafkatemplate.send("portfolio-update-info",event);
    }
}
