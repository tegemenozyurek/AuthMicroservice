package com.example.authmicroservice.controller;

import com.example.authmicroservice.dto.mapper.UserMapper;
import com.example.authmicroservice.dto.request.CreateUserRequest;
import com.example.authmicroservice.dto.request.UpdateUserRequest;
import com.example.authmicroservice.dto.response.UserResponse;
import com.example.authmicroservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)))
    })
    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/byId/{id}")
    public UserResponse getUserById(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id) {
        return userMapper.toResponse(userService.getUserById(id));
    }

    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/byEmail/{email}")
    public UserResponse getUserByEmail(
            @Parameter(description = "User email address", required = true) @PathVariable String email) {
        return userMapper.toResponse(userService.getUserByEmail(email));
    }

    /////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Get active users", description = "Retrieve a list of all active users")
    @GetMapping("/activeUsers")
    public List<UserResponse> getActiveUsers() {
        return userService.getActiveUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get inactive users", description = "Retrieve a list of all inactive users")
    @GetMapping("/inactiveUsers")
    public List<UserResponse> getInactiveUsers() {
        return userService.getInactiveUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Change user password", description = "Update a user's password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("{id}/editPassword/{newPassword}")
    public UserResponse editPassword(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "New password", required = true) @PathVariable String newPassword) {
        return userMapper.toResponse(userService.editPassword(id, newPassword));
    }

    /////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Create a new user", description = "Register a new user with email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/createUser")
    public UserResponse registerUser(@Valid @RequestBody CreateUserRequest request) {
        return userMapper.toResponse(userService.createUser(request));
    }

    @Operation(summary = "Update user", description = "Update an existing user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/updateUser/{id}")
    public UserResponse updateUser(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Valid @RequestBody UpdateUserRequest request) {
        return userMapper.toResponse(userService.updateUser(id, request));
    }

    @Operation(summary = "Delete user", description = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
