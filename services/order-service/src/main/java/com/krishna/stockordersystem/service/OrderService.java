package com.krishna.stockordersystem.service;

import com.krishna.stockordersystem.dto.request.OrderRequest;
import com.krishna.stockordersystem.dto.response.OrderAcceptedResponse;
import com.krishna.stockordersystem.dto.response.OrderResponse;

public interface OrderService {

    OrderAcceptedResponse placeOrder(OrderRequest request);
    OrderResponse getOrderByOrderId(String orderId);
}