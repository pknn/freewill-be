-- Version Unique Contraint

-- !Ups
alter table version add constraint version_unique unique (app_version);

-- !Downs
alter table version drop constraint version_unique;