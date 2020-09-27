package jdbclesson.implementation;

import jdbclesson.Connect;
import jdbclesson.model.Address;
import jdbclesson.model.Passenger;
import jdbclesson.dao.PassengerDAO;

import java.sql.*;
import java.util.*;

public class PassengerImpl implements PassengerDAO {

    @Override
    public Passenger getById(long id) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from passengers join address a on a.id = passengers.address_id where passengers.id = ?;");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("phone"), new Address(resultSet.getInt("id"),
                    resultSet.getString("country"), resultSet.getString("city")));

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Set<Passenger> getAll() {
        Set<Passenger> passengerSet = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from passengers")) {

            while (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("phone"), new Address(resultSet.getInt("id"),
                        resultSet.getString("country"), resultSet.getString("city")));

                passengerSet.add(passenger);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return passengerSet;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        Set<Passenger> passengers = new TreeSet<>();

        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from passengers left join address a on a.id = passengers.address_id order by ? limit ? offset ?");

            preparedStatement.setInt(page, 1);
            preparedStatement.setInt(perPage, 2);
            preparedStatement.setString(3, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Passenger passenger = new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("phone"), new Address(resultSet.getInt("id"),
                        resultSet.getString("country"), resultSet.getString("city")));

                passengers.add(passenger);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) {

        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into passengers(" +
                    "id, name, phone, address_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getPhone());
            preparedStatement.setObject(4, passenger.getAddress());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update passengers set id = ?, name = ?, phone = ?, address_id = ?")) {

            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getPhone());
            preparedStatement.setObject(4, passenger.getAddress());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return passenger;
    }

    @Override
    public void delete(long passengerId) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from passengers where id = ?")) {

            preparedStatement.setLong(1, passengerId);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {

        List<Passenger> passengers = new ArrayList<>();

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from passengers\n" +
                            "    inner join passenger_trip pt on passengers.id = pt.passenger_id\n" +
                            "    inner join passenger_trip p on passengers.id = p.passenger_id\n" +
                            "    inner join trip t on t.trip_number = p.trip_id\n" +
                            "    left join address a on a.id = passengers.address_id where trip_number = ?;");

            preparedStatement.setLong(1, tripNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            Passenger passenger = new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("phone"), new Address(resultSet.getInt("id"),
                    resultSet.getString("country"), resultSet.getString("city")));
            passengers.add(passenger);

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return passengers;
    }
}