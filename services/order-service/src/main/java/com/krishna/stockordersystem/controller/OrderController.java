package com.krishna.stockordersystem.controller;

import com.krishna.stockordersystem.dto.request.OrderRequest;
import com.krishna.stockordersystem.dto.response.OrderAcceptedResponse;
import com.krishna.stockordersystem.dto.response.OrderResponse;
import com.krishna.stockordersystem.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderAcceptedResponse placeOrder(@Valid @RequestBody OrderRequest request) {

        log.info("Received order request: {}", request);

        return orderService.placeOrder(request);
    }
    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable String orderId){
        return orderService.getOrderByOrderId(orderId);
    }
}