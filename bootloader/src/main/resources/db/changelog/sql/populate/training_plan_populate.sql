-- liquibase formatted sql

-- changeset liquibase:training_plan_populate_1 runOnChange:false



INSERT INTO training_plan.exercise
(
    id, name, description, main_muscles, supportive_muscles, custom, exercise_type, application_file_id, user_id
    ) VALUES (1, 'Pompki', 'Ćwiczenie na klatkę piersiową',
              'PECTORALIS_MAJOR, PECTORALIS_MINOR', 'DELTOID, TRICEPS, TERES_MAJOR, LATISSIMUS_DORSI',
              false, 'WEIGHT_TRAINING', null, null);

INSERT INTO training_plan.exercise
(
    id, name, description, main_muscles, supportive_muscles, custom, exercise_type, application_file_id, user_id
) VALUES (2, 'Rozpiętki na maszynie', 'Ćwiczenie na klatkę piersiową na maszynie',
          'PECTORALIS_MAJOR, PECTORALIS_MINOR, DELTOID', null,
          false, 'WEIGHT_TRAINING', null, null);

INSERT INTO training_plan.exercise
(
    id, name, description, main_muscles, supportive_muscles, custom, exercise_type, application_file_id, user_id
) VALUES (3, 'Rozpiętki z linkami w bramie', 'Ćwiczenie na klatkę piersiową z wykorzystaniem kabli',
          'PECTORALIS_MAJOR, PECTORALIS_MINOR', 'DELTOID, SERRATUS_ANTERIOR, BICEPS',
          true, 'WEIGHT_TRAINING', null, 1);



INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (1, 'CHEST');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (2, 'SHOULDERS');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (3, 'BICEPS');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (4, 'TRICEPS');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (5, 'FOREARMS');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (6, 'UPPER_BACK');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (7, 'LOWER_BACK');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (8, 'ABS');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (9, 'GLUTES');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (10, 'THIGH_FRONT');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (11, 'THIGH_BACK');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (12, 'CALVES');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (13, 'CARDIO');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (14, 'CUSTOM');
INSERT INTO training_plan.exercise_category_group (id, category_name)
VALUES (15, 'FAVOURITE');



INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (1, 1);

INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (1, 2);

INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (2, 1);

INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (2, 2);

INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (3, 1);

INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (3, 2);

INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (3, 14);





INSERT INTO training_plan.exercise_exercise_category_group (exercise_id, exercise_category_group_id)
VALUES (3, 12);




INSERT INTO training_plan.training_plan
(
 id, name, training_days, user_id
) VALUES (1, 'Push', 'MONDAY, FRIDAY, SUNDAY', 1);

INSERT INTO training_plan.training_plan
(
    id, name, training_days, user_id
) VALUES (2, 'Pull', 'TUESDAY, SATURDAY', 1);

INSERT INTO training_plan.training_plan
(
    id, name, training_days, user_id
) VALUES (3, 'Legs', 'WEDNESDAY, SUNDAY', 1);



INSERT INTO training_plan.training_plan_exercise
(
    id, measurement_unit, note, exercise_id, training_plan_id
) VALUES (1, 'KILOGRAM', 'Zwracać uwagę na prawidłową technikę', 1, 1);

INSERT INTO training_plan.training_plan_exercise
(
    id, measurement_unit, note, exercise_id, training_plan_id
) VALUES (2, 'KILOGRAM', 'Wykonywać bardzo powoli', 2, 1);

INSERT INTO training_plan.training_plan_exercise
(
    id, measurement_unit, note, exercise_id, training_plan_id
) VALUES (3, 'KILOGRAM', 'Wykonywać bardzo powoli', 3, 1);

INSERT INTO training_plan.training_plan_exercise
(
    id, measurement_unit, note, exercise_id, training_plan_id
) VALUES (4, 'KILOGRAM', 'Zwracać uwagę na prawidłową technikę', 1, 1);



INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (1, 0, 20, false, 'Pierwsza seria rozgrzewkowa', 1);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (2, 10, 15, false, 'Pierwsza seria właściwa', 1);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (3, 10, 15, false, 'Druga seria właściwa', 1);


INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (4, 30, 12, false, 'Pierwsza seria właściwa', 2);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (5, 30, 12, false, 'Druga seria właściwa', 2);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (6, 35, 12, false, 'Trzecia seria właściwa', 2);


INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (7, 25, 12, false, 'Pierwsza seria właściwa', 3);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (8, 30, 10, false, 'Druga seria właściwa', 3);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (9, 35, 6, false, 'Trzecia seria właściwa', 3);

INSERT INTO training_plan.exercise_set
(
    id, weight, repeats, completed, note, training_plan_exercise_id
) VALUES (10, 0, 20, false, 'Pierwsza seria rozgrzewkowa', 4);


