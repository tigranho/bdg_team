create database airportSystem;
use airportSystem;

create table companies(
company_id int not null auto_increment,
name varchar(50) not null,
found_date date not null,
primary key(company_id)
 );

 create table passengers(
 passenger_id int not null auto_increment,
 name varchar(50) not null,
 phone varchar(50) not null,
 country varchar(50) not null,
 city varchar(50) not null,
 primary key(passenger_id),
 );

 create table trip(
 trip_id int not null auto_increment,
 trip_number varchar(50) not null,
 company_id int not null,
 time_in time not null,
 time_out time not null,
 town_to varchar(50) not null,
 town_from varchar(50) not null,
 primary key(trip_id),
 foreign key(company_id) references companies(company_id)
 );

 create table passengerTrips(
 passenger_id int not null,
 trip_id int not null
 );
