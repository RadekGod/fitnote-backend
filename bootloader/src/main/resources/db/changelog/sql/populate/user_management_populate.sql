-- liquibase formatted sql

-- changeset liquibase:user_management_populate_1 runOnChange:false

INSERT INTO user_management.user_settings (id, weight_unit, length_unit)
VALUES (1, 'KILOGRAM', 'CENTIMETER');

-- INSERT INTO user_management.user_details
-- (id, keycloak_id, email, creation_time, enabled, first_name, last_name, birth_date, gender, user_settings_id
-- ) VALUES (1, 'bbe0e622-7f11-4b56-8cd6-91a73bf2f196', 'suser@mail.com', '2023-07-16 21:12:57.746883', true,
--           'Saskia', 'van den Bercken', '2023-05-18', 'FEMALE', 1);


INSERT INTO user_management.user_details
(id, email, password, creation_time, enabled, first_name, last_name, birth_date, gender, user_settings_id
) VALUES (1, 'suser@mail.com', '$2a$12$o.RaYdAUh1JWwiPwdCzSCesdIJH9KJ.4Si28LdiV14lJj1kYU2zdS', '2023-07-16 21:12:57.746883', true,
          'Saskia', 'van den Bercken', '2023-05-18', 'FEMALE', 1);

INSERT INTO user_management.authority (id, name, default_authority)
VALUES (1, 'STANDARD_USER', true);

INSERT INTO user_management.user_authority (user_id, authority_id)
VALUES (1, 1);


