package com.example.authmicroservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "auth_clients")
public class AuthClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clientKey;

    private String name;

    private String description;

    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
