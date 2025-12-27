package com.example.authmicroservice.service;

import com.example.authmicroservice.dto.mapper.UserClientMapper;
import com.example.authmicroservice.dto.request.CreateUserClientRequest;
import com.example.authmicroservice.dto.request.UpdateUserClientRequest;
import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.entity.User;
import com.example.authmicroservice.entity.UserClient;
import com.example.authmicroservice.exception.AuthClientNotFoundException;
import com.example.authmicroservice.exception.UserClientNotFoundException;
import com.example.authmicroservice.exception.UserNotFoundException;
import com.example.authmicroservice.repository.AuthClientRepository;
import com.example.authmicroservice.repository.UserClientRepository;
import com.example.authmicroservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClientService {
    private final UserClientRepository userClientRepository;
    private final UserRepository userRepository;
    private final AuthClientRepository authClientRepository;
    private final UserClientMapper userClientMapper;

    public UserClientService(UserClientRepository userClientRepository, 
                            UserRepository userRepository,
                            AuthClientRepository authClientRepository,
                            UserClientMapper userClientMapper) {
        this.userClientRepository = userClientRepository;
        this.userRepository = userRepository;
        this.authClientRepository = authClientRepository;
        this.userClientMapper = userClientMapper;
    }

    public List<UserClient> getAllUserClients() {
        return userClientRepository.findAll();
    }

    public UserClient getUserClientById(Integer id) {
        return userClientRepository.findById(id)
                .orElseThrow(() -> new UserClientNotFoundException(id));
    }

    /////////////////////////////////////////////////////////////////////////



    /////////////////////////////////////////////////////////////////////////

    public UserClient createUserClient(CreateUserClientRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        
        AuthClient client = authClientRepository.findById(request.getClientId())
                .orElseThrow(() -> new AuthClientNotFoundException(request.getClientId()));
        
        UserClient userClient = userClientMapper.toEntity(request, user, client);
        return userClientRepository.save(userClient);
    }

    public UserClient updateUserClient(Integer id, UpdateUserClientRequest request){
        UserClient existing = userClientRepository.findById(id)
                .orElseThrow(() -> new UserClientNotFoundException(id));

        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        }

        AuthClient client = null;
        if (request.getClientId() != null) {
            client = authClientRepository.findById(request.getClientId())
                    .orElseThrow(() -> new AuthClientNotFoundException(request.getClientId()));
        }

        userClientMapper.updateEntityFromRequest(existing, request, user, client);
        return userClientRepository.save(existing);
    }

    public void deleteUserClient(Integer id){
        userClientRepository.deleteById(id);
    }
}
