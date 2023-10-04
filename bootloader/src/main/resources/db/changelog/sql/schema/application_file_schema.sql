-- liquibase formatted sql

-- changeset liquibase:application_file_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS application_file;

CREATE TABLE IF NOT EXISTS application_file.application_file
(
    id            BIGSERIAL NOT NULL PRIMARY KEY,
    data          OID     NOT NULL,
    creation_date TIMESTAMP NOT NULL
);
