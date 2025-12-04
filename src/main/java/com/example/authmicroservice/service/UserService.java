package com.example.authmicroservice.service;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//1
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean isEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public User activateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setIsActive(true);
        return userRepository.save(user);
    }

    public User deactivateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setIsActive(false);
        return userRepository.save(user);
    }

    public User renameUser(Integer id, String newName) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullName(newName);
        return userRepository.save(user);
    }


    // create user
    // get user by id
    // get user by email
    // check if email exists

    // activate user (optional)
    // update last login time (optional)
    // save user (internal use)
}
