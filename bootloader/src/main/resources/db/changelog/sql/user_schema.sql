-- liquibase formatted sql

-- changeset liquibase:user_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_details;

CREATE TABLE IF NOT EXISTS user_details.user_entry
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50)              NOT NULL,
    email         VARCHAR(255)             NOT NULL,
    password      VARCHAR(255)             NOT NULL,
    creation_time TIMESTAMP WITH TIME ZONE NOT NULL,
    enabled       BOOLEAN                  NOT NULL,
    name          VARCHAR(255)             NOT NULL,
    surname       VARCHAR(255)             NULL,
    birth_date    DATE                     NOT NULL,
    gender        VARCHAR(6)               NULL
);


CREATE TABLE IF NOT EXISTS user_details.user_authority
(
    id          BIGSERIAL PRIMARY KEY,
    authorities VARCHAR(255) NOT NULL,
    user_id     BIGINT       NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_details.user_entry (id)
);


CREATE TABLE IF NOT EXISTS user_details.user_settings
(
    id          BIGSERIAL    NOT NULL PRIMARY KEY,
    language    VARCHAR(255) NULL,
    weight_unit VARCHAR(40)  NULL,
    length_unit VARCHAR(40)  NULL,
    user_id     BIGINT       NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_details.user_entry (id)
);