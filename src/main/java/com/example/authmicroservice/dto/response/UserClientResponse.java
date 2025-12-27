package com.example.authmicroservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserClientResponse {
    
    private Integer id;
    
    private Integer userId;
    
    private String userEmail;
    
    private String userName;
    
    private Integer clientId;
    
    private String clientKey;
    
    private String clientName;
    
    private String role;
    
    private LocalDateTime createdAt;
}

