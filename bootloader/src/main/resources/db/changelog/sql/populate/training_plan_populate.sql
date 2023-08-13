-- liquibase formatted sql

-- changeset liquibase:training_populate_1 runOnChange:false



INSERT INTO training_plan.exercise
(
    id, name, description, main_muscles, supportive_muscles, exercise_category_groups, exercise_type, user_id
    ) VALUES (1, 'Pompki', 'Ćwiczenie na klatkę piersiową',
              'PECTORALIS_MAJOR, PECTORALIS_MINOR', 'DELTOID, TRICEPS, TERES_MAJOR, LATISSIMUS_DORSI',
              'CHEST', 'FREE_WEIGHT', null);

INSERT INTO training_plan.exercise
(
    id, name, description, main_muscles, supportive_muscles, exercise_category_groups, exercise_type, user_id
) VALUES (2, 'Rozpiętki na maszynie', 'Ćwiczenie na klatkę piersiową na maszynie',
          'PECTORALIS_MAJOR, PECTORALIS_MINOR, DELTOID', null,
          'CHEST', 'FREE_WEIGHT', null);

INSERT INTO training_plan.exercise
(
    id, name, description, main_muscles, supportive_muscles, exercise_category_groups, exercise_type, user_id
) VALUES (3, 'Rozpiętki z linkami w bramie', 'Ćwiczenie na klatkę piersiową z wykorzystaniem kabli',
          'PECTORALIS_MAJOR, PECTORALIS_MINOR', 'DELTOID, SERRATUS_ANTERIOR, BICEPS',
          'CHEST, CUSTOM', 'FREE_WEIGHT', 1);




INSERT INTO training_plan.training_plan
(
 id, name, training_days, note, user_id
) VALUES (1, 'Push', 'MONDAY, FRIDAY, SUNDAY','Plan treningowy przeznaczony do wzmocnienia mięśni wypychających', 1);

INSERT INTO training_plan.training_plan
(
    id, name, training_days, note, user_id
) VALUES (2, 'Pull', 'TUESDAY, SATURDAY','Plan treningowy przeznaczony do wzmocnienia mięśni przyciągających', 1);

INSERT INTO training_plan.training_plan
(
    id, name, training_days, note, user_id
) VALUES (3, 'Legs', 'WEDNESDAY, SUNDAY','Plan treningowy przeznaczony do wzmocnienia dolnych partii ciała', 1);



INSERT INTO training_plan.training_plan_exercise
(
    id, measure_unit, note, exercise_id, training_plan_id
) VALUES (1, 'KILOGRAM', 'Zwracać uwagę na prawidłową technikę', 1, 1);

INSERT INTO training_plan.training_plan_exercise
(
    id, measure_unit, note, exercise_id, training_plan_id
) VALUES (2, 'KILOGRAM', 'Wykonywać bardzo powoli', 2, 1);

INSERT INTO training_plan.training_plan_exercise
(
    id, measure_unit, note, exercise_id, training_plan_id
) VALUES (3, 'KILOGRAM', 'Wykonywać bardzo powoli', 3, 1);

INSERT INTO training_plan.training_plan_exercise
(
    id, measure_unit, note, exercise_id, training_plan_id
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


