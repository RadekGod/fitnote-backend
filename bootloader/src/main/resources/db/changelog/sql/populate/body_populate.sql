-- liquibase formatted sql

-- changeset liquibase:body_populate_1 runOnChange:false

INSERT INTO body.body_measurement
(id, chest, biceps_left, biceps_right, forearm_left, forearm_right, waist, hip,
 thigh_left, thigh_right, calf_left, calf_right, length_unit, measurement_date, user_id)
VALUES (1, 99, 35, 34.8, 33.2, 34.8, 90, null, 33.2, 34.8, 33.2, 34.8, 'CENTIMETER', '2023-01-06T12:03', 1);

INSERT INTO body.body_measurement
(id, chest, biceps_left, biceps_right, forearm_left, forearm_right, waist, hip,
 thigh_left, thigh_right, calf_left, calf_right, length_unit, measurement_date, user_id)
VALUES (2, 99.3, 34.9, 34.6, 33, 34.5, 92, 94, 33.1, 34.5, 33, 34.5, 'CENTIMETER', '2023-01-04T12:03', 1);



INSERT INTO body.general_measurement
(id, weight, height, bmi, muscle_content, body_fat, length_unit, weight_unit, measurement_date, user_id)
VALUES (1, 74.3, 185, 21.3, 40, 19, 'CENTIMETER', 'KILOGRAM', '2023-01-06T12:03', 1);

INSERT INTO body.general_measurement
(id, weight, height, bmi, muscle_content, body_fat, length_unit, weight_unit, measurement_date, user_id)
VALUES (2, 74, 185, 21.0, 39, 21, 'CENTIMETER', 'KILOGRAM', '2023-01-06T12:01', 1);