package com.krishna.portfolioservice.processor;

import com.krishna.portfolioservice.event.RiskApprovedEvent;
import com.krishna.portfolioservice.model.Portfolio;
import com.krishna.portfolioservice.respository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellOrderProcessor
        implements OrderProcessor {

    private final PortfolioRepository repository;

    @Override
    public void process(
            RiskApprovedEvent event) {

        Portfolio portfolio =
                repository
                        .findByClientIdAndSymbol(
                                event.getClientId(),
                                event.getSymbol())
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "No Holdings"));

        if (portfolio.getQuantity()
                < event.getQuantity()) {

            throw new RuntimeException(
                    "Insufficient Holdings");
        }

        portfolio.setQuantity(
                portfolio.getQuantity()
                        - event.getQuantity());

        repository.save(portfolio);
    }

    @Override
    public String supportedType() {
        return "SELL";
    }
}