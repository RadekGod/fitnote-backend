-- liquibase formatted sql

-- changeset liquibase:meal_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS meal;

CREATE TABLE IF NOT EXISTS meal.meal_entry
(
    id              BIGSERIAL    NOT NULL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    type            VARCHAR(255) NULL,
    created_by_user BOOLEAN      NOT NULL,
    meal_date_time  TIMESTAMP    NULL
);


CREATE TABLE IF NOT EXISTS meal.ingredient
(
    id               BIGSERIAL    NOT NULL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    quantity         INT          NOT NULL,
    state_of_matter  VARCHAR(255) NOT NULL,
    measurement_unit VARCHAR(255) NOT NULL,
    created_by_user  BOOLEAN      NOT NULL,
    user_id          BIGINT       NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_details.user (id)
);


CREATE TABLE IF NOT EXISTS meal.meal_ingredient
(
    meal_id       BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    PRIMARY KEY (meal_id, ingredient_id),
    FOREIGN KEY (meal_id)
        REFERENCES meal.meal_entry (id),
    FOREIGN KEY (ingredient_id)
        REFERENCES meal.ingredient (id)
);


CREATE TABLE IF NOT EXISTS meal.user_meal
(
    id             BIGSERIAL NOT NULL PRIMARY KEY,
    user_id        BIGINT    NOT NULL,
    meals_id       BIGINT    NOT NULL,
    favourite_meal BOOLEAN   NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES user_details.user (id),
    FOREIGN KEY (meals_id)
        REFERENCES meal.meal_entry (id)
);