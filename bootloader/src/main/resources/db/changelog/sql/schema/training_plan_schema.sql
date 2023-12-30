-- liquibase formatted sql

-- changeset liquibase:training_plan_schema_1 runOnChange:false


CREATE SCHEMA IF NOT EXISTS training_plan;

CREATE TABLE IF NOT EXISTS training_plan.exercise
(
    id                  BIGSERIAL     NOT NULL PRIMARY KEY,
    name                VARCHAR(255)  NOT NULL,
    description         VARCHAR(1000) NULL,
    main_muscles        VARCHAR(255)  NULL,
    supportive_muscles  VARCHAR(255)  NULL,
    custom              BOOLEAN       NOT NULL,
    exercise_type       VARCHAR(255)  NOT NULL,
    application_file_id BIGINT        NULL,
    user_id             BIGINT        NULL,
    FOREIGN KEY (application_file_id) REFERENCES application_file.application_file (id),
    FOREIGN KEY (user_id) REFERENCES user_management.user (id)
);

CREATE TABLE IF NOT EXISTS training_plan.exercise_category_group
(
    id            BIGSERIAL   NOT NULL PRIMARY KEY,
    category_name VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS training_plan.exercise_exercise_category_group
(
    exercise_id                BIGINT NOT NULL,
    exercise_category_group_id BIGINT NOT NULL,
    FOREIGN KEY (exercise_id)
        REFERENCES training_plan.exercise (id),
    FOREIGN KEY (exercise_category_group_id)
        REFERENCES training_plan.exercise_category_group (id),
    CONSTRAINT exercise_exercise_category_group_pkey PRIMARY KEY (exercise_id, exercise_category_group_id)
);

CREATE TABLE IF NOT EXISTS training_plan.training_plan
(
    id            BIGSERIAL     NOT NULL PRIMARY KEY,
    name          VARCHAR(255)  NULL,
    training_days VARCHAR(255)  NOT NULL,
    user_id       BIGINT        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user (id)
);

CREATE TABLE IF NOT EXISTS training_plan.training_plan_exercise
(
    id               BIGSERIAL     NOT NULL PRIMARY KEY,
    measurement_unit VARCHAR(30)   NULL,
    note             VARCHAR(1000) NULL,
    exercise_id      BIGINT        NOT NULL,
    training_plan_id BIGINT        NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES training_plan.exercise (id),
    FOREIGN KEY (training_plan_id) REFERENCES training_plan.training_plan (id)
);

CREATE TABLE IF NOT EXISTS training_plan.exercise_set
(
    id                        BIGSERIAL     NOT NULL PRIMARY KEY,
    weight                    REAL          NULL,
    repeats                   INT           NULL,
    note                      VARCHAR(1000) NULL,
    training_plan_exercise_id BIGINT        NOT NULL,
    FOREIGN KEY (training_plan_exercise_id) REFERENCES training_plan.training_plan_exercise (id)
);

