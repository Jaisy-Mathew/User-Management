create table user.address
(
	address_id int auto_increment,
	postcode varchar(10) null,
	suburb varchar(50) null,
	state varchar(50) null,
	full_address varchar(50) null,
	constraint ADDRESS_ID_uindex
		unique (address_id)
)
comment 'Table to store address details of a user';

alter table user.address
	add primary key (address_id);

create table user.user
(
	user_id int auto_increment,
	title varchar(5) null,
	first_name varchar(50) null,
	last_name varchar(50) null,
	mobile_number varchar(15) null,
	address_id int not null,
	constraint USER_ID_uindex
		unique (user_id),
	constraint user_address_address_id_fk
		foreign key (address_id) references user.address (address_id)
)
comment 'Table to store user details';

alter table user.user
	add primary key (user_id);

