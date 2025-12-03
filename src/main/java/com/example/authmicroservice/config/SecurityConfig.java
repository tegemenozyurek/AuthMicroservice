package com.example.authmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/testConnection").permitAll()
                        .requestMatchers("/api/v1/users/all").permitAll()
                        .requestMatchers("/api/v1/users/byId/**").permitAll()
                        .requestMatchers("/api/v1/users/byEmail/**").permitAll()
                        .requestMatchers("/api/v1/users/isEmailExists/**").permitAll()
                        .anyRequest().denyAll()
                );

        return http.build();
    }
}
