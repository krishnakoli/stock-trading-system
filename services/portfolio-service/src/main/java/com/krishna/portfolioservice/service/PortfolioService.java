package com.krishna.portfolioservice.service;

import com.krishna.portfolioservice.event.RiskApprovedEvent;
import com.krishna.portfolioservice.factory.OrderProcessorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final
    OrderProcessorFactory factory;

    public void process(
            RiskApprovedEvent event) {

        factory.get(
                        event.getOrderType())
                .process(event);
    }
}
