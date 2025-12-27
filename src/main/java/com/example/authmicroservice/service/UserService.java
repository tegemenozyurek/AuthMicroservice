package com.example.authmicroservice.service;

import com.example.authmicroservice.dto.mapper.UserMapper;
import com.example.authmicroservice.dto.request.CreateUserRequest;
import com.example.authmicroservice.dto.request.UpdateUserRequest;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /////////////////////////////////////////////////////////////////////////

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /////////////////////////////////////////////////////////////////////////

    public List<User> getActiveUsers() {
        return userRepository.findByIsActive(true);
    }

    public List<User> getInactiveUsers(){
        return userRepository.findByIsActive(false);
    }

    /////////////////////////////////////////////////////////////////////////

    public User editPassword(Integer id, String newPassword){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPasswordHash(newPassword);
        return userRepository.save(user);
    }

    /////////////////////////////////////////////////////////////////////////

    public User createUser(CreateUserRequest request) {
        User user = userMapper.toEntity(request);
        return userRepository.save(user);
    }

    public User updateUser(Integer id, UpdateUserRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateEntityFromRequest(existing, request);
        return userRepository.save(existing);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
