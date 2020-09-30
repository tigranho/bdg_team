package jpa.implement;

import jpa.GetEntity;
import jpa.dao.PassengerTripDao;
import jpa.entity.PassengerTrip;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class PassengerTripImpl implements PassengerTripDao {
    @Override
    public PassengerTrip getById(long id) {
        EntityManager entityManager = GetEntity.getEntityManager();
        return entityManager.find(PassengerTrip.class, id);
    }

    @Override
    public Set<PassengerTrip> getAll() {
        Set<PassengerTrip> passengerTrips = new HashSet<>();
        EntityManager entityManager = GetEntity.getEntityManager();

        PassengerTrip passengerTrip = (PassengerTrip)
                entityManager.createQuery("select PassengerTrip from PassengerTrip ").getResultList();
        passengerTrips.add(passengerTrip);

        return passengerTrips;
    }

    @Override
    public PassengerTrip save(PassengerTrip trip) {
        EntityManager entityManager = GetEntity.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(trip);
        entityManager.getTransaction().commit();

        return trip;
    }

    @Override
    public PassengerTrip update(PassengerTrip trip) {

        EntityManager entityManager = GetEntity.getEntityManager();
        PassengerTrip passengerTrip = entityManager.find(PassengerTrip.class, trip.getId());
        entityManager.getTransaction().begin();
        passengerTrip.setPassenger_id(trip.getPassenger_id());
        passengerTrip.setTrip_id(trip.getTrip_id());
        entityManager.persist(passengerTrip);
        entityManager.getTransaction().commit();

        return trip;
    }

    @Override
    public void delete(long addressId) {

        EntityManager entityManager = GetEntity.getEntityManager();
        PassengerTrip passengerTrip = entityManager.find(PassengerTrip.class, addressId);
        entityManager.getTransaction().begin();
        entityManager.remove(passengerTrip);
        entityManager.getTransaction().commit();
    }
}