package com.klu.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private Long userId;
    private String email;
    private String role;
}