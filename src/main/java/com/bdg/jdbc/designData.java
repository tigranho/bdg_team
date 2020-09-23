package com.bdg.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class designData {
    private final String url = "jdbc:mysql://localhost:3306/airport_management_system";
    private final String username = "root";
    private final String password = "root";

    public void writeCompanies() throws IOException,SQLException {
        String sql = "INSERT INTO company(name, founding_date) VALUES(?,?)";
        try (BufferedReader companyReader = new BufferedReader(
                new FileReader("G:\\download\\homework(JDBC)\\companies.txt"));
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            companyReader.readLine();
            String s;
            while ((s = companyReader.readLine()) != null) {
                String[] nameDate = s.split(",");
                stmt.setString(1, nameDate[0]);
                stmt.setString(2,nameDate[1]);

            }
        }
    }



    
    public void writePassengers() throws IOException,SQLException {
        String sql = "INSERT INTO passenger(name, phone) VALUES(?,?)";
        try (BufferedReader passengerReader = new BufferedReader(
                new FileReader("G:\\download\\homework(JDBC)\\passengers.txt"));
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            passengerReader.readLine();
            String s;
            while ((s = passengerReader.readLine()) != null) {
                String[] namePhone = s.split(",");
                pstmt.setString(1, namePhone[0]);
                pstmt.setString(2, namePhone[1]);


                pstmt.executeUpdate();
            }
        }
    }

    public void writeTrips() throws IOException, SQLException {
        String sql = "INSERT INTO trip(company_id,time_in,time_out,from_city,to_city) VALUES(?,?,?,?,?)";
        try (BufferedReader tripReader = new BufferedReader(
                new FileReader("G:\\download\\homework(JDBC)\\trips.txt"));
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            tripReader.readLine();
            String s;
            while ((s = tripReader.readLine()) != null) {
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

    public static void main(String[] args) throws IOException,SQLException {

     //   String time = "jdbc:mysql://localhost:3306/fussa?useLegacyDatetimeCode=false&serverTimezone=UTC";
        designData loader = new designData();

        loader.writeCompanies();
        loader.writePassengers();
        loader.writeTrips();



    }
}

