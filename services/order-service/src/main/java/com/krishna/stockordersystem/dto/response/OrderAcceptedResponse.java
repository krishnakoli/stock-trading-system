package com.krishna.stockordersystem.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderAcceptedResponse {

    private String orderId;
    private String message;
}