package com.example.authmicroservice.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String clientKey;
}
