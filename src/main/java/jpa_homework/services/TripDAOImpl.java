package jpa_homework.services;

import jpa_homework.dao.TripDAO;
import jpa_homework.models.Passenger;
import jpa_homework.models.Trip;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripDAOImpl implements TripDAO {

    @Override
    public Trip getById(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        Trip trip = entityManager.find(Trip.class, id);

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
        int start = (page == 0) ? (page - 1) * perPage : (page - 1) * perPage - 1;

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trip> query = cb.createQuery(Trip.class);
        Root<Trip> from = query.from(Trip.class);
        CriteriaQuery<Trip> select = query.select(from);
        CriteriaQuery<Trip> orderBy = select.orderBy(cb.asc(from.get(sort)));
        Set<Trip> set = entityManager.createQuery(orderBy)
                .setFirstResult(start)
                .setMaxResults(perPage)
                .getResultStream()
                .collect(Collectors.toSet());

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

        entityManager.merge(trip);

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
