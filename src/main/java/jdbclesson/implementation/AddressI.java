package jdbclesson.implementation;

import jdbclesson.Address;
import jdbclesson.Connect;
import jdbclesson.dao.AddressDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AddressI implements AddressDAO {

    @Override
    public Address getById(long id) throws SQLException, ClassNotFoundException {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from passengers where id = ?");
             ResultSet resultSet = preparedStatement.executeQuery()){

            return new Address(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }
    }

    @Override
    public Set<Address> getAll() {
        Set<Address> addressSet = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from address")){

            while (resultSet.next()){
                Address address = new Address(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                addressSet.add(address);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return addressSet;
    }

    @Override
    public Address save(Address address) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into address(id, country, city) VALUES (?, ?, ?)")){

            preparedStatement.setString(1, String.valueOf(address.getId()));
            preparedStatement.setString(3, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return address;
    }

    @Override
    public Address update(Address address) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update address set id = ?, country = ?, city = ?")){

            preparedStatement.setString(1, String.valueOf(address.getId()));
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return address;
    }

    @Override
    public void delete(long addressId) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from address where id = ?")){
            preparedStatement.setString(1, String.valueOf(addressId));
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}