package tasks.airportManagementSystem.JDBC.daoImpl;

import tasks.airportManagementSystem.JDBC.dao.PassengerDAO;
import tasks.airportManagementSystem.JDBC.model.Address;
import tasks.airportManagementSystem.JDBC.model.Passenger;
import tasks.airportManagementSystem.JDBC.model.Trip;

import static tasks.airportManagementSystem.JDBC.DbConnection.getConnection;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class PassengerImpl implements PassengerDAO {
    @Override
    public Passenger getById(int id) {
        String query = "SELECT * FROM PASSENGERS LEFT JOIN ADDRESS A " +
                "ON PASSENGERS.ADDRESS_ID = A.ID WHERE PASSENGERS.ID = ?";
        Passenger passenger = new Passenger();
        Address address = new Address();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                passenger.setId(id);
                passenger.setName(rs.getString("name"));
                passenger.setPhone(rs.getString("phone"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setId(rs.getInt("address_id"));
                passenger.setAddress(address);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        String query = "SELECT * FROM PASSENGERS " +
                "LEFT JOIN ADDRESS A ON PASSENGERS.ADDRESS_ID = A.ID";
        Set<Passenger> passengers = new LinkedHashSet<>();
        Passenger passenger;
        Address address;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                address = new Address();
                address.setId(rs.getInt("address_id"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                passenger = new Passenger();
                passenger.setId(rs.getInt("id"));
                passenger.setName(rs.getString("name"));
                passenger.setPhone(rs.getString("phone"));
                passenger.setAddress(address);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return passengers;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        String query = "SELECT * FROM PASSENGERS ORDER BY " + sort + " LIMIT ? OFFSET ?";
        Set<Passenger> passengers = new LinkedHashSet<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, perPage);
            stmt.setInt(2, page);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                passengers.add(getById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) {
        Address address = new AddressImpl().ifExistIgnoreElseAddAddress(passenger.getAddress());
        String query = "insert into passengerS(name, PHONE,ADDRESS_ID) values(?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setInt(3, address.getId());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next())
                passenger.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Address address = new AddressImpl().ifExistIgnoreElseAddAddress(passenger.getAddress());
        String query = "UPDATE PASSENGERS SET NAME = ?, PHONE = ?, ADDRESS_ID = ? WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setInt(3, address.getId());
            stmt.setInt(4, passenger.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return passenger;
    }

    @Override
    public void delete(int passengerId) {
        String query = "delete from passengers where id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, passengerId);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    //TODO
    @Override
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(int passengerId, int tripNumber) {

    }
}
