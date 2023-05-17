-- liquibase formatted sql

-- changeset liquibase:body_measurement_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS body_measurement;

CREATE TABLE IF NOT EXISTS body_measurement.body_measurement_entry
(
    id                    BIGSERIAL                NOT NULL PRIMARY KEY,
    measurement_type      VARCHAR(255)             NOT NULL,
    measurement_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    user_id               BIGINT                   NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user (id)
);