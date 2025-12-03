package com.example.authmicroservice.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String accessToken;   // Short-lived JWT for authorization
    private String refreshToken;  // Long-lived token stored in DB
    private String tokenType;     // Usually "Bearer"
    private long expiresIn;       // Access token expiration in seconds
}
