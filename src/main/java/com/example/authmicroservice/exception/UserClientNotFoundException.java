package com.example.authmicroservice.exception;

public class UserClientNotFoundException extends RuntimeException {
    
    public UserClientNotFoundException(String message) {
        super(message);
    }
    
    public UserClientNotFoundException(Integer id) {
        super("UserClient relationship not found with id: " + id);
    }
}

