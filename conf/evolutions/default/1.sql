-- Version Schema
-- !Ups

create table version
(
    id serial primary key not null,
    app_version varchar(10) unique not null,
    created_at timestamptz default current_timestamp
);
insert into version
    (app_version)
values
    ('1.0.0');
-- !Downs
drop table version;