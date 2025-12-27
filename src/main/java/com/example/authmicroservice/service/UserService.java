package com.example.authmicroservice.service;

import com.example.authmicroservice.dto.mapper.UserMapper;
import com.example.authmicroservice.dto.request.CreateUserRequest;
import com.example.authmicroservice.dto.request.UpdateUserRequest;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.exception.EmailAlreadyExistsException;
import com.example.authmicroservice.exception.UserNotFoundException;
import com.example.authmicroservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /////////////////////////////////////////////////////////////////////////

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("email", email);
        }
        return user;
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
                .orElseThrow(() -> new UserNotFoundException(id));

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }

    /////////////////////////////////////////////////////////////////////////

    public User createUser(CreateUserRequest request) {
        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        
        User user = userMapper.toEntity(request);
        
        // Hash the password if provided
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            user.setPasswordHash(hashedPassword);
        }
        
        return userRepository.save(user);
    }

    public User updateUser(Integer id, UpdateUserRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Check if email is being updated and if it already exists
        if (request.getEmail() != null && !request.getEmail().equals(existing.getEmail())) {
            if (userRepository.findByEmail(request.getEmail()) != null) {
                throw new EmailAlreadyExistsException(request.getEmail());
            }
        }

        userMapper.updateEntityFromRequest(existing, request);
        
        // Hash the password if it's being updated
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            existing.setPasswordHash(hashedPassword);
        }
        
        return userRepository.save(existing);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
