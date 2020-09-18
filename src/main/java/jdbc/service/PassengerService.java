package jdbc.service;

import jdbc.model.Passenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PassengerService {
    private Passenger passenger;

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
