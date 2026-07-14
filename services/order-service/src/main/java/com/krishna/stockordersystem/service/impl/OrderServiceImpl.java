package com.krishna.stockordersystem.service.impl;

import com.krishna.stockordersystem.dto.request.OrderRequest;
import com.krishna.stockordersystem.dto.response.OrderAcceptedResponse;
import com.krishna.stockordersystem.dto.response.OrderResponse;
import com.krishna.stockordersystem.enums.OrderStatus;
import com.krishna.stockordersystem.event.OrderPlacedEvent;
import com.krishna.stockordersystem.exception.OrderNotFoundException;
import com.krishna.stockordersystem.model.Order;
import com.krishna.stockordersystem.repository.OrderRepository;
import com.krishna.stockordersystem.service.OrderService;
import com.krishna.stockordersystem.producer.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer producer;

    @Override
    public OrderAcceptedResponse placeOrder(OrderRequest request) {

        log.info("Placing order for symbol: {}", request.getSymbol());

        // 1. Create Entity
        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .clientId(request.getClientId())
                .symbol(request.getSymbol())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .orderType(request.getOrderType())
                .status(OrderStatus.NEW)
                .build();

        log.info("Saving order in DB with orderId: {}", order.getOrderId());

        order.setStatus(OrderStatus.SAVED);
        // 2. Save to DB
        Order savedOrder = orderRepository.save(order);

        log.info("Order saved successfully: {}", savedOrder.getOrderId());

        savedOrder.setStatus(OrderStatus.SENT_TO_KAFKA);

        log.info("Publishing order event to Kafka: {}", savedOrder.getOrderId());
        // 3. Publish Kafka Event
        OrderPlacedEvent event = OrderPlacedEvent.builder()
                .orderId(savedOrder.getOrderId())
                .clientId(savedOrder.getClientId())
                .symbol(savedOrder.getSymbol())
                .quantity(savedOrder.getQuantity())
                .price(savedOrder.getPrice())
                .orderType(savedOrder.getOrderType())
                .build();

        producer.publish(event);

        log.info("Kafka event published successfully for orderId: {}", savedOrder.getOrderId());

        return OrderAcceptedResponse.builder()
                .orderId(savedOrder.getOrderId())
                .message("Order accepted for processing")
                .build();
    }

    @Override
    public OrderResponse getOrderByOrderId(String orderId) {

        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .symbol(order.getSymbol())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .orderType(order.getOrderType())
                .status(order.getStatus())
                .build();
    }
}