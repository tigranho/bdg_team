package airportamangmentsystems.repository.implementation;


import airportamangmentsystems.model.Passenger;
import airportamangmentsystems.model.Trip;
import airportamangmentsystems.repository.PassengerRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerRepoImplement extends UseSqlConnection implements PassengerRepo {

    @Override
    public Passenger getById(long id) throws SQLException {
        String query = "select * from companies where id = ?;";
        Passenger passenger = null;
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                passenger = createResultSetFromPassenger(resultSet);
            }
        }
        return passenger;
    }


    @Override
    public Set<Passenger> getAll() throws SQLException {
        Set<Passenger> set = new HashSet<>();
        String query = "select * from passengers";
        try (Connection connection = this.dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                set.add(createResultSetFromPassenger(resultSet));
            }
        }
        return set;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) throws SQLException {

        return null;
    }

    @Override
    public Passenger save(Passenger passenger) throws SQLException {
        String query = "insert into passengers(name,phone,city,country)values(?,?,?)";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getCity());
            preparedStatement.setString(3, passenger.getPhone());
            preparedStatement.setString(4, passenger.getCountry());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    passenger.setId(resultSet.getInt(1));
                }
            }
        }
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) throws SQLException {
        String query = "update companies set(name,phone,country,city)";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate(query);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getPhone());
            preparedStatement.setString(3, passenger.getCity());
            preparedStatement.setString(4, passenger.getCountry());
        }
        return passenger;
    }


    @Override
    public void delete(long passengerId) throws SQLException {
        String query = "delete from companies where id = ?;";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate(query);
        }
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException {
        List<Passenger> list = new ArrayList<>();
        Passenger passenger = new Passenger();
        String query = "select passengers.id as id,passangers.name as name," +
                "passengers.phone as phone,passengers.country as country," +
                "passengers.city as city from passengers join passengertrip on " +
                "passengers.id = passengertrip.passengerId join trip on " +
                "trip.id=passengertrip.tripId where tripNumber = ?;";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, tripNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    passenger = createResultSetFromPassenger(resultSet);
                    list.add(passenger);
                }
            }
        }
        return list;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) throws SQLException {
        String query = "insert  into passengertrip(tripId,passengerId)values(?,?); ";

        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trip.getId());
            preparedStatement.setInt(2, passenger.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) throws SQLException {
        String query = "delete from passengertrip where passengerId = ? and" +
                "tripId in (select id from trip where tripNumber = ?);";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, tripNumber);
            preparedStatement.execute();
        }
    }

    public Passenger createResultSetFromPassenger(ResultSet resultSet) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setId(resultSet.getInt("id"));
        passenger.setName(resultSet.getString("name"));
        passenger.setCity(resultSet.getString("city"));
        passenger.setPhone(resultSet.getString("phone"));
        passenger.setCountry(resultSet.getString("country"));
        return passenger;
    }
}
