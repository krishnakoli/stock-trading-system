package com.krishna.riskconsumer.service.validation;

import com.krishna.riskconsumer.event.OrderPlacedEvent;
import org.springframework.stereotype.Component;

@Component
public class QuantityValidation
        implements RiskValidationStrategy {

    @Override
    public void validate(OrderPlacedEvent order) {

        if (order.getQuantity() > 10000) {
            throw new RuntimeException(
                    "Quantity Exceeded");
        }
    }
}