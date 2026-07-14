package com.krishna.riskconsumer.service;

import com.krishna.riskconsumer.event.OrderPlacedEvent;
import com.krishna.riskconsumer.service.validation.RiskValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiskValidateServiceImpl implements RiskValidateService {

    private final List<RiskValidationStrategy>
            validations;

    public void validate(
            OrderPlacedEvent order) {

        validations.forEach(
                validation ->
                        validation.validate(order));
    }
}