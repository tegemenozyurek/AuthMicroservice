package com.example.authmicroservice.dto.request;

import lombok.Data;

@Data
public class UpdateUserClientRequest {
    
    private Integer userId;
    
    private Integer clientId;
    
    private String role;
}

