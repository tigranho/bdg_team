package jdbctaskusingjpaproviderhibernate2.service;



import jdbctaskusingjpaproviderhibernate2.entity.Address;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 20.09.2020.
 */
public interface AddressService {
    Address getById(long id) throws SQLException;
    Set<Address> getAll() throws SQLException;
    Address save(Address address) throws SQLException;
    Address update(Address address) throws SQLException;
    void delete(long addressId)throws SQLException;
}
