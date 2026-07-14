package com.krishna.portfolioservice.processor;

import com.krishna.portfolioservice.event.RiskApprovedEvent;
import com.krishna.portfolioservice.model.Portfolio;
import com.krishna.portfolioservice.respository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyOrderProcessor
        implements OrderProcessor {

    private final PortfolioRepository repository;

    @Override
    public void process(
            RiskApprovedEvent event) {

        Portfolio portfolio =
                repository.findByClientIdAndSymbol(
                                event.getClientId(),
                                event.getSymbol())
                        .orElse(
                                Portfolio.builder()
                                        .clientId(
                                                event.getClientId())
                                        .symbol(
                                                event.getSymbol())
                                        .quantity(0)
                                        .averagePrice(0D)
                                        .build());

        int oldQty = portfolio.getQuantity();

        double avg =
                ((portfolio.getAveragePrice() * oldQty)
                        + (event.getPrice()
                        * event.getQuantity()))
                        / (oldQty
                        + event.getQuantity());

        portfolio.setQuantity(
                oldQty + event.getQuantity());

        portfolio.setAveragePrice(avg);

        repository.save(portfolio);
    }

    @Override
    public String supportedType() {
        return "BUY";
    }
}
