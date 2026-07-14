package com.krishna.riskconsumer.service.validation;

import com.krishna.riskconsumer.event.OrderPlacedEvent;

public interface RiskValidationStrategy {
    void validate(OrderPlacedEvent event);
}
