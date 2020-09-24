
create table passengers(
    id int not null auto_increment primary key,
    name varchar(60) not null,
    phone varchar(30),
    address_id int not null,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

create table address(
    id int not null auto_increment primary key,
    country longtext not null,
    city varchar(30)
);

create table companies(
    id int not null auto_increment primary key,
    name varchar(30) not null,
    found_date varchar(30)
);

create table trip(
    id int not null auto_increment primary key,
    trip_number int not null,
    time time not null,
    address_id int not null,
    foreign key (address_id) references address(id),
    passenger_id int not null,
    foreign key (passenger_id) references passengers(id)
)