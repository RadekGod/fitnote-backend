-- liquibase formatted sql

-- changeset liquibase:activity_populate_1 runOnChange:false

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (1, 'RUNNING', 220, true, false, null);

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (2, 'SWIMMING', 300, true, false, null);

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (3, 'GYM_WEIGHT_TRAINING', 440, false, false, null);

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (4, 'GYM_CARDIO_TIME', 440, false, false, null);

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (5, 'GYM_CARDIO_DISTANCE', 440, true, false, null);

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (6, 'Jogging', 440, true, true, 1);

INSERT INTO activity.activity_type
(id, name, average_calories_burnt_per_hour, distance_activity, custom_activity, user_id)
VALUES (7, 'TRAINING_PLAN', 220, false, false, null);


INSERT INTO activity.activity
(id, activity_duration_in_minutes, burnt_kilocalories, distance_traveled, activity_date, activity_type_id, user_id)
VALUES (1, 30, 110, 2130, '2023-10-06T12:03', 1, 1);

INSERT INTO activity.activity
(id, activity_duration_in_minutes, burnt_kilocalories, distance_traveled, activity_date, activity_type_id, user_id)
VALUES (2, 45, 97, 2130, '2023-10-06T12:03', 2, 1);

INSERT INTO activity.activity
(id, activity_duration_in_minutes, burnt_kilocalories, distance_traveled, activity_date, activity_type_id, user_id)
VALUES (3, 56, 750, null, '2023-10-06T12:03', 3, 1);

INSERT INTO activity.activity
(id, activity_duration_in_minutes, burnt_kilocalories, distance_traveled, activity_date, activity_type_id, user_id)
VALUES (6, 21, 340, 2130, '2023-10-06T12:03', 4, 1);
