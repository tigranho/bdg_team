package jdbclesson.dao;

import jdbclesson.model.Address;

import java.util.Set;

public interface AddressDAO {

    Address getById(long id);

    Set<Address> getAll();

    Address save(Address address);

    Address update(Address address);

    void delete(long addressId);
}