-- liquibase formatted sql

-- changeset liquibase:user_activity_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_activity;

CREATE TABLE IF NOT EXISTS user_activity.user_activity_entry
(
    id             BIGSERIAL    NOT NULL PRIMARY KEY,
    name           VARCHAR(255) NULL,
    start_date     TIMESTAMP    NULL,
    finish_date    TIMESTAMP    NULL,
    coleries_burnt INT          NULL,
    user_id        BIGINT       NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_details.user (id)
);