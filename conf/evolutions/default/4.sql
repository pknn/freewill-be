-- Authentication

-- !Ups
create table tokens (
    id varchar(255) primary key not null default uuid_generate_v4(),
    user_id varchar(255) unique not null references users(id) on delete cascade,
    access_token varchar(255) not null,
    refresh_token varchar(255) not null
);

-- !Downs
drop table tokens;