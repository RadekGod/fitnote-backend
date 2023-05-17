-- liquibase formatted sql

-- changeset liquibase:user_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_details;

CREATE TABLE IF NOT EXISTS user_details.user
(
    id            BIGSERIAL PRIMARY KEY,
    keycloak_id   VARCHAR(50)  NOT NULL UNIQUE,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP    NOT NULL,
    enabled       BOOLEAN      NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    birth_date    DATE         NOT NULL,
    gender        VARCHAR(6)   NULL
);


-- CREATE TABLE IF NOT EXISTS user_details.user_authority
-- (
--     id          BIGSERIAL PRIMARY KEY,
--     authorities VARCHAR(255) NOT NULL,
--     user_id     BIGINT       NOT NULL,
--     FOREIGN KEY (user_id)
--         REFERENCES user_details.user (id)
-- );


CREATE TABLE IF NOT EXISTS user_details.user_settings
(
    id          BIGSERIAL    NOT NULL PRIMARY KEY,
    language    VARCHAR(255) NULL,
    weight_unit VARCHAR(40)  NULL,
    length_unit VARCHAR(40)  NULL,
    user_id     BIGINT       NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_details.user (id)
);