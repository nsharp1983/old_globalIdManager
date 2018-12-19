-- Create the user
create user AETRANS
  default tablespace USERS
  temporary tablespace TEMP
  profile DEFAULT
  password expire;
-- Grant/Revoke role privileges
grant aq_user_role to AETRANS with admin option;
grant connect to AETRANS with admin option;
grant dba to AETRANS with admin option;
-- Grant/Revoke system privileges
grant create sequence to AETRANS with admin option;
grant create table to AETRANS with admin option;
grant select any sequence to AETRANS with admin option;
grant select any transaction to AETRANS with admin option;
grant unlimited tablespace to AETRANS with admin option;