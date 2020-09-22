package jdbc;

import jdbc.model.Company;
import jdbc.model.Passenger;
import jdbc.service.CompanyService;
import jdbc.service.PassengerService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Storedb {

    public static void storeCompaniesInfo(String url) throws SQLException, IOException {

        try (BufferedReader fileReader = new BufferedReader(
                new FileReader("C:\\Users\\Dell\\Desktop\\JavaBDG\\Task\\homework(JDBC)\\companies.txt"));
             Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(
                     "insert into companies" +
                             "(name, phone, country, city)" +
                             "values (?, ?)"
             );
        ) {
            fileReader.readLine();
            while (fileReader.readLine() != null) {
                String[] str = fileReader.readLine().split(",");
                Company company = new Company(str[0], str[1]);
                CompanyService service = new CompanyService();
                service.save(company);
            }
        }
    }

    public static void storePassengersInfo(String url) throws SQLException, IOException {

        try (BufferedReader fileReader = new BufferedReader(
                new FileReader("C:\\Users\\Dell\\Desktop\\JavaBDG\\Task\\homework(JDBC)\\passengers.txt"));
             Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(
                     "insert into passengers" +
                             "(name, phone, country, city)" +
                             "values (?, ?, ?, ?)"
             );
        ) {
            fileReader.readLine();
            while (fileReader.readLine() != null) {
                String[] str = fileReader.readLine().split(",");

                Passenger passenger = new Passenger(str[0], str[1], str[2], str[3]);
                PassengerService service = new PassengerService();
                service.save(passenger);
            }
        }
    }

    public void storeTripInfo() {

    }

    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/airportsystem";
//        storePassengersInfo(url);
//        storeCompaniesInfo(url);

    }
}

