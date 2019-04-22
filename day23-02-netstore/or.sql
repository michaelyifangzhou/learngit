use netstore;
create table orders(
	ordernum varchar(100) primary key,
	quantity int,
	money float(8,2),
	status int,
	customerId varchar(100),
	constraint customer_Id_fk foreign key(customerId) references customers(id)
);
create table orderitems(
	id varchar(100) primary key,
	quantity int,
	price float(8,2),
	bookId varchar(100),
	ordernum varchar(100),
	constraint bookId_fk foreign key(bookId) references books(id),
	constraint ordernum_fk foreign key(ordernum) references orders(ordernum)
);