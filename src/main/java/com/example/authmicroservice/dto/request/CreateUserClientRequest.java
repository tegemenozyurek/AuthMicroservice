package com.example.authmicroservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserClientRequest {
    
    @NotNull(message = "User ID is required")
    private Integer userId;
    
    @NotNull(message = "Client ID is required")
    private Integer clientId;
    
    private String role;
}

