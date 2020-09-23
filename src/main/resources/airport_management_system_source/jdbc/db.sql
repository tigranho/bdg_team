-- create database airport_management_system;

create table passenger
(
    id    bigserial primary key,
    address_id bigint not null ,
    name  varchar(100) not null,
    phone varchar(150) not null unique,
    constraint fk_passenger_address foreign key (address_id) references address (id)
);

create table address
(
    id      bigserial primary key,
    country varchar(100) not null ,
    city    varchar(100) not null
);

create table company
(
    id            bigserial primary key,
    name          varchar(100),
    founding_date date not null
);

create table trip
(
    id         bigserial primary key,
    company_id bigint    not null,
    time_in    timestamp not null,
    time_out   timestamp not null,
    from_city  varchar(100) not null ,
    to_city    varchar(100) not null ,
    constraint fk_trip_company
        foreign key (company_id) references company (id)
            on update cascade on delete cascade
);

create table passengers_trips
(
    passenger_id bigint,
    trip_id      bigint not null,
    constraint uk_passenger_trip unique (passenger_id, trip_id),
    constraint fk_passengers foreign key (passenger_id) references passenger (id) on update cascade,
    constraint fk_trips foreign key (trip_id) references trip (id) on update cascade
);
