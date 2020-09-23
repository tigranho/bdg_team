package jdbclesson.implementation;

import jdbclesson.Address;
import jdbclesson.Connect;
import jdbclesson.Passenger;
import jdbclesson.dao.PassengerDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerI implements PassengerDAO {

    @Override
    public Passenger getById(long id) throws SQLException, ClassNotFoundException {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from passengers where id = ?");
             ResultSet resultSet = preparedStatement.executeQuery()){

            return new Passenger(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4));
        }
    }

    @Override
    public Set<Passenger> getAll() {
        Set<Passenger> passengerSet = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from passengers")){

            while (resultSet.next()){
                Passenger passenger = new Passenger(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));

                passengerSet.add(passenger);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passengerSet;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Passenger save(Passenger passenger) {

        try (Connection connection = Connect.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into passengers(" +
                     "id, name, phone, address_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, String.valueOf(passenger.getId()));
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getPhone());
            preparedStatement.setString(4, String.valueOf(passenger.getAddress()));
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {

        try(Connection connection = Connect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update passengers set id = ?, name = ?, phone = ?, address_id = ?")){

            preparedStatement.setString(1, String.valueOf(passenger.getId()));
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getPhone());
            preparedStatement.setString(4, String.valueOf(passenger.getAddress()));
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passenger;
    }

    @Override
    public void delete(long passengerId) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from passengers where id = ?")){

            preparedStatement.setString(1, String.valueOf(passengerId));
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(TripI trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}