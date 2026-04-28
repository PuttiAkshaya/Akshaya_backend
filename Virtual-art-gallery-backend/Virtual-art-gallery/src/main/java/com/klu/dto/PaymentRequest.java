package com.klu.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long orderId;
    private String method;
}