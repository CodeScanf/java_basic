create database if not exists `mybatis_db`;
use `mybatis_db`;

create table if not exists `user` (
    `id` int(20) not null auto_increment,primary key (`id`),
    `name` varchar(32) not null comment '用户名',
    `age` int(3) not null comment '年龄')
    engine=innodb default charset=utf8;

insert into `user` (`name`, `age`) values ('Tom', 11);
insert into `user` (`name`, `age`) values ('Jerry', 11);