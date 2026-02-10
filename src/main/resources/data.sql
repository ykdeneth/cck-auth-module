-- =========================
-- PERMISSIONS
-- =========================
INSERT INTO permissions (id, permission_name) VALUES (1, 'user only');
INSERT INTO permissions (id, permission_name) VALUES (2, 'admin only');
INSERT INTO permissions (id, permission_name) VALUES (3, 'staff only');

-- =========================
-- ROLES
-- =========================
INSERT INTO roles (id, role_name, role_desc) VALUES (1, 'ADMIN', 'Administrator role');
INSERT INTO roles (id, role_name, role_desc) VALUES (2, 'USER', 'Standard user role');

-- =========================
-- ROLES_PERMISSIONS
-- =========================
INSERT INTO roles_permissions (id, roles_id, permissions_id) VALUES (1, 1, 1);
INSERT INTO roles_permissions (id, roles_id, permissions_id) VALUES (2, 1, 2);
INSERT INTO roles_permissions (id, roles_id, permissions_id) VALUES (3, 2, 3);

-- =========================
-- USERS
-- =========================
-- INSERT INTO users (id, user_id, username, password, status)
-- VALUES (1, UUID(), 'admin', 'admin123', true);
--
-- INSERT INTO users (id, user_id, username, password, status)
-- VALUES (2, UUID(), 'john_doe', 'password', true);

-- =========================
-- USERS_ROLES
-- =========================
INSERT INTO users_roles (id, user_id, roles_id, status) VALUES (1, 1, 1, 'ACTIVE');
INSERT INTO users_roles (id, user_id, roles_id, status) VALUES (2, 2, 2, 'ACTIVE');

-- =========================
-- REFRESH_TOKEN
-- =========================
-- INSERT INTO refresh_token (id, token, expiry_date, user_id)
-- VALUES (1, 'token_admin_123', NOW() + INTERVAL '7 DAY', 1);

-- INSERT INTO refresh_token (id, token, expiry_date, user_id)
-- VALUES (2, 'token_john_123', NOW() + INTERVAL '7 DAY', 2);
