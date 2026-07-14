package com.krishna.portfolioservice.factory;


import com.krishna.portfolioservice.processor.OrderProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderProcessorFactory {

    private final Map<String,
                OrderProcessor> processors;

    public OrderProcessorFactory(
            List<OrderProcessor> list) {

        processors =
                list.stream()
                        .collect(
                                Collectors.toMap(
                                        OrderProcessor
                                                ::supportedType,
                                        Function.identity()));
    }

    public OrderProcessor get(
            String orderType) {

        return processors.get(orderType);
    }
}