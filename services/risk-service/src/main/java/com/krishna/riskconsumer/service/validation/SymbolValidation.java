package com.krishna.riskconsumer.service.validation;

import com.krishna.riskconsumer.event.OrderPlacedEvent;
import com.krishna.riskconsumer.respository.TradableSymbolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SymbolValidation
        implements RiskValidationStrategy {

    private final TradableSymbolRepository repository;

    @Override
    public void validate(OrderPlacedEvent order) {

        if (!repository
                .existsBySymbolAndActiveTrue(
                        order.getSymbol())) {

            throw new RuntimeException(
                    "Invalid Symbol");
        }
    }
}
