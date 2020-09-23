-- Topic Schema add created_at

-- !Ups
alter table topics add column created_at timestamptz not null default current_timestamp;

-- !Downs
alter table topics drop column created_at;