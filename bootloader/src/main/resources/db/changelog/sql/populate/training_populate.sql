-- liquibase formatted sql

-- changeset liquibase:training_populate_1 runOnChange:false



INSERT INTO training.exercise
(
    id, name, description, main_muscles, supportive_muscles, exercise_category_groups, exercise_type, user_id
    ) VALUES (1, 'Pompki', 'Ćwiczenie na klatkę piersiową',
              'PECTORALIS_MAJOR, PECTORALIS_MINOR', 'DELTOID, TRICEPS, TERES_MAJOR, LATISSIMUS_DORSI',
              'CHEST', 'FREE_WEIGHT', null);

INSERT INTO training.exercise
(
    id, name, description, main_muscles, supportive_muscles, exercise_category_groups, exercise_type, user_id
) VALUES (2, 'Rozpiętki na maszynie', 'Ćwiczenie na klatkę piersiową na maszynie',
          'PECTORALIS_MAJOR, PECTORALIS_MINOR, DELTOID', null,
          'CHEST', 'FREE_WEIGHT', null);

INSERT INTO training.exercise
(
    id, name, description, main_muscles, supportive_muscles, exercise_category_groups, exercise_type, user_id
) VALUES (3, 'Rozpiętki z linkami w bramie', 'Ćwiczenie na klatkę piersiową z wykorzystaniem kabli',
          'PECTORALIS_MAJOR, PECTORALIS_MINOR', 'DELTOID, SERRATUS_ANTERIOR, BICEPS',
          'CHEST, CUSTOM', 'FREE_WEIGHT', 1);
