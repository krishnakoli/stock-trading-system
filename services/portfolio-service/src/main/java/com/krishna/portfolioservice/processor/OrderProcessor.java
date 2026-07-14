package com.krishna.portfolioservice.processor;


import com.krishna.portfolioservice.event.RiskApprovedEvent;

public interface OrderProcessor {

    void process(RiskApprovedEvent event);

    String supportedType();
}
