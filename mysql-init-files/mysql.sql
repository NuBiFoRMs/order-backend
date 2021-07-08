CREATE DATABASE order_db;

USE order_db;

create table members
(
    id       bigint auto_increment
        primary key,
    email    varchar(100) not null,
    gender   varchar(20)  null,
    nickname varchar(30)  not null,
    password varchar(500) not null,
    phone    varchar(20)  not null,
    username varchar(20)  not null,
    constraint UK_email
        unique (email),
    constraint UK_nickname
        unique (nickname)
);

create table orders
(
    id         bigint auto_increment
        primary key,
    order_date datetime(6)  not null,
    product    varchar(100) not null,
    member_id  bigint       null,
    constraint FK_member_id
        foreign key (member_id) references members (id)
);

CREATE USER `readonly`;
ALTER USER `readonly` IDENTIFIED WITH mysql_native_password BY 'readonly';
GRANT SELECT ON *.* TO `readonly`;

CREATE USER `write`;
ALTER USER `write` IDENTIFIED WITH mysql_native_password BY 'write';
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO `write`;