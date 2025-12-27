package com.example.authmicroservice.controller;

import com.example.authmicroservice.dto.mapper.UserClientMapper;
import com.example.authmicroservice.dto.request.CreateUserClientRequest;
import com.example.authmicroservice.dto.request.UpdateUserClientRequest;
import com.example.authmicroservice.dto.response.UserClientResponse;
import com.example.authmicroservice.service.UserClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user-clients")
@Tag(name = "User-Client Management", description = "APIs for managing user-client relationships (multi-tenant)")
public class UserClientController {
    private final UserClientService userClientService;
    private final UserClientMapper userClientMapper;

    public UserClientController(UserClientService userClientService, UserClientMapper userClientMapper) {
        this.userClientService = userClientService;
        this.userClientMapper = userClientMapper;
    }

    @Operation(summary = "Get all user-client relationships", description = "Retrieve all user-client mappings")
    @GetMapping("/all")
    public List<UserClientResponse> getAllUserClients() {
        return userClientService.getAllUserClients().stream()
                .map(userClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get user-client by ID", description = "Retrieve a user-client relationship by ID")
    @GetMapping("/byId/{id}")
    public UserClientResponse getUserClientById(
            @Parameter(description = "User-Client ID", required = true) @PathVariable Integer id) {
        return userClientMapper.toResponse(userClientService.getUserClientById(id));
    }

    /////////////////////////////////////////////////////////////////////////



    /////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Create user-client relationship", description = "Link a user to an authentication client with optional role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User-client relationship created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User or Client not found")
    })
    @PostMapping("/createUserClient")
    public UserClientResponse createUserClient(@Valid @RequestBody CreateUserClientRequest request) {
        return userClientMapper.toResponse(userClientService.createUserClient(request));
    }

    @Operation(summary = "Update user-client relationship", description = "Update an existing user-client relationship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User-client relationship updated successfully"),
            @ApiResponse(responseCode = "404", description = "User-client relationship not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/updateUserClient/{id}")
    public UserClientResponse updateUserClient(
            @Parameter(description = "User-Client ID", required = true) @PathVariable Integer id,
            @Valid @RequestBody UpdateUserClientRequest request) {
        return userClientMapper.toResponse(userClientService.updateUserClient(id, request));
    }

    @Operation(summary = "Delete user-client relationship", description = "Remove the link between a user and an authentication client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User-client relationship deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User-client relationship not found")
    })
    @DeleteMapping("/deleteUserClient/{id}")
    public void deleteUserClient(
            @Parameter(description = "User-Client ID", required = true) @PathVariable Integer id) {
        userClientService.deleteUserClient(id);
    }
}
