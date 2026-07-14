package com.krishna.riskconsumer.event;

import com.krishna.riskconsumer.enums.RiskStatus;
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

    private String symbol;

    private Integer quantity;

    private Double price;

    private String orderType;

    @Enumerated(EnumType.STRING)
    private RiskStatus status;

}
