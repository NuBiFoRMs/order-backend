CREATE DATABASE order_db;

USE order_db;

create table members
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) null,
    gender   varchar(255) null,
    nickname varchar(255) null,
    password varchar(255) null,
    phone    varchar(255) null,
    username varchar(255) null,
    constraint UK_9d30a9u1qpg8eou0otgkwrp5d
        unique (email),
    constraint UK_e6u9u9ypoc7oldnpxdjwcdx3
        unique (nickname)
);

create table orders
(
    id         bigint auto_increment
        primary key,
    order_date datetime(6)  null,
    product    varchar(255) null,
    member_id  bigint       null,
    constraint FK2vq7lo4gkknrmghj3rqpqqg6s
        foreign key (member_id) references members (id)
);

CREATE USER `readonly`;
ALTER USER `readonly` IDENTIFIED WITH mysql_native_password BY 'readonly';
GRANT SELECT ON *.* TO `readonly`;

CREATE USER `write`;
ALTER USER `write` IDENTIFIED WITH mysql_native_password BY 'write';
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO `write`;