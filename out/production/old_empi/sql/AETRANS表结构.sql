create table AETRANS.ETRANS_ROUTE_MAP
(
  pk                     NUMBER not null,
  pay_load_type          VARCHAR2(32) not null,
  etrans_name            VARCHAR2(32),
  etrans_type            VARCHAR2(32),
  etrans_send_domain_uid VARCHAR2(64) not null,
  etrans_send_type       VARCHAR2(64) not null,
  etrans_send_name       VARCHAR2(64),
  etrans_rec_domain_uid  VARCHAR2(64) not null,
  etrans_rec_type        VARCHAR2(64) not null,
  etrans_rec_name        VARCHAR2(64),
  etrans_send_db_url     VARCHAR2(512),
  etrans_rec_db_url      VARCHAR2(512),
  isdeleted              VARCHAR2(10) default '0',
  custom1                VARCHAR2(255),
  custom2                VARCHAR2(255),
  custom3                VARCHAR2(255),
  custom4                VARCHAR2(255),
  custom5                VARCHAR2(255)
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
comment on column AETRANS.ETRANS_ROUTE_MAP.pk
  is '主键 ';
comment on column AETRANS.ETRANS_ROUTE_MAP.pay_load_type
  is '荷载类型';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_name
  is '事件名称';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_type
  is '事件类型';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_send_domain_uid
  is '发送者域';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_send_type
  is '发送者名称';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_send_name
  is '接收者域';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_rec_domain_uid
  is '接收者类型';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_rec_type
  is '接收者类型';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_rec_name
  is '接收者名称';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_send_db_url
  is '发送者通知地址';
comment on column AETRANS.ETRANS_ROUTE_MAP.etrans_rec_db_url
  is '接收者通知地址';
comment on column AETRANS.ETRANS_ROUTE_MAP.isdeleted
  is '删除标志，0-正常,1-已删除';
comment on column AETRANS.ETRANS_ROUTE_MAP.custom1
  is '备用1';
comment on column AETRANS.ETRANS_ROUTE_MAP.custom2
  is '备用2';
comment on column AETRANS.ETRANS_ROUTE_MAP.custom3
  is '备用3';
comment on column AETRANS.ETRANS_ROUTE_MAP.custom4
  is '备用4';
comment on column AETRANS.ETRANS_ROUTE_MAP.custom5
  is '备用5';
alter table AETRANS.ETRANS_ROUTE_MAP
  add constraint ETRANS_ROUTE_MAP_PK primary key (PK)
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





create table AETRANS.TBL_AETRANS_MSG_ROUTING
(
  msgid                VARCHAR2(128) not null,
  uuid                 VARCHAR2(255) not null,
  msgfrom              VARCHAR2(128) not null,
  msgto                VARCHAR2(128) not null,
  msgtype              VARCHAR2(32) not null,
  payloadtype          VARCHAR2(32) not null,
  subtype              VARCHAR2(32) not null,
  msg                  CLOB not null,
  status               NUMBER(38) not null,
  retry                NUMBER(38) not null,
  retrytime            TIMESTAMP(6) not null,
  patient_id           VARCHAR2(64),
  visit_flow_id        VARCHAR2(64),
  visit_flow_domain_id VARCHAR2(64),
  patient_domain_id    VARCHAR2(64),
  organization_id      VARCHAR2(64),
  hospital_domain_id   VARCHAR2(64),
  start_time           TIMESTAMP(6),
  end_time             TIMESTAMP(6),
  custom1              VARCHAR2(128),
  custom2              VARCHAR2(128),
  custom3              VARCHAR2(128),
  custom4              VARCHAR2(128),
  custom5              VARCHAR2(128),
  recordcreatetime     TIMESTAMP(6) not null,
  recordupdatetime     TIMESTAMP(6) not null,
  rec_msg              CLOB
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 25M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AETRANS.TBL_AETRANS_MSG_ROUTING
  add constraint TBL_AETRANS_MSG_ROUTING_PK primary key (MSGID, MSGFROM)
  using index 
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 2M
    next 1M
    minextents 1
    maxextents unlimited
  );





create table AETRANS.TBL_AETRANS_MSG_TRANS
(
  msgid                VARCHAR2(128) default ' ' not null,
  uuid                 VARCHAR2(255) default ' ' not null,
  msgfrom              VARCHAR2(128) default ' ' not null,
  msgtype              VARCHAR2(32) default ' ' not null,
  payloadtype          VARCHAR2(32) default ' ' not null,
  subtype              VARCHAR2(32) default ' ' not null,
  status               NUMBER(38) not null,
  retry                NUMBER(38) not null,
  retrytime            TIMESTAMP(6) default current_timestamp not null,
  patient_id           VARCHAR2(64) default ' ',
  visit_flow_id        VARCHAR2(64) default ' ',
  visit_flow_domain_id VARCHAR2(64) default ' ',
  patient_domain_id    VARCHAR2(64) default ' ',
  organization_id      VARCHAR2(64) default ' ',
  hospital_domain_id   VARCHAR2(64) default ' ',
  start_time           TIMESTAMP(6) default current_timestamp,
  end_time             TIMESTAMP(6) default current_timestamp,
  custom1              VARCHAR2(128) default ' ',
  custom2              VARCHAR2(128) default ' ',
  custom3              VARCHAR2(128) default ' ',
  custom4              VARCHAR2(128) default ' ',
  custom5              VARCHAR2(128) default ' ',
  recordcreatetime     TIMESTAMP(6) default current_timestamp not null,
  recordupdatetime     TIMESTAMP(6) default current_timestamp not null
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 80M
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AETRANS.TBL_AETRANS_MSG_TRANS
  add constraint RECEIVE_INF_PK primary key (MSGID)
  using index 
  tablespace users
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 20M
    next 1M
    minextents 1
    maxextents unlimited
  );





create table AETRANS.TRANS_PATIENT_VISIT
(
  uuid                         VARCHAR2(64) not null,
  patient_id                   VARCHAR2(64) not null,
  visit_flow_id                VARCHAR2(64) not null,
  name                         VARCHAR2(64),
  date_of_birth                TIMESTAMP(6),
  birth_place                  VARCHAR2(255),
  ssn                          VARCHAR2(64),
  identity_no                  VARCHAR2(64),
  insurance_no                 VARCHAR2(64),
  gender_cd                    VARCHAR2(32),
  marital_status               VARCHAR2(32),
  home_address                 VARCHAR2(256),
  work_address                 VARCHAR2(256),
  home_phone                   VARCHAR2(64),
  work_phone                   VARCHAR2(64),
  hospital_domain_id           VARCHAR2(256),
  hospital_domain_name         VARCHAR2(256),
  identifier_domain_name       VARCHAR2(255),
  identifier_domain_id         VARCHAR2(255),
  identifier_domain_type       VARCHAR2(255),
  identifier_flow_domain_name  VARCHAR2(255),
  identifier_flow_domain_id    VARCHAR2(255),
  identifier_flow_domain_type  VARCHAR2(255),
  pat_category                 VARCHAR2(16),
  pat_current_point_of_care    VARCHAR2(256),
  pat_current_room             VARCHAR2(256),
  pat_current_bed              VARCHAR2(256),
  pat_cuurent_dep              VARCHAR2(256),
  pat_current_dep_name         VARCHAR2(256),
  pat_current_position_status  VARCHAR2(256),
  pat_current_position_type    VARCHAR2(32),
  pat_current_building         VARCHAR2(64),
  pat_current_floor            VARCHAR2(64),
  pat_cuurent_description      VARCHAR2(256),
  pat_admission_type           VARCHAR2(16),
  pat_admission_number         VARCHAR2(128),
  admissions_doctor            VARCHAR2(256),
  admissions_doctor_id         VARCHAR2(32),
  referring_doctor             VARCHAR2(256),
  referring_doctor_id          VARCHAR2(32),
  consultation_doctor          VARCHAR2(256),
  consultation_doctor_id       VARCHAR2(32),
  hospital_service             VARCHAR2(32),
  pat_admission_test           VARCHAR2(256),
  pat_re_admission             VARCHAR2(32),
  pat_admission_source         VARCHAR2(256),
  pat_ambulatory_status        VARCHAR2(256),
  pat_vip                      VARCHAR2(32),
  pat_admission_doctors        VARCHAR2(256),
  pat_admission_doctors_id     VARCHAR2(32),
  patient_class                VARCHAR2(64),
  patient_flow_id              VARCHAR2(64),
  pat_discharge_disposition    VARCHAR2(32),
  pat_discharge_location       VARCHAR2(256),
  pat_diet_type                VARCHAR2(64),
  pat_service_agencies         VARCHAR2(64),
  pat_bed_status               VARCHAR2(32),
  pat_account_status           VARCHAR2(32),
  pat_nurse_code               VARCHAR2(32),
  pat_nurse_name               VARCHAR2(32),
  tend                         VARCHAR2(32),
  pat_dietetic_mark            VARCHAR2(32),
  pat_iptimes                  VARCHAR2(255),
  pat_discharge_code           VARCHAR2(255),
  pat_deter_point_of_care      VARCHAR2(255),
  pat_deter_room               VARCHAR2(255),
  pat_deter_bed                VARCHAR2(255),
  pat_deter_dep                VARCHAR2(255),
  pat_deter_position_status    VARCHAR2(255),
  pat_deter_position_type      VARCHAR2(255),
  pat_deter_building           VARCHAR2(255),
  pat_deter_floor              VARCHAR2(255),
  pat_deter_description        VARCHAR2(255),
  pat_ipstatuscode             VARCHAR2(255),
  pat_difficulty_levelcode     VARCHAR2(255),
  baby_flag                    VARCHAR2(255),
  admit_weight                 VARCHAR2(255),
  birth_weight                 VARCHAR2(255),
  pat_former_point_of_care     VARCHAR2(256),
  pat_former_room              VARCHAR2(256),
  pat_former_bed               VARCHAR2(256),
  pat_former_dep               VARCHAR2(256),
  pat_former_position_status   VARCHAR2(256),
  pat_former_position_type     VARCHAR2(32),
  pat_former_building          VARCHAR2(64),
  pat_former_floor             VARCHAR2(64),
  pat_former_description       VARCHAR2(256),
  pat_temp_point_of_care       VARCHAR2(256),
  pat_temp_room                VARCHAR2(256),
  pat_temp_bed                 VARCHAR2(256),
  pat_temp_dep                 VARCHAR2(256),
  pat_temp_position_status     VARCHAR2(256),
  pat_temp_position_type       VARCHAR2(32),
  pat_temp_building            VARCHAR2(64),
  pat_temp_floor               VARCHAR2(64),
  pat_temp_description         VARCHAR2(256),
  pat_for_temp_point_of_care   VARCHAR2(256),
  pat_for_temp_room            VARCHAR2(256),
  pat_for_temp_bed             VARCHAR2(256),
  pat_for_temp_dep             VARCHAR2(256),
  pat_for_temp_position_status VARCHAR2(256),
  pat_for_temp_position_type   VARCHAR2(32),
  pat_for_temp_building        VARCHAR2(64),
  pat_for_temp_floor           VARCHAR2(64),
  pat_for_temp_description     VARCHAR2(256),
  oper_code                    VARCHAR2(32),
  oper_date                    TIMESTAMP(6),
  admit_date                   TIMESTAMP(6),
  discharge_date               TIMESTAMP(6),
  reg_date                     TIMESTAMP(6),
  opr_date                     TIMESTAMP(6),
  create_date                  TIMESTAMP(6),
  create_id                    VARCHAR2(32),
  voided_date                  TIMESTAMP(6),
  voided_id                    VARCHAR2(32),
  modify_date                  TIMESTAMP(6),
  modify_id                    VARCHAR2(32),
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
  patient_visit_status         VARCHAR2(64),
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
  hiup_status                  VARCHAR2(16),
  hiup_error_info              VARCHAR2(4000),
  mothers_id                   VARCHAR2(64),
  mothers_flow_id              VARCHAR2(64),
  mothers_flow_domain          VARCHAR2(64),
  mothers_name                 VARCHAR2(64),
  mothers_domain               VARCHAR2(64),
  pat_category_system          VARCHAR2(64),
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
  diagnose_icd                 VARCHAR2(64),
  diagnose_name                VARCHAR2(64),
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
  in_patient_status_c          VARCHAR2(16)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 87M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table AETRANS.TRANS_PATIENT_VISIT
  is '病人流水表';
comment on column AETRANS.TRANS_PATIENT_VISIT.patient_id
  is '病人ID';
comment on column AETRANS.TRANS_PATIENT_VISIT.visit_flow_id
  is '病人就诊流水号';
comment on column AETRANS.TRANS_PATIENT_VISIT.name
  is '姓名';
comment on column AETRANS.TRANS_PATIENT_VISIT.date_of_birth
  is '生日';
comment on column AETRANS.TRANS_PATIENT_VISIT.birth_place
  is '出生地';
comment on column AETRANS.TRANS_PATIENT_VISIT.ssn
  is '社会保险号';
comment on column AETRANS.TRANS_PATIENT_VISIT.identity_no
  is '身份证件号';
comment on column AETRANS.TRANS_PATIENT_VISIT.insurance_no
  is '医疗保险号';
comment on column AETRANS.TRANS_PATIENT_VISIT.gender_cd
  is '性别';
comment on column AETRANS.TRANS_PATIENT_VISIT.marital_status
  is '婚姻';
comment on column AETRANS.TRANS_PATIENT_VISIT.home_address
  is '家庭地址';
comment on column AETRANS.TRANS_PATIENT_VISIT.work_address
  is '工作地址';
comment on column AETRANS.TRANS_PATIENT_VISIT.home_phone
  is '家庭电话';
comment on column AETRANS.TRANS_PATIENT_VISIT.work_phone
  is '工作电话';
comment on column AETRANS.TRANS_PATIENT_VISIT.hospital_domain_id
  is '医院域ID';
comment on column AETRANS.TRANS_PATIENT_VISIT.hospital_domain_name
  is '医院名';
comment on column AETRANS.TRANS_PATIENT_VISIT.identifier_domain_name
  is '病人所属机构名';
comment on column AETRANS.TRANS_PATIENT_VISIT.identifier_domain_id
  is '病人所属机构域ID';
comment on column AETRANS.TRANS_PATIENT_VISIT.identifier_domain_type
  is '病人所属机构类型';
comment on column AETRANS.TRANS_PATIENT_VISIT.identifier_flow_domain_name
  is '病人所属流水机构名';
comment on column AETRANS.TRANS_PATIENT_VISIT.identifier_flow_domain_id
  is '病人所属流水机构ID';
comment on column AETRANS.TRANS_PATIENT_VISIT.identifier_flow_domain_type
  is '病人所属流水机构类型';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_category
  is '患者类别，门诊，急诊，LIS，RIS';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_current_room
  is '入院指定病区';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_current_bed
  is '入院指定病床';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_cuurent_dep
  is '入院指定科室';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_current_dep_name
  is '入院指定科室名';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_current_building
  is '入院指定楼';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_current_floor
  is '入院指定楼层';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_admission_type
  is '入院时情况类型编码';
comment on column AETRANS.TRANS_PATIENT_VISIT.admissions_doctor
  is '住院医生';
comment on column AETRANS.TRANS_PATIENT_VISIT.admissions_doctor_id
  is '住院医生代码';
comment on column AETRANS.TRANS_PATIENT_VISIT.referring_doctor
  is '主治医生';
comment on column AETRANS.TRANS_PATIENT_VISIT.referring_doctor_id
  is '主治医生代码';
comment on column AETRANS.TRANS_PATIENT_VISIT.consultation_doctor
  is '主任医生';
comment on column AETRANS.TRANS_PATIENT_VISIT.consultation_doctor_id
  is '主任医生代码';
comment on column AETRANS.TRANS_PATIENT_VISIT.hospital_service
  is '医院服务';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_re_admission
  is '再次入院标识';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_admission_source
  is '入院来源，入院途径，如急诊，门诊';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_ambulatory_status
  is '手术后状态';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_vip
  is '保密与否0未保密1保密';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_admission_doctors
  is '门诊接诊医生姓名';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_admission_doctors_id
  is '门诊接诊医生ID';
comment on column AETRANS.TRANS_PATIENT_VISIT.patient_class
  is '患者类型,如自费、省公医';
comment on column AETRANS.TRANS_PATIENT_VISIT.patient_flow_id
  is '病人流水ID';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_bed_status
  is '床位状态标志';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_account_status
  is '结算类别';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_nurse_code
  is '护理护士编码';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_nurse_name
  is '护理护士名';
comment on column AETRANS.TRANS_PATIENT_VISIT.tend
  is '护理级别';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_iptimes
  is '住院次数';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_discharge_code
  is '出院处置编码';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_deter_dep
  is '责任科室';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_ipstatuscode
  is '病人住院状态编码';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_difficulty_levelcode
  is '病例分型编码';
comment on column AETRANS.TRANS_PATIENT_VISIT.baby_flag
  is '婴儿标志';
comment on column AETRANS.TRANS_PATIENT_VISIT.admit_weight
  is '入院体重';
comment on column AETRANS.TRANS_PATIENT_VISIT.birth_weight
  is '出生体重';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_former_room
  is '病人前病区';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_former_bed
  is '病人前床位';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_former_dep
  is '病人前科室';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_former_building
  is '病人前楼';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_former_floor
  is '病人前楼层';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_temp_room
  is '病人临时病区';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_temp_bed
  is '病人临时床位';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_temp_dep
  is '病人临时科室';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_temp_building
  is '病人临时楼';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_temp_floor
  is '病人临时楼层';
comment on column AETRANS.TRANS_PATIENT_VISIT.oper_code
  is '操作员';
comment on column AETRANS.TRANS_PATIENT_VISIT.oper_date
  is '操作日期';
comment on column AETRANS.TRANS_PATIENT_VISIT.admit_date
  is '入院时间';
comment on column AETRANS.TRANS_PATIENT_VISIT.discharge_date
  is '出院时间';
comment on column AETRANS.TRANS_PATIENT_VISIT.create_date
  is '创建日期';
comment on column AETRANS.TRANS_PATIENT_VISIT.create_id
  is '创建人';
comment on column AETRANS.TRANS_PATIENT_VISIT.voided_date
  is '废弃日期';
comment on column AETRANS.TRANS_PATIENT_VISIT.voided_id
  is '废弃者';
comment on column AETRANS.TRANS_PATIENT_VISIT.modify_date
  is '修改日期';
comment on column AETRANS.TRANS_PATIENT_VISIT.modify_id
  is '修改者';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom1
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom2
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom3
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom4
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom5
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom6
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom7
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom8
  is '特殊字段';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom9
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom10
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom11
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom12
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom13
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom14
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom15
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom16
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom17
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom18
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom19
  is '扩展字段，未用';
comment on column AETRANS.TRANS_PATIENT_VISIT.custom20
  is '出错记录位';
comment on column AETRANS.TRANS_PATIENT_VISIT.patient_visit_status
  is '病人就诊控制字段';
comment on column AETRANS.TRANS_PATIENT_VISIT.prefix
  is '前缀';
comment on column AETRANS.TRANS_PATIENT_VISIT.insurance_type
  is '医保类型';
comment on column AETRANS.TRANS_PATIENT_VISIT.contact_person
  is '联系人';
comment on column AETRANS.TRANS_PATIENT_VISIT.contact_relations
  is '关系';
comment on column AETRANS.TRANS_PATIENT_VISIT.contact_address
  is '联系人地址';
comment on column AETRANS.TRANS_PATIENT_VISIT.contact_phone
  is '联系人电话';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_category_name
  is '患者类别名';
comment on column AETRANS.TRANS_PATIENT_VISIT.gender_name
  is '性别名';
comment on column AETRANS.TRANS_PATIENT_VISIT.pay_rate
  is '支付比例';
comment on column AETRANS.TRANS_PATIENT_VISIT.discharge_name
  is '离院处置名';
comment on column AETRANS.TRANS_PATIENT_VISIT.insurance_name
  is '医保名';
comment on column AETRANS.TRANS_PATIENT_VISIT.admission_name
  is '入院时情况名';
comment on column AETRANS.TRANS_PATIENT_VISIT.ip_status_name
  is '病人住院状态名';
comment on column AETRANS.TRANS_PATIENT_VISIT.dificulty_name
  is '病历分型名';
comment on column AETRANS.TRANS_PATIENT_VISIT.admit_way_name
  is '入院途径名';
comment on column AETRANS.TRANS_PATIENT_VISIT.admit_weight_unit
  is '入院体重单位';
comment on column AETRANS.TRANS_PATIENT_VISIT.baby_weight_unit
  is '出生体重单位';
comment on column AETRANS.TRANS_PATIENT_VISIT.hiup_status
  is '记录控制状态字';
comment on column AETRANS.TRANS_PATIENT_VISIT.hiup_error_info
  is '记录控制状态描述';
comment on column AETRANS.TRANS_PATIENT_VISIT.pat_category_system
  is '病人类型域';
alter table AETRANS.TRANS_PATIENT_VISIT
  add constraint TRAN_VISIT_PK primary key (UUID)
  using index 
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





create table AETRANS.TRANS_PERSON
(
  uuid                   VARCHAR2(64) not null,
  name                   VARCHAR2(64),
  date_of_birth          TIMESTAMP(6),
  birth_place            VARCHAR2(255),
  multiple_birth_ind     VARCHAR2(1),
  birth_order            NUMBER,
  mothers_maiden_name    VARCHAR2(64),
  ssn                    VARCHAR2(64),
  identity_no            VARCHAR2(64),
  insurance_no           VARCHAR2(64),
  insurance_type         VARCHAR2(64),
  insurance_name         VARCHAR2(64),
  gender_cd              VARCHAR2(32),
  ethnic_group_cd        VARCHAR2(32),
  race_cd                VARCHAR2(32),
  nationality_cd         VARCHAR2(32),
  language_cd            VARCHAR2(32),
  religion_cd            VARCHAR2(32),
  marital_status_cd      VARCHAR2(32),
  degree                 VARCHAR2(32),
  email                  VARCHAR2(255),
  home_address           VARCHAR2(64),
  work_address           VARCHAR2(64),
  city                   VARCHAR2(64),
  state                  VARCHAR2(64),
  country                VARCHAR2(64),
  country_code           VARCHAR2(64),
  home_number            VARCHAR2(64),
  work_number            VARCHAR2(64),
  death_ind              VARCHAR2(1),
  death_time             TIMESTAMP(6),
  date_created           TIMESTAMP(6) not null,
  creator_id             NUMBER,
  date_changed           TIMESTAMP(6),
  changed_by_id          NUMBER,
  date_voided            TIMESTAMP(6),
  voided_by_id           NUMBER,
  hospital_domain_id     VARCHAR2(255),
  identifier_domain_name VARCHAR2(255),
  identifier_domain_id   VARCHAR2(255) not null,
  identifier_domain_type VARCHAR2(255),
  identifier_id          VARCHAR2(255) not null,
  custom1                VARCHAR2(255),
  custom2                VARCHAR2(255),
  custom3                VARCHAR2(255),
  custom4                VARCHAR2(255),
  custom5                VARCHAR2(255),
  custom6                VARCHAR2(255),
  custom7                VARCHAR2(255),
  custom8                VARCHAR2(255),
  custom9                VARCHAR2(255),
  custom10               VARCHAR2(255),
  custom11               VARCHAR2(255),
  custom12               VARCHAR2(255),
  custom13               VARCHAR2(255),
  custom14               VARCHAR2(255),
  custom15               VARCHAR2(255),
  custom16               VARCHAR2(255),
  custom17               VARCHAR2(255),
  custom18               VARCHAR2(255),
  custom19               VARCHAR2(255),
  custom20               VARCHAR2(255),
  person_status          VARCHAR2(64),
  merge_patient_id       VARCHAR2(64),
  merge_person_domain    VARCHAR2(64),
  registered_province    VARCHAR2(64),
  registered_city        VARCHAR2(64),
  registered_county      VARCHAR2(64),
  registered_address     VARCHAR2(512),
  registered_zip         VARCHAR2(32),
  home_province          VARCHAR2(64),
  home_city              VARCHAR2(64),
  home_county            VARCHAR2(64),
  home_zip               VARCHAR2(32),
  private_number         VARCHAR2(64),
  citizen_card           VARCHAR2(64),
  medical_certificate    VARCHAR2(64),
  medicare_person        VARCHAR2(64),
  elder_certificate      VARCHAR2(64),
  opcaseno               VARCHAR2(32),
  company                VARCHAR2(128),
  work_zip               VARCHAR2(32),
  guardian_person        VARCHAR2(64),
  birth_province         VARCHAR2(64),
  birth_city             VARCHAR2(64),
  birth_county           VARCHAR2(64),
  birth_zip              VARCHAR2(32),
  profession             VARCHAR2(64),
  native_province        VARCHAR2(64),
  native_city            VARCHAR2(64),
  vip                    NUMBER,
  name_spell_code        VARCHAR2(64),
  name_wb_code           VARCHAR2(64),
  blood_code             VARCHAR2(64),
  gender_name            VARCHAR2(64),
  marital_status_name    VARCHAR2(64),
  degree_name            VARCHAR2(64),
  profession_name        VARCHAR2(64),
  nationality_name       VARCHAR2(64),
  ethnic_name            VARCHAR2(64),
  race_name              VARCHAR2(64),
  gender_domain          VARCHAR2(64),
  ethnic_domain          VARCHAR2(64),
  race_domain            VARCHAR2(64),
  nationality_domain     VARCHAR2(64),
  marital_domain         VARCHAR2(64),
  degree_domain          VARCHAR2(64),
  profession_domain      VARCHAR2(64),
  hl7_msg                CLOB,
  hiup_status            VARCHAR2(16) default 0,
  hiup_error_info        VARCHAR2(4000),
  relevance_domain       VARCHAR2(64),
  relevance_id           VARCHAR2(64),
  health_card            VARCHAR2(64),
  account_locked         VARCHAR2(64),
  account_locked_date    TIMESTAMP(6),
  birth_time             TIMESTAMP(6),
  card_type              VARCHAR2(16)
)
tablespace users
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 382M
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table AETRANS.TRANS_PERSON
  is '病人基本信息主表';
comment on column AETRANS.TRANS_PERSON.name
  is '中文姓名';
comment on column AETRANS.TRANS_PERSON.date_of_birth
  is '出生日期';
comment on column AETRANS.TRANS_PERSON.birth_place
  is '出生地';
comment on column AETRANS.TRANS_PERSON.multiple_birth_ind
  is '多胞胎';
comment on column AETRANS.TRANS_PERSON.birth_order
  is '出生次序';
comment on column AETRANS.TRANS_PERSON.mothers_maiden_name
  is '母亲娘家姓';
comment on column AETRANS.TRANS_PERSON.ssn
  is '社会保险号';
comment on column AETRANS.TRANS_PERSON.identity_no
  is '身份证号';
comment on column AETRANS.TRANS_PERSON.insurance_no
  is '医疗保险号';
comment on column AETRANS.TRANS_PERSON.insurance_type
  is '医疗保险类型';
comment on column AETRANS.TRANS_PERSON.insurance_name
  is '医保名称';
comment on column AETRANS.TRANS_PERSON.gender_cd
  is '性别';
comment on column AETRANS.TRANS_PERSON.ethnic_group_cd
  is '民族';
comment on column AETRANS.TRANS_PERSON.race_cd
  is '种族';
comment on column AETRANS.TRANS_PERSON.nationality_cd
  is '国籍';
comment on column AETRANS.TRANS_PERSON.language_cd
  is '语言';
comment on column AETRANS.TRANS_PERSON.religion_cd
  is '宗教';
comment on column AETRANS.TRANS_PERSON.marital_status_cd
  is '婚姻状态';
comment on column AETRANS.TRANS_PERSON.degree
  is '学位';
comment on column AETRANS.TRANS_PERSON.email
  is '电子邮件';
comment on column AETRANS.TRANS_PERSON.home_address
  is '家庭地址';
comment on column AETRANS.TRANS_PERSON.work_address
  is '单位地址';
comment on column AETRANS.TRANS_PERSON.city
  is '城市';
comment on column AETRANS.TRANS_PERSON.state
  is '洲';
comment on column AETRANS.TRANS_PERSON.country
  is '国家';
comment on column AETRANS.TRANS_PERSON.country_code
  is '国家编码';
comment on column AETRANS.TRANS_PERSON.home_number
  is '家庭电话';
comment on column AETRANS.TRANS_PERSON.work_number
  is '工作电话';
comment on column AETRANS.TRANS_PERSON.death_ind
  is '死亡标志';
comment on column AETRANS.TRANS_PERSON.death_time
  is '死亡时间';
comment on column AETRANS.TRANS_PERSON.date_created
  is '创建日期';
comment on column AETRANS.TRANS_PERSON.creator_id
  is '创建人ID';
comment on column AETRANS.TRANS_PERSON.date_changed
  is '更改日期';
comment on column AETRANS.TRANS_PERSON.changed_by_id
  is '更改人ID';
comment on column AETRANS.TRANS_PERSON.date_voided
  is '废弃日期';
comment on column AETRANS.TRANS_PERSON.voided_by_id
  is '废弃人ID';
comment on column AETRANS.TRANS_PERSON.hospital_domain_id
  is '医院ID';
comment on column AETRANS.TRANS_PERSON.identifier_domain_name
  is '机构名称';
comment on column AETRANS.TRANS_PERSON.identifier_domain_id
  is '机构域ID';
comment on column AETRANS.TRANS_PERSON.identifier_domain_type
  is '机构类型';
comment on column AETRANS.TRANS_PERSON.identifier_id
  is '机构内病人ID';
comment on column AETRANS.TRANS_PERSON.custom1
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom2
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom3
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom4
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom5
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom6
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom7
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom8
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom9
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom10
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom11
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom12
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom13
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom14
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom15
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom16
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom17
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom18
  is '特殊标志位，占用';
comment on column AETRANS.TRANS_PERSON.custom19
  is '扩展字段，未使用';
comment on column AETRANS.TRANS_PERSON.custom20
  is '扩展字段，标记上传信息出错或者AE出错状态';
comment on column AETRANS.TRANS_PERSON.person_status
  is '人控制状态';
comment on column AETRANS.TRANS_PERSON.merge_patient_id
  is '合并人ID';
comment on column AETRANS.TRANS_PERSON.merge_person_domain
  is '合并人域ID';
comment on column AETRANS.TRANS_PERSON.registered_province
  is '户口所在地省';
comment on column AETRANS.TRANS_PERSON.registered_city
  is '户口所在地市';
comment on column AETRANS.TRANS_PERSON.registered_county
  is '户口所在地区';
comment on column AETRANS.TRANS_PERSON.registered_address
  is '户口地址';
comment on column AETRANS.TRANS_PERSON.registered_zip
  is '户口邮编';
comment on column AETRANS.TRANS_PERSON.home_province
  is '居住所在地省';
comment on column AETRANS.TRANS_PERSON.home_city
  is '居住地所在地市';
comment on column AETRANS.TRANS_PERSON.home_county
  is '居住所在地区';
comment on column AETRANS.TRANS_PERSON.home_zip
  is '居住所在地邮编';
comment on column AETRANS.TRANS_PERSON.private_number
  is '私人电话';
comment on column AETRANS.TRANS_PERSON.citizen_card
  is '市民卡号';
comment on column AETRANS.TRANS_PERSON.medical_certificate
  is '医疗证号';
comment on column AETRANS.TRANS_PERSON.medicare_person
  is '医保个人编号';
comment on column AETRANS.TRANS_PERSON.elder_certificate
  is '老人证号';
comment on column AETRANS.TRANS_PERSON.opcaseno
  is '病历号';
comment on column AETRANS.TRANS_PERSON.company
  is '工作单位';
comment on column AETRANS.TRANS_PERSON.work_zip
  is '工作邮编';
comment on column AETRANS.TRANS_PERSON.guardian_person
  is '监护人';
comment on column AETRANS.TRANS_PERSON.birth_province
  is '出生地所在地的省';
comment on column AETRANS.TRANS_PERSON.birth_city
  is '出生地所在地的市';
comment on column AETRANS.TRANS_PERSON.birth_county
  is '出生地所在区县';
comment on column AETRANS.TRANS_PERSON.birth_zip
  is '出生地所在邮编';
comment on column AETRANS.TRANS_PERSON.profession
  is '职业';
comment on column AETRANS.TRANS_PERSON.native_province
  is '籍贯所在地的省';
comment on column AETRANS.TRANS_PERSON.native_city
  is '籍贯所在地的市';
comment on column AETRANS.TRANS_PERSON.vip
  is '保密级别，0表示保密，大于0皆不保密';
comment on column AETRANS.TRANS_PERSON.name_spell_code
  is '姓名拼音码';
comment on column AETRANS.TRANS_PERSON.name_wb_code
  is '姓名五笔码';
comment on column AETRANS.TRANS_PERSON.blood_code
  is '血型';
comment on column AETRANS.TRANS_PERSON.gender_name
  is '性别名称';
comment on column AETRANS.TRANS_PERSON.marital_status_name
  is '婚姻名称';
comment on column AETRANS.TRANS_PERSON.degree_name
  is '教育程度';
comment on column AETRANS.TRANS_PERSON.profession_name
  is '职业名称';
comment on column AETRANS.TRANS_PERSON.nationality_name
  is '国籍名';
comment on column AETRANS.TRANS_PERSON.ethnic_name
  is '民族名称';
comment on column AETRANS.TRANS_PERSON.race_name
  is '种族名称';
comment on column AETRANS.TRANS_PERSON.gender_domain
  is '性别编码系统';
comment on column AETRANS.TRANS_PERSON.ethnic_domain
  is '民族编码系统';
comment on column AETRANS.TRANS_PERSON.race_domain
  is '种族编码系统';
comment on column AETRANS.TRANS_PERSON.nationality_domain
  is '国籍编码系统';
comment on column AETRANS.TRANS_PERSON.marital_domain
  is '婚姻编码系统';
comment on column AETRANS.TRANS_PERSON.degree_domain
  is '教育程度编码系统';
comment on column AETRANS.TRANS_PERSON.profession_domain
  is '职业编码系统';
comment on column AETRANS.TRANS_PERSON.hiup_status
  is '记录控制状态字';
comment on column AETRANS.TRANS_PERSON.hiup_error_info
  is '记录控制信息';
comment on column AETRANS.TRANS_PERSON.health_card
  is '健康卡号';
create index AETRANS.DATE_IDX on AETRANS.TRANS_PERSON (DATE_CREATED)
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
create index AETRANS.HIUPSTATUS_IDX on AETRANS.TRANS_PERSON (HIUP_STATUS)
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
create index AETRANS.IDENTIFIERID_IDX on AETRANS.TRANS_PERSON (IDENTIFIER_ID)
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
alter table AETRANS.TRANS_PERSON
  add constraint GATE_PERSON_PK primary key (UUID)
  using index 
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





create sequence AETRANS.SEQ_MSG_ROUT_PK
minvalue 1
maxvalue 9223372036854775807
start with 4657482
increment by 1
cache 20;





create sequence AETRANS.SEQ_TRANS_PK
minvalue 1
maxvalue 999999999999999999999999999
start with 5655026
increment by 1
cache 20;





create sequence AETRANS.SQL_MSG_ROUT_PK
minvalue 1
maxvalue 9223372036854775807
start with 81
increment by 1
cache 20;

