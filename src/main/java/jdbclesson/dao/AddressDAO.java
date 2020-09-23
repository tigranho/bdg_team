package jdbclesson.dao;

import jdbclesson.Address;

import java.sql.SQLException;
import java.util.Set;

public interface AddressDAO {

    Address getById(long id) throws SQLException, ClassNotFoundException;

    Set<Address> getAll();

    Address save(Address address);

    Address update(Address address);

    void delete(long addressId);
}