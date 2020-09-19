package airportamangmentsystems.repository.implementation;


import airportamangmentsystems.model.Trip;
import airportamangmentsystems.repository.TripRepo;

import java.sql.*;
import java.util.*;

public class TripRepoImplement extends UseSqlConnection implements TripRepo {

    @Override
    public Trip getById(long id) throws SQLException {
        String query = "select * from trip where id = ?";
        Trip trip = null;
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                trip = createFromResultSetTrip(resultSet);
            }
        }
        return trip;
    }

    @Override
    public Set<Trip> getAll() throws SQLException {
        String query = "select * from trip";
        Set<Trip> set = new HashSet<>();
        try (Connection connection = this.dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                set.add(createFromResultSetTrip(resultSet));
            }
        }
        return set;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip passenger) throws SQLException {
        String query = "insert into trip(number,companyId,townTo,townFrom,timeIn,timeOut)";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, passenger.getNumber());
            preparedStatement.setInt(2, passenger.getCompanyId());
            preparedStatement.setString(3, passenger.getTownTo());
            preparedStatement.setString(4, passenger.getTownFrom());
            preparedStatement.setString(5, passenger.getTimeIn());
            preparedStatement.setString(6, passenger.getTimeOut());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                resultSet.getInt(1);
            }
        }
        return passenger;
    }

    @Override
    public Trip update(Trip passenger) throws SQLException {
        String query = "update trip(number,companyId,townTo,townFrom,timeIn,timeOut)";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate(query);
            preparedStatement.setInt(1, passenger.getCompanyId());
            preparedStatement.setInt(2, passenger.getNumber());
            preparedStatement.setString(3, passenger.getTownFrom());
            preparedStatement.setString(4, passenger.getTownTo());
            preparedStatement.setString(5, passenger.getTimeIn());
            preparedStatement.setString(6, passenger.getTimeOut());
        }
        return passenger;
    }


    @Override
    public void delete(long tripId) throws SQLException {
        String query = "delete from trip where id = ?";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate(query);
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) throws SQLException {
        String query = "select townTo from trip where city = ?;";
        List<Trip> list = new ArrayList<>();
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(createFromResultSetTrip(resultSet));
            }
        }
        return list;
    }

    @Override
    public List<Trip> getTripsTo(String city) throws SQLException {
        String query = "select townFrom from city where city = ?;";
        List<Trip> list = new ArrayList<>();
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                list.add(createFromResultSetTrip(resultSet));
            }
        }
        return list;
    }


    public Trip createFromResultSetTrip(ResultSet resultSet) throws SQLException {
        Trip trip = new Trip();
        trip.setNumber(resultSet.getInt("number"));
        trip.setTimeOut(resultSet.getString("timeOut"));
        trip.setTimeIn(resultSet.getString("timeIn"));
        trip.setCompanyId(resultSet.getInt("companyId"));
        trip.setTownFrom(resultSet.getString("townForm"));
        trip.setTownTo(resultSet.getString("townTo"));
        return trip;
    }
}
