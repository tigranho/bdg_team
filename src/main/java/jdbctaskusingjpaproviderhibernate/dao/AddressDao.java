package jdbctaskusingjpaproviderhibernate.dao;

import jdbctaskusingjpaproviderhibernate.entity.Address;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 20.09.2020.
 */
public interface AddressDao {
    Address fetchById(long id) throws SQLException;

    Set<Address> fetchAll() throws SQLException;

    Address save(Address address) throws SQLException;

    Address update(Address address) throws SQLException;

    void delete(long addressId) throws SQLException;
}
