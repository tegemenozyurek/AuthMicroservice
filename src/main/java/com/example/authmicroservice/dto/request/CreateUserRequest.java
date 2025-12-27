package com.example.authmicroservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    private String fullName;
    
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    private String googleSub;
    
    private String profilePicture;
    
    private Boolean isActive = true;
    
    private Boolean emailVerified = false;
}

