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
public class RiskRejectedEvent {
    private  String orderId;

    @Enumerated(EnumType.STRING)
    private RiskStatus status;

    private String msg;
}
