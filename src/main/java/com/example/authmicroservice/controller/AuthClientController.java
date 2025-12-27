package com.example.authmicroservice.controller;

import com.example.authmicroservice.dto.mapper.AuthClientMapper;
import com.example.authmicroservice.dto.request.CreateAuthClientRequest;
import com.example.authmicroservice.dto.request.UpdateAuthClientRequest;
import com.example.authmicroservice.dto.response.AuthClientResponse;
import com.example.authmicroservice.service.AuthClientService;
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
@RequestMapping("/api/v1/auth-clients")
@Tag(name = "Auth Client Management", description = "APIs for managing authentication clients (projects/applications)")
public class AuthClientController {
    private final AuthClientService authClientService;
    private final AuthClientMapper authClientMapper;

    public AuthClientController(AuthClientService authClientService, AuthClientMapper authClientMapper) {
        this.authClientService = authClientService;
        this.authClientMapper = authClientMapper;
    }

    @Operation(summary = "Get all auth clients", description = "Retrieve a list of all authentication clients")
    @GetMapping("/all")
    public List<AuthClientResponse> getAllAuthClients() {
        return authClientService.getAllAuthClients().stream()
                .map(authClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get auth client by ID", description = "Retrieve an authentication client by ID")
    @GetMapping("/byId/{id}")
    public AuthClientResponse getAuthClientById(
            @Parameter(description = "Auth Client ID", required = true) @PathVariable Integer id) {
        return authClientMapper.toResponse(authClientService.getAuthClientById(id));
    }

    @Operation(summary = "Get auth client by key", description = "Retrieve an authentication client by client key")
    @GetMapping("/byKey/{clientKey}")
    public AuthClientResponse getAuthClientByKey(
            @Parameter(description = "Client Key", required = true) @PathVariable String clientKey) {
        return authClientMapper.toResponse(authClientService.getAuthClientByKey(clientKey));
    }

    /////////////////////////////////////////////////////////////////////////

    @GetMapping("/activeClients")
    public List<AuthClientResponse> getActiveAuthClients(){
        return authClientService.getActiveAuthClients().stream()
                .map(authClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inactiveClients")
    public List<AuthClientResponse> getInactiveAuthClients(){
        return authClientService.getInactiveAuthClients().stream()
                .map(authClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////

    @Operation(summary = "Create auth client", description = "Create a new authentication client (project/application)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auth client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/createAuthClient")
    public AuthClientResponse createAuthClient(@Valid @RequestBody CreateAuthClientRequest request){
        return authClientMapper.toResponse(authClientService.createAuthClient(request));
    }

    @Operation(summary = "Update auth client", description = "Update an existing authentication client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auth client updated successfully"),
            @ApiResponse(responseCode = "404", description = "Auth client not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/updateAuthClient/{id}")
    public AuthClientResponse updateAuthClient(
            @Parameter(description = "Auth Client ID", required = true) @PathVariable Integer id,
            @Valid @RequestBody UpdateAuthClientRequest request){
        return authClientMapper.toResponse(authClientService.updateAuthClient(id, request));
    }

    @Operation(summary = "Delete auth client", description = "Delete an authentication client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auth client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Auth client not found")
    })
    @DeleteMapping("/deleteAuthClient/{id}")
    public void deleteAuthClient(
            @Parameter(description = "Auth Client ID", required = true) @PathVariable Integer id){
        authClientService.deleteAuthClient(id);
    }
}
