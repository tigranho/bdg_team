package jpa.implement;

import jpa.GetEntity;
import jpa.entity.Passenger;
import jpa.entity.Trip;
import jpa.dao.TripDao;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripImpl implements TripDao {

    @Override
    public Trip getById(long id) {

        EntityManager entityManager = GetEntity.getEntityManager();

        return entityManager.find(Trip.class, id);
    }

    @Override
    public Set<Trip> getAll() {
        EntityManager entityManager = GetEntity.getEntityManager();
        Set<Trip> trips = new HashSet<>();
        Trip trip = (Trip) entityManager.createQuery("select Trip from Trip ").getResultList();
        trips.add(trip);
        return trips;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        Set<Trip> trips = new HashSet<>();
        EntityManager entityManager = GetEntity.getEntityManager();
        Trip trip = (Trip) entityManager.createQuery("select Trip from Trip order by " + sort)
                .setMaxResults(page).setFirstResult(perPage).getResultList();
        trips.add(trip);

        return trips;
    }

    @Override
    public Trip save(Trip passenger) {
        EntityManager entityManager = GetEntity.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(passenger);
        entityManager.getTransaction().commit();
        return passenger;
    }

    @Override
    public Trip update(Trip passenger) {
        EntityManager entityManager = GetEntity.getEntityManager();
        Trip trip_update = entityManager.find(Trip.class, passenger.getTrip_number());
        entityManager.getTransaction().begin();
        trip_update.setTrip_number(passenger.getTrip_number());
        trip_update.setCompany_id(passenger.getCompany_id());
        trip_update.setTime_in(passenger.getTime_in());
        trip_update.setTime_out(passenger.getTime_out());
        trip_update.setTown_to(passenger.getTown_to());
        trip_update.setTown_from(passenger.getTown_from());
        entityManager.persist(trip_update);
        entityManager.getTransaction().commit();

        return passenger;
    }

    @Override
    public void delete(long tripId) {

        EntityManager entityManager = GetEntity.getEntityManager();
        Trip trip = entityManager.find(Trip.class, tripId);

        entityManager.getTransaction().begin();
        entityManager.remove(trip);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        return null;
    }

    @Override
    public void registerTrip(TripImpl trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}