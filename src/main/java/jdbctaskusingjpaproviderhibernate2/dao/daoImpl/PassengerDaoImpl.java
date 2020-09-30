package jdbctaskusingjpaproviderhibernate2.dao.daoImpl;


import jdbctaskusingjpaproviderhibernate2.dao.PassengerDao;
import jdbctaskusingjpaproviderhibernate2.entity.Passenger;
import jdbctaskusingjpaproviderhibernate2.entity.Trip;

import javax.persistence.*;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public class PassengerDaoImpl implements PassengerDao {
    @Override
    public Passenger fetchById(long id) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Passenger passenger = entityManager.find(Passenger.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return passenger;
    }

    @Override
    public Set<Passenger> fetchAll() throws SQLException {
        Set<Passenger> passengers = new HashSet<>();
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Passenger> query =
                entityManager.createQuery("SELECT c FROM Passenger c", Passenger.class);
        List<Passenger> results = query.getResultList();
        passengers.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return passengers;
    }

    @Override
    public Set<Passenger> fetch(int page, int perPage, String sort) throws SQLException {
        Set<Passenger> passengers = new HashSet<>();
        int startPoint = perPage*(page - 1);
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Passenger> query =
                entityManager.createQuery("Select p from Passenger as p order by :n ASC", Passenger.class).
                        setParameter("n", sort).
                setFirstResult(startPoint).setMaxResults(perPage);
        List<Passenger> results = query.getResultList();
        passengers.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(passenger);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(passenger);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return passenger;
    }

    @Override
    public void delete(long passengerId) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        Passenger passenger = entityManager.find(Passenger.class, passengerId);
        entityManager.remove(passenger);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        List<Passenger> list = entityManager.createQuery("Select t.passengers from Trip as t where t.tripNumber=:tn").
                setParameter("tn", tripNumber).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return list;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        trip = entityManager.find(Trip.class, trip.getTripId());
        passenger = entityManager.find(Passenger.class, passenger.getPassengerId());
        entityManager.getTransaction().begin();
        trip.getPassengers().add(passenger);
        passenger.getTrips().add(trip);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void cancelTrip(long passengerId, long tripId) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Passenger passenger = entityManager.find(Passenger.class, passengerId);
        Trip trip = entityManager.find(Trip.class, tripId);
        passenger.getTrips().remove(trip);
        trip.getPassengers().remove(passenger);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
