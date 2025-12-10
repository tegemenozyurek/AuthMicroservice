package com.example.authmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DbTestController {

    private final DataSource dataSource;

    public DbTestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/testConnection")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "OK";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
