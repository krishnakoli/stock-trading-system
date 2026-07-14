package com.krishna.notificationservice.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RiskRejectedEvent {

    private String orderId;

    private String msg;
}
