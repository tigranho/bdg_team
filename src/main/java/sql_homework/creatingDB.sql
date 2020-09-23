create table companies
(
    id int auto_increment,
    name varchar(50) not null,
    foundingDate date not null,
    constraint companies_pk
        primary key (id)
);

create unique index companies_name_uindex
    on companies (name);

create table addresses
(
    id int auto_increment,
    country varchar(50) not null,
    city varchar(50) not null,
    constraint addresses_pk
        primary key (id)
);

create table passengers
(
    id int auto_increment,
    name varchar(50) not null,
    phone varchar(50) not null,
    addressID int not null,
    constraint passengers_pk
        primary key (id),
    constraint passengers_addresses__fk
        foreign key (addressID) references addresses (`id`)
);

create table trips
(
    id int auto_increment,
    companyID int not null,
    timeIn datetime not null,
    timeOut datetime not null,
    townFrom varchar(50) not null,
    townTo varchar(50) not null,
    constraint trips_pk
        primary key (id),
    constraint trips_companies__fk
        foreign key (companyID) references companies (`id`)
);

create table passenger_trip
(
    id int auto_increment,
    passengerID int not null,
    tripID int not null,
    constraint trips_pk
        primary key (id),
    constraint passenger_trip_passengers__fk
        foreign key (passengerID) references passengers (`id`),
    constraint passenger_trip_trips__fk
        foreign key (tripID) references trips (`id`)
);