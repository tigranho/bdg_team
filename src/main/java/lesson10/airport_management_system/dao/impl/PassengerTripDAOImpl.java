package lesson10.airport_management_system.dao.impl;

import lesson10.airport_management_system.dao.PassengerTripDAO;
import lesson10.airport_management_system.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PassengerTripDAOImpl implements PassengerTripDAO {

    @Override
    public void save(long passengerId, long tripId) {
        final String query = "insert into passengers_trips values (?, ?);";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, passengerId);
            stmt.setLong(2, tripId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
    }

    @Override
    public void delete(long passengerId, long tripId) {
        final String query = "delete from passengers_trips where passenger_id = ? and trip_id = ?;";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, passengerId);
            stmt.setLong(2, tripId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
    }

    @Override
    public boolean updatePassengerByTripId(long updatePassengerId, long tripId) {
        final String query = "update passengers_trips set passenger_id = ? where trip_id = ?;";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, updatePassengerId);
            stmt.setLong(2, tripId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateTripByPassengerId(long passengerId, long updateTripId) {
        final String query = "update passengers_trips set trip_id = ? where passenger_id = ?;";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, updateTripId);
            stmt.setLong(2, passengerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
        return true;
    }

    @Override
    public void saveAll(List<String> passengersTrips) {
        StringBuilder query = new StringBuilder("insert into passengers_trips (passenger_id, trip_id) values ");
        try (Connection con = DBConnector.getConnection();
             Statement stmt = con.createStatement()) {
            con.setAutoCommit(false);
            for (String line : passengersTrips) {
                String[] data = line.split(",");
                query.append("(").append(data[0].trim()).append(", ").append(data[1].trim()).append("),");
            }
            query.replace(query.length() - 1, query.length(), ";");
            int count = stmt.executeUpdate(query.toString(), Statement.RETURN_GENERATED_KEYS);
            System.out.println(count);
            con.commit();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
    }
}
