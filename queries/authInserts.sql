USE railway;

-- Sample users (2 local, 2 Google)
INSERT INTO users (email, full_name, password_hash, google_sub, profile_picture, is_active, email_verified)
VALUES
('egemen@gmail.com', 'Egemen Ozyurek', '$2a$10$hash1', NULL, NULL, 1, 1),

('ayse@gmail.com', 'Ayse Yilmaz', '$2a$10$hash2', NULL, NULL, 1, 0),

('mehmet@gmail.com', 'Mehmet Kaya', NULL, 'google-sub-12345', 'https://img.google.com/1.jpg', 1, 1),

('zeynep@gmail.com', 'Zeynep Demir', NULL, 'google-sub-67890', 'https://img.google.com/2.jpg', 1, 1);

-- Sample projects using the auth service
INSERT INTO auth_clients (client_key, name, description, is_active)
VALUES
('beautime_mobile', 'Beautime Mobile App', 'Customer mobile application', 1),

('beautime_admin', 'Beautime Admin Panel', 'Admin management panel', 1),

('cveey_web', 'Cveey Web Platform', 'Candidate CV platform', 1),

('test_project', 'Test Project', 'Internal testing environment', 1);

-- Mapping users to projects
INSERT INTO user_clients (user_id, client_id, role)
VALUES
(1, 1, 'USER'),     -- Egemen -> Beautime Mobile

(1, 3, 'ADMIN'),    -- Egemen -> Cveey Web (Admin)

(2, 1, 'USER'),     -- Ayse -> Beautime Mobile

(3, 2, 'ADMIN');    -- Mehmet -> Beautime Admin

-- Sample refresh tokens
INSERT INTO user_refresh_tokens (user_id, token, expires_at, revoked, user_agent, ip_address)
VALUES
(1, 'rtoken-egemen-111', '2025-12-31 23:59:59', 0, 'Chrome on macOS', '192.168.1.10'),

(1, 'rtoken-egemen-222', '2025-12-31 23:59:59', 0, 'Safari on iOS', '192.168.1.11'),

(2, 'rtoken-ayse-333', '2025-12-15 23:59:59', 0, 'Firefox on Windows', '192.168.1.12'),

(3, 'rtoken-mehmet-444', '2025-11-30 23:59:59', 1, 'Chrome on Android', '192.168.1.13');

