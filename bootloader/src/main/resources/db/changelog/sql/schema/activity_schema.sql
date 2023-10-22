-- liquibase formatted sql

-- changeset liquibase:activity_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS activity;

CREATE TABLE IF NOT EXISTS activity.activity_type
(
    id                              BIGSERIAL    NOT NULL PRIMARY KEY,
    name                            VARCHAR(255) NULL,
    average_calories_burnt_per_hour REAL         NULL,
    distance_activity               BOOLEAN      NOT NULL,
    user_id                         BIGINT       NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user_details (id)
);

CREATE TABLE IF NOT EXISTS activity.activity
(
    id                BIGSERIAL NOT NULL PRIMARY KEY,
    activity_duration_in_minutes REAL      NULL,
    burnt_calories    INT       NULL,
    distance_traveled REAL      NULL,
    activity_date     TIMESTAMP NOT NULL,
    activity_type_id  BIGINT    NOT NULL,
    user_id           BIGINT    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id),
    FOREIGN KEY (activity_type_id) REFERENCES activity.activity_type (id)
);