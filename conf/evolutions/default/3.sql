-- Topic Schema

-- !Ups
create table topics
(
  id varchar(50) primary key default uuid_generate_v4(),
  title varchar(100) not null,
  description text,
  score integer check (score >= 0 and score <= 10) not null default 0,
  created_at timestamptz not null default current_timestamp
)

-- !Downs
drop table topics;