package jdbclesson.implementation;

import jdbclesson.model.Address;
import jdbclesson.Connect;
import jdbclesson.dao.AddressDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AddressImpl implements AddressDAO {

    @Override
    public Address getById(long id) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Address(resultSet.getInt("id"),
                        resultSet.getString("country"), resultSet.getString("city"));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return null;
    }

    @Override
    public Set<Address> getAll() {
        Set<Address> addressSet = new HashSet<>();

        try (Connection connection = Connect.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from address");

            while (resultSet.next()) {
                Address address = new Address(resultSet.getInt("id"),
                        resultSet.getString("country"), resultSet.getString("city"));
                addressSet.add(address);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return addressSet;
    }

    @Override
    public Address save(Address address) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into address(id, country, city) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(3, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return address;
    }

    @Override
    public Address update(Address address) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update address set id = ?, country = ?, city = ?")) {

            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return address;
    }

    @Override
    public void delete(long addressId) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from address where id = ?")) {
            preparedStatement.setLong(1, addressId);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}