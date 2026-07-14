package com.krishna.portfolioservice.event;


import com.krishna.portfolioservice.enums.RiskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RiskApprovedEvent {
    private String orderId;

    private String clientId;

    private String symbol;

    private Integer quantity;

    private Double price;

    private String orderType;

    @Enumerated(EnumType.STRING)
    private RiskStatus status;
}
