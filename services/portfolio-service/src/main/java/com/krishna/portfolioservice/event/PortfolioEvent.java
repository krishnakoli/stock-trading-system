package com.krishna.portfolioservice.event;

import com.krishna.portfolioservice.enums.PortfolioStats;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PortfolioEvent {

    private  String orderId;

    @Enumerated(EnumType.STRING)
    private PortfolioStats status;

    private String msg;
}
