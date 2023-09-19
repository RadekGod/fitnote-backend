-- liquibase formatted sql

-- changeset liquibase:body_measurement_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS body;

CREATE TABLE IF NOT EXISTS body.body_measurement
(
    id               BIGSERIAL NOT NULL PRIMARY KEY,
    chest            REAL  NULL,
    biceps_left      REAL  NULL,
    biceps_right     REAL  NULL,
    forearm_left     REAL  NULL,
    forearm_right    REAL  NULL,
    waist            REAL  NULL,
    hip              REAL  NULL,
    thigh_left       REAL  NULL,
    thigh_right      REAL  NULL,
    calf_left        REAL  NULL,
    calf_right       REAL  NULL,
    length_unit VARCHAR(40) NOT NULL,
    measurement_date TIMESTAMP NOT NULL,
    user_id          BIGINT    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
);



CREATE TABLE IF NOT EXISTS body.general_measurement
(
    id               BIGSERIAL NOT NULL PRIMARY KEY,
    weight           REAL  NOT NULL,
    height           REAL  NOT NULL,
    bmi              REAL      NOT NULL,
    muscle_content   REAL      NULL,
    body_fat      REAL      NULL,
    length_unit VARCHAR(40) NOT NULL,
    weight_unit VARCHAR(40) NOT NULL,
    measurement_date TIMESTAMP NOT NULL,
    user_id          BIGINT    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
);

-- CREATE TABLE IF NOT EXISTS body.photo
-- (
--     id               BIGSERIAL NOT NULL PRIMARY KEY,
--     creation_date TIMESTAMP NOT NULL,
--     note VARCHAR(1000) NULL,
--     user_id          BIGINT    NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES user_management.user_details (id)
-- );
