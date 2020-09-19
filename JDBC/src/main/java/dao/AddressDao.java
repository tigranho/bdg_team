package dao;

import pojo.Address;

public interface AddressDao {
    Address getById(long id);

    Address save(Address address);

    long getTripId(Address address);
}
