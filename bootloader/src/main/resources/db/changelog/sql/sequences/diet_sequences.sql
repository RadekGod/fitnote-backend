-- liquibase formatted sql

-- changeset liquibase:diet_sequences runOnChange:false


create sequence if not exists diet.meal_id_seq;
alter sequence diet.meal_id_seq owner to postgres;
alter sequence diet.meal_id_seq owned by diet.meal.id;
-- SELECT setval('body.general_body_data_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('body.general_body_data_id_seq')) - 1 FROM body.general_body_data));
SELECT setval(pg_get_serial_sequence('diet.meal', 'id'), coalesce(max(id), 0) + 1, false) FROM diet.meal;
