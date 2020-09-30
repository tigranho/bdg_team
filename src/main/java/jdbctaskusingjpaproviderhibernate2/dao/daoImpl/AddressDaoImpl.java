package jdbctaskusingjpaproviderhibernate2.dao.daoImpl;


import jdbctaskusingjpaproviderhibernate2.dao.AddressDao;
import jdbctaskusingjpaproviderhibernate2.entity.Address;

import javax.persistence.*;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 20.09.2020.
 */
public class AddressDaoImpl implements AddressDao {
    @Override
    public Address fetchById(long id) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address address = entityManager.find(Address.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return address;
    }

    @Override
    public Set<Address> fetchAll() throws SQLException {
        Set<Address> addresses = new HashSet<>();
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Address> query =
                entityManager.createQuery("SELECT c FROM Address c", Address.class);
        List<Address> results = query.getResultList();
        addresses.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return addresses;
    }

    @Override
    public Address save(Address address) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return address;
    }

    @Override
    public Address update(Address address) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return address;
    }

    @Override
    public void delete(long addressId) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        Address address = entityManager.find(Address.class, addressId);
        entityManager.getTransaction().begin();
        entityManager.remove(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
    }
}
