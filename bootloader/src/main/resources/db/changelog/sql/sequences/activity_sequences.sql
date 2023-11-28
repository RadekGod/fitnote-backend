-- liquibase formatted sql

-- changeset liquibase:body_measurement_sequences runOnChange:false


create sequence if not exists activity.activity_id_seq;
alter sequence activity.activity_id_seq owner to postgres;
alter sequence activity.activity_id_seq owned by activity.activity_type.id;
-- SELECT setval('body.body_measurement_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('body.body_measurement_id_seq')) - 1 FROM body.body_measurement));
SELECT setval(pg_get_serial_sequence('activity.activity_type', 'id'), coalesce(max(id), 0) + 1, false) FROM activity.activity_type;

create sequence if not exists activity.activity_id_seq;
alter sequence activity.activity_id_seq owner to postgres;
alter sequence activity.activity_id_seq owned by activity.activity.id;
-- SELECT setval('body.general_body_data_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('body.general_body_data_id_seq')) - 1 FROM body.general_body_data));
SELECT setval(pg_get_serial_sequence('activity.activity', 'id'), coalesce(max(id), 0) + 1, false) FROM activity.activity;
