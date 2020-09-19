package jdbc.airoport_management_system.daoImpl;

import jdbc.airoport_management_system.dao.PassengerDAO;
import jdbc.airoport_management_system.entity.Address;
import jdbc.airoport_management_system.entity.Passenger;
import jdbc.airoport_management_system.entity.Trip;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerImpl implements PassengerDAO {
    private final String url = "jdbc:postgres://localhost:5432/airport_db";
    private final String username = "postgres";
    private final String password = "1999";
    private ResultSet result = null;

    @Override
    public Passenger getById(long id) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Passenger"
                    + "WHERE id = " + id + " ";

            result = stmt.executeQuery(sql);
        }
        catch(SQLException e) {
            System.out.println("Exception is thrown");
        }

        return new Passenger(result.getString("name"),
                result.getString("phone"),
                new Address(result.getString("country"), result.getString("city")));
    }

    @Override
    public Set<Passenger> getAll() {
        Set<Passenger> passengers = new HashSet<>();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Passenger";

            result = stmt.executeQuery(sql);

            while (result.next()) {
                passengers.add(new Passenger(result.getString("name"),
                        result.getString("phone"),
                        new Address(result.getString("country"), result.getString("city"))));
            }
        }
        catch(SQLException e) {
            System.out.println("Exception is thrown");
        }


        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Passenger"
                    + "VALUES("+passenger.getName()+", "+passenger.getPhone()+", "+passenger.getAddress().getCity()+", "+passenger.getAddress().getCountry()+")";

           stmt.executeUpdate(sql);
        }
        catch(SQLException e) {
            System.out.println("Exception is thrown");
        }

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        return null;
    }

    @Override
    public void delete(long passengerId) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM Passenger" +
                    "WHERE id = "+passengerId+"";

            stmt.executeUpdate(sql);
        }
        catch(SQLException e) {
            System.out.println("Exception is thrown");
        }
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}
