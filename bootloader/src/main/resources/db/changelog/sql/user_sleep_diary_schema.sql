-- liquibase formatted sql

-- changeset liquibase:user_sleep_diary_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_sleep_diary;

CREATE TABLE IF NOT EXISTS user_sleep_diary.sleep_diary_entry
(
    id                BIGSERIAL     NOT NULL PRIMARY KEY,
    start_date_time   TIMESTAMP     NULL,
    finish_date_time  TIMESTAMP     NULL,
    awakenings_number INT           NULL,
    note              VARCHAR(1000) NULL,
    user_id           BIGINT        NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user (id)
);