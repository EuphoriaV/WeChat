--liquibase formatted sql
--changeset EuphoriaV:create_wechat_schema

create schema wechat;

--rollback drop schema wechat;