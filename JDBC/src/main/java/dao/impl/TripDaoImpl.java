package dao.impl;

import dao.TripDao;
import pojo.Trip;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripDaoImpl implements TripDao {
    @Override
    public Trip getById(long id) {
        Trip trip = null;
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Trip WHERE trip_id=?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            trip = new Trip(rs.getLong("tripNumber"),
                    new CompanyDaoImpl().getById(rs.getLong("company_id")),
                    rs.getString("timeIn"), rs.getString("timeOut"),
                    rs.getString("destination"), rs.getString("origin"),
                    new PassengerDaoImpl().getPassengersOfTrip(rs.getLong("tripNumber")));
            return trip;
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Set<Trip> trips = new HashSet<>();
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Trip")) {
            while (rs.next()) {
                Trip trip = new Trip(rs.getLong("tripNumber"),
                        new CompanyDaoImpl().getById(rs.getLong("company_id")),
                        rs.getString("timeIn"), rs.getString("timeOut"),
                        rs.getString("destination"), rs.getString("origin"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return trips;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip trip) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Trip " +
                     "(tripNumber, company_id, timeIn, timeOut, destination, origin) VALUES (?, ?, ?, ?, ?, ?)")) {
            setTripFields(trip, stmt);
        } catch (SQLException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Trip " +
                     "SET tripNumber=?, company_id=?, timeIn=?, timeOut=?, destination=?, origin=?")) {
            setTripFields(trip, stmt);
        } catch (SQLException e) {
            System.out.println("Failed to update data: " + e.getMessage());
        }
        return trip;
    }

    private static void setTripFields(Trip trip, PreparedStatement stmt) {
        try {
            stmt.setLong(1, trip.getNumber());
            stmt.setLong(2, new CompanyDaoImpl().getCompanyId(trip.getCompany()));
            stmt.setString(3, trip.getTimeIn());
            stmt.setString(3, trip.getTimeOut());
            stmt.setString(3, trip.getDestination());
            stmt.setString(3, trip.getOrigin());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to update data: " + e.getMessage());
        }
    }

    @Override
    public void delete(long tripId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Trip WHERE trip_id=?)")) {
            stmt.setLong(1, tripId);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to delete data: " + e.getMessage());
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        List<Trip> trips = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Trip WHERE origin=?)")) {
            getTripResultsIntoList(city, trips, stmt);
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        List<Trip> trips = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Trip WHERE destination=?)")) {
            getTripResultsIntoList(city, trips, stmt);
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return trips;
    }

    private void getTripResultsIntoList(String city, List<Trip> trips, PreparedStatement stmt) {
        try {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip(rs.getLong("tripNumber"),
                        new CompanyDaoImpl().getById(rs.getLong("company_id")),
                        rs.getString("timeIn"), rs.getString("timeOut"),
                        rs.getString("destination"), rs.getString("origin"),
                        new PassengerDaoImpl().getPassengersOfTrip(rs.getLong("tripNumber")));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
    }
}
