package service;

import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.PassengerDaoImpl;
import pojo.Address;
import pojo.Company;
import pojo.Passenger;
import util.DBConnection;

import java.io.*;
import java.sql.*;

public class LoadDataBase {

    private static final String CREATE_ADDRESSES = "CREATE TABLE Address (" +
            "address_id INT NOT NULL AUTO_INCREMENT, " +
            "country VARCHAR (50) NOT NULL, " +
            "city VARCHAR (50) NOT NULL, " +
            "CONSTRAINT address_pk PRIMARY KEY (address_id))";

    private static final String CREATE_PASSENGERS = "CREATE TABLE Passenger(" +
            "passenger_id INT NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(50) NOT NULL, " +
            "phone VARCHAR(50) NOT NULL, " +
            "address_id INT NOT NULL," +
            "INDEX (address_id), " +
            "FOREIGN KEY (address_id) " +
            "REFERENCES Address (address_id) ON UPDATE CASCADE ON DELETE RESTRICT, " +
            "CONSTRAINT passenger_pk PRIMARY KEY (passenger_id))";

    private static final String CREATE_COMPANIES = "CREATE TABLE Company (" +
            "company_id INT NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR (50) NOT NULL, " +
            "found_date VARCHAR (50) NOT NULL, " +
            "CONSTRAINT company_pk PRIMARY KEY (company_id))";

    private static final String CREATE_TRIPS = "CREATE TABLE Trip(" +
            "trip_id INT NOT NULL AUTO_INCREMENT, " +
            "tripNumber INT NOT NULL, " +
            "company_id INT NOT NULL, " +
            "INDEX (company_id), " +
            "FOREIGN KEY (company_id) " +
            "REFERENCES Company (company_id) ON UPDATE CASCADE ON DELETE RESTRICT, " +
            "timeIn VARCHAR(50) NOT NULL, " +
            "timeOut VARCHAR(50) NOT NULL, " +
            "destination VARCHAR(50) NOT NULL, " +
            "origin VARCHAR(50) NOT NULL, " +
            "CONSTRAINT trip_pk PRIMARY KEY (trip_id))";

    private static final String CREATE_PASSENGERS_OF_TRIPS = "CREATE TABLE PassengersOfTrip (" +
            "trip_id INT NOT NULL, " +
            "INDEX (trip_id), " +
            "FOREIGN KEY (trip_id) " +
            "REFERENCES Trip (trip_id), " +
            "passenger_id INT NOT NULL, " +
            "INDEX (passenger_id), " +
            "FOREIGN KEY (passenger_id) " +
            "REFERENCES Passenger (passenger_id))";

    public static void createAddress() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_ADDRESSES);
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createPassenger() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_PASSENGERS);
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createCompany() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_COMPANIES);
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createTrip() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TRIPS);
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createPassengersOfTrip() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_PASSENGERS_OF_TRIPS);
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void loadPassengersAndAddress() {
        try (BufferedReader reader = new BufferedReader(new FileReader("JDBC/src/main/resources/passengers.txt"))) {
            String s;
            reader.readLine();
            while ((s = reader.readLine()) != null) {
                String[] strInput = s.split(",");
                Address address = new AddressDaoImpl().save(new Address(strInput[2], strInput[3]));
                new PassengerDaoImpl().save(new Passenger(strInput[0], strInput[1], address));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with reading the file: " + e.getMessage());
        }
    }

    public static void loadCompanies() {
        try (BufferedReader reader = new BufferedReader(new FileReader("JDBC/src/main/resources/companies.txt"))) {
            String s;
            reader.readLine();
            while ((s = reader.readLine()) != null) {
                String[] strInput = s.split(",");
                Company company = new Company(strInput[0], strInput[1]);
                new CompanyDaoImpl().save(company);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createAddress();
        createPassenger();
        createCompany();
        createTrip();
        createPassengersOfTrip();
        loadPassengersAndAddress();
        loadCompanies();
    }
}
