-- Version Unique Contraint

-- !Ups
alter table version add constraint version_unique unique (app_version);
insert into version
  (app_version)
values
  ('1.0.0');

-- !Downs
alter table version drop constraint version_unique;
delete from version where version.app_version = '1.0.0';