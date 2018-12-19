--------------------------------------------
-- Export file for user ATS_DICT          --
-- Created by chris on 2018/9/4, 21:16:52 --
--------------------------------------------

set define off
spool ats_dict.log

prompt
prompt Creating table ADDRESS_TYPE_DICT
prompt ================================
prompt
create table ATS_DICT.ADDRESS_TYPE_DICT
(
  address_type_cd          NUMBER not null,
  address_type_name        VARCHAR2(64) not null,
  address_type_description VARCHAR2(255),
  address_type_code        VARCHAR2(64) not null,
  create_name              VARCHAR2(64),
  create_date              TIMESTAMP(6),
  comments                 VARCHAR2(255),
  custom1                  VARCHAR2(255),
  custom2                  VARCHAR2(255),
  custom3                  VARCHAR2(255),
  custom4                  VARCHAR2(255),
  custom5                  VARCHAR2(255)
)
;
comment on table ATS_DICT.ADDRESS_TYPE_DICT
  is '地址类型';
alter table ATS_DICT.ADDRESS_TYPE_DICT
  add constraint ADDRESS_TYPE_DICT_PKEY primary key (ADDRESS_TYPE_CD);

prompt
prompt Creating table ALLERGY_TYPE_DICT
prompt ================================
prompt
create table ATS_DICT.ALLERGY_TYPE_DICT
(
  allergy_type_id   NUMBER not null,
  allergy_type_code VARCHAR2(64) not null,
  allergy_type_name VARCHAR2(64) not null,
  input_code        VARCHAR2(64) not null,
  create_name       VARCHAR2(64),
  create_date       TIMESTAMP(6),
  comments          VARCHAR2(255),
  custom1           VARCHAR2(255),
  custom2           VARCHAR2(255),
  custom3           VARCHAR2(255),
  custom4           VARCHAR2(255),
  custom5           VARCHAR2(255)
)
;
comment on table ATS_DICT.ALLERGY_TYPE_DICT
  is '过敏类型';
alter table ATS_DICT.ALLERGY_TYPE_DICT
  add constraint ALLERGY_TYPE_DICT_PK primary key (ALLERGY_TYPE_ID);

prompt
prompt Creating table AREAROOM_DICT
prompt ============================
prompt
create table ATS_DICT.AREAROOM_DICT
(
  arearoom_cd   NUMBER not null,
  arearoom_code VARCHAR2(64) not null,
  arearoom_name VARCHAR2(64) not null,
  input_code    VARCHAR2(64) not null,
  create_name   VARCHAR2(64),
  create_date   TIMESTAMP(6),
  comments      VARCHAR2(255),
  custom1       VARCHAR2(255),
  custom2       VARCHAR2(255),
  custom3       VARCHAR2(255),
  custom4       VARCHAR2(255),
  custom5       VARCHAR2(255)
)
;
comment on table ATS_DICT.AREAROOM_DICT
  is '病房';
alter table ATS_DICT.AREAROOM_DICT
  add constraint AREAROOM_DICT_PKEY primary key (AREAROOM_CD);

prompt
prompt Creating table AREA_DICT
prompt ========================
prompt
create table ATS_DICT.AREA_DICT
(
  area_cd     NUMBER not null,
  area_code   VARCHAR2(255),
  area_name   VARCHAR2(64) not null,
  input_code  VARCHAR2(64) not null,
  create_name VARCHAR2(64),
  create_date TIMESTAMP(6),
  comments    VARCHAR2(255),
  custom1     VARCHAR2(255),
  custom2     VARCHAR2(255),
  custom3     VARCHAR2(255),
  custom4     VARCHAR2(255),
  custom5     VARCHAR2(255)
)
;
comment on table ATS_DICT.AREA_DICT
  is '病区';
alter table ATS_DICT.AREA_DICT
  add constraint AREA_DICT_PKEY primary key (AREA_CD);

prompt
prompt Creating table AUDIT_EVENT_TYPE_DICT
prompt ====================================
prompt
create table ATS_DICT.AUDIT_EVENT_TYPE_DICT
(
  audit_event_type_cd          NUMBER not null,
  audit_event_type_name        VARCHAR2(64) not null,
  audit_event_type_description VARCHAR2(255),
  audit_event_type_code        VARCHAR2(64) not null,
  create_name                  VARCHAR2(64),
  create_date                  TIMESTAMP(6),
  comments                     VARCHAR2(255),
  custom1                      VARCHAR2(255),
  custom2                      VARCHAR2(255),
  custom3                      VARCHAR2(255),
  custom4                      VARCHAR2(255),
  custom5                      VARCHAR2(255)
)
;
comment on table ATS_DICT.AUDIT_EVENT_TYPE_DICT
  is '事件类型';
alter table ATS_DICT.AUDIT_EVENT_TYPE_DICT
  add constraint AUDIT_EVENT_DICT_PK primary key (AUDIT_EVENT_TYPE_CD);

prompt
prompt Creating table BABY_FLAG_DICT
prompt =============================
prompt
create table ATS_DICT.BABY_FLAG_DICT
(
  baby_flag_id   NUMBER not null,
  baby_flag_code VARCHAR2(64) not null,
  baby_flag_name VARCHAR2(64) not null,
  input_code     VARCHAR2(64) not null,
  create_name    VARCHAR2(64),
  create_date    TIMESTAMP(6),
  comments       VARCHAR2(255),
  custom1        VARCHAR2(255),
  custom2        VARCHAR2(255),
  custom3        VARCHAR2(255),
  custom4        VARCHAR2(255),
  custom5        VARCHAR2(255)
)
;
comment on table ATS_DICT.BABY_FLAG_DICT
  is '婴儿字典';
alter table ATS_DICT.BABY_FLAG_DICT
  add constraint BABY_FLAG_DICT_PK primary key (BABY_FLAG_ID);

prompt
prompt Creating table BLOODTYPE_DICT
prompt =============================
prompt
create table ATS_DICT.BLOODTYPE_DICT
(
  bloodtype_cd   NUMBER not null,
  bloodtype_code VARCHAR2(64) not null,
  bloodtype_name VARCHAR2(64) not null,
  input_code     VARCHAR2(64) not null,
  create_name    VARCHAR2(64),
  create_date    TIMESTAMP(6),
  comments       VARCHAR2(255),
  custom1        VARCHAR2(255),
  custom2        VARCHAR2(255),
  custom3        VARCHAR2(255),
  custom4        VARCHAR2(255),
  custom5        VARCHAR2(255)
)
;
comment on table ATS_DICT.BLOODTYPE_DICT
  is '血型';
alter table ATS_DICT.BLOODTYPE_DICT
  add constraint BLOODTYPE_DICT_PK primary key (BLOODTYPE_CD);

prompt
prompt Creating table BODY_SITE_DICT
prompt =============================
prompt
create table ATS_DICT.BODY_SITE_DICT
(
  body_site_id          NUMBER not null,
  body_site_code        VARCHAR2(64) not null,
  body_site_name        VARCHAR2(64) not null,
  body_site_domain_name VARCHAR2(64),
  body_site_domain_id   VARCHAR2(64),
  body_site_description VARCHAR2(512),
  create_date           TIMESTAMP(6),
  creater               VARCHAR2(128),
  custom1               VARCHAR2(255),
  custom2               VARCHAR2(255),
  custom3               VARCHAR2(255)
)
;
comment on table ATS_DICT.BODY_SITE_DICT
  is '身体部位词典';
alter table ATS_DICT.BODY_SITE_DICT
  add constraint BODY_SITE_DICT_PK primary key (BODY_SITE_ID);

prompt
prompt Creating table CARD_TYPE_DICT
prompt =============================
prompt
create table ATS_DICT.CARD_TYPE_DICT
(
  id             NUMBER not null,
  card_type_code VARCHAR2(64),
  card_type_name VARCHAR2(64),
  comments       VARCHAR2(255),
  custom1        VARCHAR2(255),
  custom2        VARCHAR2(255),
  custom3        VARCHAR2(255),
  custom4        VARCHAR2(255),
  custom5        VARCHAR2(255)
)
;
alter table ATS_DICT.CARD_TYPE_DICT
  add constraint CARD_TYPE_DICT_ID primary key (ID);

prompt
prompt Creating table CHARGETYPE_DICT
prompt ==============================
prompt
create table ATS_DICT.CHARGETYPE_DICT
(
  chargetype_cd   NUMBER not null,
  chargetype_code VARCHAR2(255),
  chargetype_name VARCHAR2(64) not null,
  input_code      VARCHAR2(64) not null,
  create_name     VARCHAR2(64),
  create_date     TIMESTAMP(6),
  comments        VARCHAR2(255),
  custom1         VARCHAR2(255),
  custom2         VARCHAR2(255),
  custom3         VARCHAR2(255),
  custom4         VARCHAR2(255),
  custom5         VARCHAR2(255)
)
;
comment on table ATS_DICT.CHARGETYPE_DICT
  is '费别';
alter table ATS_DICT.CHARGETYPE_DICT
  add constraint CHARGETYPE_DICT_PK primary key (CHARGETYPE_CD);

prompt
prompt Creating table CONSUME_PLATFORM_MAPPING
prompt =======================================
prompt
create table ATS_DICT.CONSUME_PLATFORM_MAPPING
(
  source_column_pk        NUMBER not null,
  consume_pk              NUMBER,
  hiup_column             VARCHAR2(128),
  hiup_column_desc        VARCHAR2(256),
  consume_column          VARCHAR2(128),
  consume_column_desc     VARCHAR2(256),
  consume_table           VARCHAR2(256),
  hiup_table              VARCHAR2(256),
  hiup_primarykey         VARCHAR2(256),
  consume_primarykey      VARCHAR2(256),
  consume_primarykey_name VARCHAR2(256),
  hiup_primarykey_name    VARCHAR2(256),
  consume_dbmanage_pk     VARCHAR2(128),
  hiup_dbmanage_pk        VARCHAR2(128),
  hiup_database           VARCHAR2(128)
)
;
comment on table ATS_DICT.CONSUME_PLATFORM_MAPPING
  is '字典消费端表字段和平台字典表字段映射表';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.source_column_pk
  is '表主键';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_pk
  is '平台字典表和字典消费库表映射关系表主键';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_column
  is '平台库表中的列名';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_column_desc
  is '平台库表中的列描述';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_column
  is '字典消费库表中的列名';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_column_desc
  is '字典消费库表中的列描述';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_table
  is '字典消费库表名';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_table
  is '平台库表名';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_primarykey
  is '该平台列是否是该平台表的主键---0是1否';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_primarykey
  is '字典消费库列否是该平台表的主键---0是1否';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_primarykey_name
  is '字典消费表主键名';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_primarykey_name
  is '平台消费表主键名';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.consume_dbmanage_pk
  is '消费表对应的表数据库管理表主键';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_dbmanage_pk
  is '平台表对应的表数据库管理表主键';
comment on column ATS_DICT.CONSUME_PLATFORM_MAPPING.hiup_database
  is '源表的库';
alter table ATS_DICT.CONSUME_PLATFORM_MAPPING
  add primary key (SOURCE_COLUMN_PK);

prompt
prompt Creating table CRITICALSTATUS_DICT
prompt ==================================
prompt
create table ATS_DICT.CRITICALSTATUS_DICT
(
  criticalstatus_cd   NUMBER not null,
  criticalstatus_code VARCHAR2(64) not null,
  criticalstatus_name VARCHAR2(64) not null,
  input_code          VARCHAR2(64) not null,
  create_name         VARCHAR2(64),
  create_date         TIMESTAMP(6),
  comments            VARCHAR2(255),
  custom1             VARCHAR2(255),
  custom2             VARCHAR2(255),
  custom3             VARCHAR2(255),
  custom4             VARCHAR2(255),
  custom5             VARCHAR2(255)
)
;
comment on table ATS_DICT.CRITICALSTATUS_DICT
  is '病情';
alter table ATS_DICT.CRITICALSTATUS_DICT
  add constraint CRITICALSTATUS_DICT_PK primary key (CRITICALSTATUS_CD);

prompt
prompt Creating table DEGREE_TYPE_DICT
prompt ===============================
prompt
create table ATS_DICT.DEGREE_TYPE_DICT
(
  degree_type_cd   NUMBER not null,
  degree_type_code VARCHAR2(255),
  degree_type_name VARCHAR2(64) not null,
  input_code       VARCHAR2(64) not null,
  create_name      VARCHAR2(64),
  create_date      TIMESTAMP(6),
  comments         VARCHAR2(255),
  custom1          VARCHAR2(255),
  custom2          VARCHAR2(255),
  custom3          VARCHAR2(255),
  custom4          VARCHAR2(255),
  custom5          VARCHAR2(255)
)
;
comment on table ATS_DICT.DEGREE_TYPE_DICT
  is '学历';
alter table ATS_DICT.DEGREE_TYPE_DICT
  add constraint DEGREE_TYPE_DICT_PK primary key (DEGREE_TYPE_CD);

prompt
prompt Creating table DEPTTEAM
prompt =======================
prompt
create table ATS_DICT.DEPTTEAM
(
  constraint_code VARCHAR2(12) not null,
  stat_code       VARCHAR2(4) not null,
  pardep_code     VARCHAR2(4) not null,
  pardep_name     VARCHAR2(30),
  dept_code       VARCHAR2(4) not null,
  dept_name       VARCHAR2(30),
  spell_code      VARCHAR2(8),
  wb_code         VARCHAR2(8),
  node_kind       NUMBER(1),
  grade_code      NUMBER(3),
  sort_id         NUMBER,
  valid_state     VARCHAR2(1) not null,
  ext_flag        VARCHAR2(1),
  ext1_flag       VARCHAR2(1),
  mark            VARCHAR2(2000),
  js_status       VARCHAR2(1) default 0
)
;
comment on table ATS_DICT.DEPTTEAM
  is '护士（医生）护理站表';
comment on column ATS_DICT.DEPTTEAM.js_status
  is '同步数据标识：0=初始化，1=成功，2=同步失败';

prompt
prompt Creating table DEPT_DICT
prompt ========================
prompt
create table ATS_DICT.DEPT_DICT
(
  dept_cd     NUMBER not null,
  dept_code   VARCHAR2(64) not null,
  dept_name   VARCHAR2(64) not null,
  input_code  VARCHAR2(64) not null,
  create_name VARCHAR2(64),
  create_date TIMESTAMP(6),
  comments    VARCHAR2(255),
  custom1     VARCHAR2(255),
  custom2     VARCHAR2(255),
  custom3     VARCHAR2(255),
  custom4     VARCHAR2(255),
  custom5     VARCHAR2(255)
)
;
comment on table ATS_DICT.DEPT_DICT
  is '科室';
alter table ATS_DICT.DEPT_DICT
  add constraint DEPT_DICT_PK primary key (DEPT_CD);

prompt
prompt Creating table DEPT_DOC_ITEMS
prompt =============================
prompt
create table ATS_DICT.DEPT_DOC_ITEMS
(
  pk            NUMBER not null,
  dept_code     VARCHAR2(64) not null,
  dept_name     VARCHAR2(999) not null,
  inspect_code  VARCHAR2(64) not null,
  inspect_name  VARCHAR2(999) not null,
  isdelete      VARCHAR2(2) not null,
  remark        VARCHAR2(2999),
  pay_load_type VARCHAR2(999),
  item_code     VARCHAR2(999)
)
;
comment on table ATS_DICT.DEPT_DOC_ITEMS
  is '科室检查项目配置表';
comment on column ATS_DICT.DEPT_DOC_ITEMS.pk
  is '表主键';
comment on column ATS_DICT.DEPT_DOC_ITEMS.dept_code
  is '科室编号';
comment on column ATS_DICT.DEPT_DOC_ITEMS.dept_name
  is '科室名称';
comment on column ATS_DICT.DEPT_DOC_ITEMS.inspect_code
  is '检查项目编号';
comment on column ATS_DICT.DEPT_DOC_ITEMS.inspect_name
  is '检查项目名称';
comment on column ATS_DICT.DEPT_DOC_ITEMS.isdelete
  is '检查项目是否被删除：0否，1是';
comment on column ATS_DICT.DEPT_DOC_ITEMS.remark
  is '检查项目的备注';
comment on column ATS_DICT.DEPT_DOC_ITEMS.pay_load_type
  is '文档类型';
comment on column ATS_DICT.DEPT_DOC_ITEMS.item_code
  is '检查项目编号明细';

prompt
prompt Creating table DEPT_DOC_ITEMS_NAME
prompt ==================================
prompt
create table ATS_DICT.DEPT_DOC_ITEMS_NAME
(
  pk                 NUMBER,
  item_code          VARCHAR2(128),
  item_name          VARCHAR2(999),
  remark             VARCHAR2(999),
  dept_code          VARCHAR2(999),
  pay_load_type      VARCHAR2(999),
  document_domain_id VARCHAR2(999)
)
;
comment on table ATS_DICT.DEPT_DOC_ITEMS_NAME
  is '科室检查项目名称配置表                                                                                                                                                         





































当你看到这的时候，恭喜入坑                                                                                                                                                                                                                              ';
comment on column ATS_DICT.DEPT_DOC_ITEMS_NAME.pk
  is '表主键';
comment on column ATS_DICT.DEPT_DOC_ITEMS_NAME.item_code
  is '检查细项编号';
comment on column ATS_DICT.DEPT_DOC_ITEMS_NAME.item_name
  is '检查细项配置的名称';
comment on column ATS_DICT.DEPT_DOC_ITEMS_NAME.dept_code
  is '科室编号';
comment on column ATS_DICT.DEPT_DOC_ITEMS_NAME.pay_load_type
  is '文档类型';
comment on column ATS_DICT.DEPT_DOC_ITEMS_NAME.document_domain_id
  is '对应的域ID';

prompt
prompt Creating table DIAGNOSE_SERVICE_DICT
prompt ====================================
prompt
create table ATS_DICT.DIAGNOSE_SERVICE_DICT
(
  diagnose_service_id          NUMBER not null,
  diagnose_service_code        VARCHAR2(64) not null,
  diagnose_service_name        VARCHAR2(64) not null,
  diagnose_service_domain_name VARCHAR2(64),
  diagnose_service_domain_id   VARCHAR2(64),
  diagnose_service_description VARCHAR2(512),
  create_date                  TIMESTAMP(6),
  creater                      VARCHAR2(128),
  custom1                      VARCHAR2(255),
  custom2                      VARCHAR2(255),
  custom3                      VARCHAR2(255)
)
;
comment on table ATS_DICT.DIAGNOSE_SERVICE_DICT
  is '诊断服务词典';
alter table ATS_DICT.DIAGNOSE_SERVICE_DICT
  add constraint DIAGNOSE_SERVICE_DICT_PK primary key (DIAGNOSE_SERVICE_ID);

prompt
prompt Creating table DIAGNOSE_STATUS_TYPE_DICT
prompt ========================================
prompt
create table ATS_DICT.DIAGNOSE_STATUS_TYPE_DICT
(
  diagnose_status_type_cd   NUMBER not null,
  diagnose_status_type_code VARCHAR2(64) not null,
  diagnose_status_type_name VARCHAR2(64) not null,
  input_code                VARCHAR2(64),
  create_name               VARCHAR2(64),
  create_date               TIMESTAMP(6),
  comments                  VARCHAR2(255),
  custom1                   VARCHAR2(255),
  custom2                   VARCHAR2(255),
  custom3                   VARCHAR2(255),
  custom4                   VARCHAR2(255),
  custom5                   VARCHAR2(255)
)
;
comment on table ATS_DICT.DIAGNOSE_STATUS_TYPE_DICT
  is '诊断状态';
alter table ATS_DICT.DIAGNOSE_STATUS_TYPE_DICT
  add constraint DIAGNOSE_STATUS_TYPE_DICT_PK primary key (DIAGNOSE_STATUS_TYPE_CD);

prompt
prompt Creating table DIAGNOSE_TYPE_DICT
prompt =================================
prompt
create table ATS_DICT.DIAGNOSE_TYPE_DICT
(
  diagnose_type_cd   NUMBER not null,
  diagnose_type_code VARCHAR2(64) not null,
  diagnose_type_name VARCHAR2(64) not null,
  input_code         VARCHAR2(64) not null,
  create_name        VARCHAR2(64),
  create_date        TIMESTAMP(6),
  comments           VARCHAR2(255),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255),
  custom4            VARCHAR2(255),
  custom5            VARCHAR2(255)
)
;
comment on table ATS_DICT.DIAGNOSE_TYPE_DICT
  is '诊断类型';
alter table ATS_DICT.DIAGNOSE_TYPE_DICT
  add constraint DIAGNOSE_TYPE_DICT_PK primary key (DIAGNOSE_TYPE_CD);

prompt
prompt Creating table DICT
prompt ===================
prompt
create table ATS_DICT.DICT
(
  dict_pk         NUMBER not null,
  dic_id          VARCHAR2(124) not null,
  hospital_domain VARCHAR2(124) not null,
  dict_alias      VARCHAR2(124),
  dict_version    VARCHAR2(32),
  dict_private    VARCHAR2(124) not null,
  dict_gb         VARCHAR2(32)
)
;
comment on table ATS_DICT.DICT
  is '字典描述信息表';
comment on column ATS_DICT.DICT.dict_pk
  is '表主键';
comment on column ATS_DICT.DICT.dic_id
  is '字典编号';
comment on column ATS_DICT.DICT.hospital_domain
  is '字典所属医院ID';
comment on column ATS_DICT.DICT.dict_alias
  is '字典名称';
comment on column ATS_DICT.DICT.dict_version
  is '字典版本号';
comment on column ATS_DICT.DICT.dict_private
  is '私有字典或者是国标字典:0 国标字典
,1 私有字典
';
comment on column ATS_DICT.DICT.dict_gb
  is '私有字典对应的国标字典';
alter table ATS_DICT.DICT
  add constraint DICT_PK_INDEX primary key (DICT_PK);

prompt
prompt Creating table DICT_ADMISSION_ROUTE
prompt ===================================
prompt
create table ATS_DICT.DICT_ADMISSION_ROUTE
(
  shunxuhao NUMBER(6) not null,
  daimaid   VARCHAR2(10) not null,
  daimamc   VARCHAR2(100) not null,
  menzhensy NUMBER(1) not null,
  zhuyuansy NUMBER(1) not null,
  shuruma1  VARCHAR2(10),
  shuruma2  VARCHAR2(10),
  shuruma3  VARCHAR2(10),
  zuofeibz  NUMBER(1) not null
)
;
comment on table ATS_DICT.DICT_ADMISSION_ROUTE
  is '入院途径表';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.daimaid
  is '代码ID';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.daimamc
  is '代码名称';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_ADMISSION_ROUTE.zuofeibz
  is '作废标志';

