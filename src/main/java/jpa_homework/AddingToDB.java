package jpa_homework;

import jpa_homework.models.Address;
import jpa_homework.models.Company;
import jpa_homework.models.Passenger;
import jpa_homework.models.Trip;
import jpa_homework.services.CompanyDAOImpl;
import jpa_homework.services.PassengerDAOImpl;
import jpa_homework.services.TripDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddingToDB {
    public static void addCompanies() throws IOException {
        try (BufferedReader companiesReader = new BufferedReader(
                new FileReader("C:\\Users\\User\\Desktop\\Homeworks\\homework(JDBC)\\companies.txt")))
        {
            companiesReader.readLine();

            String s;
            while ((s = companiesReader.readLine()) != null) {
                String[] nameDate = s.split(",");
                Company company = new Company(nameDate[0],
                        LocalDate.parse(nameDate[1], DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US)));
                CompanyDAOImpl c = new CompanyDAOImpl();
                c.save(company);
            }
        }
    }

    public static void addPassengers() throws IOException {
        try (BufferedReader passengersReader = new BufferedReader(
                new FileReader("C:\\Users\\User\\Desktop\\Homeworks\\homework(JDBC)\\passengers.txt")))
        {
            passengersReader.readLine();

            String s;
            while ((s = passengersReader.readLine()) != null) {
                String[] namePhoneCountryCity = s.split(",");
                Address address = new Address(namePhoneCountryCity[2], namePhoneCountryCity[3]);
                Passenger passenger = new Passenger(namePhoneCountryCity[0], namePhoneCountryCity[1], address);
                PassengerDAOImpl p = new PassengerDAOImpl();
                p.save(passenger);
            }
        }
    }

    public static void addTrips() throws IOException {
        try (BufferedReader tripsReader = new BufferedReader(
                new FileReader("C:\\Users\\User\\Desktop\\Homeworks\\homework(JDBC)\\trips.txt")))
        {
            tripsReader.readLine();
            String s;
            while ((s = tripsReader.readLine()) != null) {
                String[] arr = s.split(",");
//                for (int i = 0; i < arr.length; i++) {
//                    System.out.println(arr[i] + " " + i);
//                }
                CompanyDAOImpl c = new CompanyDAOImpl();
                Company company = c.getById(Integer.parseInt(arr[0]));
                LocalDateTime in = LocalDateTime.parse(arr[1], DateTimeFormatter.ofPattern("M/d/yyyy HH:mm", Locale.US));
                LocalDateTime out =
                        LocalDateTime.parse(arr[2], DateTimeFormatter.ofPattern("M/d/yyyy HH:mm", Locale.US));
                Trip trip = new Trip(company, in, out, arr[3], arr[4]);
                TripDAOImpl t = new TripDAOImpl();
                t.save(trip);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        addCompanies();
//        addPassengers();
        addTrips();
    }

}
