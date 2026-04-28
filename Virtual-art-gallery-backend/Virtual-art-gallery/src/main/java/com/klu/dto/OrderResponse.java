package com.klu.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private Long orderId;
    private double totalPrice;
    private String status;
}