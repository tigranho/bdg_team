drop database AirportManagmentSystem;
Create Database AirportManagmentSystem;
use AirportManagmentSystem;
Create TABLE Passenger(
  passenger_id bigInt PRIMARY KEY auto_increment,
   name VARCHAR(255) not null,
   phone VARCHAR(255) not null,
   country VARCHAR(255) not NULL,
   city VARCHAR(255) not null
);

Create TABLE Company(
  company_id bigInt PRIMARY KEY auto_increment,
   name VARCHAR(255) Not Null,
   found_date varchar(255) not null
);
Create TABLE Trip(
	trip_id bigInt PRIMARY KEY auto_increment,
	trip_number bigInt not null,
	company_id bigInt not NULL ,
	time_in varchar(255) not NULL ,
	time_out varchar(255)  not NULL ,
	town_to VARCHAR(255) not NULL ,
	town_from VARCHAR(255) not NULL
);
Create TABLE Address(
  address_id bigInt PRIMARY KEY auto_increment,
   country VARCHAR(255) not null ,
   city VARCHAR(255) not null
);

Create TABLE Passenger_Trip(
  trip_id bigint not NULL ,
  passenger_id bigint not null
);