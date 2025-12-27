package com.example.authmicroservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAuthClientRequest {
    
    @NotBlank(message = "Client key is required")
    @Size(min = 3, max = 100, message = "Client key must be between 3 and 100 characters")
    private String clientKey;
    
    @NotBlank(message = "Name is required")
    @Size(max = 150, message = "Name must not exceed 150 characters")
    private String name;
    
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
    
    private Boolean isActive = true;
}

