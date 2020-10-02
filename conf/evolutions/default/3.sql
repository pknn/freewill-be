-- User Model

-- !Ups
create table users(
    id varchar primary key not null default uuid_generate_v4(),
    username varchar(50) not null,
    email varchar(100) not null,
    created_at timestamptz not null default current_timestamp,
    updated_at timestamptz not null default current_timestamp,
    deleted_at timestamptz
);
alter table topics add column user_id varchar(100) references users(id) on delete set null;

-- !Downs
alter table topics drop column user_id;
drop table users;