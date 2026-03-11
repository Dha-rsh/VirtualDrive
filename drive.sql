create database drive;
use drive;
create table users(
id int primary key auto_increment,
name varchar(100),
email varchar(100) not null unique ,
pass varchar(100)
);
select * from users;

create table folders(
id int primary key auto_increment,
name varchar(100),
parent_id int null,
user_id int ,
path varchar(500),
created_at timestamp default current_timestamp
);

select * from folders;
drop table files;
create table files(
id int primary key auto_increment,
name varchar(100),
file_path varchar(500),
size bigInt,
folder_id int,
user_id int,
uploaded_at timestamp default current_timestamp
);
select * from files;
insert into files(name,file_path,size,folder_id,user_id) 
values("fam.jpg","C:\virtaulStorage\user_1\fam.jpg",92673,1,1);