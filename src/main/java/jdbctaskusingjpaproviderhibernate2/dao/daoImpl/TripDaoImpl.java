package jdbctaskusingjpaproviderhibernate2.dao.daoImpl;



import jdbctaskusingjpaproviderhibernate2.dao.TripDao;
import jdbctaskusingjpaproviderhibernate2.entity.Trip;

import javax.persistence.*;
import java.sql.*;
import java.util.HashSet;
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
//        trip.getPassengers();
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
        int startPoint = perPage*(page - 1);
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
        entityManager.merge(trip);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return trip;
    }

    @Override
    public void delete(long tripId) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        Trip trip = entityManager.find(Trip.class, tripId);
        entityManager.getTransaction().begin();
        entityManager.remove(trip);
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
