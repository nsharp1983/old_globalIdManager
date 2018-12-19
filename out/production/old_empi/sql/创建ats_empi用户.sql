-- Create the user
create user ATS_EMPI
  default tablespace USERS
  temporary tablespace TEMP
  profile DEFAULT
  password expire;
-- Grant/Revoke role privileges
grant aq_user_role to ATS_EMPI with admin option;
grant connect to ATS_EMPI with admin option;
grant dba to ATS_EMPI with admin option;
-- Grant/Revoke system privileges
grant create sequence to ATS_EMPI;
grant create table to ATS_EMPI;
grant select any sequence to ATS_EMPI;
grant select any transaction to ATS_EMPI;
grant unlimited tablespace to ATS_EMPI with admin option;