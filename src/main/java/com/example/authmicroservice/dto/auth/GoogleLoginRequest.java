package com.example.authmicroservice.dto.auth;

import lombok.Data;

@Data
public class GoogleLoginRequest {
    private String idToken;   // Google JWT (from frontend Google Sign-In)
    private String clientKey; // The client using this auth service
}
