package jdbclesson.implementation;

import jdbclesson.Connect;
import jdbclesson.dao.TripPassengerDAO;
import jdbclesson.model.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TripPassengerImpl implements TripPassengerDAO {
    @Override
    public TripPassenger getById(long id) {
        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from passenger_trip " +
                            "left join passengers p on p.id = passenger_trip.passenger_id " +
                            "left join address a on a.id = p.address_id where passenger_id = ?;");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new TripPassenger(new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("phone"), new Address(resultSet.getInt("id"),
                    resultSet.getString("country"), resultSet.getString("city"))),
                    new Trip(resultSet.getInt("trip_number"), new Company(resultSet.getInt("id"),
                            resultSet.getString("name"), resultSet.getString("found_date")),
                            resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                            resultSet.getString("town_to"), resultSet.getString("town_from")));

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Set<TripPassenger> getAll() {
        Set<TripPassenger> tripPassengers = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from passenger_trip")) {

            while (resultSet.next()) {
                TripPassenger tripPassenger = new TripPassenger(new Passenger(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("phone"),
                        new Address(resultSet.getInt("id"), resultSet.getString("country"),
                                resultSet.getString("city"))),
                        new Trip(resultSet.getInt("trip_number"),
                                new Company(resultSet.getInt("id"),
                                        resultSet.getString("name"), resultSet.getString("found_date")),
                                resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                                resultSet.getString("town_to"), resultSet.getString("town_from")));

                tripPassengers.add(tripPassenger);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return tripPassengers;
    }

    @Override
    public TripPassenger save(TripPassenger address) {
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into passenger_trip(" +
                    "passenger_id, trip_id) VALUES (?, ?)");
            preparedStatement.setObject(1, address.getPassenger_id());
            preparedStatement.setObject(2, address.getTrip_id());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return address;
    }

    @Override
    public TripPassenger update(TripPassenger address) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update passenger_trip set passenger_id = ?, trip_id = ?")) {

            preparedStatement.setObject(1, address.getPassenger_id());
            preparedStatement.setObject(2, address.getTrip_id());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return address;
    }

    @Override
    public void delete(long addressId) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from passenger_trip where passenger_id = ?")) {

            preparedStatement.setLong(1, addressId);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}