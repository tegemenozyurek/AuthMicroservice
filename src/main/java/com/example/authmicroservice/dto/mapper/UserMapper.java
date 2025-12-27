package com.example.authmicroservice.dto.mapper;

import com.example.authmicroservice.dto.request.CreateUserRequest;
import com.example.authmicroservice.dto.request.UpdateUserRequest;
import com.example.authmicroservice.dto.response.UserResponse;
import com.example.authmicroservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .profilePicture(user.getProfilePicture())
                .isActive(user.getIsActive())
                .emailVerified(user.getEmailVerified())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
    
    public User toEntity(CreateUserRequest request) {
        if (request == null) {
            return null;
        }
        
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .passwordHash(null) // Will be hashed in service
                .googleSub(request.getGoogleSub())
                .profilePicture(request.getProfilePicture())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .emailVerified(request.getEmailVerified() != null ? request.getEmailVerified() : false)
                .build();
    }
    
    public void updateEntityFromRequest(User user, UpdateUserRequest request) {
        if (user == null || request == null) {
            return;
        }
        
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        // Password will be hashed in service layer
        if (request.getGoogleSub() != null) {
            user.setGoogleSub(request.getGoogleSub());
        }
        if (request.getProfilePicture() != null) {
            user.setProfilePicture(request.getProfilePicture());
        }
        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }
        if (request.getEmailVerified() != null) {
            user.setEmailVerified(request.getEmailVerified());
        }
    }
}

