package jpa.implement;

import jpa.entity.Address;
import jpa.dao.AddressDao;

import java.sql.SQLException;
import java.util.Set;

public class AddressI implements AddressDao {

    @Override
    public Address getById(long id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Set<Address> getAll() {
        return null;
    }

    @Override
    public Address save(Address address) {
        return null;
    }

    @Override
    public Address update(Address address) {
        return null;
    }

    @Override
    public void delete(long addressId) {

    }
}
