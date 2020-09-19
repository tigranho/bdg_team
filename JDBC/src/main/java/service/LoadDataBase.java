package service;

import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.PassengerDaoImpl;
import pojo.Address;
import pojo.Company;
import pojo.Passenger;
import util.DBConnection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class LoadDataBase {
    public static void createAddress() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE TABLE Address (" +
                            "address_id INT PRIMARY KEY," +
                            "country VARCHAR (50) NOT NULL," +
                            "city VARCHAR (50) NOT NULL)"
            );
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createPassengers() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE TABLE Trip(" +
                            "trip_id INT PRIMARY KEY," +
                            "tripNumber INT NOT NULL," +
                            "company_id INT NOT NULL," +
                            "INDEX (company_id)," +
                            "FOREIGN KEY (company_id) " +
                            "REFERENCES Company (company_id) ON UPDATE CASCADE ON DELETE RESTRICT," +
                            "timeIn VARCHAR(50) NOT NULL," +
                            "timeOut VARCHAR(50) NOT NULL," +
                            "destination VARCHAR(50) NOT NULL," +
                            "origin VARCHAR(50) NOT NULL)"
            );
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createCompany() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE TABLE Company (" +
                            "company_id INT PRIMARY KEY," +
                            "name VARCHAR (50) NOT NULL," +
                            "found_date VARCHAR (50) NOT NULL)"
            );
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createTrip() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE TABLE Trip(" +
                            "trip_id INT PRIMARY KEY," +
                            "tripNumber INT NOT NULL," +
                            "company_id INT NOT NULL," +
                            "INDEX (company_id)," +
                            "FOREIGN KEY (company_id)" +
                            "    REFERENCES Company (company_id) ON UPDATE CASCADE ON DELETE RESTRICT," +
                            "timeIn VARCHAR(50) NOT NULL," +
                            "timeOut VARCHAR(50) NOT NULL," +
                            "destination VARCHAR(50) NOT NULL," +
                            "origin VARCHAR(50) NOT NULL"
            );
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void createPassengersOfTrip() {
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE TABLE PassengersOfTrip" +
                            "(rip_id INT NOT NULL," +
                            "INDEX (trip_id)," +
                            "FOREIGN KEY (trip_id) REFERENCES Trip (trip_id)," +
                            "passenger_id INT NOT NULL," +
                            "INDEX (passenger_id)," +
                            "FOREIGN KEY (passenger_id)" +
                            "    REFERENCES Passenger (passenger_id))"
            );
        } catch (SQLException e) {
            System.out.println("Failed to create table");
        }
    }

    public static void loadPassengersAndAddress() {
        try (BufferedReader reader = new BufferedReader(new FileReader("service/passengers.txt"))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] strInput = s.split(",");
                Address address = new AddressDaoImpl().save(new Address(strInput[2], strInput[3]));
                Passenger passenger = new Passenger(strInput[0], strInput[1], address);
                new PassengerDaoImpl().save(passenger);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with reading the file: " + e.getMessage());
        }
    }

    public static void loadCompany() {
        try (BufferedReader reader = new BufferedReader(new FileReader("service/companies.txt"))) {
            String s;
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
        createPassengers();
        createCompany();
        createTrip();
        createPassengersOfTrip();
        loadPassengersAndAddress();
        loadCompany();
    }
}
