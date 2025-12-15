package com.example.authmicroservice.controller;

import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.service.AuthClientService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/byId/{id}")
    public AuthClient getAuthClientById(@PathVariable Integer id) {
        return authClientService.getAuthClientById(id);
    }

    @GetMapping("/byKey/{clientKey}")
    public AuthClient getAuthClientByKey(@PathVariable String clientKey) {
        return authClientService.getAuthClientByKey(clientKey);
    }

    /////////////////////////////////////////////////////////////////////////

    @GetMapping("/activeClients")
    public List<AuthClient> getActiveAuthClients(){
        return authClientService.getActiveAuthClients();
    }

    @GetMapping("/inactiveClients")
    public List<AuthClient> getInactiveAuthClients(){
        return authClientService.getInactiveAuthClients();
    }

    /////////////////////////////////////////////////////////////////////////

    @PostMapping("/createAuthClient")
    public AuthClient createAuthClient(@RequestBody AuthClient authClient){
        return authClientService.createAuthClient(authClient);
    }

    @PutMapping("/updateAuthClient/{id}")
    public AuthClient updateAuthClient(@PathVariable Integer id, @RequestBody AuthClient authClient){
        authClient.setId(id);
        return authClientService.updateAuthClient(authClient);
    }

    @DeleteMapping("/deleteAuthClient/{id}")
    public void deleteAuthClient(@PathVariable Integer id){
        authClientService.deleteAuthClient(id);
    }
}
