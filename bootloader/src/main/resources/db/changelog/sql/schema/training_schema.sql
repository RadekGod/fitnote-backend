-- liquibase formatted sql

-- changeset liquibase:training_schema_1 runOnChange:false


CREATE SCHEMA IF NOT EXISTS training;

CREATE TABLE IF NOT EXISTS training.exercise
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

CREATE TABLE IF NOT EXISTS training.training_plan
(
    id  BIGSERIAL  NOT  NULL  PRIMARY  KEY,
    name VARCHAR(255) NULL,
    training_days VARCHAR(255) NOT NULL,
    note VARCHAR (1000) NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
    );

CREATE TABLE IF NOT EXISTS training.exercise_set
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    weight REAL NULL,
    measure_unit VARCHAR(30) NULL,
    repeats INT NULL,
    completed BOOLEAN NULL,
    note VARCHAR(1000) NULL,
    exercise_id BIGINT NOT NULL,
    training_plan_id BIGINT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES training.exercise (id),
    FOREIGN KEY (training_plan_id) REFERENCES training.training_plan (id)
);


--
--
--
--
-- CREATE TABLE IF NOT EXISTS training.user_training_plan
-- (
--     user_id BIGINT NOT NULL,
--     training_plan_id BIGINT NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES user_management.user (id),
--     FOREIGN KEY (training_plan_id) REFERENCES training.training_plan (id)
-- );
--
--
-- CREATE TABLE IF NOT EXISTS training.training_plan_exercise
-- (
--     id BIGSERIAL NOT NULL PRIMARY KEY,
--     training_plan_id BIGINT NOT NULL,
--     exercise_id BIGINT NOT NULL,
--     exercise_sequence_number INT NOT NULL,
--     note VARCHAR(1000) NULL,
--     FOREIGN KEY (training_plan_id) REFERENCES training.training_plan (id),
--     FOREIGN KEY(exercise_id) REFERENCES training.exercise (id)
-- );
--
--
-- CREATE TABLE IF NOT EXISTS training.training_entry
-- (
--     id BIGSERIAL NOT NULL PRIMARY KEY,
--     start_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
--     finish_date_time TIMESTAMP WITH TIME ZONE NULL,
--     user_id BIGINT NOT NULL,
--     training_plan_id BIGINT NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES user_management.user (id),
--     FOREIGN KEY (training_plan_id) REFERENCES training.training_plan (id)
-- );
--
--

--
--
--
