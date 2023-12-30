-- liquibase formatted sql

-- changeset liquibase:user_management_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_management;

CREATE TABLE IF NOT EXISTS user_management.user_settings
(
    id          BIGSERIAL   NOT NULL PRIMARY KEY,
    weight_unit VARCHAR(40) NULL,
    length_unit VARCHAR(40) NULL
);


CREATE TABLE IF NOT EXISTS user_management.user
(
    id               BIGSERIAL PRIMARY KEY,
    email            VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    creation_time    TIMESTAMP    NOT NULL,
    enabled          BOOLEAN      NOT NULL,
    first_name       VARCHAR(255) NULL,
    last_name        VARCHAR(255) NULL,
    birth_date       DATE         NULL,
    gender           VARCHAR(6)   NULL,
    user_settings_id BIGINT NOT NULL,
    FOREIGN KEY (user_settings_id)
        REFERENCES user_management.user_settings (id)
);

CREATE TABLE IF NOT EXISTS user_management.authority
(
    id   BIGSERIAL   NOT NULL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    default_authority BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS user_management.user_authority
(
    user_id      BIGINT    NOT NULL,
    authority_id BIGINT    NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user (id),
    FOREIGN KEY (authority_id)
        REFERENCES user_management.authority (id),
    CONSTRAINT user_authority_pkey PRIMARY KEY (user_id, authority_id)
);