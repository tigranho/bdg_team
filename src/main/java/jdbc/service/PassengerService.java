package jdbc.service;

import jdbc.model.Passenger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PassengerService {
    private Passenger passenger;

    public Passenger getById(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/airportsystem", "root", "root");

             PreparedStatement pstmt = conn.prepareStatement("select * from passengers where passenger_id =" + id);
             ResultSet rs = pstmt.executeQuery();) {
            rs.first();
                Passenger passenger = new Passenger
                        (rs.getString("name"), rs.getString("phone"),
                                rs.getString("country"), rs.getString("city"));
                return passenger;
            }
    }

    public Set<Passenger> getAll() throws SQLException {
        Set<Passenger> passengers = new HashSet<>();
        try(Connection conn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/airportsystem", "root", "root");
            PreparedStatement pstmt = conn.prepareStatement("select * from passengers");
            ResultSet rs = pstmt.executeQuery();){
            while(rs.next()) {
                Passenger passenger = new Passenger
                        (rs.getString("name"), rs.getString("phone"),
                                rs.getString("country"), rs.getString("city"));
                passengers.add(passenger);
            }
                return passengers;
        }
    }


    public Passenger save(Passenger passenger) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/airportsystem", "root", "root");
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into passengers" +
                                "(name, phone, country, city)" +
                                "values (?, ?, ?, ?)"
                );) {
            pstmt.setString(1, passenger.getName());
            pstmt.setString(2, passenger.getPhone());
            pstmt.setString(3, passenger.getCountry());
            pstmt.setString(4, passenger.getCity());
            pstmt.execute();
        }
        return passenger;
    }
}
