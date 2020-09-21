-- Version Schema
-- !Ups
create table version
(
    id serial primary key not null,
    app_version varchar(10) not null,
    created_at timestamptz default current_timestamp
);

-- !Downs
drop table version;