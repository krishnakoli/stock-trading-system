package com.krishna.notificationservice.event;

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

    private String status;

    private String msg;
}

