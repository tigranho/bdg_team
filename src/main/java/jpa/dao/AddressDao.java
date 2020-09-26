package jpa.dao;

import jpa.entity.Address;

import java.sql.SQLException;
import java.util.Set;

public interface AddressDao {

    Address getById(long id) throws SQLException, ClassNotFoundException;

    Set<Address> getAll();

    Address save(Address address);

    Address update(Address address);

    void delete(long addressId);
}