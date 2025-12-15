package com.example.authmicroservice.repository;

import com.example.authmicroservice.entity.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClientRepository extends JpaRepository<UserClient, Integer> {
    List<UserClient> findByClient_Id(Integer clientId);
    List<UserClient> findByUser_Id(Integer userId);
}
