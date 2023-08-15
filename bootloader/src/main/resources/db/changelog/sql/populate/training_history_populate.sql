-- liquibase formatted sql

-- changeset liquibase:training_populate_1 runOnChange:false



INSERT INTO training_history.training
(
 id, name, note, start_time, finish_time, user_id
) VALUES (1, 'Push', 'Trening przebiegł w porządku, następym razem powinienem bardziej rozgrzać ramiona',
          '2023-01-06T10:24', '2023-01-06T12:03', 1);

INSERT INTO training_history.training
(
    id, name, note, start_time, finish_time, user_id
) VALUES (2, 'Pull', 'Trening przebiegł w porządku, następym razem wziąć większe obciążenie',
          '2023-01-07T10:24', '2023-01-07T12:03', 1);





INSERT INTO training_history.training_exercise
(
 id, measure_unit, note, exercise_id, training_id
) VALUES (1, 'KILOGRAM', 'Wykonywać powoli', 1, 1);

INSERT INTO training_history.training_exercise
(
    id, measure_unit, note, exercise_id, training_id
) VALUES (2, 'KILOGRAM', 'Wykonywać powoli', 2, 1);


INSERT INTO training_history.training_exercise
(
    id, measure_unit, note, exercise_id, training_id
) VALUES (3, 'KILOGRAM', 'Wykonywać powoli', 1, 2);

INSERT INTO training_history.training_exercise
(
    id, measure_unit, note, exercise_id, training_id
) VALUES (4, 'KILOGRAM', 'Wykonywać powoli', 2, 2);






INSERT INTO training_history.training_exercise_set
(
 id, weight, repeats, completed, note, training_exercise_id
) VALUES (1, 20, 12, true, 'Wykonywać powoli', 1);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (2, 30, 10, true, 'Wykonywać powoli', 1);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (3, 35, 8, true, 'Wykonywać powoli', 1);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (4, 40, 6, false, 'Wykonywać powoli', 1);


INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (5, 20, 12, true, 'Wykonywać powoli', 2);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (6, 30, 10, true, 'Wykonywać powoli', 2);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (7, 35, 8, true, 'Wykonywać powoli', 2);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (8, 40, 6, false, 'Wykonywać powoli', 2);







INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (9, 20, 12, true, 'Wykonywać powoli', 3);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (10, 30, 10, true, 'Wykonywać powoli', 3);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (11, 35, 8, true, 'Wykonywać powoli', 3);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (12, 40, 6, false, 'Wykonywać powoli', 3);


INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (13, 20, 12, true, 'Wykonywać powoli', 4);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (14, 30, 10, true, 'Wykonywać powoli', 4);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (15, 35, 8, true, 'Wykonywać powoli', 4);

INSERT INTO training_history.training_exercise_set
(
    id, weight, repeats, completed, note, training_exercise_id
) VALUES (16, 40, 6, false, 'Wykonywać powoli', 4);




SELECT setval('training_history.training_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('training_history.training_id_seq')) - 1 FROM training_history.training));
SELECT setval('training_history.training_exercise_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('training_history.training_exercise_id_seq')) - 1 FROM training_history.training_exercise));


-- SELECT MAX(id) FROM training_history.training;
-- SELECT nextval('training_history.training_id_seq');