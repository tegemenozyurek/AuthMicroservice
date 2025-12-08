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

    ////////////////////////////////////////////////////////////////////////

    @GetMapping("/byId/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/byEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    /////////////////////////////////////////////////////////////////////////

    @GetMapping("/activeUsers")
    public List<User> getActiveUsers() {
        return userService.getActiveUsers();
    }

    @GetMapping("/inactiveUsers")
    public List<User> getInactiveUsers() {
        return userService.getInactiveUsers();
    }

    /////////////////////////////////////////////////////////////////////////

    @PatchMapping("{id}/newPassword/{newPassword}")
    public User editPassword(@PathVariable Integer id, @PathVariable String newPassword) {
        return userService.editPassword(id, newPassword);
    }

    /////////////////////////////////////////////////////////////////////////

    @PostMapping("/registerLocal")
    public User registerUser(@RequestBody User user) {
        return userService.registerLocal(user);
    }
}
