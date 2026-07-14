package com.krishna.stockordersystem.dto.response;

import com.krishna.stockordersystem.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponse {

    private String orderId;
    private String symbol;
    private Integer quantity;
    private Double price;
    private String orderType;
    private OrderStatus status;
}