package jdbctaskusingjpaproviderhibernate.dao.daoImpl;


import jdbctaskusingjpaproviderhibernate.dao.PassengerDao;
import jdbctaskusingjpaproviderhibernate.entity.Address;
import jdbctaskusingjpaproviderhibernate.entity.Passenger;
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
        int startPoint = perPage * page - perPage;
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
        Long id = passenger.getPassengerId();
        entityManager.getTransaction().commit();
        passenger.setPassengerId(id);
        entityManager.close();
        entityManagerFactory.close();
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(
                "UPDATE Passenger as p SET p.name=:n,  p.phone=:p1 WHERE p.passengerId=:id");
        int updateCount = query.setParameter("n", passenger.getName())
                .setParameter("p1", passenger.getPhone())
                .setParameter("id", passenger.getPassengerId()).executeUpdate();
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
        Query query = entityManager.createQuery(
                "delete from Passenger as p WHERE p.passengerId=:id");
        int updateCount = query
                .setParameter("id", passengerId).executeUpdate();
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
        entityManager.getTransaction().begin();

        Set<Passenger> passengers = new HashSet<>();
        passengers.add(passenger);
        trip.setPassengers(passengers);

        Set<Trip> trips = new HashSet<>();
        trips.add(trip);
        passenger.setTrips(trips);

        Address address = new Address();
        address = passenger.getAddress();
        address.setPassengers(passengers);

        entityManager.persist(trip);
        entityManager.persist(passenger);
        entityManager.persist(address);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) throws SQLException {
//        String q = "DELETE from passenger_trip where passenger_id=? and " +
//                "trip_id in (Select trip_id from trip where trip_number = ?);";
//        try(Connection conn = DatabaseConnectionFactory.getInstance().getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(q)){
//            preparedStatement.setLong(1, passengerId);
//            preparedStatement.setLong(2, tripNumber);
//            preparedStatement.execute();
//        }
    }

}