prompt
prompt Creating table DICT_AGENCY_MAP
prompt ==============================
prompt
create table ATS_DICT.DICT_AGENCY_MAP
(
  id                 NUMBER not null,
  hospital_domain_id VARCHAR2(64) not null,
  dict_domain_id     VARCHAR2(64) not null,
  hospital_name      VARCHAR2(128),
  dict_name          VARCHAR2(128),
  custom1            VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_AGENCY_MAP
  is '机构与字典的关系映射表';
comment on column ATS_DICT.DICT_AGENCY_MAP.id
  is '机构与字典映射表的表主键';
comment on column ATS_DICT.DICT_AGENCY_MAP.hospital_domain_id
  is '机构域id值';
comment on column ATS_DICT.DICT_AGENCY_MAP.dict_domain_id
  is '字典域id值';
comment on column ATS_DICT.DICT_AGENCY_MAP.hospital_name
  is '机构名称';
comment on column ATS_DICT.DICT_AGENCY_MAP.dict_name
  is '字典名称';
comment on column ATS_DICT.DICT_AGENCY_MAP.custom1
  is '备用字段1';

prompt
prompt Creating table DICT_ALLERGEN
prompt ============================
prompt
create table ATS_DICT.DICT_ALLERGEN
(
  allergenii             VARCHAR2(128) not null,
  parentii               VARCHAR2(128) not null,
  allergenkindcode       VARCHAR2(10) not null,
  allergenkindcodesystem VARCHAR2(50),
  allergenkindcodename   VARCHAR2(30),
  nodekindcode           VARCHAR2(10) not null,
  nodekindsystem         VARCHAR2(50),
  nodekindname           VARCHAR2(30),
  name                   VARCHAR2(30) not null,
  spellcode              VARCHAR2(10),
  wbcode                 VARCHAR2(10),
  code                   VARCHAR2(30) not null,
  isdeleted              NUMBER(1) not null,
  rowversion             TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_ALLERGEN
  is '字典管理-过敏原';
comment on column ATS_DICT.DICT_ALLERGEN.allergenii
  is '过敏原的唯一标识';
comment on column ATS_DICT.DICT_ALLERGEN.parentii
  is '父节点';
comment on column ATS_DICT.DICT_ALLERGEN.allergenkindcode
  is '过敏原类型';
comment on column ATS_DICT.DICT_ALLERGEN.allergenkindcodesystem
  is '过敏原类型编码系统';
comment on column ATS_DICT.DICT_ALLERGEN.allergenkindcodename
  is '过敏原类型名称';
comment on column ATS_DICT.DICT_ALLERGEN.nodekindcode
  is '过敏原节点类型';
comment on column ATS_DICT.DICT_ALLERGEN.nodekindsystem
  is '过敏原节点类型编码系统';
comment on column ATS_DICT.DICT_ALLERGEN.nodekindname
  is '过敏原节点类型名称';
comment on column ATS_DICT.DICT_ALLERGEN.name
  is '名称';
comment on column ATS_DICT.DICT_ALLERGEN.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_ALLERGEN.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_ALLERGEN.code
  is '编码';
comment on column ATS_DICT.DICT_ALLERGEN.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_ALLERGEN.rowversion
  is '修改日期';
alter table ATS_DICT.DICT_ALLERGEN
  add constraint DICT_ALLERGEN_PK primary key (ALLERGENII);

prompt
prompt Creating table DICT_ANTIBACTERIALCATALOG
prompt ========================================
prompt
create table ATS_DICT.DICT_ANTIBACTERIALCATALOG
(
  antibacterialcatalogii VARCHAR2(128) not null,
  name                   VARCHAR2(50) not null,
  parentcatalogii        VARCHAR2(128) not null,
  isdeleted              NUMBER(1) not null,
  rowversion             TIMESTAMP(6) not null,
  code                   VARCHAR2(10)
)
;
comment on table ATS_DICT.DICT_ANTIBACTERIALCATALOG
  is '字典管理-抗生素目录';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOG.antibacterialcatalogii
  is '抗生素目录唯一标识';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOG.name
  is '名称';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOG.parentcatalogii
  is '父目录';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOG.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOG.rowversion
  is '修改时间';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOG.code
  is '编码';
alter table ATS_DICT.DICT_ANTIBACTERIALCATALOG
  add constraint DICT_ANTIBACTERIALCATALOG_PK primary key (ANTIBACTERIALCATALOGII);

prompt
prompt Creating table DICT_ANTIBACTERIALCATALOGITEM
prompt ============================================
prompt
create table ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM
(
  antibacterialcatalogii VARCHAR2(128) not null,
  medicineii             VARCHAR2(128) not null,
  ordinal                NUMBER not null,
  isdeleted              NUMBER(1) not null,
  rowversion             TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM
  is '字典管理-药品与抗生素目录的关联';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM.antibacterialcatalogii
  is '抗生素目录';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM.medicineii
  is '药品';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM.ordinal
  is '序号';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM.isdeleted
  is '内部自定已删除标记位';
comment on column ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_ANTIBACTERIALCATALOGITEM
  add constraint ANTIBACTERIALCATALOGITEM_PK primary key (ANTIBACTERIALCATALOGII);

prompt
prompt Creating table DICT_ARRANGECONSOLE
prompt ==================================
prompt
create table ATS_DICT.DICT_ARRANGECONSOLE
(
  opsdpcd    VARCHAR2(64) not null,
  deptcode   VARCHAR2(64) not null,
  week       VARCHAR2(1),
  limitedqty NUMBER(3),
  usedqty    NUMBER(3),
  opercode   VARCHAR2(6),
  operdate   TIMESTAMP(6)
)
;
comment on table ATS_DICT.DICT_ARRANGECONSOLE
  is '手术正台';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.opsdpcd
  is '1';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.deptcode
  is '2';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.week
  is '3';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.limitedqty
  is '4';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.usedqty
  is '5';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.opercode
  is '6';
comment on column ATS_DICT.DICT_ARRANGECONSOLE.operdate
  is '7';
alter table ATS_DICT.DICT_ARRANGECONSOLE
  add constraint ODW unique (OPSDPCD, DEPTCODE, WEEK);

prompt
prompt Creating table DICT_BED
prompt =======================
prompt
create table ATS_DICT.DICT_BED
(
  chuangweiid  VARCHAR2(6) not null,
  bingquid     VARCHAR2(10) not null,
  yuanquid     VARCHAR2(1) not null,
  keshiid      VARCHAR2(10),
  fangjianid   VARCHAR2(10),
  bingrenzyid  VARCHAR2(10),
  chuangweizt  VARCHAR2(4),
  shoufeixm    VARCHAR2(10),
  fenzudm      VARCHAR2(10),
  chuangweilx  VARCHAR2(4),
  bianzhifl    VARCHAR2(4),
  bingrenbz    VARCHAR2(4),
  zhuzhiys     VARCHAR2(10),
  xiugairen    VARCHAR2(10) not null,
  xiugaisj     DATE not null,
  zuofeibz     NUMBER(1),
  chuangweidj  VARCHAR2(10),
  zhanchuangbz NUMBER(1)
)
;
comment on table ATS_DICT.DICT_BED
  is '床位';
comment on column ATS_DICT.DICT_BED.chuangweiid
  is '床位ID';
comment on column ATS_DICT.DICT_BED.bingquid
  is '病区ID';
comment on column ATS_DICT.DICT_BED.yuanquid
  is '院区ID';
comment on column ATS_DICT.DICT_BED.keshiid
  is '科室ID';
comment on column ATS_DICT.DICT_BED.fangjianid
  is '房间ID';
comment on column ATS_DICT.DICT_BED.bingrenzyid
  is '病人住院号';
comment on column ATS_DICT.DICT_BED.chuangweizt
  is '床位状态';
comment on column ATS_DICT.DICT_BED.shoufeixm
  is '收费项目';
comment on column ATS_DICT.DICT_BED.fenzudm
  is '分组代码';
comment on column ATS_DICT.DICT_BED.chuangweilx
  is '床位类型';
comment on column ATS_DICT.DICT_BED.bianzhifl
  is '编制分类';
comment on column ATS_DICT.DICT_BED.bingrenbz
  is '病人标志';
comment on column ATS_DICT.DICT_BED.zhuzhiys
  is '主治医生';
comment on column ATS_DICT.DICT_BED.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_BED.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_BED.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_BED.chuangweidj
  is '床位登记';
comment on column ATS_DICT.DICT_BED.zhanchuangbz
  is '占床标志';

prompt
prompt Creating table DICT_BLOODELEMENT
prompt ================================
prompt
create table ATS_DICT.DICT_BLOODELEMENT
(
  bloodtypecode VARCHAR2(32) not null,
  bloodtypename VARCHAR2(128) not null,
  spellcode     VARCHAR2(32),
  wbcode        VARCHAR2(32),
  customecode   VARCHAR2(32),
  matchflag     VARCHAR2(32),
  uselife       NUMBER,
  temperature   NUMBER,
  stockunit     VARCHAR2(32),
  minamount     NUMBER,
  tradeprice    NUMBER,
  saleprice     NUMBER,
  validdays     VARCHAR2(32),
  validflag     VARCHAR2(32),
  sortid        NUMBER,
  memo          VARCHAR2(255),
  opercode      VARCHAR2(32),
  operdate      TIMESTAMP(6),
  isdeleted     VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_BLOODELEMENT
  is '字典管理-血液成分';
comment on column ATS_DICT.DICT_BLOODELEMENT.bloodtypecode
  is '血液成分代码';
comment on column ATS_DICT.DICT_BLOODELEMENT.bloodtypename
  is '血液成分名称';
comment on column ATS_DICT.DICT_BLOODELEMENT.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_BLOODELEMENT.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_BLOODELEMENT.customecode
  is '扩展码';
comment on column ATS_DICT.DICT_BLOODELEMENT.matchflag
  is '是否需要配血(0否 1是)';
comment on column ATS_DICT.DICT_BLOODELEMENT.uselife
  is '有效期(天数)';
comment on column ATS_DICT.DICT_BLOODELEMENT.temperature
  is '保存温度';
comment on column ATS_DICT.DICT_BLOODELEMENT.stockunit
  is '最小单位';
comment on column ATS_DICT.DICT_BLOODELEMENT.minamount
  is '最小计费数量';
comment on column ATS_DICT.DICT_BLOODELEMENT.tradeprice
  is '购入价';
comment on column ATS_DICT.DICT_BLOODELEMENT.saleprice
  is '零售价';
comment on column ATS_DICT.DICT_BLOODELEMENT.validdays
  is '申请单有效天数(0为一直有效)';
comment on column ATS_DICT.DICT_BLOODELEMENT.validflag
  is '项目有效性 1 有效 0 无效';
comment on column ATS_DICT.DICT_BLOODELEMENT.sortid
  is '血液成分序号';
comment on column ATS_DICT.DICT_BLOODELEMENT.memo
  is '备注';
comment on column ATS_DICT.DICT_BLOODELEMENT.opercode
  is '操作员';
comment on column ATS_DICT.DICT_BLOODELEMENT.operdate
  is '操作日期';
alter table ATS_DICT.DICT_BLOODELEMENT
  add constraint DICT_BLOODELEMENT_PK primary key (BLOODTYPECODE);

prompt
prompt Creating table DICT_BRAND
prompt =========================
prompt
create table ATS_DICT.DICT_BRAND
(
  brandii      VARCHAR2(128) not null,
  name         VARCHAR2(100) not null,
  code         VARCHAR2(100) not null,
  spellcode    VARCHAR2(50),
  wbcode       VARCHAR2(50),
  shortname    VARCHAR2(20),
  medicationii VARCHAR2(50) not null,
  producerii   VARCHAR2(128),
  note         VARCHAR2(50),
  isdeleted    NUMBER(1) not null,
  rowversion   TIMESTAMP(6) not null,
  englishname  VARCHAR2(50)
)
;
comment on table ATS_DICT.DICT_BRAND
  is '字典管理-商品名';
comment on column ATS_DICT.DICT_BRAND.brandii
  is '商品名的唯一标识';
comment on column ATS_DICT.DICT_BRAND.name
  is '名称';
comment on column ATS_DICT.DICT_BRAND.code
  is '编码';
comment on column ATS_DICT.DICT_BRAND.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_BRAND.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_BRAND.shortname
  is '短名称';
comment on column ATS_DICT.DICT_BRAND.medicationii
  is '药品';
comment on column ATS_DICT.DICT_BRAND.producerii
  is '厂家';
comment on column ATS_DICT.DICT_BRAND.note
  is '备注';
comment on column ATS_DICT.DICT_BRAND.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_BRAND.rowversion
  is '修改日期';
comment on column ATS_DICT.DICT_BRAND.englishname
  is '英文名';
alter table ATS_DICT.DICT_BRAND
  add constraint DICT_BRAND_PK primary key (BRANDII);

prompt
prompt Creating table DICT_CDA_PAYLOAD_MAP
prompt ===================================
prompt
create table ATS_DICT.DICT_CDA_PAYLOAD_MAP
(
  pay_load_type VARCHAR2(64) not null,
  detail        VARCHAR2(64),
  ccd_title     VARCHAR2(128),
  domain_id     VARCHAR2(128),
  domain_name   VARCHAR2(64)
)
;
alter table ATS_DICT.DICT_CDA_PAYLOAD_MAP
  add constraint PAYLOADTUPEUNQUE unique (PAY_LOAD_TYPE);

prompt
prompt Creating table DICT_CDA_SECTION_CODE
prompt ====================================
prompt
create table ATS_DICT.DICT_CDA_SECTION_CODE
(
  code      VARCHAR2(255) not null,
  display   VARCHAR2(255),
  comments  VARCHAR2(255),
  custom1   VARCHAR2(255),
  custom2   VARCHAR2(255),
  custom3   VARCHAR2(255),
  custom4   VARCHAR2(255),
  custom5   VARCHAR2(255),
  isdeleted VARCHAR2(4) default 0
)
;
alter table ATS_DICT.DICT_CDA_SECTION_CODE
  add primary key (CODE);

prompt
prompt Creating table DICT_CHECK_VALUE
prompt ===============================
prompt
create table ATS_DICT.DICT_CHECK_VALUE
(
  checked_pk   NUMBER not null,
  dict_id      VARCHAR2(32) not null,
  dict_key     VARCHAR2(32) not null,
  dict_value   VARCHAR2(32) not null,
  entity_field VARCHAR2(32)
)
;
comment on table ATS_DICT.DICT_CHECK_VALUE
  is '值域校验中字典和参数对照表';
comment on column ATS_DICT.DICT_CHECK_VALUE.checked_pk
  is '表主键';
comment on column ATS_DICT.DICT_CHECK_VALUE.dict_id
  is '字典表中字典编号';
comment on column ATS_DICT.DICT_CHECK_VALUE.dict_key
  is '字典名称';
comment on column ATS_DICT.DICT_CHECK_VALUE.dict_value
  is '字典值';
comment on column ATS_DICT.DICT_CHECK_VALUE.entity_field
  is '需要校验的字段';
alter table ATS_DICT.DICT_CHECK_VALUE
  add constraint CHECKED_PK_INDEX primary key (CHECKED_PK);

prompt
prompt Creating table DICT_COMPLEXPROJECT
prompt ==================================
prompt
create table ATS_DICT.DICT_COMPLEXPROJECT
(
  packagecode        VARCHAR2(12) not null,
  packagename        VARCHAR2(200),
  sysclass           VARCHAR2(3),
  spellcode          VARCHAR2(20),
  wbcode             VARCHAR2(20),
  inputcode          VARCHAR2(32),
  deptcode           VARCHAR2(200),
  sortid             NUMBER,
  confirmflag        VARCHAR2(1),
  validstate         VARCHAR2(1) not null,
  extflag            VARCHAR2(500),
  ext1flag           VARCHAR2(1),
  opercode           VARCHAR2(6) not null,
  operdate           TIMESTAMP(6) not null,
  mark               VARCHAR2(2000),
  mark1              VARCHAR2(2000),
  mark2              VARCHAR2(2000),
  mark3              VARCHAR2(2000),
  mark4              VARCHAR2(50),
  needbespeak        VARCHAR2(1),
  feecode            VARCHAR2(3),
  defaultsample      VARCHAR2(30),
  scopeofuse         VARCHAR2(10),
  deptlist           VARCHAR2(255),
  sysclasscodesystem VARCHAR2(64),
  feecodesystem      VARCHAR2(64),
  isdeleted          VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_COMPLEXPROJECT
  is '字典管理-复合项目(临床项目)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.packagecode
  is '组套编码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.packagename
  is '组套名称';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.sysclass
  is '系统类别';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.inputcode
  is '输入码(收费编号)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.deptcode
  is '执行科室编码 用 | 分隔';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.sortid
  is '顺序号';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.confirmflag
  is '确认标志 1 需要确认 0 不需要确认';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.validstate
  is '有效性标志 0 在用 1 停用 2 废弃';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.extflag
  is '出单';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.ext1flag
  is '扩展标志1';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.opercode
  is '操作员';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.mark
  is '备注';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.mark1
  is '病史及检查(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.mark2
  is '检查要求(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.mark3
  is '注意事项(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.mark4
  is '检查申请单名称';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.needbespeak
  is '是否需要预约 1 需要 0 不需要';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.defaultsample
  is 'LIS对照码(检验指标)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.scopeofuse
  is '使用范围：
0是全院
1是门诊
2是住院
';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.deptlist
  is '使用科室列表，以半角逗号分隔
如果该字段有值则忽略“使用范围”字段限制。
';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.sysclasscodesystem
  is '系统类别编码系统2.16.840.1.113883.4.487.2.1.1.1.59';
comment on column ATS_DICT.DICT_COMPLEXPROJECT.feecodesystem
  is '最小费用编码系统2.16.840.1.113883.4.487.2.1.1.1.60';
alter table ATS_DICT.DICT_COMPLEXPROJECT
  add constraint DICT_COMPLEXPROJECT_PK primary key (PACKAGECODE);

prompt
prompt Creating table DICT_COMPLEXPROJECT_TOTALPRICE
prompt =============================================
prompt
create table ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE
(
  operatetype        VARCHAR2(10),
  risid              VARCHAR2(20),
  packagecode        VARCHAR2(12) not null,
  packagename        VARCHAR2(200),
  sysclass           VARCHAR2(3),
  spellcode          VARCHAR2(20),
  wbcode             VARCHAR2(20),
  inputcode          VARCHAR2(32),
  exambody           VARCHAR2(10),
  exambodypy         VARCHAR2(20),
  exammainbody       VARCHAR2(20),
  exammethod         VARCHAR2(20),
  exammethodpy       VARCHAR2(20),
  examaddr           VARCHAR2(20),
  modalitytype       VARCHAR2(20),
  deptcode           VARCHAR2(200),
  opendepart         VARCHAR2(20),
  sortid             NUMBER,
  confirmflag        VARCHAR2(1),
  validstate         VARCHAR2(1),
  extflag            VARCHAR2(500),
  ext1flag           VARCHAR2(1),
  opercode           VARCHAR2(6),
  operdate           TIMESTAMP(6),
  mark               VARCHAR2(2000),
  mark1              VARCHAR2(2000),
  mark2              VARCHAR2(2000),
  mark3              VARCHAR2(2000),
  mark4              VARCHAR2(50),
  needbespeak        VARCHAR2(1),
  feecode            VARCHAR2(3),
  defaultsample      VARCHAR2(30),
  scopeofuse         VARCHAR2(10),
  deptlist           VARCHAR2(255),
  sysclasscodesystem VARCHAR2(64),
  feecodesystem      VARCHAR2(64),
  totalprice         VARCHAR2(200) default 0,
  totalprice1        VARCHAR2(200) default 0,
  totalprice2        VARCHAR2(200) default 0
)
;
comment on table ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE
  is '字典管理-复合项目(临床项目)总价表';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.operatetype
  is '操作类型';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.risid
  is 'RIS系统主键';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.packagecode
  is '组套编码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.packagename
  is '组套名称';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.sysclass
  is '系统类别';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.inputcode
  is '输入码(收费编号)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.exambody
  is '检查部位';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.exambodypy
  is '检查部位拼音';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.exammainbody
  is '检查大部位';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.exammethod
  is '检查方法';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.exammethodpy
  is '检查方法拼音';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.examaddr
  is '检查地点（暂时废弃）';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.modalitytype
  is '检查设备类型';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.deptcode
  is '执行科室编码 用 | 分隔';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.opendepart
  is '开单科室代码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.sortid
  is '顺序号';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.confirmflag
  is '确认标志 1 需要确认 0 不需要确认';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.validstate
  is '有效性标志 0 在用 1 停用 2 废弃';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.extflag
  is '出单';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.ext1flag
  is '扩展标志1';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.opercode
  is '操作员';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.mark
  is '备注';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.mark1
  is '病史及检查(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.mark2
  is '检查要求(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.mark3
  is '注意事项(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.mark4
  is '检查申请单名称';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.needbespeak
  is '是否需要预约 1 需要 0 不需要';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.defaultsample
  is 'LIS对照码(检验指标)';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.scopeofuse
  is '使用范围：
0是全院
1是门诊
2是住院
';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.deptlist
  is '使用科室列表，以半角逗号分隔
如果该字段有值则忽略“使用范围”字段限制。
';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.sysclasscodesystem
  is '系统类别编码系统2.16.840.1.113883.4.487.2.1.1.1.59';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.feecodesystem
  is '最小费用编码系统2.16.840.1.113883.4.487.2.1.1.1.60';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.totalprice
  is '三甲总价';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.totalprice1
  is '儿童总价';
comment on column ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE.totalprice2
  is '特诊总价';
create index ATS_DICT.IN_PAIN_TOTAL on ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE (PACKAGECODE, INPUTCODE);
alter table ATS_DICT.DICT_COMPLEXPROJECT_TOTALPRICE
  add constraint DICT_COMPLEXPROJECT_TOTAL_PK primary key (PACKAGECODE);

prompt
prompt Creating table DICT_COMPOUND_TERM_JC
prompt ====================================
prompt
create table ATS_DICT.DICT_COMPOUND_TERM_JC
(
  jianchaxmid    VARCHAR2(10) not null,
  jianchaxmmc    VARCHAR2(100) not null,
  jianchalx      VARCHAR2(10) not null,
  zhixingks      VARCHAR2(10),
  shuruma1       VARCHAR2(10),
  shuruma2       VARCHAR2(10),
  shuruma3       VARCHAR2(10),
  fuleiid        VARCHAR2(10),
  mojibz         NUMBER(1) default 0 not null,
  yuanqusy       VARCHAR2(10),
  menzhensy      NUMBER(1),
  zhuyuansy      NUMBER(1),
  zuofeibz       NUMBER(1) default 0,
  huajiabz       NUMBER(1),
  mobandm        VARCHAR2(50),
  jianchasm      VARCHAR2(500),
  daoyism        VARCHAR2(500),
  yuyuebz        NUMBER(1) default 0 not null,
  buweijjbz      NUMBER(1) default 0,
  youxiaobwsl    NUMBER(18,4) default 0,
  dangtianzdyyxz NUMBER(1) default 0,
  mobanjfbz      NUMBER(1) default 0,
  jizhensy       NUMBER(1) default 0
)
;
comment on table ATS_DICT.DICT_COMPOUND_TERM_JC
  is '公用_检查项目';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.jianchaxmid
  is '检查项目ID';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.jianchaxmmc
  is '检查项目名称';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.jianchalx
  is '检查类型';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.zhixingks
  is '执行科室';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.fuleiid
  is '父类ID';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.mojibz
  is '末级标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.yuanqusy
  is '院区使用';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.menzhensy
  is '门诊使用';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.zhuyuansy
  is '住院使用';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.huajiabz
  is '划价标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.mobandm
  is '模版代码';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.jianchasm
  is '检查说明';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.daoyism
  is '导医说明';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.yuyuebz
  is '预约标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.buweijjbz
  is '部位计价标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.youxiaobwsl
  is '有效部位数量';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.dangtianzdyyxz
  is '当天自动预约限制';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JC.mobanjfbz
  is '模板计费标志';
create index ATS_DICT.IDX_DICT_COMPOUND_TERM_JC_SRM1 on ATS_DICT.DICT_COMPOUND_TERM_JC (SHURUMA1);
create index ATS_DICT.IDX_DICT_COMPOUND_TERM_JC_SRM2 on ATS_DICT.DICT_COMPOUND_TERM_JC (SHURUMA2);
create index ATS_DICT.IDX_DICT_COMPOUND_TERM_JC_SRM3 on ATS_DICT.DICT_COMPOUND_TERM_JC (SHURUMA3);
alter table ATS_DICT.DICT_COMPOUND_TERM_JC
  add constraint PK_DICT_COMPOUND_TERM_JC primary key (JIANCHAXMID);

prompt
prompt Creating table DICT_COMPOUND_TERM_JY
prompt ====================================
prompt
create table ATS_DICT.DICT_COMPOUND_TERM_JY
(
  jianyanxmid VARCHAR2(10) not null,
  jianyanmd   VARCHAR2(100) not null,
  dayinmc     VARCHAR2(100),
  yangbenlx   VARCHAR2(10) not null,
  caijibw     VARCHAR2(10),
  zhixingks   VARCHAR2(10),
  menzhensy   NUMBER(1),
  zhuyuansy   NUMBER(1),
  jizhensy    NUMBER(1),
  taocanbz    NUMBER(1),
  rongqi      VARCHAR2(10),
  caijism     VARCHAR2(200),
  huayanfl    VARCHAR2(10),
  caixueliang VARCHAR2(20),
  caixuesj    VARCHAR2(100),
  caixuedd    VARCHAR2(100),
  qubaogdd    VARCHAR2(500),
  qubaogsj    VARCHAR2(500),
  jianyansm   VARCHAR2(500),
  xiugairen   VARCHAR2(10) not null,
  xiugaisj    DATE not null,
  zuofeibz    NUMBER(1),
  teshubz     NUMBER(1),
  zuhebh      VARCHAR2(10),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  daoyism     VARCHAR2(500),
  jianyanfl   VARCHAR2(10),
  bianyingylx VARCHAR2(2),
  waisongbz   NUMBER(1)
)
;
comment on table ATS_DICT.DICT_COMPOUND_TERM_JY
  is '公用_检验项目';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.jianyanxmid
  is '检验项目ID';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.jianyanmd
  is '检验目的';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.dayinmc
  is '打印名称';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.yangbenlx
  is '样本类型';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.caijibw
  is '采集部位';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.zhixingks
  is '执行科室';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.menzhensy
  is '门诊使用';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.zhuyuansy
  is '住院使用';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.jizhensy
  is '急诊使用';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.taocanbz
  is '套餐标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.rongqi
  is '容器';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.caijism
  is '采集说明';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.huayanfl
  is '化验分类';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.caixueliang
  is '采血量';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.caixuesj
  is '采血时间';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.caixuedd
  is '采血地点';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.qubaogdd
  is '取报告地点';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.qubaogsj
  is '取报告时间';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.jianyansm
  is '检验说明';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.teshubz
  is '特殊标志';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.zuhebh
  is '组合编号';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.daoyism
  is '导医说明';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.jianyanfl
  is '检验分类';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.bianyingylx
  is '变应原类型';
comment on column ATS_DICT.DICT_COMPOUND_TERM_JY.waisongbz
  is '外送标志';
alter table ATS_DICT.DICT_COMPOUND_TERM_JY
  add constraint PK_DICT_COMPOUND_TERM_JY primary key (JIANYANXMID);

prompt
prompt Creating table DICT_CONSTANT_DOMAIN
prompt ===================================
prompt
create table ATS_DICT.DICT_CONSTANT_DOMAIN
(
  pk                      NUMBER,
  dict_domain_id          VARCHAR2(64) not null,
  dict_domain_name        VARCHAR2(255) not null,
  dict_domain_comments    VARCHAR2(255),
  dict_domian_system_type VARCHAR2(255) not null,
  dict_domain_system_code VARCHAR2(64),
  dict_source             VARCHAR2(10) default 0,
  auto_update             VARCHAR2(10) default 0,
  isdeleted               VARCHAR2(10) default 0,
  custom1                 VARCHAR2(255),
  custom2                 VARCHAR2(255),
  custom3                 VARCHAR2(255),
  custom4                 VARCHAR2(255),
  custom5                 VARCHAR2(255),
  dict_type               VARCHAR2(64)
)
;
comment on table ATS_DICT.DICT_CONSTANT_DOMAIN
  is '各系统常数字典域表';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.pk
  is '主键';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_domain_id
  is '字典域ID';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_domain_name
  is '字典域名称';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_domain_comments
  is '字典域备注';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_domian_system_type
  is '字典域系统类型';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_domain_system_code
  is '字典域系统编码';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_source
  is '是否是字典源，1是；0否';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.auto_update
  is '是否自定更新，1是；0否';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.custom1
  is '备用1';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.custom2
  is '备用2';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.custom3
  is '备用3';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.custom4
  is '备用4';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.custom5
  is '备用5';
comment on column ATS_DICT.DICT_CONSTANT_DOMAIN.dict_type
  is '字典域类型';
create unique index ATS_DICT.DICT_CONSTANT_DOMAIN_PK on ATS_DICT.DICT_CONSTANT_DOMAIN (DICT_DOMAIN_ID);

prompt
prompt Creating table DICT_CONSTANT_MAP
prompt ================================
prompt
create table ATS_DICT.DICT_CONSTANT_MAP
(
  codesystem      VARCHAR2(64) not null,
  dict_name       VARCHAR2(64) not null,
  dict_type       VARCHAR2(64),
  code            VARCHAR2(64) not null,
  name            VARCHAR2(64) not null,
  spellcode       VARCHAR2(64),
  wbcode          VARCHAR2(64),
  mark            VARCHAR2(255),
  order_num       NUMBER,
  default_code    VARCHAR2(64),
  isdeleted       VARCHAR2(10) default 0,
  custom1         VARCHAR2(255),
  custom2         VARCHAR2(255),
  custom3         VARCHAR2(255),
  custom4         VARCHAR2(255),
  custom5         VARCHAR2(255),
  dict_table_name VARCHAR2(255),
  edit_operator   VARCHAR2(255),
  edit_time       TIMESTAMP(6),
  pk              NUMBER,
  version         VARCHAR2(64)
)
;
comment on table ATS_DICT.DICT_CONSTANT_MAP
  is '平台常数字典表';
comment on column ATS_DICT.DICT_CONSTANT_MAP.codesystem
  is '字典域ID';
comment on column ATS_DICT.DICT_CONSTANT_MAP.dict_name
  is '字典名称';
comment on column ATS_DICT.DICT_CONSTANT_MAP.dict_type
  is '字典类型';
comment on column ATS_DICT.DICT_CONSTANT_MAP.code
  is '编码';
comment on column ATS_DICT.DICT_CONSTANT_MAP.name
  is '编码说明';
comment on column ATS_DICT.DICT_CONSTANT_MAP.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_CONSTANT_MAP.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_CONSTANT_MAP.mark
  is '备注';
comment on column ATS_DICT.DICT_CONSTANT_MAP.order_num
  is '顺序ID';
comment on column ATS_DICT.DICT_CONSTANT_MAP.default_code
  is '默认编码';
comment on column ATS_DICT.DICT_CONSTANT_MAP.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_CONSTANT_MAP.custom1
  is '备用1';
comment on column ATS_DICT.DICT_CONSTANT_MAP.custom2
  is '备用2';
comment on column ATS_DICT.DICT_CONSTANT_MAP.custom3
  is '备用3';
comment on column ATS_DICT.DICT_CONSTANT_MAP.custom4
  is '备用4';
comment on column ATS_DICT.DICT_CONSTANT_MAP.custom5
  is '备用5';
comment on column ATS_DICT.DICT_CONSTANT_MAP.dict_table_name
  is '对应表字典的名称，给潘预留接口';
comment on column ATS_DICT.DICT_CONSTANT_MAP.edit_operator
  is '操作者名称';
comment on column ATS_DICT.DICT_CONSTANT_MAP.edit_time
  is '操作时间';
comment on column ATS_DICT.DICT_CONSTANT_MAP.pk
  is '常数字典PK';
comment on column ATS_DICT.DICT_CONSTANT_MAP.version
  is '版本';
create unique index ATS_DICT.DICT_CONSTANT_MAP_PK on ATS_DICT.DICT_CONSTANT_MAP (CODE, CODESYSTEM);

prompt
prompt Creating table DICT_CONSUME_MAPPING
prompt ===================================
prompt
create table ATS_DICT.DICT_CONSUME_MAPPING
(
  consume_pk          NUMBER not null,
  hiup_database       VARCHAR2(128) not null,
  hiup_tabel          VARCHAR2(128) not null,
  consume_database    VARCHAR2(128) not null,
  consume_tabel       VARCHAR2(128) not null,
  status              VARCHAR2(2) not null,
  hiup_database_pk    NUMBER,
  consume_database_pk NUMBER,
  source_name         VARCHAR2(128),
  source_pk           NUMBER
)
;
comment on table ATS_DICT.DICT_CONSUME_MAPPING
  is '平台字典表和字典消费者库表映射关系表';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.consume_pk
  is '表主键';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.hiup_database
  is '平台数据库名称';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.hiup_tabel
  is '平台数据库表名';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.consume_database
  is '字典源数据库';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.consume_tabel
  is '字典源表名称';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.status
  is '状态1=表示启用，0=表示停用';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.hiup_database_pk
  is '平台字典数据库对应pk';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.consume_database_pk
  is '消费字典数据库对应pk';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.source_name
  is '厂商名';
comment on column ATS_DICT.DICT_CONSUME_MAPPING.source_pk
  is '平台字典表和字典源库表映射关系表Dict_Consume_Mapping主键';
alter table ATS_DICT.DICT_CONSUME_MAPPING
  add primary key (CONSUME_PK);

prompt
prompt Creating table DICT_DB_MANAGE
prompt =============================
prompt
create table ATS_DICT.DICT_DB_MANAGE
(
  pk            NUMBER(10) not null,
  database_name VARCHAR2(120) not null,
  ip            VARCHAR2(200) not null,
  instance_name VARCHAR2(120),
  database_type VARCHAR2(120),
  port          VARCHAR2(120),
  user_name     VARCHAR2(120),
  user_pass     VARCHAR2(120),
  createdate    DATE,
  updatedate    DATE,
  isdeleted     VARCHAR2(2) default 0,
  source        VARCHAR2(10)
)
;
comment on table ATS_DICT.DICT_DB_MANAGE
  is '数据库信息管理';
comment on column ATS_DICT.DICT_DB_MANAGE.pk
  is '主键';
comment on column ATS_DICT.DICT_DB_MANAGE.database_name
  is '数据库名称';
comment on column ATS_DICT.DICT_DB_MANAGE.ip
  is '数据库ip地址';
comment on column ATS_DICT.DICT_DB_MANAGE.instance_name
  is '实例名称';
comment on column ATS_DICT.DICT_DB_MANAGE.database_type
  is '数据库类型（oracle,mysql,db2,selserver,redis,mongodb等等）';
comment on column ATS_DICT.DICT_DB_MANAGE.port
  is '端口号';
comment on column ATS_DICT.DICT_DB_MANAGE.user_name
  is '用户名';
comment on column ATS_DICT.DICT_DB_MANAGE.user_pass
  is '用户密码';
comment on column ATS_DICT.DICT_DB_MANAGE.createdate
  is '创建时间';
comment on column ATS_DICT.DICT_DB_MANAGE.updatedate
  is '最后一次修改时间';
comment on column ATS_DICT.DICT_DB_MANAGE.isdeleted
  is '删除标志---0正常，1删除';
comment on column ATS_DICT.DICT_DB_MANAGE.source
  is '区分配置信息是厂商或者平台';
alter table ATS_DICT.DICT_DB_MANAGE
  add primary key (PK, DATABASE_NAME, IP);

prompt
prompt Creating table DICT_DEPARTMENT
prompt ==============================
prompt
create table ATS_DICT.DICT_DEPARTMENT
(
  organizationii     VARCHAR2(128) not null,
  code               VARCHAR2(30) not null,
  spellcode          VARCHAR2(30),
  wbcode             VARCHAR2(30),
  name               VARCHAR2(100) not null,
  shortname          VARCHAR2(50),
  address            VARCHAR2(100),
  telephone          VARCHAR2(50),
  fax                VARCHAR2(50),
  rowversion         TIMESTAMP(6) not null,
  isdeleted          VARCHAR2(2) not null,
  isvirtual          VARCHAR2(2) not null,
  englishname        VARCHAR2(30),
  depttypecode       VARCHAR2(4),
  depttype           VARCHAR2(64),
  depttypecodesystem VARCHAR2(64),
  deptprocode        VARCHAR2(1) default 0,
  deptpro            VARCHAR2(64),
  deptprocodesystem  VARCHAR2(64),
  regdeptflag        VARCHAR2(1),
  tatdeptflag        VARCHAR2(1),
  employeeii         VARCHAR2(6) not null,
  js_status          NUMBER default 0,
  hlz_dept_code      VARCHAR2(30),
  hlz_dept_name      VARCHAR2(100)
)
;
comment on table ATS_DICT.DICT_DEPARTMENT
  is '字典管理-科室表';
comment on column ATS_DICT.DICT_DEPARTMENT.organizationii
  is '唯一标识';
comment on column ATS_DICT.DICT_DEPARTMENT.code
  is '编码';
comment on column ATS_DICT.DICT_DEPARTMENT.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_DEPARTMENT.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_DEPARTMENT.name
  is '名称';
comment on column ATS_DICT.DICT_DEPARTMENT.shortname
  is '简称';
comment on column ATS_DICT.DICT_DEPARTMENT.address
  is '地址';
comment on column ATS_DICT.DICT_DEPARTMENT.telephone
  is '电话';
comment on column ATS_DICT.DICT_DEPARTMENT.fax
  is '传真';
comment on column ATS_DICT.DICT_DEPARTMENT.rowversion
  is '最后修改时间';
comment on column ATS_DICT.DICT_DEPARTMENT.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_DEPARTMENT.isvirtual
  is '虚拟标志，0-实体,1-虚拟';
comment on column ATS_DICT.DICT_DEPARTMENT.englishname
  is '英文名称';
comment on column ATS_DICT.DICT_DEPARTMENT.depttypecode
  is '科室类型编码';
comment on column ATS_DICT.DICT_DEPARTMENT.depttype
  is '科室类型名称';
comment on column ATS_DICT.DICT_DEPARTMENT.depttypecodesystem
  is '科室类型编码系统,默认平台2.16.840.1.113883.4.487.2.1.1.1.22';
comment on column ATS_DICT.DICT_DEPARTMENT.deptprocode
  is '特殊科室属性编码';
comment on column ATS_DICT.DICT_DEPARTMENT.deptpro
  is '特殊科室属性名称';
comment on column ATS_DICT.DICT_DEPARTMENT.deptprocodesystem
  is '特殊科室属性编码系统，默认平台2.16.840.1.113883.4.487.2.1.1.1.23';
comment on column ATS_DICT.DICT_DEPARTMENT.regdeptflag
  is '是否挂号科室   0 - 假,  1 - 真';
comment on column ATS_DICT.DICT_DEPARTMENT.tatdeptflag
  is '是否核算科室   0 - 假,  1 - 真';
comment on column ATS_DICT.DICT_DEPARTMENT.employeeii
  is '操作员';
comment on column ATS_DICT.DICT_DEPARTMENT.js_status
  is '同步数据状态，0=初始化，1=成功，2=失败';
comment on column ATS_DICT.DICT_DEPARTMENT.hlz_dept_code
  is '科室代码(护理站)';
comment on column ATS_DICT.DICT_DEPARTMENT.hlz_dept_name
  is '科室名称(护理站)';
alter table ATS_DICT.DICT_DEPARTMENT
  add constraint ORGANIZATIONII primary key (ORGANIZATIONII);

prompt
prompt Creating table DICT_DEPARTMENT_JL
prompt =================================
prompt
create table ATS_DICT.DICT_DEPARTMENT_JL
(
  keshiid      VARCHAR2(10) not null,
  yuanquid     VARCHAR2(1),
  keshimc      VARCHAR2(100) not null,
  keshibm      VARCHAR2(100),
  hesuanks     VARCHAR2(10),
  chengbenks   VARCHAR2(10),
  weizhism     VARCHAR2(100),
  shuruma1     VARCHAR2(10),
  shuruma2     VARCHAR2(10),
  shuruma3     VARCHAR2(10),
  menzhenbz    NUMBER(1),
  zhuyuanbz    NUMBER(1),
  jizhenbz     NUMBER(1) not null,
  keshixz      VARCHAR2(4) not null,
  xingzhisx    VARCHAR2(50) not null,
  yaopinsyfw   NUMBER(1),
  yiliaosyfw   NUMBER(1),
  guahaofxm    VARCHAR2(10),
  zhenliaofxm  VARCHAR2(10),
  zuofeibz     NUMBER(1) not null,
  xiugairen    VARCHAR2(10) not null,
  xiugaisj     DATE not null,
  renshiks     VARCHAR2(10),
  pingjuncfxe  NUMBER(18,4),
  xianekzpl    VARCHAR2(4),
  shuyecfxyl   NUMBER(6),
  shuyecfxts   NUMBER(6),
  shuyexlkzpd  VARCHAR2(4),
  yingwenming  VARCHAR2(50),
  lianxidh     VARCHAR2(20),
  shangjiywks  VARCHAR2(10),
  zhenjikssj   DATE,
  zhenliaokssj DATE,
  koufukssj    DATE,
  zhenjiqdetl  VARCHAR2(4),
  ybcws        VARCHAR2(4)
)
;
comment on table ATS_DICT.DICT_DEPARTMENT_JL
  is '科室';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.keshiid
  is '科室ID';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.yuanquid
  is '院区ID';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.keshimc
  is '科室名称';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.keshibm
  is '科室别名';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.hesuanks
  is '核算科室';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.chengbenks
  is '成本科室';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.weizhism
  is '位置说明';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.menzhenbz
  is '门诊标志';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.zhuyuanbz
  is '住院标志';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.jizhenbz
  is '急诊标志';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.keshixz
  is '科室性质';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.xingzhisx
  is '性质属性';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.yaopinsyfw
  is '药品使用范围';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.yiliaosyfw
  is '医疗使用范围';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.guahaofxm
  is '挂号费项目';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.zhenliaofxm
  is '诊疗费项目';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.renshiks
  is '人事科室';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.pingjuncfxe
  is '平均处方限额';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.xianekzpl
  is '限额控制频率';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shuyecfxyl
  is '输液处方限用量';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shuyecfxts
  is '输液处方限天数';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shuyexlkzpd
  is '输液限量控制频度';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.yingwenming
  is '英文名';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.lianxidh
  is '联系电话';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.shangjiywks
  is '上级业务科室';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.zhenjikssj
  is '针剂开始时间';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.zhenliaokssj
  is '诊疗开始时间';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.koufukssj
  is '口服开始时间';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.zhenjiqdetl
  is '针剂取第二天量';
comment on column ATS_DICT.DICT_DEPARTMENT_JL.ybcws
  is '医保财务室';

prompt
prompt Creating table DICT_DIAGNOSE
prompt ============================
prompt
create table ATS_DICT.DICT_DIAGNOSE
(
  diseaseii             VARCHAR2(128) not null,
  diseasename           VARCHAR2(100) not null,
  icdcode               VARCHAR2(30),
  istcmdisease          VARCHAR2(1) default '0' not null,
  isdeleted             VARCHAR2(1) not null,
  rowversion            TIMESTAMP(6) not null,
  diseasecode           VARCHAR2(16),
  diseasecodename       VARCHAR2(64),
  diseasecodecodesystem VARCHAR2(64),
  disease30flag         VARCHAR2(1) default '0',
  infectflag            VARCHAR2(1) default '0',
  cancerflag            VARCHAR2(1) default '0',
  spellcode             VARCHAR2(20),
  wbcode                VARCHAR2(20),
  sextype               VARCHAR2(2),
  simplecode            VARCHAR2(20),
  simplename            VARCHAR2(150)
)
;
comment on table ATS_DICT.DICT_DIAGNOSE
  is '字典管理-诊断ICD';
comment on column ATS_DICT.DICT_DIAGNOSE.diseaseii
  is '唯一标识';
comment on column ATS_DICT.DICT_DIAGNOSE.diseasename
  is '疾病名称';
comment on column ATS_DICT.DICT_DIAGNOSE.icdcode
  is '疾病ICD码';
comment on column ATS_DICT.DICT_DIAGNOSE.istcmdisease
  is '是否中医诊断';
comment on column ATS_DICT.DICT_DIAGNOSE.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_DIAGNOSE.rowversion
  is '最后修改时间';
comment on column ATS_DICT.DICT_DIAGNOSE.diseasecode
  is '疾病分类编码';
comment on column ATS_DICT.DICT_DIAGNOSE.diseasecodename
  is '疾病分类名称';
comment on column ATS_DICT.DICT_DIAGNOSE.diseasecodecodesystem
  is '疾病分类编码系统2.16.840.1.113883.4.487.2.1.1.3.32';
comment on column ATS_DICT.DICT_DIAGNOSE.disease30flag
  is '30种疾病标志0 假 1 真';
comment on column ATS_DICT.DICT_DIAGNOSE.infectflag
  is '传染病标志0 假 1 真';
comment on column ATS_DICT.DICT_DIAGNOSE.cancerflag
  is '肿瘤标志0 假 1 真';
comment on column ATS_DICT.DICT_DIAGNOSE.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_DIAGNOSE.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_DIAGNOSE.sextype
  is '适用性别    A 适用于所有性别  M 只 适用于男性 F 只适用于女性';
comment on column ATS_DICT.DICT_DIAGNOSE.simplecode
  is '名称简写码';
comment on column ATS_DICT.DICT_DIAGNOSE.simplename
  is '名称简写';
alter table ATS_DICT.DICT_DIAGNOSE
  add constraint DICT_DIAGNOSE_PK primary key (DISEASEII);

prompt
prompt Creating table DICT_DISCHARGE_MODE
prompt ==================================
prompt
create table ATS_DICT.DICT_DISCHARGE_MODE
(
  shunxuhao NUMBER(6) not null,
  daimaid   VARCHAR2(10) not null,
  daimamc   VARCHAR2(100) not null,
  menzhensy NUMBER(1) not null,
  zhuyuansy NUMBER(1) not null,
  shuruma1  VARCHAR2(10),
  shuruma2  VARCHAR2(10),
  shuruma3  VARCHAR2(10),
  zuofeibz  NUMBER(1) not null
)
;
comment on table ATS_DICT.DICT_DISCHARGE_MODE
  is '出院方式表';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.daimaid
  is '代码ID';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.daimamc
  is '代码名称';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_DISCHARGE_MODE.zuofeibz
  is '作废标志';

prompt
prompt Creating table DICT_DOCUMENT_PATEINT_DOMAIN
prompt ===========================================
prompt
create table ATS_DICT.DICT_DOCUMENT_PATEINT_DOMAIN
(
  document_domain_id     VARCHAR2(64) not null,
  patient_main_domain_id VARCHAR2(64),
  comments               VARCHAR2(255),
  code                   VARCHAR2(64)
)
;
alter table ATS_DICT.DICT_DOCUMENT_PATEINT_DOMAIN
  add constraint DICT_DOC_PATEINT_DOMAIN_ID primary key (DOCUMENT_DOMAIN_ID);

prompt
prompt Creating table DICT_DOMAIN
prompt ==========================
prompt
create table ATS_DICT.DICT_DOMAIN
(
  id       NUMBER not null,
  type     VARCHAR2(128) not null,
  domainid VARCHAR2(128) not null,
  sortid   VARCHAR2(128),
  custom1  VARCHAR2(128),
  custom2  VARCHAR2(128),
  custom3  VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_DOMAIN
  is '字段对应的域Id';
comment on column ATS_DICT.DICT_DOMAIN.id
  is '表主键ID';
comment on column ATS_DICT.DICT_DOMAIN.type
  is '字典名称';
comment on column ATS_DICT.DICT_DOMAIN.domainid
  is '字典域';
comment on column ATS_DICT.DICT_DOMAIN.sortid
  is '类别';
comment on column ATS_DICT.DICT_DOMAIN.custom1
  is '备用字段1';
comment on column ATS_DICT.DICT_DOMAIN.custom2
  is '备用字段2';
comment on column ATS_DICT.DICT_DOMAIN.custom3
  is '备用字段3';

prompt
prompt Creating table DICT_DOSAGE_FORM
prompt ===============================
prompt
create table ATS_DICT.DICT_DOSAGE_FORM
(
  jixingid    VARCHAR2(10) not null,
  jixingmc    VARCHAR2(100) not null,
  yingwenming VARCHAR2(100),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  zuofeibz    NUMBER(1) not null,
  shunxuhao   NUMBER(6) not null
)
;
comment on table ATS_DICT.DICT_DOSAGE_FORM
  is '剂型表';
comment on column ATS_DICT.DICT_DOSAGE_FORM.jixingid
  is '剂型ID';
comment on column ATS_DICT.DICT_DOSAGE_FORM.jixingmc
  is '剂型名称';
comment on column ATS_DICT.DICT_DOSAGE_FORM.yingwenming
  is '英文名';
comment on column ATS_DICT.DICT_DOSAGE_FORM.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_DOSAGE_FORM.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_DOSAGE_FORM.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_DOSAGE_FORM.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_DOSAGE_FORM.shunxuhao
  is '顺序号';

prompt
prompt Creating table DICT_DRUGS
prompt =========================
prompt
create table ATS_DICT.DICT_DRUGS
(
  yaopinid    VARCHAR2(10) not null,
  shoufeixm   VARCHAR2(10),
  yaopinfl    VARCHAR2(10) not null,
  biaozhunbm  VARCHAR2(20) not null,
  yaopinmc    VARCHAR2(100) not null,
  yaopinlx    VARCHAR2(4) not null,
  kufanglx    VARCHAR2(4) not null,
  jizhenyybz  NUMBER(1) not null,
  kongzhiylbz VARCHAR2(10),
  zuofeibz    NUMBER(1) not null,
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  xiugairen   VARCHAR2(10),
  xiugaisj    DATE,
  dulifl      VARCHAR2(4) not null,
  jiazhifl    VARCHAR2(4) not null,
  zhijijx     VARCHAR2(10)
)
;
comment on table ATS_DICT.DICT_DRUGS
  is '药品';
comment on column ATS_DICT.DICT_DRUGS.yaopinid
  is '药品ID';
comment on column ATS_DICT.DICT_DRUGS.shoufeixm
  is '收费项目';
comment on column ATS_DICT.DICT_DRUGS.yaopinfl
  is '药品分类';
comment on column ATS_DICT.DICT_DRUGS.biaozhunbm
  is '标准编码';
comment on column ATS_DICT.DICT_DRUGS.yaopinmc
  is '药品名称';
comment on column ATS_DICT.DICT_DRUGS.yaopinlx
  is '药品类型';
comment on column ATS_DICT.DICT_DRUGS.kufanglx
  is '库房类型';
comment on column ATS_DICT.DICT_DRUGS.jizhenyybz
  is '急诊用药标志';
comment on column ATS_DICT.DICT_DRUGS.kongzhiylbz
  is '控制药量标志';
comment on column ATS_DICT.DICT_DRUGS.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_DRUGS.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_DRUGS.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_DRUGS.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_DRUGS.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_DRUGS.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_DRUGS.dulifl
  is '毒理分类';
comment on column ATS_DICT.DICT_DRUGS.jiazhifl
  is '价值分类';
comment on column ATS_DICT.DICT_DRUGS.zhijijx
  is '制剂剂型';

prompt
prompt Creating table DICT_ELSEWHEREMEDICARE
prompt =====================================
prompt
create table ATS_DICT.DICT_ELSEWHEREMEDICARE
(
  medicine_code VARCHAR2(128) not null,
  medicine_name VARCHAR2(256) not null,
  medicine_desc VARCHAR2(512),
  rowversion    TIMESTAMP(6),
  isdeleted     VARCHAR2(1) default 0
)
;
comment on table ATS_DICT.DICT_ELSEWHEREMEDICARE
  is '广东省内异地医保字典表';
comment on column ATS_DICT.DICT_ELSEWHEREMEDICARE.medicine_code
  is '广东省内异地医保字典编号（唯一性）';
comment on column ATS_DICT.DICT_ELSEWHEREMEDICARE.medicine_name
  is '广东省内异地医保字典名称';
comment on column ATS_DICT.DICT_ELSEWHEREMEDICARE.medicine_desc
  is '异地医保限制描述与备注';
alter table ATS_DICT.DICT_ELSEWHEREMEDICARE
  add constraint ELSEWHERE_MEDICINE_CODE primary key (MEDICINE_CODE);

prompt
prompt Creating table DICT_EMPLOYEES
prompt =============================
prompt
create table ATS_DICT.DICT_EMPLOYEES
(
  employeeii           VARCHAR2(128) not null,
  organizationii       VARCHAR2(128) not null,
  name                 VARCHAR2(30) not null,
  code                 VARCHAR2(10) not null,
  spellcode            VARCHAR2(30),
  wbcode               VARCHAR2(30),
  logonname            VARCHAR2(20),
  gendercode           VARCHAR2(20) not null,
  gendercodesystem     VARCHAR2(64) not null,
  gendername           VARCHAR2(30) not null,
  birthday             TIMESTAMP(6),
  isukeylogin          VARCHAR2(1) not null,
  ukeythumbprint       VARCHAR2(100),
  effectivestarttime   TIMESTAMP(6),
  effectiveendtime     TIMESTAMP(6),
  isdeleted            VARCHAR2(2) not null,
  rowversion           TIMESTAMP(6) not null,
  techpostcode         VARCHAR2(10),
  techpostcodesystem   VARCHAR2(64),
  techpostname         VARCHAR2(30),
  identitycard         VARCHAR2(18),
  dutycode             VARCHAR2(10),
  dutycodesystem       VARCHAR2(64),
  educatedcode         VARCHAR2(10),
  educatedcodesystem   VARCHAR2(64),
  educatedname         VARCHAR2(50),
  email                VARCHAR2(60),
  medicalcertificateno VARCHAR2(18),
  ntloginname          VARCHAR2(20),
  ondutycode           VARCHAR2(10),
  ondutycodesystem     VARCHAR2(64),
  ondutyname           VARCHAR2(50),
  phone                VARCHAR2(20),
  pyname               VARCHAR2(50),
  professioncode       VARCHAR2(10),
  professioncodesystem VARCHAR2(64),
  professionname       VARCHAR2(50),
  nurseorganizationii  VARCHAR2(128),
  empltype             VARCHAR2(10),
  expertflag           VARCHAR2(1),
  operemployeeii       VARCHAR2(128) not null,
  empltypecode         VARCHAR2(4),
  empltypecodesystem   VARCHAR2(64),
  dutyname             VARCHAR2(50),
  privdepts            VARCHAR2(100),
  password             VARCHAR2(50),
  username             VARCHAR2(50),
  usecode              VARCHAR2(50),
  hiup_status          VARCHAR2(255) default 0,
  start_time           TIMESTAMP(6),
  end_time             TIMESTAMP(6),
  retry                NUMBER,
  retry_time           TIMESTAMP(6),
  hiup_info            VARCHAR2(4000)
)
;
comment on table ATS_DICT.DICT_EMPLOYEES
  is '字典管理-员工';
comment on column ATS_DICT.DICT_EMPLOYEES.employeeii
  is '唯一标识符';
comment on column ATS_DICT.DICT_EMPLOYEES.organizationii
  is '员工所属科室的唯一标识符';
comment on column ATS_DICT.DICT_EMPLOYEES.name
  is '姓名';
comment on column ATS_DICT.DICT_EMPLOYEES.code
  is '工号';
comment on column ATS_DICT.DICT_EMPLOYEES.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_EMPLOYEES.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_EMPLOYEES.logonname
  is '登录名';
comment on column ATS_DICT.DICT_EMPLOYEES.gendercode
  is '性别编码';
comment on column ATS_DICT.DICT_EMPLOYEES.gendercodesystem
  is '性别编码系统';
comment on column ATS_DICT.DICT_EMPLOYEES.gendername
  is '性别名称';
comment on column ATS_DICT.DICT_EMPLOYEES.birthday
  is '出生日期';
comment on column ATS_DICT.DICT_EMPLOYEES.isukeylogin
  is '是否使用U盾登录';
comment on column ATS_DICT.DICT_EMPLOYEES.ukeythumbprint
  is 'U盾指纹';
comment on column ATS_DICT.DICT_EMPLOYEES.effectivestarttime
  is '启用时间';
comment on column ATS_DICT.DICT_EMPLOYEES.effectiveendtime
  is '结束时间';
comment on column ATS_DICT.DICT_EMPLOYEES.isdeleted
  is '删除标志，0 - 正常,1 - 已删除';
comment on column ATS_DICT.DICT_EMPLOYEES.rowversion
  is '最后修改时间';
comment on column ATS_DICT.DICT_EMPLOYEES.techpostcode
  is '职称编码';
comment on column ATS_DICT.DICT_EMPLOYEES.techpostcodesystem
  is '职称编码系统';
comment on column ATS_DICT.DICT_EMPLOYEES.techpostname
  is '职称名称';
comment on column ATS_DICT.DICT_EMPLOYEES.identitycard
  is '身份证';
comment on column ATS_DICT.DICT_EMPLOYEES.dutycode
  is '职务编码';
comment on column ATS_DICT.DICT_EMPLOYEES.dutycodesystem
  is '职务编码系统';
comment on column ATS_DICT.DICT_EMPLOYEES.educatedcode
  is '受教育程度';
comment on column ATS_DICT.DICT_EMPLOYEES.educatedcodesystem
  is '学历编码系统';
comment on column ATS_DICT.DICT_EMPLOYEES.educatedname
  is '学历名称';
comment on column ATS_DICT.DICT_EMPLOYEES.email
  is '邮件地址';
comment on column ATS_DICT.DICT_EMPLOYEES.medicalcertificateno
  is '医疗证号';
comment on column ATS_DICT.DICT_EMPLOYEES.ntloginname
  is '登录计算机的用户名';
comment on column ATS_DICT.DICT_EMPLOYEES.ondutycode
  is '在职编码';
comment on column ATS_DICT.DICT_EMPLOYEES.ondutycodesystem
  is '在职编码系统';
comment on column ATS_DICT.DICT_EMPLOYEES.ondutyname
  is '在职名称';
comment on column ATS_DICT.DICT_EMPLOYEES.phone
  is '联系电话';
comment on column ATS_DICT.DICT_EMPLOYEES.pyname
  is '拼音姓名';
comment on column ATS_DICT.DICT_EMPLOYEES.professioncode
  is '职业编码';
comment on column ATS_DICT.DICT_EMPLOYEES.professioncodesystem
  is '职业编码系统（默认为.16.840.1.113883.3.551.1.2.24）';
comment on column ATS_DICT.DICT_EMPLOYEES.professionname
  is '职业名称';
comment on column ATS_DICT.DICT_EMPLOYEES.nurseorganizationii
  is '所属护理站';
comment on column ATS_DICT.DICT_EMPLOYEES.empltype
  is '人员类型名称';
comment on column ATS_DICT.DICT_EMPLOYEES.expertflag
  is '是否专家';
comment on column ATS_DICT.DICT_EMPLOYEES.operemployeeii
  is '操作员';
comment on column ATS_DICT.DICT_EMPLOYEES.empltypecode
  is '人员类型编码';
comment on column ATS_DICT.DICT_EMPLOYEES.empltypecodesystem
  is '人员类型编码系统2.16.840.1.113883.4.487.2.1.1.1.30';
comment on column ATS_DICT.DICT_EMPLOYEES.dutyname
  is '职务名称';
comment on column ATS_DICT.DICT_EMPLOYEES.privdepts
  is '权限科室';
comment on column ATS_DICT.DICT_EMPLOYEES.password
  is '登录密码MD5';
comment on column ATS_DICT.DICT_EMPLOYEES.username
  is '用户工号(核对用)';
comment on column ATS_DICT.DICT_EMPLOYEES.usecode
  is '用户名';
comment on column ATS_DICT.DICT_EMPLOYEES.hiup_status
  is '状态';
comment on column ATS_DICT.DICT_EMPLOYEES.start_time
  is '开始时间';
comment on column ATS_DICT.DICT_EMPLOYEES.end_time
  is '结束时间';
comment on column ATS_DICT.DICT_EMPLOYEES.retry
  is '重试次数';
comment on column ATS_DICT.DICT_EMPLOYEES.retry_time
  is '上次重试时间';
comment on column ATS_DICT.DICT_EMPLOYEES.hiup_info
  is '出错详细信息';
alter table ATS_DICT.DICT_EMPLOYEES
  add constraint EMPLOYEEII_PK primary key (EMPLOYEEII);
alter table ATS_DICT.DICT_EMPLOYEES
  add constraint EMPLOYEE_CODE unique (CODE);

prompt
prompt Creating table DICT_FIELD_MAP
prompt =============================
prompt
create table ATS_DICT.DICT_FIELD_MAP
(
  pk                             NUMBER not null,
  hiup_dict_name                 VARCHAR2(16) not null,
  hiup_dict_type                 VARCHAR2(32),
  hiup_dict_domain_uid           VARCHAR2(64) not null,
  hiup_dict_code_value           VARCHAR2(16) not null,
  hiup_dict_code_comment         VARCHAR2(255),
  hiup_dict_code_value_field     VARCHAR2(32),
  hiup_dict_code_comment_field   VARCHAR2(32),
  system_domain_uid              VARCHAR2(64) not null,
  system_dict_domain_uid         VARCHAR2(64) not null,
  system_dict_code_value         VARCHAR2(16) not null,
  system_dict_code_comment       VARCHAR2(255),
  system_dict_code_value_field   VARCHAR2(32),
  system_dict_code_comment_field VARCHAR2(32),
  isdeleted                      VARCHAR2(10) default 0,
  custom1                        VARCHAR2(128),
  custom2                        VARCHAR2(128),
  custom3                        VARCHAR2(128),
  custom4                        VARCHAR2(128),
  custom5                        VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_FIELD_MAP
  is '各系统字典映射表';
comment on column ATS_DICT.DICT_FIELD_MAP.pk
  is '主键，自增';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_name
  is 'HIUP字典名称';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_type
  is 'HIUP字典类型，内部使用';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_domain_uid
  is 'HIUP字典域UID（编码系统）';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_code_value
  is 'HIUP字典编码';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_code_comment
  is 'HIUP字典编码说明';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_code_value_field
  is 'HIUP字典编码的字段名，内部使用';
comment on column ATS_DICT.DICT_FIELD_MAP.hiup_dict_code_comment_field
  is 'HIUP字典编码说明的字段名，内部使用';
comment on column ATS_DICT.DICT_FIELD_MAP.system_domain_uid
  is '各系统域UID，标识是哪一个系统';
comment on column ATS_DICT.DICT_FIELD_MAP.system_dict_domain_uid
  is '各系统字典域UID（编码系统），标识是哪一个编码系统';
comment on column ATS_DICT.DICT_FIELD_MAP.system_dict_code_value
  is '各系统字典编码';
comment on column ATS_DICT.DICT_FIELD_MAP.system_dict_code_comment
  is '各系统字典编码说明';
comment on column ATS_DICT.DICT_FIELD_MAP.system_dict_code_value_field
  is '各系统字典编码的字段名';
comment on column ATS_DICT.DICT_FIELD_MAP.system_dict_code_comment_field
  is '各系统字典编码说明的字段名';
comment on column ATS_DICT.DICT_FIELD_MAP.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_FIELD_MAP.custom1
  is '备用1';
comment on column ATS_DICT.DICT_FIELD_MAP.custom2
  is '备用2';
comment on column ATS_DICT.DICT_FIELD_MAP.custom3
  is '备用3';
comment on column ATS_DICT.DICT_FIELD_MAP.custom4
  is '备用4';
comment on column ATS_DICT.DICT_FIELD_MAP.custom5
  is '备用5';
alter table ATS_DICT.DICT_FIELD_MAP
  add constraint FIELD_MAP_PK primary key (PK);

prompt
prompt Creating table DICT_FREQUENCY
prompt =============================
prompt
create table ATS_DICT.DICT_FREQUENCY
(
  frequencyii            VARCHAR2(128) not null,
  name                   VARCHAR2(60) not null,
  code                   VARCHAR2(10) not null,
  standardexectimeseries VARCHAR2(300),
  isdeleted              NUMBER(1) not null,
  rowversion             TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_FREQUENCY
  is '字典管理-频次/用药频率';
comment on column ATS_DICT.DICT_FREQUENCY.frequencyii
  is '唯一标识';
comment on column ATS_DICT.DICT_FREQUENCY.name
  is '名称';
comment on column ATS_DICT.DICT_FREQUENCY.code
  is '编码';
comment on column ATS_DICT.DICT_FREQUENCY.standardexectimeseries
  is '标准执行时间（用;分隔）08:00;12:00;16:00;20:00';
comment on column ATS_DICT.DICT_FREQUENCY.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_FREQUENCY.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_FREQUENCY
  add constraint DICT_FREQUENCY_PK primary key (FREQUENCYII);

prompt
prompt Creating table DICT_GENDER
prompt ==========================
prompt
create table ATS_DICT.DICT_GENDER
(
  shunxuhao NUMBER(6) not null,
  daimaid   VARCHAR2(10) not null,
  daimamc   VARCHAR2(100) not null,
  menzhensy NUMBER(1) not null,
  zhuyuansy NUMBER(1) not null,
  shuruma1  VARCHAR2(10),
  shuruma2  VARCHAR2(10),
  shuruma3  VARCHAR2(10),
  zuofeibz  NUMBER(1) not null
)
;
comment on table ATS_DICT.DICT_GENDER
  is '性别表';
comment on column ATS_DICT.DICT_GENDER.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_GENDER.daimaid
  is '代码ID';
comment on column ATS_DICT.DICT_GENDER.daimamc
  is '代码名称';
comment on column ATS_DICT.DICT_GENDER.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_GENDER.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_GENDER.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_GENDER.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_GENDER.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_GENDER.zuofeibz
  is '作废标志';

prompt
prompt Creating table DICT_HUITONG_DEPARTMENT
prompt ======================================
prompt
create table ATS_DICT.DICT_HUITONG_DEPARTMENT
(
  organizationii VARCHAR2(128) not null,
  code           VARCHAR2(30) not null,
  name           VARCHAR2(100) not null,
  hiscode        VARCHAR2(30)
)
;
comment on column ATS_DICT.DICT_HUITONG_DEPARTMENT.organizationii
  is '慧通科室编号
';
comment on column ATS_DICT.DICT_HUITONG_DEPARTMENT.code
  is '慧通科室编号
';
comment on column ATS_DICT.DICT_HUITONG_DEPARTMENT.name
  is '慧通科室名称
';
comment on column ATS_DICT.DICT_HUITONG_DEPARTMENT.hiscode
  is 'HIS科室编号
';

prompt
prompt Creating table DICT_ICD10
prompt =========================
prompt
create table ATS_DICT.DICT_ICD10
(
  jibingid    VARCHAR2(10) not null,
  jibingmc    VARCHAR2(100) not null,
  jici        NUMBER(6),
  mojibz      NUMBER(1) not null,
  fuleijb     VARCHAR2(10),
  icd9        VARCHAR2(20),
  icd10       VARCHAR2(20),
  jibingfl    VARCHAR2(50),
  tongjima    VARCHAR2(100),
  baokalx     VARCHAR2(500),
  tishiwz     VARCHAR2(500),
  zhongyizdfl VARCHAR2(100),
  zhuming     NUMBER(1),
  zhongyizdbz NUMBER(1),
  baokafs     NUMBER(1),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  menzhensy   NUMBER(1) not null,
  zhuyuansy   NUMBER(1) not null,
  zuofeibz    NUMBER(1) not null,
  shunxuhao   NUMBER(6) not null
)
;
comment on table ATS_DICT.DICT_ICD10
  is 'ICD10表';
comment on column ATS_DICT.DICT_ICD10.jibingid
  is '疾病ID';
comment on column ATS_DICT.DICT_ICD10.jibingmc
  is '疾病名称';
comment on column ATS_DICT.DICT_ICD10.jici
  is '几次';
comment on column ATS_DICT.DICT_ICD10.mojibz
  is '膜剂标志';
comment on column ATS_DICT.DICT_ICD10.fuleijb
  is '父类疾病';
comment on column ATS_DICT.DICT_ICD10.icd9
  is 'ICU9';
comment on column ATS_DICT.DICT_ICD10.icd10
  is 'ICU10';
comment on column ATS_DICT.DICT_ICD10.jibingfl
  is '疾病分类';
comment on column ATS_DICT.DICT_ICD10.tongjima
  is '统计码';
comment on column ATS_DICT.DICT_ICD10.baokalx
  is '报卡类型';
comment on column ATS_DICT.DICT_ICD10.tishiwz
  is '提示位置';
comment on column ATS_DICT.DICT_ICD10.zhongyizdfl
  is '中医诊断分类';
comment on column ATS_DICT.DICT_ICD10.zhuming
  is '注明';
comment on column ATS_DICT.DICT_ICD10.zhongyizdbz
  is '中医诊断标志';
comment on column ATS_DICT.DICT_ICD10.baokafs
  is '报卡方式';
comment on column ATS_DICT.DICT_ICD10.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_ICD10.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_ICD10.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_ICD10.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_ICD10.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_ICD10.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_ICD10.shunxuhao
  is '顺序号';

prompt
prompt Creating table DICT_ICD9
prompt ========================
prompt
create table ATS_DICT.DICT_ICD9
(
  shoushumcid VARCHAR2(10) not null,
  shoushumc   VARCHAR2(100) not null,
  shoushuzy   VARCHAR2(10),
  shoushujb   VARCHAR2(10),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  zuofeibz    NUMBER(1) not null,
  xiugairen   VARCHAR2(10),
  xiugaisj    DATE,
  shoushuyssj NUMBER(18,4),
  shoushuysfy NUMBER(18,4),
  zhuanyeyyid VARCHAR2(4),
  jianchaxmid VARCHAR2(10),
  zhuanyefl   VARCHAR2(10),
  shoushuywmc VARCHAR2(100),
  binganssxh  NUMBER(10),
  icd9        VARCHAR2(20),
  xinkaizbz   NUMBER(1),
  shoushufl   VARCHAR2(20),
  fuleiid     VARCHAR2(10),
  mojibz      VARCHAR2(10)
)
;
comment on table ATS_DICT.DICT_ICD9
  is 'ICD9';
comment on column ATS_DICT.DICT_ICD9.shoushumcid
  is '手术名称ID';
comment on column ATS_DICT.DICT_ICD9.shoushumc
  is '手术名称';
comment on column ATS_DICT.DICT_ICD9.shoushuzy
  is '手术专业';
comment on column ATS_DICT.DICT_ICD9.shoushujb
  is '手术级别';
comment on column ATS_DICT.DICT_ICD9.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_ICD9.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_ICD9.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_ICD9.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_ICD9.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_ICD9.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_ICD9.shoushuyssj
  is '手术预算时间';
comment on column ATS_DICT.DICT_ICD9.shoushuysfy
  is '手术预算费用';
comment on column ATS_DICT.DICT_ICD9.zhuanyeyyid
  is '专业应用ID';
comment on column ATS_DICT.DICT_ICD9.jianchaxmid
  is '检查项目ID';
comment on column ATS_DICT.DICT_ICD9.zhuanyefl
  is '专业分类';
comment on column ATS_DICT.DICT_ICD9.shoushuywmc
  is '手术英文名称';
comment on column ATS_DICT.DICT_ICD9.binganssxh
  is '病案手术序号';
comment on column ATS_DICT.DICT_ICD9.icd9
  is 'ICD9';
comment on column ATS_DICT.DICT_ICD9.xinkaizbz
  is 'XINKAIZBZ';
comment on column ATS_DICT.DICT_ICD9.shoushufl
  is '手术分类';
comment on column ATS_DICT.DICT_ICD9.fuleiid
  is '父类ID';
comment on column ATS_DICT.DICT_ICD9.mojibz
  is '膜剂标志';

prompt
prompt Creating table DICT_INAVENUE
prompt ============================
prompt
create table ATS_DICT.DICT_INAVENUE
(
  his_code VARCHAR2(10),
  his_name VARCHAR2(10),
  xin_code VARCHAR2(10),
  xin_name VARCHAR2(10)
)
;
comment on column ATS_DICT.DICT_INAVENUE.his_code
  is 'HIS厂商产生的入院来源CODE';
comment on column ATS_DICT.DICT_INAVENUE.his_name
  is 'HIS厂商产生的入院来源NAME';
comment on column ATS_DICT.DICT_INAVENUE.xin_code
  is '病案科产生的入院来源CODE';
comment on column ATS_DICT.DICT_INAVENUE.xin_name
  is '病案科产生的入院来源NAME';

prompt
prompt Creating table DICT_ITEM
prompt ========================
prompt
create table ATS_DICT.DICT_ITEM
(
  item_pk             NUMBER not null,
  dict_pk             NUMBER not null,
  key                 VARCHAR2(124) not null,
  text                VARCHAR2(124),
  type_class_fication VARCHAR2(124),
  patient_use_type    VARCHAR2(1),
  spell_code          VARCHAR2(32),
  fivetyping_code     VARCHAR2(32),
  other_code          VARCHAR2(32),
  effective_flag      VARCHAR2(1) default 1,
  py_code             VARCHAR2(32),
  center_key          VARCHAR2(32)
)
;
comment on table ATS_DICT.DICT_ITEM
  is '字典项信息';
comment on column ATS_DICT.DICT_ITEM.item_pk
  is '表主键';
comment on column ATS_DICT.DICT_ITEM.dict_pk
  is 'dict字典表主键';
comment on column ATS_DICT.DICT_ITEM.key
  is '名称';
comment on column ATS_DICT.DICT_ITEM.text
  is '编码';
comment on column ATS_DICT.DICT_ITEM.patient_use_type
  is '患者使用类型标识:0 无限制
,1 仅门诊患者使用,
2 仅住院患者使用
';
comment on column ATS_DICT.DICT_ITEM.spell_code
  is '拼音码';
comment on column ATS_DICT.DICT_ITEM.fivetyping_code
  is '五笔码';
comment on column ATS_DICT.DICT_ITEM.other_code
  is '其他编码';
comment on column ATS_DICT.DICT_ITEM.effective_flag
  is '是否有效：1有效，0无效';
comment on column ATS_DICT.DICT_ITEM.py_code
  is '编码';
alter table ATS_DICT.DICT_ITEM
  add constraint ITEM_PK_INDEX primary key (ITEM_PK);

prompt
prompt Creating table DICT_JY_COMPLEXPROJECT
prompt =====================================
prompt
create table ATS_DICT.DICT_JY_COMPLEXPROJECT
(
  complexproject_pk  NUMBER not null,
  itemid             VARCHAR2(64) not null,
  packagecode        VARCHAR2(128),
  packagename        VARCHAR2(128),
  sysclass           VARCHAR2(128),
  spellcode          VARCHAR2(128),
  wbcode             VARCHAR2(128),
  inputcode          VARCHAR2(128),
  deptcode           VARCHAR2(128),
  deptscope          VARCHAR2(512),
  sample             VARCHAR2(128),
  samplescope        VARCHAR2(128),
  sortid             VARCHAR2(128),
  confirmflag        VARCHAR2(128),
  validstate         VARCHAR2(128),
  extflag            VARCHAR2(128),
  ext1flag           VARCHAR2(128),
  opercode           VARCHAR2(128),
  operdate           TIMESTAMP(6),
  mark               VARCHAR2(128),
  mark1              VARCHAR2(128),
  mark2              VARCHAR2(128),
  mark3              VARCHAR2(128),
  mark4              VARCHAR2(128),
  needbespeak        VARCHAR2(128),
  feecode            VARCHAR2(128),
  defaultsample      VARCHAR2(128),
  scopeofuse         VARCHAR2(128),
  sysclasscodesystem VARCHAR2(128),
  feecodesystem      VARCHAR2(128),
  deptlist           CLOB
)
;
comment on table ATS_DICT.DICT_JY_COMPLEXPROJECT
  is '检验复合收费项目中的复合字典项';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.complexproject_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.itemid
  is '检验复合收费项目主表（DICT_DICTASYNCQUERYITEM）的唯一标识字段';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.packagecode
  is '组套编码';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.packagename
  is '组套名称';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.sysclass
  is '系统类别';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.inputcode
  is '输入码(收费编号)';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.deptcode
  is '执行科室编码';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.deptscope
  is '科室范围';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.sample
  is '默认样本';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.samplescope
  is '样本范围';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.sortid
  is '顺序号';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.confirmflag
  is '确认标志1需要确认0不需要确认';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.validstate
  is '有效性标志0在用1停用2废弃';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.extflag
  is '出单';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.ext1flag
  is '扩展标志1';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.opercode
  is '操作员编号';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.mark
  is '备注';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.mark1
  is '病史及检查(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.mark2
  is '检查要求(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.mark3
  is '注意事项(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.mark4
  is '检查申请单名称';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.needbespeak
  is '是否需要预约1需要0不需要';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.defaultsample
  is 'Lis对照码';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.scopeofuse
  is '使用范围';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.sysclasscodesystem
  is '病案费用类';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.feecodesystem
  is '病案费用类';
comment on column ATS_DICT.DICT_JY_COMPLEXPROJECT.deptlist
  is '使用科室列表';
create index ATS_DICT.COMLEXPROJECT_ITEMID on ATS_DICT.DICT_JY_COMPLEXPROJECT (ITEMID);
alter table ATS_DICT.DICT_JY_COMPLEXPROJECT
  add constraint COMPLEXPROJECT_PK primary key (COMPLEXPROJECT_PK);

prompt
prompt Creating table DICT_JY_DICTASYNCQUERYITEM
prompt =========================================
prompt
create table ATS_DICT.DICT_JY_DICTASYNCQUERYITEM
(
  dictasyncqueryitem_pk NUMBER not null,
  itemid                VARCHAR2(64) not null,
  dicttype              VARCHAR2(128),
  operatetype           VARCHAR2(64)
)
;
comment on table ATS_DICT.DICT_JY_DICTASYNCQUERYITEM
  is '检验复合收费项目字典主表';
comment on column ATS_DICT.DICT_JY_DICTASYNCQUERYITEM.dictasyncqueryitem_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_DICTASYNCQUERYITEM.itemid
  is 'ItemID每条记录唯一性标识';
comment on column ATS_DICT.DICT_JY_DICTASYNCQUERYITEM.dicttype
  is '字典类型描述';
comment on column ATS_DICT.DICT_JY_DICTASYNCQUERYITEM.operatetype
  is '字典操作类型。ADD、UPDATE、del';
create index ATS_DICT.ASYNCQUER_ITEMID_INDEX on ATS_DICT.DICT_JY_DICTASYNCQUERYITEM (ITEMID);
alter table ATS_DICT.DICT_JY_DICTASYNCQUERYITEM
  add constraint DICTASYNCQUERYITEM_PK primary key (DICTASYNCQUERYITEM_PK);

prompt
prompt Creating table DICT_JY_INFO_ZSYYSOURCEINFO
prompt ==========================================
prompt
create table ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO
(
  zsyysourceinfo_pk  NUMBER not null,
  zsyyprofileinfo_pk NUMBER not null,
  itemid             VARCHAR2(64) not null,
  sourcecode         VARCHAR2(128),
  sourcename         VARCHAR2(128),
  isdefault          VARCHAR2(128),
  testid             VARCHAR2(128)
)
;
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.zsyysourceinfo_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.zsyyprofileinfo_pk
  is 'DICT_JY_ZSYYPROFILEINFO表中的主键';
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.itemid
  is '主表（DICT_JY_DICTASYNCQUERYITEM）中的ItemID唯一标识字段';
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.sourcecode
  is '标本来源编码';
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.sourcename
  is '标本来源名称';
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.isdefault
  is '是否默认的标本来源';
comment on column ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO.testid
  is 'DICT_JY_ZSYYPROFILEINFO表中的testid字段';
create index ATS_DICT.TESTID_INDEX on ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO (TESTID);
create index ATS_DICT.ZSYYPROFILEINFO_INDEX on ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO (ZSYYPROFILEINFO_PK);
create index ATS_DICT.ZSYYSOURCEINFO_ITEMID on ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO (ITEMID);
alter table ATS_DICT.DICT_JY_INFO_ZSYYSOURCEINFO
  add constraint ZSYYSOURCEINFO_PK primary key (ZSYYSOURCEINFO_PK);

prompt
prompt Creating table DICT_JY_NONPHARDETAILS
prompt =====================================
prompt
create table ATS_DICT.DICT_JY_NONPHARDETAILS
(
  nonphardetails_pk  NUMBER not null,
  itemid             VARCHAR2(64) not null,
  itemcode           VARCHAR2(128),
  itemname           VARCHAR2(128),
  sysclass           VARCHAR2(128),
  feecode            VARCHAR2(128),
  inputcode          VARCHAR2(128),
  spellcode          VARCHAR2(128),
  wbcode             VARCHAR2(128),
  gbcode             VARCHAR2(128),
  internationalcode  VARCHAR2(128),
  unitprice          VARCHAR2(128),
  unitprice1         VARCHAR2(128),
  unitprice2         VARCHAR2(128),
  unitprice3         VARCHAR2(128),
  unitprice4         VARCHAR2(128),
  emergscale         VARCHAR2(128),
  stockunit          VARCHAR2(128),
  itemnum            VARCHAR2(128),
  specialflag        VARCHAR2(128),
  specialflag1       VARCHAR2(128),
  specialflag2       VARCHAR2(128),
  specialflag3       VARCHAR2(128),
  specialflag4       VARCHAR2(128),
  familyplane        VARCHAR2(128),
  specialitem        VARCHAR2(128),
  itemgrade          VARCHAR2(128),
  confirmflag        VARCHAR2(128),
  validstate         VARCHAR2(128),
  specs              VARCHAR2(128),
  exedeptcode        VARCHAR2(128),
  facilityno         VARCHAR2(128),
  defaultsample      VARCHAR2(128),
  operatecode        VARCHAR2(128),
  operatekind        VARCHAR2(128),
  operatetype        VARCHAR2(128),
  collateflag        VARCHAR2(128),
  mark               VARCHAR2(128),
  opercode           VARCHAR2(128),
  operdate           TIMESTAMP(6),
  diseaseclass       VARCHAR2(128),
  specialdept        VARCHAR2(128),
  consentflag        VARCHAR2(128),
  mark1              VARCHAR2(128),
  mark2              VARCHAR2(128),
  mark3              VARCHAR2(128),
  mark4              VARCHAR2(128),
  needbespeak        VARCHAR2(128),
  itemarea           VARCHAR2(128),
  itemnoarea         VARCHAR2(128),
  medinsurance       VARCHAR2(128),
  bafylb             VARCHAR2(128),
  sysclasscodesystem VARCHAR2(128),
  feecodesystem      VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_JY_NONPHARDETAILS
  is '检验复合收费项目字典中Nonphardetails信息';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.nonphardetails_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemid
  is '主表（DICT_JY_DICTASYNCQUERYITEM）中的ItemID唯一标识字段';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemcode
  is '非药品编码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemname
  is '非药品名称';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.sysclass
  is '系统类别';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.inputcode
  is '输入码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.gbcode
  is '国家编码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.internationalcode
  is '国际标准代码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.unitprice
  is '三甲价';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.unitprice1
  is '儿童价';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.unitprice2
  is '特诊价';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.unitprice3
  is '单价2';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.unitprice4
  is '材料入货价';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.emergscale
  is '急诊加成比例';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.stockunit
  is '单位';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemnum
  is '数量';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialflag
  is '省限制0不限制1限制';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialflag1
  is '市限制0不限制1限制';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialflag2
  is '自费项目0假1真';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialflag3
  is '特定治疗项目0假1真';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialflag4
  is '中山一：是否强制出单';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.familyplane
  is '计划生育标记0假1真';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialitem
  is '此字段跟specialflag3重复，现在没用';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemgrade
  is '甲乙类标志1甲2乙';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.confirmflag
  is '确认标志0不需要确认1需要确认';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.validstate
  is '有效性标识0在用1停用2废弃';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specs
  is '规格';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.exedeptcode
  is '执行科室';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.facilityno
  is '设备编号用|区分';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.defaultsample
  is '默认检查部位或标本';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.operatecode
  is '手术编码';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.operatekind
  is '手术分类';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.operatetype
  is '手术规模';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.collateflag
  is '是否有物资项目与之对照(1有，0没有)';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.mark
  is '备注';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.opercode
  is '操作员';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.diseaseclass
  is '疾病分类(开立检验项目时使用)';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.specialdept
  is '专科名称(开立检验项目时使用)';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.consentflag
  is '是否需要打印知情同意书（0需要，1不需要）';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.mark1
  is '病史及检查(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.mark2
  is '检查要求(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.mark3
  is '注意事项(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.mark4
  is '检查申请单名称';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.needbespeak
  is '是否需要预约1需要0不需要';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemarea
  is '项目范围';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.itemnoarea
  is '项目列外';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.medinsurance
  is '医保信息';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.bafylb
  is '病案费用类';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.sysclasscodesystem
  is '病案费用类';
comment on column ATS_DICT.DICT_JY_NONPHARDETAILS.feecodesystem
  is '病案费用类';
create index ATS_DICT.NONPHARDETAILS_ITEMID on ATS_DICT.DICT_JY_NONPHARDETAILS (ITEMID);
alter table ATS_DICT.DICT_JY_NONPHARDETAILS
  add constraint NONPHARDETAILS_PK primary key (NONPHARDETAILS_PK);

prompt
prompt Creating table DICT_JY_TEST_ZSYYSOURCEINFO
prompt ==========================================
prompt
create table ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO
(
  test_zsyysourceinfo_pk NUMBER not null,
  zsyysingletestinfo_pk  NUMBER not null,
  itemid                 VARCHAR2(64) not null,
  testid                 VARCHAR2(128),
  sourcecode             VARCHAR2(128),
  sourcename             VARCHAR2(218),
  isdefault              VARCHAR2(2)
)
;
comment on table ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO
  is '检验复合收费项目字典中的ZSYYSingleTestInfo的子项ZSYYSourceInfo';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.test_zsyysourceinfo_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.zsyysingletestinfo_pk
  is 'DICT_JY_ZSYYSINGLETESTINFO表主键';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.itemid
  is '主表（DICT_JY_DICTASYNCQUERYITEM）中的ItemID唯一标识字段';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.testid
  is 'DICT_JY_ZSYYSINGLETESTINFO表中的项目内码';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.sourcecode
  is '标本来源编码';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.sourcename
  is '标本来源名称';
comment on column ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO.isdefault
  is '是否默认的标本来源';
alter table ATS_DICT.DICT_JY_TEST_ZSYYSOURCEINFO
  add constraint TEST_ZSYYSOURCEINFO_PK primary key (TEST_ZSYYSOURCEINFO_PK);

prompt
prompt Creating table DICT_JY_ZSYYDEPTGROUPINFO
prompt ========================================
prompt
create table ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO
(
  zsyydeptgroupinfo_pk NUMBER not null,
  zsyyprofileinfo_pk   NUMBER not null,
  itemid               VARCHAR2(64) not null,
  testid               VARCHAR2(128),
  deptgroupcode        VARCHAR2(128),
  deptgroupname        VARCHAR2(128),
  isdefault            VARCHAR2(64)
)
;
comment on table ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO
  is '检验复合收费项目字典ZSYYDeptGroupInfo专业组信息';
comment on column ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO.zsyyprofileinfo_pk
  is 'DICT_JY_ZSYYPROFILEINFO表中的主键';
comment on column ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO.itemid
  is '主表（DICT_JY_DICTASYNCQUERYITEM）中的ItemID唯一标识字段';
comment on column ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO.testid
  is 'DICT_JY_ZSYYPROFILEINFO表中的testid字段';
comment on column ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO.deptgroupcode
  is '专业组编码';
comment on column ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO.deptgroupname
  is '专业组名称';
comment on column ATS_DICT.DICT_JY_ZSYYDEPTGROUPINFO.isdefault
  is '是否默认的专业组';

prompt
prompt Creating table DICT_JY_ZSYYPROFILEINFO
prompt ======================================
prompt
create table ATS_DICT.DICT_JY_ZSYYPROFILEINFO
(
  zsyyprofileinfo_pk  NUMBER not null,
  itemid              VARCHAR2(64) not null,
  testid              VARCHAR2(128),
  testcode            VARCHAR2(128),
  testname            VARCHAR2(128),
  shortname           VARCHAR2(128),
  precautions         VARCHAR2(128),
  instructions        VARCHAR2(128),
  mustcollectposition VARCHAR2(128),
  isactive            VARCHAR2(128),
  orderindex          NUMBER
)
;
comment on table ATS_DICT.DICT_JY_ZSYYPROFILEINFO
  is '检验复合收费项目字典中ZSYYProfileInfo信息';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.zsyyprofileinfo_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.itemid
  is '主表（DICT_JY_DICTASYNCQUERYITEM）中的ItemID唯一标识字段';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.testid
  is '项目内码';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.testcode
  is '项目编码，用户使用';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.testname
  is '项目名称';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.shortname
  is '项目简称/别名';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.precautions
  is '注意事项';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.instructions
  is '用途说明';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.mustcollectposition
  is '是否必须填采集部位';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.isactive
  is '是否激活';
comment on column ATS_DICT.DICT_JY_ZSYYPROFILEINFO.orderindex
  is '排序';
create index ATS_DICT.ZSYYPROFILEINFO_ITEMID on ATS_DICT.DICT_JY_ZSYYPROFILEINFO (ITEMID);
alter table ATS_DICT.DICT_JY_ZSYYPROFILEINFO
  add constraint ZSYYPROFILEINFO_PK primary key (ZSYYPROFILEINFO_PK);

prompt
prompt Creating table DICT_JY_ZSYYSINGLETESTINFO
prompt =========================================
prompt
create table ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO
(
  zsyysingletestinfo_pk NUMBER not null,
  zsyyprofileinfo_pk    NUMBER not null,
  itemid                VARCHAR2(64) not null,
  testid                VARCHAR2(128),
  testcode              VARCHAR2(128),
  testname              VARCHAR2(512),
  shortname             VARCHAR2(512),
  precautions           VARCHAR2(512),
  instructions          VARCHAR2(512),
  mustcollectposition   VARCHAR2(128),
  isactive              VARCHAR2(12),
  orderindex            NUMBER,
  testtypecode          VARCHAR2(128),
  testtypename          VARCHAR2(218)
)
;
comment on table ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO
  is '检验复合收费项目字典中的ZSYYSingleTestInfo信息';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.zsyysingletestinfo_pk
  is '表主键';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.zsyyprofileinfo_pk
  is 'DICT_JY_ZSYYPROFILEINFO表中的主键';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.itemid
  is '主表（DICT_JY_DICTASYNCQUERYITEM）中的ItemID唯一标识字段';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.testid
  is '项目内码';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.testcode
  is '项目编码，用户使用';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.testname
  is '项目名称';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.shortname
  is '项目简称/别名';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.precautions
  is '注意事项';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.instructions
  is '用途说明';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.mustcollectposition
  is '是否必须填采集部位';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.isactive
  is '是否激活';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.orderindex
  is '排序';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.testtypecode
  is '检验分类编码';
comment on column ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO.testtypename
  is '检验分类名称(医学上的分类，如：临床生化,临床免疫,血气分析,临床微生物)';
alter table ATS_DICT.DICT_JY_ZSYYSINGLETESTINFO
  add constraint ZSYYSINGLETESTINFO_PK primary key (ZSYYSINGLETESTINFO_PK);

prompt
prompt Creating table DICT_LOINC
prompt =========================
prompt
create table ATS_DICT.DICT_LOINC
(
  code          VARCHAR2(24) not null,
  display       VARCHAR2(255),
  comments      VARCHAR2(255),
  pay_load_type VARCHAR2(255),
  custom1       VARCHAR2(255),
  custom2       VARCHAR2(255),
  custom3       VARCHAR2(255),
  custom4       VARCHAR2(255),
  custom5       VARCHAR2(255),
  isdeleted     VARCHAR2(4) default 0
)
;
alter table ATS_DICT.DICT_LOINC
  add primary key (CODE);

prompt
prompt Creating table DICT_MEASUREMENT_UNIT
prompt ====================================
prompt
create table ATS_DICT.DICT_MEASUREMENT_UNIT
(
  daimaid     VARCHAR2(10) not null,
  daimamc     VARCHAR2(100) not null,
  yingwenming VARCHAR2(100),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  shunxuhao   NUMBER(6) not null
)
;
comment on table ATS_DICT.DICT_MEASUREMENT_UNIT
  is '计量单位表';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.daimaid
  is '代码ID';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.daimamc
  is '代码名称';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.yingwenming
  is '英文名';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_MEASUREMENT_UNIT.shunxuhao
  is '顺序号';

prompt
prompt Creating table DICT_MEDICATION
prompt ==============================
prompt
create table ATS_DICT.DICT_MEDICATION
(
  medicationii              VARCHAR2(128) not null,
  medicationclasscode       VARCHAR2(20) not null,
  medicationclasscodesystem VARCHAR2(50),
  medicationclasscodename   VARCHAR2(30),
  code                      VARCHAR2(10) not null,
  name                      VARCHAR2(50) not null,
  shortname                 VARCHAR2(10),
  spellcode                 VARCHAR2(10),
  wbcode                    VARCHAR2(10),
  isdeleted                 VARCHAR2(1) not null,
  rowversion                TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICATION
  is '字典管理-通用名';
comment on column ATS_DICT.DICT_MEDICATION.medicationii
  is '通用名的唯一标识';
comment on column ATS_DICT.DICT_MEDICATION.medicationclasscode
  is '药物分类';
comment on column ATS_DICT.DICT_MEDICATION.medicationclasscodesystem
  is '药物分类编码系统';
comment on column ATS_DICT.DICT_MEDICATION.medicationclasscodename
  is '药物编码名称';
comment on column ATS_DICT.DICT_MEDICATION.code
  is '编码';
comment on column ATS_DICT.DICT_MEDICATION.name
  is '通用名名称';
comment on column ATS_DICT.DICT_MEDICATION.shortname
  is '短名称';
comment on column ATS_DICT.DICT_MEDICATION.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_MEDICATION.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_MEDICATION.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICATION.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICATION
  add constraint DICT_MEDICATION_PK primary key (MEDICATIONII);

prompt
prompt Creating table DICT_MEDICATIONCATALOG
prompt =====================================
prompt
create table ATS_DICT.DICT_MEDICATIONCATALOG
(
  medicationcatalogii       VARCHAR2(128) not null,
  name                      VARCHAR2(128) not null,
  parentcatalogii           VARCHAR2(128) default -1 not null,
  medicationclasscode       VARCHAR2(32),
  medicationclasscodesystem VARCHAR2(50),
  medicationclasscodename   VARCHAR2(30),
  rowversion                TIMESTAMP(6) not null,
  isdeleted                 VARCHAR2(1) not null
)
;
comment on table ATS_DICT.DICT_MEDICATIONCATALOG
  is '字典管理-通用名目录';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.medicationcatalogii
  is '通用名目录唯一标识';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.name
  is '名称';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.parentcatalogii
  is '父目录结果默认值为-1';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.medicationclasscode
  is '药物分类';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.medicationclasscodesystem
  is '药物分类编码系统';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.medicationclasscodename
  is '药物编码名称';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.rowversion
  is '修改时间';
comment on column ATS_DICT.DICT_MEDICATIONCATALOG.isdeleted
  is '删除标志';
alter table ATS_DICT.DICT_MEDICATIONCATALOG
  add constraint DICT_MEDICATIONCATALOG_PK primary key (MEDICATIONCATALOGII);

prompt
prompt Creating table DICT_MEDICATIONCATALOGITEM
prompt =========================================
prompt
create table ATS_DICT.DICT_MEDICATIONCATALOGITEM
(
  medicationcatalogii VARCHAR2(128) not null,
  medicationii        VARCHAR2(128) not null,
  ordinal             VARCHAR2(128) default 0 not null,
  isdeleted           VARCHAR2(1) default 0 not null,
  rowversion          TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICATIONCATALOGITEM
  is '字典管理-通用名目录与通用名的关系';
comment on column ATS_DICT.DICT_MEDICATIONCATALOGITEM.medicationcatalogii
  is '通用名目录唯一标识';
comment on column ATS_DICT.DICT_MEDICATIONCATALOGITEM.medicationii
  is '通用名';
comment on column ATS_DICT.DICT_MEDICATIONCATALOGITEM.ordinal
  is '序号';
comment on column ATS_DICT.DICT_MEDICATIONCATALOGITEM.isdeleted
  is '岱嘉新增删除字段，为处理方便';
alter table ATS_DICT.DICT_MEDICATIONCATALOGITEM
  add constraint DICT_MEDICATIONCATALOGITEM_PK primary key (MEDICATIONCATALOGII, MEDICATIONII);

prompt
prompt Creating table DICT_MEDICINE
prompt ============================
prompt
create table ATS_DICT.DICT_MEDICINE
(
  medicineii                    VARCHAR2(128) not null,
  substancecode                 VARCHAR2(30) not null,
  substancecodesystem           VARCHAR2(50) not null,
  substancecodename             VARCHAR2(30),
  brandii                       VARCHAR2(128),
  formii                        VARCHAR2(128),
  dosage                        NUMBER,
  dosageunit                    VARCHAR2(16) not null,
  strength                      VARCHAR2(50),
  note                          VARCHAR2(500),
  barcode                       VARCHAR2(20),
  specializedkindcodeid         VARCHAR2(10) not null,
  specializedkindcodesystem     VARCHAR2(50),
  specializedkindcode           VARCHAR2(30),
  antibacterialkind             VARCHAR2(10),
  antibacterialkindsystem       VARCHAR2(50),
  costlycode                    VARCHAR2(10) not null,
  costlycodesystem              VARCHAR2(50),
  costlycodename                VARCHAR2(30),
  upperlimitdose                NUMBER,
  upperlimittotaldose           NUMBER,
  defaultdose                   NUMBER not null,
  roundmodecode                 VARCHAR2(10) not null,
  roundmodecodesystem           VARCHAR2(50),
  roundmodecodename             VARCHAR2(30),
  isdispensedonfactor           VARCHAR2(1) default 0 not null,
  isdeleted                     VARCHAR2(1) default 0 not null,
  rowversion                    TIMESTAMP(6),
  defaulttemplateprescriptionii VARCHAR2(128),
  medicationii                  VARCHAR2(128),
  producerii                    VARCHAR2(128),
  code                          VARCHAR2(20),
  name                          VARCHAR2(100) not null,
  englishname                   VARCHAR2(128),
  shortname                     VARCHAR2(128),
  spellcode                     VARCHAR2(20),
  wbcode                        VARCHAR2(20),
  uomii                         VARCHAR2(128) not null,
  unitprice                     VARCHAR2(128) not null,
  internationalcode             VARCHAR2(20),
  gbcode                        VARCHAR2(20),
  feecode                       VARCHAR2(32),
  feecodename                   VARCHAR2(64),
  feecodesystem                 VARCHAR2(64),
  packunit                      VARCHAR2(16),
  packqty                       NUMBER(4),
  minunit                       VARCHAR2(16),
  usagecode                     VARCHAR2(3),
  frequencycode                 VARCHAR2(6),
  oncedose                      NUMBER(10,4),
  caution                       VARCHAR2(50),
  selfflag                      VARCHAR2(1),
  octflag                       VARCHAR2(2),
  gmpflag                       VARCHAR2(1),
  testflag                      VARCHAR2(1),
  approveinfo                   VARCHAR2(32),
  label                         VARCHAR2(50),
  ingredient                    VARCHAR2(50),
  executestandard               VARCHAR2(50),
  storecondition                VARCHAR2(50),
  briefintroduction             VARCHAR2(50),
  mark                          VARCHAR2(200),
  specialflag                   VARCHAR2(1),
  specialflag1                  VARCHAR2(1),
  flagauto                      VARCHAR2(1),
  medinsurance                  LONG,
  antibacterialkindname         VARCHAR2(30),
  classcode                     VARCHAR2(32),
  classname                     VARCHAR2(64),
  classcodesystem               VARCHAR2(64),
  cephalosporinsalgebracode     VARCHAR2(32),
  cephalosporinsalgebraname     VARCHAR2(64),
  cephalosporinsalgebrasystem   VARCHAR2(64),
  drug_quality                  VARCHAR2(128),
  lackflag                      VARCHAR2(10),
  ordername                     VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_MEDICINE
  is '字典管理-药品';
comment on column ATS_DICT.DICT_MEDICINE.medicineii
  is '药品的唯一标识';
comment on column ATS_DICT.DICT_MEDICINE.substancecode
  is '物资类型';
comment on column ATS_DICT.DICT_MEDICINE.substancecodesystem
  is '物资类型编码系统';
comment on column ATS_DICT.DICT_MEDICINE.substancecodename
  is '物资类型名称';
comment on column ATS_DICT.DICT_MEDICINE.brandii
  is '商品名';
comment on column ATS_DICT.DICT_MEDICINE.formii
  is '剂型';
comment on column ATS_DICT.DICT_MEDICINE.dosage
  is '剂量';
comment on column ATS_DICT.DICT_MEDICINE.dosageunit
  is '单位名称';
comment on column ATS_DICT.DICT_MEDICINE.strength
  is '规格';
comment on column ATS_DICT.DICT_MEDICINE.note
  is '说明';
comment on column ATS_DICT.DICT_MEDICINE.barcode
  is '条形码';
comment on column ATS_DICT.DICT_MEDICINE.specializedkindcodeid
  is '监管类型';
comment on column ATS_DICT.DICT_MEDICINE.specializedkindcodesystem
  is '监管类型编码系统(药品性质)';
comment on column ATS_DICT.DICT_MEDICINE.specializedkindcode
  is '监管类型名称';
comment on column ATS_DICT.DICT_MEDICINE.antibacterialkind
  is '药品抗菌类型';
comment on column ATS_DICT.DICT_MEDICINE.antibacterialkindsystem
  is '药品抗菌类型名称';
comment on column ATS_DICT.DICT_MEDICINE.costlycode
  is '贵重等级标识';
comment on column ATS_DICT.DICT_MEDICINE.costlycodesystem
  is '贵重等级标识编码系统';
comment on column ATS_DICT.DICT_MEDICINE.costlycodename
  is '贵重等级标识名称';
comment on column ATS_DICT.DICT_MEDICINE.upperlimitdose
  is '最高限制每次量';
comment on column ATS_DICT.DICT_MEDICINE.upperlimittotaldose
  is '最高限制总量';
comment on column ATS_DICT.DICT_MEDICINE.defaultdose
  is '缺省每次用量';
comment on column ATS_DICT.DICT_MEDICINE.roundmodecode
  is '取整方式';
comment on column ATS_DICT.DICT_MEDICINE.roundmodecodesystem
  is '取整方式编码系统';
comment on column ATS_DICT.DICT_MEDICINE.roundmodecodename
  is '取整方式名称';
comment on column ATS_DICT.DICT_MEDICINE.isdispensedonfactor
  is '是否因子发药';
comment on column ATS_DICT.DICT_MEDICINE.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINE.rowversion
  is '修改时间';
comment on column ATS_DICT.DICT_MEDICINE.defaulttemplateprescriptionii
  is '默认用药模板';
comment on column ATS_DICT.DICT_MEDICINE.medicationii
  is '通用名';
comment on column ATS_DICT.DICT_MEDICINE.producerii
  is '药品生产厂家ID';
comment on column ATS_DICT.DICT_MEDICINE.code
  is '编码';
comment on column ATS_DICT.DICT_MEDICINE.name
  is '名称';
comment on column ATS_DICT.DICT_MEDICINE.englishname
  is '英文名';
comment on column ATS_DICT.DICT_MEDICINE.shortname
  is '短名称';
comment on column ATS_DICT.DICT_MEDICINE.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_MEDICINE.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_MEDICINE.uomii
  is '计价单位';
comment on column ATS_DICT.DICT_MEDICINE.unitprice
  is '单价';
comment on column ATS_DICT.DICT_MEDICINE.internationalcode
  is '国际编码';
comment on column ATS_DICT.DICT_MEDICINE.gbcode
  is '国家编码';
comment on column ATS_DICT.DICT_MEDICINE.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_MEDICINE.feecodename
  is '最小费用名称';
comment on column ATS_DICT.DICT_MEDICINE.feecodesystem
  is '最小费用系统';
comment on column ATS_DICT.DICT_MEDICINE.packunit
  is '包装单位';
comment on column ATS_DICT.DICT_MEDICINE.packqty
  is '包装数(每包所含数量)';
comment on column ATS_DICT.DICT_MEDICINE.minunit
  is '最小单位';
comment on column ATS_DICT.DICT_MEDICINE.usagecode
  is '用法编码';
comment on column ATS_DICT.DICT_MEDICINE.frequencycode
  is '频次编码';
comment on column ATS_DICT.DICT_MEDICINE.oncedose
  is '一次用量';
comment on column ATS_DICT.DICT_MEDICINE.caution
  is '注意事项';
comment on column ATS_DICT.DICT_MEDICINE.selfflag
  is '自制标志';
comment on column ATS_DICT.DICT_MEDICINE.octflag
  is 'OCT标志';
comment on column ATS_DICT.DICT_MEDICINE.gmpflag
  is 'GMP';
comment on column ATS_DICT.DICT_MEDICINE.testflag
  is '是否需要试敏(是否皮要试)';
comment on column ATS_DICT.DICT_MEDICINE.approveinfo
  is '批文信息';
comment on column ATS_DICT.DICT_MEDICINE.label
  is '商标';
comment on column ATS_DICT.DICT_MEDICINE.ingredient
  is '有效成分';
comment on column ATS_DICT.DICT_MEDICINE.executestandard
  is '执行标准';
comment on column ATS_DICT.DICT_MEDICINE.storecondition
  is '储藏条件';
comment on column ATS_DICT.DICT_MEDICINE.briefintroduction
  is '药品简介';
comment on column ATS_DICT.DICT_MEDICINE.mark
  is '备注';
comment on column ATS_DICT.DICT_MEDICINE.specialflag
  is '省限制';
comment on column ATS_DICT.DICT_MEDICINE.specialflag1
  is '市限制';
comment on column ATS_DICT.DICT_MEDICINE.flagauto
  is '自动发药标志';
comment on column ATS_DICT.DICT_MEDICINE.medinsurance
  is '药物各类医保等级';
comment on column ATS_DICT.DICT_MEDICINE.antibacterialkindname
  is '药品抗菌类型名称';
comment on column ATS_DICT.DICT_MEDICINE.classcode
  is '系统类别';
comment on column ATS_DICT.DICT_MEDICINE.classname
  is '系统类别名称';
comment on column ATS_DICT.DICT_MEDICINE.classcodesystem
  is '系统类别编码系统';
comment on column ATS_DICT.DICT_MEDICINE.cephalosporinsalgebracode
  is '头孢代数编码';
comment on column ATS_DICT.DICT_MEDICINE.cephalosporinsalgebraname
  is '头孢代数名称';
comment on column ATS_DICT.DICT_MEDICINE.cephalosporinsalgebrasystem
  is '头孢代数编码系统';
comment on column ATS_DICT.DICT_MEDICINE.lackflag
  is '缺药标志：1表示缺药，0表示不缺药';
comment on column ATS_DICT.DICT_MEDICINE.ordername
  is '医嘱名称';
alter table ATS_DICT.DICT_MEDICINE
  add constraint MEDICINEII_PK primary key (MEDICINEII);

prompt
prompt Creating table DICT_MEDICINEALLERGEN
prompt ====================================
prompt
create table ATS_DICT.DICT_MEDICINEALLERGEN
(
  medicineallergenii VARCHAR2(128) not null,
  medicineii         VARCHAR2(128) not null,
  allergenii         VARCHAR2(128) not null,
  isdeleted          NUMBER(1) not null,
  rowversion         TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINEALLERGEN
  is '字典管理-过敏原与药品关系';
comment on column ATS_DICT.DICT_MEDICINEALLERGEN.medicineallergenii
  is '药品过敏原唯一标识';
comment on column ATS_DICT.DICT_MEDICINEALLERGEN.medicineii
  is '药品';
comment on column ATS_DICT.DICT_MEDICINEALLERGEN.allergenii
  is '过敏原';
comment on column ATS_DICT.DICT_MEDICINEALLERGEN.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINEALLERGEN.rowversion
  is '修改日期';
alter table ATS_DICT.DICT_MEDICINEALLERGEN
  add constraint DICT_MEDICINEALLERGEN_PK primary key (MEDICINEALLERGENII);

prompt
prompt Creating table DICT_MEDICINEASSORTKIND
prompt ======================================
prompt
create table ATS_DICT.DICT_MEDICINEASSORTKIND
(
  medicineassortkindii VARCHAR2(128) not null,
  medicineii           VARCHAR2(128) not null,
  assortkindcode       VARCHAR2(32) not null,
  assortkindcodesystem VARCHAR2(50),
  assortkindcodename   VARCHAR2(64),
  isdeleted            NUMBER(1) not null,
  rowversion           TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINEASSORTKIND
  is '字典管理-药品分类';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.medicineassortkindii
  is '药品分类唯一标识';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.medicineii
  is '药品';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.assortkindcode
  is '药品分类编码';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.assortkindcodesystem
  is '药品分类编码系统';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.assortkindcodename
  is '药品分类名称';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINEASSORTKIND.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINEASSORTKIND
  add constraint DICT_MEDICINEASSORTKIND_PK primary key (MEDICINEASSORTKINDII);

prompt
prompt Creating table DICT_MEDICINECATALOG
prompt ===================================
prompt
create table ATS_DICT.DICT_MEDICINECATALOG
(
  medicinecatalogii   VARCHAR2(128) not null,
  substancecode       VARCHAR2(10),
  substancecodesystem VARCHAR2(50),
  substancecodename   VARCHAR2(30),
  name                VARCHAR2(50) not null,
  spellcode           VARCHAR2(20),
  parentcatalogii     VARCHAR2(50) default -1 not null,
  isdeleted           VARCHAR2(1) not null,
  rowversion          TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINECATALOG
  is '字典管理-药品目录';
comment on column ATS_DICT.DICT_MEDICINECATALOG.medicinecatalogii
  is '药品目录唯一标识';
comment on column ATS_DICT.DICT_MEDICINECATALOG.substancecode
  is '物资类型';
comment on column ATS_DICT.DICT_MEDICINECATALOG.substancecodesystem
  is '物资类型编码系统';
comment on column ATS_DICT.DICT_MEDICINECATALOG.substancecodename
  is '物资类型名称';
comment on column ATS_DICT.DICT_MEDICINECATALOG.name
  is '名称';
comment on column ATS_DICT.DICT_MEDICINECATALOG.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_MEDICINECATALOG.parentcatalogii
  is '父目录';
comment on column ATS_DICT.DICT_MEDICINECATALOG.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINECATALOG.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINECATALOG
  add constraint MEDICINECATALOGID_PK primary key (MEDICINECATALOGII);

prompt
prompt Creating table DICT_MEDICINECATALOGITEM
prompt =======================================
prompt
create table ATS_DICT.DICT_MEDICINECATALOGITEM
(
  medicinecatalogii VARCHAR2(128),
  medicineii        VARCHAR2(128) not null,
  ordinal           NUMBER,
  isdeleted         VARCHAR2(2) not null,
  rowversion        TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINECATALOGITEM
  is '字典管理-药品目录与药品的关系';
comment on column ATS_DICT.DICT_MEDICINECATALOGITEM.medicinecatalogii
  is '药品目录';
comment on column ATS_DICT.DICT_MEDICINECATALOGITEM.medicineii
  is '药品';
comment on column ATS_DICT.DICT_MEDICINECATALOGITEM.ordinal
  is '序号';
comment on column ATS_DICT.DICT_MEDICINECATALOGITEM.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINECATALOGITEM.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINECATALOGITEM
  add constraint MEDICINECATALOGII_PK primary key (MEDICINEII);

prompt
prompt Creating table DICT_MEDICINEDISPENSINGFACTOR
prompt ============================================
prompt
create table ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR
(
  medicinedispensingfactorii VARCHAR2(128) not null,
  medicineii                 VARCHAR2(128) not null,
  substancecode              VARCHAR2(32) not null,
  substancecodesystem        VARCHAR2(64) not null,
  substancecodename          VARCHAR2(64) not null,
  quantity                   NUMBER not null,
  pharmacyii                 VARCHAR2(128),
  isdeleted                  NUMBER(1) not null,
  rowversion                 TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR
  is '字典管理-发药因子';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.medicinedispensingfactorii
  is '发药因子唯一标识';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.medicineii
  is '药品';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.substancecode
  is '物资类型';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.substancecodesystem
  is '物资类型编码系统';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.substancecodename
  is '物资类型名称';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.quantity
  is '数量';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.pharmacyii
  is '药房';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINEDISPENSINGFACTOR
  add constraint MEDICINEDISPENSINGFACTOR_PK primary key (MEDICINEDISPENSINGFACTORII);

prompt
prompt Creating table DICT_MEDICINEFORM
prompt ================================
prompt
create table ATS_DICT.DICT_MEDICINEFORM
(
  medicineformii     VARCHAR2(128) not null,
  formkindcode       VARCHAR2(10) not null,
  formkindcodesystme VARCHAR2(50),
  formkindcodename   VARCHAR2(30),
  code               VARCHAR2(10) not null,
  name               VARCHAR2(50) not null,
  spellcode          VARCHAR2(10),
  wbcode             VARCHAR2(10),
  isdeleted          NUMBER(1) not null,
  rowversion         TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINEFORM
  is '字典管理-剂型';
comment on column ATS_DICT.DICT_MEDICINEFORM.medicineformii
  is '剂型唯一标识';
comment on column ATS_DICT.DICT_MEDICINEFORM.formkindcode
  is '药品剂型分类';
comment on column ATS_DICT.DICT_MEDICINEFORM.formkindcodesystme
  is '药品剂型分类编码系统';
comment on column ATS_DICT.DICT_MEDICINEFORM.formkindcodename
  is '药品剂型分类名称';
comment on column ATS_DICT.DICT_MEDICINEFORM.code
  is '编码';
comment on column ATS_DICT.DICT_MEDICINEFORM.name
  is '名称';
comment on column ATS_DICT.DICT_MEDICINEFORM.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_MEDICINEFORM.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_MEDICINEFORM.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINEFORM.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINEFORM
  add constraint DICT_MEDICINEFORM_PK primary key (MEDICINEFORMII);

prompt
prompt Creating table DICT_MEDICINEMENSTRUUM
prompt =====================================
prompt
create table ATS_DICT.DICT_MEDICINEMENSTRUUM
(
  medicinemenstruumii VARCHAR2(128) not null,
  medicineii          VARCHAR2(128) not null,
  menstruumii         VARCHAR2(128) not null,
  dosagemultiple      NUMBER not null,
  isdeleted           NUMBER(1) not null,
  rowversion          TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINEMENSTRUUM
  is '字典管理-溶媒';
comment on column ATS_DICT.DICT_MEDICINEMENSTRUUM.medicinemenstruumii
  is '溶媒的唯一标识';
comment on column ATS_DICT.DICT_MEDICINEMENSTRUUM.medicineii
  is '药品';
comment on column ATS_DICT.DICT_MEDICINEMENSTRUUM.menstruumii
  is '溶媒药品';
comment on column ATS_DICT.DICT_MEDICINEMENSTRUUM.dosagemultiple
  is '药品溶媒的次量倍数';
comment on column ATS_DICT.DICT_MEDICINEMENSTRUUM.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINEMENSTRUUM.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINEMENSTRUUM
  add constraint DICT_MEDICINEMENSTRUUM_PK primary key (MEDICINEMENSTRUUMII);

prompt
prompt Creating table DICT_MEDICINENAME
prompt ================================
prompt
create table ATS_DICT.DICT_MEDICINENAME
(
  medicinenameii     VARCHAR2(128) not null,
  medicineii         VARCHAR2(128) not null,
  nametypecode       VARCHAR2(10) not null,
  nametypecodesystem VARCHAR2(50),
  nametypecodename   VARCHAR2(30),
  name               VARCHAR2(50) not null,
  spellcode          VARCHAR2(20),
  wbcode             VARCHAR2(20),
  validstarttime     TIMESTAMP(6),
  validendtime       TIMESTAMP(6),
  isdeleted          NUMBER(1) not null,
  rowversion         TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_MEDICINENAME
  is '字典管理-别名';
comment on column ATS_DICT.DICT_MEDICINENAME.medicinenameii
  is '药品别名的唯一标识';
comment on column ATS_DICT.DICT_MEDICINENAME.medicineii
  is '药品';
comment on column ATS_DICT.DICT_MEDICINENAME.nametypecode
  is '名称的类型编码';
comment on column ATS_DICT.DICT_MEDICINENAME.nametypecodesystem
  is '名称的类型编码系统';
comment on column ATS_DICT.DICT_MEDICINENAME.nametypecodename
  is '名称的类型名称';
comment on column ATS_DICT.DICT_MEDICINENAME.name
  is '名称';
comment on column ATS_DICT.DICT_MEDICINENAME.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_MEDICINENAME.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_MEDICINENAME.validstarttime
  is '有效开始时间';
comment on column ATS_DICT.DICT_MEDICINENAME.validendtime
  is '有效结束时间';
comment on column ATS_DICT.DICT_MEDICINENAME.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_MEDICINENAME.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_MEDICINENAME
  add constraint DICT_MEDICINENAME_PK primary key (MEDICINENAMEII);

prompt
prompt Creating table DICT_MED_MONITOR_DICT
prompt ====================================
prompt
create table ATS_DICT.DICT_MED_MONITOR_DICT
(
  monitor_label             VARCHAR2(20),
  manu_firm_name            VARCHAR2(40),
  model                     VARCHAR2(40),
  interface_type            NUMBER(1),
  interface_desc            VARCHAR2(20),
  ip_addr                   VARCHAR2(15),
  mac_addr                  VARCHAR2(12),
  last_recv_time            DATE,
  last_recv_bed_id          VARCHAR2(5),
  duplex_flag               NUMBER(5),
  autoin_flag               VARCHAR2(1),
  comm_port                 VARCHAR2(6),
  baud_rate                 NUMBER(10),
  byte_size                 NUMBER(5),
  parity                    NUMBER(5),
  stop_bits                 NUMBER(5),
  f_outx                    NUMBER(5),
  f_inx                     NUMBER(5),
  f_hardware                NUMBER(5),
  tx_queuesize              NUMBER(10),
  rx_queuesize              NUMBER(10),
  xon_lim                   NUMBER(10),
  xoff_lim                  NUMBER(10),
  xon_char                  VARCHAR2(1),
  xoff_char                 VARCHAR2(1),
  error_char                VARCHAR2(1),
  event_char                VARCHAR2(1),
  driver_prog               VARCHAR2(128),
  priority                  NUMBER(5),
  item_type                 VARCHAR2(1),
  auto_load                 NUMBER(5),
  start_date_time           DATE,
  default_recv_frequency    NUMBER(5),
  current_recv_frequency    NUMBER(5),
  current_recvtimes_uplimit NUMBER(5),
  current_recv_items        VARCHAR2(200),
  ward_code                 VARCHAR2(8),
  ward_type                 NUMBER(2),
  bed_no                    VARCHAR2(20),
  patient_id                VARCHAR2(20),
  visit_id                  NUMBER(2),
  oper_id                   NUMBER(2),
  using_indicator           NUMBER(1),
  frequency_display         NUMBER(5),
  memo                      VARCHAR2(100),
  datalog_start_time        DATE,
  pc_port                   NUMBER(5),
  datalog_status            VARCHAR2(4),
  ip_port                   NUMBER(5),
  in_port                   NUMBER(5),
  out_port                  NUMBER(5)
)
;

prompt
prompt Creating table DICT_MINIMUM_CHARGE_ITEMS
prompt ========================================
prompt
create table ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS
(
  shoufeixmid VARCHAR2(10) not null,
  shoufeixmmc VARCHAR2(100) not null,
  shoufeixmnh VARCHAR2(500),
  fuleixm     VARCHAR2(10),
  hesuanxm    VARCHAR2(10) not null,
  xiangmulx   VARCHAR2(4) not null,
  bingansfxm  VARCHAR2(10),
  kuaijikm    VARCHAR2(10),
  taocanbz    NUMBER(1) not null,
  mojibz      NUMBER(1),
  jijiadw     VARCHAR2(10) not null,
  danjia1     NUMBER(18,4) not null,
  danjia2     NUMBER(18,4),
  danjia3     NUMBER(18,4),
  danjia4     NUMBER(18,4),
  danjia5     NUMBER(18,4),
  biaozhunbm  VARCHAR2(20),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  beizhu      VARCHAR2(500),
  yuanqusy    VARCHAR2(10) not null,
  zhuyuansy   NUMBER(1) not null,
  menzhensy   NUMBER(1) not null,
  jizhensy    NUMBER(1),
  tishism     VARCHAR2(500),
  qiyongrq    DATE not null,
  tingyongrq  DATE,
  zuofeibz    NUMBER(1) not null,
  xiugairen   VARCHAR2(10) not null,
  xiugaisj    DATE not null,
  yangbenlx   VARCHAR2(10),
  caijibw     VARCHAR2(10),
  qubaogdd    VARCHAR2(50),
  qubaogsj    VARCHAR2(50),
  xingzhisx   VARCHAR2(50),
  yibaodj     VARCHAR2(4),
  zifu01      VARCHAR2(100),
  zifu02      VARCHAR2(100),
  zifu03      VARCHAR2(100),
  zifu04      VARCHAR2(100),
  zifu05      VARCHAR2(100),
  zifu06      VARCHAR2(100),
  zifu07      VARCHAR2(100),
  zifu08      VARCHAR2(100),
  zifu09      VARCHAR2(100),
  zifu10      VARCHAR2(100),
  biaozhi1    NUMBER(1),
  biaozhi2    NUMBER(1),
  shuzi1      NUMBER(18,4),
  shuzi2      NUMBER(18,4),
  shuzi3      NUMBER(18,4),
  shuzi4      NUMBER(18,4),
  shuzi5      NUMBER(18,4),
  xiangmubm   VARCHAR2(20),
  menzhenfpxm VARCHAR2(10),
  zhuyuanfpxm VARCHAR2(10),
  waibuxmid   VARCHAR2(20),
  wuzixh      VARCHAR2(10),
  binganxm    VARCHAR2(10)
)
;
comment on table ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS
  is '最小收费项目列表';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shoufeixmid
  is '收费项目ID';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shoufeixmmc
  is '收费项目名称';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shoufeixmnh
  is '收费项目内涵';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.fuleixm
  is '父类项目';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.hesuanxm
  is '核算项目';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.xiangmulx
  is '项目类型';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.bingansfxm
  is '病案收费项目';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.kuaijikm
  is '会计科目';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.taocanbz
  is '套餐标志';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.mojibz
  is '末级标志';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.jijiadw
  is '计价单位';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.danjia1
  is '单价1';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.danjia2
  is '单价2';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.danjia3
  is '单价3';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.danjia4
  is '单价4';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.danjia5
  is '单价5';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.biaozhunbm
  is '标准编码';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.beizhu
  is '备注';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.yuanqusy
  is '院区使用';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zhuyuansy
  is '住院使用';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.menzhensy
  is '门诊使用';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.jizhensy
  is '急诊使用';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.tishism
  is '提示说明';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.qiyongrq
  is '启用日期';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.tingyongrq
  is '停用日期';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.yangbenlx
  is '样本类型';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.caijibw
  is '采集部位';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.qubaogdd
  is '取报告地点';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.qubaogsj
  is '取报告时间';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.xingzhisx
  is '性质属性';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.yibaodj
  is '医保等级';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu01
  is '字符01';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu02
  is '字符02';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu03
  is '字符03';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu04
  is '字符04';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu05
  is '字符05';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu06
  is '字符06';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu07
  is '字符07';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu08
  is '字符08';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu09
  is '字符09';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zifu10
  is '字符10';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.biaozhi1
  is '标志位1';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.biaozhi2
  is '标志位2';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuzi1
  is '数字1';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuzi2
  is '数字2';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuzi3
  is '数字3';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuzi4
  is '数字4';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.shuzi5
  is '数字5';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.xiangmubm
  is '项目编码';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.menzhenfpxm
  is '门诊发票项目';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.zhuyuanfpxm
  is '住院发票项目';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.waibuxmid
  is '外部项目ID';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.wuzixh
  is '物资序号';
comment on column ATS_DICT.DICT_MINIMUM_CHARGE_ITEMS.binganxm
  is '新病案收费项目';

prompt
prompt Creating table DICT_MONITOR_BUSINESS
prompt ====================================
prompt
create table ATS_DICT.DICT_MONITOR_BUSINESS
(
  business_type VARCHAR2(2) not null,
  app_code      VARCHAR2(64) not null,
  business_code VARCHAR2(64) not null,
  relation_code VARCHAR2(256) not null
)
;
comment on table ATS_DICT.DICT_MONITOR_BUSINESS
  is '监控业务编码对应各业务系统子业务的编码';
comment on column ATS_DICT.DICT_MONITOR_BUSINESS.business_type
  is '业务类型，1-患者，2-cda，3-消息';
comment on column ATS_DICT.DICT_MONITOR_BUSINESS.app_code
  is '业务编码';
comment on column ATS_DICT.DICT_MONITOR_BUSINESS.business_code
  is '子业务编码';
comment on column ATS_DICT.DICT_MONITOR_BUSINESS.relation_code
  is '关系编码，对应各个业务系统区分子业务的字段内容，患者-系统域，cda-文书类型';
alter table ATS_DICT.DICT_MONITOR_BUSINESS
  add constraint MONITOR_BUSI_UNI unique (APP_CODE, BUSINESS_CODE, RELATION_CODE);

prompt
prompt Creating table DICT_MSG_TYPE
prompt ============================
prompt
create table ATS_DICT.DICT_MSG_TYPE
(
  pk        NUMBER not null,
  code      VARCHAR2(32) not null,
  name      VARCHAR2(32),
  comments  VARCHAR2(255),
  isdeleted VARCHAR2(10) default 0,
  custom1   VARCHAR2(255),
  custom2   VARCHAR2(255),
  custom3   VARCHAR2(255),
  custom4   VARCHAR2(255),
  custom5   VARCHAR2(255)
)
;
comment on table ATS_DICT.DICT_MSG_TYPE
  is '平台消息类型字典表';
comment on column ATS_DICT.DICT_MSG_TYPE.pk
  is '主键';
comment on column ATS_DICT.DICT_MSG_TYPE.code
  is '消息类型';
comment on column ATS_DICT.DICT_MSG_TYPE.name
  is '消息说明';
comment on column ATS_DICT.DICT_MSG_TYPE.comments
  is '描述';
comment on column ATS_DICT.DICT_MSG_TYPE.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_MSG_TYPE.custom1
  is '备用1';
comment on column ATS_DICT.DICT_MSG_TYPE.custom2
  is '备用2';
comment on column ATS_DICT.DICT_MSG_TYPE.custom3
  is '备用3';
comment on column ATS_DICT.DICT_MSG_TYPE.custom4
  is '备用4';
comment on column ATS_DICT.DICT_MSG_TYPE.custom5
  is '备用5';
alter table ATS_DICT.DICT_MSG_TYPE
  add constraint DICT_MSG_TYPE_PK primary key (PK);

prompt
prompt Creating table DICT_NATION
prompt ==========================
prompt
create table ATS_DICT.DICT_NATION
(
  shunxuhao NUMBER(6) not null,
  daimaid   VARCHAR2(10) not null,
  daimamc   VARCHAR2(100) not null,
  menzhensy NUMBER(1) not null,
  zhuyuansy NUMBER(1) not null,
  shuruma1  VARCHAR2(10),
  shuruma2  VARCHAR2(10),
  shuruma3  VARCHAR2(10),
  zuofeibz  NUMBER(1) not null,
  minzudm2  VARCHAR2(20)
)
;
comment on table ATS_DICT.DICT_NATION
  is '民族表';
comment on column ATS_DICT.DICT_NATION.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_NATION.daimaid
  is '代码ID';
comment on column ATS_DICT.DICT_NATION.daimamc
  is '代码名称';
comment on column ATS_DICT.DICT_NATION.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_NATION.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_NATION.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_NATION.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_NATION.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_NATION.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_NATION.minzudm2
  is '民族代码2';

prompt
prompt Creating table DICT_NATIONALITY
prompt ===============================
prompt
create table ATS_DICT.DICT_NATIONALITY
(
  shunxuhao NUMBER(6) not null,
  daimaid   VARCHAR2(10) not null,
  daimamc   VARCHAR2(100) not null,
  menzhensy NUMBER(1) not null,
  zhuyuansy NUMBER(1) not null,
  shuruma1  VARCHAR2(10),
  shuruma2  VARCHAR2(10),
  shuruma3  VARCHAR2(10),
  zuofeibz  NUMBER(1) not null
)
;
comment on table ATS_DICT.DICT_NATIONALITY
  is '国籍表';
comment on column ATS_DICT.DICT_NATIONALITY.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_NATIONALITY.daimaid
  is '代码ID';
comment on column ATS_DICT.DICT_NATIONALITY.daimamc
  is '代码名称';
comment on column ATS_DICT.DICT_NATIONALITY.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_NATIONALITY.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_NATIONALITY.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_NATIONALITY.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_NATIONALITY.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_NATIONALITY.zuofeibz
  is '作废标志';

prompt
prompt Creating table DICT_NONPHARDETAILS
prompt ==================================
prompt
create table ATS_DICT.DICT_NONPHARDETAILS
(
  itemcode           VARCHAR2(12) not null,
  itemname           VARCHAR2(200),
  sysclass           VARCHAR2(3),
  feecode            VARCHAR2(3) not null,
  inputcode          VARCHAR2(14),
  spellcode          VARCHAR2(20),
  wbcode             VARCHAR2(20),
  gbcode             VARCHAR2(14),
  internationalcode  VARCHAR2(20),
  unitprice          NUMBER(12,6) not null,
  unitprice1         NUMBER(12,6),
  unitprice2         NUMBER(12,6),
  unitprice3         NUMBER(12,6),
  unitprice4         NUMBER(12,6),
  emergscale         NUMBER(3,2),
  stockunit          VARCHAR2(16),
  specialflag        VARCHAR2(1),
  specialflag1       VARCHAR2(1),
  specialflag2       VARCHAR2(1),
  specialflag3       VARCHAR2(1),
  specialflag4       VARCHAR2(1),
  familyplane        VARCHAR2(1),
  specialitem        VARCHAR2(2),
  itemgrade          VARCHAR2(1),
  confirmflag        VARCHAR2(1),
  validstate         VARCHAR2(1) not null,
  specs              VARCHAR2(32),
  exedeptcode        VARCHAR2(200),
  facilityno         VARCHAR2(200),
  defaultsample      VARCHAR2(30),
  operatecode        VARCHAR2(7),
  operatekind        VARCHAR2(3),
  operatetype        VARCHAR2(3),
  collateflag        VARCHAR2(1),
  mark               VARCHAR2(2000),
  opercode           VARCHAR2(6),
  operdate           TIMESTAMP(6) not null,
  diseaseclass       VARCHAR2(100),
  specialdept        VARCHAR2(16),
  consentflag        VARCHAR2(1) not null,
  mark1              VARCHAR2(2000),
  mark2              VARCHAR2(2000),
  mark3              VARCHAR2(2000),
  mark4              VARCHAR2(50),
  needbespeak        VARCHAR2(1),
  itemarea           VARCHAR2(2000),
  itemnoarea         VARCHAR2(2000),
  medinsurance       VARCHAR2(50),
  bafylb             VARCHAR2(6),
  sysclasscodesystem VARCHAR2(64),
  feecodesystem      VARCHAR2(64),
  isdeleted          VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_NONPHARDETAILS
  is '字典管理-非药品明细(收费项目)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.itemcode
  is '非药品编码';
comment on column ATS_DICT.DICT_NONPHARDETAILS.itemname
  is '非药品名称';
comment on column ATS_DICT.DICT_NONPHARDETAILS.sysclass
  is '系统类别';
comment on column ATS_DICT.DICT_NONPHARDETAILS.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_NONPHARDETAILS.inputcode
  is '输入码';
comment on column ATS_DICT.DICT_NONPHARDETAILS.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_NONPHARDETAILS.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_NONPHARDETAILS.gbcode
  is '国家编码';
comment on column ATS_DICT.DICT_NONPHARDETAILS.internationalcode
  is '国际标准代码';
comment on column ATS_DICT.DICT_NONPHARDETAILS.unitprice
  is '三甲价';
comment on column ATS_DICT.DICT_NONPHARDETAILS.unitprice1
  is '儿童价';
comment on column ATS_DICT.DICT_NONPHARDETAILS.unitprice2
  is '特诊价';
comment on column ATS_DICT.DICT_NONPHARDETAILS.unitprice3
  is '单价2';
comment on column ATS_DICT.DICT_NONPHARDETAILS.unitprice4
  is '材料入货价';
comment on column ATS_DICT.DICT_NONPHARDETAILS.emergscale
  is '急诊加成比例';
comment on column ATS_DICT.DICT_NONPHARDETAILS.stockunit
  is '单位';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialflag
  is '省限制   0不限制 1限制';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialflag1
  is '市限制   0不限制 1限制';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialflag2
  is '自费项目   0假 1真';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialflag3
  is '特定治疗项目        0假 1真';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialflag4
  is '中山一：是否强制出单0假 1真';
comment on column ATS_DICT.DICT_NONPHARDETAILS.familyplane
  is '计划生育标记 0假 1真';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialitem
  is '此字段跟Specialflag3重复，现在没用';
comment on column ATS_DICT.DICT_NONPHARDETAILS.itemgrade
  is '甲乙类标志 1 甲 2 乙';
comment on column ATS_DICT.DICT_NONPHARDETAILS.confirmflag
  is '确认标志  0 不需要确认 1 需要确认';
comment on column ATS_DICT.DICT_NONPHARDETAILS.validstate
  is '有效性标识 0 在用 1 停用 2 废弃';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specs
  is '规格';
comment on column ATS_DICT.DICT_NONPHARDETAILS.exedeptcode
  is '执行科室';
comment on column ATS_DICT.DICT_NONPHARDETAILS.facilityno
  is '设备编号 用 | 区分';
comment on column ATS_DICT.DICT_NONPHARDETAILS.defaultsample
  is '默认检查部位或标本';
comment on column ATS_DICT.DICT_NONPHARDETAILS.operatecode
  is '手术编码(暂不启用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.operatekind
  is '手术分类(暂不启用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.operatetype
  is '手术规模(暂不启用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.collateflag
  is '是否有物资项目与之对照(1有，0没有)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.mark
  is '备注';
comment on column ATS_DICT.DICT_NONPHARDETAILS.opercode
  is '操作员';
comment on column ATS_DICT.DICT_NONPHARDETAILS.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_NONPHARDETAILS.diseaseclass
  is '疾病分类(开立检验项目时使用) (暂不启用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.specialdept
  is '专科名称(开立检验项目时使用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.consentflag
  is '是否需要打印知情同意书（0需要，1不需要）';
comment on column ATS_DICT.DICT_NONPHARDETAILS.mark1
  is '病史及检查(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.mark2
  is '检查要求(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.mark3
  is '注意事项(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.mark4
  is '检查申请单名称';
comment on column ATS_DICT.DICT_NONPHARDETAILS.needbespeak
  is '是否需要预约 1 需要 0 不需要';
comment on column ATS_DICT.DICT_NONPHARDETAILS.itemarea
  is '项目范围';
comment on column ATS_DICT.DICT_NONPHARDETAILS.itemnoarea
  is '项目例外';
comment on column ATS_DICT.DICT_NONPHARDETAILS.medinsurance
  is '医保信息';
comment on column ATS_DICT.DICT_NONPHARDETAILS.bafylb
  is '病案费用类别(暂不启用)';
comment on column ATS_DICT.DICT_NONPHARDETAILS.sysclasscodesystem
  is '系统类别编码系统2.16.840.1.113883.4.487.2.1.1.1.59';
comment on column ATS_DICT.DICT_NONPHARDETAILS.feecodesystem
  is '最小费用编码系统2.16.840.1.113883.4.487.2.1.1.1.60';
alter table ATS_DICT.DICT_NONPHARDETAILS
  add constraint DICT_NONPHARDETAILS_PK primary key (ITEMCODE);

prompt
prompt Creating table DICT_NONPHARTOCOMPLEX
prompt ====================================
prompt
create table ATS_DICT.DICT_NONPHARTOCOMPLEX
(
  packagecode VARCHAR2(12) not null,
  itemcode    VARCHAR2(12) not null,
  sortid      NUMBER,
  opercode    VARCHAR2(6) not null,
  operdate    TIMESTAMP(6) not null,
  spellcode   VARCHAR2(8),
  wbcode      VARCHAR2(8),
  inputcode   VARCHAR2(14),
  validstate  VARCHAR2(1),
  qty         NUMBER(9,4),
  isdeleted   VARCHAR2(6) default 0,
  sysclass    VARCHAR2(26),
  stockunit   VARCHAR2(26),
  itemname    VARCHAR2(36),
  packagename VARCHAR2(121)
)
;
comment on table ATS_DICT.DICT_NONPHARTOCOMPLEX
  is '字典管理-非药品明细同复合项目对照表';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.packagecode
  is '组套编码';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.itemcode
  is '非药品编码';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.sortid
  is '顺序号';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.opercode
  is '操作员';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.inputcode
  is '输入码';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.validstate
  is '有效 0 无效  1';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.qty
  is '数量';
comment on column ATS_DICT.DICT_NONPHARTOCOMPLEX.isdeleted
  is '删除标志1表示删除';
alter table ATS_DICT.DICT_NONPHARTOCOMPLEX
  add constraint DICT_NONPHARTOCOMPLEX_PK primary key (PACKAGECODE, ITEMCODE);

prompt
prompt Creating table DICT_NURSEORDER
prompt ==============================
prompt
create table ATS_DICT.DICT_NURSEORDER
(
  itemcode           VARCHAR2(12) not null,
  itemname           VARCHAR2(200),
  sysclass           VARCHAR2(3),
  feecode            VARCHAR2(3) not null,
  inputcode          VARCHAR2(14),
  spellcode          VARCHAR2(20),
  wbcode             VARCHAR2(20),
  gbcode             VARCHAR2(14),
  internationalcode  VARCHAR2(20),
  unitprice          NUMBER(12,6) not null,
  unitprice1         NUMBER(12,6),
  unitprice2         NUMBER(12,6),
  unitprice3         NUMBER(12,6),
  unitprice4         NUMBER(12,6),
  emergscale         NUMBER(3,2),
  stockunit          VARCHAR2(16),
  specialflag        VARCHAR2(1),
  specialflag1       VARCHAR2(1),
  specialflag2       VARCHAR2(1),
  specialflag3       VARCHAR2(1),
  specialflag4       VARCHAR2(1),
  familyplane        VARCHAR2(1),
  specialitem        VARCHAR2(2),
  itemgrade          VARCHAR2(1),
  confirmflag        VARCHAR2(1),
  validstate         VARCHAR2(1) not null,
  specs              VARCHAR2(32),
  exedeptcode        VARCHAR2(200),
  facilityno         VARCHAR2(200),
  defaultsample      VARCHAR2(30),
  operatecode        VARCHAR2(7),
  operatekind        VARCHAR2(3),
  operatetype        VARCHAR2(3),
  collateflag        VARCHAR2(1),
  mark               VARCHAR2(2000),
  opercode           VARCHAR2(6),
  operdate           TIMESTAMP(6) not null,
  diseaseclass       VARCHAR2(100),
  specialdept        VARCHAR2(16),
  consentflag        VARCHAR2(1) not null,
  mark1              VARCHAR2(2000),
  mark2              VARCHAR2(2000),
  mark3              VARCHAR2(2000),
  mark4              VARCHAR2(50),
  needbespeak        VARCHAR2(1),
  itemarea           VARCHAR2(2000),
  itemnoarea         VARCHAR2(2000),
  medinsurance       VARCHAR2(50),
  bafylb             VARCHAR2(6),
  sysclasscodesystem VARCHAR2(64),
  feecodesystem      VARCHAR2(64),
  isdeleted          VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_NURSEORDER
  is '字典管理-护理医嘱字典';
comment on column ATS_DICT.DICT_NURSEORDER.itemcode
  is '非药品编码';
comment on column ATS_DICT.DICT_NURSEORDER.itemname
  is '非药品名称';
comment on column ATS_DICT.DICT_NURSEORDER.sysclass
  is '系统类别';
comment on column ATS_DICT.DICT_NURSEORDER.feecode
  is '最小费用代码';
comment on column ATS_DICT.DICT_NURSEORDER.inputcode
  is '输入码';
comment on column ATS_DICT.DICT_NURSEORDER.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_NURSEORDER.wbcode
  is '五笔';
comment on column ATS_DICT.DICT_NURSEORDER.gbcode
  is '国家编码';
comment on column ATS_DICT.DICT_NURSEORDER.internationalcode
  is '国际标准代码';
comment on column ATS_DICT.DICT_NURSEORDER.unitprice
  is '三甲价';
comment on column ATS_DICT.DICT_NURSEORDER.unitprice1
  is '儿童价';
comment on column ATS_DICT.DICT_NURSEORDER.unitprice2
  is '特诊价';
comment on column ATS_DICT.DICT_NURSEORDER.unitprice3
  is '单价2';
comment on column ATS_DICT.DICT_NURSEORDER.unitprice4
  is '材料入货价';
comment on column ATS_DICT.DICT_NURSEORDER.emergscale
  is '急诊加成比例';
comment on column ATS_DICT.DICT_NURSEORDER.stockunit
  is '单位';
comment on column ATS_DICT.DICT_NURSEORDER.specialflag
  is '省限制   0不限制 1限制';
comment on column ATS_DICT.DICT_NURSEORDER.specialflag1
  is '市限制   0不限制 1限制';
comment on column ATS_DICT.DICT_NURSEORDER.specialflag2
  is '自费项目   0假 1真';
comment on column ATS_DICT.DICT_NURSEORDER.specialflag3
  is '特定治疗项目        0假 1真';
comment on column ATS_DICT.DICT_NURSEORDER.specialflag4
  is '中山一：是否强制出单0假 1真';
comment on column ATS_DICT.DICT_NURSEORDER.familyplane
  is '计划生育标记 0假 1真';
comment on column ATS_DICT.DICT_NURSEORDER.specialitem
  is '此字段跟Specialflag3重复，现在没用';
comment on column ATS_DICT.DICT_NURSEORDER.itemgrade
  is '甲乙类标志 1 甲 2 乙';
comment on column ATS_DICT.DICT_NURSEORDER.confirmflag
  is '确认标志  0 不需要确认 1 需要确认';
comment on column ATS_DICT.DICT_NURSEORDER.validstate
  is '有效性标识 0 在用 1 停用 2 废弃';
comment on column ATS_DICT.DICT_NURSEORDER.specs
  is '规格';
comment on column ATS_DICT.DICT_NURSEORDER.exedeptcode
  is '执行科室';
comment on column ATS_DICT.DICT_NURSEORDER.facilityno
  is '设备编号 用 | 区分';
comment on column ATS_DICT.DICT_NURSEORDER.defaultsample
  is '默认检查部位或标本';
comment on column ATS_DICT.DICT_NURSEORDER.operatecode
  is '手术编码(暂不启用)';
comment on column ATS_DICT.DICT_NURSEORDER.operatekind
  is '手术分类(暂不启用)';
comment on column ATS_DICT.DICT_NURSEORDER.operatetype
  is '手术规模(暂不启用)';
comment on column ATS_DICT.DICT_NURSEORDER.collateflag
  is '是否有物资项目与之对照(1有，0没有)';
comment on column ATS_DICT.DICT_NURSEORDER.mark
  is '备注';
comment on column ATS_DICT.DICT_NURSEORDER.opercode
  is '操作员';
comment on column ATS_DICT.DICT_NURSEORDER.operdate
  is '操作日期';
comment on column ATS_DICT.DICT_NURSEORDER.diseaseclass
  is '疾病分类(开立检验项目时使用) (暂不启用)';
comment on column ATS_DICT.DICT_NURSEORDER.specialdept
  is '专科名称(开立检验项目时使用)';
comment on column ATS_DICT.DICT_NURSEORDER.consentflag
  is '是否需要打印知情同意书（0需要，1不需要）';
comment on column ATS_DICT.DICT_NURSEORDER.mark1
  is '病史及检查(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_NURSEORDER.mark2
  is '检查要求(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_NURSEORDER.mark3
  is '注意事项(开立检查申请单时使用)';
comment on column ATS_DICT.DICT_NURSEORDER.mark4
  is '检查申请单名称';
comment on column ATS_DICT.DICT_NURSEORDER.needbespeak
  is '是否需要预约 1 需要 0 不需要';
comment on column ATS_DICT.DICT_NURSEORDER.itemarea
  is '项目范围';
comment on column ATS_DICT.DICT_NURSEORDER.itemnoarea
  is '项目例外';
comment on column ATS_DICT.DICT_NURSEORDER.medinsurance
  is '医保信息';
comment on column ATS_DICT.DICT_NURSEORDER.bafylb
  is '病案费用类别(暂不启用)';
comment on column ATS_DICT.DICT_NURSEORDER.sysclasscodesystem
  is '系统类别编码系统2.16.840.1.113883.4.487.2.1.1.1.59';
comment on column ATS_DICT.DICT_NURSEORDER.feecodesystem
  is '最小费用编码系统2.16.840.1.113883.4.487.2.1.1.1.60';
alter table ATS_DICT.DICT_NURSEORDER
  add constraint DICT_NURSEORDER_PK primary key (ITEMCODE);

prompt
prompt Creating table DICT_OPTICD
prompt ==========================
prompt
create table ATS_DICT.DICT_OPTICD
(
  procedureii              VARCHAR2(128) not null,
  name                     VARCHAR2(200) not null,
  icdcode                  VARCHAR2(30) not null,
  procedurelevelcode       VARCHAR2(10) not null,
  procedurelevelcodesystem VARCHAR2(50) not null,
  procedurelevelname       VARCHAR2(50) not null,
  isdeleted                VARCHAR2(1) not null,
  rowversion               TIMESTAMP(6) not null,
  issuergy                 VARCHAR2(1),
  diseasecode              VARCHAR2(64),
  diseasecodename          VARCHAR2(64),
  diseasecodecodesystem    VARCHAR2(64),
  disease30flag            VARCHAR2(1) default '0',
  infectflag               VARCHAR2(1) default '0',
  cancerflag               VARCHAR2(1) default '0'
)
;
comment on table ATS_DICT.DICT_OPTICD
  is '字典管理-处置/手术ICD9';
comment on column ATS_DICT.DICT_OPTICD.procedureii
  is '唯一标识';
comment on column ATS_DICT.DICT_OPTICD.name
  is '名称';
comment on column ATS_DICT.DICT_OPTICD.icdcode
  is 'ICD码';
comment on column ATS_DICT.DICT_OPTICD.procedurelevelcode
  is '手术级别编码';
comment on column ATS_DICT.DICT_OPTICD.procedurelevelcodesystem
  is '手术级别编码系统';
comment on column ATS_DICT.DICT_OPTICD.procedurelevelname
  is '手术级别名称';
comment on column ATS_DICT.DICT_OPTICD.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_OPTICD.rowversion
  is '修改时间';
comment on column ATS_DICT.DICT_OPTICD.issuergy
  is '是否为手术ICD9编码，0-否,1-是';
comment on column ATS_DICT.DICT_OPTICD.diseasecode
  is '疾病分类编码';
comment on column ATS_DICT.DICT_OPTICD.diseasecodename
  is '疾病分类名称';
comment on column ATS_DICT.DICT_OPTICD.diseasecodecodesystem
  is '疾病分类编码系统';
comment on column ATS_DICT.DICT_OPTICD.disease30flag
  is '30种疾病标志
0 假 1 真
';
comment on column ATS_DICT.DICT_OPTICD.infectflag
  is '传染病标志
0 假 1 真
';
comment on column ATS_DICT.DICT_OPTICD.cancerflag
  is '肿瘤标志
0 假 1 真
';
alter table ATS_DICT.DICT_OPTICD
  add constraint DICT_OPTICD_PK primary key (PROCEDUREII);

prompt
prompt Creating table DICT_PATIENTTYPE
prompt ===============================
prompt
create table ATS_DICT.DICT_PATIENTTYPE
(
  patienttypeii               VARCHAR2(128) not null,
  name                        VARCHAR2(50) not null,
  code                        VARCHAR2(10) not null,
  mnemoniccode                VARCHAR2(10),
  spellcode                   VARCHAR2(10),
  wbcode                      VARCHAR2(10),
  opproportion                NUMBER,
  ipproportion                NUMBER,
  registerfeeproportion       NUMBER,
  diagnosefeeproportion       NUMBER,
  medicareproportion          NUMBER,
  ipmedicinelimitamount       NUMBER,
  opmedicinelimitamount       NUMBER,
  examinelimitamount          NUMBER,
  curelimitamount             NUMBER,
  outdalimiteddays            NUMBER,
  outdalimitedamount          NUMBER,
  ipsalvagelimitamount        NUMBER,
  ipsickbedlimitamount        NUMBER,
  surgerylimitamount          NUMBER,
  outdalimitrecipequantity    NUMBER,
  opdiagnosefeeproportionflag NUMBER,
  ipminlimitamount            NUMBER,
  isdeleted                   VARCHAR2(1),
  isvirtual                   VARCHAR2(1),
  patientkindcode             NUMBER,
  patientkindname             VARCHAR2(16),
  patientkindcodesystem       VARCHAR2(64),
  chpaymentnocode             VARCHAR2(50),
  chpaymentno                 VARCHAR2(32),
  chpaymentnocodesystem       VARCHAR2(128),
  chpaymentno2                VARCHAR2(10),
  rowversion                  TIMESTAMP(6),
  priceform                   VARCHAR2(2),
  pubratio                    NUMBER,
  payratio                    NUMBER,
  ownratio                    NUMBER,
  ecoratio                    NUMBER,
  arrratio                    NUMBER,
  babyflag                    VARCHAR2(1),
  mcardflag                   VARCHAR2(1),
  controlflag                 VARCHAR2(1),
  flag                        VARCHAR2(1),
  daylimit                    NUMBER,
  monthlimit                  NUMBER,
  yearlimit                   NUMBER,
  oncelimit                   NUMBER,
  bedlimit                    NUMBER,
  airlimit                    NUMBER,
  employeeii                  VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_PATIENTTYPE
  is '字典管理-患者类型表';
comment on column ATS_DICT.DICT_PATIENTTYPE.patienttypeii
  is '唯一标识 （合同代码）';
comment on column ATS_DICT.DICT_PATIENTTYPE.name
  is '患者类型 （合同单位名称）';
comment on column ATS_DICT.DICT_PATIENTTYPE.code
  is '编码（合同代码）';
comment on column ATS_DICT.DICT_PATIENTTYPE.mnemoniccode
  is '助记码';
comment on column ATS_DICT.DICT_PATIENTTYPE.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_PATIENTTYPE.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_PATIENTTYPE.opproportion
  is '门诊比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.ipproportion
  is '住院比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.registerfeeproportion
  is '挂号费比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.diagnosefeeproportion
  is '诊金比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.medicareproportion
  is '广州市医保共付段自负比例（万分数）';
comment on column ATS_DICT.DICT_PATIENTTYPE.ipmedicinelimitamount
  is '住院药费限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.opmedicinelimitamount
  is '门诊药费限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.examinelimitamount
  is '检查限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.curelimitamount
  is '治疗限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.outdalimiteddays
  is '出院医嘱限制天数';
comment on column ATS_DICT.DICT_PATIENTTYPE.outdalimitedamount
  is '出院医嘱限制金额';
comment on column ATS_DICT.DICT_PATIENTTYPE.ipsalvagelimitamount
  is '住院抢救限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.ipsickbedlimitamount
  is '住院床位限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.surgerylimitamount
  is '手术限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.outdalimitrecipequantity
  is '出院医嘱限制张数';
comment on column ATS_DICT.DICT_PATIENTTYPE.opdiagnosefeeproportionflag
  is '门诊公费诊金记帐比例设置';
comment on column ATS_DICT.DICT_PATIENTTYPE.ipminlimitamount
  is '住院最小限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.isdeleted
  is '删除标志，-正常,1-已删除''';
comment on column ATS_DICT.DICT_PATIENTTYPE.isvirtual
  is '虚拟标记，-非虚拟,1-虚拟';
comment on column ATS_DICT.DICT_PATIENTTYPE.patientkindcode
  is '患者类型分类编码';
comment on column ATS_DICT.DICT_PATIENTTYPE.patientkindname
  is '患者类型分类名称';
comment on column ATS_DICT.DICT_PATIENTTYPE.patientkindcodesystem
  is '患者类型分类编码系统';
comment on column ATS_DICT.DICT_PATIENTTYPE.chpaymentnocode
  is '结算类别编码(医疗支付方式，旧版病案首页中显示用)';
comment on column ATS_DICT.DICT_PATIENTTYPE.chpaymentno
  is '结算类别名称';
comment on column ATS_DICT.DICT_PATIENTTYPE.chpaymentnocodesystem
  is '结算类别编码系统
2.16.840.1.113883.4.487.2.1.1.1.31
';
comment on column ATS_DICT.DICT_PATIENTTYPE.chpaymentno2
  is '医疗支付方式编码，新版病案首页中显示用';
comment on column ATS_DICT.DICT_PATIENTTYPE.rowversion
  is '修改时间  （操作时间）';
comment on column ATS_DICT.DICT_PATIENTTYPE.priceform
  is '价格形式，0三甲价 1特诊价 2儿童价';
comment on column ATS_DICT.DICT_PATIENTTYPE.pubratio
  is '公费比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.payratio
  is '自负比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.ownratio
  is '自费比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.ecoratio
  is '优惠比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.arrratio
  is '欠费比例';
comment on column ATS_DICT.DICT_PATIENTTYPE.babyflag
  is '婴儿标志';
comment on column ATS_DICT.DICT_PATIENTTYPE.mcardflag
  is '是否要求必须有医疗证号';
comment on column ATS_DICT.DICT_PATIENTTYPE.controlflag
  is '是否监控';
comment on column ATS_DICT.DICT_PATIENTTYPE.flag
  is '适用标识0 全部 1 药品 2 非药品';
comment on column ATS_DICT.DICT_PATIENTTYPE.daylimit
  is '日限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.monthlimit
  is '月限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.yearlimit
  is '年限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.oncelimit
  is '一次限额';
comment on column ATS_DICT.DICT_PATIENTTYPE.bedlimit
  is '床位上限';
comment on column ATS_DICT.DICT_PATIENTTYPE.airlimit
  is '空调上限';
comment on column ATS_DICT.DICT_PATIENTTYPE.employeeii
  is '操作员';
alter table ATS_DICT.DICT_PATIENTTYPE
  add constraint DICT_PATIENTTYPE_PK primary key (PATIENTTYPEII);

prompt
prompt Creating table DICT_PAYMENT_TYPE
prompt ================================
prompt
create table ATS_DICT.DICT_PAYMENT_TYPE
(
  zhifufsid VARCHAR2(10) not null,
  zhifumc   VARCHAR2(100) not null,
  shuruma1  VARCHAR2(10),
  shuruma2  VARCHAR2(10),
  shuruma3  VARCHAR2(10),
  menzhensy NUMBER(1) not null,
  zhuyuansy NUMBER(1) not null,
  shunxuhao NUMBER(6) not null,
  huilvid   VARCHAR2(20)
)
;
comment on table ATS_DICT.DICT_PAYMENT_TYPE
  is '支付类型表';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.zhifufsid
  is '支付方式ID';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.zhifumc
  is '支付名称';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_PAYMENT_TYPE.huilvid
  is '汇率ID';

prompt
prompt Creating table DICT_PAY_LOAD_TYPE
prompt =================================
prompt
create table ATS_DICT.DICT_PAY_LOAD_TYPE
(
  pk                   NUMBER,
  code                 VARCHAR2(32) not null,
  name                 VARCHAR2(128),
  comments             VARCHAR2(255),
  allow_send           VARCHAR2(10) default 1,
  allow_rec            VARCHAR2(10) default 1,
  isdeleted            VARCHAR2(10) default 0,
  custom1              VARCHAR2(255),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255),
  dict_table_name      VARCHAR2(255),
  dict_table_pk_name   VARCHAR2(255),
  dict_table_timestamp VARCHAR2(255),
  dict_delete_name     VARCHAR2(255),
  if_before_notify     VARCHAR2(10) default 0,
  if_after_notify      VARCHAR2(10) default 0,
  dict_from_domain_id  VARCHAR2(128),
  isflowused           VARCHAR2(255)
)
;
comment on table ATS_DICT.DICT_PAY_LOAD_TYPE
  is '平台字典-荷载类型字典表';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.pk
  is '主键';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.code
  is '荷载类型';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.name
  is '说明';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.comments
  is '描述';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.allow_send
  is '允许发送，0，不允许，1允许';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.allow_rec
  is '允许接收，0，不允许，1允许';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.custom1
  is '字典表中编码字段';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.custom2
  is '字典表中拼音字段';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.custom3
  is '备用3';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.custom4
  is '备用4';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.custom5
  is '备用5';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.dict_table_name
  is '对应的字典表名';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.dict_table_pk_name
  is '对应的字典表的主键名';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.dict_table_timestamp
  is '对应的字典表需要转换的timestamp段';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.dict_delete_name
  is '对应的字典表废弃字段位名称';
comment on column ATS_DICT.DICT_PAY_LOAD_TYPE.dict_from_domain_id
  is '字典来源系统域ID';
create unique index ATS_DICT.DICT_PAY_LOAD_TYPE_PK on ATS_DICT.DICT_PAY_LOAD_TYPE (CODE);

prompt
prompt Creating table DICT_PHACOMCOMPANY
prompt =================================
prompt
create table ATS_DICT.DICT_PHACOMCOMPANY
(
  facii      VARCHAR2(10) not null,
  faccode    VARCHAR2(10) not null,
  facname    VARCHAR2(200) not null,
  address    VARCHAR2(200),
  relation   VARCHAR2(100),
  isdeleted  NUMBER(1) not null,
  rowversion TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_PHACOMCOMPANY
  is '字典管理-药品生产厂家';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.facii
  is '唯一标识';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.faccode
  is '公司编码';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.facname
  is '公司名称';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.address
  is '公司地址';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.relation
  is '联系方式';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.isdeleted
  is '删除标记位，内部增加';
comment on column ATS_DICT.DICT_PHACOMCOMPANY.rowversion
  is '修改日期';
alter table ATS_DICT.DICT_PHACOMCOMPANY
  add constraint DICT_PHACOMCOMPANY_PK primary key (FACII);

prompt
prompt Creating table DICT_PHACOMPIVAOUT
prompt =================================
prompt
create table ATS_DICT.DICT_PHACOMPIVAOUT
(
  groupid       VARCHAR2(10) not null,
  seqid         VARCHAR2(10),
  itemcode      VARCHAR2(12),
  itemname      VARCHAR2(100),
  typecode      VARCHAR2(2),
  frequencycode VARCHAR2(6),
  usagecode     VARCHAR2(3),
  oncedose      NUMBER(10,4),
  doseunit      VARCHAR2(16),
  qty           NUMBER(10,4) not null,
  qtyunit       VARCHAR2(20),
  days          NUMBER(2),
  combno        VARCHAR2(14),
  maindrug      VARCHAR2(1),
  itemnote      VARCHAR2(20),
  exedeptcode   VARCHAR2(4),
  datebgn       TIMESTAMP(6),
  dateend       TIMESTAMP(6),
  remark        VARCHAR2(150),
  remarkcomb    VARCHAR2(150),
  opercode      VARCHAR2(6),
  operdate      TIMESTAMP(6),
  intervald     VARCHAR2(2),
  intervaldays  VARCHAR2(2),
  classcode     VARCHAR2(3),
  extcode       VARCHAR2(5),
  minunitflag   VARCHAR2(1),
  pivaflag      VARCHAR2(1),
  regname       VARCHAR2(100),
  specis        VARCHAR2(100),
  packunit      VARCHAR2(100),
  packnum       NUMBER(20),
  validflag     VARCHAR2(1),
  printflag     VARCHAR2(1),
  price         NUMBER(10,2),
  isdeleted     VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_PHACOMPIVAOUT
  is '字典管理-外购药物';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.groupid
  is '流水号';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.seqid
  is '组套内流水号';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.itemcode
  is '药品编号';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.itemname
  is '项目名称';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.typecode
  is '医嘱类型';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.frequencycode
  is '服药频次';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.usagecode
  is '用法编号';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.oncedose
  is '每次量';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.doseunit
  is '剂量单位';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.qty
  is '开立数量';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.qtyunit
  is '开立单位';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.days
  is '使用时间';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.combno
  is '医嘱号';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.maindrug
  is '是否主药';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.itemnote
  is '备注';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.exedeptcode
  is '执行科室编码';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.datebgn
  is '开始日期';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.dateend
  is '结束日期';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.remark
  is '医嘱备注';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.remarkcomb
  is '药物组合医嘱备注';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.opercode
  is '操作时间';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.operdate
  is '操作人';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.intervald
  is '间隔时间';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.intervaldays
  is '间隔天数';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.classcode
  is '系统类别';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.extcode
  is '扩展代码';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.minunitflag
  is '最小单位';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.pivaflag
  is '配药标准，0不配，1配';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.regname
  is '通用名';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.specis
  is '规格';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.packunit
  is '包装单位';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.packnum
  is '包装数量';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.validflag
  is '1有效';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.printflag
  is '0未打印，1已打印';
comment on column ATS_DICT.DICT_PHACOMPIVAOUT.price
  is '价格';
alter table ATS_DICT.DICT_PHACOMPIVAOUT
  add constraint DICT_PHACOMPIVAOUT_PK primary key (GROUPID);

prompt
prompt Creating table DICT_PHARMACOLOGY
prompt ================================
prompt
create table ATS_DICT.DICT_PHARMACOLOGY
(
  yaopinflid VARCHAR2(10) not null,
  shangjifl  VARCHAR2(10),
  fenleimc   VARCHAR2(100) not null,
  biaozhunbm VARCHAR2(20) not null,
  mojibz     NUMBER(1) not null,
  yaopinlx   VARCHAR2(4) not null,
  shunxuhao  NUMBER(6) not null,
  xiugairen  VARCHAR2(10),
  xiugaisj   DATE,
  fenleilb   VARCHAR2(10) not null,
  zuofeibz   NUMBER(1) not null,
  shuruma1   VARCHAR2(10),
  shuruma2   VARCHAR2(10),
  shuruma3   VARCHAR2(10)
)
;
comment on table ATS_DICT.DICT_PHARMACOLOGY
  is '药理';
comment on column ATS_DICT.DICT_PHARMACOLOGY.yaopinflid
  is '药品分类ID';
comment on column ATS_DICT.DICT_PHARMACOLOGY.shangjifl
  is '上级分类';
comment on column ATS_DICT.DICT_PHARMACOLOGY.fenleimc
  is '分类名称';
comment on column ATS_DICT.DICT_PHARMACOLOGY.biaozhunbm
  is '标准编码';
comment on column ATS_DICT.DICT_PHARMACOLOGY.mojibz
  is '末级标志';
comment on column ATS_DICT.DICT_PHARMACOLOGY.yaopinlx
  is '药品类型';
comment on column ATS_DICT.DICT_PHARMACOLOGY.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_PHARMACOLOGY.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_PHARMACOLOGY.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_PHARMACOLOGY.fenleilb
  is '分类类别';
comment on column ATS_DICT.DICT_PHARMACOLOGY.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_PHARMACOLOGY.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_PHARMACOLOGY.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_PHARMACOLOGY.shuruma3
  is '输入码3';

prompt
prompt Creating table DICT_PORT_PROGRAM
prompt ================================
prompt
create table ATS_DICT.DICT_PORT_PROGRAM
(
  pk           NUMBER not null,
  code         VARCHAR2(32) not null,
  name         VARCHAR2(128),
  program_id   VARCHAR2(128),
  program_name VARCHAR2(128),
  server_ip    VARCHAR2(255),
  isdeleted    VARCHAR2(10) default 0,
  message_type VARCHAR2(32),
  message_name VARCHAR2(50)
)
;
comment on column ATS_DICT.DICT_PORT_PROGRAM.pk
  is 'pk值';
comment on column ATS_DICT.DICT_PORT_PROGRAM.code
  is '接口编码';
comment on column ATS_DICT.DICT_PORT_PROGRAM.name
  is '接口名称';
comment on column ATS_DICT.DICT_PORT_PROGRAM.program_id
  is '接口所在程序代码';
comment on column ATS_DICT.DICT_PORT_PROGRAM.program_name
  is '接口所在程序名称';
comment on column ATS_DICT.DICT_PORT_PROGRAM.server_ip
  is '接口所在程序的服务地址';
comment on column ATS_DICT.DICT_PORT_PROGRAM.isdeleted
  is '是否启用 0启用 -1 暂废';

prompt
prompt Creating table DICT_PROVINCECITYCOUNTYTOWNADD
prompt =============================================
prompt
create table ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD
(
  code          VARCHAR2(8) not null,
  name          VARCHAR2(128) not null,
  senioraddress VARCHAR2(8),
  lever         VARCHAR2(8),
  isdeleted     NUMBER not null,
  rowversion    TIMESTAMP(6) not null,
  opercode      VARCHAR2(6) not null
)
;
comment on table ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD
  is '字典管理-省/市/县/镇地址';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.code
  is '编码';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.name
  is '名称';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.senioraddress
  is '上级地址';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.lever
  is '级别';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.isdeleted
  is '删除标志0-正常,1-已删除';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.rowversion
  is '修改时间';
comment on column ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD.opercode
  is '操作员';
alter table ATS_DICT.DICT_PROVINCECITYCOUNTYTOWNADD
  add constraint PROVINCECITYCOUNTYTOWNADD_PK primary key (CODE);

prompt
prompt Creating table DICT_PUSH_LOG
prompt ============================
prompt
create table ATS_DICT.DICT_PUSH_LOG
(
  log_pk            NUMBER not null,
  msg_id            VARCHAR2(128) not null,
  rec_sys_domain_id VARCHAR2(128) not null,
  rec_sys_name      VARCHAR2(128),
  pay_load_type     VARCHAR2(128) not null,
  push_time         TIMESTAMP(6) not null,
  update_time       TIMESTAMP(6) not null,
  repeat_times      NUMBER not null,
  push_status       VARCHAR2(1) not null,
  uuid              VARCHAR2(128) not null,
  operate_type      VARCHAR2(24) not null
)
;
comment on table ATS_DICT.DICT_PUSH_LOG
  is '字典推送到第三方日志记录表';
comment on column ATS_DICT.DICT_PUSH_LOG.log_pk
  is '表主键';
comment on column ATS_DICT.DICT_PUSH_LOG.msg_id
  is '消息ID，字段值来自网关字典DICT_REC_INFO表中的MSG_ID';
comment on column ATS_DICT.DICT_PUSH_LOG.rec_sys_domain_id
  is '字典接收系统域ID';
comment on column ATS_DICT.DICT_PUSH_LOG.rec_sys_name
  is '字典接收系统名称';
comment on column ATS_DICT.DICT_PUSH_LOG.pay_load_type
  is '字典荷载类型';
comment on column ATS_DICT.DICT_PUSH_LOG.push_time
  is '字典推送时间';
comment on column ATS_DICT.DICT_PUSH_LOG.update_time
  is '推送更新时间';
comment on column ATS_DICT.DICT_PUSH_LOG.repeat_times
  is '推送次数';
comment on column ATS_DICT.DICT_PUSH_LOG.push_status
  is '推送状态：0=表示推送成功，1=表示推送失败,2=没有匹配的路由信息';
comment on column ATS_DICT.DICT_PUSH_LOG.uuid
  is '字典在推送流程中的唯一标识';
comment on column ATS_DICT.DICT_PUSH_LOG.operate_type
  is '字典操作类型';
create index ATS_DICT.DOAMIN_ID_UUID on ATS_DICT.DICT_PUSH_LOG (REC_SYS_DOMAIN_ID, UUID);
create index ATS_DICT.PUSH_LOG_UUID on ATS_DICT.DICT_PUSH_LOG (UUID);
alter table ATS_DICT.DICT_PUSH_LOG
  add constraint PUSH_LOG_PK primary key (LOG_PK);

prompt
prompt Creating table DICT_REC_INFO
prompt ============================
prompt
create table ATS_DICT.DICT_REC_INFO
(
  dict_rec_pk         NUMBER not null,
  dict_from_domain_id VARCHAR2(4000) not null,
  msg_id              VARCHAR2(1024) not null,
  msg_format          VARCHAR2(64) not null,
  pay_load_type       VARCHAR2(1024) not null,
  msg                 CLOB not null,
  retry               NUMBER not null,
  retry_time          TIMESTAMP(6) not null,
  push_status         VARCHAR2(2) default 0 not null,
  rec_time            TIMESTAMP(6) not null,
  scatter_status      VARCHAR2(2) default 0 not null,
  uuid                VARCHAR2(1024) not null,
  sub_type            VARCHAR2(1024)
)
;
comment on table ATS_DICT.DICT_REC_INFO
  is '网关字典表通过webservice接收第三方字典';
comment on column ATS_DICT.DICT_REC_INFO.dict_rec_pk
  is '字典接收表主键';
comment on column ATS_DICT.DICT_REC_INFO.dict_from_domain_id
  is '字典来源系统域ID';
comment on column ATS_DICT.DICT_REC_INFO.msg_id
  is '消息唯一标识，对应xml里面的msgId字段';
comment on column ATS_DICT.DICT_REC_INFO.msg_format
  is '消息格式为两中，json和xml';
comment on column ATS_DICT.DICT_REC_INFO.pay_load_type
  is '字段荷载类型';
comment on column ATS_DICT.DICT_REC_INFO.msg
  is '字典内容';
comment on column ATS_DICT.DICT_REC_INFO.retry
  is '重试次数';
comment on column ATS_DICT.DICT_REC_INFO.retry_time
  is '重试时间';
comment on column ATS_DICT.DICT_REC_INFO.push_status
  is '推送到发送队列状态：0=表示初始化，1=推送成功，2=推送失败';
comment on column ATS_DICT.DICT_REC_INFO.rec_time
  is '接收时间';
comment on column ATS_DICT.DICT_REC_INFO.scatter_status
  is '推送打散队列状态：0=表示初始化，1=推送成功，2=推送失败';
comment on column ATS_DICT.DICT_REC_INFO.uuid
  is '字典在推送流程中的唯一标识';
comment on column ATS_DICT.DICT_REC_INFO.sub_type
  is '操作类型（ADD=新增，UPDATE=更新，ABANBON=作废）';
create index ATS_DICT.DICT_REC_INFO_UUID on ATS_DICT.DICT_REC_INFO (UUID);
alter table ATS_DICT.DICT_REC_INFO
  add constraint DICT_REC_PK_PRIMARY primary key (DICT_REC_PK);

prompt
prompt Creating table DICT_REC_INFO001
prompt ===============================
prompt
create table ATS_DICT.DICT_REC_INFO001
(
  dict_rec_pk         NUMBER not null,
  dict_from_domain_id VARCHAR2(128) not null,
  msg_id              VARCHAR2(128) not null,
  msg_format          VARCHAR2(10) not null,
  pay_load_type       VARCHAR2(128) not null,
  msg                 CLOB not null,
  retry               NUMBER not null,
  retry_time          TIMESTAMP(6) not null,
  push_status         VARCHAR2(1) default 0 not null,
  rec_time            TIMESTAMP(6) not null,
  scatter_status      VARCHAR2(1) default 0 not null,
  uuid                VARCHAR2(64) not null,
  sub_type            VARCHAR2(64)
)
;
comment on table ATS_DICT.DICT_REC_INFO001
  is '网关字典表通过webservice接收第三方字典';
comment on column ATS_DICT.DICT_REC_INFO001.dict_rec_pk
  is '字典接收表主键';
comment on column ATS_DICT.DICT_REC_INFO001.dict_from_domain_id
  is '字典来源系统域ID';
comment on column ATS_DICT.DICT_REC_INFO001.msg_id
  is '消息唯一标识，对应xml里面的msgId字段';
comment on column ATS_DICT.DICT_REC_INFO001.msg_format
  is '消息格式为两中，json和xml';
comment on column ATS_DICT.DICT_REC_INFO001.pay_load_type
  is '字段荷载类型';
comment on column ATS_DICT.DICT_REC_INFO001.msg
  is '字典内容';
comment on column ATS_DICT.DICT_REC_INFO001.retry
  is '重试次数';
comment on column ATS_DICT.DICT_REC_INFO001.retry_time
  is '重试时间';
comment on column ATS_DICT.DICT_REC_INFO001.push_status
  is '推送到发送队列状态：0=表示初始化，1=推送成功，2=推送失败';
comment on column ATS_DICT.DICT_REC_INFO001.rec_time
  is '接收时间';
comment on column ATS_DICT.DICT_REC_INFO001.scatter_status
  is '推送打散队列状态：0=表示初始化，1=推送成功，2=推送失败';
comment on column ATS_DICT.DICT_REC_INFO001.uuid
  is '字典在推送流程中的唯一标识';
comment on column ATS_DICT.DICT_REC_INFO001.sub_type
  is '操作类型（ADD=新增，UPDATE=更新，ABANBON=作废）';
create index ATS_DICT.DICT_FROM_DOMAIID_INDEX on ATS_DICT.DICT_REC_INFO001 (DICT_FROM_DOMAIN_ID, MSG_ID);
alter table ATS_DICT.DICT_REC_INFO001
  add constraint DICT_REC_PK primary key (DICT_REC_PK);

prompt
prompt Creating table DICT_REGISTRATION_LEVEL
prompt ======================================
prompt
create table ATS_DICT.DICT_REGISTRATION_LEVEL
(
  shunxuhao   NUMBER(6) not null,
  leibieid    VARCHAR2(10) not null,
  leibiemc    VARCHAR2(100) not null,
  yuanquid    VARCHAR2(100),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  leibiegl    VARCHAR2(20),
  guahaofxm   VARCHAR2(20),
  zhenliaofxm VARCHAR2(50),
  yingyongid  VARCHAR2(500),
  leibiejc    VARCHAR2(20),
  hebingbz    VARCHAR2(500)
)
;
comment on table ATS_DICT.DICT_REGISTRATION_LEVEL
  is '挂号级别表';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.leibieid
  is '类别ID';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.leibiemc
  is '类别名称';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.yuanquid
  is '院区ID';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.leibiegl
  is '类别管理';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.guahaofxm
  is '挂号费项目';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.zhenliaofxm
  is '诊疗费项目';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.yingyongid
  is '应用ID';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.leibiejc
  is '类别检查';
comment on column ATS_DICT.DICT_REGISTRATION_LEVEL.hebingbz
  is '合并标志';

prompt
prompt Creating table DICT_ROUTE
prompt =========================
prompt
create table ATS_DICT.DICT_ROUTE
(
  routeii                 VARCHAR2(128) not null,
  parentii                VARCHAR2(128),
  code                    VARCHAR2(10) not null,
  name                    VARCHAR2(50) not null,
  spellcode               VARCHAR2(10),
  wbcode                  VARCHAR2(10),
  unitprice               NUMBER,
  unit                    VARCHAR2(50),
  clinicitemii            VARCHAR2(128),
  executekindcode         VARCHAR2(10),
  executekindcodesystem   VARCHAR2(50),
  executekindname         VARCHAR2(30),
  chargemodecode          VARCHAR2(10),
  chargemodecodesystem    VARCHAR2(50),
  chargemodename          VARCHAR2(30),
  injectionkindcode       VARCHAR2(10),
  injectionkindcodesystem VARCHAR2(50),
  injectionkindname       VARCHAR2(30),
  isdeleted               NUMBER(1) not null,
  rowversion              TIMESTAMP(6) not null
)
;
comment on table ATS_DICT.DICT_ROUTE
  is '字典管理-用药途径';
comment on column ATS_DICT.DICT_ROUTE.routeii
  is '用药途径的唯一标识';
comment on column ATS_DICT.DICT_ROUTE.parentii
  is '父节点';
comment on column ATS_DICT.DICT_ROUTE.code
  is '编码';
comment on column ATS_DICT.DICT_ROUTE.name
  is '名称';
comment on column ATS_DICT.DICT_ROUTE.spellcode
  is '拼音码';
comment on column ATS_DICT.DICT_ROUTE.wbcode
  is '五笔码';
comment on column ATS_DICT.DICT_ROUTE.unitprice
  is '单价';
comment on column ATS_DICT.DICT_ROUTE.unit
  is '单位';
comment on column ATS_DICT.DICT_ROUTE.clinicitemii
  is '临床项目';
comment on column ATS_DICT.DICT_ROUTE.executekindcode
  is '执行法类型编码';
comment on column ATS_DICT.DICT_ROUTE.executekindcodesystem
  is '执行法类型编码系统，';
comment on column ATS_DICT.DICT_ROUTE.executekindname
  is '执行法类型名称';
comment on column ATS_DICT.DICT_ROUTE.chargemodecode
  is '计费方式编码';
comment on column ATS_DICT.DICT_ROUTE.chargemodecodesystem
  is '计费方式编码系统';
comment on column ATS_DICT.DICT_ROUTE.chargemodename
  is '计费方式名称';
comment on column ATS_DICT.DICT_ROUTE.injectionkindcode
  is '注射类型编码';
comment on column ATS_DICT.DICT_ROUTE.injectionkindcodesystem
  is '注射类型编码系统';
comment on column ATS_DICT.DICT_ROUTE.injectionkindname
  is '注射类型名称';
comment on column ATS_DICT.DICT_ROUTE.isdeleted
  is '删除标志';
comment on column ATS_DICT.DICT_ROUTE.rowversion
  is '修改时间';
alter table ATS_DICT.DICT_ROUTE
  add constraint DICT_ROUTE_PK primary key (ROUTEII);

prompt
prompt Creating table DICT_ROUTE_MAP
prompt =============================
prompt
create table ATS_DICT.DICT_ROUTE_MAP
(
  pk                       NUMBER not null,
  pay_load_type            VARCHAR2(32) not null,
  dict_name                VARCHAR2(32),
  dict_type                VARCHAR2(32),
  dict_send_domain_uid     VARCHAR2(64) not null,
  dict_send_type           VARCHAR2(64) not null,
  dict_send_name           VARCHAR2(64),
  dict_rec_domain_uid      VARCHAR2(64) not null,
  dict_rec_type            VARCHAR2(64) not null,
  dict_rec_name            VARCHAR2(64),
  isdeleted                VARCHAR2(10) default 0,
  custom1                  VARCHAR2(255),
  custom2                  VARCHAR2(255),
  custom3                  VARCHAR2(255),
  custom4                  VARCHAR2(255),
  custom5                  VARCHAR2(255),
  dict_send_db_url         VARCHAR2(128),
  dict_send_db_dirver      VARCHAR2(128),
  dict_send_db_username    VARCHAR2(128),
  dict_send_db_password    VARCHAR2(128),
  dict_send_db_dialect     VARCHAR2(128),
  dict_send_soap_url       VARCHAR2(255),
  dict_rec_db_url          VARCHAR2(128),
  dict_rec_db_dirver       VARCHAR2(128),
  dict_rec_db_username     VARCHAR2(128),
  dict_rec_db_password     VARCHAR2(128),
  dict_rec_db_dialect      VARCHAR2(128),
  dict_rec_soap_notice_url VARCHAR2(255),
  dict_rec_soap_enable_url VARCHAR2(255),
  if_constant              VARCHAR2(10) default 0 not null
)
;
comment on table ATS_DICT.DICT_ROUTE_MAP
  is '事件路由规则表';
comment on column ATS_DICT.DICT_ROUTE_MAP.pk
  is '主键 ';
comment on column ATS_DICT.DICT_ROUTE_MAP.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_name
  is '字典名称';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_type
  is '字典类型';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_domain_uid
  is '字典发送者域ID ';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_type
  is '字典发送者类型，主要分为DB、SOAP';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_name
  is '字典发送者名称';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_domain_uid
  is '字典接受者域ID';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_type
  is '字典接受者类型，主要分为DB、SOAP';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_name
  is '字典接收者名称';
comment on column ATS_DICT.DICT_ROUTE_MAP.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_ROUTE_MAP.custom1
  is '备用1';
comment on column ATS_DICT.DICT_ROUTE_MAP.custom2
  is '备用2';
comment on column ATS_DICT.DICT_ROUTE_MAP.custom3
  is '备用3';
comment on column ATS_DICT.DICT_ROUTE_MAP.custom4
  is '备用4';
comment on column ATS_DICT.DICT_ROUTE_MAP.custom5
  is '备用5';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_db_url
  is '发送端数据库地址';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_db_dirver
  is '发送端数据库驱动';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_db_username
  is '发送端数据库用户名';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_db_password
  is '发送端数据库密码';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_db_dialect
  is '发送端数据库方言';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_send_soap_url
  is '发送端启用通知服务地址';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_db_url
  is '接收端数据库地址';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_db_dirver
  is '接收端数据库驱动';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_db_username
  is '接收端数据库用户名';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_db_password
  is '接收端数据库密码';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_db_dialect
  is '接收端数据库方言';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_soap_notice_url
  is '接收端推送服务地址';
comment on column ATS_DICT.DICT_ROUTE_MAP.dict_rec_soap_enable_url
  is '接收端启用通知服务地址';
comment on column ATS_DICT.DICT_ROUTE_MAP.if_constant
  is '区分是否为常数字典，常数字典：1，非常数字典：0';
alter table ATS_DICT.DICT_ROUTE_MAP
  add constraint DICT_ROUTE_MAP_PK primary key (PK);
grant select on ATS_DICT.DICT_ROUTE_MAP to PMTEST with grant option;

prompt
prompt Creating table DICT_SCATTER_LOG
prompt ===============================
prompt
create table ATS_DICT.DICT_SCATTER_LOG
(
  scatter_log_pk NUMBER not null,
  pay_load_type  VARCHAR2(64) not null,
  scatter_time   TIMESTAMP(6) not null,
  repeat_times   NUMBER default 0 not null,
  scatter_status VARCHAR2(1) not null,
  uuid           VARCHAR2(64) not null,
  operator_type  VARCHAR2(24) not null,
  scatter_desc   VARCHAR2(256),
  msg_id         VARCHAR2(128) not null
)
;
comment on table ATS_DICT.DICT_SCATTER_LOG
  is '平台字典打散日志记录表';
comment on column ATS_DICT.DICT_SCATTER_LOG.scatter_log_pk
  is '表主键';
comment on column ATS_DICT.DICT_SCATTER_LOG.pay_load_type
  is '字典荷载类型';
comment on column ATS_DICT.DICT_SCATTER_LOG.scatter_time
  is '字典打散时间';
comment on column ATS_DICT.DICT_SCATTER_LOG.repeat_times
  is '字典打散重试次数';
comment on column ATS_DICT.DICT_SCATTER_LOG.scatter_status
  is '打散状态：0=表示打散成功，1=表示打散失败,2=没有匹配的荷载类型
';
comment on column ATS_DICT.DICT_SCATTER_LOG.uuid
  is '字典在队列中的唯一标识';
comment on column ATS_DICT.DICT_SCATTER_LOG.operator_type
  is '字典操作类型';
comment on column ATS_DICT.DICT_SCATTER_LOG.scatter_desc
  is '字典打散失败描述';
comment on column ATS_DICT.DICT_SCATTER_LOG.msg_id
  is '消息ID，字段值来自网关字典DICT_REC_INFO表中的MSG_ID';
create index ATS_DICT.PAY_LOAD_UUID_INDEX on ATS_DICT.DICT_SCATTER_LOG (PAY_LOAD_TYPE, UUID);
create index ATS_DICT.SCATTER_LOG_UUID on ATS_DICT.DICT_SCATTER_LOG (UUID);
alter table ATS_DICT.DICT_SCATTER_LOG
  add constraint SCATTER_LOG_PK primary key (SCATTER_LOG_PK);

prompt
prompt Creating table DICT_SHOW_CONFIG
prompt ===============================
prompt
create table ATS_DICT.DICT_SHOW_CONFIG
(
  user_id       NUMBER not null,
  user_name     VARCHAR2(64) not null,
  pay_load_type VARCHAR2(1024),
  show_columns  VARCHAR2(4000) not null,
  status        VARCHAR2(2) default 0 not null,
  create_time   TIMESTAMP(6),
  update_time   TIMESTAMP(6),
  table_name    VARCHAR2(64) not null,
  query_columns VARCHAR2(1000)
)
;
comment on column ATS_DICT.DICT_SHOW_CONFIG.user_id
  is 'dumpling用户id';
comment on column ATS_DICT.DICT_SHOW_CONFIG.user_name
  is 'dumpling用户名';
comment on column ATS_DICT.DICT_SHOW_CONFIG.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.DICT_SHOW_CONFIG.show_columns
  is '展示字段';
comment on column ATS_DICT.DICT_SHOW_CONFIG.status
  is '启用状态 0启用 -1停止';
comment on column ATS_DICT.DICT_SHOW_CONFIG.create_time
  is '创建时间';
comment on column ATS_DICT.DICT_SHOW_CONFIG.update_time
  is '更新时间';
comment on column ATS_DICT.DICT_SHOW_CONFIG.table_name
  is '具体字典表名称';
comment on column ATS_DICT.DICT_SHOW_CONFIG.query_columns
  is '可提供查询表的字段';

prompt
prompt Creating table DICT_SICKBED
prompt ===========================
prompt
create table ATS_DICT.DICT_SICKBED
(
  sickbedii                  VARCHAR2(128) not null,
  no                         VARCHAR2(16) not null,
  name                       VARCHAR2(16) not null,
  ordinal                    NUMBER(18) not null,
  organizationii             VARCHAR2(128) not null,
  physiciandoctorii          VARCHAR2(128),
  attendingphysiciandoctorii VARCHAR2(128),
  chiefconsultantdoctorii    VARCHAR2(128),
  departmentdirectordoctorii VARCHAR2(128),
  refresherdoctorii          VARCHAR2(128),
  graduatedinternedoctorii   VARCHAR2(128),
  internedoctorii            VARCHAR2(128),
  responsiblenurseii         VARCHAR2(128),
  qcdoctorii                 VARCHAR2(128),
  qcnurseii                  VARCHAR2(128),
  isdeleted                  VARCHAR2(1) not null,
  rowversion                 TIMESTAMP(6) not null,
  groupno                    VARCHAR2(20),
  gendercode                 VARCHAR2(10),
  gendercodesystem           VARCHAR2(64),
  gendername                 VARCHAR2(30),
  isaddedcode                VARCHAR2(10),
  isadded                    VARCHAR2(64),
  isaddedcodesystem          VARCHAR2(64),
  priceperday                NUMBER,
  feegradecode               VARCHAR2(20) not null,
  feegrade                   VARCHAR2(20),
  feegradecodesystem         VARCHAR2(64),
  bedphonecode               VARCHAR2(14),
  employeeii                 VARCHAR2(128),
  note                       VARCHAR2(200)
)
;
comment on table ATS_DICT.DICT_SICKBED
  is '字典管理-床位';
comment on column ATS_DICT.DICT_SICKBED.sickbedii
  is '唯一标识';
comment on column ATS_DICT.DICT_SICKBED.no
  is '床号';
comment on column ATS_DICT.DICT_SICKBED.name
  is '病床名称（如果无名称可用床号代替）';
comment on column ATS_DICT.DICT_SICKBED.ordinal
  is '床位序号，各科室的床位独立排序';
comment on column ATS_DICT.DICT_SICKBED.organizationii
  is '科室的唯一标识符';
comment on column ATS_DICT.DICT_SICKBED.physiciandoctorii
  is '住院医师';
comment on column ATS_DICT.DICT_SICKBED.attendingphysiciandoctorii
  is '主治医师';
comment on column ATS_DICT.DICT_SICKBED.chiefconsultantdoctorii
  is '(副)主任医师';
comment on column ATS_DICT.DICT_SICKBED.departmentdirectordoctorii
  is '科主任';
comment on column ATS_DICT.DICT_SICKBED.refresherdoctorii
  is '进修医师';
comment on column ATS_DICT.DICT_SICKBED.graduatedinternedoctorii
  is '研究生实习医师';
comment on column ATS_DICT.DICT_SICKBED.internedoctorii
  is '实习医师';
comment on column ATS_DICT.DICT_SICKBED.responsiblenurseii
  is '护士';
comment on column ATS_DICT.DICT_SICKBED.qcdoctorii
  is '质控医师';
comment on column ATS_DICT.DICT_SICKBED.qcnurseii
  is '质控护士';
comment on column ATS_DICT.DICT_SICKBED.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_SICKBED.rowversion
  is '修改时间';
comment on column ATS_DICT.DICT_SICKBED.groupno
  is '组号';
comment on column ATS_DICT.DICT_SICKBED.gendercode
  is '性别编码';
comment on column ATS_DICT.DICT_SICKBED.gendercodesystem
  is '性别编码系统
2.16.840.1.113883.4.487.2.1.1.1.9
';
comment on column ATS_DICT.DICT_SICKBED.gendername
  is '性别名称';
comment on column ATS_DICT.DICT_SICKBED.isaddedcode
  is '床位编制编码';
comment on column ATS_DICT.DICT_SICKBED.isadded
  is '床位编制名称';
comment on column ATS_DICT.DICT_SICKBED.isaddedcodesystem
  is '床位编制编码系统
2.16.840.1.113883.4.487.2.1.1.1.25
';
comment on column ATS_DICT.DICT_SICKBED.priceperday
  is '每天价格';
comment on column ATS_DICT.DICT_SICKBED.feegradecode
  is '床位等级编码 ';
comment on column ATS_DICT.DICT_SICKBED.feegrade
  is '床位等级名称';
comment on column ATS_DICT.DICT_SICKBED.feegradecodesystem
  is '床位等级编码系统
2.16.840.1.113883.4.487.2.1.1.1.26
';
comment on column ATS_DICT.DICT_SICKBED.bedphonecode
  is '病床电话';
comment on column ATS_DICT.DICT_SICKBED.employeeii
  is '操作员';
comment on column ATS_DICT.DICT_SICKBED.note
  is '备注';
alter table ATS_DICT.DICT_SICKBED
  add constraint DICT_SICKBED_PK primary key (SICKBEDII);

prompt
prompt Creating table DICT_SOURCE_MAPPING
prompt ==================================
prompt
create table ATS_DICT.DICT_SOURCE_MAPPING
(
  source_pk          NUMBER not null,
  hiup_database      VARCHAR2(128) not null,
  hiup_tabel         VARCHAR2(128) not null,
  source_database    VARCHAR2(128) not null,
  source_tabel       VARCHAR2(128) not null,
  status             VARCHAR2(2) not null,
  hiup_database_pk   NUMBER,
  source_database_pk NUMBER,
  source_name        VARCHAR2(128)
)
;
comment on table ATS_DICT.DICT_SOURCE_MAPPING
  is '平台字典表和字典源库表映射关系表';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.source_pk
  is '表主键';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.hiup_database
  is '平台数据库名称';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.hiup_tabel
  is '平台数据库表名';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.source_database
  is '字典源数据库';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.source_tabel
  is '字典源表名称';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.status
  is '状态1=表示启用，0=表示停用';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.hiup_database_pk
  is '平台字典数据库对应pk';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.source_database_pk
  is '字典源字典数据库对应pk';
comment on column ATS_DICT.DICT_SOURCE_MAPPING.source_name
  is '厂商名';
alter table ATS_DICT.DICT_SOURCE_MAPPING
  add primary key (SOURCE_PK);

prompt
prompt Creating table DICT_SPECIMENCONTAINER
prompt =====================================
prompt
create table ATS_DICT.DICT_SPECIMENCONTAINER
(
  cntnid         VARCHAR2(128) not null,
  container      VARCHAR2(128) not null,
  isactive       VARCHAR2(1) not null,
  lastupdatetime TIMESTAMP(6) not null,
  isdeleted      VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_SPECIMENCONTAINER
  is '字典管理-标本容器';
comment on column ATS_DICT.DICT_SPECIMENCONTAINER.cntnid
  is '标本容器唯一标识';
comment on column ATS_DICT.DICT_SPECIMENCONTAINER.container
  is '标本容器名称';
comment on column ATS_DICT.DICT_SPECIMENCONTAINER.isactive
  is '是否激活,1是激活，0是非激活';
comment on column ATS_DICT.DICT_SPECIMENCONTAINER.lastupdatetime
  is '修改日期';
alter table ATS_DICT.DICT_SPECIMENCONTAINER
  add constraint DICT_SPECIMENCONTAINER_PK primary key (CNTNID);

prompt
prompt Creating table DICT_SPECIMENTYPE
prompt ================================
prompt
create table ATS_DICT.DICT_SPECIMENTYPE
(
  specimentypeid   VARCHAR2(128) not null,
  specimentypename VARCHAR2(128) not null,
  isactive         NUMBER(1) not null,
  lastupdatetime   TIMESTAMP(6) not null,
  isdeleted        VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_SPECIMENTYPE
  is '字典管理-标本类型';
comment on column ATS_DICT.DICT_SPECIMENTYPE.specimentypeid
  is '标本类型唯一标识';
comment on column ATS_DICT.DICT_SPECIMENTYPE.specimentypename
  is '标本类型名称';
comment on column ATS_DICT.DICT_SPECIMENTYPE.isactive
  is '是否激活,1是激活，0是非激活';
comment on column ATS_DICT.DICT_SPECIMENTYPE.lastupdatetime
  is '修改日期';
comment on column ATS_DICT.DICT_SPECIMENTYPE.isdeleted
  is '删除标志';
alter table ATS_DICT.DICT_SPECIMENTYPE
  add constraint DICT_SPECIMEN_PK primary key (SPECIMENTYPEID);

prompt
prompt Creating table DICT_SUB_TYPE
prompt ============================
prompt
create table ATS_DICT.DICT_SUB_TYPE
(
  pk        NUMBER not null,
  code      VARCHAR2(32) not null,
  name      VARCHAR2(32),
  comments  VARCHAR2(255),
  isdeleted VARCHAR2(10) default 0,
  custom1   VARCHAR2(255),
  custom2   VARCHAR2(255),
  custom3   VARCHAR2(255),
  custom4   VARCHAR2(255),
  custom5   VARCHAR2(255)
)
;
comment on table ATS_DICT.DICT_SUB_TYPE
  is '平台荷载子类型字典表';
comment on column ATS_DICT.DICT_SUB_TYPE.pk
  is '主键';
comment on column ATS_DICT.DICT_SUB_TYPE.code
  is '荷载子类型';
comment on column ATS_DICT.DICT_SUB_TYPE.name
  is '说明';
comment on column ATS_DICT.DICT_SUB_TYPE.comments
  is '描述';
comment on column ATS_DICT.DICT_SUB_TYPE.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_SUB_TYPE.custom1
  is '备用1';
comment on column ATS_DICT.DICT_SUB_TYPE.custom2
  is '备用2';
comment on column ATS_DICT.DICT_SUB_TYPE.custom3
  is '备用3';
comment on column ATS_DICT.DICT_SUB_TYPE.custom4
  is '备用4';
comment on column ATS_DICT.DICT_SUB_TYPE.custom5
  is '备用5';
alter table ATS_DICT.DICT_SUB_TYPE
  add constraint DICT_SUB_TYPE_PK primary key (PK);

prompt
prompt Creating table DICT_SYSTEM_DOMAIN
prompt =================================
prompt
create table ATS_DICT.DICT_SYSTEM_DOMAIN
(
  pk                      NUMBER not null,
  dict_domain_id          VARCHAR2(64) not null,
  dict_domain_name        VARCHAR2(255) not null,
  dict_domain_comments    VARCHAR2(255),
  dict_domian_system_type VARCHAR2(255),
  dict_domain_system_code VARCHAR2(64) not null,
  is_seiral               VARCHAR2(10) default 0,
  isdeleted               VARCHAR2(10) default 0,
  father_pk               VARCHAR2(255) not null,
  custom1                 VARCHAR2(255),
  custom2                 VARCHAR2(255),
  custom3                 VARCHAR2(255),
  custom4                 VARCHAR2(255),
  custom5                 VARCHAR2(255)
)
;
comment on table ATS_DICT.DICT_SYSTEM_DOMAIN
  is '各系统域字典表';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.pk
  is '主键';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.dict_domain_id
  is '域ID';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.dict_domain_name
  is '域名称';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.dict_domain_comments
  is '域备注';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.dict_domian_system_type
  is '系统类型';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.dict_domain_system_code
  is '系统编码';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.is_seiral
  is '流水标志，0-非流水号,1-流水号';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.father_pk
  is '父结点';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.custom1
  is '备用1';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.custom2
  is '备用2';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.custom3
  is '备用3';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.custom4
  is '备用4';
comment on column ATS_DICT.DICT_SYSTEM_DOMAIN.custom5
  is '备用5';
alter table ATS_DICT.DICT_SYSTEM_DOMAIN
  add constraint DICT_SYSTEM_DOMAIN_PK primary key (PK);

prompt
prompt Creating table DICT_TESTINDICATORS
prompt ==================================
prompt
create table ATS_DICT.DICT_TESTINDICATORS
(
  intcode              VARCHAR2(128) not null,
  chinesename          VARCHAR2(100) not null,
  shortdesc            VARCHAR2(100),
  longdesc             VARCHAR2(100),
  testcode             VARCHAR2(20) not null,
  resultkindcode       VARCHAR2(20),
  resultkindcodesystem VARCHAR2(50),
  resultkindname       VARCHAR2(50),
  orderindex           NUMBER(18),
  decimals             NUMBER,
  precautions          VARCHAR2(500),
  instructions         VARCHAR2(500),
  isactive             NUMBER(1) not null,
  lastupdatetime       TIMESTAMP(6) not null,
  isdeleted            VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_TESTINDICATORS
  is '字典管理-检验指标';
comment on column ATS_DICT.DICT_TESTINDICATORS.intcode
  is '唯一标识符';
comment on column ATS_DICT.DICT_TESTINDICATORS.chinesename
  is '名称';
comment on column ATS_DICT.DICT_TESTINDICATORS.shortdesc
  is '中文简称';
comment on column ATS_DICT.DICT_TESTINDICATORS.longdesc
  is '英文名缩写';
comment on column ATS_DICT.DICT_TESTINDICATORS.testcode
  is '编码';
comment on column ATS_DICT.DICT_TESTINDICATORS.resultkindcode
  is '检验结果类型编码';
comment on column ATS_DICT.DICT_TESTINDICATORS.resultkindcodesystem
  is '检验结果类型编码系统';
comment on column ATS_DICT.DICT_TESTINDICATORS.resultkindname
  is '检验结果类型名称';
comment on column ATS_DICT.DICT_TESTINDICATORS.orderindex
  is '用户检验结果排序';
comment on column ATS_DICT.DICT_TESTINDICATORS.decimals
  is '结果数值精度（即小数位数）';
comment on column ATS_DICT.DICT_TESTINDICATORS.precautions
  is '注意事项';
comment on column ATS_DICT.DICT_TESTINDICATORS.instructions
  is '用途说明(EMR新增，在医生开医嘱时作为提示信息)';
comment on column ATS_DICT.DICT_TESTINDICATORS.isactive
  is '是否已删除';
comment on column ATS_DICT.DICT_TESTINDICATORS.lastupdatetime
  is '最后修改时间';
alter table ATS_DICT.DICT_TESTINDICATORS
  add constraint DICT_TESTINDICATORS_PK primary key (INTCODE);

prompt
prompt Creating table DICT_UNIFIEDPORTALNUMBER
prompt =======================================
prompt
create table ATS_DICT.DICT_UNIFIEDPORTALNUMBER
(
  accountid    VARCHAR2(128) not null,
  logonname    VARCHAR2(128) not null,
  password     VARCHAR2(128) not null,
  ukeythumb    VARCHAR2(2000),
  ukeyflag     VARCHAR2(1),
  employeeid   VARCHAR2(128),
  employeecode VARCHAR2(128),
  employeename VARCHAR2(128),
  status       VARCHAR2(1),
  validflag    VARCHAR2(1),
  isdeleted    VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.DICT_UNIFIEDPORTALNUMBER
  is '字典管理-统一门户账号';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.accountid
  is '帐户ID   系统自动产生的帐户唯一ID';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.logonname
  is '登录名   登录名称、登录帐号';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.password
  is '密码     MD5加密密文';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.ukeythumb
  is 'U盾指纹  ';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.ukeyflag
  is 'UKey标志 0- 末启用UKEY 1-启用UKEY';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.employeeid
  is '员工ID   对应的员工ID';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.employeecode
  is '员工编码 对应的员工编码';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.employeename
  is '员工姓名 对应的员工姓名';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.status
  is '帐户状态 0-停用，1-在用';
comment on column ATS_DICT.DICT_UNIFIEDPORTALNUMBER.validflag
  is '有效标志 0-无效，1-有效';
alter table ATS_DICT.DICT_UNIFIEDPORTALNUMBER
  add constraint DICT_UNIFIEDPORTALNUMBER_PK primary key (ACCOUNTID);

prompt
prompt Creating table DICT_UNIT_OF_MEDICINE
prompt ====================================
prompt
create table ATS_DICT.DICT_UNIT_OF_MEDICINE
(
  danweiid    VARCHAR2(10) not null,
  danweimc    VARCHAR2(100) not null,
  yingwenming VARCHAR2(100),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  shunxuhao   NUMBER(6) not null
)
;
comment on table ATS_DICT.DICT_UNIT_OF_MEDICINE
  is '药品最小单位表';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.danweiid
  is '单位ID';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.danweimc
  is '单位名称';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.yingwenming
  is '英文名';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_UNIT_OF_MEDICINE.shunxuhao
  is '顺序号';

prompt
prompt Creating table DICT_USAGE
prompt =========================
prompt
create table ATS_DICT.DICT_USAGE
(
  geiyaofsid  VARCHAR2(10) not null,
  geiyaofsmc  VARCHAR2(100) not null,
  yingwenming VARCHAR2(100),
  dayinmc     VARCHAR2(500),
  shuruma1    VARCHAR2(10),
  shuruma2    VARCHAR2(10),
  shuruma3    VARCHAR2(10),
  menzhensy   NUMBER(1) not null,
  zhuyuansy   NUMBER(1) not null,
  geiyaofslx  VARCHAR2(18),
  yaopinqzfs  VARCHAR2(20),
  zuofeibz    NUMBER(1) not null,
  shunxuhao   NUMBER(6) not null,
  shiyongfw   VARCHAR2(50),
  xingzhisx   VARCHAR2(20),
  dayinbz     NUMBER(1)
)
;
comment on table ATS_DICT.DICT_USAGE
  is '用法表';
comment on column ATS_DICT.DICT_USAGE.geiyaofsid
  is '给药方式ID';
comment on column ATS_DICT.DICT_USAGE.geiyaofsmc
  is '给药方式名称';
comment on column ATS_DICT.DICT_USAGE.yingwenming
  is '英文名';
comment on column ATS_DICT.DICT_USAGE.dayinmc
  is '打印名称';
comment on column ATS_DICT.DICT_USAGE.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_USAGE.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_USAGE.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_USAGE.menzhensy
  is '门诊索引';
comment on column ATS_DICT.DICT_USAGE.zhuyuansy
  is '住院索引';
comment on column ATS_DICT.DICT_USAGE.geiyaofslx
  is '给药方式类型';
comment on column ATS_DICT.DICT_USAGE.yaopinqzfs
  is '药品签字方式';
comment on column ATS_DICT.DICT_USAGE.zuofeibz
  is '作废标志';
comment on column ATS_DICT.DICT_USAGE.shunxuhao
  is '顺序号';
comment on column ATS_DICT.DICT_USAGE.shiyongfw
  is '使用范围';
comment on column ATS_DICT.DICT_USAGE.xingzhisx
  is '性质事项';
comment on column ATS_DICT.DICT_USAGE.dayinbz
  is '打印标志';

prompt
prompt Creating table DICT_USE_ANTIBIOTIC_LEVEL
prompt ========================================
prompt
create table ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL
(
  zhigongid    VARCHAR2(10) not null,
  zhigonggh    VARCHAR2(10),
  zhigongxm    VARCHAR2(20),
  xingbie      VARCHAR2(4),
  chushengrq   DATE,
  shenfenzh    VARCHAR2(20),
  jiatingdz    VARCHAR2(100),
  jiatingyb    VARCHAR2(10),
  dianziyj     VARCHAR2(20),
  dianhua      VARCHAR2(20),
  zhiwu        VARCHAR2(10),
  zhicheng     VARCHAR2(10),
  canjiagzsj   DATE,
  xiugairen    VARCHAR2(10) not null,
  xiugaisj     DATE not null,
  shuruma1     VARCHAR2(10),
  shuruma3     VARCHAR2(10),
  shuruma2     VARCHAR2(10),
  dangqianzt   VARCHAR2(4) not null,
  quanxian     VARCHAR2(20) not null,
  menzhenlb    VARCHAR2(10),
  zhigonglb    VARCHAR2(4) not null,
  mima         VARCHAR2(20),
  renshiks     VARCHAR2(10),
  guahaofxm    VARCHAR2(10),
  zhenliaofxm  VARCHAR2(10),
  keshiid      VARCHAR2(10) not null,
  yishengdj    VARCHAR2(4),
  yingwenming  VARCHAR2(50),
  qianmingbz   NUMBER,
  zhigongxjbz  VARCHAR2(20),
  ybysbm       VARCHAR2(20),
  ysybffzgzt   VARCHAR2(3),
  wsjsryzyjszw VARCHAR2(3),
  yszgzbm      VARCHAR2(50),
  byyx         VARCHAR2(50)
)
;
comment on table ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL
  is '人员开抗生素权限级别';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhigongid
  is '职工ID';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhigonggh
  is '职工工号';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhigongxm
  is '职工姓名';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.xingbie
  is '性别';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.chushengrq
  is '出生日期';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.shenfenzh
  is '身份证号';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.jiatingdz
  is '家庭地址';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.jiatingyb
  is '家庭邮编';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.dianziyj
  is '电子邮件';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.dianhua
  is '电话';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhiwu
  is '职务';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhicheng
  is '职称';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.canjiagzsj
  is '参加工作时间';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.xiugairen
  is '修改人';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.xiugaisj
  is '修改时间';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.shuruma1
  is '输入码1';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.shuruma3
  is '输入码3';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.shuruma2
  is '输入码2';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.dangqianzt
  is '当前状态';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.quanxian
  is '权限';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.menzhenlb
  is '门诊类别';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhigonglb
  is '职工类别';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.mima
  is '密码';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.renshiks
  is '人事科室';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.guahaofxm
  is '挂号费项目';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhenliaofxm
  is '诊疗费项目';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.keshiid
  is '科室ID';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.yishengdj
  is '医生等级';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.yingwenming
  is '英文名';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.qianmingbz
  is '签名标志';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.zhigongxjbz
  is '职工星级标志';
comment on column ATS_DICT.DICT_USE_ANTIBIOTIC_LEVEL.ybysbm
  is '医保医师编码';

prompt
prompt Creating table ENT_MAC_VESRSION
prompt ===============================
prompt
create table ATS_DICT.ENT_MAC_VESRSION
(
  pk          NUMBER(10) not null,
  mac_address VARCHAR2(30),
  mac_version VARCHAR2(30),
  update_time TIMESTAMP(6),
  descript    VARCHAR2(200),
  device_type VARCHAR2(1000),
  code        VARCHAR2(128)
)
;

prompt
prompt Creating table ERBH
prompt ===================
prompt
create table ATS_DICT.ERBH
(
  inpatient_no VARCHAR2(100),
  item_code    VARCHAR2(12),
  card_no      VARCHAR2(30),
  name         VARCHAR2(80),
  sex_code     VARCHAR2(1),
  birthday     DATE,
  oper_date    DATE,
  exec_dpcd    VARCHAR2(4),
  exec_dpnm    VARCHAR2(20),
  reg_date     DATE
)
;

prompt
prompt Creating table ERBHEE
prompt =====================
prompt
create table ATS_DICT.ERBHEE
(
  inpatient_no VARCHAR2(14),
  card_no      VARCHAR2(30),
  item_code    VARCHAR2(12),
  name         VARCHAR2(80),
  sex_code     VARCHAR2(1),
  birthday     DATE,
  oper_date    DATE,
  exec_dpcd    VARCHAR2(4),
  exec_dpnm    VARCHAR2(30),
  reg_date     DATE
)
;
comment on column ATS_DICT.ERBHEE.inpatient_no
  is '病人流水号';
comment on column ATS_DICT.ERBHEE.card_no
  is '病人住院OR门诊号';
comment on column ATS_DICT.ERBHEE.item_code
  is '项目CODE';
comment on column ATS_DICT.ERBHEE.name
  is '项目NAME';
comment on column ATS_DICT.ERBHEE.sex_code
  is '性别';
comment on column ATS_DICT.ERBHEE.birthday
  is '出生日期';
comment on column ATS_DICT.ERBHEE.oper_date
  is '操作时间';
comment on column ATS_DICT.ERBHEE.exec_dpcd
  is '执行科室CODE';
comment on column ATS_DICT.ERBHEE.exec_dpnm
  is '执行科室NAME';
comment on column ATS_DICT.ERBHEE.reg_date
  is '挂号时间';

prompt
prompt Creating table ERROR_DICT
prompt =========================
prompt
create table ATS_DICT.ERROR_DICT
(
  error_id          NUMBER not null,
  error_code        VARCHAR2(20),
  error_description VARCHAR2(4000),
  create_date       TIMESTAMP(6),
  creater           VARCHAR2(256),
  custom1           VARCHAR2(256),
  custom2           VARCHAR2(256),
  custom3           VARCHAR2(256)
)
;
alter table ATS_DICT.ERROR_DICT
  add constraint ERROR_ID_PK primary key (ERROR_ID);

prompt
prompt Creating table ETHNICGROUP_DICT
prompt ===============================
prompt
create table ATS_DICT.ETHNICGROUP_DICT
(
  ethnic_group_cd          NUMBER not null,
  ethnic_group_name        VARCHAR2(64) not null,
  ethnic_group_description VARCHAR2(255),
  ethnic_group_code        VARCHAR2(64) not null,
  comments                 VARCHAR2(255),
  custom1                  VARCHAR2(255),
  custom2                  VARCHAR2(255),
  custom3                  VARCHAR2(255),
  custom4                  VARCHAR2(255),
  custom5                  VARCHAR2(255)
)
;
comment on table ATS_DICT.ETHNICGROUP_DICT
  is '民族';
alter table ATS_DICT.ETHNICGROUP_DICT
  add constraint ETHNICGROUP_DICT_PK primary key (ETHNIC_GROUP_CD);

prompt
prompt Creating table EVENT_PROCESS_NOTICE_INFO
prompt ========================================
prompt
create table ATS_DICT.EVENT_PROCESS_NOTICE_INFO
(
  pk             NUMBER not null,
  dict_uuid_from VARCHAR2(255) not null,
  dict_from      VARCHAR2(64) not null,
  if_from        VARCHAR2(10) not null,
  msg_type       VARCHAR2(32) default 'xml' not null,
  pay_load_type  VARCHAR2(32),
  sub_type       VARCHAR2(32) not null,
  dict_id_from   VARCHAR2(255) not null,
  dict_id_to     VARCHAR2(255),
  dict_to        VARCHAR2(64),
  msg            CLOB,
  behavior       VARCHAR2(255) not null,
  event_status   VARCHAR2(255),
  hiup_status    VARCHAR2(255),
  start_time     TIMESTAMP(6),
  end_time       TIMESTAMP(6),
  retry          NUMBER,
  retry_time     TIMESTAMP(6),
  custom1        VARCHAR2(128),
  custom2        VARCHAR2(128),
  custom3        VARCHAR2(128),
  custom4        VARCHAR2(128),
  custom5        VARCHAR2(128)
)
;
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.pk
  is '消息Id，Sqeuence自增';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.dict_uuid_from
  is '字典提供者消息id，由字典提供者保证唯一';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.dict_from
  is '字典提供者域UID';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.if_from
  is '标识该条记录的归属者
1：标识属于字典提供者
0：标识属于字典接受者
';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.msg_type
  is '消息类型，全部为xml';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.sub_type
  is '荷载子类型，描述新增、修改、删除';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.dict_id_from
  is '字典提供者的字典项目的id';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.dict_id_to
  is '字典接收者的字典项目的id,
自动更新时必填；
人工干预更新时为空
';
comment on column ATS_DICT.EVENT_PROCESS_NOTICE_INFO.dict_to
  is '字典接收者的域UID, 自动更新时必填；
人工干预更新时为空
';
alter table ATS_DICT.EVENT_PROCESS_NOTICE_INFO
  add constraint PROCESS_NOTICE_PK primary key (PK);

prompt
prompt Creating table EVENT_PUSH_ROUTE_INFO
prompt ====================================
prompt
create table ATS_DICT.EVENT_PUSH_ROUTE_INFO
(
  pk                   NUMBER not null,
  dict_uuid_from       VARCHAR2(255) not null,
  dict_from            VARCHAR2(64) not null,
  if_from              VARCHAR2(10) not null,
  msg_type             VARCHAR2(32) default 'XML' not null,
  pay_load_type        VARCHAR2(32) not null,
  sub_type             VARCHAR2(32) not null,
  dict_id_from         VARCHAR2(255) not null,
  dict_id_to           VARCHAR2(255),
  dict_to              VARCHAR2(64),
  patient_id           VARCHAR2(64),
  patient_domain_id    VARCHAR2(64),
  visit_flow_id        VARCHAR2(64),
  visit_flow_domain_id VARCHAR2(64),
  organization_id      VARCHAR2(64),
  hospital_domain_id   VARCHAR2(64),
  msg                  CLOB not null,
  behavior             VARCHAR2(255) not null,
  event_status         VARCHAR2(255),
  hiup_status          VARCHAR2(255),
  start_time           TIMESTAMP(6),
  end_time             TIMESTAMP(6),
  retry                NUMBER,
  retry_time           TIMESTAMP(6),
  custom1              VARCHAR2(128),
  custom2              VARCHAR2(128),
  custom3              VARCHAR2(128),
  custom4              VARCHAR2(128),
  custom5              VARCHAR2(128)
)
;
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.pk
  is '消息Id，Sqeuence自增';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.dict_uuid_from
  is '字典提供者消息id，由字典提供者保证唯一';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.dict_from
  is '字典提供者域UID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.if_from
  is '标识该条记录的归属者
1：标识属于字典提供者
0：标识属于字典接受者
';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.msg_type
  is '消息类型，全部为xml';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.sub_type
  is '荷载子类型，描述新增、修改、删除';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.dict_id_from
  is '字典提供者的字典项目的id';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.dict_id_to
  is '字典接收者的字典项目的id,
自动更新时必填；
人工干预更新时为空
';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.dict_to
  is '字典接收者的域UID, 自动更新时必填；
人工干预更新时为空；
';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.patient_id
  is '病人ID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.patient_domain_id
  is '病人域ID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.visit_flow_id
  is '病人流水ID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.visit_flow_domain_id
  is '病人流水域ID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.organization_id
  is '科室字典ID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.hospital_domain_id
  is '医院域ID';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.msg
  is '字典内容';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.behavior
  is 'Auto自动更新
Manual人工干预更新
';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.event_status
  is '处理状态，由第三方接口填写';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.hiup_status
  is '平台状态，由平台填写，包括通信状态、完毕通知状态、启用通知状态';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.start_time
  is '开始时间';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.end_time
  is '结束时间';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.retry
  is '重试次数';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.retry_time
  is '上次重试时间';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.custom1
  is '备用1';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.custom2
  is '备用2';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.custom3
  is '备用3';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.custom4
  is '备用4';
comment on column ATS_DICT.EVENT_PUSH_ROUTE_INFO.custom5
  is '备用5';
alter table ATS_DICT.EVENT_PUSH_ROUTE_INFO
  add constraint EVENT_REC_INFO_PK primary key (PK);

prompt
prompt Creating table EVENT_REC_INFO
prompt =============================
prompt
create table ATS_DICT.EVENT_REC_INFO
(
  pk                   NUMBER not null,
  dict_uuid_from       VARCHAR2(255) not null,
  dict_from            VARCHAR2(64) not null,
  msg_type             VARCHAR2(32) default 'XML' not null,
  pay_load_type        VARCHAR2(32),
  sub_type             VARCHAR2(32) not null,
  dict_id_from         VARCHAR2(255) not null,
  patient_id           VARCHAR2(64),
  patient_domain_id    VARCHAR2(64),
  visit_flow_id        VARCHAR2(64),
  visit_flow_domain_id VARCHAR2(64),
  organization_id      VARCHAR2(64),
  hospital_domain_id   VARCHAR2(64),
  msg                  CLOB not null,
  behavior             VARCHAR2(255),
  hiup_status          VARCHAR2(255) default 0,
  start_time           TIMESTAMP(6),
  end_time             TIMESTAMP(6),
  retry                NUMBER,
  retry_time           TIMESTAMP(6),
  custom1              VARCHAR2(500),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255),
  hiup_info            VARCHAR2(4000)
)
;
comment on column ATS_DICT.EVENT_REC_INFO.pk
  is '消息Id，Sqeuence自增';
comment on column ATS_DICT.EVENT_REC_INFO.dict_uuid_from
  is '字典提供者消息id，由字典提供者保证唯一';
comment on column ATS_DICT.EVENT_REC_INFO.dict_from
  is '字典提供者域UID';
comment on column ATS_DICT.EVENT_REC_INFO.msg_type
  is '消息类型，全部为xml';
comment on column ATS_DICT.EVENT_REC_INFO.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.EVENT_REC_INFO.sub_type
  is '荷载子类型，描述新增、修改、删除';
comment on column ATS_DICT.EVENT_REC_INFO.dict_id_from
  is '字典提供者的字典项目的id';
comment on column ATS_DICT.EVENT_REC_INFO.patient_id
  is '病人ID';
comment on column ATS_DICT.EVENT_REC_INFO.patient_domain_id
  is '病人域ID';
comment on column ATS_DICT.EVENT_REC_INFO.visit_flow_id
  is '病人流水ID';
comment on column ATS_DICT.EVENT_REC_INFO.visit_flow_domain_id
  is '病人流水域ID';
comment on column ATS_DICT.EVENT_REC_INFO.organization_id
  is '科室字典ID';
comment on column ATS_DICT.EVENT_REC_INFO.hospital_domain_id
  is '医院域ID';
comment on column ATS_DICT.EVENT_REC_INFO.msg
  is '字典内容';
comment on column ATS_DICT.EVENT_REC_INFO.behavior
  is 'Auto自动更新
Manual人工干预更新
';
comment on column ATS_DICT.EVENT_REC_INFO.hiup_status
  is '状态';
comment on column ATS_DICT.EVENT_REC_INFO.start_time
  is '开始时间';
comment on column ATS_DICT.EVENT_REC_INFO.end_time
  is '结束时间';
comment on column ATS_DICT.EVENT_REC_INFO.retry
  is '重试次数';
comment on column ATS_DICT.EVENT_REC_INFO.retry_time
  is '上次重试时间';
comment on column ATS_DICT.EVENT_REC_INFO.custom1
  is '备用1';
comment on column ATS_DICT.EVENT_REC_INFO.custom2
  is '备用2';
comment on column ATS_DICT.EVENT_REC_INFO.custom3
  is '备用3';
comment on column ATS_DICT.EVENT_REC_INFO.custom4
  is '备用4';
comment on column ATS_DICT.EVENT_REC_INFO.custom5
  is '备用5';
comment on column ATS_DICT.EVENT_REC_INFO.hiup_info
  is '出错详细信息';
alter table ATS_DICT.EVENT_REC_INFO
  add constraint REC_INFO_PK primary key (PK);

prompt
prompt Creating table EVENT_USE_ROUTE_INFO
prompt ===================================
prompt
create table ATS_DICT.EVENT_USE_ROUTE_INFO
(
  pk             NUMBER not null,
  dict_uuid_from VARCHAR2(255) not null,
  dict_from      VARCHAR2(64) not null,
  if_from        VARCHAR2(10) not null,
  dict_id_from   VARCHAR2(255) not null,
  dict_id_to     VARCHAR2(255),
  dict_to        VARCHAR2(64),
  behavior       VARCHAR2(255),
  event_status   VARCHAR2(255),
  hiup_status    VARCHAR2(255),
  start_time     TIMESTAMP(6),
  end_time       TIMESTAMP(6),
  retry          NUMBER,
  retry_time     TIMESTAMP(6),
  custom1        VARCHAR2(128),
  custom2        VARCHAR2(128),
  custom3        VARCHAR2(128),
  custom4        VARCHAR2(128),
  custom5        VARCHAR2(128),
  msg_type       VARCHAR2(32),
  pay_load_type  VARCHAR2(32),
  sub_type       VARCHAR2(32)
)
;
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.pk
  is '消息Id，Sqeuence自增';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.dict_uuid_from
  is '字典提供者消息id，由字典提供者保证唯一';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.dict_from
  is '字典提供者域UID';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.if_from
  is '标识该条记录的归属者
1：标识属于字典提供者
0：标识属于字典接受者
';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.dict_id_from
  is '字典提供者的字典项目的id';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.dict_id_to
  is '字典接收者的字典项目的id,
自动更新时必填；
人工干预更新时为空；
';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.dict_to
  is '字典接收者的域UID, 自动更新时必填；
人工干预更新时为空；
';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.behavior
  is 'Auto自动更新
Manual人工干预更新
';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.event_status
  is '处理状态，由第三方接口填写，';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.hiup_status
  is '平台状态，由平台填写，包括通信状态、完毕通知状态、启用通知状态';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.start_time
  is '开始时间';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.end_time
  is '结束时间';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.retry
  is '重试次数';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.retry_time
  is '上次重试时间';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.custom1
  is '备用1';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.custom2
  is '备用2';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.custom3
  is '备用3';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.custom4
  is '备用4';
comment on column ATS_DICT.EVENT_USE_ROUTE_INFO.custom5
  is '备用5';
alter table ATS_DICT.EVENT_USE_ROUTE_INFO
  add constraint EVENT_USE_ROUTE_INFO_PK primary key (PK);

prompt
prompt Creating table GENDER_DICT
prompt ==========================
prompt
create table ATS_DICT.GENDER_DICT
(
  gender_cd          NUMBER not null,
  gender_name        VARCHAR2(64) not null,
  gender_description VARCHAR2(255),
  gender_code        VARCHAR2(64) not null,
  create_name        VARCHAR2(64),
  create_date        TIMESTAMP(6),
  comments           VARCHAR2(255),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255),
  custom4            VARCHAR2(255),
  custom5            VARCHAR2(255)
)
;
comment on table ATS_DICT.GENDER_DICT
  is '性别';
alter table ATS_DICT.GENDER_DICT
  add constraint GENDER_DICT_PK primary key (GENDER_CD);

prompt
prompt Creating table HIUP_PRODUCT_DICT
prompt ================================
prompt
create table ATS_DICT.HIUP_PRODUCT_DICT
(
  product_id          NUMBER not null,
  product_code        VARCHAR2(20),
  product_description VARCHAR2(4000),
  create_date         TIMESTAMP(6),
  creater             VARCHAR2(256),
  custom1             VARCHAR2(256),
  custom2             VARCHAR2(256),
  custom3             VARCHAR2(256)
)
;
alter table ATS_DICT.HIUP_PRODUCT_DICT
  add constraint PRODUCT_ID_PK primary key (PRODUCT_ID);

prompt
prompt Creating table HL7_ORDER_CONTROL_DICT
prompt =====================================
prompt
create table ATS_DICT.HL7_ORDER_CONTROL_DICT
(
  control_id          NUMBER not null,
  control_code        VARCHAR2(64) not null,
  event_message_type  VARCHAR2(64) not null,
  control_description VARCHAR2(512),
  originator          VARCHAR2(16),
  create_name         VARCHAR2(64),
  create_date         TIMESTAMP(6),
  custom1             VARCHAR2(255),
  custom2             VARCHAR2(255),
  custom3             VARCHAR2(255)
)
;
comment on table ATS_DICT.HL7_ORDER_CONTROL_DICT
  is 'ORC控制指令字典';
alter table ATS_DICT.HL7_ORDER_CONTROL_DICT
  add constraint HL7_ORDER_CONTROL_DICT_PK primary key (CONTROL_ID);

prompt
prompt Creating table HOSPITAL_DANGER_DICT
prompt ===================================
prompt
create table ATS_DICT.HOSPITAL_DANGER_DICT
(
  danger_id          NUMBER not null,
  danger_code        VARCHAR2(64) not null,
  danger_name        VARCHAR2(64) not null,
  danger_domain_name VARCHAR2(64),
  danger_domain_id   VARCHAR2(64),
  danger_description VARCHAR2(512),
  create_date        TIMESTAMP(6),
  creater            VARCHAR2(128),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_DANGER_DICT
  is '样本行为词典';
alter table ATS_DICT.HOSPITAL_DANGER_DICT
  add constraint HOSPITAL_DANGER_DICT_PK primary key (DANGER_ID);

prompt
prompt Creating table HOSPITAL_DICT
prompt ============================
prompt
create table ATS_DICT.HOSPITAL_DICT
(
  hospital_cd   NUMBER not null,
  hospital_code VARCHAR2(255),
  hospital_name VARCHAR2(64) not null,
  input_code    VARCHAR2(64) not null,
  create_name   VARCHAR2(64),
  create_date   TIMESTAMP(6),
  comments      VARCHAR2(255),
  custom1       VARCHAR2(255),
  custom2       VARCHAR2(255),
  custom3       VARCHAR2(255),
  custom4       VARCHAR2(255),
  custom5       VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_DICT
  is '医院';
alter table ATS_DICT.HOSPITAL_DICT
  add constraint HOSPITAL_DICT_PK primary key (HOSPITAL_CD);

prompt
prompt Creating table HOSPITAL_DOMAIN_DICT
prompt ===================================
prompt
create table ATS_DICT.HOSPITAL_DOMAIN_DICT
(
  id              NUMBER not null,
  id_code         VARCHAR2(64),
  id_name         VARCHAR2(64),
  id_type         VARCHAR2(64),
  id_seiral       VARCHAR2(10),
  comments        VARCHAR2(255),
  custom1         VARCHAR2(255),
  custom2         VARCHAR2(255),
  custom3         VARCHAR2(255),
  custom4         VARCHAR2(255),
  custom5         VARCHAR2(255),
  hospital_name   VARCHAR2(64),
  hospital_domain VARCHAR2(64),
  isdeleted       VARCHAR2(4) default 0
)
;
comment on table ATS_DICT.HOSPITAL_DOMAIN_DICT
  is '卡号类别字典表';
create index ATS_DICT.HOSPITAL_DOMAIN_INDEX on ATS_DICT.HOSPITAL_DOMAIN_DICT (HOSPITAL_DOMAIN);
alter table ATS_DICT.HOSPITAL_DOMAIN_DICT
  add constraint HOSPITAL_DOMAIN_DICT_ID primary key (ID);

prompt
prompt Creating table HOSPITAL_DOSATE_FORM_DICT
prompt ========================================
prompt
create table ATS_DICT.HOSPITAL_DOSATE_FORM_DICT
(
  dosate_form_id          NUMBER not null,
  dosate_form_code        VARCHAR2(64) not null,
  dosate_form_name        VARCHAR2(64) not null,
  dosate_form_domain_name VARCHAR2(64),
  dosate_form_domain_id   VARCHAR2(64),
  dosate_form_description VARCHAR2(512),
  create_date             TIMESTAMP(6),
  creater                 VARCHAR2(128),
  custom1                 VARCHAR2(255),
  custom2                 VARCHAR2(255),
  custom3                 VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_DOSATE_FORM_DICT
  is '医院给予类型词典';
alter table ATS_DICT.HOSPITAL_DOSATE_FORM_DICT
  add constraint HOSPITAL_DOSATE_FORM_DICT_PK primary key (DOSATE_FORM_ID);

prompt
prompt Creating table HOSPITAL_EQUIPMENT_DICT
prompt ======================================
prompt
create table ATS_DICT.HOSPITAL_EQUIPMENT_DICT
(
  equipment_id          NUMBER not null,
  equipment_code        VARCHAR2(64) not null,
  equipment_name        VARCHAR2(64) not null,
  equipment_domain_name VARCHAR2(64),
  equipment_domain_id   VARCHAR2(64),
  equipment_description VARCHAR2(512),
  create_date           TIMESTAMP(6),
  creater               VARCHAR2(128),
  custom1               VARCHAR2(255),
  custom2               VARCHAR2(255),
  custom3               VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_EQUIPMENT_DICT
  is '设备词典';
alter table ATS_DICT.HOSPITAL_EQUIPMENT_DICT
  add constraint EQUIPMENT_DICT_PK primary key (EQUIPMENT_ID);

prompt
prompt Creating table HOSPITAL_GIVE_TYPE_DICT
prompt ======================================
prompt
create table ATS_DICT.HOSPITAL_GIVE_TYPE_DICT
(
  give_type_id          NUMBER not null,
  give_type_code        VARCHAR2(64) not null,
  give_type_name        VARCHAR2(64) not null,
  give_type_domain_name VARCHAR2(64),
  give_type_domain_id   VARCHAR2(64),
  give_type_description VARCHAR2(512),
  create_date           TIMESTAMP(6),
  creater               VARCHAR2(128),
  custom1               VARCHAR2(255),
  custom2               VARCHAR2(255),
  custom3               VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_GIVE_TYPE_DICT
  is '医院给予类型词典';
alter table ATS_DICT.HOSPITAL_GIVE_TYPE_DICT
  add constraint HOSPITAL_GIVE_TYPE_DICT_PK primary key (GIVE_TYPE_ID);

prompt
prompt Creating table HOSPITAL_SPECIMEN_ACTION_DICT
prompt ============================================
prompt
create table ATS_DICT.HOSPITAL_SPECIMEN_ACTION_DICT
(
  specimen_action_id          NUMBER not null,
  specimen_action_code        VARCHAR2(64) not null,
  specimen_action_name        VARCHAR2(64) not null,
  specimen_action_domain_name VARCHAR2(64),
  specimen_action_domain_id   VARCHAR2(64),
  specimen_action_description VARCHAR2(512),
  create_date                 TIMESTAMP(6),
  creater                     VARCHAR2(128),
  custom1                     VARCHAR2(255),
  custom2                     VARCHAR2(255),
  custom3                     VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_SPECIMEN_ACTION_DICT
  is '样本行为词典';
alter table ATS_DICT.HOSPITAL_SPECIMEN_ACTION_DICT
  add constraint SPECIMEN_ACTION_DICT_PK primary key (SPECIMEN_ACTION_ID);

prompt
prompt Creating table HOSPITAL_STAFF_DICT
prompt ==================================
prompt
create table ATS_DICT.HOSPITAL_STAFF_DICT
(
  staff_id           NUMBER not null,
  staff_code         VARCHAR2(64) not null,
  staff_name         VARCHAR2(64) not null,
  staff_description  VARCHAR2(512),
  staff_level        VARCHAR2(64),
  staff_jurisdiction VARCHAR2(512),
  staff_dept_id      VARCHAR2(16),
  staff_dept_domain  VARCHAR2(64),
  create_date        TIMESTAMP(6),
  creater            VARCHAR2(128),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_STAFF_DICT
  is '医院人员字典';
create index ATS_DICT.HOSPITAL_STAFF_CODE_IND on ATS_DICT.HOSPITAL_STAFF_DICT (STAFF_CODE);
alter table ATS_DICT.HOSPITAL_STAFF_DICT
  add constraint HOSPITAL_STAFF_DICT_PK primary key (STAFF_ID);

prompt
prompt Creating table HOSPITAL_TREATMENT_DICT
prompt ======================================
prompt
create table ATS_DICT.HOSPITAL_TREATMENT_DICT
(
  treatment_id          NUMBER not null,
  treatment_code        VARCHAR2(64) not null,
  treatment_name        VARCHAR2(64) not null,
  treatment_domain_name VARCHAR2(64),
  treatment_domain_id   VARCHAR2(64),
  treatment_description VARCHAR2(512),
  create_date           TIMESTAMP(6),
  creater               VARCHAR2(128),
  custom1               VARCHAR2(255),
  custom2               VARCHAR2(255),
  custom3               VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_TREATMENT_DICT
  is '治疗方式词典';
alter table ATS_DICT.HOSPITAL_TREATMENT_DICT
  add constraint HOSPITAL_TREATMENT_DICT_PK primary key (TREATMENT_ID);

prompt
prompt Creating table HOSPITAL_UNITS_DICT
prompt ==================================
prompt
create table ATS_DICT.HOSPITAL_UNITS_DICT
(
  units_id          NUMBER not null,
  units_code        VARCHAR2(64) not null,
  units_name        VARCHAR2(64) not null,
  units_domain_name VARCHAR2(64),
  units_domain_id   VARCHAR2(64),
  units_description VARCHAR2(512),
  create_date       TIMESTAMP(6),
  creater           VARCHAR2(128),
  custom1           VARCHAR2(255),
  custom2           VARCHAR2(255),
  custom3           VARCHAR2(255)
)
;
comment on table ATS_DICT.HOSPITAL_UNITS_DICT
  is '医院单位词典';
create index ATS_DICT.HOSPITAL_UNITS_CODE_IND on ATS_DICT.HOSPITAL_UNITS_DICT (UNITS_CODE);
alter table ATS_DICT.HOSPITAL_UNITS_DICT
  add constraint HOSPITAL_UNITS_DICT_PK primary key (UNITS_ID);

prompt
prompt Creating table ICD10_DICT
prompt =========================
prompt
create table ATS_DICT.ICD10_DICT
(
  icd10_cd    NUMBER not null,
  icd10_code  VARCHAR2(64) not null,
  icd10_name  VARCHAR2(64) not null,
  input_code  VARCHAR2(64) not null,
  create_name VARCHAR2(64),
  create_date TIMESTAMP(6),
  comments    VARCHAR2(255),
  custom1     VARCHAR2(255),
  custom2     VARCHAR2(255),
  custom3     VARCHAR2(255),
  custom4     VARCHAR2(255),
  custom5     VARCHAR2(255)
)
;
comment on table ATS_DICT.ICD10_DICT
  is 'icd10';
alter table ATS_DICT.ICD10_DICT
  add constraint ICD10_DICT_PK primary key (ICD10_CD);

prompt
prompt Creating table ICD9_DICT
prompt ========================
prompt
create table ATS_DICT.ICD9_DICT
(
  icd9_cd     NUMBER not null,
  icd9_code   VARCHAR2(64) not null,
  icd9_name   VARCHAR2(64) not null,
  input_code  VARCHAR2(64) not null,
  create_name VARCHAR2(64),
  create_date TIMESTAMP(6),
  comments    VARCHAR2(255),
  custom1     VARCHAR2(255),
  custom2     VARCHAR2(255),
  custom3     VARCHAR2(255),
  custom4     VARCHAR2(255),
  custom5     VARCHAR2(255)
)
;
comment on table ATS_DICT.ICD9_DICT
  is 'icd9';
alter table ATS_DICT.ICD9_DICT
  add constraint ICD0_DICT_PK primary key (ICD9_CD);

prompt
prompt Creating table IDENTIFIER_DOMAIN_DICT
prompt =====================================
prompt
create table ATS_DICT.IDENTIFIER_DOMAIN_DICT
(
  identifier_domain_id           NUMBER not null,
  universal_identifier           VARCHAR2(255),
  universal_identifier_type_code VARCHAR2(255),
  namespace_identifier           VARCHAR2(255),
  date_created                   TIMESTAMP(6) not null,
  creator_id                     NUMBER not null,
  comments                       VARCHAR2(255),
  custom1                        VARCHAR2(255),
  custom2                        VARCHAR2(255),
  custom3                        VARCHAR2(255),
  custom4                        VARCHAR2(255),
  custom5                        VARCHAR2(255)
)
;
comment on table ATS_DICT.IDENTIFIER_DOMAIN_DICT
  is '颁布机构';
create index ATS_DICT.NAMESPACE_IDEN_IDX on ATS_DICT.IDENTIFIER_DOMAIN_DICT (NAMESPACE_IDENTIFIER);
alter table ATS_DICT.IDENTIFIER_DOMAIN_DICT
  add constraint IDENTIFIER_DICT_PK primary key (IDENTIFIER_DOMAIN_ID);

prompt
prompt Creating table INPATIENT_CLASS_DICT
prompt ===================================
prompt
create table ATS_DICT.INPATIENT_CLASS_DICT
(
  inpatient_class_cd   NUMBER not null,
  inpatient_class_code VARCHAR2(64) not null,
  inpatient_class_name VARCHAR2(64) not null,
  input_code           VARCHAR2(64) not null,
  create_name          VARCHAR2(64),
  create_date          TIMESTAMP(6),
  comments             VARCHAR2(255),
  custom1              VARCHAR2(255),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255)
)
;
comment on table ATS_DICT.INPATIENT_CLASS_DICT
  is '入院方式';
alter table ATS_DICT.INPATIENT_CLASS_DICT
  add constraint INPATIENT_DICT_PK primary key (INPATIENT_CLASS_CD);

prompt
prompt Creating table INSURANCE_TYPE_DICT
prompt ==================================
prompt
create table ATS_DICT.INSURANCE_TYPE_DICT
(
  insurance_type_id   NUMBER not null,
  insurance_type_code VARCHAR2(64) not null,
  insurance_type_name VARCHAR2(64) not null,
  input_code          VARCHAR2(64) not null,
  create_name         VARCHAR2(64),
  create_date         TIMESTAMP(6),
  comments            VARCHAR2(255),
  custom1             VARCHAR2(255),
  custom2             VARCHAR2(255),
  custom3             VARCHAR2(255),
  custom4             VARCHAR2(255),
  custom5             VARCHAR2(255)
)
;
comment on table ATS_DICT.INSURANCE_TYPE_DICT
  is '医保类型字典';
alter table ATS_DICT.INSURANCE_TYPE_DICT
  add constraint INSURANCE_TYPE_DICT_PK primary key (INSURANCE_TYPE_ID);

prompt
prompt Creating table LANGUAGE_DICT
prompt ============================
prompt
create table ATS_DICT.LANGUAGE_DICT
(
  language_cd          NUMBER not null,
  language_name        VARCHAR2(64) not null,
  language_description VARCHAR2(255),
  language_code        VARCHAR2(64) not null,
  comments             VARCHAR2(255),
  custom1              VARCHAR2(255),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255)
)
;
comment on table ATS_DICT.LANGUAGE_DICT
  is '语言';
alter table ATS_DICT.LANGUAGE_DICT
  add constraint LANGUAGE_DICT_PK primary key (LANGUAGE_CD);

prompt
prompt Creating table MARITAL_STATUS_DICT
prompt ==================================
prompt
create table ATS_DICT.MARITAL_STATUS_DICT
(
  marital_status_cd   NUMBER not null,
  marital_status_code VARCHAR2(255),
  marital_status_name VARCHAR2(64) not null,
  input_code          VARCHAR2(64) not null,
  create_name         VARCHAR2(64),
  create_date         TIMESTAMP(6),
  comments            VARCHAR2(255),
  custom1             VARCHAR2(255),
  custom2             VARCHAR2(255),
  custom3             VARCHAR2(255),
  custom4             VARCHAR2(255),
  custom5             VARCHAR2(255)
)
;
comment on table ATS_DICT.MARITAL_STATUS_DICT
  is '婚姻状态';
alter table ATS_DICT.MARITAL_STATUS_DICT
  add constraint MARITAL_STATUS_DICT_PK primary key (MARITAL_STATUS_CD);

prompt
prompt Creating table MEDICINEASSORTKIND
prompt =================================
prompt
create table ATS_DICT.MEDICINEASSORTKIND
(
  medicinecodedattributeid VARCHAR2(128) not null,
  medicineid               VARCHAR2(128) not null,
  codedtermid              VARCHAR2(10) not null,
  codeid                   VARCHAR2(50) not null,
  attributevalue           VARCHAR2(30),
  isdeleted                NUMBER(1),
  rowversion               TIMESTAMP(6)
)
;

prompt
prompt Creating table NAME_TYPE_DICT
prompt =============================
prompt
create table ATS_DICT.NAME_TYPE_DICT
(
  name_type_cd          NUMBER not null,
  name_type_name        VARCHAR2(64) not null,
  name_type_description VARCHAR2(255),
  name_type_code        VARCHAR2(64) not null,
  create_name           VARCHAR2(64),
  create_date           TIMESTAMP(6),
  comments              VARCHAR2(255),
  custom1               VARCHAR2(255),
  custom2               VARCHAR2(255),
  custom3               VARCHAR2(255),
  custom4               VARCHAR2(255),
  custom5               VARCHAR2(255)
)
;
comment on table ATS_DICT.NAME_TYPE_DICT
  is '名字类型';
alter table ATS_DICT.NAME_TYPE_DICT
  add constraint NAME_TYPE_DICT_PK primary key (NAME_TYPE_CD);

prompt
prompt Creating table NATIONALITY_DICT
prompt ===============================
prompt
create table ATS_DICT.NATIONALITY_DICT
(
  nationality_cd          NUMBER not null,
  nationality_name        VARCHAR2(64) not null,
  nationality_description VARCHAR2(255),
  ationality_code         VARCHAR2(64) not null,
  create_name             VARCHAR2(64),
  create_date             TIMESTAMP(6),
  comments                VARCHAR2(255),
  custom1                 VARCHAR2(255),
  custom2                 VARCHAR2(255),
  custom3                 VARCHAR2(255),
  custom4                 VARCHAR2(255),
  custom5                 VARCHAR2(255)
)
;
comment on table ATS_DICT.NATIONALITY_DICT
  is '国籍';
alter table ATS_DICT.NATIONALITY_DICT
  add constraint NATIONAL_DICT_PK primary key (NATIONALITY_CD);

prompt
prompt Creating table NEW_DICTIONARY_ROUTE_RULE
prompt ========================================
prompt
create table ATS_DICT.NEW_DICTIONARY_ROUTE_RULE
(
  route_rule_pk      NUMBER not null,
  event_route_map_pk NUMBER not null,
  isdelete           VARCHAR2(2) default 0 not null,
  para_name          VARCHAR2(500) not null,
  operator           VARCHAR2(32) not null,
  value              VARCHAR2(64) not null
)
;
comment on table ATS_DICT.NEW_DICTIONARY_ROUTE_RULE
  is '字典推送路由表对应规则表';
comment on column ATS_DICT.NEW_DICTIONARY_ROUTE_RULE.route_rule_pk
  is '主键';
comment on column ATS_DICT.NEW_DICTIONARY_ROUTE_RULE.event_route_map_pk
  is '引用event_route_map路由表主键';
comment on column ATS_DICT.NEW_DICTIONARY_ROUTE_RULE.isdelete
  is '删除标志0正常1删除';
comment on column ATS_DICT.NEW_DICTIONARY_ROUTE_RULE.para_name
  is 'XPath校验的节点名称';
comment on column ATS_DICT.NEW_DICTIONARY_ROUTE_RULE.operator
  is '运算符。枚举值：equal(等于),greater(大于),less(小于),contain(包含),
Greater and equal(大于等于),less and equal(小于等于)';
comment on column ATS_DICT.NEW_DICTIONARY_ROUTE_RULE.value
  is '规则校验值';
create index ATS_DICT.EVENT_ROUTE_RULE_INDEX on ATS_DICT.NEW_DICTIONARY_ROUTE_RULE (EVENT_ROUTE_MAP_PK);
alter table ATS_DICT.NEW_DICTIONARY_ROUTE_RULE
  add constraint DICT_ROUTE_RULE_PK primary key (ROUTE_RULE_PK);

prompt
prompt Creating table NEW_DICT_ROUTE_MAP
prompt =================================
prompt
create table ATS_DICT.NEW_DICT_ROUTE_MAP
(
  pk                         NUMBER not null,
  pay_load_type              VARCHAR2(128) not null,
  event_name                 VARCHAR2(32),
  event_type                 VARCHAR2(32),
  event_send_domain_uid      VARCHAR2(64) not null,
  event_send_type            VARCHAR2(64),
  event_send_name            VARCHAR2(64),
  event_rec_domain_uid       VARCHAR2(64) not null,
  event_rec_type             VARCHAR2(64),
  event_rec_name             VARCHAR2(64),
  event_send_soap_notice_url VARCHAR2(128),
  event_rec_soap_notice_url  VARCHAR2(255) not null,
  isdeleted                  VARCHAR2(10) default 0 not null,
  custom1                    VARCHAR2(255),
  custom2                    VARCHAR2(255),
  custom3                    VARCHAR2(255),
  custom4                    VARCHAR2(255),
  custom5                    VARCHAR2(255)
)
;
comment on table ATS_DICT.NEW_DICT_ROUTE_MAP
  is '字典推送路由表';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.pk
  is '主键 ';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_name
  is '事件名称';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_type
  is '事件类型';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_send_domain_uid
  is '发送者域';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_send_type
  is '发送者类型';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_send_name
  is '发送者名称';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_rec_domain_uid
  is '接收者域';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_rec_type
  is '接收者类型';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_rec_name
  is '接收者名称';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_send_soap_notice_url
  is '发送者地址';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.event_rec_soap_notice_url
  is '接收者地址';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.custom1
  is '备用1';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.custom2
  is '备用2';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.custom3
  is '备用3';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.custom4
  is '备用4';
comment on column ATS_DICT.NEW_DICT_ROUTE_MAP.custom5
  is '备用5';
alter table ATS_DICT.NEW_DICT_ROUTE_MAP
  add constraint NEW_DICT_ROUTE_MAP_PK primary key (PK);
grant select on ATS_DICT.NEW_DICT_ROUTE_MAP to PMTEST with grant option;

prompt
prompt Creating table OBR_RESULT_STATUS_DICT
prompt =====================================
prompt
create table ATS_DICT.OBR_RESULT_STATUS_DICT
(
  obr_result_status_id          NUMBER not null,
  obr_result_status_code        VARCHAR2(64) not null,
  obr_result_status_name        VARCHAR2(64) not null,
  obr_result_status_domain_name VARCHAR2(64),
  obr_result_status_domain_id   VARCHAR2(64),
  obr_result_status_description VARCHAR2(512),
  create_date                   TIMESTAMP(6),
  creater                       VARCHAR2(128),
  custom1                       VARCHAR2(255),
  custom2                       VARCHAR2(255),
  custom3                       VARCHAR2(255)
)
;
comment on table ATS_DICT.OBR_RESULT_STATUS_DICT
  is '结果状态词典';
alter table ATS_DICT.OBR_RESULT_STATUS_DICT
  add constraint OBR_RESULT_STATUS_DICT_PK primary key (OBR_RESULT_STATUS_ID);

prompt
prompt Creating table OBX_ABNORMAL_TYPE_DICT
prompt =====================================
prompt
create table ATS_DICT.OBX_ABNORMAL_TYPE_DICT
(
  obx_abnormal_type_id          NUMBER not null,
  obx_abnormal_type_code        VARCHAR2(64) not null,
  obx_abnormal_type_name        VARCHAR2(64) not null,
  obx_abnormal_type_domain_name VARCHAR2(64),
  obx_abnormal_type_domain_id   VARCHAR2(64),
  obx_abnormal_type_description VARCHAR2(512),
  create_date                   TIMESTAMP(6),
  creater                       VARCHAR2(128),
  custom1                       VARCHAR2(255),
  custom2                       VARCHAR2(255),
  custom3                       VARCHAR2(255)
)
;
comment on table ATS_DICT.OBX_ABNORMAL_TYPE_DICT
  is 'OBX异常类型词典';
alter table ATS_DICT.OBX_ABNORMAL_TYPE_DICT
  add constraint OBX_ABNORMAL_TYPE_DICT_PK primary key (OBX_ABNORMAL_TYPE_ID);

prompt
prompt Creating table OBX_VALUE_TYPE_DICT
prompt ==================================
prompt
create table ATS_DICT.OBX_VALUE_TYPE_DICT
(
  obx_value_type_id          NUMBER not null,
  obx_value_type_code        VARCHAR2(64) not null,
  obx_value_type_name        VARCHAR2(64) not null,
  obx_value_type_domain_name VARCHAR2(64),
  obx_value_type_domain_id   VARCHAR2(64),
  obx_value_type_description VARCHAR2(512),
  create_date                TIMESTAMP(6),
  creater                    VARCHAR2(128),
  custom1                    VARCHAR2(255),
  custom2                    VARCHAR2(255),
  custom3                    VARCHAR2(255)
)
;
comment on table ATS_DICT.OBX_VALUE_TYPE_DICT
  is 'OBX2值类型词典';
alter table ATS_DICT.OBX_VALUE_TYPE_DICT
  add constraint OBX_VALUE_TYPE_DICT_PK primary key (OBX_VALUE_TYPE_ID);

prompt
prompt Creating table PAT_ACCOUNT_STATUS_DICT
prompt ======================================
prompt
create table ATS_DICT.PAT_ACCOUNT_STATUS_DICT
(
  pat_account_status_id   NUMBER not null,
  pat_account_status_code VARCHAR2(64) not null,
  pat_account_status_name VARCHAR2(64) not null,
  input_code              VARCHAR2(64) not null,
  create_name             VARCHAR2(64),
  create_date             TIMESTAMP(6),
  comments                VARCHAR2(255),
  custom1                 VARCHAR2(255),
  custom2                 VARCHAR2(255),
  custom3                 VARCHAR2(255),
  custom4                 VARCHAR2(255),
  custom5                 VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_ACCOUNT_STATUS_DICT
  is '支付 状态字典';
alter table ATS_DICT.PAT_ACCOUNT_STATUS_DICT
  add constraint PAT_ACCOUNT_STATUS_DICT_PK primary key (PAT_ACCOUNT_STATUS_ID);

prompt
prompt Creating table PAT_ADMISIOON_DICT
prompt =================================
prompt
create table ATS_DICT.PAT_ADMISIOON_DICT
(
  pat_admisioon_id   NUMBER not null,
  pat_admisioon_code VARCHAR2(64) not null,
  pat_admisioon_name VARCHAR2(64) not null,
  input_code         VARCHAR2(64) not null,
  create_name        VARCHAR2(64),
  create_date        TIMESTAMP(6),
  comments           VARCHAR2(255),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255),
  custom4            VARCHAR2(255),
  custom5            VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_ADMISIOON_DICT
  is '入院情况类型字典';
alter table ATS_DICT.PAT_ADMISIOON_DICT
  add constraint PAT_ADMISIOON_DICT_PK primary key (PAT_ADMISIOON_ID);

prompt
prompt Creating table PAT_ADMISIOON_SOURCE_DICT
prompt ========================================
prompt
create table ATS_DICT.PAT_ADMISIOON_SOURCE_DICT
(
  pat_admisioon_source_id   NUMBER not null,
  pat_admisioon_source_code VARCHAR2(64) not null,
  pat_admisioon_source_name VARCHAR2(64) not null,
  input_code                VARCHAR2(64) not null,
  create_name               VARCHAR2(64),
  create_date               TIMESTAMP(6),
  comments                  VARCHAR2(255),
  custom1                   VARCHAR2(255),
  custom2                   VARCHAR2(255),
  custom3                   VARCHAR2(255),
  custom4                   VARCHAR2(255),
  custom5                   VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_ADMISIOON_SOURCE_DICT
  is '入院途径类型字典';
alter table ATS_DICT.PAT_ADMISIOON_SOURCE_DICT
  add constraint PAT_ADMISIOON_SOURCE_DICT_PK primary key (PAT_ADMISIOON_SOURCE_ID);

prompt
prompt Creating table PAT_CATEGORY_DICT
prompt ================================
prompt
create table ATS_DICT.PAT_CATEGORY_DICT
(
  pat_category_id   NUMBER not null,
  pat_category_code VARCHAR2(64) not null,
  pat_category_name VARCHAR2(64) not null,
  input_code        VARCHAR2(64) not null,
  create_name       VARCHAR2(64),
  create_date       TIMESTAMP(6),
  comments          VARCHAR2(255),
  custom1           VARCHAR2(255),
  custom2           VARCHAR2(255),
  custom3           VARCHAR2(255),
  custom4           VARCHAR2(255),
  custom5           VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_CATEGORY_DICT
  is '患者类别字典';
alter table ATS_DICT.PAT_CATEGORY_DICT
  add constraint PAT_CATEGORY_DICT_PK primary key (PAT_CATEGORY_ID);

prompt
prompt Creating table PAT_CLASS_DICT
prompt =============================
prompt
create table ATS_DICT.PAT_CLASS_DICT
(
  pat_class_id   NUMBER not null,
  pat_class_code VARCHAR2(64) not null,
  pat_class_name VARCHAR2(64) not null,
  input_code     VARCHAR2(64) not null,
  create_name    VARCHAR2(64),
  create_date    TIMESTAMP(6),
  comments       VARCHAR2(255),
  custom1        VARCHAR2(255),
  custom2        VARCHAR2(255),
  custom3        VARCHAR2(255),
  custom4        VARCHAR2(255),
  custom5        VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_CLASS_DICT
  is '患者类型字典';
alter table ATS_DICT.PAT_CLASS_DICT
  add constraint PAT_CLASS_DICT_PK primary key (PAT_CLASS_ID);

prompt
prompt Creating table PAT_DISCHARGE_DICT
prompt =================================
prompt
create table ATS_DICT.PAT_DISCHARGE_DICT
(
  pat_discharge_id   NUMBER not null,
  pat_discharge_code VARCHAR2(64) not null,
  pat_discharge_name VARCHAR2(64) not null,
  input_code         VARCHAR2(64) not null,
  create_name        VARCHAR2(64),
  create_date        TIMESTAMP(6),
  comments           VARCHAR2(255),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255),
  custom4            VARCHAR2(255),
  custom5            VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_DISCHARGE_DICT
  is '出院处置字典';
alter table ATS_DICT.PAT_DISCHARGE_DICT
  add constraint PAT_DISCHARGE_DICT_PK primary key (PAT_DISCHARGE_ID);

prompt
prompt Creating table PAT_IP_STATUS_DICT
prompt =================================
prompt
create table ATS_DICT.PAT_IP_STATUS_DICT
(
  pat_ip_status_id   NUMBER not null,
  pat_ip_status_code VARCHAR2(64) not null,
  pat_ip_status_name VARCHAR2(64) not null,
  input_code         VARCHAR2(64) not null,
  create_name        VARCHAR2(64),
  create_date        TIMESTAMP(6),
  comments           VARCHAR2(255),
  custom1            VARCHAR2(255),
  custom2            VARCHAR2(255),
  custom3            VARCHAR2(255),
  custom4            VARCHAR2(255),
  custom5            VARCHAR2(255)
)
;
comment on table ATS_DICT.PAT_IP_STATUS_DICT
  is '住院状态字典';
alter table ATS_DICT.PAT_IP_STATUS_DICT
  add constraint PAT_IP_STATUS_DICT_PK primary key (PAT_IP_STATUS_ID);

prompt
prompt Creating table PAY_LOAD_TYPE_MAP
prompt ================================
prompt
create table ATS_DICT.PAY_LOAD_TYPE_MAP
(
  pk            NUMBER not null,
  pay_load_type VARCHAR2(50),
  table_name    VARCHAR2(50),
  new_clomn     VARCHAR2(255),
  clob_clomn    VARCHAR2(255),
  pk_name       VARCHAR2(100),
  filter        VARCHAR2(1) not null
)
;
comment on column ATS_DICT.PAY_LOAD_TYPE_MAP.pay_load_type
  is '新增荷载类型';
comment on column ATS_DICT.PAY_LOAD_TYPE_MAP.table_name
  is '荷载类型所属表名';
comment on column ATS_DICT.PAY_LOAD_TYPE_MAP.new_clomn
  is '中心端新增字段（多个以英文,相隔）';
comment on column ATS_DICT.PAY_LOAD_TYPE_MAP.clob_clomn
  is '新增clob中对应的节点名称';
comment on column ATS_DICT.PAY_LOAD_TYPE_MAP.pk_name
  is '对应字典变唯一标识11';
comment on column ATS_DICT.PAY_LOAD_TYPE_MAP.filter
  is '是否过滤  1是0否';
alter table ATS_DICT.PAY_LOAD_TYPE_MAP
  add constraint PK_PK primary key (PK);

prompt
prompt Creating table PHONE_TYPE_DICT
prompt ==============================
prompt
create table ATS_DICT.PHONE_TYPE_DICT
(
  phone_type_cd          NUMBER not null,
  phone_type_name        VARCHAR2(64) not null,
  phone_type_description VARCHAR2(255),
  phone_type_code        VARCHAR2(64) not null,
  create_name            VARCHAR2(64),
  create_date            TIMESTAMP(6),
  comments               VARCHAR2(255),
  custom1                VARCHAR2(255),
  custom2                VARCHAR2(255),
  custom3                VARCHAR2(255),
  custom4                VARCHAR2(255),
  custom5                VARCHAR2(255)
)
;
comment on table ATS_DICT.PHONE_TYPE_DICT
  is '电话类型';
comment on column ATS_DICT.PHONE_TYPE_DICT.phone_type_cd
  is 'cd';
alter table ATS_DICT.PHONE_TYPE_DICT
  add constraint PHONE_TYPE_DICT primary key (PHONE_TYPE_CD);

prompt
prompt Creating table PID4_FIELDS_DICT
prompt ===============================
prompt
create table ATS_DICT.PID4_FIELDS_DICT
(
  fields_id   NUMBER not null,
  fields_name VARCHAR2(512),
  custom1     VARCHAR2(256),
  custom2     VARCHAR2(256),
  custom3     VARCHAR2(256)
)
;
alter table ATS_DICT.PID4_FIELDS_DICT
  add constraint PID4_FIELDS_PK primary key (FIELDS_ID);

prompt
prompt Creating table PROFESSION_TYPE_DICT
prompt ===================================
prompt
create table ATS_DICT.PROFESSION_TYPE_DICT
(
  profession_type_cd          NUMBER not null,
  profession_type_name        VARCHAR2(64) not null,
  profession_type_description VARCHAR2(255),
  profession_type_code        VARCHAR2(64) not null,
  create_name                 VARCHAR2(64),
  create_date                 TIMESTAMP(6),
  comments                    VARCHAR2(255),
  custom1                     VARCHAR2(255),
  custom2                     VARCHAR2(255),
  custom3                     VARCHAR2(255),
  custom4                     VARCHAR2(255),
  custom5                     VARCHAR2(255)
)
;
comment on table ATS_DICT.PROFESSION_TYPE_DICT
  is '职业类型';
alter table ATS_DICT.PROFESSION_TYPE_DICT
  add constraint PROFESSION_TYPE_DICT_PK primary key (PROFESSION_TYPE_CD);

prompt
prompt Creating table QR_PID
prompt =====================
prompt
create table ATS_DICT.QR_PID
(
  id     NUMBER,
  pid    VARCHAR2(10) not null,
  status VARCHAR2(4)
)
;

prompt
prompt Creating table QR_USER
prompt ======================
prompt
create table ATS_DICT.QR_USER
(
  id         NUMBER,
  username   VARCHAR2(10) not null,
  pass       VARCHAR2(100),
  createtime DATE,
  status     VARCHAR2(4)
)
;

prompt
prompt Creating table RACE_DICT
prompt ========================
prompt
create table ATS_DICT.RACE_DICT
(
  race_cd          NUMBER not null,
  race_name        VARCHAR2(64) not null,
  race_description VARCHAR2(255),
  race_code        VARCHAR2(64) not null,
  create_name      VARCHAR2(64),
  create_date      TIMESTAMP(6),
  comments         VARCHAR2(255),
  custom1          VARCHAR2(255),
  custom2          VARCHAR2(255),
  custom3          VARCHAR2(255),
  custom4          VARCHAR2(255),
  custom5          VARCHAR2(255)
)
;
comment on table ATS_DICT.RACE_DICT
  is '种族';
alter table ATS_DICT.RACE_DICT
  add constraint RACE_DICT_PK primary key (RACE_CD);

prompt
prompt Creating table RELATIONSHIP_TYPE_DICT
prompt =====================================
prompt
create table ATS_DICT.RELATIONSHIP_TYPE_DICT
(
  relationship_type_cd   NUMBER not null,
  relationship_type_code VARCHAR2(64) not null,
  relationship_type_name VARCHAR2(64) not null,
  input_code             VARCHAR2(64) not null,
  create_name            VARCHAR2(64),
  create_date            TIMESTAMP(6),
  comments               VARCHAR2(255),
  custom1                VARCHAR2(255),
  custom2                VARCHAR2(255),
  custom3                VARCHAR2(255),
  custom4                VARCHAR2(255),
  custom5                VARCHAR2(255)
)
;
comment on table ATS_DICT.RELATIONSHIP_TYPE_DICT
  is '社会关系';
alter table ATS_DICT.RELATIONSHIP_TYPE_DICT
  add constraint RELATIONSHIP_TYPE_DICT_PK primary key (RELATIONSHIP_TYPE_CD);

prompt
prompt Creating table RELIGION_DICT
prompt ============================
prompt
create table ATS_DICT.RELIGION_DICT
(
  religion_cd          NUMBER not null,
  religion_name        VARCHAR2(64) not null,
  religion_description VARCHAR2(255),
  religion_code        VARCHAR2(64) not null,
  create_name          VARCHAR2(64),
  create_date          TIMESTAMP(6),
  comments             VARCHAR2(255),
  custom1              VARCHAR2(255),
  custom2              VARCHAR2(255),
  custom3              VARCHAR2(255),
  custom4              VARCHAR2(255),
  custom5              VARCHAR2(255)
)
;
comment on table ATS_DICT.RELIGION_DICT
  is '宗教';
alter table ATS_DICT.RELIGION_DICT
  add constraint RELIGION_DICT_PK primary key (RELIGION_CD);

prompt
prompt Creating table REPORT_INFOMATION
prompt ================================
prompt
create table ATS_DICT.REPORT_INFOMATION
(
  card_no   VARCHAR2(30) not null,
  is_upload VARCHAR2(10),
  item_code VARCHAR2(500),
  file_name VARCHAR2(500),
  file_addr VARCHAR2(500),
  pk        NUMBER not null,
  item_name VARCHAR2(100),
  name      VARCHAR2(80),
  sex_code  VARCHAR2(1),
  birthday  DATE,
  oper_date DATE,
  exec_dpcd VARCHAR2(4),
  exec_dpnm VARCHAR2(20),
  code      VARCHAR2(10),
  dutycode  VARCHAR2(200),
  dutyname  VARCHAR2(200),
  docname   VARCHAR2(200),
  issys     VARCHAR2(10)
)
;
comment on column ATS_DICT.REPORT_INFOMATION.is_upload
  is '上传状态 0表示失败，1表示成功';
comment on column ATS_DICT.REPORT_INFOMATION.item_code
  is '项目编码';
comment on column ATS_DICT.REPORT_INFOMATION.file_name
  is '文件名';
comment on column ATS_DICT.REPORT_INFOMATION.file_addr
  is '文件地址';
comment on column ATS_DICT.REPORT_INFOMATION.item_name
  is '检查项目名';
comment on column ATS_DICT.REPORT_INFOMATION.name
  is '患者姓名';
comment on column ATS_DICT.REPORT_INFOMATION.oper_date
  is '检查日期';
comment on column ATS_DICT.REPORT_INFOMATION.code
  is '工号';
comment on column ATS_DICT.REPORT_INFOMATION.dutycode
  is '职务编码';
comment on column ATS_DICT.REPORT_INFOMATION.dutyname
  is '职务名称';
comment on column ATS_DICT.REPORT_INFOMATION.docname
  is '医生姓名';
comment on column ATS_DICT.REPORT_INFOMATION.issys
  is '是否同步到中心端 0表示失败，1或null表示成功';
alter table ATS_DICT.REPORT_INFOMATION
  add constraint REPORT_PK primary key (PK)
  disable;

prompt
prompt Creating table REPORT_STATUS_FLOW_DICT
prompt ======================================
prompt
create table ATS_DICT.REPORT_STATUS_FLOW_DICT
(
  pk                 NUMBER not null,
  pay_load_type      NVARCHAR2(64) not null,
  event_status       NVARCHAR2(64) not null,
  ccd_flow_node_name NVARCHAR2(64) not null,
  ccd_position       NVARCHAR2(64) not null
)
;
comment on table ATS_DICT.REPORT_STATUS_FLOW_DICT
  is '报告状态和流程图的位置关系字典表';
comment on column ATS_DICT.REPORT_STATUS_FLOW_DICT.pk
  is 'PK';
comment on column ATS_DICT.REPORT_STATUS_FLOW_DICT.pay_load_type
  is '荷载类型';
comment on column ATS_DICT.REPORT_STATUS_FLOW_DICT.event_status
  is ' 厂商上传报告状态';
comment on column ATS_DICT.REPORT_STATUS_FLOW_DICT.ccd_flow_node_name
  is 'CCD中状态';
comment on column ATS_DICT.REPORT_STATUS_FLOW_DICT.ccd_position
  is 'CCD中位置';

prompt
prompt Creating table SICKBED_DICT
prompt ===========================
prompt
create table ATS_DICT.SICKBED_DICT
(
  sickbed_cd   NUMBER not null,
  sickbed_code VARCHAR2(64) not null,
  sickbed_name VARCHAR2(64) not null,
  input_code   VARCHAR2(64) not null,
  create_name  VARCHAR2(64),
  create_date  TIMESTAMP(6),
  comments     VARCHAR2(255),
  custom1      VARCHAR2(255),
  custom2      VARCHAR2(255),
  custom3      VARCHAR2(255),
  custom4      VARCHAR2(255),
  custom5      VARCHAR2(255)
)
;
comment on table ATS_DICT.SICKBED_DICT
  is '病床';
alter table ATS_DICT.SICKBED_DICT
  add constraint SICKBED_DICT_PK primary key (SICKBED_CD);

prompt
prompt Creating table SOURCE_PLATFORM_MAPPING
prompt ======================================
prompt
create table ATS_DICT.SOURCE_PLATFORM_MAPPING
(
  source_column_pk       NUMBER not null,
  source_pk              NUMBER not null,
  hiup_column            VARCHAR2(128) not null,
  hiup_column_desc       VARCHAR2(256) not null,
  source_column          VARCHAR2(128) not null,
  source_column_desc     VARCHAR2(256) not null,
  source_primarykey      VARCHAR2(256),
  hiup_primarykey        VARCHAR2(256),
  hiup_table             VARCHAR2(256),
  source_table           VARCHAR2(256),
  hiup_primarykey_name   VARCHAR2(256),
  source_primarykey_name VARCHAR2(256),
  hiup_dbmanage_pk       VARCHAR2(128),
  source_dbmanage_pk     VARCHAR2(128),
  hiup_database          VARCHAR2(128)
)
;
comment on table ATS_DICT.SOURCE_PLATFORM_MAPPING
  is '字典源表字段和平台字段表字段映射关系表';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_column_pk
  is '表主键';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_pk
  is '平台字典表和字典源库表映射关系表主键';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_column
  is '平台库表中的列名';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_column_desc
  is '平台库表中的描述';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_column
  is '字典源库表中的列名';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_column_desc
  is '字典源库表中的列描述';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_primarykey
  is '该字段是否是源表主键----0是1否';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_primarykey
  is '该字段是否是平台表主键----0是1否';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_table
  is '平台表名';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_table
  is '源表名';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_primarykey_name
  is '平台主键名';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_primarykey_name
  is '源表主键名';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_dbmanage_pk
  is '平台表对应的表数据库管理表PK';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.source_dbmanage_pk
  is '源表对应的表数据库管理表PK';
comment on column ATS_DICT.SOURCE_PLATFORM_MAPPING.hiup_database
  is '平台表对应的库';
alter table ATS_DICT.SOURCE_PLATFORM_MAPPING
  add primary key (SOURCE_COLUMN_PK);

prompt
prompt Creating table SPECIMEN_SOURCE_DICT
prompt ===================================
prompt
create table ATS_DICT.SPECIMEN_SOURCE_DICT
(
  specimen_source_id          NUMBER not null,
  specimen_source_code        VARCHAR2(64) not null,
  specimen_source_name        VARCHAR2(64) not null,
  specimen_source_domain_name VARCHAR2(64),
  specimen_source_domain_id   VARCHAR2(64),
  specimen_source_description VARCHAR2(512),
  create_date                 TIMESTAMP(6),
  creater                     VARCHAR2(128),
  custom1                     VARCHAR2(255),
  custom2                     VARCHAR2(255),
  custom3                     VARCHAR2(255)
)
;
comment on table ATS_DICT.SPECIMEN_SOURCE_DICT
  is '标本来源词典';
alter table ATS_DICT.SPECIMEN_SOURCE_DICT
  add constraint SPECIMEN_SOURCE_DICT_PK primary key (SPECIMEN_SOURCE_ID);

prompt
prompt Creating table TRANS_MODE_DICT
prompt ==============================
prompt
create table ATS_DICT.TRANS_MODE_DICT
(
  trans_mode_id          NUMBER not null,
  trans_mode_code        VARCHAR2(64) not null,
  trans_mode_name        VARCHAR2(64) not null,
  trans_mode_domain_name VARCHAR2(64),
  trans_mode_domain_id   VARCHAR2(64),
  trans_mode_description VARCHAR2(512),
  create_date            TIMESTAMP(6),
  creater                VARCHAR2(128),
  custom1                VARCHAR2(255),
  custom2                VARCHAR2(255),
  custom3                VARCHAR2(255)
)
;
comment on table ATS_DICT.TRANS_MODE_DICT
  is '结果状态词典';
alter table ATS_DICT.TRANS_MODE_DICT
  add constraint TRANS_MODE_DICT_PK primary key (TRANS_MODE_ID);

prompt
prompt Creating table WORKSTATUS_TYPE_DICT
prompt ===================================
prompt
create table ATS_DICT.WORKSTATUS_TYPE_DICT
(
  workstatus_type_cd          NUMBER not null,
  workstatus_type_name        VARCHAR2(64) not null,
  workstatus_type_description VARCHAR2(255),
  workstatus_type_code        VARCHAR2(64) not null,
  create_name                 VARCHAR2(64),
  create_date                 TIMESTAMP(6),
  comments                    VARCHAR2(255),
  custom1                     VARCHAR2(255),
  custom2                     VARCHAR2(255),
  custom3                     VARCHAR2(255),
  custom4                     VARCHAR2(255),
  custom5                     VARCHAR2(255)
)
;
comment on table ATS_DICT.WORKSTATUS_TYPE_DICT
  is '工作状态';
alter table ATS_DICT.WORKSTATUS_TYPE_DICT
  add constraint WORKSTATUS_TYPE_DICT_PK primary key (WORKSTATUS_TYPE_CD);

prompt
prompt Creating sequence A_SEQ
prompt =======================
prompt
create sequence ATS_DICT.A_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 181
increment by 1
cache 20;

prompt
prompt Creating sequence CONSUME_PLATFORM_MAPPING_SEQ
prompt ==============================================
prompt
create sequence ATS_DICT.CONSUME_PLATFORM_MAPPING_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 22921
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_CONSTANT_DOMAIN_SEQ
prompt ==========================================
prompt
create sequence ATS_DICT.DICT_CONSTANT_DOMAIN_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_CONSTANT_MAP_SEQ
prompt =======================================
prompt
create sequence ATS_DICT.DICT_CONSTANT_MAP_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_CONSUME_MAPPING_SEQ
prompt ==========================================
prompt
create sequence ATS_DICT.DICT_CONSUME_MAPPING_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 301
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_DB_MANAGE_SEQ
prompt ====================================
prompt
create sequence ATS_DICT.DICT_DB_MANAGE_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 181
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_FIELD_MAP_SEQ
prompt ====================================
prompt
create sequence ATS_DICT.DICT_FIELD_MAP_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_PAY_LOAD_TYPE_SEQ
prompt ========================================
prompt
create sequence ATS_DICT.DICT_PAY_LOAD_TYPE_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 700
increment by 1
cache 10;

prompt
prompt Creating sequence DICT_PUSH_LOG_SEQ
prompt ===================================
prompt
create sequence ATS_DICT.DICT_PUSH_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 23241
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_REC_INFO_SEQ
prompt ===================================
prompt
create sequence ATS_DICT.DICT_REC_INFO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 31181
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_ROUTE_MAP_SEQ
prompt ====================================
prompt
create sequence ATS_DICT.DICT_ROUTE_MAP_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_SCATTER_LOG_SEQ
prompt ======================================
prompt
create sequence ATS_DICT.DICT_SCATTER_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 21801
increment by 1
cache 20;

prompt
prompt Creating sequence DICT_SOURCE_MAPPING_SEQ
prompt =========================================
prompt
create sequence ATS_DICT.DICT_SOURCE_MAPPING_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 201
increment by 1
cache 20;

prompt
prompt Creating sequence ENT_MAC_VESRSION_SEQ
prompt ======================================
prompt
create sequence ATS_DICT.ENT_MAC_VESRSION_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 326
increment by 1
cache 20;

prompt
prompt Creating sequence EVENT_PROCESS_NOTICE_INFO_SEQ
prompt ===============================================
prompt
create sequence ATS_DICT.EVENT_PROCESS_NOTICE_INFO_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 4825
increment by 1
cache 20;

prompt
prompt Creating sequence EVENT_PUSH_ROUTE_SEQ
prompt ======================================
prompt
create sequence ATS_DICT.EVENT_PUSH_ROUTE_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 4981
increment by 1
cache 20;

prompt
prompt Creating sequence EVENT_REC_INFO_SEQ
prompt ====================================
prompt
create sequence ATS_DICT.EVENT_REC_INFO_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 169434
increment by 1
cache 20;

prompt
prompt Creating sequence EVENT_USE_ROUTE_SEQ
prompt =====================================
prompt
create sequence ATS_DICT.EVENT_USE_ROUTE_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 2981
increment by 1
cache 20;

prompt
prompt Creating sequence HOSPITAL_DOMAIN_DICT_SEQ
prompt ==========================================
prompt
create sequence ATS_DICT.HOSPITAL_DOMAIN_DICT_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 840
increment by 1
cache 20;

prompt
prompt Creating sequence IDENTIFIER_DOMAIN_SEQ
prompt =======================================
prompt
create sequence ATS_DICT.IDENTIFIER_DOMAIN_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence JY_COMPLEXPROJECT_SEQ
prompt =======================================
prompt
create sequence ATS_DICT.JY_COMPLEXPROJECT_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 181
increment by 1
cache 20;

prompt
prompt Creating sequence JY_DICTASYNCQUERYITEM_SEQ
prompt ===========================================
prompt
create sequence ATS_DICT.JY_DICTASYNCQUERYITEM_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 121
increment by 1
cache 20;

prompt
prompt Creating sequence JY_INFO_ZSYYSOURCEINFO_SEQ
prompt ============================================
prompt
create sequence ATS_DICT.JY_INFO_ZSYYSOURCEINFO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 161
increment by 1
cache 20;

prompt
prompt Creating sequence JY_NONPHARDETAILS_SEQ
prompt =======================================
prompt
create sequence ATS_DICT.JY_NONPHARDETAILS_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 121
increment by 1
cache 20;

prompt
prompt Creating sequence JY_TEST_ZSYYSOURCEINFO_SEQ
prompt ============================================
prompt
create sequence ATS_DICT.JY_TEST_ZSYYSOURCEINFO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 141
increment by 1
cache 20;

prompt
prompt Creating sequence JY_ZSYYDEPTGROUPINFO_SEQ
prompt ==========================================
prompt
create sequence ATS_DICT.JY_ZSYYDEPTGROUPINFO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 281
increment by 1
cache 20;

prompt
prompt Creating sequence JY_ZSYYPROFILEINFO_SEQ
prompt ========================================
prompt
create sequence ATS_DICT.JY_ZSYYPROFILEINFO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 121
increment by 1
cache 20;

prompt
prompt Creating sequence JY_ZSYYSINGLETESTINFO_SEQ
prompt ===========================================
prompt
create sequence ATS_DICT.JY_ZSYYSINGLETESTINFO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 181
increment by 1
cache 20;

prompt
prompt Creating sequence MAPPING_SEQ
prompt =============================
prompt
create sequence ATS_DICT.MAPPING_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 21821
increment by 1
cache 20;

prompt
prompt Creating sequence NEW_DICTIONARY_ROUTE_RULE_SEQ
prompt ===============================================
prompt
create sequence ATS_DICT.NEW_DICTIONARY_ROUTE_RULE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 663
increment by 1
nocache;

prompt
prompt Creating sequence NEW_DICT_ROUTE_MAP_SEQ
prompt ========================================
prompt
create sequence ATS_DICT.NEW_DICT_ROUTE_MAP_SEQ
minvalue 1
maxvalue 9223372036854775807
start with 150
increment by 1
cache 20;

prompt
prompt Creating sequence REPORT_INFOMATION_SEQ
prompt =======================================
prompt
create sequence ATS_DICT.REPORT_INFOMATION_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 772
increment by 1
cache 20;

prompt
prompt Creating sequence REPORT_STATUS_FLOW_DICT_SEQ
prompt =============================================
prompt
create sequence ATS_DICT.REPORT_STATUS_FLOW_DICT_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SOURCE_PLATFORM_MAPPING_SEQ
prompt =============================================
prompt
create sequence ATS_DICT.SOURCE_PLATFORM_MAPPING_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 22981
increment by 1
cache 20;

prompt
prompt Creating view SYSDICTSTATUS
prompt ===========================
prompt
create or replace force view ats_dict.sysdictstatus
(pk, dict_uuid_from, dict_from, dict_id_from, pay_load_type, sub_type_name, send_name, rec_domain_uid, dict_rec_name, dict_name, dict_type, push_status, event_status, notice_status, use_status, if_from, start_time)
as
select a.PK,a.DICT_UUID_FROM,a.DICT_FROM,a.DICT_ID_FROM,
a.pay_load_type,s.name as sub_type_name,
e.dict_send_name,e.dict_rec_domain_uid,e.dict_rec_name,e.dict_name,e.dict_type,
a.hiup_status as push_status,c.event_status,
c.hiup_status as notice_status,d.hiup_status as use_status,d.if_From,a.start_time
from ATS_DICT.event_push_route_info a
left join ATS_DICT.dict_sub_type s on a.sub_type=s.code
left join ATS_DICT.dict_route_map e on a.pay_load_type=e.pay_load_type and a.dict_to=e.dict_rec_domain_uid
left join ATS_DICT.event_process_notice_info c on a.dict_uuid_from=c.dict_uuid_from and a.dict_from = c.dict_from and a.dict_id_from = c.dict_id_from
left join ATS_DICT.event_use_route_info d on c.dict_uuid_from=d.dict_uuid_from and c.dict_from=d.dict_from and c.dict_id_from=d.dict_id_from
where  e.isdeleted='0' order by a.start_time desc;

prompt
prompt Creating view V_DICT_PUSH_SCATTER_STATUS
prompt ========================================
prompt
create or replace force view ats_dict.v_dict_push_scatter_status as
select t.uuid,t.msg_id,t.pay_load_type,t.rec_time,(t.queue_staus+t.push_status+t.scatter_status) as status,t.sub_type  from (select d.uuid,d.msg_id,d.pay_load_type,d.rec_time,d.sub_type,
      count( case   when  (d.push_status = '2' or  d.scatter_status = '2'） then  '失败' end) as queue_staus,
      count(case when dl.push_status!='0' or dl.push_status is null then '失败' end) as push_status,
      count(case when sl.scatter_status!='0' or sl.scatter_status is null then '失败' end ) as scatter_status
  from dict_rec_info d
  left join dict_push_log dl
    on d.uuid = dl.uuid
  left join dict_scatter_log sl
    on d.uuid = sl.uuid
     group by d.uuid,d.msg_id,d.pay_load_type,d.rec_time,d.sub_type)t;

prompt
prompt Creating view V_USER_DEPT
prompt =========================
prompt
CREATE OR REPLACE FORCE VIEW ATS_DICT.V_USER_DEPT AS
SELECT em.EMPLOYEEII,em.CODE,em.NAME as USER_NAME,em.GENDERCODE as SEX,em.BIRTHDAY as BIRTH_DAY,em.IDENTITYCARD as IDENTITY_CARD,em.DUTYCODE,em.DUTYNAME,em.EMAIL,em.PHONE,em.PYNAME as SPELLCODE,
em.MEDICALCERTIFICATENO,em.NURSEORGANIZATIONII,em.EXPERTFLAG,
dp.NAME AS DEPT_NAME,dp.TELEPHONE as dpphone,dp.CODE as dpcode
FROM ATS_DICT.dict_employees em LEFT JOIN ATS_DICT.DICT_DEPARTMENT dp ON em.ORGANIZATIONII=dp.ORGANIZATIONII;


spool off
