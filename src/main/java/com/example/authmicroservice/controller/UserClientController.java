package com.example.authmicroservice.controller;

import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.entity.UserClient;
import com.example.authmicroservice.service.UserClientService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-clients")
public class UserClientController {
    public UserClientService userClientService;

    public UserClientController(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @GetMapping("/all")
    public List<UserClient> getAllUserClients() {
        return userClientService.getAllUserClients();
    }

    @GetMapping("/byId/{id}")
    public UserClient getUserClientById(Integer id) {
        return userClientService.getUserClientById(id);
    }

    /////////////////////////////////////////////////////////////////////////

    @GetMapping("/getClientUsers-byClientId/{clientId}")
    public List<User> getClientUsers(Integer clientId) {
        return userClientService.getClientUsers(clientId);
    }

    @GetMapping("/getUserClients-byUserId/{userId}")
    public List<AuthClient> getUserClients(Integer userId) {
        return userClientService.getUserClients(userId);
    }

    /////////////////////////////////////////////////////////////////////////

    @PostMapping("/createUserClient")
    public UserClient createUserClient(@RequestBody UserClient userClient) {
        return userClientService.createUserClient(userClient);
    }

    @GetMapping("/updateUserClient/{id}")
    public UserClient updateUserClient(@PathVariable Integer id, @RequestBody UserClient userClient) {
        userClient.setId(id);
        return userClientService.updateUserClient(userClient);
    }

    @GetMapping("/deleteUserClient/{id}")
    public void deleteUserClient(@PathVariable Integer id) {
        userClientService.deleteUserClient(id);
    }
}
