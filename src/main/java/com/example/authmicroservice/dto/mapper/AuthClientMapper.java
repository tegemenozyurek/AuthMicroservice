package com.example.authmicroservice.dto.mapper;

import com.example.authmicroservice.dto.request.CreateAuthClientRequest;
import com.example.authmicroservice.dto.request.UpdateAuthClientRequest;
import com.example.authmicroservice.dto.response.AuthClientResponse;
import com.example.authmicroservice.entity.AuthClient;
import org.springframework.stereotype.Component;

@Component
public class AuthClientMapper {
    
    public AuthClientResponse toResponse(AuthClient authClient) {
        if (authClient == null) {
            return null;
        }
        
        return AuthClientResponse.builder()
                .id(authClient.getId())
                .clientKey(authClient.getClientKey())
                .name(authClient.getName())
                .description(authClient.getDescription())
                .isActive(authClient.getIsActive())
                .createdAt(authClient.getCreatedAt())
                .build();
    }
    
    public AuthClient toEntity(CreateAuthClientRequest request) {
        if (request == null) {
            return null;
        }
        
        return AuthClient.builder()
                .clientKey(request.getClientKey())
                .name(request.getName())
                .description(request.getDescription())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();
    }
    
    public void updateEntityFromRequest(AuthClient authClient, UpdateAuthClientRequest request) {
        if (authClient == null || request == null) {
            return;
        }
        
        if (request.getClientKey() != null) {
            authClient.setClientKey(request.getClientKey());
        }
        if (request.getName() != null) {
            authClient.setName(request.getName());
        }
        if (request.getDescription() != null) {
            authClient.setDescription(request.getDescription());
        }
        if (request.getIsActive() != null) {
            authClient.setIsActive(request.getIsActive());
        }
    }
}

