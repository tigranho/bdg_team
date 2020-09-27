package jdbclesson.implementation;

import jdbclesson.Connect;
import jdbclesson.model.Company;
import jdbclesson.model.Passenger;
import jdbclesson.model.Trip;
import jdbclesson.dao.TripDAO;

import java.sql.*;
import java.util.*;

public class TripImpl implements TripDAO {

    @Override
    public Trip getById(long id) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from trip left join companies c on c.id = trip.company_id where trip_number = ?");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new Trip(resultSet.getInt("trip_number"),
                    new Company(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("found_date")),
                    resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                    resultSet.getString("town_to"), resultSet.getString("town_from"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Set<Trip> getAll() {
        Set<Trip> tripSet = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from trip")) {

            while (resultSet.next()) {
                Trip trip = new Trip(resultSet.getInt("trip_number"), new Company(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("found_date")),
                        resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                        resultSet.getString("town_too"), resultSet.getString("town_from"));

                tripSet.add(trip);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return tripSet;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        Set<Trip> trips = new TreeSet<>();

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from trip " +
                            "left join companies c on c.id = trip.company_id where trip_number order by ? limit ? offset ?");

            preparedStatement.setInt(1, page);
            preparedStatement.setInt(2, perPage);
            preparedStatement.setString(3, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Trip trip = new Trip(resultSet.getInt("trip_number"), new Company(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("found_date")),
                        resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                        resultSet.getString("town_too"), resultSet.getString("town_from"));
                trips.add(trip);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return trips;
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
            throw new RuntimeException(throwables);
        }
        return passenger;
    }

    @Override
    public Trip update(Trip passenger) {

        try (Connection connection = Connect.getConnection()) {
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
            throw new RuntimeException(throwables);
        }

        return passenger;
    }

    @Override
    public void delete(long tripId) {

        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from trip where trip_number = ?");
            preparedStatement.setLong(1, tripId);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) {

        List<Trip> trips = new ArrayList<>();

        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from trip " +
                            "left join companies c on c.id = trip.company_id where town_from = ?");

            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Trip trip = new Trip(resultSet.getInt("trip_number"), new Company(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("found_date")),
                        resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                        resultSet.getString("town_too"), resultSet.getString("town_from"));
                trips.add(trip);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {

        List<Trip> trips = new ArrayList<>();

        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from trip " +
                            "left join companies c on c.id = trip.company_id where town_too = ?");
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Trip trip = new Trip(resultSet.getInt("trip_number"), new Company(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("found_date")),
                        resultSet.getTimestamp("time_in"), resultSet.getTimestamp("time_out"),
                        resultSet.getString("town_too"), resultSet.getString("town_from"));
                trips.add(trip);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return trips;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into passenger_trip(passenger_id, trip_id) values (?, ?)");

            preparedStatement.setObject(1, trip.getTrip_number());
            preparedStatement.setObject(2, passenger.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from passenger_trip where passenger_id = ? and trip_id = ?");

            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, tripNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}