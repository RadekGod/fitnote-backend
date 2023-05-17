-- liquibase formatted sql

-- changeset liquibase:user_goal_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_goal;

CREATE TABLE IF NOT EXISTS user_goal.user_goal_entry
(
    id                  BIGSERIAL    NOT NULL PRIMARY KEY,
    goal_type           VARCHAR(255) NOT NULL,
    start_date_time     TIMESTAMP    NOT NULL,
    finish_date_time    TIMESTAMP    NOT NULL,
    goal_starting_value INT          NOT NULL,
    goal_target_value   INT          NOT NULL,
    user_id             BIGINT       NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user (id)
);