package jdbc_homework;

import java.io.*;
import java.sql.*;

public class AddingToDB {

    private static void writeCompanies(String url) throws IOException, SQLException {
        String sql = "INSERT INTO companies(name, `found date`) VALUES(?,?)";
        try (BufferedReader companiesReader = new BufferedReader(
                new FileReader("C:\\Users\\User\\Desktop\\Homeworks\\homework(JDBC)\\companies.txt"));
             Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            companiesReader.readLine();
            String s;
            while ((s = companiesReader.readLine()) != null) {
                String[] nameDate = s.split(",");
                pstmt.setString(1, nameDate[0]);
                pstmt.setString(2, nameDate[1]);
                pstmt.executeUpdate();
            }
        }
    }

    private static void writePassengers(String url) throws IOException, SQLException {
        String sql = "INSERT INTO passengers(name, phone, country, city) VALUES(?,?,?,?)";
        try (BufferedReader companiesReader = new BufferedReader(
                new FileReader("C:\\Users\\User\\Desktop\\Homeworks\\homework(JDBC)\\passengers.txt"));
             Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            companiesReader.readLine();
            String s;
            while ((s = companiesReader.readLine()) != null) {
                String[] namePhoneCountryCity = s.split(",");
                pstmt.setString(1, namePhoneCountryCity[0]);
                pstmt.setString(2, namePhoneCountryCity[1]);
                pstmt.setString(3, namePhoneCountryCity[2]);
                pstmt.setString(4, namePhoneCountryCity[3]);
                pstmt.executeUpdate();
            }
        }
    }

    private static void writeTrips(String url) throws IOException, SQLException {
        String sql = "INSERT INTO trips(`trip number`, company, `time in`, `time out`, `town from`, `town to`) VALUES(?,?,?,?,?,?)";
        try (BufferedReader tripsReader = new BufferedReader(
                new FileReader("C:\\Users\\User\\Desktop\\Homeworks\\homework(JDBC)\\trips.txt"));
             Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            tripsReader.readLine();
            String s;
            while ((s = tripsReader.readLine()) != null) {
                String[] arr = s.split(",");
                pstmt.setString(1, arr[0]);
                pstmt.setString(2, arr[1]);
                pstmt.setString(3, arr[2]);
                pstmt.setString(4, arr[3]);
                pstmt.setString(5, arr[4]);
                pstmt.setString(6, arr[5]);
                pstmt.executeUpdate();
            }
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        String url = "jdbc:mysql://localhost:3306/airport";
        writeCompanies(url);
        writePassengers(url);
        writeTrips(url);
    }
}
