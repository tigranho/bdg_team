package jpa_homework.services;

import jpa_homework.dao.TripDAO;
import jpa_homework.models.Passenger;
import jpa_homework.models.Trip;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripDAOImpl implements TripDAO {

    @Override
    public Trip getById(long id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Trip> typedQuery
                = entityManager.createQuery("select t from Trip t where t.id = :id", Trip.class);
        typedQuery.setParameter("id", id);
        Trip trip = typedQuery.getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Trip> typedQuery
                = entityManager.createQuery("select t from Trip t", Trip.class);
        Set<Trip> set = new HashSet<>(typedQuery.getResultList());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return set;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        int start = (page - 1) * perPage;
        int end = start + perPage;

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Trip> typedQuery
                = entityManager.createQuery("select t from Trip t where t.id >= :start and t.id < :end", Trip.class);

        typedQuery.setParameter("start", start);
        typedQuery.setParameter("end", end);
        Set<Trip> set = new HashSet<>(typedQuery.getResultList());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return set;
    }

    @Override
    public Trip save(Trip trip) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(trip);

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Trip> typedQuery =
                entityManager.createQuery("select p from Trip p where p.id = :tripID", Trip.class);
        typedQuery.setParameter("tripID", trip.getId());
        typedQuery.getSingleResult().setCompany(trip.getCompany());
        typedQuery.getSingleResult().setTimeIn(trip.getTimeIn());
        typedQuery.getSingleResult().setTimeOut(trip.getTimeOut());
        typedQuery.getSingleResult().setTownFrom(trip.getTownFrom());
        typedQuery.getSingleResult().setTownTo(trip.getTownTo());


        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return null;
    }

    @Override
    public void delete(long tripId) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("delete from Trip p where p.id = :tripId");
        query.setParameter("tripId", tripId).executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Trip> typedQuery = entityManager.createQuery("select t from Trip t where t.townFrom = :city", Trip.class);
        typedQuery.setParameter("city", city);
        List<Trip> list = typedQuery.getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return list;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Trip> typedQuery = entityManager.createQuery("select t from Trip t where t.townTo = :city", Trip.class);
        typedQuery.setParameter("city", city);
        List<Trip> list = typedQuery.getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return list;
    }
}
