package tasks.airportManagementSystem.JPA.daoImpl;

import tasks.airportManagementSystem.JPA.GetEntityManager;
import tasks.airportManagementSystem.JPA.model.Trip;
import tasks.airportManagementSystem.JPA.dao.TripDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 25-Sep-20
 */
public class TripImpl implements TripDAO {
    @Override
    public Trip getById(int id) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        Trip trip = entityManager.find(Trip.class, id);
        entityManager.close();
        return trip;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Trip> getAll() {
        Set<Trip> trips = new LinkedHashSet<>();
        EntityManager entityManager = GetEntityManager.getEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT t FROM Trip t ");
            List<Trip> list = (List<Trip>) query.getResultList();
            trips.addAll(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        entityManager.close();
        return trips;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        Set<Trip> trips = new LinkedHashSet<>();
        String query = "SELECT t FROM Trip t order by t." + sort;
        EntityManager entityManager = GetEntityManager.getEntityManager();
        try {
            List<Trip> list = (List<Trip>) entityManager
                    .createQuery(query)
                    .setMaxResults(perPage)
                    .setFirstResult(((page - 1) * perPage))
                    .getResultList();
            trips.addAll(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        entityManager.close();
        return trips;
    }

    @Override
    public Trip save(Trip trip) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(trip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Trip updatedTrip = entityManager.find(Trip.class, trip.getId());
            updatedTrip.setCompany(trip.getCompany());
            updatedTrip.setCity_from(trip.getCity_from());
            updatedTrip.setCity_too(trip.getCity_too());
            updatedTrip.setTime_out(trip.getTime_out());
            updatedTrip.setTime_in(trip.getTime_in());
            entityManager.persist(updatedTrip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return trip;
    }

    @Override
    public void delete(int tripId) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Trip trip = entityManager.find(Trip.class, tripId);
            entityManager.remove(trip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Trip> getTripsFrom(String city) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM Trip t WHERE t.city_from = ?1");
        List<Trip> list = (List<Trip>) query
                .setParameter(1, city)
                .getResultList();
        entityManager.close();
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Trip> getTripsTo(String city) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM Trip t WHERE t.city_too = ?1");
        List<Trip> list = (List<Trip>) query
                .setParameter(1, city)
                .getResultList();
        entityManager.close();
        return list;
    }
}
