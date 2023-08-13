-- liquibase formatted sql

-- changeset liquibase:training_history_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS training_history;

CREATE TABLE IF NOT EXISTS training_history.training
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    note VARCHAR(1000) NULL,
    start_time    TIMESTAMP    NOT NULL,
    finish_time    TIMESTAMP    NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
);

CREATE TABLE IF NOT EXISTS training_history.training_exercise
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    measure_unit VARCHAR(30) NULL,
    note VARCHAR(1000) NULL,
    exercise_id BIGINT NOT NULL,
    training_id BIGINT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES training_plan.exercise (id),
    FOREIGN KEY (training_id) REFERENCES training_history.training (id)
);

CREATE TABLE IF NOT EXISTS training_history.training_exercise_set
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    weight REAL NULL,
    repeats INT NULL,
    completed BOOLEAN NULL,
    note VARCHAR(1000) NULL,
    training_exercise_id BIGINT NOT NULL,
    FOREIGN KEY (training_exercise_id) REFERENCES training_history.training_exercise (id)
);