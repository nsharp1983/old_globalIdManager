create table ALLERGY
(
  allergy_id    NUMBER not null,
  person_id     NUMBER not null,
  allergy_cd    VARCHAR2(64),
  allergy_text  VARCHAR2(255),
  allergy_code  VARCHAR2(16),
  allergy_type  VARCHAR2(64),
  allergy_class VARCHAR2(64),
  allergy_date  TIMESTAMP(6),
  create_date   TIMESTAMP(6),
  create_name   VARCHAR2(256),
  custom1       VARCHAR2(255),
  custom2       VARCHAR2(255),
  custom3       VARCHAR2(255),
  custom4       VARCHAR2(255),
  custom5       VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ALLERGY
  is '过敏信息表';
comment on column ALLERGY.allergy_id
  is '主键';
comment on column ALLERGY.person_id
  is 'person表主键';
comment on column ALLERGY.allergy_cd
  is '过敏症CD';
comment on column ALLERGY.allergy_text
  is '过敏症内容';
comment on column ALLERGY.allergy_code
  is '过敏症CODE';
comment on column ALLERGY.allergy_type
  is '过敏症类型';
comment on column ALLERGY.allergy_class
  is '过敏症等级';
comment on column ALLERGY.allergy_date
  is '过敏症日期';
comment on column ALLERGY.create_date
  is '创建日期';
comment on column ALLERGY.create_name
  is '创建名字';
comment on column ALLERGY.custom1
  is '自定义1';
comment on column ALLERGY.custom2
  is '自定义2';
comment on column ALLERGY.custom3
  is '自定义3';
comment on column ALLERGY.custom4
  is '自定义4';
comment on column ALLERGY.custom5
  is '自定义5';

  alter table ALLERGY
  add constraint ALLERGY_PK primary key (ALLERGY_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );




create table APP_USER
(
  id                  NUMBER not null,
  username            VARCHAR2(50) not null,
  email               VARCHAR2(255) not null,
  phone_number        VARCHAR2(255),
  password_hint       VARCHAR2(255),
  first_name          VARCHAR2(50) not null,
  last_name           VARCHAR2(50) not null,
  website             VARCHAR2(255),
  account_expired     NUMBER(1) not null,
  account_locked      NUMBER(1) not null,
  credentials_expired NUMBER(1) not null,
  city                VARCHAR2(50) not null,
  province            VARCHAR2(100),
  postal_code         VARCHAR2(15) not null,
  address             VARCHAR2(150),
  country             VARCHAR2(100),
  account_enabled     NUMBER(1),
  version             NUMBER,
  password            VARCHAR2(255) not null,
  custom1             VARCHAR2(255),
  custom2             VARCHAR2(255),
  custom3             VARCHAR2(255),
  custom4             VARCHAR2(255),
  custom5             VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table APP_USER
  is '用户信息';
comment on column APP_USER.id
  is '主键';
comment on column APP_USER.username
  is '用户名';
comment on column APP_USER.email
  is '邮箱';
comment on column APP_USER.phone_number
  is '电话号码';
comment on column APP_USER.password_hint
  is '密码提示';
comment on column APP_USER.first_name
  is '姓';
comment on column APP_USER.last_name
  is '名';
comment on column APP_USER.website
  is '网站';
comment on column APP_USER.account_expired
  is '帐户过期';
comment on column APP_USER.account_locked
  is '帐户被锁';
comment on column APP_USER.credentials_expired
  is '证书过期';
comment on column APP_USER.city
  is '城市';
comment on column APP_USER.province
  is '省';
comment on column APP_USER.postal_code
  is '邮政编码';
comment on column APP_USER.address
  is '地址';
comment on column APP_USER.country
  is '国家';
comment on column APP_USER.account_enabled
  is '账户未启用';
comment on column APP_USER.version
  is '版本号';
comment on column APP_USER.password
  is '密码';
comment on column APP_USER.custom1
  is '自定义1';
comment on column APP_USER.custom2
  is '自定义2';
comment on column APP_USER.custom3
  is '自定义3';
comment on column APP_USER.custom4
  is '自定义4';
comment on column APP_USER.custom5
  is '自定义5';
alter table APP_USER
  add constraint APP_USER_PKEY primary key (ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP_USER
  add constraint APP_USER_EMAIL_KEY unique (EMAIL)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table APP_USER
  add constraint APP_USER_USERNAME_KEY unique (USERNAME)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table AUDIT_EVENT
(
  audit_event_id           NUMBER not null,
  date_created             TIMESTAMP(6) not null,
  audit_event_type_cd      NUMBER not null,
  audit_event_description  VARCHAR2(255),
  ref_person_id            NUMBER,
  alt_ref_person_id        NUMBER,
  patient_id               VARCHAR2(64),
  patient_domain           VARCHAR2(64),
  person_id                VARCHAR2(64),
  visit_flow_id            VARCHAR2(64),
  visit_flow_domain        VARCHAR2(64),
  empi                     VARCHAR2(64),
  hospital_domain          VARCHAR2(64),
  before_the_change_person VARCHAR2(4000),
  after_the_change_person  VARCHAR2(4000),
  before_the_change_visit  VARCHAR2(4000),
  after_the_change_visit   VARCHAR2(4000),
  before_the_change_order  VARCHAR2(4000),
  after_the_change_order   VARCHAR2(4000),
  temp_the_change          VARCHAR2(4000),
  change_description       VARCHAR2(4000),
  creator_id               NUMBER not null,
  custom1                  VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 4096M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table AUDIT_EVENT
  is '事件记录';
comment on column AUDIT_EVENT.audit_event_id
  is '主键';
comment on column AUDIT_EVENT.date_created
  is '创建日期';
comment on column AUDIT_EVENT.audit_event_type_cd
  is '审计事件类型CD';
comment on column AUDIT_EVENT.audit_event_description
  is '审计事件描述';
comment on column AUDIT_EVENT.ref_person_id
  is 'The field refers to a person record that is associated in some way with the audit event. For example in the case of a person record update audit event this field will refer to the person record that was updated.';
comment on column AUDIT_EVENT.alt_ref_person_id
  is 'The audit event may refer to a second person that is associated with the event in some way. For example a link audit event would refer to the second person record that was linked.';
comment on column AUDIT_EVENT.patient_id
  is '患者ID';
comment on column AUDIT_EVENT.patient_domain
  is '患者域';
comment on column AUDIT_EVENT.person_id
  is 'person_id';
comment on column AUDIT_EVENT.visit_flow_id
  is '流水ID';
comment on column AUDIT_EVENT.visit_flow_domain
  is '流水域ID';
comment on column AUDIT_EVENT.empi
  is 'EMPI';
comment on column AUDIT_EVENT.hospital_domain
  is '医院ID';
comment on column AUDIT_EVENT.before_the_change_person
  is '变更前的患者信息';
comment on column AUDIT_EVENT.after_the_change_person
  is '变更后的患者信息';
comment on column AUDIT_EVENT.before_the_change_visit
  is '变更前的患者就诊信息';
comment on column AUDIT_EVENT.after_the_change_visit
  is '变更后的患者就诊信息';
comment on column AUDIT_EVENT.before_the_change_order
  is '变更前的订单信息';
comment on column AUDIT_EVENT.after_the_change_order
  is '变更后的订单信息';
comment on column AUDIT_EVENT.temp_the_change
  is '临时的变化';
comment on column AUDIT_EVENT.change_description
  is '变更描述';
comment on column AUDIT_EVENT.creator_id
  is '创建者ID';
comment on column AUDIT_EVENT.custom1
  is '自定义1';
create index ALT_PESON_IDX on AUDIT_EVENT (ALT_REF_PERSON_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index AUDIT_PAT_DO_CUS on AUDIT_EVENT (PATIENT_ID, PATIENT_DOMAIN, CUSTOM1)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index REF_IDX on AUDIT_EVENT (REF_PERSON_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 736M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AUDIT_EVENT
  add constraint AUDIT_EVENT_PK primary key (AUDIT_EVENT_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 696M
    next 1M
    minextents 1
    maxextents unlimited
  );



create table CONTACTPERSON
(
  contact_id      NUMBER not null,
  person_id       NUMBER not null,
  contact_name    VARCHAR2(64),
  contact_type    VARCHAR2(64),
  contact_address VARCHAR2(255),
  contact_phone   VARCHAR2(255),
  contact_mobile  VARCHAR2(255),
  custom1         VARCHAR2(255),
  custom2         VARCHAR2(255),
  custom3         VARCHAR2(255),
  custom4         VARCHAR2(255),
  custom5         VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 40M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table CONTACTPERSON
  is '联系人表';
comment on column CONTACTPERSON.contact_id
  is '主键';
comment on column CONTACTPERSON.person_id
  is 'person表主键ID';
comment on column CONTACTPERSON.contact_name
  is '联系人姓名';
comment on column CONTACTPERSON.contact_type
  is '联系人关系';
comment on column CONTACTPERSON.contact_address
  is '联系人地址';
comment on column CONTACTPERSON.contact_phone
  is '联系人电话';
comment on column CONTACTPERSON.contact_mobile
  is '联系人手机';
comment on column CONTACTPERSON.custom1
  is '自定义1';
comment on column CONTACTPERSON.custom2
  is '自定义2';
comment on column CONTACTPERSON.custom3
  is '自定义3';
comment on column CONTACTPERSON.custom4
  is '自定义4';
comment on column CONTACTPERSON.custom5
  is '自定义5';
alter table CONTACTPERSON
  add constraint CONTACT_PK primary key (CONTACT_ID, PERSON_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 26M
    next 1M
    minextents 1
    maxextents unlimited
  );



create table DIAGNOSE
(
  diagnose_id          NUMBER not null,
  person_id            NUMBER not null,
  diagnose_icd         VARCHAR2(64),
  diagnose_text        VARCHAR2(255),
  diagnose_doctor_id   VARCHAR2(16),
  diagnose_doctor_name VARCHAR2(64),
  diagnose_type        VARCHAR2(64),
  diagnose_class       VARCHAR2(64),
  diagnose_date        TIMESTAMP(6),
  create_date          TIMESTAMP(6),
  create_name          VARCHAR2(256),
  custom1              VARCHAR2(255),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table DIAGNOSE
  is '病人诊断信息表';
comment on column DIAGNOSE.diagnose_id
  is '主键';
comment on column DIAGNOSE.person_id
  is 'person表主键ID';
comment on column DIAGNOSE.diagnose_icd
  is '诊断ICD';
comment on column DIAGNOSE.diagnose_text
  is '诊断内容';
comment on column DIAGNOSE.diagnose_doctor_id
  is '诊断医生ID';
comment on column DIAGNOSE.diagnose_doctor_name
  is '诊断医生姓名';
comment on column DIAGNOSE.diagnose_type
  is '诊断类型';
comment on column DIAGNOSE.diagnose_class
  is '诊断级别';
comment on column DIAGNOSE.diagnose_date
  is '诊断日期';
comment on column DIAGNOSE.create_date
  is '创建日期';
comment on column DIAGNOSE.create_name
  is '创建者名字';
comment on column DIAGNOSE.custom1
  is '自定义1';
comment on column DIAGNOSE.custom2
  is '自定义2';
comment on column DIAGNOSE.custom3
  is '自定义3';
comment on column DIAGNOSE.custom4
  is '自定义4';
comment on column DIAGNOSE.custom5
  is '自定义5';
alter table DIAGNOSE
  add constraint DIAGNOSE_PK primary key (DIAGNOSE_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table EMPI
(
  empi                         VARCHAR2(64) not null,
  name                         VARCHAR2(64),
  name_code                    VARCHAR2(64),
  name_spell_code              VARCHAR2(64),
  name_wb_code                 VARCHAR2(64),
  date_of_birth                DATE,
  birth_province               VARCHAR2(128),
  birth_city                   VARCHAR2(64),
  birth_county                 VARCHAR2(64),
  birth_zip                    VARCHAR2(32),
  birth_place                  VARCHAR2(255),
  multiple_birth_ind           VARCHAR2(1),
  birth_order                  VARCHAR2(64),
  mothers_maiden_name          VARCHAR2(64),
  ssn                          VARCHAR2(64),
  identity_no                  VARCHAR2(64),
  health_card                  VARCHAR2(64),
  citizen_card                 VARCHAR2(64),
  medical_certificate          VARCHAR2(64),
  meicare_person               VARCHAR2(64),
  elder_certificate            VARCHAR2(64),
  opcaseno                     VARCHAR2(64),
  insurance_no                 VARCHAR2(64),
  insurance_type               VARCHAR2(64),
  insurance_name               VARCHAR2(64),
  gender_cd                    VARCHAR2(64),
  gender_name                  VARCHAR2(64),
  gender_domain                VARCHAR2(64),
  ethnic_group_cd              VARCHAR2(64),
  ethnic_name                  VARCHAR2(64),
  ethnic_domain                VARCHAR2(64),
  race_cd                      VARCHAR2(64),
  race_name                    VARCHAR2(64),
  race_domain                  VARCHAR2(64),
  nationality_cd               VARCHAR2(64),
  nationality_name             VARCHAR2(64),
  nationality_domain           VARCHAR2(64),
  language_cd                  VARCHAR2(64),
  religion_cd                  VARCHAR2(64),
  marital_status_cd            VARCHAR2(64),
  marital_status_name          VARCHAR2(64),
  marital_domain               VARCHAR2(64),
  degree                       VARCHAR2(64),
  degree_name                  VARCHAR2(64),
  degree_domain                VARCHAR2(64),
  email                        VARCHAR2(255),
  home_province                VARCHAR2(64),
  home_city                    VARCHAR2(64),
  home_county                  VARCHAR2(64),
  home_zip                     VARCHAR2(64),
  home_address                 VARCHAR2(512),
  registered_province          VARCHAR2(64),
  registered_city              VARCHAR2(64),
  registered_county            VARCHAR2(64),
  registered_zip               VARCHAR2(32),
  registered_address           VARCHAR2(512),
  native_province              VARCHAR2(64),
  native_city                  VARCHAR2(64),
  work_zip                     VARCHAR2(64),
  work_address                 VARCHAR2(512),
  address1                     VARCHAR2(64),
  postal_code                  VARCHAR2(64),
  address_type_cd              VARCHAR2(64),
  address2                     VARCHAR2(64),
  postal_code1                 VARCHAR2(64),
  address1_type_cd             VARCHAR2(64),
  city                         VARCHAR2(64),
  state                        VARCHAR2(64),
  country                      VARCHAR2(64),
  country_code                 VARCHAR2(64),
  phone_country_code           VARCHAR2(64),
  phone_area_code              VARCHAR2(64),
  phone_number                 VARCHAR2(64),
  phone_ext                    VARCHAR2(64),
  phone_type_cd                VARCHAR2(64),
  phone_country_code1          VARCHAR2(64),
  phone_area_code1             VARCHAR2(64),
  phone_number1                VARCHAR2(64),
  phone_ext1                   VARCHAR2(64),
  phone_type_cd1               VARCHAR2(64),
  company                      VARCHAR2(64),
  companycontacts              VARCHAR2(64),
  birthplace_cd                VARCHAR2(64),
  workstatus                   VARCHAR2(64),
  profession                   VARCHAR2(64),
  profession_name              VARCHAR2(64),
  profession_domain            VARCHAR2(64),
  private_number               VARCHAR2(64),
  home_number                  VARCHAR2(64),
  work_number                  VARCHAR2(64),
  guardian_person              VARCHAR2(64),
  vip                          VARCHAR2(64),
  account_identifier_domain_id VARCHAR2(64),
  account                      VARCHAR2(255),
  death_ind                    VARCHAR2(1),
  death_time                   TIMESTAMP(6),
  date_changed                 TIMESTAMP(6),
  changed_by_id                VARCHAR2(64),
  date_voided                  TIMESTAMP(6),
  voided_by_id                 VARCHAR2(64),
  blood_type_cd                VARCHAR2(64),
  rh_type                      VARCHAR2(64),
  hospital_cd                  VARCHAR2(64),
  custom1                      VARCHAR2(255),
  custom2                      VARCHAR2(255),
  custom3                      VARCHAR2(255),
  custom4                      VARCHAR2(255),
  custom5                      VARCHAR2(255),
  custom6                      VARCHAR2(255),
  custom7                      VARCHAR2(255),
  custom8                      VARCHAR2(255),
  custom9                      VARCHAR2(255),
  custom10                     VARCHAR2(255),
  card_type                    VARCHAR2(16),
  account_locked               VARCHAR2(64),
  account_locked_date          TIMESTAMP(6),
  birth_time                   TIMESTAMP(6)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 1129M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table EMPI
  is '病人EMPI信息索引表';
comment on column EMPI.empi
  is 'EMPI';
comment on column EMPI.name
  is '姓名';
comment on column EMPI.name_code
  is '名字代码';
comment on column EMPI.name_spell_code
  is '中文姓名拼音码';
comment on column EMPI.name_wb_code
  is '中文姓名五笔码';
comment on column EMPI.date_of_birth
  is '生日';
comment on column EMPI.birth_province
  is '出生地所在地的省';
comment on column EMPI.birth_city
  is '出生地所在地的市';
comment on column EMPI.birth_county
  is '出生地所在区县';
comment on column EMPI.birth_zip
  is '出生地所在地邮编';
comment on column EMPI.birth_place
  is '出生地';
comment on column EMPI.multiple_birth_ind
  is '多胞胎标志';
comment on column EMPI.birth_order
  is '出生顺序';
comment on column EMPI.mothers_maiden_name
  is '母亲娘家姓';
comment on column EMPI.ssn
  is '社会保险号';
comment on column EMPI.identity_no
  is '身份证号';
comment on column EMPI.health_card
  is '健康卡号';
comment on column EMPI.citizen_card
  is '市民卡号';
comment on column EMPI.medical_certificate
  is '医疗证号';
comment on column EMPI.meicare_person
  is '医保个人编号';
comment on column EMPI.elder_certificate
  is '老人证号';
comment on column EMPI.opcaseno
  is '病历号';
comment on column EMPI.insurance_no
  is '医保卡号';
comment on column EMPI.insurance_type
  is '医保卡号类型';
comment on column EMPI.insurance_name
  is '医保卡号姓名';
comment on column EMPI.gender_cd
  is '性别信息';
comment on column EMPI.gender_name
  is '性别名称';
comment on column EMPI.gender_domain
  is '性别编码系统';
comment on column EMPI.ethnic_group_cd
  is '民族信息';
comment on column EMPI.ethnic_name
  is '民族名称';
comment on column EMPI.ethnic_domain
  is '民族编码系统';
comment on column EMPI.race_cd
  is '种族信息';
comment on column EMPI.race_name
  is '种族民称';
comment on column EMPI.race_domain
  is '种族编码系统';
comment on column EMPI.nationality_cd
  is '国籍信息';
comment on column EMPI.nationality_name
  is '国籍名';
comment on column EMPI.nationality_domain
  is '国籍编码系统';
comment on column EMPI.language_cd
  is '语言信息';
comment on column EMPI.religion_cd
  is '宗教信息';
comment on column EMPI.marital_status_cd
  is '婚姻状态信息';
comment on column EMPI.marital_status_name
  is '婚姻名称';
comment on column EMPI.marital_domain
  is '婚姻编码系统';
comment on column EMPI.degree
  is '教育程度编码';
comment on column EMPI.degree_name
  is '教育程度名称';
comment on column EMPI.degree_domain
  is '教育编码系统';
comment on column EMPI.email
  is '邮件地址';
comment on column EMPI.home_province
  is '居住地所在地省';
comment on column EMPI.home_city
  is '居住地所在市';
comment on column EMPI.home_county
  is '居住地所在地区县';
comment on column EMPI.home_zip
  is '居住地所在地邮编';
comment on column EMPI.home_address
  is '居住地址';
comment on column EMPI.registered_province
  is '户口所在地省';
comment on column EMPI.registered_city
  is '户口所在地市';
comment on column EMPI.registered_county
  is '户口所在地区县';
comment on column EMPI.registered_zip
  is '户口所在地邮编';
comment on column EMPI.registered_address
  is '户口地址';
comment on column EMPI.native_province
  is '籍贯所在地省';
comment on column EMPI.native_city
  is '籍贯所在地市';
comment on column EMPI.work_zip
  is '工作邮编';
comment on column EMPI.work_address
  is '单位地址';
comment on column EMPI.address1
  is '地址1';
comment on column EMPI.postal_code
  is '邮政编码1';
comment on column EMPI.address_type_cd
  is '地址类型编码1';
comment on column EMPI.address2
  is '地址2';
comment on column EMPI.postal_code1
  is '邮政编码2';
comment on column EMPI.address1_type_cd
  is '地址类型编码2';
comment on column EMPI.city
  is '城市';
comment on column EMPI.state
  is '省';
comment on column EMPI.country
  is '国家';
comment on column EMPI.country_code
  is '国家编码';
comment on column EMPI.phone_country_code
  is '电话号码国家代码';
comment on column EMPI.phone_area_code
  is '国家号码';
comment on column EMPI.phone_number
  is '电话号码';
comment on column EMPI.phone_ext
  is '分机号';
comment on column EMPI.phone_type_cd
  is '电话类型编码';
comment on column EMPI.phone_country_code1
  is '电话号码国家代码1';
comment on column EMPI.phone_area_code1
  is '国家号码1';
comment on column EMPI.phone_number1
  is '电话号码1';
comment on column EMPI.phone_ext1
  is '分机号1';
comment on column EMPI.phone_type_cd1
  is '电话类型编码1';
comment on column EMPI.company
  is '公司';
comment on column EMPI.companycontacts
  is '公司联系方式';
comment on column EMPI.birthplace_cd
  is '出生地编码';
comment on column EMPI.workstatus
  is '工作状态';
comment on column EMPI.profession
  is '职业编码';
comment on column EMPI.profession_name
  is '职业编码名称';
comment on column EMPI.profession_domain
  is '职业编码系统';
comment on column EMPI.private_number
  is '私人电话';
comment on column EMPI.home_number
  is '家庭电话';
comment on column EMPI.work_number
  is '工作电话';
comment on column EMPI.guardian_person
  is '监护人';
comment on column EMPI.vip
  is '保密级别';
comment on column EMPI.account_identifier_domain_id
  is '账户域ID';
comment on column EMPI.account
  is '账户';
comment on column EMPI.death_ind
  is '死亡标志信息';
comment on column EMPI.death_time
  is '死亡时间';
comment on column EMPI.date_changed
  is '修改日期';
comment on column EMPI.changed_by_id
  is '修改人';
comment on column EMPI.date_voided
  is '废弃日期';
comment on column EMPI.voided_by_id
  is '废弃标识';
comment on column EMPI.blood_type_cd
  is '血型编码';
comment on column EMPI.rh_type
  is 'RH类型';
comment on column EMPI.hospital_cd
  is '医院编码';
comment on column EMPI.custom1
  is '自定义1';
comment on column EMPI.custom2
  is '自定义2';
comment on column EMPI.custom3
  is '自定义3';
comment on column EMPI.custom4
  is '自定义4';
comment on column EMPI.custom5
  is '自定义5';
comment on column EMPI.custom6
  is '自定义6';
comment on column EMPI.custom7
  is '自定义7';
comment on column EMPI.custom8
  is '自定义8';
comment on column EMPI.custom9
  is '自定义9';
comment on column EMPI.custom10
  is '自定义10';
comment on column EMPI.card_type
  is '卡号类型';
comment on column EMPI.account_locked
  is '是否封帐';
comment on column EMPI.account_locked_date
  is '封帐日期';
comment on column EMPI.birth_time
  is '出生时间';
create index DATE_CHANGED_IDX on EMPI (DATE_CHANGED)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IND_DATEVOIDED on EMPI (DATE_VOIDED)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index NAME_IND on EMPI (NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EMPI
  add constraint EMPI_PK primary key (EMPI)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 336M
    next 1M
    minextents 1
    maxextents unlimited
  );


-- 删除表信息
-- delete from EMPI;
-- commit;
--
-- delete from DIAGNOSE;
-- commit;
--
-- delete from CONTACTPERSON;
-- commit;
--
-- delete from AUDIT_EVENT;
-- commit;
--
-- delete from APP_USER;
-- commit;
--
-- delete from ALLERGY;
-- commit;



create table EXTENDFORPERSON
(
  extend_id       NUMBER not null,
  extendfieldname VARCHAR2(64) not null,
  pid4_fields     NUMBER not null,
  custom1         VARCHAR2(256),
  custom2         VARCHAR2(256),
  custom3         VARCHAR2(256)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table EXTENDFORPERSON
  is '扩展字段关系对应表';
comment on column EXTENDFORPERSON.extend_id
  is '主键';
comment on column EXTENDFORPERSON.extendfieldname
  is '扩展字段名';
comment on column EXTENDFORPERSON.pid4_fields
  is 'PID4字段顺位';
comment on column EXTENDFORPERSON.custom1
  is '自定义1';
comment on column EXTENDFORPERSON.custom2
  is '自定义2';
comment on column EXTENDFORPERSON.custom3
  is '自定义3';
alter table EXTENDFORPERSON
  add constraint EXTENDFORPERSON_PK primary key (EXTEND_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );






create table FILE_SYSTEM
(
  file_system_id NUMBER not null,
  file_path      VARCHAR2(64),
  status         VARCHAR2(16),
  create_date    VARCHAR2(32),
  update_date    VARCHAR2(32)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column FILE_SYSTEM.file_system_id
  is '文件盘符路径管理自增长主键';
comment on column FILE_SYSTEM.file_path
  is '文件路径';
comment on column FILE_SYSTEM.status
  is '当前状态';
comment on column FILE_SYSTEM.create_date
  is '创建时间';
comment on column FILE_SYSTEM.update_date
  is '更新时间';
alter table FILE_SYSTEM
  add primary key (FILE_SYSTEM_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table HL7_RECEIVE_LOG
(
  pk              NUMBER not null,
  send_adderss    VARCHAR2(32),
  send_msg        VARCHAR2(4000),
  send_date       DATE,
  hl7_msg_type    VARCHAR2(32),
  receive_adderss VARCHAR2(32),
  receive_msg     VARCHAR2(4000),
  receive_date    DATE,
  status          VARCHAR2(32) default 'DEFAULT',
  retry           NUMBER,
  retry_time      DATE,
  error_info      VARCHAR2(1000)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HL7_RECEIVE_LOG.pk
  is '唯一标识';
comment on column HL7_RECEIVE_LOG.send_adderss
  is '发送地址';
comment on column HL7_RECEIVE_LOG.send_msg
  is '发送消息';
comment on column HL7_RECEIVE_LOG.send_date
  is '发送时间';
comment on column HL7_RECEIVE_LOG.hl7_msg_type
  is 'HL7消息类型';
comment on column HL7_RECEIVE_LOG.receive_adderss
  is '接收地址';
comment on column HL7_RECEIVE_LOG.receive_msg
  is '接收消息';
comment on column HL7_RECEIVE_LOG.receive_date
  is '接收时间';
comment on column HL7_RECEIVE_LOG.status
  is '状态:SUCCESS,FAIL,DEFAULT';
comment on column HL7_RECEIVE_LOG.retry
  is '重试次数';
comment on column HL7_RECEIVE_LOG.retry_time
  is '重试时间';
comment on column HL7_RECEIVE_LOG.error_info
  is '错误信息';


create table HL7_SEND_LOG
(
  pk              NUMBER not null,
  send_adderss    VARCHAR2(32),
  send_msg        VARCHAR2(4000),
  send_date       DATE,
  hl7_msg_type    VARCHAR2(32),
  receive_adderss VARCHAR2(32),
  receive_msg     VARCHAR2(4000),
  receive_date    DATE,
  status          VARCHAR2(32) default 'DEFAULT',
  retry           NUMBER,
  retry_time      DATE,
  error_info      VARCHAR2(1000)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HL7_SEND_LOG.pk
  is '唯一标识';
comment on column HL7_SEND_LOG.send_adderss
  is '发送地址';
comment on column HL7_SEND_LOG.send_msg
  is '发送消息';
comment on column HL7_SEND_LOG.send_date
  is '发送时间';
comment on column HL7_SEND_LOG.hl7_msg_type
  is 'HL7消息类型';
comment on column HL7_SEND_LOG.receive_adderss
  is '接收地址';
comment on column HL7_SEND_LOG.receive_msg
  is '接收消息';
comment on column HL7_SEND_LOG.receive_date
  is '接收时间';
comment on column HL7_SEND_LOG.status
  is '状态:SUCCESS,FAIL,DEFAULT';
comment on column HL7_SEND_LOG.retry
  is '重试次数';
comment on column HL7_SEND_LOG.retry_time
  is '重试时间';
comment on column HL7_SEND_LOG.error_info
  is '错误信息';



create table INDEX_TEST
(
  index_name VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table MANUAL_MERGE_OPERATE_LOG
(
  manual_merge_operate_id NUMBER not null,
  name                    VARCHAR2(32),
  gender_name             VARCHAR2(16),
  patient_id              VARCHAR2(64),
  patient_domain_id       VARCHAR2(64),
  empi                    VARCHAR2(255),
  identity_no             VARCHAR2(32),
  operate_name            VARCHAR2(32),
  operate_status          VARCHAR2(16),
  operate_content         VARCHAR2(255),
  create_date             VARCHAR2(32),
  relevance_id_01         VARCHAR2(64),
  relevance_id_02         VARCHAR2(64),
  relevance_id_03         VARCHAR2(64),
  relevance_id_04         VARCHAR2(64),
  relevance_id_05         VARCHAR2(64),
  deal_date               VARCHAR2(32),
  insurance_no            VARCHAR2(32),
  uuid                    VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column MANUAL_MERGE_OPERATE_LOG.manual_merge_operate_id
  is '患者手动合并操作日志自增长主键';
comment on column MANUAL_MERGE_OPERATE_LOG.name
  is '患者姓名';
comment on column MANUAL_MERGE_OPERATE_LOG.gender_name
  is '性别';
comment on column MANUAL_MERGE_OPERATE_LOG.patient_id
  is '患者ID';
comment on column MANUAL_MERGE_OPERATE_LOG.patient_domain_id
  is '患者域';
comment on column MANUAL_MERGE_OPERATE_LOG.empi
  is 'EMPI';
comment on column MANUAL_MERGE_OPERATE_LOG.identity_no
  is '身份证号';
comment on column MANUAL_MERGE_OPERATE_LOG.operate_name
  is '合并操作者姓名';
comment on column MANUAL_MERGE_OPERATE_LOG.operate_status
  is '操作内容状态';
comment on column MANUAL_MERGE_OPERATE_LOG.operate_content
  is '操作内容';
comment on column MANUAL_MERGE_OPERATE_LOG.create_date
  is '患者手动合并处理创建时间';
comment on column MANUAL_MERGE_OPERATE_LOG.relevance_id_01
  is '患者关联ID01';
comment on column MANUAL_MERGE_OPERATE_LOG.relevance_id_02
  is '患者关联ID02';
comment on column MANUAL_MERGE_OPERATE_LOG.relevance_id_03
  is '患者关联ID03';
comment on column MANUAL_MERGE_OPERATE_LOG.relevance_id_04
  is '患者关联ID04';
comment on column MANUAL_MERGE_OPERATE_LOG.relevance_id_05
  is '患者关联ID05';
comment on column MANUAL_MERGE_OPERATE_LOG.deal_date
  is '患者手动合并分组用时间';
comment on column MANUAL_MERGE_OPERATE_LOG.insurance_no
  is '医保卡号';
comment on column MANUAL_MERGE_OPERATE_LOG.uuid
  is '患者手动合并分组用唯一ID';
create index DEAL_DATE_IDX on MANUAL_MERGE_OPERATE_LOG (DEAL_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index NAME_IDX on MANUAL_MERGE_OPERATE_LOG (NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index OPERATE_NAME_IDX on MANUAL_MERGE_OPERATE_LOG (OPERATE_NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MANUAL_MERGE_OPERATE_LOG
  add primary key (MANUAL_MERGE_OPERATE_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table MATCH_RATIO
(
  id           NUMBER not null,
  group_date   VARCHAR2(16) not null,
  sum_num      NUMBER not null,
  type         VARCHAR2(64) not null,
  date_created VARCHAR2(32) not null,
  comments     VARCHAR2(255),
  domain_id    VARCHAR2(64) not null
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MATCH_RATIO
  is '匹配率统计数据表';
comment on column MATCH_RATIO.id
  is '自增长ID';
comment on column MATCH_RATIO.group_date
  is '年月日期(YYYY-MM)';
comment on column MATCH_RATIO.sum_num
  is '每月总数量/每月匹配量';
comment on column MATCH_RATIO.type
  is '统计类型(all:总数、match匹配总数)';
comment on column MATCH_RATIO.date_created
  is '创建日期';
comment on column MATCH_RATIO.comments
  is '备注';
comment on column MATCH_RATIO.domain_id
  is '系统域ID';
create index MR_DOMAIN_ID_IDX on MATCH_RATIO (DOMAIN_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MR_GROUP_DATE_IDX on MATCH_RATIO (GROUP_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MR_SUM_NUM_IDX on MATCH_RATIO (SUM_NUM)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MR_TYPE_IDX on MATCH_RATIO (TYPE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MATCH_RATIO
  add primary key (ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table MERGE_EVENT
(
  merge_event_id     NUMBER not null,
  event_uuid         VARCHAR2(64),
  old_person_id      VARCHAR2(32),
  old_patient_id     VARCHAR2(32),
  old_patient_domain VARCHAR2(32),
  old_name           VARCHAR2(64),
  old_sex            VARCHAR2(8),
  old_identity_no    VARCHAR2(32),
  old_birthday       TIMESTAMP(6),
  old_empi           VARCHAR2(64),
  new_empi           VARCHAR2(64),
  merge_type         VARCHAR2(32),
  merge_status       VARCHAR2(8),
  merge_date         TIMESTAMP(6) not null,
  send_status        VARCHAR2(8),
  send_date          TIMESTAMP(6),
  send_back_date     TIMESTAMP(6),
  notify_status      VARCHAR2(8),
  notify_date        TIMESTAMP(6),
  notify_back_date   TIMESTAMP(6)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MERGE_EVENT
  is '合并事件记录';
comment on column MERGE_EVENT.event_uuid
  is '一次合并记录的唯一事件UUID';
comment on column MERGE_EVENT.old_person_id
  is '原病人记录PK';
comment on column MERGE_EVENT.old_patient_id
  is '原病人ID';
comment on column MERGE_EVENT.old_patient_domain
  is '原病人ID域';
comment on column MERGE_EVENT.old_name
  is '原病人姓名';
comment on column MERGE_EVENT.old_sex
  is '原病人性别';
comment on column MERGE_EVENT.old_identity_no
  is '原病人身份证号';
comment on column MERGE_EVENT.old_birthday
  is '原病人生辰';
comment on column MERGE_EVENT.old_empi
  is '原病人EMPI号';
comment on column MERGE_EVENT.new_empi
  is '新病人EMPI号';
comment on column MERGE_EVENT.merge_type
  is '合并类型: UPDATE AUTO_MERGE MERGE CANCEL_MERGE';
comment on column MERGE_EVENT.merge_status
  is '合并状态，0 成功 1 失败';
comment on column MERGE_EVENT.merge_date
  is '合并记录插入日期';
comment on column MERGE_EVENT.send_status
  is '发送状态 0 成功 1失败';
comment on column MERGE_EVENT.send_date
  is '同步发送时间';
comment on column MERGE_EVENT.send_back_date
  is '同步发送返回时间';
comment on column MERGE_EVENT.notify_status
  is '通知状态 0 成功 1失败';
comment on column MERGE_EVENT.notify_date
  is '推送时间';
comment on column MERGE_EVENT.notify_back_date
  is '推送返回时间';
create index MERGE_EVENT_OLD_EMPI_IDX on MERGE_EVENT (OLD_EMPI)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MERGE_EVENT_SEND_STATUS_IDX on MERGE_EVENT (SEND_STATUS)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MERGE_EVENT_UUID_ID_IDX on MERGE_EVENT (EVENT_UUID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MERGE_ID_DOMAIN_EMPI_IDX on MERGE_EVENT (OLD_PERSON_ID, OLD_PATIENT_ID, OLD_PATIENT_DOMAIN, OLD_EMPI)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MERGE_EVENT
  add constraint MERGE_EVENT_PK primary key (MERGE_EVENT_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MERGE_EVENT
  add constraint MERGE_ID_DOMAIN_EMPI unique (OLD_PERSON_ID, OLD_PATIENT_ID, OLD_PATIENT_DOMAIN, OLD_EMPI)
  novalidate;



create table PATIENT_FINALLY_STATUS
(
  patient_finally_status_id NUMBER not null,
  empi                      VARCHAR2(64),
  name                      VARCHAR2(32),
  gender_cd                 VARCHAR2(32),
  date_of_birth             VARCHAR2(32),
  home_address              VARCHAR2(128),
  home_phone                VARCHAR2(32),
  identity_no               VARCHAR2(32),
  ssn                       VARCHAR2(32),
  insurance_no              VARCHAR2(32),
  insurance_type            VARCHAR2(32),
  health_card               VARCHAR2(32),
  citizen_card              VARCHAR2(32),
  driver_license            VARCHAR2(32),
  elder_certificate         VARCHAR2(32),
  soldier_certificate       VARCHAR2(32),
  birth_place               VARCHAR2(128),
  registered_address        VARCHAR2(128),
  residential_address       VARCHAR2(128),
  patient_id                VARCHAR2(64) not null,
  patient_domain_id         VARCHAR2(64) not null,
  visit_flow_id             VARCHAR2(128),
  visit_flow_domain         VARCHAR2(128),
  visit_card_no             VARCHAR2(32),
  pat_category              VARCHAR2(32),
  patient_class             VARCHAR2(32),
  admissions_doctor         VARCHAR2(32),
  admissions_doctor_id      VARCHAR2(32),
  referring_doctor          VARCHAR2(32),
  referring_doctor_id       VARCHAR2(32),
  consultation_doctor       VARCHAR2(32),
  consultation_doctor_id    VARCHAR2(32),
  pat_admission_doctors     VARCHAR2(32),
  pat_admission_doctors_id  VARCHAR2(32),
  tend_level                VARCHAR2(32),
  pat_nurse_name            VARCHAR2(32),
  pat_nurse_code            VARCHAR2(32),
  admit_date                VARCHAR2(32),
  admit_dept                VARCHAR2(32),
  admit_dept_id             VARCHAR2(32),
  discharge_date            VARCHAR2(32),
  discharge_dept            VARCHAR2(32),
  discharge_dept_id         VARCHAR2(32),
  discharge_name            VARCHAR2(32),
  contact_person            VARCHAR2(32),
  contact_relations         VARCHAR2(32),
  contact_address           VARCHAR2(128),
  contact_phone             VARCHAR2(32),
  parent_code               VARCHAR2(128),
  current_code              VARCHAR2(128),
  shift_type                VARCHAR2(128),
  old_data_code             VARCHAR2(128),
  old_data_name             VARCHAR2(128),
  new_data_code             VARCHAR2(128),
  new_data_name             VARCHAR2(128),
  shift_cause               VARCHAR2(128),
  finally_status            VARCHAR2(64),
  mark                      VARCHAR2(128),
  date_created              VARCHAR2(32) not null,
  creator_by_id             VARCHAR2(32) not null,
  date_changed              VARCHAR2(32) not null,
  changed_by_id             VARCHAR2(32) not null,
  date_voided               VARCHAR2(64),
  voided_by_id              VARCHAR2(64),
  finally_status_code       VARCHAR2(32)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table PATIENT_FINALLY_STATUS
  is '患者就诊最终状态表';
comment on column PATIENT_FINALLY_STATUS.patient_finally_status_id
  is '自增主键';
comment on column PATIENT_FINALLY_STATUS.empi
  is 'EMPI';
comment on column PATIENT_FINALLY_STATUS.name
  is '患者姓名';
comment on column PATIENT_FINALLY_STATUS.gender_cd
  is '性别编码';
comment on column PATIENT_FINALLY_STATUS.date_of_birth
  is '出生日期';
comment on column PATIENT_FINALLY_STATUS.home_address
  is '家庭地址';
comment on column PATIENT_FINALLY_STATUS.home_phone
  is '家庭电话';
comment on column PATIENT_FINALLY_STATUS.identity_no
  is '身份证号';
comment on column PATIENT_FINALLY_STATUS.ssn
  is '社会保险号';
comment on column PATIENT_FINALLY_STATUS.insurance_no
  is '医疗保险号';
comment on column PATIENT_FINALLY_STATUS.insurance_type
  is '医保类型';
comment on column PATIENT_FINALLY_STATUS.health_card
  is '健康卡号';
comment on column PATIENT_FINALLY_STATUS.citizen_card
  is '市民卡号';
comment on column PATIENT_FINALLY_STATUS.driver_license
  is '驾驶证号';
comment on column PATIENT_FINALLY_STATUS.elder_certificate
  is '老人证号';
comment on column PATIENT_FINALLY_STATUS.soldier_certificate
  is '军官证号';
comment on column PATIENT_FINALLY_STATUS.birth_place
  is '出生地详细地址';
comment on column PATIENT_FINALLY_STATUS.registered_address
  is '户口所在详细地址';
comment on column PATIENT_FINALLY_STATUS.residential_address
  is '居住地详细地址';
comment on column PATIENT_FINALLY_STATUS.patient_id
  is '机构内患者ID';
comment on column PATIENT_FINALLY_STATUS.patient_domain_id
  is '机构内患者域ID';
comment on column PATIENT_FINALLY_STATUS.visit_flow_id
  is '就诊ID';
comment on column PATIENT_FINALLY_STATUS.visit_flow_domain
  is '就诊域ID';
comment on column PATIENT_FINALLY_STATUS.visit_card_no
  is '就诊卡号';
comment on column PATIENT_FINALLY_STATUS.pat_category
  is '患者类别（住院、门诊、急诊、体检）';
comment on column PATIENT_FINALLY_STATUS.patient_class
  is '患者类型';
comment on column PATIENT_FINALLY_STATUS.admissions_doctor
  is '住院医师';
comment on column PATIENT_FINALLY_STATUS.admissions_doctor_id
  is '住院医师工号';
comment on column PATIENT_FINALLY_STATUS.referring_doctor
  is '主治医师';
comment on column PATIENT_FINALLY_STATUS.referring_doctor_id
  is '主治医师工号';
comment on column PATIENT_FINALLY_STATUS.consultation_doctor
  is '主任医师';
comment on column PATIENT_FINALLY_STATUS.consultation_doctor_id
  is '主任医师工号';
comment on column PATIENT_FINALLY_STATUS.pat_admission_doctors
  is '门诊接诊医生';
comment on column PATIENT_FINALLY_STATUS.pat_admission_doctors_id
  is '门诊接诊医生工号';
comment on column PATIENT_FINALLY_STATUS.tend_level
  is '住院护理级别';
comment on column PATIENT_FINALLY_STATUS.pat_nurse_name
  is '住院护士姓名';
comment on column PATIENT_FINALLY_STATUS.pat_nurse_code
  is '住院护士工号';
comment on column PATIENT_FINALLY_STATUS.admit_date
  is '入院日期';
comment on column PATIENT_FINALLY_STATUS.admit_dept
  is '入院科室名称';
comment on column PATIENT_FINALLY_STATUS.admit_dept_id
  is '入院科室ID';
comment on column PATIENT_FINALLY_STATUS.discharge_date
  is '出院日期';
comment on column PATIENT_FINALLY_STATUS.discharge_dept
  is '出院科室名称';
comment on column PATIENT_FINALLY_STATUS.discharge_dept_id
  is '出院科室ID';
comment on column PATIENT_FINALLY_STATUS.discharge_name
  is '离院处置名称';
comment on column PATIENT_FINALLY_STATUS.contact_person
  is '联系人';
comment on column PATIENT_FINALLY_STATUS.contact_relations
  is '联系人与患者关系';
comment on column PATIENT_FINALLY_STATUS.contact_address
  is '联系人地址';
comment on column PATIENT_FINALLY_STATUS.contact_phone
  is '联系人电话';
comment on column PATIENT_FINALLY_STATUS.parent_code
  is '父级医疗机构编码';
comment on column PATIENT_FINALLY_STATUS.current_code
  is '本级医院机构编码';
comment on column PATIENT_FINALLY_STATUS.shift_type
  is '流转类型';
comment on column PATIENT_FINALLY_STATUS.old_data_code
  is '原资料代号';
comment on column PATIENT_FINALLY_STATUS.old_data_name
  is '原资料名称';
comment on column PATIENT_FINALLY_STATUS.new_data_code
  is '新资料代号';
comment on column PATIENT_FINALLY_STATUS.new_data_name
  is '新资料名称';
comment on column PATIENT_FINALLY_STATUS.shift_cause
  is '变更原因';
comment on column PATIENT_FINALLY_STATUS.finally_status
  is '最终状态';
comment on column PATIENT_FINALLY_STATUS.mark
  is '备注';
comment on column PATIENT_FINALLY_STATUS.date_created
  is '创建日期';
comment on column PATIENT_FINALLY_STATUS.creator_by_id
  is '创建者';
comment on column PATIENT_FINALLY_STATUS.date_changed
  is '修改日期';
comment on column PATIENT_FINALLY_STATUS.changed_by_id
  is '修改人';
comment on column PATIENT_FINALLY_STATUS.date_voided
  is '废弃日期';
comment on column PATIENT_FINALLY_STATUS.voided_by_id
  is '废弃标识';
comment on column PATIENT_FINALLY_STATUS.finally_status_code
  is '最终状态CODE';


create table PATIENT_VISIT
(
  visit_id                      NUMBER not null,
  person_id                     NUMBER not null,
  person_domain                 VARCHAR2(64) not null,
  visit_flow_id                 VARCHAR2(64) not null,
  visit_flow_domain             VARCHAR2(64) not null,
  hospital_domain               VARCHAR2(64),
  pat_category                  VARCHAR2(16),
  pat_current_point_of_care     VARCHAR2(256),
  pat_current_room              VARCHAR2(256),
  pat_current_bed               VARCHAR2(256),
  pat_cuurent_dep               VARCHAR2(256),
  pat_current_position_status   VARCHAR2(256),
  pat_current_position_type     VARCHAR2(32),
  pat_current_building          VARCHAR2(64),
  pat_current_floor             VARCHAR2(64),
  pat_cuurent_description       VARCHAR2(256),
  pat_admission_type            VARCHAR2(16),
  pat_admission_number          VARCHAR2(128),
  pat_former_point_of_care      VARCHAR2(256),
  pat_former_room               VARCHAR2(256),
  pat_former_bed                VARCHAR2(256),
  pat_former_dep                VARCHAR2(256),
  pat_former_position_status    VARCHAR2(256),
  pat_former_position_type      VARCHAR2(32),
  pat_former_building           VARCHAR2(64),
  pat_former_floor              VARCHAR2(64),
  pat_former_description        VARCHAR2(256),
  admissions_doctor             VARCHAR2(256),
  admissions_doctor_id          VARCHAR2(32),
  referring_doctor              VARCHAR2(256),
  referring_doctor_id           VARCHAR2(32),
  consultation_doctor           VARCHAR2(256),
  consultation_doctor_id        VARCHAR2(32),
  hospital_service              VARCHAR2(32),
  pat_temp_point_of_care        VARCHAR2(256),
  pat_temp_room                 VARCHAR2(256),
  pat_temp_bed                  VARCHAR2(256),
  pat_temp_dep                  VARCHAR2(256),
  pat_temp_position_status      VARCHAR2(256),
  pat_temp_position_type        VARCHAR2(32),
  pat_temp_building             VARCHAR2(64),
  pat_temp_floor                VARCHAR2(64),
  pat_temp_description          VARCHAR2(256),
  pat_admission_test            VARCHAR2(256),
  pat_re_admission              VARCHAR2(32),
  pat_admission_source          VARCHAR2(256),
  pat_ambulatory_status         VARCHAR2(256),
  pat_vip                       VARCHAR2(32),
  pat_admission_doctors         VARCHAR2(256),
  pat_admission_doctors_id      VARCHAR2(32),
  patient_class                 VARCHAR2(64),
  patient_id                    VARCHAR2(64),
  pat_financial_class           VARCHAR2(32),
  room_bed_cost_price           VARCHAR2(64),
  courtesy_code                 VARCHAR2(16),
  credit_rating                 VARCHAR2(32),
  contract_code                 VARCHAR2(32),
  contract_create_date          VARCHAR2(32),
  contract_price                VARCHAR2(32),
  contract_time                 VARCHAR2(32),
  interest_rate_code            VARCHAR2(32),
  bad_debts                     VARCHAR2(32),
  bad_debts_create_date         VARCHAR2(32),
  bad_debts_code                VARCHAR2(32),
  bad_debts_price               VARCHAR2(32),
  bad_debts__restore_price      VARCHAR2(32),
  pat_account_voided            VARCHAR2(32),
  pat_account_voided_date       VARCHAR2(32),
  pat_discharge_disposition     VARCHAR2(32),
  pat_discharge_location        VARCHAR2(256),
  pat_diet_type                 VARCHAR2(64),
  pat_service_agencies          VARCHAR2(64),
  pat_bed_status                VARCHAR2(32),
  pat_account_status            VARCHAR2(32),
  pat_deter_point_of_care       VARCHAR2(256),
  pat_deter_room                VARCHAR2(256),
  pat_deter_bed                 VARCHAR2(256),
  pat_deter_dep                 VARCHAR2(256),
  pat_deter_position_status     VARCHAR2(256),
  pat_deter_position_type       VARCHAR2(32),
  pat_deter_building            VARCHAR2(64),
  pat_deter_floor               VARCHAR2(64),
  pat_deter_description         VARCHAR2(256),
  pat_for_temp_point_of_care    VARCHAR2(256),
  pat_for_temp_room             VARCHAR2(256),
  pat_for_temp_bed              VARCHAR2(256),
  pat_for_temp_dep              VARCHAR2(256),
  pat_for_temp_position_status  VARCHAR2(256),
  pat_for_temp_position_type    VARCHAR2(32),
  pat_for_temp_building         VARCHAR2(64),
  pat_for_temp_floor            VARCHAR2(64),
  pat_for_temp_description      VARCHAR2(256),
  admit_date                    TIMESTAMP(6),
  discharge_date                TIMESTAMP(6),
  pat_difference                VARCHAR2(32),
  pat_total_cost                VARCHAR2(32),
  pat_total_dispatch            VARCHAR2(32),
  pat_total_amount_payable      VARCHAR2(32),
  pat_spare_id                  VARCHAR2(32),
  pat_visit_logo                VARCHAR2(32),
  other_medical_institutions    VARCHAR2(256),
  create_date                   TIMESTAMP(6),
  create_id                     NUMBER,
  voided_date                   TIMESTAMP(6),
  voided_id                     NUMBER,
  modify_date                   TIMESTAMP(6),
  modify_id                     NUMBER,
  custom1                       VARCHAR2(256),
  custom2                       VARCHAR2(256),
  custom3                       VARCHAR2(256),
  custom4                       VARCHAR2(256),
  custom5                       VARCHAR2(256),
  pat_nurse_code                VARCHAR2(32),
  pat_nurse_name                VARCHAR2(32),
  tend                          VARCHAR2(32),
  baby_flag                     VARCHAR2(255),
  admit_weight                  VARCHAR2(255),
  birth_weight                  VARCHAR2(255),
  oper_code                     VARCHAR2(32),
  oper_date                     TIMESTAMP(6),
  prefix                        VARCHAR2(64),
  insurance_type                VARCHAR2(64),
  contact_person                VARCHAR2(64),
  contact_relations             VARCHAR2(64),
  contact_address               VARCHAR2(512),
  contact_phone                 VARCHAR2(64),
  pat_category_name             VARCHAR2(64),
  gender_name                   VARCHAR2(64),
  pay_rate                      VARCHAR2(64),
  discharge_name                VARCHAR2(64),
  insurance_name                VARCHAR2(64),
  admission_name                VARCHAR2(64),
  ip_status_name                VARCHAR2(64),
  dificulty_name                VARCHAR2(64),
  admit_way_name                VARCHAR2(64),
  admit_weight_unit             VARCHAR2(64),
  baby_weight_unit              VARCHAR2(64),
  medicinelimitamount           NUMBER,
  sickbedlimitamount            NUMBER,
  examinelimitamount            NUMBER,
  curelimitamount               NUMBER,
  hiup_status                   VARCHAR2(16),
  hiup_error_info               VARCHAR2(4000),
  admission_domain              VARCHAR2(64),
  admission_source_domain       VARCHAR2(64),
  admission_source_name         VARCHAR2(64),
  patient_class_name            VARCHAR2(64),
  patient_class_domain          VARCHAR2(64),
  ip_status_domain              VARCHAR2(64),
  dificulty_domain              VARCHAR2(64),
  discharge_domain              VARCHAR2(64),
  account_status_name           VARCHAR2(64),
  account_status_domain         VARCHAR2(64),
  gender_domain                 VARCHAR2(64),
  pat_category_system           VARCHAR2(64),
  mothers_id                    VARCHAR2(64),
  mothers_domain                VARCHAR2(64),
  mothers_flow_id               VARCHAR2(64),
  mothers_flow_domain           VARCHAR2(64),
  mothers_name                  VARCHAR2(64),
  noon_code                     VARCHAR2(16),
  paykind_code                  VARCHAR2(64),
  paykind_name                  VARCHAR2(64),
  schema_no                     VARCHAR2(64),
  order_no                      VARCHAR2(64),
  seeno                         VARCHAR2(64),
  begin_time                    TIMESTAMP(6),
  end_time                      TIMESTAMP(6),
  ynbook                        VARCHAR2(16),
  ynfr                          VARCHAR2(16),
  append_flag                   VARCHAR2(16),
  ynsee                         VARCHAR2(16),
  see_date                      TIMESTAMP(6),
  triage_flag                   VARCHAR2(16),
  triage_opcd                   VARCHAR2(64),
  triage_date                   TIMESTAMP(6),
  see_dpcd                      VARCHAR2(64),
  see_docd                      VARCHAR2(64),
  out_patient_status_a          VARCHAR2(16),
  out_patient_status_b          VARCHAR2(16),
  out_patient_status_c          VARCHAR2(16),
  in_patient_status_a           VARCHAR2(16),
  in_patient_status_b           VARCHAR2(16),
  in_patient_status_c           VARCHAR2(16),
  patient_source_name           VARCHAR2(64),
  old_patient_id                VARCHAR2(64),
  old_patient_domain            VARCHAR2(64),
  old_visit_flow_id             VARCHAR2(64),
  old_visit_flow_domain         VARCHAR2(64),
  old_visit_id                  VARCHAR2(64),
  old_person_id                 VARCHAR2(64),
  old_status                    VARCHAR2(64),
  old_info                      VARCHAR2(255),
  isemergency                   VARCHAR2(64),
  pat_ip_times                  VARCHAR2(16),
  diagnose_icd                  VARCHAR2(64),
  diagnose_name                 VARCHAR2(64),
  custom6                       VARCHAR2(64),
  relevance_id                  VARCHAR2(64),
  relevance_domain              VARCHAR2(64),
  relevance_name                VARCHAR2(64),
  diagnosis_treatment_group     VARCHAR2(64),
  diagnosis_treatment_groupcode VARCHAR2(16),
  nursing_group                 VARCHAR2(64),
  nursing_group_code            VARCHAR2(16),
  hospitalization_purposes      VARCHAR2(255),
  tube_bed_doctor               VARCHAR2(64),
  oper_name                     VARCHAR2(16),
  enter_dept_time               VARCHAR2(16)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 4096M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table PATIENT_VISIT
  is '病人就诊信息';
comment on column PATIENT_VISIT.visit_id
  is '就诊自增长';
comment on column PATIENT_VISIT.person_id
  is '患者ID';
comment on column PATIENT_VISIT.person_domain
  is '患者域ID';
comment on column PATIENT_VISIT.visit_flow_id
  is '流水ID';
comment on column PATIENT_VISIT.visit_flow_domain
  is '流水域ID';
comment on column PATIENT_VISIT.hospital_domain
  is '医院域ID';
comment on column PATIENT_VISIT.pat_category
  is '患者类别';
comment on column PATIENT_VISIT.pat_current_point_of_care
  is '入院护理区域';
comment on column PATIENT_VISIT.pat_current_room
  is '入院病区';
comment on column PATIENT_VISIT.pat_current_bed
  is '入院床位';
comment on column PATIENT_VISIT.pat_cuurent_dep
  is '入院科室';
comment on column PATIENT_VISIT.pat_current_position_status
  is '入院位置的状态';
comment on column PATIENT_VISIT.pat_current_position_type
  is '入院位置的类型';
comment on column PATIENT_VISIT.pat_current_building
  is '入院大楼';
comment on column PATIENT_VISIT.pat_current_floor
  is '入院楼层';
comment on column PATIENT_VISIT.pat_cuurent_description
  is '入院相关描述';
comment on column PATIENT_VISIT.pat_admission_type
  is '允许入院类型';
comment on column PATIENT_VISIT.pat_admission_number
  is '允许入院号码';
comment on column PATIENT_VISIT.pat_former_point_of_care
  is '病人前护理区域';
comment on column PATIENT_VISIT.pat_former_room
  is '病人前病区';
comment on column PATIENT_VISIT.pat_former_bed
  is '病人前床位';
comment on column PATIENT_VISIT.pat_former_dep
  is '病人前科室';
comment on column PATIENT_VISIT.pat_former_position_status
  is '病人前位置状态';
comment on column PATIENT_VISIT.pat_former_position_type
  is '病人前位置类型';
comment on column PATIENT_VISIT.pat_former_building
  is '病人前大楼';
comment on column PATIENT_VISIT.pat_former_floor
  is '病人前楼层';
comment on column PATIENT_VISIT.pat_former_description
  is '病人前相关描述';
comment on column PATIENT_VISIT.admissions_doctor
  is '住院医师';
comment on column PATIENT_VISIT.admissions_doctor_id
  is '住院医师ID';
comment on column PATIENT_VISIT.referring_doctor
  is '主治医师';
comment on column PATIENT_VISIT.referring_doctor_id
  is '主治医师ID';
comment on column PATIENT_VISIT.consultation_doctor
  is '主任医师';
comment on column PATIENT_VISIT.consultation_doctor_id
  is '主任医师ID';
comment on column PATIENT_VISIT.hospital_service
  is '医院服务';
comment on column PATIENT_VISIT.pat_temp_point_of_care
  is '临时护理区域';
comment on column PATIENT_VISIT.pat_temp_room
  is '临时病区';
comment on column PATIENT_VISIT.pat_temp_bed
  is '临时床位';
comment on column PATIENT_VISIT.pat_temp_dep
  is '临时科室';
comment on column PATIENT_VISIT.pat_temp_position_status
  is '临时位置状态';
comment on column PATIENT_VISIT.pat_temp_position_type
  is '临时位置类型';
comment on column PATIENT_VISIT.pat_temp_building
  is '临时大楼';
comment on column PATIENT_VISIT.pat_temp_floor
  is '临时楼层';
comment on column PATIENT_VISIT.pat_temp_description
  is '临时相关描述';
comment on column PATIENT_VISIT.pat_admission_test
  is '实验';
comment on column PATIENT_VISIT.pat_re_admission
  is '住院次数';
comment on column PATIENT_VISIT.pat_admission_source
  is '入院来源编码';
comment on column PATIENT_VISIT.pat_ambulatory_status
  is '走动状态';
comment on column PATIENT_VISIT.pat_vip
  is 'VIP标志';
comment on column PATIENT_VISIT.pat_admission_doctors
  is '门诊接诊医生';
comment on column PATIENT_VISIT.pat_admission_doctors_id
  is '门诊接诊医生ID';
comment on column PATIENT_VISIT.patient_class
  is '患者类型';
comment on column PATIENT_VISIT.patient_id
  is '病人ID';
comment on column PATIENT_VISIT.pat_financial_class
  is '财务类';
comment on column PATIENT_VISIT.room_bed_cost_price
  is '床位价格';
comment on column PATIENT_VISIT.courtesy_code
  is '住院状态';
comment on column PATIENT_VISIT.credit_rating
  is '病例分型';
comment on column PATIENT_VISIT.contract_code
  is '合同编码';
comment on column PATIENT_VISIT.contract_create_date
  is '合同创建日期';
comment on column PATIENT_VISIT.contract_price
  is '合同价格';
comment on column PATIENT_VISIT.contract_time
  is '合同时间';
comment on column PATIENT_VISIT.interest_rate_code
  is '支付比例编码';
comment on column PATIENT_VISIT.bad_debts
  is '坏账';
comment on column PATIENT_VISIT.bad_debts_create_date
  is '坏账创建日期';
comment on column PATIENT_VISIT.bad_debts_code
  is '坏账编码';
comment on column PATIENT_VISIT.bad_debts_price
  is '坏账费用';
comment on column PATIENT_VISIT.bad_debts__restore_price
  is '坏账恢复';
comment on column PATIENT_VISIT.pat_account_voided
  is '账户无效';
comment on column PATIENT_VISIT.pat_account_voided_date
  is '账户无效日期';
comment on column PATIENT_VISIT.pat_discharge_disposition
  is '出院处置编码';
comment on column PATIENT_VISIT.pat_discharge_location
  is '出院位置';
comment on column PATIENT_VISIT.pat_diet_type
  is '饮食类型';
comment on column PATIENT_VISIT.pat_service_agencies
  is '服务机构';
comment on column PATIENT_VISIT.pat_bed_status
  is '床位标志';
comment on column PATIENT_VISIT.pat_account_status
  is '支付方式编码';
comment on column PATIENT_VISIT.pat_deter_point_of_care
  is '取消的护理区域';
comment on column PATIENT_VISIT.pat_deter_room
  is '取消的病区';
comment on column PATIENT_VISIT.pat_deter_bed
  is '取消的床位';
comment on column PATIENT_VISIT.pat_deter_dep
  is '取消时科室';
comment on column PATIENT_VISIT.pat_deter_position_status
  is '取消时位置状态';
comment on column PATIENT_VISIT.pat_deter_position_type
  is '取消时位置类型';
comment on column PATIENT_VISIT.pat_deter_building
  is '取消时大楼';
comment on column PATIENT_VISIT.pat_deter_floor
  is '取消时楼层';
comment on column PATIENT_VISIT.pat_deter_description
  is '取消时相关描述';
comment on column PATIENT_VISIT.pat_for_temp_point_of_care
  is '前临时护理区域';
comment on column PATIENT_VISIT.pat_for_temp_room
  is '前临时病区';
comment on column PATIENT_VISIT.pat_for_temp_bed
  is '前临时床位';
comment on column PATIENT_VISIT.pat_for_temp_dep
  is '前临时科室';
comment on column PATIENT_VISIT.pat_for_temp_position_status
  is '前临时位置状态';
comment on column PATIENT_VISIT.pat_for_temp_position_type
  is '前临时位置类型';
comment on column PATIENT_VISIT.pat_for_temp_building
  is '前临时大楼';
comment on column PATIENT_VISIT.pat_for_temp_floor
  is '前临时楼层';
comment on column PATIENT_VISIT.pat_for_temp_description
  is '前临时相关描述';
comment on column PATIENT_VISIT.admit_date
  is '入院日期';
comment on column PATIENT_VISIT.discharge_date
  is '出院日期';
comment on column PATIENT_VISIT.pat_difference
  is '差额';
comment on column PATIENT_VISIT.pat_total_cost
  is '总价';
comment on column PATIENT_VISIT.pat_total_dispatch
  is '总调度';
comment on column PATIENT_VISIT.pat_total_amount_payable
  is '应付的总额';
comment on column PATIENT_VISIT.pat_spare_id
  is '多余的ID';
comment on column PATIENT_VISIT.pat_visit_logo
  is '访问标志';
comment on column PATIENT_VISIT.other_medical_institutions
  is '其他卫生机构';
comment on column PATIENT_VISIT.create_date
  is '创建日期';
comment on column PATIENT_VISIT.create_id
  is '创建者';
comment on column PATIENT_VISIT.voided_date
  is '废弃日';
comment on column PATIENT_VISIT.voided_id
  is '废弃标识';
comment on column PATIENT_VISIT.modify_date
  is '修改日';
comment on column PATIENT_VISIT.modify_id
  is '修改者';
comment on column PATIENT_VISIT.custom1
  is '自定义1';
comment on column PATIENT_VISIT.custom2
  is '自定义2';
comment on column PATIENT_VISIT.custom3
  is '自定义3';
comment on column PATIENT_VISIT.custom4
  is '自定义4';
comment on column PATIENT_VISIT.custom5
  is '自定义5';
comment on column PATIENT_VISIT.pat_nurse_code
  is '护士ID';
comment on column PATIENT_VISIT.pat_nurse_name
  is '护士姓名';
comment on column PATIENT_VISIT.tend
  is '护理';
comment on column PATIENT_VISIT.baby_flag
  is '婴儿标志';
comment on column PATIENT_VISIT.admit_weight
  is '入院体重';
comment on column PATIENT_VISIT.birth_weight
  is '出生体重';
comment on column PATIENT_VISIT.oper_code
  is '入院登记操作员';
comment on column PATIENT_VISIT.oper_date
  is '操作日期';
comment on column PATIENT_VISIT.prefix
  is '前缀';
comment on column PATIENT_VISIT.insurance_type
  is '保险类型';
comment on column PATIENT_VISIT.contact_person
  is '联系人';
comment on column PATIENT_VISIT.contact_relations
  is '联系关系';
comment on column PATIENT_VISIT.contact_address
  is '联系地址';
comment on column PATIENT_VISIT.contact_phone
  is '联系电话';
comment on column PATIENT_VISIT.pat_category_name
  is '患者类型名称';
comment on column PATIENT_VISIT.gender_name
  is '性别名称';
comment on column PATIENT_VISIT.pay_rate
  is '付费标准';
comment on column PATIENT_VISIT.discharge_name
  is '离院处置名称';
comment on column PATIENT_VISIT.insurance_name
  is '保险名称';
comment on column PATIENT_VISIT.admission_name
  is '入院时情况名称';
comment on column PATIENT_VISIT.ip_status_name
  is '病人住院状态名称';
comment on column PATIENT_VISIT.dificulty_name
  is '病例分型名称';
comment on column PATIENT_VISIT.admit_way_name
  is '许可进入名称';
comment on column PATIENT_VISIT.admit_weight_unit
  is '入院体重单位';
comment on column PATIENT_VISIT.baby_weight_unit
  is '出院体重单位';
comment on column PATIENT_VISIT.medicinelimitamount
  is '药费限额';
comment on column PATIENT_VISIT.sickbedlimitamount
  is '床位限额';
comment on column PATIENT_VISIT.examinelimitamount
  is '检查限额';
comment on column PATIENT_VISIT.curelimitamount
  is '治疗限额';
comment on column PATIENT_VISIT.hiup_status
  is 'HIUP状态';
comment on column PATIENT_VISIT.hiup_error_info
  is 'HIUP错误信息';
comment on column PATIENT_VISIT.admission_domain
  is '入院时情况编码系统';
comment on column PATIENT_VISIT.admission_source_domain
  is '入院途径编码系统';
comment on column PATIENT_VISIT.admission_source_name
  is '入院途径名称';
comment on column PATIENT_VISIT.patient_class_name
  is '患者类型编码';
comment on column PATIENT_VISIT.patient_class_domain
  is '患者类型编码系统';
comment on column PATIENT_VISIT.ip_status_domain
  is '病人住院状态编码系统';
comment on column PATIENT_VISIT.dificulty_domain
  is '病例分型编码系统';
comment on column PATIENT_VISIT.discharge_domain
  is '离院处置编码系统';
comment on column PATIENT_VISIT.account_status_name
  is '支付方式名称';
comment on column PATIENT_VISIT.account_status_domain
  is '支付方式编码系统';
comment on column PATIENT_VISIT.gender_domain
  is '性别域';
comment on column PATIENT_VISIT.pat_category_system
  is '患者类别编码系统';
comment on column PATIENT_VISIT.mothers_id
  is '母亲ID';
comment on column PATIENT_VISIT.mothers_domain
  is '母亲域';
comment on column PATIENT_VISIT.mothers_flow_id
  is '母亲流水ID';
comment on column PATIENT_VISIT.mothers_flow_domain
  is '母亲流水域';
comment on column PATIENT_VISIT.mothers_name
  is '母亲姓名';
comment on column PATIENT_VISIT.noon_code
  is '后续编码';
comment on column PATIENT_VISIT.paykind_code
  is '支付编码';
comment on column PATIENT_VISIT.paykind_name
  is '支付名称';
comment on column PATIENT_VISIT.schema_no
  is '计划、图解、概要号';
comment on column PATIENT_VISIT.order_no
  is '订单号';
comment on column PATIENT_VISIT.seeno
  is '看编号';
comment on column PATIENT_VISIT.begin_time
  is '开始时间';
comment on column PATIENT_VISIT.end_time
  is '结束时间';
comment on column PATIENT_VISIT.ynbook
  is '年鉴';
comment on column PATIENT_VISIT.ynfr
  is '年鉴故障率';
comment on column PATIENT_VISIT.append_flag
  is '附加标记';
comment on column PATIENT_VISIT.see_date
  is '看日期';
comment on column PATIENT_VISIT.triage_flag
  is '诊断标志';
comment on column PATIENT_VISIT.triage_opcd
  is '诊断OP编号';
comment on column PATIENT_VISIT.triage_date
  is '诊断日期';
comment on column PATIENT_VISIT.see_dpcd
  is '看DP编号';
comment on column PATIENT_VISIT.see_docd
  is '看DO编号';
comment on column PATIENT_VISIT.out_patient_status_a
  is 'OUT患者状态A';
comment on column PATIENT_VISIT.out_patient_status_b
  is 'OUT患者状态B';
comment on column PATIENT_VISIT.out_patient_status_c
  is 'OUT患者状态C';
comment on column PATIENT_VISIT.in_patient_status_a
  is 'IN患者状态A';
comment on column PATIENT_VISIT.in_patient_status_b
  is 'IN患者状态B';
comment on column PATIENT_VISIT.in_patient_status_c
  is 'IN患者状态C';
comment on column PATIENT_VISIT.patient_source_name
  is '患者来源名称';
comment on column PATIENT_VISIT.old_patient_id
  is '旧患者ID';
comment on column PATIENT_VISIT.old_patient_domain
  is '旧患者域ID';
comment on column PATIENT_VISIT.old_visit_flow_id
  is '旧患者流水ID';
comment on column PATIENT_VISIT.old_visit_flow_domain
  is '旧患者流水域ID';
comment on column PATIENT_VISIT.old_visit_id
  is '旧患者就诊ID';
comment on column PATIENT_VISIT.old_person_id
  is '旧person_id';
comment on column PATIENT_VISIT.old_status
  is '旧状态';
comment on column PATIENT_VISIT.old_info
  is '旧信息';
comment on column PATIENT_VISIT.isemergency
  is '紧急情况标识';
comment on column PATIENT_VISIT.pat_ip_times
  is '次数';
comment on column PATIENT_VISIT.diagnose_icd
  is '诊断ICD';
comment on column PATIENT_VISIT.diagnose_name
  is '诊断名称';
comment on column PATIENT_VISIT.custom6
  is '自定义6';
comment on column PATIENT_VISIT.relevance_id
  is '关联ID';
comment on column PATIENT_VISIT.relevance_domain
  is '关联域';
comment on column PATIENT_VISIT.relevance_name
  is '关联名';
comment on column PATIENT_VISIT.diagnosis_treatment_group
  is '诊疗组';
comment on column PATIENT_VISIT.diagnosis_treatment_groupcode
  is '诊疗组CODE';
comment on column PATIENT_VISIT.nursing_group
  is '护理组';
comment on column PATIENT_VISIT.nursing_group_code
  is '护理组CODE';
comment on column PATIENT_VISIT.hospitalization_purposes
  is '住院目的';
comment on column PATIENT_VISIT.tube_bed_doctor
  is '管床医生';
comment on column PATIENT_VISIT.oper_name
  is '操作员姓名';
comment on column PATIENT_VISIT.enter_dept_time
  is '首次入科时间';
create index ADMITE_IDX on PATIENT_VISIT (ADMIT_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM1_IDX on PATIENT_VISIT (CUSTOM1)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1223M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM4_IDX on PATIENT_VISIT (CUSTOM4)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1280M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM5_IDX on PATIENT_VISIT (CUSTOM5)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1344M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index DATE_AD_DIS on PATIENT_VISIT (DISCHARGE_DATE, ADMIT_DATE, CREATE_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index DISCHARGE_DATE_IDX on PATIENT_VISIT (DISCHARGE_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IND_TYPE on PATIENT_VISIT (PAT_CATEGORY, COURTESY_CODE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IPSTNAME_IDX on PATIENT_VISIT (IP_STATUS_NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index OPER_DATE_IDX on PATIENT_VISIT (OPER_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PATIENT_ID_IDX on PATIENT_VISIT (PATIENT_ID, PERSON_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1348M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PATIENT_VISIT_DEPI_COMBINE on PATIENT_VISIT (CREATE_DATE, PERSON_DOMAIN, PAT_CUURENT_DEP)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PAT_VIS_CUSTOM6 on PATIENT_VISIT (CUSTOM6)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PAT_VIS_MODIFY_DATE_IDX on PATIENT_VISIT (MODIFY_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PAT_VIS_RELEVAN_IDX on PATIENT_VISIT (RELEVANCE_ID, RELEVANCE_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PAT_VIS_RELEVAN_NAME_IDX on PATIENT_VISIT (RELEVANCE_NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index VISIT_FLOW_DOMAIN_IDX on PATIENT_VISIT (VISIT_FLOW_ID, VISIT_FLOW_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1344M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index VISIT_HOSPITAL_IDX on PATIENT_VISIT (HOSPITAL_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index VISIT_PATIENT_IDX on PATIENT_VISIT (PATIENT_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index VISIT_PERSON_DOMAIN_IDX on PATIENT_VISIT (PERSON_ID, PERSON_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2368M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index VISIT_PERSON_DO_IDX on PATIENT_VISIT (PERSON_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PATIENT_VISIT
  add constraint PATIENT_VISIT_PK primary key (VISIT_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 400M
    next 1M
    minextents 1
    maxextents unlimited
  );




create table PATIENT_VISIT_HISTORY
(
  history_id                   NUMBER not null,
  global_id                    VARCHAR2(256) not null,
  visit_id                     NUMBER not null,
  person_id                    NUMBER not null,
  person_domain                VARCHAR2(64) not null,
  visit_flow_id                VARCHAR2(64) not null,
  visit_flow_domain            VARCHAR2(64) not null,
  hospital_domain              VARCHAR2(64),
  pat_category                 VARCHAR2(16),
  pat_current_point_of_care    VARCHAR2(256),
  pat_current_room             VARCHAR2(256),
  pat_current_bed              VARCHAR2(256),
  pat_cuurent_dep              VARCHAR2(256),
  pat_current_position_status  VARCHAR2(256),
  pat_current_position_type    VARCHAR2(32),
  pat_current_building         VARCHAR2(64),
  pat_current_floor            VARCHAR2(64),
  pat_cuurent_description      VARCHAR2(256),
  pat_admission_type           VARCHAR2(16),
  pat_admission_number         VARCHAR2(128),
  pat_former_point_of_care     VARCHAR2(256),
  pat_former_room              VARCHAR2(256),
  pat_former_bed               VARCHAR2(256),
  pat_former_dep               VARCHAR2(256),
  pat_former_position_status   VARCHAR2(256),
  pat_former_position_type     VARCHAR2(32),
  pat_former_building          VARCHAR2(64),
  pat_former_floor             VARCHAR2(64),
  pat_former_description       VARCHAR2(256),
  admissions_doctor            VARCHAR2(256),
  admissions_doctor_id         VARCHAR2(32),
  referring_doctor             VARCHAR2(256),
  referring_doctor_id          VARCHAR2(32),
  consultation_doctor          VARCHAR2(256),
  consultation_doctor_id       VARCHAR2(32),
  hospital_service             VARCHAR2(32),
  pat_temp_point_of_care       VARCHAR2(256),
  pat_temp_room                VARCHAR2(256),
  pat_temp_bed                 VARCHAR2(256),
  pat_temp_dep                 VARCHAR2(256),
  pat_temp_position_status     VARCHAR2(256),
  pat_temp_position_type       VARCHAR2(32),
  pat_temp_building            VARCHAR2(64),
  pat_temp_floor               VARCHAR2(64),
  pat_temp_description         VARCHAR2(256),
  pat_admission_test           VARCHAR2(256),
  pat_re_admission             VARCHAR2(32),
  pat_admission_source         VARCHAR2(256),
  pat_ambulatory_status        VARCHAR2(256),
  pat_vip                      VARCHAR2(32),
  pat_admission_doctors        VARCHAR2(256),
  pat_admission_doctors_id     VARCHAR2(32),
  patient_class                VARCHAR2(64),
  patient_id                   VARCHAR2(64),
  pat_financial_class          VARCHAR2(32),
  room_bed_cost_price          VARCHAR2(64),
  courtesy_code                VARCHAR2(16),
  credit_rating                VARCHAR2(32),
  contract_code                VARCHAR2(32),
  contract_create_date         VARCHAR2(32),
  contract_price               VARCHAR2(32),
  contract_time                VARCHAR2(32),
  interest_rate_code           VARCHAR2(32),
  bad_debts                    VARCHAR2(32),
  bad_debts_create_date        VARCHAR2(32),
  bad_debts_code               VARCHAR2(32),
  bad_debts_price              VARCHAR2(32),
  bad_debts__restore_price     VARCHAR2(32),
  pat_account_voided           VARCHAR2(32),
  pat_account_voided_date      VARCHAR2(32),
  pat_discharge_disposition    VARCHAR2(32),
  pat_discharge_location       VARCHAR2(256),
  pat_diet_type                VARCHAR2(64),
  pat_service_agencies         VARCHAR2(64),
  pat_bed_status               VARCHAR2(32),
  pat_account_status           VARCHAR2(32),
  pat_deter_point_of_care      VARCHAR2(256),
  pat_deter_room               VARCHAR2(256),
  pat_deter_bed                VARCHAR2(256),
  pat_deter_dep                VARCHAR2(256),
  pat_deter_position_status    VARCHAR2(256),
  pat_deter_position_type      VARCHAR2(32),
  pat_deter_building           VARCHAR2(64),
  pat_deter_floor              VARCHAR2(64),
  pat_deter_description        VARCHAR2(256),
  pat_for_temp_point_of_care   VARCHAR2(256),
  pat_for_temp_room            VARCHAR2(256),
  pat_for_temp_bed             VARCHAR2(256),
  pat_for_temp_dep             VARCHAR2(256),
  pat_for_temp_position_status VARCHAR2(256),
  pat_for_temp_position_type   VARCHAR2(32),
  pat_for_temp_building        VARCHAR2(64),
  pat_for_temp_floor           VARCHAR2(64),
  pat_for_temp_description     VARCHAR2(256),
  admit_date                   TIMESTAMP(6),
  discharge_date               TIMESTAMP(6),
  pat_difference               VARCHAR2(32),
  pat_total_cost               VARCHAR2(32),
  pat_total_dispatch           VARCHAR2(32),
  pat_total_amount_payable     VARCHAR2(32),
  pat_spare_id                 VARCHAR2(32),
  pat_visit_logo               VARCHAR2(32),
  other_medical_institutions   VARCHAR2(256),
  create_date                  TIMESTAMP(6),
  create_id                    VARCHAR2(256),
  voided_date                  TIMESTAMP(6),
  voided_id                    VARCHAR2(256),
  modify_date                  TIMESTAMP(6),
  modify_id                    VARCHAR2(256),
  custom1                      VARCHAR2(256),
  custom2                      VARCHAR2(256),
  custom3                      VARCHAR2(256),
  custom4                      VARCHAR2(256),
  custom5                      VARCHAR2(256),
  pat_nurse_code               VARCHAR2(32),
  pat_nurse_name               VARCHAR2(32),
  tend                         VARCHAR2(32),
  baby_flag                    VARCHAR2(255),
  admit_weight                 VARCHAR2(255),
  birth_weight                 VARCHAR2(255),
  oper_code                    VARCHAR2(32),
  oper_date                    TIMESTAMP(6),
  prefix                       VARCHAR2(64),
  insurance_type               VARCHAR2(64),
  contact_person               VARCHAR2(64),
  contact_relations            VARCHAR2(64),
  contact_address              VARCHAR2(512),
  contact_phone                VARCHAR2(64),
  pat_category_name            VARCHAR2(64),
  gender_name                  VARCHAR2(64),
  pay_rate                     VARCHAR2(64),
  discharge_name               VARCHAR2(64),
  insurance_name               VARCHAR2(64),
  admission_name               VARCHAR2(64),
  ip_status_name               VARCHAR2(64),
  dificulty_name               VARCHAR2(64),
  admit_way_name               VARCHAR2(64),
  admit_weight_unit            VARCHAR2(64),
  baby_weight_unit             VARCHAR2(64),
  medicinelimitamount          NUMBER,
  sickbedlimitamount           NUMBER,
  examinelimitamount           NUMBER,
  curelimitamount              NUMBER,
  hiup_status                  VARCHAR2(16),
  hiup_error_info              VARCHAR2(4000),
  admission_domain             VARCHAR2(64),
  admission_source_domain      VARCHAR2(64),
  admission_source_name        VARCHAR2(64),
  patient_class_name           VARCHAR2(64),
  patient_class_domain         VARCHAR2(64),
  ip_status_domain             VARCHAR2(64),
  dificulty_domain             VARCHAR2(64),
  discharge_domain             VARCHAR2(64),
  account_status_name          VARCHAR2(64),
  account_status_domain        VARCHAR2(64),
  gender_domain                VARCHAR2(64),
  pat_category_system          VARCHAR2(64),
  mothers_id                   VARCHAR2(64),
  mothers_domain               VARCHAR2(64),
  mothers_flow_id              VARCHAR2(64),
  mothers_flow_domain          VARCHAR2(64),
  mothers_name                 VARCHAR2(64),
  noon_code                    VARCHAR2(16),
  paykind_code                 VARCHAR2(64),
  paykind_name                 VARCHAR2(64),
  schema_no                    VARCHAR2(64),
  order_no                     VARCHAR2(64),
  seeno                        VARCHAR2(64),
  begin_time                   TIMESTAMP(6),
  end_time                     TIMESTAMP(6),
  ynbook                       VARCHAR2(16),
  ynfr                         VARCHAR2(16),
  append_flag                  VARCHAR2(16),
  ynsee                        VARCHAR2(16),
  see_date                     TIMESTAMP(6),
  triage_flag                  VARCHAR2(16),
  triage_opcd                  VARCHAR2(64),
  triage_date                  TIMESTAMP(6),
  see_dpcd                     VARCHAR2(64),
  see_docd                     VARCHAR2(64),
  out_patient_status_a         VARCHAR2(16),
  out_patient_status_b         VARCHAR2(16),
  out_patient_status_c         VARCHAR2(16),
  in_patient_status_a          VARCHAR2(16),
  in_patient_status_b          VARCHAR2(16),
  in_patient_status_c          VARCHAR2(16),
  patient_source_name          VARCHAR2(64),
  old_patient_id               VARCHAR2(64),
  old_patient_domain           VARCHAR2(64),
  old_visit_flow_id            VARCHAR2(64),
  old_visit_flow_domain        VARCHAR2(64),
  old_visit_id                 VARCHAR2(64),
  old_person_id                VARCHAR2(64),
  old_status                   VARCHAR2(64),
  old_info                     VARCHAR2(255),
  isemergency                  VARCHAR2(64),
  pat_ip_times                 VARCHAR2(16),
  diagnose_icd                 VARCHAR2(64),
  diagnose_name                VARCHAR2(64),
  custom6                      VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 389M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table PATIENT_VISIT_HISTORY
  is '病人历史就诊信息';
comment on column PATIENT_VISIT_HISTORY.history_id
  is '主键';
comment on column PATIENT_VISIT_HISTORY.global_id
  is 'EMPI';
comment on column PATIENT_VISIT_HISTORY.visit_id
  is '就诊信息表主键ID';
comment on column PATIENT_VISIT_HISTORY.person_id
  is '患者ID';
comment on column PATIENT_VISIT_HISTORY.person_domain
  is '患者域';
comment on column PATIENT_VISIT_HISTORY.visit_flow_id
  is '就诊流水';
comment on column PATIENT_VISIT_HISTORY.visit_flow_domain
  is '就诊流水域';
comment on column PATIENT_VISIT_HISTORY.hospital_domain
  is '医院域';
comment on column PATIENT_VISIT_HISTORY.pat_category
  is '患者类别';
comment on column PATIENT_VISIT_HISTORY.pat_current_point_of_care
  is '入院护理区域';
comment on column PATIENT_VISIT_HISTORY.pat_current_room
  is '入院病区';
comment on column PATIENT_VISIT_HISTORY.pat_current_bed
  is '入院床位';
comment on column PATIENT_VISIT_HISTORY.pat_cuurent_dep
  is '入院科室';
comment on column PATIENT_VISIT_HISTORY.pat_current_position_status
  is '入院位置的状态';
comment on column PATIENT_VISIT_HISTORY.pat_current_position_type
  is '入院位置的类型';
comment on column PATIENT_VISIT_HISTORY.pat_current_building
  is '入院大楼';
comment on column PATIENT_VISIT_HISTORY.pat_current_floor
  is '入院楼层';
comment on column PATIENT_VISIT_HISTORY.pat_cuurent_description
  is '入院相关描述';
comment on column PATIENT_VISIT_HISTORY.pat_admission_type
  is '允许入院类型';
comment on column PATIENT_VISIT_HISTORY.pat_admission_number
  is '允许入院号码';
comment on column PATIENT_VISIT_HISTORY.pat_former_point_of_care
  is '病人前护理区域';
comment on column PATIENT_VISIT_HISTORY.pat_former_room
  is '病人前病区';
comment on column PATIENT_VISIT_HISTORY.pat_former_bed
  is '病人前床位';
comment on column PATIENT_VISIT_HISTORY.pat_former_dep
  is '病人前科室';
comment on column PATIENT_VISIT_HISTORY.pat_former_position_status
  is '病人前位置状态';
comment on column PATIENT_VISIT_HISTORY.pat_former_position_type
  is '病人前位置类型';
comment on column PATIENT_VISIT_HISTORY.pat_former_building
  is '病人前大楼';
comment on column PATIENT_VISIT_HISTORY.pat_former_floor
  is '病人前楼层';
comment on column PATIENT_VISIT_HISTORY.pat_former_description
  is '病人前相关描述';
comment on column PATIENT_VISIT_HISTORY.admissions_doctor
  is '住院医师';
comment on column PATIENT_VISIT_HISTORY.admissions_doctor_id
  is '住院医师ID';
comment on column PATIENT_VISIT_HISTORY.referring_doctor
  is '主治医师';
comment on column PATIENT_VISIT_HISTORY.referring_doctor_id
  is '主治医师ID';
comment on column PATIENT_VISIT_HISTORY.consultation_doctor
  is '主任医师';
comment on column PATIENT_VISIT_HISTORY.consultation_doctor_id
  is '主任医师ID';
comment on column PATIENT_VISIT_HISTORY.hospital_service
  is '医院服务';
comment on column PATIENT_VISIT_HISTORY.pat_temp_point_of_care
  is '临时护理区域';
comment on column PATIENT_VISIT_HISTORY.pat_temp_room
  is '临时病区';
comment on column PATIENT_VISIT_HISTORY.pat_temp_bed
  is '临时床位';
comment on column PATIENT_VISIT_HISTORY.pat_temp_dep
  is '临时科室';
comment on column PATIENT_VISIT_HISTORY.pat_temp_position_status
  is '临时位置状态';
comment on column PATIENT_VISIT_HISTORY.pat_temp_position_type
  is '临时位置类型';
comment on column PATIENT_VISIT_HISTORY.pat_temp_building
  is '临时大楼';
comment on column PATIENT_VISIT_HISTORY.pat_temp_floor
  is '临时楼层';
comment on column PATIENT_VISIT_HISTORY.pat_temp_description
  is '临时相关描述';
comment on column PATIENT_VISIT_HISTORY.pat_admission_test
  is '实验';
comment on column PATIENT_VISIT_HISTORY.pat_re_admission
  is '住院次数';
comment on column PATIENT_VISIT_HISTORY.pat_admission_source
  is '入院来源编码';
comment on column PATIENT_VISIT_HISTORY.pat_ambulatory_status
  is '走动状态';
comment on column PATIENT_VISIT_HISTORY.pat_vip
  is 'VIP标志';
comment on column PATIENT_VISIT_HISTORY.pat_admission_doctors
  is '门诊接诊医生';
comment on column PATIENT_VISIT_HISTORY.pat_admission_doctors_id
  is '门诊接诊医生ID';
comment on column PATIENT_VISIT_HISTORY.patient_class
  is '患者类型';
comment on column PATIENT_VISIT_HISTORY.patient_id
  is '病人ID';
comment on column PATIENT_VISIT_HISTORY.pat_financial_class
  is '财务类';
comment on column PATIENT_VISIT_HISTORY.room_bed_cost_price
  is '床位价格';
comment on column PATIENT_VISIT_HISTORY.courtesy_code
  is '住院状态';
comment on column PATIENT_VISIT_HISTORY.credit_rating
  is '病例分型';
comment on column PATIENT_VISIT_HISTORY.contract_code
  is '合同编码';
comment on column PATIENT_VISIT_HISTORY.contract_create_date
  is '合同创建日期';
comment on column PATIENT_VISIT_HISTORY.contract_price
  is '合同价格';
comment on column PATIENT_VISIT_HISTORY.contract_time
  is '合同时间';
comment on column PATIENT_VISIT_HISTORY.interest_rate_code
  is '支付比例编码';
comment on column PATIENT_VISIT_HISTORY.bad_debts
  is '坏账';
comment on column PATIENT_VISIT_HISTORY.bad_debts_create_date
  is '坏账创建日期';
comment on column PATIENT_VISIT_HISTORY.bad_debts_code
  is '坏账编码';
comment on column PATIENT_VISIT_HISTORY.bad_debts_price
  is '坏账费用';
comment on column PATIENT_VISIT_HISTORY.bad_debts__restore_price
  is '坏账恢复';
comment on column PATIENT_VISIT_HISTORY.pat_account_voided
  is '账户无效';
comment on column PATIENT_VISIT_HISTORY.pat_account_voided_date
  is '账户无效日期';
comment on column PATIENT_VISIT_HISTORY.pat_discharge_disposition
  is '出院处置编码';
comment on column PATIENT_VISIT_HISTORY.pat_discharge_location
  is '出院位置';
comment on column PATIENT_VISIT_HISTORY.pat_diet_type
  is '饮食类型';
comment on column PATIENT_VISIT_HISTORY.pat_service_agencies
  is '服务机构';
comment on column PATIENT_VISIT_HISTORY.pat_bed_status
  is '床位标志';
comment on column PATIENT_VISIT_HISTORY.pat_account_status
  is '支付方式编码';
comment on column PATIENT_VISIT_HISTORY.pat_deter_point_of_care
  is '取消的护理区域';
comment on column PATIENT_VISIT_HISTORY.pat_deter_room
  is '取消的病区';
comment on column PATIENT_VISIT_HISTORY.pat_deter_bed
  is '取消的床位';
comment on column PATIENT_VISIT_HISTORY.pat_deter_dep
  is '取消时科室';
comment on column PATIENT_VISIT_HISTORY.pat_deter_position_status
  is '取消时位置状态';
comment on column PATIENT_VISIT_HISTORY.pat_deter_position_type
  is '取消时位置类型';
comment on column PATIENT_VISIT_HISTORY.pat_deter_building
  is '取消时大楼';
comment on column PATIENT_VISIT_HISTORY.pat_deter_floor
  is '取消时楼层';
comment on column PATIENT_VISIT_HISTORY.pat_deter_description
  is '取消时相关描述';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_point_of_care
  is '前临时护理区域';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_room
  is '前临时病区';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_bed
  is '前临时床位';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_dep
  is '前临时科室';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_position_status
  is '前临时位置状态';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_position_type
  is '前临时位置类型';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_building
  is '前临时大楼';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_floor
  is '前临时楼层';
comment on column PATIENT_VISIT_HISTORY.pat_for_temp_description
  is '前临时相关描述';
comment on column PATIENT_VISIT_HISTORY.admit_date
  is '入院日期';
comment on column PATIENT_VISIT_HISTORY.discharge_date
  is '出院日期';
comment on column PATIENT_VISIT_HISTORY.pat_difference
  is '差额';
comment on column PATIENT_VISIT_HISTORY.pat_total_cost
  is '总价';
comment on column PATIENT_VISIT_HISTORY.pat_total_dispatch
  is '总调度';
comment on column PATIENT_VISIT_HISTORY.pat_total_amount_payable
  is '应付的总额';
comment on column PATIENT_VISIT_HISTORY.pat_spare_id
  is '多余的ID';
comment on column PATIENT_VISIT_HISTORY.pat_visit_logo
  is '访问标志';
comment on column PATIENT_VISIT_HISTORY.other_medical_institutions
  is '其他卫生机构';
comment on column PATIENT_VISIT_HISTORY.create_date
  is '创建日期';
comment on column PATIENT_VISIT_HISTORY.create_id
  is '创建者';
comment on column PATIENT_VISIT_HISTORY.voided_date
  is '废弃日';
comment on column PATIENT_VISIT_HISTORY.voided_id
  is '废弃标识';
comment on column PATIENT_VISIT_HISTORY.modify_date
  is '修改日';
comment on column PATIENT_VISIT_HISTORY.modify_id
  is '修改者';
comment on column PATIENT_VISIT_HISTORY.custom1
  is '自定义1';
comment on column PATIENT_VISIT_HISTORY.custom2
  is '自定义2';
comment on column PATIENT_VISIT_HISTORY.custom3
  is '自定义3';
comment on column PATIENT_VISIT_HISTORY.custom4
  is '自定义4';
comment on column PATIENT_VISIT_HISTORY.custom5
  is '自定义5';
comment on column PATIENT_VISIT_HISTORY.pat_nurse_code
  is '护士ID';
comment on column PATIENT_VISIT_HISTORY.pat_nurse_name
  is '护士姓名';
comment on column PATIENT_VISIT_HISTORY.tend
  is '护理';
comment on column PATIENT_VISIT_HISTORY.baby_flag
  is '婴儿标志';
comment on column PATIENT_VISIT_HISTORY.admit_weight
  is '入院体重';
comment on column PATIENT_VISIT_HISTORY.birth_weight
  is '出生体重';
comment on column PATIENT_VISIT_HISTORY.oper_code
  is '入院登记操作员';
comment on column PATIENT_VISIT_HISTORY.oper_date
  is '操作日期';
comment on column PATIENT_VISIT_HISTORY.prefix
  is '前缀';
comment on column PATIENT_VISIT_HISTORY.insurance_type
  is '保险类型';
comment on column PATIENT_VISIT_HISTORY.contact_person
  is '联系人';
comment on column PATIENT_VISIT_HISTORY.contact_relations
  is '联系关系';
comment on column PATIENT_VISIT_HISTORY.contact_address
  is '联系地址';
comment on column PATIENT_VISIT_HISTORY.contact_phone
  is '联系电话';
comment on column PATIENT_VISIT_HISTORY.pat_category_name
  is '患者类型名称';
comment on column PATIENT_VISIT_HISTORY.gender_name
  is '性别名称';
comment on column PATIENT_VISIT_HISTORY.pay_rate
  is '付费标准';
comment on column PATIENT_VISIT_HISTORY.discharge_name
  is '离院处置名称';
comment on column PATIENT_VISIT_HISTORY.insurance_name
  is '保险名称';
comment on column PATIENT_VISIT_HISTORY.admission_name
  is '入院时情况名称';
comment on column PATIENT_VISIT_HISTORY.ip_status_name
  is '病人住院状态名称';
comment on column PATIENT_VISIT_HISTORY.dificulty_name
  is '病例分型名称';
comment on column PATIENT_VISIT_HISTORY.admit_way_name
  is '允许进入名称';
comment on column PATIENT_VISIT_HISTORY.admit_weight_unit
  is '入院体重单位';
comment on column PATIENT_VISIT_HISTORY.baby_weight_unit
  is '出院体重单位';
comment on column PATIENT_VISIT_HISTORY.medicinelimitamount
  is '药费限额';
comment on column PATIENT_VISIT_HISTORY.sickbedlimitamount
  is '床位限额';
comment on column PATIENT_VISIT_HISTORY.examinelimitamount
  is '检查限额';
comment on column PATIENT_VISIT_HISTORY.curelimitamount
  is '治疗限额';
comment on column PATIENT_VISIT_HISTORY.hiup_status
  is 'HIUP状态';
comment on column PATIENT_VISIT_HISTORY.hiup_error_info
  is 'HIUP错误信息';
comment on column PATIENT_VISIT_HISTORY.admission_domain
  is '入院时情况编码系统';
comment on column PATIENT_VISIT_HISTORY.admission_source_domain
  is '入院途径编码系统';
comment on column PATIENT_VISIT_HISTORY.admission_source_name
  is '入院途径名称';
comment on column PATIENT_VISIT_HISTORY.patient_class_name
  is '患者类型编码';
comment on column PATIENT_VISIT_HISTORY.patient_class_domain
  is '患者类型编码系统';
comment on column PATIENT_VISIT_HISTORY.ip_status_domain
  is '病人住院状态编码系统';
comment on column PATIENT_VISIT_HISTORY.dificulty_domain
  is '病例分型编码系统';
comment on column PATIENT_VISIT_HISTORY.discharge_domain
  is '离院处置编码系统';
comment on column PATIENT_VISIT_HISTORY.account_status_name
  is '支付方式名称';
comment on column PATIENT_VISIT_HISTORY.account_status_domain
  is '支付方式编码系统';
comment on column PATIENT_VISIT_HISTORY.gender_domain
  is '性别域';
comment on column PATIENT_VISIT_HISTORY.pat_category_system
  is '患者类别编码系统';
comment on column PATIENT_VISIT_HISTORY.mothers_id
  is '母亲ID';
comment on column PATIENT_VISIT_HISTORY.mothers_domain
  is '母亲域';
comment on column PATIENT_VISIT_HISTORY.mothers_flow_id
  is '母亲流水ID';
comment on column PATIENT_VISIT_HISTORY.mothers_flow_domain
  is '母亲流水域';
comment on column PATIENT_VISIT_HISTORY.mothers_name
  is '母亲姓名';
comment on column PATIENT_VISIT_HISTORY.noon_code
  is '后续编码';
comment on column PATIENT_VISIT_HISTORY.paykind_code
  is '支付编码';
comment on column PATIENT_VISIT_HISTORY.paykind_name
  is '支付名称';
comment on column PATIENT_VISIT_HISTORY.schema_no
  is '计划、图解、概要号';
comment on column PATIENT_VISIT_HISTORY.order_no
  is '订单号';
comment on column PATIENT_VISIT_HISTORY.seeno
  is '看编号';
comment on column PATIENT_VISIT_HISTORY.begin_time
  is '开始时间';
comment on column PATIENT_VISIT_HISTORY.end_time
  is '结束时间';
comment on column PATIENT_VISIT_HISTORY.ynbook
  is '年鉴';
comment on column PATIENT_VISIT_HISTORY.ynfr
  is '年鉴故障率';
comment on column PATIENT_VISIT_HISTORY.append_flag
  is '附加标记';
comment on column PATIENT_VISIT_HISTORY.see_date
  is '看日期';
comment on column PATIENT_VISIT_HISTORY.triage_flag
  is '诊断标志';
comment on column PATIENT_VISIT_HISTORY.triage_opcd
  is '诊断OP编号';
comment on column PATIENT_VISIT_HISTORY.triage_date
  is '诊断日期';
comment on column PATIENT_VISIT_HISTORY.see_dpcd
  is '看DP编号';
comment on column PATIENT_VISIT_HISTORY.see_docd
  is '看DO编号';
comment on column PATIENT_VISIT_HISTORY.out_patient_status_a
  is 'OUT患者状态A';
comment on column PATIENT_VISIT_HISTORY.out_patient_status_b
  is 'OUT患者状态B';
comment on column PATIENT_VISIT_HISTORY.out_patient_status_c
  is 'OUT患者状态C';
comment on column PATIENT_VISIT_HISTORY.in_patient_status_a
  is 'OUT患者状态A';
comment on column PATIENT_VISIT_HISTORY.in_patient_status_b
  is 'OUT患者状态B';
comment on column PATIENT_VISIT_HISTORY.in_patient_status_c
  is 'OUT患者状态C';
comment on column PATIENT_VISIT_HISTORY.patient_source_name
  is '患者来源名称';
comment on column PATIENT_VISIT_HISTORY.old_patient_id
  is '旧患者ID';
comment on column PATIENT_VISIT_HISTORY.old_patient_domain
  is '旧患者域ID';
comment on column PATIENT_VISIT_HISTORY.old_visit_flow_id
  is '旧患者流水ID';
comment on column PATIENT_VISIT_HISTORY.old_visit_flow_domain
  is '旧患者流水域ID';
comment on column PATIENT_VISIT_HISTORY.old_visit_id
  is '旧患者就诊ID';
comment on column PATIENT_VISIT_HISTORY.old_person_id
  is '旧person_id';
comment on column PATIENT_VISIT_HISTORY.old_status
  is '旧状态';
comment on column PATIENT_VISIT_HISTORY.old_info
  is '旧信息';
comment on column PATIENT_VISIT_HISTORY.isemergency
  is '紧急情况标识';
comment on column PATIENT_VISIT_HISTORY.pat_ip_times
  is '次数';
comment on column PATIENT_VISIT_HISTORY.diagnose_icd
  is '诊断ICD';
comment on column PATIENT_VISIT_HISTORY.diagnose_name
  is '诊断名称';
comment on column PATIENT_VISIT_HISTORY.custom6
  is '自定义6';
create index IN_PERSONID on PATIENT_VISIT_HISTORY (PERSON_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IN_PERSON_DOMAIN on PATIENT_VISIT_HISTORY (PERSON_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PATIENT_ID on PATIENT_VISIT_HISTORY (PATIENT_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PAT_VIS__HIS_CUSTOM6 on PATIENT_VISIT_HISTORY (CUSTOM6)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PATIENT_VISIT_HISTORY
  add constraint PATIENT_VISIT_HISTORY_PK primary key (HISTORY_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10M
    next 1M
    minextents 1
    maxextents unlimited
  );




create table PERSON
(
  person_id                    NUMBER not null,
  given_name                   VARCHAR2(64),
  middle_name                  VARCHAR2(64),
  family_name                  VARCHAR2(64),
  prefix                       VARCHAR2(50),
  suffix                       VARCHAR2(50),
  name_type_cd                 NUMBER,
  name                         VARCHAR2(64),
  name_code                    VARCHAR2(64),
  name_spell_code              VARCHAR2(64),
  name_wb_code                 VARCHAR2(64),
  date_of_birth                DATE,
  birth_province               VARCHAR2(128),
  birth_city                   VARCHAR2(64),
  birth_county                 VARCHAR2(64),
  birth_zip                    VARCHAR2(32),
  birth_place                  VARCHAR2(255),
  multiple_birth_ind           VARCHAR2(1),
  birth_order                  NUMBER,
  mothers_maiden_name          VARCHAR2(64),
  ssn                          VARCHAR2(64),
  identity_no                  VARCHAR2(64),
  health_card                  VARCHAR2(64),
  citizen_card                 VARCHAR2(64),
  medical_certificate          VARCHAR2(64),
  meicare_person               VARCHAR2(64),
  elder_certificate            VARCHAR2(64),
  opcaseno                     VARCHAR2(64),
  insurance_no                 VARCHAR2(64),
  insurance_type               VARCHAR2(64),
  insurance_name               VARCHAR2(64),
  gender_cd                    NUMBER,
  gender_name                  VARCHAR2(64),
  gender_domain                VARCHAR2(64),
  ethnic_group_cd              NUMBER,
  ethnic_name                  VARCHAR2(64),
  ethnic_domain                VARCHAR2(64),
  race_cd                      NUMBER,
  race_name                    VARCHAR2(64),
  race_domain                  VARCHAR2(64),
  nationality_cd               NUMBER,
  nationality_name             VARCHAR2(64),
  nationality_domain           VARCHAR2(64),
  language_cd                  NUMBER,
  religion_cd                  NUMBER,
  marital_status_cd            NUMBER,
  marital_status_name          VARCHAR2(64),
  marital_domain               VARCHAR2(64),
  degree                       NUMBER,
  degree_name                  VARCHAR2(64),
  degree_domain                VARCHAR2(64),
  email                        VARCHAR2(255),
  home_province                VARCHAR2(64),
  home_city                    VARCHAR2(64),
  home_county                  VARCHAR2(64),
  home_zip                     VARCHAR2(64),
  home_address                 VARCHAR2(512),
  registered_province          VARCHAR2(64),
  registered_city              VARCHAR2(64),
  registered_county            VARCHAR2(64),
  registered_zip               VARCHAR2(32),
  registered_address           VARCHAR2(512),
  native_province              VARCHAR2(64),
  native_city                  VARCHAR2(64),
  work_zip                     VARCHAR2(64),
  work_address                 VARCHAR2(512),
  address1                     VARCHAR2(64),
  postal_code                  VARCHAR2(64),
  address_type_cd              NUMBER,
  address2                     VARCHAR2(64),
  postal_code1                 VARCHAR2(64),
  address1_type_cd             NUMBER,
  city                         VARCHAR2(64),
  state                        VARCHAR2(64),
  country                      VARCHAR2(64),
  country_code                 VARCHAR2(64),
  phone_country_code           VARCHAR2(64),
  phone_area_code              VARCHAR2(64),
  phone_number                 VARCHAR2(64),
  phone_ext                    VARCHAR2(64),
  phone_type_cd                NUMBER,
  phone_country_code1          VARCHAR2(64),
  phone_area_code1             VARCHAR2(64),
  phone_number1                VARCHAR2(64),
  phone_ext1                   VARCHAR2(64),
  phone_type_cd1               NUMBER,
  company                      VARCHAR2(64),
  companycontacts              VARCHAR2(64),
  birthplace_cd                NUMBER,
  workstatus                   NUMBER,
  profession                   NUMBER,
  profession_name              VARCHAR2(64),
  profession_domain            VARCHAR2(64),
  private_number               VARCHAR2(64),
  home_number                  VARCHAR2(64),
  work_number                  VARCHAR2(64),
  guardian_person              VARCHAR2(64),
  vip                          VARCHAR2(64),
  account_identifier_domain_id NUMBER,
  account                      VARCHAR2(255),
  death_ind                    VARCHAR2(1),
  death_time                   DATE,
  date_created                 TIMESTAMP(6) not null,
  creator_id                   NUMBER not null,
  date_changed                 TIMESTAMP(6),
  changed_by_id                NUMBER,
  date_voided                  TIMESTAMP(6),
  voided_by_id                 NUMBER,
  group_number                 VARCHAR2(64),
  blood_type_cd                NUMBER,
  rh_type                      NUMBER,
  empi                         VARCHAR2(64),
  hospital_cd                  NUMBER,
  custom1                      VARCHAR2(255),
  custom2                      VARCHAR2(255),
  custom3                      VARCHAR2(255),
  custom4                      VARCHAR2(255),
  custom5                      VARCHAR2(255),
  custom6                      VARCHAR2(255),
  custom7                      VARCHAR2(255),
  custom8                      VARCHAR2(255),
  custom9                      VARCHAR2(255),
  custom10                     VARCHAR2(255),
  custom11                     VARCHAR2(255),
  custom12                     VARCHAR2(255),
  custom13                     VARCHAR2(255),
  custom14                     VARCHAR2(255),
  custom15                     VARCHAR2(255),
  custom16                     VARCHAR2(255),
  custom17                     VARCHAR2(255),
  custom18                     VARCHAR2(255),
  custom19                     VARCHAR2(255),
  custom20                     VARCHAR2(255),
  custom21                     VARCHAR2(255),
  custom22                     VARCHAR2(255),
  custom23                     VARCHAR2(255),
  custom24                     VARCHAR2(255),
  custom25                     VARCHAR2(255),
  custom26                     VARCHAR2(255),
  custom27                     VARCHAR2(255),
  custom28                     VARCHAR2(255),
  custom29                     VARCHAR2(255),
  custom30                     VARCHAR2(255),
  card_type                    VARCHAR2(16),
  account_locked               VARCHAR2(64),
  account_locked_date          TIMESTAMP(6),
  birth_time                   TIMESTAMP(6),
  home_street                  VARCHAR2(512),
  registered_street            VARCHAR2(512),
  age                          NUMBER,
  allergy_information          VARCHAR2(400),
  certificates_no              VARCHAR2(64),
  certificates_type            VARCHAR2(64),
  blood                        VARCHAR2(16)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 4096M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table PERSON
  is '病人基本信息主表';
comment on column PERSON.person_id
  is '序号';
comment on column PERSON.given_name
  is '姓名';
comment on column PERSON.middle_name
  is '中间名';
comment on column PERSON.family_name
  is '家族名';
comment on column PERSON.prefix
  is '前缀';
comment on column PERSON.suffix
  is '后缀';
comment on column PERSON.name_type_cd
  is '名字类型编码';
comment on column PERSON.name
  is '姓名';
comment on column PERSON.name_code
  is '名字代码';
comment on column PERSON.name_spell_code
  is '中文姓名拼音码';
comment on column PERSON.name_wb_code
  is '中文姓名五笔码';
comment on column PERSON.date_of_birth
  is '出生日期';
comment on column PERSON.birth_province
  is '出生地所在地省';
comment on column PERSON.birth_city
  is '出生地所在地市';
comment on column PERSON.birth_county
  is '出生地所在区县';
comment on column PERSON.birth_zip
  is '出生地邮编';
comment on column PERSON.birth_place
  is '出生地';
comment on column PERSON.multiple_birth_ind
  is '多胞胎';
comment on column PERSON.birth_order
  is '出生次序';
comment on column PERSON.mothers_maiden_name
  is '母亲娘家姓';
comment on column PERSON.ssn
  is '社会保险号';
comment on column PERSON.identity_no
  is '身份证号';
comment on column PERSON.health_card
  is '健康卡号';
comment on column PERSON.citizen_card
  is '市民卡号';
comment on column PERSON.medical_certificate
  is '医疗证号';
comment on column PERSON.meicare_person
  is '医保个人编号';
comment on column PERSON.elder_certificate
  is '老人证号';
comment on column PERSON.opcaseno
  is '病历号';
comment on column PERSON.insurance_no
  is '医疗保险号';
comment on column PERSON.insurance_type
  is '医疗保险类型';
comment on column PERSON.insurance_name
  is '医疗保险名称';
comment on column PERSON.gender_cd
  is '性别编码';
comment on column PERSON.gender_name
  is '性别编码名';
comment on column PERSON.gender_domain
  is '性别编码系统';
comment on column PERSON.ethnic_group_cd
  is '民族编码';
comment on column PERSON.ethnic_name
  is '民族名称';
comment on column PERSON.ethnic_domain
  is '民族编码系统';
comment on column PERSON.race_cd
  is '种族编码';
comment on column PERSON.race_name
  is '种族名称';
comment on column PERSON.race_domain
  is '种族编码系统';
comment on column PERSON.nationality_cd
  is '国籍编码';
comment on column PERSON.nationality_name
  is '国籍名称';
comment on column PERSON.nationality_domain
  is '国籍编码系统';
comment on column PERSON.language_cd
  is '语言编码';
comment on column PERSON.religion_cd
  is '宗教编码';
comment on column PERSON.marital_status_cd
  is '婚姻状态编码';
comment on column PERSON.marital_status_name
  is '婚姻状态名称';
comment on column PERSON.marital_domain
  is '婚姻状态编码系统';
comment on column PERSON.degree
  is '学历编码';
comment on column PERSON.degree_name
  is '学历名称';
comment on column PERSON.degree_domain
  is '学历编码系统';
comment on column PERSON.email
  is '邮件地址';
comment on column PERSON.home_province
  is '居住地所在地省';
comment on column PERSON.home_city
  is '居住地所在地市';
comment on column PERSON.home_county
  is '居住地所在区县';
comment on column PERSON.home_zip
  is '居住地邮编';
comment on column PERSON.home_address
  is '居住地地址';
comment on column PERSON.registered_province
  is '户口所在地省';
comment on column PERSON.registered_city
  is '户口所在地市';
comment on column PERSON.registered_county
  is '户口所在地区县';
comment on column PERSON.registered_zip
  is '户口所在地邮编';
comment on column PERSON.registered_address
  is '户口所在地之';
comment on column PERSON.native_province
  is '籍贯所在地省';
comment on column PERSON.native_city
  is '籍贯所在地市';
comment on column PERSON.work_zip
  is '工作邮编';
comment on column PERSON.work_address
  is '单位地址';
comment on column PERSON.address1
  is '地址1';
comment on column PERSON.postal_code
  is '邮政编码1';
comment on column PERSON.address_type_cd
  is '地址类型编码1';
comment on column PERSON.address2
  is '地址2';
comment on column PERSON.postal_code1
  is '邮政编码2';
comment on column PERSON.address1_type_cd
  is '地址类型编码2';
comment on column PERSON.city
  is '城市';
comment on column PERSON.state
  is '省';
comment on column PERSON.country
  is '国家';
comment on column PERSON.country_code
  is '国家编码';
comment on column PERSON.phone_country_code
  is '电话号码国家代码';
comment on column PERSON.phone_area_code
  is '国家号码';
comment on column PERSON.phone_number
  is '电话号码';
comment on column PERSON.phone_ext
  is '分机号';
comment on column PERSON.phone_type_cd
  is '电话类型编码';
comment on column PERSON.phone_country_code1
  is '电话号码国家代码1';
comment on column PERSON.phone_area_code1
  is '国家号码1';
comment on column PERSON.phone_number1
  is '电话号码1';
comment on column PERSON.phone_ext1
  is '分机号1';
comment on column PERSON.phone_type_cd1
  is '电话类型编码1';
comment on column PERSON.company
  is '公司';
comment on column PERSON.companycontacts
  is '公司联系方式';
comment on column PERSON.birthplace_cd
  is '出生地编码';
comment on column PERSON.workstatus
  is '工作状态';
comment on column PERSON.profession
  is '职业编码';
comment on column PERSON.profession_name
  is '职业名称';
comment on column PERSON.profession_domain
  is '职业编码系统';
comment on column PERSON.private_number
  is '私人电话';
comment on column PERSON.home_number
  is '家庭电话';
comment on column PERSON.work_number
  is '单位电话';
comment on column PERSON.guardian_person
  is '监护人';
comment on column PERSON.vip
  is '保密';
comment on column PERSON.account_identifier_domain_id
  is '账户域ID';
comment on column PERSON.account
  is '账户';
comment on column PERSON.death_ind
  is '死亡标识';
comment on column PERSON.death_time
  is '死亡事件';
comment on column PERSON.date_created
  is '创建日期';
comment on column PERSON.creator_id
  is '创建者';
comment on column PERSON.date_changed
  is '修改日期';
comment on column PERSON.changed_by_id
  is '修改人';
comment on column PERSON.date_voided
  is '废弃日期';
comment on column PERSON.voided_by_id
  is '废弃标识';
comment on column PERSON.group_number
  is '组号';
comment on column PERSON.blood_type_cd
  is '血型编码';
comment on column PERSON.rh_type
  is 'RH类型';
comment on column PERSON.empi
  is 'EMPI';
comment on column PERSON.hospital_cd
  is '医院CD';
comment on column PERSON.custom1
  is '自定义1';
comment on column PERSON.custom2
  is '流水域名字';
comment on column PERSON.custom3
  is '流水域ID';
comment on column PERSON.custom4
  is '流水域类型';
comment on column PERSON.custom5
  is '自定义5';
comment on column PERSON.custom6
  is '流水ID^流水域ID';
comment on column PERSON.custom7
  is '市民卡号';
comment on column PERSON.custom8
  is '医疗证号';
comment on column PERSON.custom9
  is '医保个人编号';
comment on column PERSON.custom10
  is '机构名称';
comment on column PERSON.custom11
  is '机构域ID';
comment on column PERSON.custom12
  is '机构类型';
comment on column PERSON.custom13
  is '老人证号';
comment on column PERSON.custom14
  is '病历号';
comment on column PERSON.custom15
  is 'HIS就诊号';
comment on column PERSON.custom16
  is '机构内病人ID';
comment on column PERSON.custom17
  is '社会保险号';
comment on column PERSON.custom18
  is '健康卡';
comment on column PERSON.custom19
  is 'HIS流水号';
comment on column PERSON.custom20
  is '关联ID^关联域';
comment on column PERSON.custom21
  is '关联ID^关联域';
comment on column PERSON.custom22
  is '自定义22';
comment on column PERSON.custom23
  is '自定义23';
comment on column PERSON.custom24
  is '病案号';
comment on column PERSON.custom25
  is '自定义25';
comment on column PERSON.custom26
  is '自定义26';
comment on column PERSON.custom27
  is '自定义27';
comment on column PERSON.custom28
  is '自定义28';
comment on column PERSON.custom29
  is '自定义29';
comment on column PERSON.custom30
  is '病人办卡方式';
comment on column PERSON.card_type
  is '卡类型';
comment on column PERSON.account_locked
  is '账户锁定';
comment on column PERSON.account_locked_date
  is '账户锁定时间';
comment on column PERSON.birth_time
  is '出生时间';
comment on column PERSON.home_street
  is '居住街道';
comment on column PERSON.registered_street
  is '户口街道';
comment on column PERSON.age
  is '年龄';
comment on column PERSON.allergy_information
  is '过敏信息';
comment on column PERSON.certificates_no
  is '证件号';
comment on column PERSON.certificates_type
  is '证件类型';
comment on column PERSON.blood
  is '血型';
create index SYSTEM.CUSTOM11_16_IDX on PERSON (CUSTOM16, CUSTOM11)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM11_IDX on PERSON (CUSTOM11)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM15_IDX on PERSON (CUSTOM15)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 568M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM16_IDX on PERSON (CUSTOM16)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM19_IDX on PERSON (CUSTOM19)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1280M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index CUSTOM6_IDX on PERSON (CUSTOM6)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 400M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IND_NAME on PERSON (NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IN_CUSTOM10 on PERSON (CUSTOM10)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IN_CUSTOM11_16 on PERSON (CUSTOM11, CUSTOM16)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index MEDICAL_CERTIFICATE_IDX on PERSON (MEDICAL_CERTIFICATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_CUSTOM1_IDX on PERSON (CUSTOM1)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_CUSTOM29_IDX on PERSON (CUSTOM29)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_DATEOFBIRTH_IDX on PERSON (DATE_OF_BIRTH)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 160M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_DATEVOIDED_IDX on PERSON (DATE_VOIDED)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_DATE_CHANGED_IDX on PERSON (DATE_CHANGED)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_DATE_CREATED_IDX on PERSON (DATE_CREATED)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_EMPI_IDX on PERSON (EMPI)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_GIVENNAME_IDX on PERSON (GIVEN_NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 144M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_IDENTITYNO_IDX on PERSON (IDENTITY_NO)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 15M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_INSURANCENO_IDX on PERSON (INSURANCE_NO)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 832K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_SSN_IDX on PERSON (SSN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PERSON
  add constraint PERSON_PK primary key (PERSON_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PERSON
  add constraint PERSON_CUSTOM16_11 unique (CUSTOM16, CUSTOM11)
  novalidate;




insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (1, 'CUSTOM6', 1, '系统流水号固定', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (2, 'CUSTOM7', 2, 'citizenCard', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (3, 'CUSTOM8', 3, 'medicalCertificate', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (4, 'CUSTOM9', 4, 'meicarePerson', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (5, 'CUSTOM13', 5, 'elderCertificate', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (6, 'CUSTOM14', 6, 'opcaseno', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (7, 'CUSTOM15', 7, 'HIS就诊流水号固定', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (8, 'CUSTOM18', 8, 'healthCard', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (9, 'CUSTOM19', 9, 'custom23', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (10, 'CUSTOM20', 10, 'custom24', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (11, 'CUSTOM24', 11, '病案号', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (12, 'CUSTOM22', 12, '宣武PID段', null, null);
insert into EXTENDFORPERSON (extend_id, extendfieldname, pid4_fields, custom1, custom2, custom3)
values (13, 'CUSTOM25', 13, '备用号2', null, null);
commit;


insert into FILE_SYSTEM (file_system_id, file_path, status, create_date, update_date)
values (1, 'c:\test', '0', '2017-11-15 14:33:34', '2017-11-15 14:33:34');
commit;



create table PERSONIDENTIFIER
(
  registrypatientid  VARCHAR2(255) not null,
  assigningauthority VARCHAR2(255),
  patientid          VARCHAR2(255),
  deleted            VARCHAR2(1),
  merged             VARCHAR2(1),
  survivingpatientid VARCHAR2(255),
  create_date        TIMESTAMP(6)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 864M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PERSONIDENTIFIER.registrypatientid
  is '主键';
comment on column PERSONIDENTIFIER.assigningauthority
  is '患者域和域类型';
comment on column PERSONIDENTIFIER.patientid
  is 'EMPI';
comment on column PERSONIDENTIFIER.deleted
  is '删除标识';
comment on column PERSONIDENTIFIER.merged
  is '合并标识';
comment on column PERSONIDENTIFIER.survivingpatientid
  is '存在的患者ID';
comment on column PERSONIDENTIFIER.create_date
  is '创建日期';
alter table PERSONIDENTIFIER
  add constraint PERSONIDENTIFIER_PK primary key (REGISTRYPATIENTID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 414M
    next 1M
    minextents 1
    maxextents unlimited
  );



create table PERSON_IDENTIFIER
(
  person_identifier_id NUMBER not null,
  date_created         TIMESTAMP(6) not null,
  date_voided          TIMESTAMP(6),
  identifier           VARCHAR2(255) not null,
  person_id            NUMBER not null,
  creator_id           NUMBER not null,
  identifier_domain_id NUMBER not null,
  voided_by_id         NUMBER,
  custom1              VARCHAR2(255),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 1569M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PERSON_IDENTIFIER.person_identifier_id
  is '主键';
comment on column PERSON_IDENTIFIER.date_created
  is '创建日期';
comment on column PERSON_IDENTIFIER.date_voided
  is '废弃日期';
comment on column PERSON_IDENTIFIER.identifier
  is '标识符';
comment on column PERSON_IDENTIFIER.person_id
  is 'person_id';
comment on column PERSON_IDENTIFIER.creator_id
  is '创建ID';
comment on column PERSON_IDENTIFIER.identifier_domain_id
  is '域ID';
comment on column PERSON_IDENTIFIER.voided_by_id
  is '废弃标识';
comment on column PERSON_IDENTIFIER.custom1
  is '自定义1';
comment on column PERSON_IDENTIFIER.custom2
  is '自定义2';
comment on column PERSON_IDENTIFIER.custom3
  is '自定义3';
comment on column PERSON_IDENTIFIER.custom4
  is '自定义4';
comment on column PERSON_IDENTIFIER.custom5
  is '自定义5';
create index PERSON_DATE_VOIDED_IDX on PERSON_IDENTIFIER (DATE_VOIDED)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_IDENTIFIER_IDX on PERSON_IDENTIFIER (IDENTIFIER)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 512M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_IDENTIFIER_ID_IDX on PERSON_IDENTIFIER (IDENTIFIER_DOMAIN_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 400M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_IDEN_CUSTOM1 on PERSON_IDENTIFIER (CUSTOM1)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_ID_IDX on PERSON_IDENTIFIER (PERSON_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PERSON_IDENTIFIER
  add constraint PERSON_IDENTIFIER_PK primary key (PERSON_IDENTIFIER_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 247M
    next 1M
    minextents 1
    maxextents unlimited
  );



create table PERSON_IN_OUT
(
  person_in_out_id   NUMBER not null,
  patient_id         VARCHAR2(64),
  patient_domain     VARCHAR2(64),
  visit_flow_id      VARCHAR2(64),
  visit_flow_domain  VARCHAR2(64),
  name               VARCHAR2(64),
  date_of_birth      TIMESTAMP(6),
  sex                VARCHAR2(8),
  identity_no        VARCHAR2(64),
  insurance_no       VARCHAR2(64),
  insurance_type     VARCHAR2(16),
  current_bed        VARCHAR2(16),
  current_dep        VARCHAR2(16),
  current_room       VARCHAR2(16),
  old_content        VARCHAR2(512),
  new_content        VARCHAR2(512),
  change_type        VARCHAR2(32),
  change_description VARCHAR2(512),
  oper_id            VARCHAR2(32),
  date_created       TIMESTAMP(6),
  custom1            VARCHAR2(64),
  custom2            VARCHAR2(64),
  custom3            VARCHAR2(64),
  custom4            VARCHAR2(64),
  custom5            VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table PERSON_IN_OUT
  is '病人出入转流水记录';
comment on column PERSON_IN_OUT.person_in_out_id
  is '出入转流水记录PK';
comment on column PERSON_IN_OUT.patient_id
  is '病人ID';
comment on column PERSON_IN_OUT.patient_domain
  is '病人域ID';
comment on column PERSON_IN_OUT.visit_flow_id
  is '病人流水ID';
comment on column PERSON_IN_OUT.visit_flow_domain
  is '病人流水域ID';
comment on column PERSON_IN_OUT.name
  is '病人姓名';
comment on column PERSON_IN_OUT.date_of_birth
  is '病人生日';
comment on column PERSON_IN_OUT.sex
  is '病人性别';
comment on column PERSON_IN_OUT.identity_no
  is '身份证号';
comment on column PERSON_IN_OUT.insurance_no
  is '医保卡号';
comment on column PERSON_IN_OUT.insurance_type
  is '医保卡类型';
comment on column PERSON_IN_OUT.current_bed
  is '当前所在床位';
comment on column PERSON_IN_OUT.current_dep
  is '当前所在科室';
comment on column PERSON_IN_OUT.current_room
  is '当前所在病区';
comment on column PERSON_IN_OUT.old_content
  is '原内容';
comment on column PERSON_IN_OUT.new_content
  is '新内容';
comment on column PERSON_IN_OUT.change_type
  is '变更类型';
comment on column PERSON_IN_OUT.change_description
  is '变更描述';
comment on column PERSON_IN_OUT.oper_id
  is '操作员';
comment on column PERSON_IN_OUT.date_created
  is '记录日期';
comment on column PERSON_IN_OUT.custom1
  is '备注1';
comment on column PERSON_IN_OUT.custom2
  is '备注2';
comment on column PERSON_IN_OUT.custom3
  is '备注3';
comment on column PERSON_IN_OUT.custom4
  is '备注4';
comment on column PERSON_IN_OUT.custom5
  is '备注5';
create index PERSON_IN_IDX on PERSON_IN_OUT (CHANGE_TYPE, DATE_CREATED, CURRENT_DEP)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PERSON_IN_OUT
  add constraint PERSON_IN_OUT_ID_PK primary key (PERSON_IN_OUT_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table PERSON_LINK
(
  person_link_id NUMBER not null,
  date_created   TIMESTAMP(6) not null,
  weight         NUMBER not null,
  rh_person_id   NUMBER not null,
  creator_id     NUMBER not null,
  lh_person_id   NUMBER not null,
  custom1        VARCHAR2(255),
  custom2        VARCHAR2(255),
  custom3        VARCHAR2(255),
  custom4        VARCHAR2(255),
  custom5        VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 128M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PERSON_LINK.person_link_id
  is '主键';
comment on column PERSON_LINK.date_created
  is '创建日期';
comment on column PERSON_LINK.weight
  is '重量';
comment on column PERSON_LINK.rh_person_id
  is 'RH患者ID';
comment on column PERSON_LINK.creator_id
  is '创建者ID';
comment on column PERSON_LINK.lh_person_id
  is 'LH患者ID';
comment on column PERSON_LINK.custom1
  is '自定义1';
comment on column PERSON_LINK.custom2
  is '自定义2';
comment on column PERSON_LINK.custom3
  is '自定义3';
comment on column PERSON_LINK.custom4
  is '自定义4';
comment on column PERSON_LINK.custom5
  is '自定义5';
create index PERSON_LINK_LH_IDX on PERSON_LINK (LH_PERSON_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 48M
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_LINK_RH_IDX on PERSON_LINK (RH_PERSON_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 53M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PERSON_LINK
  add constraint PERSON_LINK_PK primary key (PERSON_LINK_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 80M
    next 1M
    minextents 1
    maxextents unlimited
  );



create table PERSON_LINK_CANCEL
(
  person_link_cancel_id NUMBER not null,
  empi                  VARCHAR2(64) not null,
  person_id             NUMBER not null,
  date_created          TIMESTAMP(6)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PERSON_LINK_CANCEL.person_link_cancel_id
  is '主键';
comment on column PERSON_LINK_CANCEL.empi
  is 'EMPI';
comment on column PERSON_LINK_CANCEL.person_id
  is 'person表主键';
comment on column PERSON_LINK_CANCEL.date_created
  is '创建日期';
alter table PERSON_LINK_CANCEL
  add constraint PERSON_LINK_CANCEL_PK primary key (PERSON_LINK_CANCEL_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table PERSON_LINK_REVIEW
(
  person_link_review_id NUMBER not null,
  rh_person_id          NUMBER not null,
  lh_person_id          NUMBER not null,
  date_created          TIMESTAMP(6) not null,
  weight                NUMBER,
  creator_id            NUMBER not null,
  reviewer_id           NUMBER,
  date_reviewed         NUMBER,
  custom1               VARCHAR2(255),
  custom2               VARCHAR2(255),
  custom3               VARCHAR2(255),
  custom4               VARCHAR2(255),
  custom5               VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PERSON_LINK_REVIEW.person_link_review_id
  is '主键';
comment on column PERSON_LINK_REVIEW.rh_person_id
  is 'RH患者ID';
comment on column PERSON_LINK_REVIEW.lh_person_id
  is 'LH患者ID';
comment on column PERSON_LINK_REVIEW.date_created
  is '创建日期';
comment on column PERSON_LINK_REVIEW.weight
  is '重量';
comment on column PERSON_LINK_REVIEW.creator_id
  is '创建者ID';
comment on column PERSON_LINK_REVIEW.reviewer_id
  is '评论者ID';
comment on column PERSON_LINK_REVIEW.date_reviewed
  is '复审日期';
comment on column PERSON_LINK_REVIEW.custom1
  is '自定义1';
comment on column PERSON_LINK_REVIEW.custom2
  is '自定义2';
comment on column PERSON_LINK_REVIEW.custom3
  is '自定义3';
comment on column PERSON_LINK_REVIEW.custom4
  is '自定义4';
comment on column PERSON_LINK_REVIEW.custom5
  is '自定义5';
alter table PERSON_LINK_REVIEW
  add constraint PERSON_LINK_REVIEW_PK primary key (PERSON_LINK_REVIEW_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table PERSON_REGISTER_LOG
(
  person_register_log_id   NUMBER not null,
  msg_id                   VARCHAR2(255) not null,
  patient_id               VARCHAR2(64),
  patient_domain_id        VARCHAR2(64),
  operate_type             VARCHAR2(16),
  status                   VARCHAR2(16),
  status_name              VARCHAR2(64),
  error_info               VARCHAR2(4000),
  app_code                 VARCHAR2(32),
  app_name                 VARCHAR2(32),
  function_code            VARCHAR2(32),
  create_date              VARCHAR2(32),
  update_date              VARCHAR2(32),
  relevance_id_01          VARCHAR2(64),
  relevance_id_02          VARCHAR2(64),
  relevance_id_03          VARCHAR2(64),
  relevance_id_04          VARCHAR2(64),
  relevance_id_05          VARCHAR2(64),
  topic_name               VARCHAR2(64),
  retry_status             VARCHAR2(16),
  retry_count              NUMBER default 0,
  file_system_id           NUMBER,
  message_path             VARCHAR2(128),
  retry_type               VARCHAR2(16),
  retry_target             VARCHAR2(255),
  relevance_msg_id         VARCHAR2(255),
  business_create_date     VARCHAR2(32),
  nonstandard_process_type VARCHAR2(16),
  name                     VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PERSON_REGISTER_LOG.person_register_log_id
  is '患者注册日志表自增长主键';
comment on column PERSON_REGISTER_LOG.msg_id
  is '患者注册消息唯一标识';
comment on column PERSON_REGISTER_LOG.patient_id
  is '患者ID';
comment on column PERSON_REGISTER_LOG.patient_domain_id
  is '患者域';
comment on column PERSON_REGISTER_LOG.operate_type
  is '操作类型';
comment on column PERSON_REGISTER_LOG.status
  is '状态';
comment on column PERSON_REGISTER_LOG.status_name
  is '状态名称';
comment on column PERSON_REGISTER_LOG.error_info
  is '错误信息';
comment on column PERSON_REGISTER_LOG.app_code
  is '应用code';
comment on column PERSON_REGISTER_LOG.app_name
  is '显示节点名称';
comment on column PERSON_REGISTER_LOG.function_code
  is '匹配节点';
comment on column PERSON_REGISTER_LOG.create_date
  is '患者注册创建时间';
comment on column PERSON_REGISTER_LOG.update_date
  is '患者注册更新时间';
comment on column PERSON_REGISTER_LOG.relevance_id_01
  is '患者关联ID01';
comment on column PERSON_REGISTER_LOG.relevance_id_02
  is '患者关联ID02';
comment on column PERSON_REGISTER_LOG.relevance_id_03
  is '患者关联ID03';
comment on column PERSON_REGISTER_LOG.relevance_id_04
  is '患者关联ID04';
comment on column PERSON_REGISTER_LOG.relevance_id_05
  is '患者关联ID05';
comment on column PERSON_REGISTER_LOG.topic_name
  is '队列名';
comment on column PERSON_REGISTER_LOG.retry_status
  is '重试状态';
comment on column PERSON_REGISTER_LOG.retry_count
  is '重试次数';
comment on column PERSON_REGISTER_LOG.file_system_id
  is '文件盘符路径关联ID';
comment on column PERSON_REGISTER_LOG.message_path
  is '消息文件夹相关路径';
comment on column PERSON_REGISTER_LOG.retry_type
  is '重试类型';
comment on column PERSON_REGISTER_LOG.retry_target
  is '重试目标';
comment on column PERSON_REGISTER_LOG.relevance_msg_id
  is '网关或第三方的消息标识';
comment on column PERSON_REGISTER_LOG.business_create_date
  is '医院业务申请创建时间';
comment on column PERSON_REGISTER_LOG.nonstandard_process_type
  is '非标准流程数据库操作类型';
comment on column PERSON_REGISTER_LOG.name
  is '患者姓名';
create index PERSON_BUSINESS_DATE_IDX on PERSON_REGISTER_LOG (BUSINESS_CREATE_DATE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_FUNCTION_CODE_IDX on PERSON_REGISTER_LOG (FUNCTION_CODE)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_MSG_ID_IDX on PERSON_REGISTER_LOG (MSG_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index PERSON_STATUS_IDX on PERSON_REGISTER_LOG (STATUS)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table PERSON_REGISTER_LOG
  add primary key (PERSON_REGISTER_LOG_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table REG_ERROR_EVENT
(
  reg_error_id      NUMBER not null,
  base_id           VARCHAR2(32),
  base_domain       VARCHAR2(32),
  base_name         VARCHAR2(128),
  reg_id            VARCHAR2(32),
  reg_domain        VARCHAR2(32),
  reg_name          VARCHAR2(128),
  error_type        VARCHAR2(16),
  error_description VARCHAR2(256),
  error_date        TIMESTAMP(6),
  handle_status     VARCHAR2(16),
  handle_date       TIMESTAMP(6),
  notify_status     VARCHAR2(16),
  notify_date       TIMESTAMP(6),
  custom1           VARCHAR2(64),
  custom2           VARCHAR2(64),
  custom3           VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table REG_ERROR_EVENT
  is '不同名错误事件记录';
comment on column REG_ERROR_EVENT.reg_error_id
  is '主键';
comment on column REG_ERROR_EVENT.base_id
  is '对比用的ID,例如:HIS号';
comment on column REG_ERROR_EVENT.base_domain
  is '对比用的域ID，例如：HIS域';
comment on column REG_ERROR_EVENT.base_name
  is '对应对比用ID的姓名';
comment on column REG_ERROR_EVENT.reg_id
  is '注册ID';
comment on column REG_ERROR_EVENT.reg_domain
  is '注册ID的域';
comment on column REG_ERROR_EVENT.reg_name
  is '注册ID对应的姓名';
comment on column REG_ERROR_EVENT.error_type
  is '错误类型';
comment on column REG_ERROR_EVENT.error_description
  is '错误类型描述';
comment on column REG_ERROR_EVENT.error_date
  is '错误产生日期';
comment on column REG_ERROR_EVENT.handle_status
  is '处理状态';
comment on column REG_ERROR_EVENT.handle_date
  is '处理日期';
comment on column REG_ERROR_EVENT.notify_status
  is '通知状态';
comment on column REG_ERROR_EVENT.notify_date
  is '通知日期';
comment on column REG_ERROR_EVENT.custom1
  is '自定义1';
comment on column REG_ERROR_EVENT.custom2
  is '自定义2';
comment on column REG_ERROR_EVENT.custom3
  is '自定义3';
alter table REG_ERROR_EVENT
  add constraint REG_EVENT_PK primary key (REG_ERROR_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table RELEVANCE_AND_ID
(
  relevance_and_id NUMBER not null,
  reg_id           VARCHAR2(64),
  reg_domain       VARCHAR2(64),
  reg_name         VARCHAR2(64),
  relevance_id     VARCHAR2(64),
  relevance_domain VARCHAR2(64),
  relevance_name   VARCHAR2(64),
  empi             VARCHAR2(64),
  date_created     TIMESTAMP(6),
  custom1          VARCHAR2(64),
  custom2          VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table RELEVANCE_AND_ID
  is '关联ID记录';
comment on column RELEVANCE_AND_ID.relevance_and_id
  is '关联ID记录PK';
comment on column RELEVANCE_AND_ID.reg_id
  is '注册病人ID';
comment on column RELEVANCE_AND_ID.reg_domain
  is '注册病人域ID';
comment on column RELEVANCE_AND_ID.reg_name
  is '注册病人姓名';
comment on column RELEVANCE_AND_ID.relevance_id
  is '注册病人关联ID';
comment on column RELEVANCE_AND_ID.relevance_domain
  is '注册病人关联域ID';
comment on column RELEVANCE_AND_ID.relevance_name
  is '注册病人关联ID姓名';
comment on column RELEVANCE_AND_ID.empi
  is 'EMPI';
comment on column RELEVANCE_AND_ID.date_created
  is '创建日期';
comment on column RELEVANCE_AND_ID.custom1
  is '备注1';
comment on column RELEVANCE_AND_ID.custom2
  is '备注2';
create index RELEVANCE_DOMAIN_IDX on RELEVANCE_AND_ID (REG_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RELEVANCE_ID1_IDX on RELEVANCE_AND_ID (RELEVANCE_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RELEVANCE_ID_CUSTOM1 on RELEVANCE_AND_ID (CUSTOM1)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RELEVANCE_ID_DOMAIN_IDX on RELEVANCE_AND_ID (REG_ID, REG_DOMAIN)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RELEVANCE_ID_IDX on RELEVANCE_AND_ID (REG_ID)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RELEVANCE_NAME_IDX on RELEVANCE_AND_ID (REG_NAME)
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table RELEVANCE_AND_ID
  add constraint RELEVANCE_AND_ID_PK primary key (RELEVANCE_AND_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table ROLE
(
  id          NUMBER not null,
  name        VARCHAR2(20),
  description VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ROLE.id
  is 'ID';
comment on column ROLE.name
  is '姓名';
comment on column ROLE.description
  is '描述';
alter table ROLE
  add constraint ROLE_PK primary key (ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table TMPL
(
  empi        VARCHAR2(64),
  given_name  VARCHAR2(64),
  identity_no VARCHAR2(64)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column TMPL.empi
  is 'EMPI';
comment on column TMPL.given_name
  is '姓名';
comment on column TMPL.identity_no
  is '身份证';


create table USER_FILE
(
  user_file_id NUMBER not null,
  user_id      NUMBER not null,
  name         VARCHAR2(255) not null,
  filename     VARCHAR2(255) not null,
  imported_ind CHAR(1) default 'N',
  date_created TIMESTAMP(6) not null,
  custom1      VARCHAR2(255),
  custom2      VARCHAR2(255),
  custom3      VARCHAR2(255),
  custom4      VARCHAR2(255),
  custom5      VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column USER_FILE.user_file_id
  is '用户文件ID';
comment on column USER_FILE.user_id
  is '用户ID';
comment on column USER_FILE.name
  is '姓名';
comment on column USER_FILE.filename
  is '文件名称';
comment on column USER_FILE.imported_ind
  is '输入 默认N';
comment on column USER_FILE.date_created
  is '创建时间';
comment on column USER_FILE.custom1
  is '自定义1';
comment on column USER_FILE.custom2
  is '自定义2';
comment on column USER_FILE.custom3
  is '自定义3';
comment on column USER_FILE.custom4
  is '自定义4';
comment on column USER_FILE.custom5
  is '自定义5';
alter table USER_FILE
  add constraint USER_FILE_PK primary key (USER_FILE_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table USER_ROLE
(
  user_id NUMBER not null,
  role_id NUMBER not null,
  custom1 VARCHAR2(255),
  custom2 VARCHAR2(255),
  custom3 VARCHAR2(255),
  custom4 VARCHAR2(255),
  custom5 VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column USER_ROLE.user_id
  is '用户ID';
comment on column USER_ROLE.role_id
  is '权限ID';
comment on column USER_ROLE.custom1
  is '自定义1';
comment on column USER_ROLE.custom2
  is '自定义2';
comment on column USER_ROLE.custom3
  is '自定义3';
comment on column USER_ROLE.custom4
  is '自定义4';
comment on column USER_ROLE.custom5
  is '自定义5';
alter table USER_ROLE
  add constraint USER_ROLE_PK primary key (USER_ID, ROLE_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table USER_SESSION
(
  session_id   NUMBER not null,
  date_created TIMESTAMP(6) not null,
  session_key  VARCHAR2(255),
  user_id      NUMBER not null,
  custom1      VARCHAR2(255),
  custom2      VARCHAR2(255),
  custom3      VARCHAR2(255),
  custom4      VARCHAR2(255),
  custom5      VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 27M
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column USER_SESSION.session_id
  is '全局ID主键';
comment on column USER_SESSION.date_created
  is '创建日期';
comment on column USER_SESSION.session_key
  is '全局KEY值';
comment on column USER_SESSION.user_id
  is '用户ID';
comment on column USER_SESSION.custom1
  is '自定义1';
comment on column USER_SESSION.custom2
  is '自定义2';
comment on column USER_SESSION.custom3
  is '自定义3';
comment on column USER_SESSION.custom4
  is '自定义4';
comment on column USER_SESSION.custom5
  is '自定义5';
alter table USER_SESSION
  add constraint USER_SESSION_PK primary key (SESSION_ID)
  using index
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 9M
    next 1M
    minextents 1
    maxextents unlimited
  );





create sequence ATS_EMPI.ALLERGY_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 481122
increment by 1
cache 20;





create sequence ATS_EMPI.AUDIT_EVENT_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 3907876422
increment by 1
cache 20;





create sequence ATS_EMPI.CONTACT_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 1421294882
increment by 1
cache 20;





create sequence ATS_EMPI.FILE_SYSTEM_ID_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;





create sequence ATS_EMPI.HIBERNATE_SEQUENCE
minvalue 1
maxvalue 9223372036854775807
start with 242
increment by 1
cache 20;





create sequence ATS_EMPI.HISTORY_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 61765418002965
increment by 1
cache 20;





create sequence ATS_EMPI.HL7_RECEIVE_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 3001
increment by 1
cache 20;





create sequence ATS_EMPI.HL7_RECEIVE_SEND_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;





create sequence ATS_EMPI.HL7_SEND_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 3261
increment by 1
cache 20;





create sequence ATS_EMPI.IDENTIFIER_ATTRIBUTE_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 122
increment by 1
cache 20;





create sequence ATS_EMPI.IDENTIFIER_DOMAIN_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 641502
increment by 1
cache 20;





create sequence ATS_EMPI.IDENTIFIER_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 122
increment by 1
cache 20;





create sequence ATS_EMPI.MANUAL_MERGE_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 31821
increment by 1
cache 20;





create sequence ATS_EMPI.MATCH_RATIO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 46721
increment by 1
cache 20;





create sequence ATS_EMPI.MERGE_EVENT_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 3951169
increment by 1
cache 20;





create sequence ATS_EMPI.PATIENT_FINALLY_STATUS_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;





create sequence ATS_EMPI.PATIENT_VISIT_SEQUENCE
minvalue 1
maxvalue 9223372036854775807
start with 13567502455047
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_IDENTIFIER_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 2279705004
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_IN_OUT_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 17774291
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_LINK_REVIEW_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 122
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_LINK_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 3031901994
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_REGISTER_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 6781
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 1583347782
increment by 1
cache 20;





create sequence ATS_EMPI.PERSON_SEQUENCE
minvalue 1
maxvalue 9223372036854775807
start with 1789607142
increment by 1
cache 20;





create sequence ATS_EMPI.REG_ERROR_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 7041
increment by 1
cache 20;





create sequence ATS_EMPI.RELEVANCE_AND_ID_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 7368313724
increment by 1
cache 20;





create sequence ATS_EMPI.SEQ_TEST
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;





create sequence ATS_EMPI.USER_FILE_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 122
increment by 1
cache 20;





create sequence ATS_EMPI.USER_SESSION_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 12062434423
increment by 1
cache 20;





create sequence ATS_EMPI.VISIT_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 2713503422
increment by 1
cache 20;





create or replace force view ats_empi.empi_and_id as
select distinct "NAME",
                "PATIENT_ID",
                "PATIENT_DOMAIN",
                "EMPI",
                "RELEVANCE_NAME"
  from (select given_name name,
               custom16   patient_id,
               custom11   patient_domain,
               empi,
               GIVEN_NAME RELEVANCE_NAME
          from ats_empi.person
         where date_voided is null
           and custom17 is null
           and CUSTOM16 not like 'HB%'
        UNION
        select T.given_name     name,
               T1.visit_flow_id patient_id,
               T1.visit_flow_domain patient_domain,
               T.empi           empi,
               T.GIVEN_NAME     RELEVANCE_name
          from ats_empi.person T, ats_empi.patient_visit T1
         where T.Date_Voided is null
              --and A.CUSTOM14 is null
           and T.CUSTOM16 = T1.Patient_Id
           and T.custom11 = T1.person_domain
           and T.custom17 is null
           and T.CUSTOM16 not like 'HB%'
        union
        select b.given_name        name,
               A.relevance_id     patient_id,
               A.relevance_domain patient_domain,
               B.empi             empi,
               A.relevance_name   relevance_name
          from ats_empi.relevance_and_id A,ats_empi.person B
          where A.Reg_Id = B.custom16
           and  A.Reg_Domain = B.custom11
           and B.date_voided is null
           and B.CUSTOM16 not like 'HB%')


 where empi in (select empi from ats_empi.empi where date_voided is null)
;





create or replace force view ats_empi.person_for_relevance as
select "NAME", "PATIENT_ID", "PATIENT_DOMAIN", "EMPI"
  from (select given_name name,
               custom16   patient_id,
               custom11   patient_domain,
               empi
          from ats_empi.person
         where date_voided is null
        UNION
        select A.given_name        name,
               B.visit_flow_id     patient_id,
               B.visit_flow_domain patient_domain,
               B.custom1           empi
          from ats_empi.person A, ats_empi.patient_visit B
         where A.Date_Voided is null
           --and A.CUSTOM14 is null
           and A.CUSTOM16 = B.Patient_Id
           and A.custom11 = B.person_domain)
 where empi in (select empi from ats_empi.empi where date_voided is null)
;





CREATE OR REPLACE PROCEDURE ATS_EMPI.MERGE_TYPE_B(IN_EMPI IN VARCHAR2,IN_PERSONID IN VARCHAR2,IN_IDENTIFIER IN VARCHAR2,IN_DOMAIN IN VARCHAR2,IN_DOMAIN_NAME IN VARCHAR2,OUT_V_MSG OUT VARCHAR2)
IS

BEGIN


--PERSON--2013-11-29:PANMIN

    UPDATE ATS_EMPI.PERSON SET EMPI = IN_EMPI WHERE PERSON_ID = IN_PERSONID AND CUSTOM16 = IN_IDENTIFIER AND CUSTOM11 = IN_DOMAIN AND CUSTOM10 = IN_DOMAIN_NAME AND DATE_VOIDED IS NULL;

--PERSON_IDENTIFIER--2013-11-29:PANMIN

    UPDATE ATS_EMPI.PERSON_IDENTIFIER SET CUSTOM1 = IN_EMPI WHERE DATE_VOIDED IS NULL AND PERSON_ID = IN_PERSONID AND IDENTIFIER = IN_IDENTIFIER AND IDENTIFIER_DOMAIN_ID = (SELECT IDENTIFIER_DOMAIN_ID FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER = IN_DOMAIN AND NAMESPACE_IDENTIFIER = IN_DOMAIN_NAME);

--PATIENT_VISIT--20150723:PANMIN

   -- UPDATE ATS_EMPI.PATIENT_VISIT SET CUSTOM1 = IN_EMPI WHERE VOIDED_DATE IS NULL AND PATIENT_ID = IN_IDENTIFIER AND PERSON_DOMAIN = IN_DOMAIN;

--PATIENT_VISIT_HISTORY-20150723:PANMIN

   -- UPDATE ATS_EMPI.PATIENT_VISIT_HISTORY SET GLOBAL_ID = IN_EMPI WHERE VOIDED_DATE IS NULL AND PATIENT_ID = IN_IDENTIFIER AND PERSON_DOMAIN = IN_DOMAIN;

    OUT_V_MSG:='1';

EXCEPTION

   WHEN OTHERS THEN

      ROLLBACK;

      OUT_V_MSG:='0';

      RAISE_APPLICATION_ERROR(-20010,'MERGE_TYPE_B: 脚本执行错误');
END;
/





CREATE OR REPLACE PROCEDURE ATS_EMPI.QUERY_DOCUMENTUNIQUEID (PATIENT_ID IN VARCHAR2,PATIENT_DOMAIN IN VARCHAR2,OUT_V_MSG OUT VARCHAR2)
IS

EMPI_1   VARCHAR2(64);

BEGIN


  select empi INTO EMPI_1 from ats_empi.empi_and_id ee where ee.PATIENT_ID = PATIENT_ID and ee.PATIENT_DOMAIN  = PATIENT_DOMAIN;

  select DOCUMENTUNIQUEID INTO  OUT_V_MSG from axds.idlist t where exists(select tmp.patientId, tmp.patientDomain from (select pai.PATIENT_ID as patientId,pai.PATIENT_DOMAIN as patientDomain from ats_empi.empi_and_id pai where pai.EMPI = EMPI_1) tmp where tmp.patientId = t.id and tmp.patientDomain = t.domain_id) group by DOCUMENTUNIQUEID;

   OUT_V_MSG:='1';

EXCEPTION

   WHEN OTHERS THEN

      ROLLBACK;

      OUT_V_MSG:='0';

      RAISE_APPLICATION_ERROR(-20010,'MERGE_TYPE_A: 脚本执行错误');

END;





CREATE OR REPLACE PROCEDURE ATS_EMPI.UPDATE_TYPE_A(IN_EMPI IN VARCHAR2,IN_OLD_EMPI IN VARCHAR2,IN_DOMAIN_ID IN VARCHAR2,IN_DOMAIN_NAME IN VARCHAR2,IN_PERSONID IN VARCHAR2,IN_REPLACEID IN VARCHAR2,OUT_V_MSG OUT VARCHAR2)
IS
SQL_COUNTS     NUMBER;
GLOBAL_DOMAIN_CODE      VARCHAR2(64);
IDENTIFIER_DOMAIN       VARCHAR2(64);
PATIENTID               VARCHAR2(64);

BEGIN

--PERSON_IDENTIFIER--2013-11-29:PANMIN

  SELECT IDENTIFIER_DOMAIN_ID INTO GLOBAL_DOMAIN_CODE FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER ='2.16.840.1.113883.4.487.2' AND ROWNUM<2;

  SELECT IDENTIFIER_DOMAIN_ID INTO IDENTIFIER_DOMAIN FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER = IN_DOMAIN_ID AND NAMESPACE_IDENTIFIER = IN_DOMAIN_NAME;

  SELECT IDENTIFIER INTO PATIENTID FROM ATS_EMPI.PERSON_IDENTIFIER WHERE PERSON_ID = IN_PERSONID AND IDENTIFIER_DOMAIN_ID = IDENTIFIER_DOMAIN AND DATE_VOIDED IS NULL;

  SELECT COUNT(*) INTO SQL_COUNTS FROM ATS_EMPI.PERSON_IDENTIFIER WHERE PERSON_ID = IN_PERSONID AND CUSTOM1 = IN_OLD_EMPI AND DATE_VOIDED IS NULL;

  IF SQL_COUNTS = 2 THEN

    UPDATE ATS_EMPI.PERSON_IDENTIFIER SET CUSTOM2 = CUSTOM1,CUSTOM1 = IN_EMPI WHERE PERSON_ID = IN_PERSONID AND CUSTOM1 = IN_OLD_EMPI AND IDENTIFIER_DOMAIN_ID !=GLOBAL_DOMAIN_CODE AND DATE_VOIDED IS NULL;

    UPDATE ATS_EMPI.PERSON_IDENTIFIER SET PERSON_ID = IN_REPLACEID WHERE PERSON_ID = IN_PERSONID AND IDENTIFIER_DOMAIN_ID = GLOBAL_DOMAIN_CODE AND DATE_VOIDED IS NULL;

    INSERT INTO ATS_EMPI.PERSON_IDENTIFIER VALUES (ATS_EMPI.PERSON_IDENTIFIER_SEQ.nextval,SYSDATE,NULL,IN_EMPI,IN_PERSONID,20120920000001,GLOBAL_DOMAIN_CODE,NULL,IN_EMPI,NULL,NULL,NULL,NULL);

  END IF;

  IF SQL_COUNTS = 1 THEN

       UPDATE ATS_EMPI.PERSON_IDENTIFIER SET CUSTOM2 = CUSTOM1,CUSTOM1 = IN_EMPI WHERE PERSON_ID = IN_PERSONID AND CUSTOM1 = IN_OLD_EMPI AND IDENTIFIER_DOMAIN_ID !=GLOBAL_DOMAIN_CODE AND DATE_VOIDED IS NULL;

       INSERT INTO ATS_EMPI.PERSON_IDENTIFIER VALUES (ATS_EMPI.PERSON_IDENTIFIER_SEQ.nextval,SYSDATE,NULL,IN_EMPI,IN_PERSONID,20120920000001,GLOBAL_DOMAIN_CODE,NULL,IN_EMPI,NULL,NULL,NULL,NULL);

  END IF;

 --PATIENT_VISIT--2013-11-29
   --UPDATE ATS_EMPI.PATIENT_VISIT SET CUSTOM1=IN_EMPI  WHERE PATIENT_ID = PATIENTID AND PERSON_DOMAIN = IN_DOMAIN_ID AND VOIDED_DATE IS NULL;

 --PATIENT_VISIT_HISTORY-2013-11-29
   --UPDATE ATS_EMPI.PATIENT_VISIT_HISTORY SET CUSTOM1=IN_EMPI,GLOBAL_ID = IN_EMPI WHERE PATIENT_ID = PATIENTID AND PERSON_DOMAIN = IN_DOMAIN_ID AND VOIDED_DATE IS NULL;

--MERGE_EVENT--
  insert into ats_empi.merge_event select
   ats_empi.merge_event_seq.nextval,
   sys_guid(),
         person_id,
         custom16,
         custom11,
         given_name,
         gender_cd,
         identity_no,
         date_of_birth,
         IN_old_empi,
         IN_empi,
         'UPDATE_A',
         '1',
         sysdate,
         '0',
         null,
         null,
         '0',
         null,
         null
    from ats_empi.person
    where person_id = IN_personid;

   OUT_V_MSG:='1';

EXCEPTION

   WHEN OTHERS THEN

      ROLLBACK;

      OUT_V_MSG:='0';

      RAISE_APPLICATION_ERROR(-20010,'UPDATE_TYPE_A: 脚本执行错误');

END;
/





CREATE OR REPLACE PROCEDURE ATS_EMPI.UPDATE_TYPE_B(IN_EMPI IN VARCHAR2,IN_OLD_EMPI IN VARCHAR2,IN_DOMAIN_ID IN VARCHAR2,IN_DOMAIN_NAME IN VARCHAR2,IN_PERSONID IN VARCHAR2,IN_REPLACEID IN VARCHAR2,OUT_V_MSG OUT VARCHAR2)
IS
SQL_COUNTS     NUMBER;
GLOBAL_DOMAIN_CODE      VARCHAR2(64);
IDENTIFIER_DOMAIN       VARCHAR2(64);
PATIENTID               VARCHAR2(64);

BEGIN

--PERSON_IDENTIFIER--2013-11-29:PANMIN

  SELECT IDENTIFIER_DOMAIN_ID INTO GLOBAL_DOMAIN_CODE FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER ='2.16.840.1.113883.4.487.2' AND ROWNUM<2;

  SELECT IDENTIFIER_DOMAIN_ID INTO IDENTIFIER_DOMAIN FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER = IN_DOMAIN_ID AND NAMESPACE_IDENTIFIER = IN_DOMAIN_NAME;

  SELECT IDENTIFIER INTO PATIENTID FROM ATS_EMPI.PERSON_IDENTIFIER WHERE PERSON_ID = IN_PERSONID AND IDENTIFIER_DOMAIN_ID = IDENTIFIER_DOMAIN AND DATE_VOIDED IS NULL;

  UPDATE ATS_EMPI.PERSON_IDENTIFIER SET CUSTOM2 = NULL,CUSTOM1 = IN_EMPI WHERE DATE_VOIDED IS NULL AND PERSON_ID = IN_PERSONID AND CUSTOM1 = IN_OLD_EMPI AND IDENTIFIER_DOMAIN_ID !=GLOBAL_DOMAIN_CODE;

  UPDATE ATS_EMPI.PERSON_IDENTIFIER SET DATE_VOIDED = SYSDATE WHERE PERSON_ID = IN_PERSONID AND CUSTOM1 = IN_OLD_EMPI AND IDENTIFIER_DOMAIN_ID = GLOBAL_DOMAIN_CODE AND DATE_VOIDED IS NULL;


 --PATIENT_VISIT--2013-11-29
   --UPDATE ATS_EMPI.PATIENT_VISIT SET CUSTOM1=IN_EMPI WHERE PATIENT_ID = PATIENTID AND PERSON_DOMAIN = IN_DOMAIN_ID AND VOIDED_DATE IS NULL;

 --PATIENT_VISIT_HISTORY-2013-11-29
   --UPDATE ATS_EMPI.PATIENT_VISIT_HISTORY SET CUSTOM1= IN_EMPI,GLOBAL_ID = IN_EMPI WHERE PATIENT_ID = PATIENTID AND PERSON_DOMAIN = IN_DOMAIN_ID AND VOIDED_DATE IS NULL;

--MERGE_EVENT--
  insert into ats_empi.merge_event select
	 ats_empi.merge_event_seq.nextval,
	 sys_guid(),
         person_id,
         custom16,
         custom11,
         given_name,
         gender_cd,
         identity_no,
         date_of_birth,
         IN_old_empi,
         IN_empi,
         'UPDATE_B',
         '1',
         sysdate,
         '0',
         null,
         null,
         '0',
         null,
         null
    from ats_empi.person
    where person_id = IN_personid;

   OUT_V_MSG:='1';

   EXCEPTION

     WHEN OTHERS THEN

       ROLLBACK;

       OUT_V_MSG:='0';

       RAISE_APPLICATION_ERROR(-20010,'UPDATE_TYPE_B: 脚本执行错误');

END;
/





CREATE OR REPLACE PROCEDURE ATS_EMPI.UPDATE_TYPE_C(IN_NEW_EMPI IN VARCHAR2,IN_OLD_EMPI IN VARCHAR2,IN_DOMAIN_ID IN VARCHAR2,IN_DOMAIN_NAME IN VARCHAR2,IN_PERSONID IN VARCHAR2,IN_REPLACEID IN VARCHAR2,OUT_V_MSG OUT VARCHAR2)
IS
SQL_COUNTS     NUMBER;
GLOBAL_DOMAIN_CODE      VARCHAR2(64);
IDENTIFIER_DOMAIN       VARCHAR2(64);
PATIENTID               VARCHAR2(64);

BEGIN

  SELECT IDENTIFIER_DOMAIN_ID INTO GLOBAL_DOMAIN_CODE FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER ='2.16.840.1.113883.4.487.2' AND ROWNUM<2;

  SELECT IDENTIFIER_DOMAIN_ID INTO IDENTIFIER_DOMAIN FROM ATS_DICT.IDENTIFIER_DOMAIN_DICT WHERE UNIVERSAL_IDENTIFIER = IN_DOMAIN_ID AND NAMESPACE_IDENTIFIER = IN_DOMAIN_NAME;

  SELECT IDENTIFIER INTO PATIENTID FROM ATS_EMPI.PERSON_IDENTIFIER WHERE PERSON_ID = IN_PERSONID AND IDENTIFIER_DOMAIN_ID = IDENTIFIER_DOMAIN AND DATE_VOIDED IS NULL;

--PERSON--2013-11-29:PANMIN

  UPDATE ATS_EMPI.PERSON SET EMPI = IN_NEW_EMPI WHERE EMPI= IN_OLD_EMPI AND DATE_VOIDED IS NULL;

  --DBMS_OUTPUT.PUT_LINE(OLD_EMPI);

--PERSON_IDENTIFIER--2013-11-29:PANMIN

  UPDATE ATS_EMPI.PERSON_IDENTIFIER SET DATE_VOIDED = SYSDATE WHERE CUSTOM1 = IN_OLD_EMPI AND IDENTIFIER_DOMAIN_ID = GLOBAL_DOMAIN_CODE AND DATE_VOIDED IS NULL;

  UPDATE ATS_EMPI.PERSON_IDENTIFIER SET CUSTOM2 = NULL,CUSTOM1 = IN_NEW_EMPI WHERE CUSTOM1 = IN_OLD_EMPI AND IDENTIFIER_DOMAIN_ID !=GLOBAL_DOMAIN_CODE AND DATE_VOIDED IS NULL;

 --PATIENT_VISIT--2013-11-29

  -- UPDATE ATS_EMPI.PATIENT_VISIT SET CUSTOM1 = IN_NEW_EMPI WHERE CUSTOM1 = IN_OLD_EMPI AND VOIDED_DATE IS NULL;

 --PATIENT_VISIT_HISTORY-2013-11-29

  -- UPDATE ATS_EMPI.PATIENT_VISIT_HISTORY SET CUSTOM1 = IN_NEW_EMPI,GLOBAL_ID = IN_NEW_EMPI WHERE CUSTOM1 = IN_OLD_EMPI AND VOIDED_DATE IS NULL;

--EMPI-2013-12-3

   UPDATE ATS_EMPI.EMPI SET DATE_VOIDED = SYSDATE WHERE EMPI = IN_OLD_EMPI AND DATE_VOIDED IS NULL;

--MERGE_EVENT--
  insert into ats_empi.merge_event select
   ats_empi.merge_event_seq.nextval,
   sys_guid(),
         person_id,
         custom16,
         custom11,
         given_name,
         gender_cd,
         identity_no,
         date_of_birth,
         IN_old_empi,
         IN_NEW_empi,
         'UPDATE_B',
         '1',
         sysdate,
         '0',
         null,
         null,
         '0',
         null,
         null
    from ats_empi.person
    where person_id = IN_personid;

   OUT_V_MSG:='1';

   EXCEPTION

       WHEN OTHERS THEN

       ROLLBACK;

       OUT_V_MSG:='0';

      RAISE_APPLICATION_ERROR(-20010,'UPDATE_TYPE_C: 脚本执行错误');

END;

-- Create table
create table PATIENT_INTERMEDIATE
(
  pk                       NUMBER not null,
  patient_id               NUMBER,
  institution_domain_id    VARCHAR2(255),
  patient_name             VARCHAR2(255),
  name_spell               VARCHAR2(255),
  date_of_birth            VARCHAR2(255),
  identity_no              VARCHAR2(64),
  home_place               VARCHAR2(255),
  registered_place         VARCHAR2(255),
  native_place             VARCHAR2(255),
  birth_place              VARCHAR2(255),
  social_security_number   VARCHAR2(255),
  health_card_number       VARCHAR2(255),
  citizen_card_number      VARCHAR2(255),
  sex_code                 VARCHAR2(64),
  sex                      VARCHAR2(255),
  ethnic_code              VARCHAR2(64),
  ethnic                   VARCHAR2(255),
  country_code             VARCHAR2(64),
  country                  VARCHAR2(255),
  medical_insurance_number VARCHAR2(255),
  elder_certificate_number VARCHAR2(255),
  medical_records_number   VARCHAR2(255),
  language_code            VARCHAR2(64),
  language                 VARCHAR2(255),
  religion_code            VARCHAR2(64),
  religion                 VARCHAR2(255),
  marital_code             VARCHAR2(64),
  marital                  VARCHAR2(255),
  degree_code              VARCHAR2(64),
  degree                   VARCHAR2(255),
  home_phone               VARCHAR2(255),
  home_zip_code            VARCHAR2(64),
  registered_zip_code      VARCHAR2(64),
  birth_zip_code           VARCHAR2(255),
  native_zip_code          VARCHAR2(255),
  email                    VARCHAR2(255),
  identity                 VARCHAR2(255),
  identity_code            VARCHAR2(64),
  work_address             VARCHAR2(255),
  work_phone               VARCHAR2(255),
  relation_adress          VARCHAR2(255),
  relation_name            VARCHAR2(255),
  relation_phone           VARCHAR2(255),
  relation_zip_code        VARCHAR2(255),
  blood_type_code          VARCHAR2(64),
  blood_type               VARCHAR2(64),
  certificates_type_code   VARCHAR2(64),
  certificates_number      VARCHAR2(255),
  charge_type              VARCHAR2(255),
  upload_time              DATE,
  register_status          NUMBER default 0 not null,
  registered_globalid      VARCHAR2(255),
  original_data_xml        CLOB,
  allergy_information      VARCHAR2(255)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table PATIENT_INTERMEDIATE
  is '患者数据抽取和患者empi注册的中间表';
-- Add comments to the columns
comment on column PATIENT_INTERMEDIATE.pk
  is '主键';
comment on column PATIENT_INTERMEDIATE.patient_id
  is '患者在该机构内的唯一id';
comment on column PATIENT_INTERMEDIATE.institution_domain_id
  is '上传机构域id';
comment on column PATIENT_INTERMEDIATE.patient_name
  is '患者姓名';
comment on column PATIENT_INTERMEDIATE.name_spell
  is '姓名拼写';
comment on column PATIENT_INTERMEDIATE.date_of_birth
  is '生日 yyyy-dd-mm';
comment on column PATIENT_INTERMEDIATE.identity_no
  is '身份证号';
comment on column PATIENT_INTERMEDIATE.home_place
  is '居住地/联系地（全）';
comment on column PATIENT_INTERMEDIATE.registered_place
  is '户口所在地(全)';
comment on column PATIENT_INTERMEDIATE.native_place
  is '籍贯(全)';
comment on column PATIENT_INTERMEDIATE.birth_place
  is '出生地(全)';
comment on column PATIENT_INTERMEDIATE.social_security_number
  is '社保号';
comment on column PATIENT_INTERMEDIATE.health_card_number
  is '健康卡号';
comment on column PATIENT_INTERMEDIATE.citizen_card_number
  is '市民卡号';
comment on column PATIENT_INTERMEDIATE.sex_code
  is '性别代码';
comment on column PATIENT_INTERMEDIATE.sex
  is '性别(男,女,其它)';
comment on column PATIENT_INTERMEDIATE.ethnic_code
  is '民族代码';
comment on column PATIENT_INTERMEDIATE.ethnic
  is '民族';
comment on column PATIENT_INTERMEDIATE.country_code
  is '国籍代码';
comment on column PATIENT_INTERMEDIATE.country
  is '国籍';
comment on column PATIENT_INTERMEDIATE.medical_insurance_number
  is '医保卡号';
comment on column PATIENT_INTERMEDIATE.elder_certificate_number
  is '老人卡号';
comment on column PATIENT_INTERMEDIATE.medical_records_number
  is '病历卡号';
comment on column PATIENT_INTERMEDIATE.language_code
  is '语言代码';
comment on column PATIENT_INTERMEDIATE.language
  is '语言';
comment on column PATIENT_INTERMEDIATE.religion_code
  is '宗教代码';
comment on column PATIENT_INTERMEDIATE.religion
  is '宗教';
comment on column PATIENT_INTERMEDIATE.marital_code
  is '婚姻代码';
comment on column PATIENT_INTERMEDIATE.marital
  is '婚姻(已婚,未婚,离异,丧偶,其它)';
comment on column PATIENT_INTERMEDIATE.degree_code
  is '学历代码';
comment on column PATIENT_INTERMEDIATE.degree
  is '学历';
comment on column PATIENT_INTERMEDIATE.home_phone
  is '家庭联系电话';
comment on column PATIENT_INTERMEDIATE.home_zip_code
  is '居住地/联系地邮编';
comment on column PATIENT_INTERMEDIATE.registered_zip_code
  is '户口所在地邮编';
comment on column PATIENT_INTERMEDIATE.birth_zip_code
  is '出生地邮编';
comment on column PATIENT_INTERMEDIATE.native_zip_code
  is '籍贯邮编';
comment on column PATIENT_INTERMEDIATE.email
  is '邮箱';
comment on column PATIENT_INTERMEDIATE.identity
  is '身份';
comment on column PATIENT_INTERMEDIATE.identity_code
  is '身份代码';
comment on column PATIENT_INTERMEDIATE.work_address
  is '单位地址';
comment on column PATIENT_INTERMEDIATE.work_phone
  is '工作联系电话';
comment on column PATIENT_INTERMEDIATE.relation_adress
  is '联系人地址';
comment on column PATIENT_INTERMEDIATE.relation_name
  is '联系人姓名';
comment on column PATIENT_INTERMEDIATE.relation_phone
  is '联系人电话';
comment on column PATIENT_INTERMEDIATE.relation_zip_code
  is '联系人邮编';
comment on column PATIENT_INTERMEDIATE.blood_type_code
  is '血型代码';
comment on column PATIENT_INTERMEDIATE.blood_type
  is '血型';
comment on column PATIENT_INTERMEDIATE.certificates_type_code
  is '证件代码';
comment on column PATIENT_INTERMEDIATE.certificates_number
  is '证件号';
comment on column PATIENT_INTERMEDIATE.charge_type
  is '费用类别';
comment on column PATIENT_INTERMEDIATE.upload_time
  is '上传时间';
comment on column PATIENT_INTERMEDIATE.register_status
  is '未注册0,已注册1,注册失败2';
comment on column PATIENT_INTERMEDIATE.registered_globalid
  is '注册成功的患者唯一id';
comment on column PATIENT_INTERMEDIATE.original_data_xml
  is '原始数据xml';
comment on column PATIENT_INTERMEDIATE.allergy_information
  is '过敏信息';





-- Create table
create table PATIENT_INFO
(
  pk                       NUMBER not null,
  global_id                VARCHAR2(255),
  patient_id               NUMBER,
  institution_domain_id    VARCHAR2(255),
  patient_name             VARCHAR2(255),
  name_spell               VARCHAR2(255),
  date_of_birth            VARCHAR2(255),
  identity_no              VARCHAR2(64),
  home_place               VARCHAR2(255),
  registered_place         VARCHAR2(255),
  native_place             VARCHAR2(255),
  birth_place              VARCHAR2(255),
  social_security_number   VARCHAR2(255),
  health_card_number       VARCHAR2(255),
  citizen_card_number      VARCHAR2(255),
  sex_code                 VARCHAR2(64),
  sex                      VARCHAR2(255),
  ethnic_code              VARCHAR2(64),
  ethnic                   VARCHAR2(255),
  country_code             VARCHAR2(64),
  country                  VARCHAR2(255),
  medical_insurance_number VARCHAR2(255),
  elder_certificate_number VARCHAR2(255),
  medical_records_number   VARCHAR2(255),
  language_code            VARCHAR2(64),
  language                 VARCHAR2(255),
  religion_code            VARCHAR2(64),
  religion                 VARCHAR2(255),
  marital_code             VARCHAR2(64),
  marital                  VARCHAR2(255),
  degree_code              VARCHAR2(64),
  degree                   VARCHAR2(255),
  home_phone               VARCHAR2(255),
  home_zip_code            VARCHAR2(64),
  registered_zip_code      VARCHAR2(64),
  birth_zip_code           VARCHAR2(255),
  native_zip_code          VARCHAR2(255),
  email                    VARCHAR2(255),
  identity                 VARCHAR2(255),
  identity_code            VARCHAR2(64),
  work_address             VARCHAR2(255),
  work_phone               VARCHAR2(255),
  relation_adress          VARCHAR2(255),
  relation_name            VARCHAR2(255),
  relation_phone           VARCHAR2(255),
  relation_zip_code        VARCHAR2(255),
  blood_type_code          VARCHAR2(64),
  blood_type               VARCHAR2(64),
  certificates_type_code   VARCHAR2(64),
  certificates_number      VARCHAR2(255),
  charge_type              VARCHAR2(255),
  allergy_information      VARCHAR2(255),
  register_time            DATE,
  info_status              NUMBER default 0 not null
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table PATIENT_INFO
  is '患者信息表';
-- Add comments to the columns
comment on column PATIENT_INFO.pk
  is '主键';
comment on column PATIENT_INFO.global_id
  is '患者唯一ID';
comment on column PATIENT_INFO.patient_id
  is '患者在该机构内的唯一id';
comment on column PATIENT_INFO.institution_domain_id
  is '上传机构域id';
comment on column PATIENT_INFO.patient_name
  is '患者姓名';
comment on column PATIENT_INFO.name_spell
  is '姓名拼写';
comment on column PATIENT_INFO.date_of_birth
  is '生日 yyyy-dd-mm';
comment on column PATIENT_INFO.identity_no
  is '身份证号';
comment on column PATIENT_INFO.home_place
  is '居住地/联系地（全）';
comment on column PATIENT_INFO.registered_place
  is '户口所在地(全)';
comment on column PATIENT_INFO.native_place
  is '籍贯(全)';
comment on column PATIENT_INFO.birth_place
  is '出生地(全)';
comment on column PATIENT_INFO.social_security_number
  is '社保号';
comment on column PATIENT_INFO.health_card_number
  is '健康卡号';
comment on column PATIENT_INFO.citizen_card_number
  is '市民卡号';
comment on column PATIENT_INFO.sex_code
  is '性别代码';
comment on column PATIENT_INFO.sex
  is '性别(男,女,其它)';
comment on column PATIENT_INFO.ethnic_code
  is '民族代码';
comment on column PATIENT_INFO.ethnic
  is '民族';
comment on column PATIENT_INFO.country_code
  is '国籍代码';
comment on column PATIENT_INFO.country
  is '国籍';
comment on column PATIENT_INFO.medical_insurance_number
  is '医保卡号';
comment on column PATIENT_INFO.elder_certificate_number
  is '老人卡号';
comment on column PATIENT_INFO.medical_records_number
  is '病历卡号';
comment on column PATIENT_INFO.language_code
  is '语言代码';
comment on column PATIENT_INFO.language
  is '语言';
comment on column PATIENT_INFO.religion_code
  is '宗教代码';
comment on column PATIENT_INFO.religion
  is '宗教';
comment on column PATIENT_INFO.marital_code
  is '婚姻代码';
comment on column PATIENT_INFO.marital
  is '婚姻(已婚,未婚,离异,丧偶,其它)';
comment on column PATIENT_INFO.degree_code
  is '学历代码';
comment on column PATIENT_INFO.degree
  is '学历';
comment on column PATIENT_INFO.home_phone
  is '家庭联系电话';
comment on column PATIENT_INFO.home_zip_code
  is '居住地/联系地邮编';
comment on column PATIENT_INFO.registered_zip_code
  is '户口所在地邮编';
comment on column PATIENT_INFO.birth_zip_code
  is '出生地邮编';
comment on column PATIENT_INFO.native_zip_code
  is '籍贯邮编';
comment on column PATIENT_INFO.email
  is '邮箱';
comment on column PATIENT_INFO.identity
  is '身份';
comment on column PATIENT_INFO.identity_code
  is '身份代码';
comment on column PATIENT_INFO.work_address
  is '单位地址';
comment on column PATIENT_INFO.work_phone
  is '工作联系电话';
comment on column PATIENT_INFO.relation_adress
  is '联系人地址';
comment on column PATIENT_INFO.relation_name
  is '联系人姓名';
comment on column PATIENT_INFO.relation_phone
  is '联系人电话';
comment on column PATIENT_INFO.relation_zip_code
  is '联系人邮编';
comment on column PATIENT_INFO.blood_type_code
  is '血型代码';
comment on column PATIENT_INFO.blood_type
  is '血型';
comment on column PATIENT_INFO.certificates_type_code
  is '证件代码';
comment on column PATIENT_INFO.certificates_number
  is '证件号';
comment on column PATIENT_INFO.charge_type
  is '费用类别';
comment on column PATIENT_INFO.allergy_information
  is '过敏信息';
comment on column PATIENT_INFO.register_time
  is '注册时间';
comment on column PATIENT_INFO.info_status
  is '正常0,作废1,异常2';

