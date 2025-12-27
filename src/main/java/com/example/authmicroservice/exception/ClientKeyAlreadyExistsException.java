package com.example.authmicroservice.exception;

public class ClientKeyAlreadyExistsException extends RuntimeException {
    
    public ClientKeyAlreadyExistsException(String clientKey) {
        super("Client key already exists: " + clientKey);
    }
}

