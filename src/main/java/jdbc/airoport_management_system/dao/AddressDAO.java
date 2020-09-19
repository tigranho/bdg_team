package jdbc.airoport_management_system.dao;

import jdbc.airoport_management_system.entity.Address;

import java.util.List;
import java.util.Set;

public interface AddressDAO {
    Address getById(long id);
    Set<Address> getAll();
    Address save(Address address);
    Address update(Address address);
    void delete(long addressId);
    List<Address> getAddressesFrom(String city);
    List<Address> getTripsTo(String city);

}
