CREATE DATABASE bank;
USE bank;

--drop table account;
create table account (
  id int auto_increment,
  number varchar(30),
  balance bigint,
  primary key (id)
);

--drop table operation;
create table operation (
  id int auto_increment,
  timestamp timestamp default now(),
  account_id int,
  funds bigint,
  type varchar(10),
  parent_id int null,
  primary key (id),
  foreign key (account_id) references account(id) on delete cascade,
  foreign key (parent_id) references operation(id) on delete cascade
) engine=innodb;