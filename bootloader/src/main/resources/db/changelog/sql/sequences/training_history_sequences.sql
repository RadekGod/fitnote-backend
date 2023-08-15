-- liquibase formatted sql

-- changeset liquibase:training_history_sequences runOnChange:false


create sequence if not exists training_history.training_exercise_id_seq;
alter sequence training_history.training_exercise_id_seq owner to postgres;
alter sequence training_history.training_exercise_id_seq owned by training_history.training_exercise.id;
-- SELECT setval('training_history.training_exercise_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('training_history.training_exercise_id_seq')) - 1 FROM training_history.training_exercise));
SELECT setval(pg_get_serial_sequence('training_history.training_exercise', 'id'), coalesce(max(id), 0) + 1, false) FROM training_history.training_exercise;


create sequence if not exists training_history.training_id_seq start 3 increment 1;
alter sequence training_history.training_id_seq owner to postgres;
alter sequence training_history.training_id_seq owned by training_history.training.id;
-- SELECT setval('training_history.training_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('training_history.training_id_seq')) - 1 FROM training_history.training));
SELECT setval(pg_get_serial_sequence('training_history.training', 'id'), coalesce(max(id), 0) + 1, false) FROM training_history.training;


create sequence if not exists training_history.training_exercise_set_id_seq;
alter sequence training_history.training_exercise_set_id_seq owner to postgres;
alter sequence training_history.training_exercise_set_id_seq owned by training_history.training_exercise_set.id;
-- SELECT setval('training_history.training_exercise_set_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('training_history.training_exercise_set_id_seq')) - 1 FROM training_history.training_exercise_set));
SELECT setval(pg_get_serial_sequence('training_history.training_exercise_set', 'id'), coalesce(max(id), 0) + 1, false) FROM training_history.training_exercise_set;


