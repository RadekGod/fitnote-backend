-- liquibase formatted sql

-- changeset liquibase:diet_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS diet;

CREATE TABLE IF NOT EXISTS diet.meal
(
    id              BIGSERIAL         NOT NULL PRIMARY KEY,
    name            VARCHAR(255)      NOT NULL,
    kilocalories   REAL              NULL,
    proteins        REAL              NULL,
    carbohydrates   REAL              NULL,
    fat             REAL              NULL,
    salt            REAL              NULL,
    meal_date       TIMESTAMP NOT NULL,
    user_id         BIGINT    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user (id)
);