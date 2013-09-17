create database pollsystem;

use pollsystem;

create table polls(
pollid varchar(10) primary key ,
adminid varchar(13) ,
title varchar(50) ,
initiator varchar(10),
description varchar(255),
email varchar(50),
polltype int ,
ipaddress varchar(25),
pollcreatetime datetime,
updatetime datetime,
lastvotetime datetime,
isdelete int  
);

create table polloptions(
optionid int primary key auto_increment,
pollid varchar(10) not null references polls(pollid),
options varchar(255) 
);


create table pollmembers(
memberid int primary key auto_increment,
pollid varchar(10) not null references polls(pollid),
membername varchar(10)
);

create table pollcomments(
commentid int primary key auto_increment,
pollid varchar(10) not null references polls(pollid),
name varchar(10),
ipaddress varchar(25),
commenttime datetime,
commentcont varchar(255)
);

create table pollitems(
itemid int primary key auto_increment,
pollid varchar(10) not null references polls(pollid),
optionid int not null references polloptions(optionid),
memberid int not null references pollmenbers(memberid),
itemcont int
);


create table pollcount(
pollnum int primary key
);

drop table polls;
drop table polloptions;
drop table pollmembers;
drop table pollcomments;
drop table pollitems;