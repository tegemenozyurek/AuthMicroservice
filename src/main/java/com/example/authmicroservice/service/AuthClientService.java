package com.example.authmicroservice.service;

import com.example.authmicroservice.dto.mapper.AuthClientMapper;
import com.example.authmicroservice.dto.request.CreateAuthClientRequest;
import com.example.authmicroservice.dto.request.UpdateAuthClientRequest;
import com.example.authmicroservice.entity.AuthClient;
import com.example.authmicroservice.exception.AuthClientNotFoundException;
import com.example.authmicroservice.exception.ClientKeyAlreadyExistsException;
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
        return authClientRepository.findById(id)
                .orElseThrow(() -> new AuthClientNotFoundException(id));
    }

    public AuthClient getAuthClientByKey(String clientKey){
        AuthClient authClient = authClientRepository.findByClientKey(clientKey);
        if (authClient == null) {
            throw new AuthClientNotFoundException("clientKey", clientKey);
        }
        return authClient;
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
        // Check if client key already exists
        if (authClientRepository.findByClientKey(request.getClientKey()) != null) {
            throw new ClientKeyAlreadyExistsException(request.getClientKey());
        }
        
        AuthClient authClient = authClientMapper.toEntity(request);
        return authClientRepository.save(authClient);
    }

    public AuthClient updateAuthClient(Integer id, UpdateAuthClientRequest request){
        AuthClient existing = authClientRepository.findById(id)
                .orElseThrow(() -> new AuthClientNotFoundException(id));

        // Check if client key is being updated and if it already exists
        if (request.getClientKey() != null && !request.getClientKey().equals(existing.getClientKey())) {
            if (authClientRepository.findByClientKey(request.getClientKey()) != null) {
                throw new ClientKeyAlreadyExistsException(request.getClientKey());
            }
        }

        authClientMapper.updateEntityFromRequest(existing, request);
        return authClientRepository.save(existing);
    }

    public void deleteAuthClient(Integer id){
        authClientRepository.deleteById(id);
    }
}
