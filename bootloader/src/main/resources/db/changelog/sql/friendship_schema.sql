-- liquibase formatted sql

-- changeset liquibase:friendship_schema_1 runOnChange:false

CREATE SCHEMA IF NOT EXISTS friendship;

CREATE TABLE IF NOT EXISTS friendship.friendship_entry
(
    id                 BIGSERIAL                NOT NULL PRIMARY KEY,
    requester_id       BIGINT                   NOT NULL,
    receiver_id        BIGINT                   NOT NULL,
    creation_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (requester_id)
        REFERENCES user_management.user (id),
    FOREIGN KEY (receiver_id)
        REFERENCES user_management.user (id)
);


CREATE TABLE IF NOT EXISTS friendship.friendship_status
(
    id                  BIGSERIAL                NOT NULL PRIMARY KEY,
    specifier_id        BIGINT                   NOT NULL,
    status              VARCHAR(45)              NOT NULL,
    specified_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    friendship_id       BIGINT                   NOT NULL,
    FOREIGN KEY (specifier_id)
        REFERENCES user_management.user (id),
    FOREIGN KEY (friendship_id)
        REFERENCES friendship.friendship_entry (id)
);