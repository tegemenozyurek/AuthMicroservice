package com.example.authmicroservice.controller;

import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.service.AuthClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth-clients")
public class AuthClientController {
    public AuthClientService authClientService;

    public AuthClientController(AuthClientService authClientService) {
        this.authClientService = authClientService;
    }

    @GetMapping("/all")
    public List<AuthClient> getAllAuthClients() {
        return authClientService.getAllAuthClients();
    }
}
