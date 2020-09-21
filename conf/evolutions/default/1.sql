-- Version Schema
-- !Ups
create table version(
    app_version varchar(10) primary key not null,
    created_at timestamptz default current_timestamp
);

-- !Downs
drop table version;