package dao.impl;

import dao.PassengerDao;
import pojo.Address;
import pojo.Passenger;
import pojo.Trip;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {
    @Override
    public Passenger getById(long id) {
        Passenger passenger = null;
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Passenger WHERE passenger_id=?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Address address = new AddressDaoImpl().getById(rs.getLong("address_id"));
            passenger = new Passenger(rs.getString("name"), rs.getString("phone"), address);
            return passenger;
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        Set<Passenger> passengers = new HashSet<>();
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger")) {
            while (rs.next()) {
                Address address = new AddressDaoImpl().getById(rs.getLong("address_id"));
                Passenger passenger = new Passenger(rs.getString("name"), rs.getString("phone"), address);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return passengers;
    }

    // TODO
    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        Set<Passenger> passengers = new HashSet<>();
        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Passenger " +
                     "(name, phone, address_id) VALUES (?, ?, ?)")) {
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setLong(3, new AddressDaoImpl().getTripId(passenger.getAddress()));
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Passenger SET name=?, phone=?, address_id=?")) {
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setLong(3, new AddressDaoImpl().getTripId(passenger.getAddress()));
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to update data: " + e.getMessage());
        }
        return passenger;
    }

    @Override
    public void delete(long passengerId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Passenger WHERE passenger_id=?)")) {
            stmt.setLong(1, passengerId);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to delete data: " + e.getMessage());
        }
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        List<Passenger> passengers = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Passenger WHERE passenger_id IN" +
                     "(SELECT passenger_id PassengersOfTrip WHERE trip_id =?")) {
            ResultSet rs = stmt.executeQuery();
            stmt.setLong(1, tripNumber);
            stmt.execute();
            while (rs.next()) {
                Address address = new AddressDaoImpl().getById(rs.getLong("address_id"));
                Passenger passenger = new Passenger(rs.getString("name"), rs.getString("phone"), address);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return passengers;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt1 = conn.prepareStatement("SELECT passenger_id FROM Passenger WHERE name=?");
             PreparedStatement stmt2 = conn.prepareStatement("SELECT trip_id FROM Trips WHERE trip_id=?");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO PassengersOfTrip " +
                     "(name, trip_id, passenger_id) VALUES (?, ?)")) {
            stmt1.setString(1, passenger.getName());
            ResultSet rs1 = stmt1.executeQuery();
            stmt2.setLong(1, trip.getNumber());
            ResultSet rs2 = stmt2.executeQuery();
            stmt.setString(1, rs1.getString(1));
            stmt.setString(2, rs2.getString(1));
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to insert data: " + e.getMessage());
        }
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM PassengersOfTrip " +
                     "WHERE trip_id=? AND passenger_id=?")) {
            stmt.setLong(1, passengerId);
            stmt.setLong(2, tripNumber);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to delete data: " + e.getMessage());
        }
    }
}
