drop database if exists morningstar_kill;
create database if not exists morningstar_kill;

use morningstar_kill;

-- 构建用户表
create table if not exists user (
    id binary(16), # 通过`UUID_TO_BIN(UUID())`生成
    username varchar(32) unique not null,
    password char(60) not null, # Bcrypt需要60字节存储
    phone char(11),
    email varchar(64),
    nickname varchar(32),
    sex boolean,
    create_time datetime,
    update_time datetime,
    PRIMARY KEY(id)
);
insert into user (id, username, password, phone, sex, create_time, update_time)
values (UUID_TO_BIN('24acdcc0-5269-11ef-a8c2-0242ac120006'), 'henry', '$2a$10$bI8Iyuq9qDtIa3V2lnE70eUii0CsApY0mu6l6v4FyoaoCZBDnz7Oi', '19850052801', false, DATE_SUB(now(), INTERVAL 1 day), now());


-- 构建记录表
create table if not exists record (
    id binary(16),
    content text,
    create_time datetime,
    update_time datetime
);
insert into record (id, content, create_time, update_time) value (UUID_TO_BIN('364d362c-5269-11ef-a8c2-0242ac120006'), '这是个记录', NOW(), NOW());


-- 构建用户记录表
create table if not exists user_record (
    user_id binary(16),
    record_id binary(16),
    PRIMARY KEY (user_id, record_id)
);
insert into user_record (user_id, record_id) values (UUID_TO_BIN('24acdcc0-5269-11ef-a8c2-0242ac120006'), UUID_TO_BIN('364d362c-5269-11ef-a8c2-0242ac120006'));


# select UUID() "Next UUID";
