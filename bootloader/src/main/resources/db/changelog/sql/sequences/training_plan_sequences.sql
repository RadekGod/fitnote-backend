-- liquibase formatted sql

-- changeset liquibase:training_plan_sequences runOnChange:false

create sequence if not exists training_plan.exercise_id_seq;
alter sequence training_plan.exercise_id_seq owner to postgres;
alter sequence training_plan.exercise_id_seq owned by training_plan.exercise.id;
SELECT setval(pg_get_serial_sequence('training_plan.exercise', 'id'), coalesce(max(id), 0) + 1, false) FROM training_plan.exercise;


create sequence if not exists training_plan.training_plan_id_seq;
alter sequence training_plan.training_plan_id_seq owner to postgres;
alter sequence training_plan.training_plan_id_seq owned by training_plan.training_plan.id;
SELECT setval(pg_get_serial_sequence('training_plan.training_plan', 'id'), coalesce(max(id), 0) + 1, false) FROM training_plan.training_plan;


create sequence if not exists training_plan.training_plan_exercise_id_seq;
alter sequence training_plan.training_plan_exercise_id_seq owner to postgres;
alter sequence training_plan.training_plan_exercise_id_seq owned by training_plan.training_plan_exercise.id;
SELECT setval(pg_get_serial_sequence('training_plan.training_plan_exercise', 'id'), coalesce(max(id), 0) + 1, false) FROM training_plan.training_plan_exercise;


create sequence if not exists training_plan.exercise_set_id_seq;
alter sequence training_plan.exercise_set_id_seq owner to postgres;
alter sequence training_plan.exercise_set_id_seq owned by training_plan.exercise_set.id;
SELECT setval(pg_get_serial_sequence('training_plan.exercise_set', 'id'), coalesce(max(id), 0) + 1, false) FROM training_plan.exercise_set;