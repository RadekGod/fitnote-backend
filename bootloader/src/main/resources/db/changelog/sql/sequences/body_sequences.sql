-- liquibase formatted sql

-- changeset liquibase:body_measurement_sequences runOnChange:false


create sequence if not exists body.body_measurement_id_seq;
alter sequence body.body_measurement_id_seq owner to postgres;
alter sequence body.body_measurement_id_seq owned by body.body_measurement.id;
-- SELECT setval('body.body_measurement_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('body.body_measurement_id_seq')) - 1 FROM body.body_measurement));
SELECT setval(pg_get_serial_sequence('body.body_measurement', 'id'), coalesce(max(id), 0) + 1, false) FROM body.body_measurement;

create sequence if not exists body.general_measurement_id_seq;
alter sequence body.general_measurement_id_seq owner to postgres;
alter sequence body.general_measurement_id_seq owned by body.general_measurement.id;
-- SELECT setval('body.general_body_data_id_seq',(SELECT GREATEST(MAX(id)+1,nextval('body.general_body_data_id_seq')) - 1 FROM body.general_body_data));
SELECT setval(pg_get_serial_sequence('body.general_measurement', 'id'), coalesce(max(id), 0) + 1, false) FROM body.general_measurement;
