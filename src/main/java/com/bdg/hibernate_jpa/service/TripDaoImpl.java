package com.bdg.hibernate_jpa.service;

import com.bdg.hibernate_jpa.dao.TripDao;
import com.bdg.hibernate_jpa.entity.Trip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripDaoImpl implements TripDao {

    EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_jpa");
    EntityManager entityManager = enfactory.createEntityManager();

    @Override
    public Trip getById(long id) {
        return entityManager.find(Trip.class, id);
    }

    @Override
    public Set<Trip> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT Trip FROM Trip ", Trip.class).getResultList());

    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip trip) {
        entityManager.persist(trip);
        return trip;
    }

    @Override
    public Trip update(Trip trip, long id) {
        Trip prevTrip = entityManager.find(Trip.class, id);

        prevTrip.setTime_in(trip.getTime_in());
        prevTrip.setTime_out(trip.getTime_out());
        prevTrip.setTo_town(trip.getTo_town());
        prevTrip.setFrom_town(trip.getFrom_town());

        return prevTrip;
    }

    @Override
    public void delete(long tripId) {
        Trip trip = entityManager.find(Trip.class, tripId);

        entityManager.remove(trip);
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        return null;
    }
}