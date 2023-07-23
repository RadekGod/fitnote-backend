-- liquibase formatted sql

-- changeset liquibase:user_populate_1 runOnChange:false

INSERT INTO user_management.user_settings (id, weight_unit, length_unit)
VALUES (1, 'KILOGRAM', 'CENTIMETER');

INSERT INTO user_management.user_details
(id, keycloak_id, email, creation_time, enabled, first_name, last_name, birth_date, gender, user_settings_id
) VALUES (1, 'bbe0e622-7f11-4b56-8cd6-91a73bf2f196', 'suser@mail.com', '2023-07-16 21:12:57.746883', true,
          'Saskia', 'van den Bercken', '2023-05-18', 'FEMALE', 1);


