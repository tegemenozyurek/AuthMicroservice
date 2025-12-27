package com.example.authmicroservice.service;

import com.example.authmicroservice.dto.mapper.AuthClientMapper;
import com.example.authmicroservice.dto.request.CreateAuthClientRequest;
import com.example.authmicroservice.dto.request.UpdateAuthClientRequest;
import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.repository.AuthClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthClientService {
    private final AuthClientRepository authClientRepository;
    private final AuthClientMapper authClientMapper;

    public AuthClientService(AuthClientRepository authClientRepository, AuthClientMapper authClientMapper) {
        this.authClientRepository = authClientRepository;
        this.authClientMapper = authClientMapper;
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

    public AuthClient createAuthClient(CreateAuthClientRequest request){
        AuthClient authClient = authClientMapper.toEntity(request);
        return authClientRepository.save(authClient);
    }

    public AuthClient updateAuthClient(Integer id, UpdateAuthClientRequest request){
        AuthClient existing = authClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AuthClient not found"));

        authClientMapper.updateEntityFromRequest(existing, request);
        return authClientRepository.save(existing);
    }

    public void deleteAuthClient(Integer id){
        authClientRepository.deleteById(id);
    }
}
