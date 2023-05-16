-- liquibase formatted sql

-- changeset liquibase:initial_schema runOnChange:false

-- Schema fitnote
CREATE SCHEMA IF NOT EXISTS fitnote AUTHORIZATION postgres;
SET SCHEMA 'fitnote';


-- *** user`
CREATE SEQUENCE IF NOT EXISTS fitnote.user_seq;

CREATE TABLE IF NOT EXISTS fitnote.user
(
    id            BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.user_seq'),
    username      VARCHAR(50)  NOT NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP(0) NULL     DEFAULT CURRENT_TIMESTAMP,
    enabled       SMALLINT     NOT NULL,
    PRIMARY KEY (id)
);


-- *** user_authority`
CREATE SEQUENCE IF NOT EXISTS fitnote.user_authority_seq;

CREATE TABLE IF NOT EXISTS fitnote.user_authority
(
    id          BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.user_authority_seq'),
    authorities VARCHAR(255) NOT NULL,
    user_id     BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_userAuthorities_user
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX IF NOT EXISTS fk_userAuthorities_user_idx ON fitnote.user_authority (user_id ASC);


-- SQLINES DEMO *** exercise
CREATE SEQUENCE IF NOT EXISTS fitnote.exercise_seq;

CREATE TABLE IF NOT EXISTS fitnote.exercise
(
    id               BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.exercise_seq'),
    name             VARCHAR(255) NOT NULL,
    description      VARCHAR(255) NULL,
    involved_muscles VARCHAR(255) NULL,
    exercise_type    VARCHAR(255) NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


--  *** body_measurement
CREATE SEQUENCE IF NOT EXISTS fitnote.body_measurement_seq;

CREATE TABLE IF NOT EXISTS fitnote.body_measurement
(
    id                    BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.body_measurement_seq'),
    measurement_type      VARCHAR(255) NOT NULL,
    measurement_date_time TIMESTAMP(0) NOT NULL,
    user_id               BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_bodyMeasurements_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE UNIQUE INDEX IF NOT EXISTS body_measurement_id_UNIQUE on fitnote.body_measurement (id ASC);
CREATE INDEX IF NOT EXISTS fk_bodyMeasurements_user1_idx ON fitnote.body_measurement (user_id ASC);


-- *** training_plan
CREATE SEQUENCE IF NOT EXISTS fitnote.training_plan_seq;

CREATE TABLE IF NOT EXISTS fitnote.training_plan
(
    id                 BIGINT        NOT NULL DEFAULT NEXTVAL('fitnote.training_plan_seq'),
    name               VARCHAR(255)  NULL,
    type               VARCHAR(255)  NOT NULL,
    difficulty         VARCHAR(255)  NULL,
    created_by_user    SMALLINT      NOT NULL,
    creation_date_time TIMESTAMP(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description        VARCHAR(1000) NULL,
    PRIMARY KEY (id)
);


-- *** meal
CREATE SEQUENCE IF NOT EXISTS fitnote.meal_seq;

CREATE TABLE IF NOT EXISTS fitnote.meal
(
    id              BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.meal_seq'),
    name            VARCHAR(255) NOT NULL,
    type            VARCHAR(255) NULL,
    created_by_user SMALLINT     NOT NULL,
    meal_date_time  TIMESTAMP(0) NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


-- *** userActivity`
CREATE SEQUENCE IF NOT EXISTS fitnote.user_activity_seq;

CREATE TABLE IF NOT EXISTS fitnote.user_activity
(
    id             BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.user_activity_seq'),
    name           VARCHAR(255) NULL,
    password       VARCHAR(32)  NULL,
    start_date     TIMESTAMP(0) NULL     DEFAULT CURRENT_TIMESTAMP,
    finish_date    TIMESTAMP(0) NULL,
    coleries_burnt INT          NULL,
    user_id        BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_activity_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX IF NOT EXISTS fk_user_activity_user1_idx ON fitnote.user_activity (user_id ASC);


-- *** training_plan_exercise`
CREATE TABLE IF NOT EXISTS fitnote.training_plan_exercise
(
    training_plan_id         BIGINT        NOT NULL,
    exercise_id              BIGINT        NOT NULL,
    exercise_sequence_number INT           NOT NULL,
    note                     VARCHAR(1000) NULL,
    PRIMARY KEY (training_plan_id, exercise_id),
    CONSTRAINT fk_exercisePlans_has_exercises_exercisePlans1
        FOREIGN KEY (training_plan_id)
            REFERENCES fitnote.training_plan (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_exercisePlans_has_exercises_exercises1
        FOREIGN KEY (exercise_id)
            REFERENCES fitnote.exercise (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX IF NOT EXISTS fk_exercisePlans_has_exercises_exercises1_idx ON fitnote.training_plan_exercise (exercise_id ASC);
CREATE INDEX IF NOT EXISTS fk_exercisePlans_has_exercises_exercisePlans1_idx on fitnote.training_plan_exercise (training_plan_id ASC);


-- *** sleep_diary`
CREATE TABLE IF NOT EXISTS fitnote.sleep_diary
(
    start_date_time   TIMESTAMP(0)  NULL DEFAULT CURRENT_TIMESTAMP,
    finish_date_time  TIMESTAMP(0)  NULL,
    awakenings_number INT           NULL,
    note              VARCHAR(1000) NULL,
    user_id           BIGINT        NOT NULL,
    CONSTRAINT fk_sleep_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- *** user_training_plan`
CREATE TABLE IF NOT EXISTS fitnote.user_training_plan
(
    user_id          BIGINT NOT NULL,
    exercisePlans_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, exercisePlans_id),
    CONSTRAINT fk_user_has_exercisePlans_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_user_has_exercisePlans_exercisePlans1
        FOREIGN KEY (exercisePlans_id)
            REFERENCES fitnote.training_plan (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX IF NOT EXISTS fk_user_has_exercisePlans_exercisePlans1_idx ON fitnote.user_training_plan (exercisePlans_id ASC);
CREATE INDEX IF NOT EXISTS fk_user_has_exercisePlans_user1_idx ON fitnote.user_training_plan (user_id ASC);


-- *** ingredient`
CREATE SEQUENCE IF NOT EXISTS fitnote.ingredient_seq;

CREATE TABLE IF NOT EXISTS fitnote.ingredient
(
    id               BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.ingredient_seq'),
    name             VARCHAR(255) NOT NULL,
    quantity         INT          NOT NULL,
    state_of_matter  VARCHAR(255) NOT NULL,
    measurement_unit VARCHAR(255) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by_user  SMALLINT     NOT NULL,
    user_id          BIGINT       NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_ingredients_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX IF NOT EXISTS fk_ingredients_user1_idx ON fitnote.ingredient (user_id ASC);


-- *** meal_ingredient`
CREATE TABLE IF NOT EXISTS fitnote.meal_ingredient
(
    meal_id       BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    PRIMARY KEY (meal_id, ingredient_id),
    CONSTRAINT fk_meals_has_ingredients_meals1
        FOREIGN KEY (meal_id)
            REFERENCES fitnote.meal (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_meals_has_ingredients_ingredients1
        FOREIGN KEY (ingredient_id)
            REFERENCES fitnote.ingredient (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
CREATE INDEX IF NOT EXISTS fk_meals_has_ingredients_ingredients1_idx ON fitnote.meal_ingredient (ingredient_id ASC);
CREATE INDEX IF NOT EXISTS fk_meals_has_ingredients_meals1_idx ON fitnote.meal_ingredient (meal_id ASC);


-- *** training`
CREATE SEQUENCE IF NOT EXISTS fitnote.training_seq;

CREATE TABLE IF NOT EXISTS fitnote.training
(
    id               BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.training_seq'),
    start_date_time  TIMESTAMP(0) NULL     DEFAULT CURRENT_TIMESTAMP,
    finish_date_time TIMESTAMP(0) NULL,
    user_id          BIGINT       NOT NULL,
    training_plan_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT training_id_UNIQUE UNIQUE (id),
    CONSTRAINT fk_trainings_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_trainings_exercisePlans1
        FOREIGN KEY (training_plan_id)
            REFERENCES fitnote.training_plan (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_trainings_user1_idx ON fitnote.training (user_id ASC);
CREATE INDEX IF NOT EXISTS fk_trainings_exercisePlans1_idx ON fitnote.training (training_plan_id ASC);


-- *** exercise_set`
CREATE SEQUENCE IF NOT EXISTS fitnote.exercise_set_seq;

CREATE TABLE IF NOT EXISTS fitnote.exercise_set
(
    id               BIGINT      NOT NULL DEFAULT NEXTVAL('fitnote.exercise_set_seq'),
    repeats          INT         NULL,
    weight           INT         NULL,
    weight_unit      VARCHAR(30) NULL     DEFAULT CURRENT_TIMESTAMP,
    set_completed    SMALLINT    NULL,
    training_plan_id BIGINT      NOT NULL,
    exercise_id      BIGINT      NOT NULL,
    training_id      BIGINT      NULL,
    PRIMARY KEY (id),
    CONSTRAINT exercise_set_id_UNIQUE UNIQUE (id),
    CONSTRAINT fk_training_plan_exercise_set_training_plan_exercise1
        FOREIGN KEY (training_plan_id, exercise_id)
            REFERENCES fitnote.training_plan_exercise (training_plan_id, exercise_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_exercise_set_training1
        FOREIGN KEY (training_id)
            REFERENCES fitnote.training (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_training_plan_exercise_set_training_plan_exercise1_idx ON fitnote.exercise_set (training_plan_id ASC, exercise_id ASC);
CREATE INDEX IF NOT EXISTS fk_exercise_set_training1_idx ON fitnote.exercise_set (training_id ASC);


--  *** user_meal`
CREATE TABLE IF NOT EXISTS fitnote.user_meal
(
    user_id        BIGINT   NOT NULL,
    meals_id       BIGINT   NULL,
    favourite_meal SMALLINT NOT NULL,
    PRIMARY KEY (user_id, meals_id),

    CONSTRAINT fk_user_has_meals_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_user_has_meals_meals1
        FOREIGN KEY (meals_id)
            REFERENCES fitnote.meal (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_user_has_meals_meals1_idx ON fitnote.user_meal (meals_id ASC);
CREATE INDEX IF NOT EXISTS fk_user_has_meals_user1_idx ON fitnote.user_meal (user_id ASC);


-- *** wellbeing_note`
CREATE SEQUENCE IF NOT EXISTS fitnote.wellbeing_note_seq;

CREATE TABLE IF NOT EXISTS fitnote.wellbeing_note
(
    id                         BIGINT        NOT NULL DEFAULT NEXTVAL('fitnote.wellbeing_note_seq'),
    wellbeing_note             INT           NULL,
    stress_note                INT           NULL,
    stressful_situations_notes VARCHAR(1000) NULL,
    other_notes                VARCHAR(1000) NULL,
    note_date_time             TIMESTAMP(0)  NOT NULL,
    user_id                    BIGINT        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_wellbeingNotes_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_wellbeingNotes_user1_idx ON fitnote.wellbeing_note (user_id ASC);


-- *** user_goal`
CREATE SEQUENCE IF NOT EXISTS fitnote.user_goal_seq;

CREATE TABLE IF NOT EXISTS fitnote.user_goal
(
    id                  BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.user_goal_seq'),
    goal_type           VARCHAR(255) NOT NULL,
    start_date_time     TIMESTAMP(0) NOT NULL,
    finish_date_time    TIMESTAMP(0) NOT NULL,
    goal_starting_value INT          NOT NULL,
    goal_target_value   INT          NOT NULL,
    user_id             BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_userGoals_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_userGoals_user1_idx ON fitnote.user_goal (user_id ASC);


-- *** user_settings`
CREATE SEQUENCE IF NOT EXISTS fitnote.user_settings_seq;

CREATE TABLE IF NOT EXISTS fitnote.user_settings
(
    id          BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.user_settings_seq'),
    language    VARCHAR(255) NULL,
    weight_unit VARCHAR(40)  NULL,
    length_unit VARCHAR(40)  NULL     DEFAULT CURRENT_TIMESTAMP,
    user_id     BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_settings_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE INDEX IF NOT EXISTS fk_user_settings_user1_idx ON fitnote.user_settings (user_id ASC);


-- *** friendship`
CREATE SEQUENCE IF NOT EXISTS fitnote.friendship_seq;

CREATE TABLE IF NOT EXISTS fitnote.friendship
(
    id                 BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.friendship_seq'),
    requester_id       BIGINT       NOT NULL,
    receiver_id        BIGINT       NOT NULL,
    creation_date_time TIMESTAMP(0) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_friendship_user1
        FOREIGN KEY (requester_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_friendship_user2
        FOREIGN KEY (receiver_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE INDEX IF NOT EXISTS fk_friendship_user1_idx ON fitnote.friendship (requester_id ASC);
CREATE INDEX IF NOT EXISTS fk_friendship_user2_idx ON fitnote.friendship (receiver_id ASC);


-- *** friendship_status`
CREATE TABLE IF NOT EXISTS fitnote.friendship_status
(
    id                  BIGINT       NOT NULL,
    specifier_id        BIGINT       NOT NULL,
    status              VARCHAR(45)  NOT NULL,
    specified_date_time TIMESTAMP(0) NOT NULL,
    friendship_id       BIGINT       NOT NULL,
    PRIMARY KEY (id, friendship_id),
    CONSTRAINT fk_friendship_status_user1
        FOREIGN KEY (specifier_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_friendship_status_friendship1
        FOREIGN KEY (friendship_id)
            REFERENCES fitnote.friendship (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_friendship_status_user1_idx ON fitnote.friendship_status (specifier_id ASC);
CREATE INDEX IF NOT EXISTS fk_friendship_status_friendship1_idx ON fitnote.friendship_status (friendship_id ASC);


-- *** user_details`
CREATE SEQUENCE IF NOT EXISTS fitnote.user_details_seq;

CREATE TABLE IF NOT EXISTS fitnote.user_details
(
    id         BIGINT       NOT NULL DEFAULT NEXTVAL('fitnote.user_details_seq'),
    name       VARCHAR(16)  NOT NULL,
    surname    VARCHAR(255) NULL,
    birth_date DATE         NOT NULL,
    gender     VARCHAR(6)   NULL,
    user_id    BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_details_user1
        FOREIGN KEY (user_id)
            REFERENCES fitnote.user (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE INDEX IF NOT EXISTS fk_user_details_user1_idx ON fitnote.user_details (user_id ASC);



