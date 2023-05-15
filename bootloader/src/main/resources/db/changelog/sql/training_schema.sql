-- liquibase formatted sql

-- changeset liquibase:training_schema_1 runOnChange:false


CREATE SCHEMA IF NOT EXISTS training;

CREATE TABLE IF NOT EXISTS training.training_plan
(
    id
    BIGSERIAL
    NOT
    NULL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NULL,
    type VARCHAR
(
    255
) NOT NULL,
    difficulty VARCHAR
(
    255
) NULL,
    created_by_user BOOLEAN NOT NULL,
    creation_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
                                     description VARCHAR (1000) NULL
    );


CREATE TABLE IF NOT EXISTS training.exercise
(
    id
    BIGSERIAL
    NOT
    NULL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    description VARCHAR
(
    1000
) NULL,
    involved_muscles VARCHAR
(
    255
) NULL,
    exercise_type VARCHAR
(
    255
) NULL
    );

CREATE TABLE IF NOT EXISTS training.user_entry_training_plan
(
    user_id
    BIGINT
    NOT
    NULL,
    training_plan_id
    BIGINT
    NOT
    NULL,
    FOREIGN
    KEY
(
    user_id
)
    REFERENCES user_details.user_entry
(
    id
),
    FOREIGN KEY
(
    training_plan_id
)
    REFERENCES training.training_plan
(
    id
)
    );


CREATE TABLE IF NOT EXISTS training.training_plan_exercise
(
    id
    BIGSERIAL
    NOT
    NULL
    PRIMARY
    KEY,
    training_plan_id
    BIGINT
    NOT
    NULL,
    exercise_id
    BIGINT
    NOT
    NULL,
    exercise_sequence_number
    INT
    NOT
    NULL,
    note
    VARCHAR
(
    1000
) NULL,
    FOREIGN KEY
(
    training_plan_id
)
    REFERENCES training.training_plan
(
    id
),
    FOREIGN KEY
(
    exercise_id
)
    REFERENCES training.exercise
(
    id
)
    );


CREATE TABLE IF NOT EXISTS training.training_entry
(
    id
    BIGSERIAL
    NOT
    NULL
    PRIMARY
    KEY,
    start_date_time
    TIMESTAMP
    WITH
    TIME
    ZONE
    NOT
    NULL,
    finish_date_time
    TIMESTAMP
    WITH
    TIME
    ZONE
    NULL,
    user_id
    BIGINT
    NOT
    NULL,
    training_plan_id
    BIGINT
    NOT
    NULL,
    FOREIGN
    KEY
(
    user_id
)
    REFERENCES user_details.user_entry
(
    id
),
    FOREIGN KEY
(
    training_plan_id
)
    REFERENCES training.training_plan
(
    id
)
    );


CREATE TABLE IF NOT EXISTS training.exercise_set
(
    id
    BIGSERIAL
    NOT
    NULL
    PRIMARY
    KEY,
    repeats
    INT
    NULL,
    weight
    INT
    NULL,
    weight_unit
    VARCHAR
(
    30
) NULL,
    set_completed BOOLEAN NULL,
    training_plan_exercise_id BIGINT NOT NULL,
    training_id BIGINT NULL,
    FOREIGN KEY
(
    training_plan_exercise_id
)
    REFERENCES training.training_plan_exercise
(
    id
),
    FOREIGN KEY
(
    training_id
)
    REFERENCES training.training_entry
(
    id
)
    );



