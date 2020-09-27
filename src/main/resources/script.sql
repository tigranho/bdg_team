create table address(
    id int not null auto_increment primary key,
    country longtext not null,
    city varchar(60)
);

create table passengers(
    id int not null auto_increment primary key,
    name varchar(60) not null,
    phone varchar(30),
    address_id int not null,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

create table companies(
    id int not null auto_increment primary key,
    name varchar(30) not null,
    found_date varchar(30)
);

create table trip(
    trip_number int not null auto_increment primary key,
    company_id int not null,
    time_in time not null,
    time_out time not null,
    town_too varchar(60) not null,
    town_from varchar(60) not null,
    foreign key (company_id) references companies(id)
);

create table passenger_trip(
    passenger_id int not null,
    trip_id int not null,
    constraint unique (passenger_id, trip_id),
    foreign key (passenger_id) references passengers(id),
    foreign key (trip_id) references trip(trip_number)
);