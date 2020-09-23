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
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = enfactory.createEntityManager();
        return entityManager.find(Address.class, id);
    }

    @Override
    public Set<Address> getAll() {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = enfactory.createEntityManager();
        return new HashSet<>(entityManager.createQuery("SELECT Address FROM Address", Address.class).getResultList());
    }

    @Override
    public Set<Address> get(int page, int perPage, String sort) {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = enfactory.createEntityManager();
        return null;
    }

    @Override
    public Address save(Address address) {


        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return address;
    }

    @Override
    public Address update(int id, Address address) {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = enfactory.createEntityManager();

        Address prevAddress = entityManager.find(Address.class, id);

        prevAddress.setCity(address.getCity());
        prevAddress.setCountry(address.getCountry());
        return prevAddress;
    }

    @Override
    public void delete(long addressId) {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = enfactory.createEntityManager();
        Address address = entityManager.find(Address.class, addressId);

        entityManager.remove(address);
    }
}
