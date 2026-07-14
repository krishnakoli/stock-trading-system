package com.krishna.riskconsumer.service;


import com.krishna.riskconsumer.event.OrderPlacedEvent;

public interface RiskValidateService {

   void validate(OrderPlacedEvent event);

}
