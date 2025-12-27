package com.example.authmicroservice.controller;

import com.example.authmicroservice.dto.mapper.AuthClientMapper;
import com.example.authmicroservice.dto.request.CreateAuthClientRequest;
import com.example.authmicroservice.dto.request.UpdateAuthClientRequest;
import com.example.authmicroservice.dto.response.AuthClientResponse;
import com.example.authmicroservice.service.AuthClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth-clients")
public class AuthClientController {
    private final AuthClientService authClientService;
    private final AuthClientMapper authClientMapper;

    public AuthClientController(AuthClientService authClientService, AuthClientMapper authClientMapper) {
        this.authClientService = authClientService;
        this.authClientMapper = authClientMapper;
    }

    @GetMapping("/all")
    public List<AuthClientResponse> getAllAuthClients() {
        return authClientService.getAllAuthClients().stream()
                .map(authClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public AuthClientResponse getAuthClientById(@PathVariable Integer id) {
        return authClientMapper.toResponse(authClientService.getAuthClientById(id));
    }

    @GetMapping("/byKey/{clientKey}")
    public AuthClientResponse getAuthClientByKey(@PathVariable String clientKey) {
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

    @PostMapping("/createAuthClient")
    public AuthClientResponse createAuthClient(@Valid @RequestBody CreateAuthClientRequest request){
        return authClientMapper.toResponse(authClientService.createAuthClient(request));
    }

    @PutMapping("/updateAuthClient/{id}")
    public AuthClientResponse updateAuthClient(@PathVariable Integer id, @Valid @RequestBody UpdateAuthClientRequest request){
        return authClientMapper.toResponse(authClientService.updateAuthClient(id, request));
    }

    @DeleteMapping("/deleteAuthClient/{id}")
    public void deleteAuthClient(@PathVariable Integer id){
        authClientService.deleteAuthClient(id);
    }
}
