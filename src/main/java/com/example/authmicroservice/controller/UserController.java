package com.example.authmicroservice.controller;

import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/byId/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/byEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/isEmailExists/{email}")
    public Boolean isEmailExists(@PathVariable String email) {
        return userService.isEmailExists(email);
    }

    @PatchMapping("/{id}/activate")
    public User activateUser(@PathVariable Integer id) {
        return userService.activateUser(id);
    }

    @PatchMapping("/{id}/deactivate")
    public User deactivateUser(@PathVariable Integer id) {
        return userService.deactivateUser(id);
    }

    @PatchMapping("/{id}/rename/{newName}")
    public User renameUser(@PathVariable Integer id, @PathVariable String newName) {
        return userService.renameUser(id, newName);
    }

}
