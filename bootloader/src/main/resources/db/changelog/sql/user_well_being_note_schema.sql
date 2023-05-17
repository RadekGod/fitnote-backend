-- liquibase formatted sql

-- changeset liquibase:user_well_being_note_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS user_well_being_note;

CREATE TABLE IF NOT EXISTS user_well_being_note.user_well_being_note_entry
(
    id                         BIGSERIAL     NOT NULL PRIMARY KEY,
    wellbeing_note             INT           NULL,
    stress_note                INT           NULL,
    stressful_situations_notes VARCHAR(1000) NULL,
    other_notes                VARCHAR(1000) NULL,
    note_date_time             TIMESTAMP     NOT NULL,
    user_id                    BIGINT        NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_management.user (id)
);