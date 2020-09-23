package jdbctaskusingjpaproviderhibernate.dao.daoImpl;


import jdbctaskusingjpaproviderhibernate.dao.TripDao;
import jdbctaskusingjpaproviderhibernate.entity.Trip;

import javax.persistence.*;
import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public class TripDaoImpl implements TripDao {

    @Override
    public Trip fetchById(long id) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Trip trip = entityManager.find(Trip.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return trip;
    }

    @Override
    public Set<Trip> fetchAll() throws SQLException {
        Set<Trip> trips = new HashSet<>();
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Trip> query =
                entityManager.createQuery("SELECT c FROM Trip c", Trip.class);
        List<Trip> results = query.getResultList();
        trips.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return trips;
    }

    @Override
    public Set<Trip> fetch(int page, int perPage, String sort) throws SQLException {
        Set<Trip> trips = new HashSet<>();
        int startPoint = perPage * page - perPage;
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Trip> query =
                entityManager.createQuery("Select p from Trip as p order by :n ASC", Trip.class).
                        setParameter("n", sort).
                        setFirstResult(startPoint).setMaxResults(perPage);
        List<Trip> results = query.getResultList();
        trips.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return trips;
    }

    @Override
    public Trip save(Trip trip) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(trip);
        Long id = trip.getTripId();
        entityManager.getTransaction().commit();
        trip.setTripId(id);
        entityManager.close();
        entityManagerFactory.close();
        return trip;
    }

    @Override
    public Trip update(Trip trip) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(
                "UPDATE Trip as t SET t.tripNumber=:tn,  t.timeIn=:ti, t.timeOut=:to, t.townTo=:tto," +
                        "t.townFrom=:tf WHERE t.tripId=:id");
        int updateCount = query.setParameter("tn", trip.getTripNumber())
                .setParameter("ti", trip.getTimeIn())
                .setParameter("to", trip.getTimeOut())
                .setParameter("tto", trip.getTownTo())
                .setParameter("tf", trip.getTownFrom())
                .setParameter("id", trip.getTripId()).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return trip;
    }

    @Override
    public void delete(long tripId) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(
                "delete from Trip as p WHERE p.tripId=:id");
        int updateCount = query
                .setParameter("id", tripId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
    }

    @Override
    public List<Trip> getTripsFrom(String city) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        List<Trip> list = entityManager.createQuery("select t from Trip  as t where t.townFrom=:t").
                setParameter("t", city).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return list;
    }

    @Override
    public List<Trip> getTripsTo(String city) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        List<Trip> list = entityManager.createQuery("select t from Trip  as t where t.townTo=:t").
                setParameter("t", city).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return list;
    }
}
