-- liquibase formatted sql

-- changeset liquibase:training_plan_schema_1 runOnChange:false


CREATE SCHEMA IF NOT EXISTS training_plan;

CREATE TABLE IF NOT EXISTS training_plan.exercise
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000) NULL,
    main_muscles VARCHAR(255) NULL,
    supportive_muscles VARCHAR(255) NULL,
    exercise_category_groups VARCHAR(255) NULL,
    exercise_type VARCHAR(255) NULL,
    user_id BIGINT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
    );

CREATE TABLE IF NOT EXISTS training_plan.training_plan
(
    id  BIGSERIAL  NOT  NULL  PRIMARY  KEY,
    name VARCHAR(255) NULL,
    training_days VARCHAR(255) NOT NULL,
    note VARCHAR (1000) NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
    );

CREATE TABLE IF NOT EXISTS training_plan.training_plan_exercise
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    measure_unit VARCHAR(30) NULL,
    note VARCHAR(1000) NULL,
    exercise_id BIGINT NOT NULL,
    training_plan_id BIGINT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES training_plan.exercise (id),
    FOREIGN KEY (training_plan_id) REFERENCES training_plan.training_plan (id)
    );

CREATE TABLE IF NOT EXISTS training_plan.exercise_set
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    weight REAL NULL,
    repeats INT NULL,
    completed BOOLEAN NULL,
    note VARCHAR(1000) NULL,
    training_plan_exercise_id BIGINT NOT NULL,
    FOREIGN KEY (training_plan_exercise_id) REFERENCES training_plan.training_plan_exercise (id)
);

