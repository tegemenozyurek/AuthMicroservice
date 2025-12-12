package com.example.authmicroservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {

    private Integer id;

    private String email;

    private String fullName;

    private String profilePicture;

    private Boolean isActive;

    private Boolean emailVerified;

    private String role;

    private LocalDateTime createdAt;
}
