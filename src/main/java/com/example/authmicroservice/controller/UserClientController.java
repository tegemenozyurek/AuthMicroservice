package com.example.authmicroservice.controller;

import com.example.authmicroservice.dto.mapper.UserClientMapper;
import com.example.authmicroservice.dto.request.CreateUserClientRequest;
import com.example.authmicroservice.dto.request.UpdateUserClientRequest;
import com.example.authmicroservice.dto.response.UserClientResponse;
import com.example.authmicroservice.service.UserClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user-clients")
public class UserClientController {
    private final UserClientService userClientService;
    private final UserClientMapper userClientMapper;

    public UserClientController(UserClientService userClientService, UserClientMapper userClientMapper) {
        this.userClientService = userClientService;
        this.userClientMapper = userClientMapper;
    }

    @GetMapping("/all")
    public List<UserClientResponse> getAllUserClients() {
        return userClientService.getAllUserClients().stream()
                .map(userClientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public UserClientResponse getUserClientById(@PathVariable Integer id) {
        return userClientMapper.toResponse(userClientService.getUserClientById(id));
    }

    /////////////////////////////////////////////////////////////////////////



    /////////////////////////////////////////////////////////////////////////

    @PostMapping("/createUserClient")
    public UserClientResponse createUserClient(@Valid @RequestBody CreateUserClientRequest request) {
        return userClientMapper.toResponse(userClientService.createUserClient(request));
    }

    @PutMapping("/updateUserClient/{id}")
    public UserClientResponse updateUserClient(@PathVariable Integer id, @Valid @RequestBody UpdateUserClientRequest request) {
        return userClientMapper.toResponse(userClientService.updateUserClient(id, request));
    }

    @DeleteMapping("/deleteUserClient/{id}")
    public void deleteUserClient(@PathVariable Integer id) {
        userClientService.deleteUserClient(id);
    }
}
