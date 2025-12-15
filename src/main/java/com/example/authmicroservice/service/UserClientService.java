package com.example.authmicroservice.service;

import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.entity.UserClient;
import com.example.authmicroservice.repository.UserClientRepository;
import com.example.authmicroservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClientService {
    private final UserClientRepository userClientRepository;
    private final UserRepository userRepository;

    public UserClientService(UserClientRepository userClientRepository, UserRepository userRepository) {
        this.userClientRepository = userClientRepository;
        this.userRepository = userRepository;
    }

    public List<UserClient> getAllUserClients() {
        return userClientRepository.findAll();
    }

    public UserClient getUserClientById(Integer id) {
        return userClientRepository.findById(id).orElse(null);
    }

    /////////////////////////////////////////////////////////////////////////

    // List every user of a client
    public List<User> getClientUsers(Integer clientId) {
        return userClientRepository.findByClient_Id(clientId)
                .stream()
                .map(UserClient::getUser)
                .collect(Collectors.toList());
    }

    // List every client of a user
    public List<AuthClient> getUserClients(Integer userId) {
        return userClientRepository.findByUser_Id(userId)
                .stream()
                .map(UserClient::getClient)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////

    public UserClient createUserClient(UserClient userClient){
        return userClientRepository.save(userClient);
    }

    public UserClient updateUserClient(UserClient userClient){
        UserClient existing = userClientRepository.findById(userClient.getId())
                .orElseThrow(() -> new RuntimeException("UserClient not found"));

        if (userClient.getUser() != null) {
            existing.setUser(userClient.getUser());
        }
        if (userClient.getClient() != null) {
            existing.setClient(userClient.getClient());
        }
        if (userClient.getRole() != null) {
            existing.setRole(userClient.getRole());
        }

        return userClientRepository.save(existing);
    }

    public void deleteUserClient(Integer id){
        userClientRepository.deleteById(id);
    }

}
