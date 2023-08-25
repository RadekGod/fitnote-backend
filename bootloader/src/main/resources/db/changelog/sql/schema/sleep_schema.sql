-- liquibase formatted sql

-- changeset liquibase:sleep_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS sleep;

CREATE TABLE IF NOT EXISTS sleep.sleep
(
    id                BIGSERIAL     NOT NULL PRIMARY KEY,
    start_date        TIMESTAMP     NULL,
    finish_date       TIMESTAMP     NULL,
    rating            REAL          NULL,
    awakenings_number SMALLINT      NULL,
    note              VARCHAR(1000) NULL,
    creation_date     TIMESTAMP     NULL,
    user_id           BIGINT        NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user_details (id)
);