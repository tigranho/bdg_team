package hibernate.airport_management_system.dao;

import hibernate.airport_management_system.entity.Address;

import javax.persistence.EntityManager;
import java.util.Set;

public interface AddressDao {

    Address getById(long id);
    Set<Address> getAll();
    Set<Address> get(int page, int perPage, String sort);
    Address save(Address address);
    Address update(int id, Address address);
    void delete(long companyId);

}
