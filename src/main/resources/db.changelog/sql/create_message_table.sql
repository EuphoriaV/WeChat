--liquibase formatted sql
--changeset EuphoriaV:create_table_message

create table wechat.message
(
    id           bigserial primary key,
    text         varchar not null,
    user_id_from bigint  not null references wechat.user (id),
    user_id_to   bigint  not null references wechat.user (id),
    created_at   timestamp with time zone
);

--rollback drop table wechat.message;