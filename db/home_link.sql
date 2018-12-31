/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/31 18:06:52                          */
/*==============================================================*/


drop table if exists air_sensor_record;

drop table if exists app_user;

drop table if exists home;

drop table if exists home_user;

drop table if exists smart_component;

drop table if exists smart_component_type;

/*==============================================================*/
/* Table: air_sensor_record                                     */
/*==============================================================*/
create table air_sensor_record
(
   id                   bigint not null auto_increment comment 'ID',
   c_id                 bigint comment '组件类型',
   create_time          datetime comment '创建时间',
   home_id              bigint comment '所属家',
   temp                 decimal(20,2),
   humidity             decimal(20,3),
   altitude             decimal(20,2),
   qnh                  decimal(20,3),
   primary key (id)
);

/*==============================================================*/
/* Table: app_user                                              */
/*==============================================================*/
create table app_user
(
   id                   bigint not null auto_increment comment 'ID',
   open_id              varchar(200) comment '微信openID',
   union_id             varchar(200) comment '微信UNIONID只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
   mobile               varchar(50) comment '手机号',
   name                 varchar(50) comment '姓名',
   status               varchar(5) default '0' comment '0-正常 1-禁用',
   create_time          datetime comment '创建时间',
   password             varchar(50) comment '密码',
   photo                varchar(200) comment '图像',
   city                 varchar(50) comment '所属城市',
   gender               varchar(5) default '0' comment '性别0未知 1-男 2-女（后面可有身份证号自动关联出生年月）',
   birthday             datetime comment '出生日期（后面可有身份证号自动关联出生年月）',
   is_follow            varchar(5) default '0' comment '是否关注微信公众号0-没有 1-已关注 2-关注老师（第三方）',
   real_name            varchar(200) comment '真实姓名',
   primary key (id)
);

/*==============================================================*/
/* Table: home                                                  */
/*==============================================================*/
create table home
(
   id                   bigint not null auto_increment comment 'ID',
   home_name            varchar(200) comment '家名',
   belong_user_id       bigint comment '所属者',
   status               varchar(5) default '0' comment '0-正常 1-禁用',
   create_time          datetime comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: home_user                                             */
/*==============================================================*/
create table home_user
(
   id                   bigint not null auto_increment comment 'ID',
   home_id              bigint comment '家庭',
   user_id              bigint comment '成员ID',
   primary key (id)
);

/*==============================================================*/
/* Table: smart_component                                       */
/*==============================================================*/
create table smart_component
(
   id                   bigint not null auto_increment comment 'ID',
   type_id              bigint comment '组件类型',
   name                 varchar(200) comment '名称',
   status               varchar(5) default '0' comment '0-正常 1-禁用',
   create_time          datetime comment '创建时间',
   is_controller_center int default 0 comment '是否是控制中心0-不是 1-是',
   remark               varchar(200),
   create_user_id       bigint comment '创建者',
   home_id              bigint comment '所属家',
   ic_name              varchar(200) comment '芯片名称',
   firmware_version     varchar(200) comment '固件版本',
   primary key (id)
);

/*==============================================================*/
/* Table: smart_component_type                                  */
/*==============================================================*/
create table smart_component_type
(
   id                   bigint not null auto_increment comment 'ID',
   type_name            varchar(200) comment '类型名称',
   status               varchar(5) default '0' comment '0-正常 1-禁用',
   create_time          datetime comment '创建时间',
   is_controller_center int default 0 comment '是否是控制中心0-不是 1-是',
   remark               varchar(200),
   primary key (id)
);

alter table air_sensor_record add constraint FK_Reference_10 foreign key (home_id)
      references home (id) on delete restrict on update restrict;

alter table air_sensor_record add constraint FK_Reference_9 foreign key (c_id)
      references smart_component (id) on delete restrict on update restrict;

alter table home add constraint FK_Reference_3 foreign key (belong_user_id)
      references app_user (id) on delete restrict on update restrict;

alter table home_user add constraint FK_Reference_6 foreign key (user_id)
      references app_user (id) on delete restrict on update restrict;

alter table home_user add constraint FK_Reference_7 foreign key (home_id)
      references home (id) on delete restrict on update restrict;

alter table smart_component add constraint FK_Reference_4 foreign key (home_id)
      references home (id) on delete restrict on update restrict;

alter table smart_component add constraint FK_Reference_5 foreign key (type_id)
      references smart_component_type (id) on delete restrict on update restrict;

alter table smart_component add constraint FK_Reference_8 foreign key (create_user_id)
      references app_user (id) on delete restrict on update restrict;

