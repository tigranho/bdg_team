package lesson10.airport_management_system.dao.impl;

import lesson10.airport_management_system.dao.TripDAO;
import lesson10.airport_management_system.model.Address;
import lesson10.airport_management_system.model.Company;
import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;
import lesson10.airport_management_system.util.DBConnector;

import java.sql.*;
import java.util.*;

public class TripDAOImpl implements TripDAO {

    @Override
    public Optional<Trip> getById(long id) {
        final String query = "select t.id as trip_id, name, founding_date, time_in, time_out," +
                " to_city, from_city, c.id as company_id from trip t" +
                " left join company c on t.company_id = c.id where t.id = ?";
        Trip trip = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Company company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("company_id"));
                trip = new Trip(company, rs.getTimestamp("time_in").toLocalDateTime(),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getString("from_city"), rs.getString("to_city"));
                trip.setId(rs.getLong("trip_id"));
            }
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
        return Optional.ofNullable(trip);
    }

    @Override
    public Set<Trip> getAll() {
        final String query = "select t.id as trip_id, name, founding_date, time_in, time_out, " +
                " to_city, from_city, c.id as company_id from trip t" +
                " left join company c on t.company_id = c.id";
        Set<Trip> trips = null;
        Trip trip = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (trips == null) trips = new HashSet<>();
                Company company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("company_id"));
                trip = new Trip(company, rs.getTimestamp("time_in").toLocalDateTime(),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getString("from_city"), rs.getString("to_city"));
                trip.setId(rs.getLong("trip_id"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.err.println("failed to fetch data: message: " + e.getMessage());
        }
        return trips != null ? trips : Collections.emptySet();
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        final String query = "select t.id as trip_id, name, founding_date, time_in, time_out," +
                " to_city, from_city, c.id as company_id from trip t left join company c on t.company_id = c.id" +
                " order by ? Limit ? offset ?";
        Set<Trip> trips = null;
        Trip trip = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, sort);
            stmt.setInt(2, page);
            stmt.setInt(3, perPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (trips == null) trips = new HashSet<>();
                Company company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("company_id"));
                trip = new Trip(company, rs.getTimestamp("time_in").toLocalDateTime(),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getString("from_city"), rs.getString("to_city"));
                trip.setId(rs.getLong("trip_id"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.err.println("failed to fetch data: message: " + e.getMessage());
        }
        return trips;
    }

    @Override
    public Optional<Trip> save(Trip trip) {
        final String query = "insert into trip (company_id, time_in, time_out, from_city, to_city) values (?, ?, ?, ?, ?)";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, trip.getCompany().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(trip.getTimeIn()));
            stmt.setTimestamp(3, Timestamp.valueOf(trip.getTimeOut()));
            stmt.setString(4, trip.getFromCity());
            stmt.setString(5, trip.getToCity());
            ResultSet genKey = null;
            if (stmt.executeUpdate() == 1) {
                genKey = stmt.getGeneratedKeys();
                if (genKey.next())
                    trip.setId(genKey.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
        return Optional.of(trip);
    }

    @Override
    public boolean saveAll(List<String> trips) {
        final StringBuilder query = new StringBuilder("insert into trip(company_id, time_in, time_out, from_city, to_city)" +
                " values ");
        try (Connection con = DBConnector.getConnection();
             Statement stmt = con.createStatement()) {
            con.setAutoCommit(false);
            for (String line : trips) {
                String[] data = line.split(",");
                query.append("('").append(data[0].trim()).append("', '").append(data[1].trim())
                        .append("', '").append(data[2].trim()).append("', '").append(data[3].trim())
                        .append("', '").append(data[4].trim()).append("'),");
            }
            query.replace(query.length() - 1, query.length(), ";");
            int count = stmt.executeUpdate(query.toString(), Statement.RETURN_GENERATED_KEYS);
            System.out.println(count);
            con.commit();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
        return true;
    }

    @Override
    public Set<Passenger> findPassengersByTripId(long tripId) {
        final String query = "select passenger.id, name, phone, country, city from passenger inner join passengers_trips pt" +
                " on passenger.id = pt.passenger_id and pt.trip_id = ?" +
                "  left join address a on passenger.id = a.id";
        Set<Passenger> tripPassengers = null;
        Passenger passenger = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, tripId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (tripPassengers == null) tripPassengers = new HashSet<>();
                passenger = new Passenger(rs.getString("name"), rs.getString("phone"),
                        new Address(rs.getString("country"), rs.getString("city")));
                passenger.setId(rs.getLong("id"));
                tripPassengers.add(passenger);
            }
        } catch (SQLException e) {
            System.err.println("failed to fetch data: message: " + e.getMessage());
        }
        return tripPassengers != null ? tripPassengers : Collections.emptySet();
    }

    @Override
    public Optional<Trip> update(Trip trip) {
        final String query = "update trip set company_id = ?, time_in = ?," +
                " time_out = ?, from_city = ?, to_city = ? where id = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, trip.getCompany().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(trip.getTimeIn()));
            stmt.setTimestamp(3, Timestamp.valueOf(trip.getTimeOut()));
            stmt.setString(4, trip.getFromCity());
            stmt.setString(5, trip.getToCity());
            stmt.setLong(6, trip.getId());
            if (stmt.executeUpdate() == 1)
                System.out.printf("trip by id:%d successfully updated%n", trip.getId());
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return Optional.of(trip);
    }

    @Override
    public void delete(long tripId) {
        final String query = "delete from trip where id = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, tripId);
            if (stmt.executeUpdate() == 1)
                System.out.printf("trip by id:%d successfully deleted%n", tripId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Set<Trip> findTripsByPassengerId(long passengerId) {
        final String query = "select trip.id as trip_id, c.id as company_id, name, founding_date, time_in, time_out," +
                " from_city, to_city from trip inner join passengers_trips pt" +
                " on trip.id = pt.trip_id and pt.passenger_id = ? " +
                "left join company c on trip.id = c.id";
        Set<Trip> passengerTrips = null;
        Trip trip = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, passengerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (passengerTrips == null) passengerTrips = new HashSet<>();
                Company company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("company_id"));
                trip = new Trip(company, rs.getTimestamp("time_in").toLocalDateTime(),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getString("from_city"), rs.getString("to_city"));
                trip.setId(rs.getLong("trip_id"));
                passengerTrips.add(trip);
            }
        } catch (SQLException e) {
            System.err.println("failed to fetch data: message: " + e.getMessage());
        }
        return passengerTrips != null ? passengerTrips : Collections.emptySet();
    }

    @Override
    public List<Trip> getTripsByFromCity(String fromCity) {
        final String query = "select t.id as trip_id, name, founding_date, time_in, time_out," +
                " to_city, from_city, c.id as company_id from trip t left join company c" +
                " on t.company_id = c.id where from_city = ?";
        return getTripsByCity(query, fromCity);
    }

    @Override
    public List<Trip> getTripsByToCity(String toCity) {
        final String query = "select t.id as trip_id, name, founding_date, time_in, time_out," +
                " to_city, from_city, c.id as company_id from trip t left join company c" +
                " on t.company_id = c.id where to_city = ?";
        return getTripsByCity(query, toCity);
    }


    private List<Trip> getTripsByCity(String query, String city) {
        List<Trip> trips = null;
        Trip trip = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (trips == null) trips = new LinkedList<>();
                Company company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("company_id"));
                trip = new Trip(company, rs.getTimestamp("time_in").toLocalDateTime(),
                        rs.getTimestamp("time_out").toLocalDateTime(),
                        rs.getString("from_city"), rs.getString("to_city"));
                trip.setId(rs.getLong("trip_id"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return trips != null ? trips : Collections.emptyList();
    }
}
