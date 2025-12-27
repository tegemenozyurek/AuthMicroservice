package com.example.authmicroservice.dto.mapper;

import com.example.authmicroservice.dto.request.CreateUserClientRequest;
import com.example.authmicroservice.dto.request.UpdateUserClientRequest;
import com.example.authmicroservice.dto.response.UserClientResponse;
import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.entity.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientMapper {
    
    public UserClientResponse toResponse(UserClient userClient) {
        if (userClient == null) {
            return null;
        }
        
        User user = userClient.getUser();
        AuthClient client = userClient.getClient();
        
        return UserClientResponse.builder()
                .id(userClient.getId())
                .userId(user != null ? user.getId() : null)
                .userEmail(user != null ? user.getEmail() : null)
                .userName(user != null ? user.getFullName() : null)
                .clientId(client != null ? client.getId() : null)
                .clientKey(client != null ? client.getClientKey() : null)
                .clientName(client != null ? client.getName() : null)
                .role(userClient.getRole())
                .createdAt(userClient.getCreatedAt())
                .build();
    }
    
    public UserClient toEntity(CreateUserClientRequest request, User user, AuthClient client) {
        if (request == null) {
            return null;
        }
        
        return UserClient.builder()
                .user(user)
                .client(client)
                .role(request.getRole())
                .build();
    }
    
    public void updateEntityFromRequest(UserClient userClient, UpdateUserClientRequest request, User user, AuthClient client) {
        if (userClient == null || request == null) {
            return;
        }
        
        if (user != null) {
            userClient.setUser(user);
        }
        if (client != null) {
            userClient.setClient(client);
        }
        if (request.getRole() != null) {
            userClient.setRole(request.getRole());
        }
    }
}

