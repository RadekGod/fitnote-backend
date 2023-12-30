-- liquibase formatted sql

-- changeset liquibase:user_management_sequences runOnChange:false


create sequence if not exists user_management.authority_id_seq;
alter sequence user_management.authority_id_seq owner to postgres;
alter sequence user_management.authority_id_seq owned by user_management.authority.id;
SELECT setval(pg_get_serial_sequence('user_management.authority', 'id'), coalesce(max(id), 0) + 1, false) FROM user_management.authority;

create sequence if not exists user_management.user_id_seq;
alter sequence user_management.user_id_seq owner to postgres;
alter sequence user_management.user_id_seq owned by user_management.user.id;
SELECT setval(pg_get_serial_sequence('user_management.user', 'id'), coalesce(max(id), 0) + 1, false) FROM user_management.user;

create sequence if not exists user_management.user_settings_id_seq;
alter sequence user_management.user_settings_id_seq owner to postgres;
alter sequence user_management.user_settings_id_seq owned by user_management.user_settings.id;
SELECT setval(pg_get_serial_sequence('user_management.user_settings', 'id'), coalesce(max(id), 0) + 1, false) FROM user_management.user_settings;
