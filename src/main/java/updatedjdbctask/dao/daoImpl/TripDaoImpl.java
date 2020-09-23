package updatedjdbctask.dao.daoImpl;


import updatedjdbctask.dao.TripDao;
import updatedjdbctask.model.Trip;
import updatedjdbctask.util.DatabaseConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public class TripDaoImpl implements TripDao {

    @Override
    public Trip fetchById(long id) throws SQLException {
        Trip t = new Trip();
        String q = "Select *  from trip where trip_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(q)) {
            p.setLong(1, id);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    t.setTripId(r.getLong(1));
                    t.setTripNumber(r.getLong(2));
                    t.setCompanyId(r.getLong(3));
                    t.setTimeIn(r.getString(4));
                    t.setTimeOut(r.getString(5));
                    t.setTownTo(r.getString(6));
                    t.setTownFrom(r.getString(7));
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public Set<Trip> fetchAll() throws SQLException {
        String q = "select * from trip;";
        Set<Trip> trips = new HashSet<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(q)) {
            try (ResultSet resultSet = p.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = new Trip();
                    trip = getFromResultSet(resultSet);
                    trips.add(trip);
                }
            }
        }
        return trips;
    }

    @Override
    public Set<Trip> fetch(int page, int perPage, String sort) throws SQLException {
        Set<Trip> trips = new HashSet<>();
        int startPoint = perPage * page - perPage;
        String q = "Select * from trip order by ? ASC Limit ?, ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            s.setString(1, sort);
            s.setLong(2, startPoint);
            s.setLong(3, perPage);
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = new Trip();
                    trip = getFromResultSet(resultSet);
                    trips.add(trip);
                }
            }
        }
        return trips;
    }

    @Override
    public Trip save(Trip trip) throws SQLException {
        String q = "Insert into trip (trip_number, company_id, time_in, time_out, town_to, town_from) " +
                "values(?, ?, ?, ?, ?, ?);";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, trip.getTripNumber());
            preparedStatement.setLong(2, trip.getCompanyId());
            preparedStatement.setString(3, trip.getTimeIn());
            preparedStatement.setString(4, trip.getTimeOut());
            preparedStatement.setString(5, trip.getTownTo());
            preparedStatement.setString(6, trip.getTownFrom());
            preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    trip.setTripId(resultSet.getLong(1));
                }
            }
        }
        return trip;
    }

    @Override
    public Trip update(Trip trip) throws SQLException {
        String query = "UPDATE trip set trip_number = ?, company_id = ?, time_in=?, time_out=?, town_to=?, town_from=? WHERE  trip_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, trip.getTripNumber());
            preparedStatement.setLong(2, trip.getCompanyId());
            preparedStatement.setString(3, trip.getTimeIn());
            preparedStatement.setString(4, trip.getTimeOut());
            preparedStatement.setString(5, trip.getTownTo());
            preparedStatement.setString(6, trip.getTownFrom());
            preparedStatement.setLong(7, trip.getTripId());
            preparedStatement.executeUpdate(query);
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return trip;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(long tripId) throws SQLException {
        String q = "delete from trip where trip_id=?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(q)) {
            p.setLong(1, tripId);
            p.execute();
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) throws SQLException {
        String q = "select * from trip where town_from = ?;";
        List<Trip> trips = new LinkedList<>();
        Trip trip = new Trip();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(q)) {
            p.setString(1, city);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    trip = getFromResultSet(r);
                    trips.add(trip);
                }
            }
        }
        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) throws SQLException {
        String q = "select * from trip where town_to = ?;";
        List<Trip> trips = new LinkedList<>();
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(q)) {
            p.setString(1, city);
            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    Trip trip = new Trip();
                    trip = getFromResultSet(r);
                    trips.add(trip);
                }
            }
        }
        return trips;
    }

    private static Trip getFromResultSet(ResultSet r) throws SQLException {
        Trip trip = new Trip();
        trip.setTripId(r.getLong(1));
        trip.setTripNumber(r.getLong(2));
        trip.setCompanyId(r.getLong(3));
        trip.setTimeIn(r.getString(4));
        trip.setTimeOut(r.getString(5));
        trip.setTownTo(r.getString(6));
        trip.setTownFrom(r.getString(7));
        return trip;
    }
}
