package com.bdg.hibernate_jpa.dao;
import com.bdg.hibernate_jpa.entity.Address;

import java.util.Set;

public interface AddressDao {
    Address getById(long id);
    Set<Address> getAll();
    Set<Address> get(int page, int perPage, String sort);
    Address save(Address address);
    Address update(int id, Address address);
    void delete(long companyId);

}