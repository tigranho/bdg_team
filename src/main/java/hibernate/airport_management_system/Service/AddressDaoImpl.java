package hibernate.airport_management_system.Service;

import hibernate.airport_management_system.dao.AddressDao;
import hibernate.airport_management_system.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class AddressDaoImpl implements AddressDao {

    EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
    EntityManager entityManager = enfactory.createEntityManager();

    @Override
    public Address getById(long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public Set<Address> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT Address FROM Address", Address.class).getResultList());
    }

    @Override
    public Set<Address> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public Address update(int id, Address address) {

        Address prevAddress = entityManager.find(Address.class, id);

        prevAddress.setCity(address.getCity());
        prevAddress.setCountry(address.getCountry());
        return prevAddress;
    }

    @Override
    public void delete(long addressId) {
        Address address = entityManager.find(Address.class, addressId);

        entityManager.remove(address);
    }
}
