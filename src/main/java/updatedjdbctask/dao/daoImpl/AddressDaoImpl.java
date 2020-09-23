package updatedjdbctask.dao.daoImpl;


import updatedjdbctask.dao.AddressDao;
import updatedjdbctask.model.Address;
import updatedjdbctask.util.DatabaseConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 20.09.2020.
 */
public class AddressDaoImpl implements AddressDao {
    @Override
    public Address fetchById(long id) throws SQLException {
        Address address = new Address();
        String q = "select city,country from address where address_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            s.setLong(1, id);
            try (ResultSet resultSet = s.executeQuery()) {
                if (resultSet.next()) {
                    address.setCity(resultSet.getString(1));
                    address.setCountry(resultSet.getString(2));
                    address.setAddressId(id);
                }
            }
        }
        return address;
    }

    @Override
    public Set<Address> fetchAll() throws SQLException {
        Set<Address> addresses = new HashSet<>();
        String q = "select * from address;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    Address address = new Address();
                    address = getFromResultSet(resultSet);
                    addresses.add(address);
                }
            }
        }
        return addresses;
    }

    @Override
    public Address save(Address address) throws SQLException {
        String query = String.format("insert into address (country, city) values('%s', '%s');",
                address.getCountry(), address.getCity());
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    address.setAddressId(resultSet.getLong(1));
                    return address;
                }
            }
        }
        return null;
    }

    @Override
    public Address update(Address address) throws SQLException {
        String query = "UPDATE address set country = ?,  city = ? WHERE  address_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setLong(3, address.getAddressId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return address;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(long addressId) throws SQLException {
        String query = "delete form address where address_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(query)) {
            p.setLong(1, addressId);
            p.execute();
        }
    }

    private static Address getFromResultSet(ResultSet r) throws SQLException {
        Address address = new Address();
        address.setAddressId(r.getLong(1));
        address.setCountry(r.getString(2));
        address.setCity(r.getString(3));
        return address;
    }
}
