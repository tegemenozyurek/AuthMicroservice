package com.example.authmicroservice.dto.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
    private String clientKey;
}
