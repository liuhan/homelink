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
   c_id                 bigint comment '�������',
   create_time          datetime comment '����ʱ��',
   home_id              bigint comment '������',
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
   open_id              varchar(200) comment '΢��openID',
   union_id             varchar(200) comment '΢��UNIONIDֻ�����û������ںŰ󶨵�΢�ſ���ƽ̨�ʺź󣬲Ż���ָ��ֶ�',
   mobile               varchar(50) comment '�ֻ���',
   name                 varchar(50) comment '����',
   status               varchar(5) default '0' comment '0-���� 1-����',
   create_time          datetime comment '����ʱ��',
   password             varchar(50) comment '����',
   photo                varchar(200) comment 'ͼ��',
   city                 varchar(50) comment '��������',
   gender               varchar(5) default '0' comment '�Ա�0δ֪ 1-�� 2-Ů������������֤���Զ������������£�',
   birthday             datetime comment '�������ڣ�����������֤���Զ������������£�',
   is_follow            varchar(5) default '0' comment '�Ƿ��ע΢�Ź��ں�0-û�� 1-�ѹ�ע 2-��ע��ʦ����������',
   real_name            varchar(200) comment '��ʵ����',
   primary key (id)
);

/*==============================================================*/
/* Table: home                                                  */
/*==============================================================*/
create table home
(
   id                   bigint not null auto_increment comment 'ID',
   home_name            varchar(200) comment '����',
   belong_user_id       bigint comment '������',
   status               varchar(5) default '0' comment '0-���� 1-����',
   create_time          datetime comment '����ʱ��',
   primary key (id)
);

/*==============================================================*/
/* Table: home_user                                             */
/*==============================================================*/
create table home_user
(
   id                   bigint not null auto_increment comment 'ID',
   home_id              bigint comment '��ͥ',
   user_id              bigint comment '��ԱID',
   primary key (id)
);

/*==============================================================*/
/* Table: smart_component                                       */
/*==============================================================*/
create table smart_component
(
   id                   bigint not null auto_increment comment 'ID',
   type_id              bigint comment '�������',
   name                 varchar(200) comment '����',
   status               varchar(5) default '0' comment '0-���� 1-����',
   create_time          datetime comment '����ʱ��',
   is_controller_center int default 0 comment '�Ƿ��ǿ�������0-���� 1-��',
   remark               varchar(200),
   create_user_id       bigint comment '������',
   home_id              bigint comment '������',
   ic_name              varchar(200) comment 'оƬ����',
   firmware_version     varchar(200) comment '�̼��汾',
   primary key (id)
);

/*==============================================================*/
/* Table: smart_component_type                                  */
/*==============================================================*/
create table smart_component_type
(
   id                   bigint not null auto_increment comment 'ID',
   type_name            varchar(200) comment '��������',
   status               varchar(5) default '0' comment '0-���� 1-����',
   create_time          datetime comment '����ʱ��',
   is_controller_center int default 0 comment '�Ƿ��ǿ�������0-���� 1-��',
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

