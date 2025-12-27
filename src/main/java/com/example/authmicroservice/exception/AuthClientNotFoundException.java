package com.example.authmicroservice.exception;

public class AuthClientNotFoundException extends RuntimeException {
    
    public AuthClientNotFoundException(String message) {
        super(message);
    }
    
    public AuthClientNotFoundException(Integer id) {
        super("AuthClient not found with id: " + id);
    }
    
    public AuthClientNotFoundException(String field, String value) {
        super("AuthClient not found with " + field + ": " + value);
    }
}

