package com.example.authmicroservice.repository;

import com.example.authmicroservice.entity.AuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthClientRepository extends JpaRepository<AuthClient, Integer> {
    AuthClient findByClientKey(String clientKey);
    List<AuthClient> findByIsActive(Boolean isActive);
}
