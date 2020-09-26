package jdbclesson.implementation;

import jdbclesson.Connect;
import jdbclesson.model.Company;
import jdbclesson.model.Passenger;
import jdbclesson.model.Trip;
import jdbclesson.dao.TripDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripImpl implements TripDAO {

    @Override
    public Trip getById(long id) {

        try (Connection connection = Connect.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from trip where trip_number = ?");
            ResultSet resultSet = preparedStatement.executeQuery();

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
                Trip trip = new Trip(resultSet.getInt("trip_number"), new Company(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("found_date")),
                        resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                        resultSet.getString("town_too"), resultSet.getString("town_from"));

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
                    "insert into trip(trip_number, company_id, time_in, time_out, town_too, town_from) values (?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, passenger.getTrip_number());
            preparedStatement.setObject(2, passenger.getCompany_id());
            preparedStatement.setTimestamp(3, passenger.getTime_in());
            preparedStatement.setTimestamp(4, passenger.getTime_out());
            preparedStatement.setString(5, passenger.getTown_to());
            preparedStatement.setString(6, passenger.getTown_from());
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
                    "update trip set trip_number = ?, company_id = ?, time_in = ?, time_out = ?, town_too = ?, town_from = ?");

            preparedStatement.setInt(1, passenger.getTrip_number());
            preparedStatement.setObject(2, passenger.getCompany_id());
            preparedStatement.setTimestamp(3, passenger.getTime_in());
            preparedStatement.setTimestamp(4, passenger.getTime_out());
            preparedStatement.setString(5, passenger.getTown_to());
            preparedStatement.setString(6, passenger.getTown_from());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passenger;
    }

    @Override
    public void delete(long tripId) {

        try (Connection connection = Connect.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from trip where trip_number = ?");
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
    public void registerTrip(TripImpl trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}