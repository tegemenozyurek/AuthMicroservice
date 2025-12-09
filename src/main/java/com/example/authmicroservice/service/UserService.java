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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
