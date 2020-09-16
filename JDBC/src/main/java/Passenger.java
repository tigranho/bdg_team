import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Passenger {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:Mysql:@127.0.0.1:3306";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    private String name;
    private String phone;
    private String country;
    private String city;

    public Passenger(String name, String phone, String country, String city) {
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public Passenger getById(long id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger WHERE passenger_id='" + id + "'");) {
            Passenger passenger = new Passenger(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            return passenger;
        }
    }

    public Set<Passenger> getAll() throws SQLException {
        Set<Passenger> passengers = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger");) {
            while (rs.next()) {
                Passenger passenger = new Passenger(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                passengers.add(passenger);
            }
        }
        return passengers;
    }

    // TODO
    public Set<Passenger> get(int page, int perPage, String sort) {
        Set<Passenger> passengers = new HashSet<>();
        return passengers;
    }

    public static Passenger save(Passenger passenger) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Passengers" +
                             "(name, phone, country, city)" +
                             "VALUES (?, ?, ?, ?)"
             );
        ) {
            stmt.setString(1, passenger.name);
            stmt.setString(2, passenger.phone);
            stmt.setString(3, passenger.country);
            stmt.setString(4, passenger.city);
            stmt.execute();
        }
        return passenger;
    }

    public Passenger update(Passenger passenger) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE Passenger SET name=?, phone=?, country=?, city=?");) {
            stmt.setString(1, passenger.name);
            stmt.setString(2, passenger.phone);
            stmt.setString(3, passenger.country);
            stmt.setString(4, passenger.city);
            stmt.execute();
        }
        return passenger;
    }

    public void delete(long passengerId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Passenger WHERE passenger_id=?)");) {
            stmt.setString(1, String.valueOf(passengerId));
            stmt.execute();
        }
    }

    public List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger" +
                     "WHERE passenger_id IN" +
                     "(SELECT passenger_id PassengersOfTrip WHERE trip_id = '" + tripNumber + "')");) {
            while (rs.next()) {
                Passenger passenger = new Passenger(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                passengers.add(passenger);
            }
        }
        return passengers;
    }

    public void registerTrip(Trip trip, Passenger passenger) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO PassengersOfTrip" +
                             "(name, trip_id, passenger_id)" +
                             "VALUES (?, ?)");
             Statement stmt1 = conn.createStatement();
             ResultSet rs1 = stmt1.executeQuery("SELECT passenger_id FROM Passengers WHERE name = '" + passenger.name + "'");
             ResultSet rs2 = stmt1.executeQuery("SELECT trip_id FROM Passengers WHERE name = '" + trip.getNumber() + "'");
        ) {
            stmt.setString(1, rs1.getString(1));
            stmt.setString(2, rs2.getString(1));
        }
    }

    // TODO
    public void cancelTrip(long passengerId, long tripNumber) {
    }
}
