INSERT INTO users (username, password, email) VALUES
                                                  ('user1', 'password1', 'user1@example.com'),
                                                  ('user2', 'password2', 'user2@example.com'),
                                                  ('user3', 'password3', 'user3@example.com');
INSERT INTO project (name, description, created_date) VALUES
    ('Project A', 'Description for Project A', '2024-01-25'),
    ('Project B', 'Description for Project B', '2024-01-26'),
    ('Project C', 'Description for Project C', '2024-01-27');
INSERT INTO project_users (related_entity_id, project_id, user_id) VALUES
    (1, 1, 1),  -- Проект 1 связан с пользователем 1
    (2, 1, 2),  -- Проект 1 связан с пользователем 2
    (3, 2, 3);  -- Проект 2 связан с пользователем 3