drop database AirportManagmentSystem1;
Create Database AirportManagmentSystem1;
use AirportManagmentSystem1;
Create TABLE Address(
  address_id bigInt PRIMARY KEY auto_increment,
   country VARCHAR(255) not null ,
   city VARCHAR(255) not null
);

Create TABLE Passenger(
  passenger_id bigInt PRIMARY KEY auto_increment,
   name VARCHAR(255) not null,
   phone VARCHAR(255) not null,
    address_id bigint not null,
    CONSTRAINT fk_address
    FOREIGN KEY (address_id)
      REFERENCES address(address_id)
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
	town_from VARCHAR(255) not NULL,
	CONSTRAINT fk_company
	FOREIGN KEY (company_id)
	  REFERENCES Company(company_id)
);

Create TABLE Passenger_Trip(
  trip_id bigint not NULL ,
  passenger_id bigint not null
);