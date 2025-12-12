package com.example.authmicroservice.service;
//2

import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.repository.AuthClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthClientService {
    private final AuthClientRepository authClientRepository;

    public AuthClientService(AuthClientRepository authClientRepository) {
        this.authClientRepository = authClientRepository;
    }

    /////////////////////////////////////////////////////////////////////////

    public List<AuthClient> getAllAuthClients() {
        return authClientRepository.findAll();
    }



    /////////////////////////////////////////////////////////////////////////

    //getClientByKey(clientKey)
    //getClientById(clientId)
    //getActiveUsers
    //getInactiveUsers
    //create
    //update
    //edit
    //delete

}
