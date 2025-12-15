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

    public AuthClient getAuthClientById(Integer id) {
        return authClientRepository.findById(id).orElse(null);
    }

    public AuthClient getAuthClientByKey(String clientKey){
        return authClientRepository.findByClientKey(clientKey);
    }

    /////////////////////////////////////////////////////////////////////////

    public List<AuthClient> getActiveAuthClients(){
        return authClientRepository.findByIsActive(true);
    }

    public List<AuthClient> getInactiveAuthClients(){
        return authClientRepository.findByIsActive(false);
    }

    /////////////////////////////////////////////////////////////////////////

    public AuthClient createAuthClient(AuthClient authClient){
        return authClientRepository.save(authClient);
    }

    public AuthClient updateAuthClient(AuthClient authClient){
        AuthClient existing = authClientRepository.findById(authClient.getId())
                .orElseThrow(() -> new RuntimeException("AuthClient not found"));

        if (authClient.getClientKey() != null) existing.setClientKey(authClient.getClientKey());
        if (authClient.getName() != null) existing.setName(authClient.getName());
        if (authClient.getDescription() != null) existing.setDescription(authClient.getDescription());
        if (authClient.getIsActive() != null) existing.setIsActive(authClient.getIsActive());

        return authClientRepository.save(existing);
    }

    public void deleteAuthClient(Integer id){
        authClientRepository.deleteById(id);
    }

}
