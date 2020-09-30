package jpa.implement;

import jpa.GetEntity;
import jpa.entity.Address;
import jpa.dao.AddressDao;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class AddressImpl implements AddressDao {

    @Override
    public Address getById(long id) {

        EntityManager entityManager = GetEntity.getEntityManager();

        return entityManager.find(Address.class, id);
    }

    @Override
    public Set<Address> getAll() {
        EntityManager entityManager = GetEntity.getEntityManager();
        Set<Address> addresses = new HashSet<>();
        Address address = (Address) entityManager.createQuery("select Address from Address ").getResultList();
        addresses.add(address);
        return addresses;
    }

    @Override
    public Address save(Address address) {

        EntityManager entityManager = GetEntity.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        return address;
    }

    @Override
    public Address update(Address address) {
        EntityManager entityManager = GetEntity.getEntityManager();
        Address update_address = entityManager.find(Address.class, address.getId());
        entityManager.getTransaction().begin();
        update_address.setCountry(address.getCountry());
        update_address.setCity(address.getCity());
        entityManager.persist(update_address);
        entityManager.getTransaction().commit();

        return address;
    }

    @Override
    public void delete(long addressId) {

        EntityManager entityManager = GetEntity.getEntityManager();
        Address address = entityManager.find(Address.class, addressId);

        entityManager.getTransaction().begin();
        entityManager.remove(address);
        entityManager.getTransaction().commit();
    }
}