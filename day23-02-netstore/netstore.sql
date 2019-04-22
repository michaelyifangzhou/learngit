create database netstore;
use netstore;
create table categorys(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);
create table books(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	author varchar(100),
	price float(8,2),
	path varchar(100),
	filename varchar(100),
	description varchar(255),
	categoryId varchar(100),
	constraint category_id_fk foreign key(categoryId) references categorys(id)
);
create table customers(
	id varchar(100) primary key,
	username varchar(100) not null unique,
	password varchar(100) not null,
	nickname varchar(100),
	phonenum varchar(100),
	address varchar(100),
	email varchar(100)
);
create table users(
	id varchar(100) primary key,
	username varchar(100) not null unique,
	password varchar(100) 
);
create table roles(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(100) 
);
create table functions(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(100) 
);
create table user_role(
	u_id varchar(100),
	r_id varchar(100),
	primary key(u_id,r_id),
	constraint user_id_fk foreign key(u_id) references users(id),
	constraint role_id_fk1 foreign key(r_id) references roles(id)
);
create table role_function(
	r_id varchar(100),
	f_id varchar(100),
	primary key(r_id,f_id),
	constraint role_id_fk2 foreign key(r_id) references roles(id),
	constraint functon_id_fk foreign key(f_id) references functions(id)
);