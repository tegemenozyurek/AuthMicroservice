package com.example.authmicroservice.controller;

import com.example.authmicroservice.dto.mapper.UserMapper;
import com.example.authmicroservice.dto.request.CreateUserRequest;
import com.example.authmicroservice.dto.request.UpdateUserRequest;
import com.example.authmicroservice.dto.response.UserResponse;
import com.example.authmicroservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userMapper.toResponse(userService.getUserById(id));
    }

    @GetMapping("/byEmail/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
        return userMapper.toResponse(userService.getUserByEmail(email));
    }

    /////////////////////////////////////////////////////////////////////////

    @GetMapping("/activeUsers")
    public List<UserResponse> getActiveUsers() {
        return userService.getActiveUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inactiveUsers")
    public List<UserResponse> getInactiveUsers() {
        return userService.getInactiveUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////

    @PatchMapping("{id}/editPassword/{newPassword}")
    public UserResponse editPassword(@PathVariable Integer id, @PathVariable String newPassword) {
        return userMapper.toResponse(userService.editPassword(id, newPassword));
    }

    /////////////////////////////////////////////////////////////////////////

    @PostMapping("/createUser")
    public UserResponse registerUser(@Valid @RequestBody CreateUserRequest request) {
        return userMapper.toResponse(userService.createUser(request));
    }

    @PutMapping("/updateUser/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserRequest request) {
        return userMapper.toResponse(userService.updateUser(id, request));
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
