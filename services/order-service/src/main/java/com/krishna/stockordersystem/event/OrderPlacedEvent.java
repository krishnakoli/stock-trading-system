package com.krishna.stockordersystem.event;

import com.krishna.stockordersystem.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderPlacedEvent {

    private String orderId;

    private String clientId;

    private String symbol;

    private Integer quantity;

    private Double price;

    private String orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}