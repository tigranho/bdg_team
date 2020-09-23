package tasks.airportManagementSystem.JDBC.daoImpl;


import tasks.airportManagementSystem.JDBC.dao.PassengerDAO;
import tasks.airportManagementSystem.JDBC.model.Address;
import tasks.airportManagementSystem.JDBC.model.Company;
import tasks.airportManagementSystem.JDBC.model.Passenger;
import tasks.airportManagementSystem.JDBC.model.Trip;

import static tasks.airportManagementSystem.JDBC.daoImpl.CompanyImpl.getConnection;

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
    public Passenger getById(int id) throws SQLException {
        String query = "SELECT * FROM PASSENGERS " +
                "LEFT JOIN ADDRESS A ON PASSENGERS.ADDRESS_ID = A.ID " +
                "WHERE PASSENGERS.ID = ?";
        Passenger passenger = new Passenger();
        Address address = new Address();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
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
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public Set<Passenger> getAll() throws SQLException {
        String query = "SELECT * FROM PASSENGERS " +
                "LEFT JOIN ADDRESS A ON PASSENGERS.ADDRESS_ID = A.ID";
        Set<Passenger> passengers = new LinkedHashSet<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                passengers.add(getById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    //TODO  like companyImpl.get method
    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        String query = "SELECT * FROM PASSENGERS ORDER BY ? LIMIT ? OFFSET ?";
        Set<Passenger> passengers = new LinkedHashSet<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, sort);
            stmt.setInt(2, perPage);
            stmt.setInt(3, page);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                passengers.add(getById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) throws SQLException {
        Address address = new AddressImpl().save(passenger.getAddress());
        String query = "insert into passengerS(name, PHONE,ADDRESS_ID) values(?,?,?)";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setInt(3, address.getId());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next())
                passenger.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) throws SQLException {
        Address address = new AddressImpl().save(passenger.getAddress());
        String query = "UPDATE PASSENGERS SET NAME = ?, PHONE = ?, ADDRESS_ID = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setInt(3, address.getId());
            stmt.setInt(4, passenger.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public void delete(int passengerId) {
        String query = "delete from passengers where id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, passengerId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
