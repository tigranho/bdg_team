package jdbclesson.implementation;

import jdbclesson.Connect;
import jdbclesson.Passenger;
import jdbclesson.Trip;
import jdbclesson.dao.TripDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripI implements TripDAO {

    @Override
    public Trip getById(long id) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from trip where id = ?");
             ResultSet resultSet = preparedStatement.executeQuery()){


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Set<Trip> getAll() {
        Set<Trip> tripSet = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from trip")){

            while (resultSet.next()){
                Trip trip = new Trip(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getTimestamp(3), resultSet.getInt(4), resultSet.getInt(5));

                tripSet.add(trip);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return tripSet;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip passenger) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into trip(id, trip_number, time, address_id, passenger_id) values (?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setInt(2, passenger.getTrip_number());
            preparedStatement.setTimestamp(3, passenger.getTime());
            preparedStatement.setInt(4, passenger.getAddress_id());
            preparedStatement.setInt(5, passenger.getPassenger_id());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return passenger;
    }

    @Override
    public Trip update(Trip passenger) {

        try (Connection connection = Connect.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update trip set id = ?, trip_number = ?, time = ?, address_id = ?, passenger_id = ?");

            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setInt(2, passenger.getTrip_number());
            preparedStatement.setTimestamp(3, passenger.getTime());
            preparedStatement.setInt(4, passenger.getAddress_id());
            preparedStatement.setInt(5, passenger.getPassenger_id());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passenger;
    }

    @Override
    public void delete(long tripId) {

        try (Connection connection = Connect.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from trip where id = ?");
            preparedStatement.setLong(1, tripId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        return null;
    }

    @Override
    public void registerTrip(TripI trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}