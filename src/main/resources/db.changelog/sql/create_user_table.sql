--liquibase formatted sql
--changeset EuphoriaV:create_table_user

create table wechat.user
(
    id               bigserial primary key,
    login            varchar unique not null,
    name             varchar        not null,
    encoded_password varchar        not null,
    created_at       timestamp with time zone
);

--rollback drop table wechat.user;