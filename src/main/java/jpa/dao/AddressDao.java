package jpa.dao;

import jpa.entity.Address;

import java.util.Set;

public interface AddressDao {

    Address getById(long id);

    Set<Address> getAll();

    Address save(Address address);

    Address update(Address address);

    void delete(long addressId);
}