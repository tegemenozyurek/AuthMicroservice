-- Create database (optional)
CREATE DATABASE IF NOT EXISTS railway
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE railway;

-- Main users table for both local (email + password) and Google accounts
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Unique email used for local login and contact
    email VARCHAR(150) NOT NULL UNIQUE,
    
    -- User's display name
    full_name VARCHAR(150) NULL,
    
    -- Password hash for email + password login (NULL if user only uses Google)
    password_hash VARCHAR(255) NULL,
    
    -- Google unique ID (sub claim from Google ID token), NULL if user does not use Google
    google_sub VARCHAR(255) UNIQUE NULL,
    
    -- Optional profile picture URL
    profile_picture VARCHAR(255) NULL,
    
    -- Account status flag (1 = active, 0 = disabled)
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    
    -- Email verification status for local auth
    email_verified TINYINT(1) NOT NULL DEFAULT 0,
    
    -- Timestamps for auditing
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP NULL DEFAULT NULL,
    
    -- At least one auth method should be present (MySQL 8+ enforces this)
    CONSTRAINT chk_at_least_one_auth
        CHECK (password_hash IS NOT NULL OR google_sub IS NOT NULL)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table that represents each external project/application using this auth service
CREATE TABLE auth_clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Public identifier sent by the project (e.g. in headers or JSON body)
    client_key VARCHAR(100) NOT NULL UNIQUE,
    
    -- Project / application name
    name VARCHAR(150) NOT NULL,
    
    -- Optional description
    description VARCHAR(255) NULL,
    
    -- Project status flag (1 = active, 0 = disabled)
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Links users to projects (multi-tenant mapping)
CREATE TABLE user_clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Reference to the user
    user_id INT NOT NULL,
    
    -- Reference to the client/project
    client_id INT NOT NULL,
    
    -- Optional role of the user inside that specific project (e.g. ADMIN, USER)
    role VARCHAR(50) NULL,
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_uc_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
        
    CONSTRAINT fk_uc_client
        FOREIGN KEY (client_id) REFERENCES auth_clients(id)
        ON DELETE CASCADE,
        
    -- One user cannot be duplicated inside the same project
    UNIQUE KEY uq_user_client (user_id, client_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Optional table for storing refresh tokens (recommended if you use refresh tokens)
CREATE TABLE user_refresh_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    
    -- Reference to the authenticated user
    user_id INT NOT NULL,
    
    -- Refresh token value (store hashed value in production)
    token VARCHAR(500) NOT NULL,
    
    -- Token expiration datetime
    expires_at DATETIME NOT NULL,
    
    -- Flag to revoke tokens (1 = revoked, 0 = active)
    revoked TINYINT(1) NOT NULL DEFAULT 0,
    
    -- Minimal metadata for security/debugging
    user_agent VARCHAR(255) NULL,
    ip_address VARCHAR(45) NULL,
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_refresh_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    
    -- Ensure one token value is not duplicated
    UNIQUE KEY uq_refresh_token (token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
