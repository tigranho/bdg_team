package updatedjdbctask.dao.daoImpl;


import updatedjdbctask.dao.PassengerDao;
import updatedjdbctask.model.Passenger;
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
public class PassengerDaoImpl implements PassengerDao {
    @Override
    public Passenger fetchById(long id) throws SQLException {
        Passenger passenger = new Passenger();
        String q = "select name, phone, address_id from passenger where passenger_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            s.setLong(1, id);
            try (ResultSet resultSet = s.executeQuery()) {
                if (resultSet.next()) {
                    passenger.setName(resultSet.getString(1));
                    passenger.setPhone(resultSet.getString(2));
                    passenger.setAddressId(resultSet.getLong(3));
                    passenger.setPassengerId(id);
                    return passenger;
                }
            }
        }
        return null;
    }

    @Override
    public Set<Passenger> fetchAll() throws SQLException {
        Set<Passenger> passengers = new HashSet<>();
        String q = "select * from passenger;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    Passenger passenger = new Passenger();
                    passenger = getFromResultSet(resultSet);
                    passengers.add(passenger);
                }
            }
        }
        return passengers;
    }

    @Override
    public Set<Passenger> fetch(int page, int perPage, String sort) throws SQLException {
        Set<Passenger> passengers = new HashSet<>();
        int startPoint = perPage * page - perPage;
        String q = "Select * from passenger order by ? ASC Limit ?, ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            s.setString(1, sort);
            s.setLong(2, startPoint);
            s.setLong(3, perPage);
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    Passenger passenger = new Passenger();
                    passenger = getFromResultSet(resultSet);
                    passengers.add(passenger);
                }
            }
        }
        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) throws SQLException {
        String query = String.format("insert into passenger (name, phone, address_id) values('%s','%s', '%s');",
                passenger.getName(), passenger.getPhone(), passenger.getAddressId());
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    passenger.setPassengerId(resultSet.getLong(1));
                    return passenger;
                }
            }
        }
        return null;
    }

    @Override
    public Passenger update(Passenger passenger) throws SQLException {
        String query = "update passenger name=?, phone=?, address_id = ? WHERE  passenger_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getPhone());
            preparedStatement.setLong(3, passenger.getAddressId());
            preparedStatement.setLong(4, passenger.getPassengerId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return passenger;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(long passengerId) throws SQLException {
        String query = "delete form passenger where passenger_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(query)) {
            p.setLong(1, passengerId);
            p.execute();
        }
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException {
        List<Passenger> list = new LinkedList<>();
        Passenger p = new Passenger();
        String q = "Select passenger_id as pid, name, phone, address_id from passenger join passenger_trip as pt " +
                "on passenger.pid = pt.passenger_id where trip_id in (Select trip_id from trip where trip_number = ?);";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(q)) {
            preparedStatement.setLong(1, tripNumber);
            try (ResultSet r = preparedStatement.executeQuery()) {
                while (r.next()) {
                    p.setPassengerId(r.getLong(1));
                    p.setName(r.getString(2));
                    p.setPhone(r.getString(3));
                    p.setAddressId(r.getLong(4));
                    list.add(p);
                }
            }
        }
        return list;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) throws SQLException {
        String q = "insert into passenger_trip (passenegr_id, trip_id) values(?, ?);";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(q)) {
            preparedStatement.setLong(1, passenger.getPassengerId());
            preparedStatement.setLong(2, trip.getTripId());
            preparedStatement.execute();
        }
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) throws SQLException {
        String q = "DELETE from passenger_trip where passenger_id=? and " +
                "trip_id in (Select trip_id from trip where trip_number = ?);";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(q)) {
            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, tripNumber);
            preparedStatement.execute();
        }
    }

    private static Passenger getFromResultSet(ResultSet r) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(r.getLong(1));
        passenger.setName(r.getString(2));
        passenger.setPhone(r.getString(3));
        passenger.setAddressId(r.getLong(4));
        return passenger;
    }
}
