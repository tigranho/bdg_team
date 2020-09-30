package jpa.implement;

import jpa.GetEntity;
import jpa.entity.Passenger;
import jpa.dao.PassengersDao;
import jpa.entity.PassengerTrip;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengersImpl implements PassengersDao {
    @Override
    public Passenger getById(long id) {

        EntityManager entityManager = GetEntity.getEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.find(Passenger.class, id);
    }

    @Override
    public Set<Passenger> getAll() {

        Set<Passenger> passengers = new HashSet<>();
        EntityManager entityManager = GetEntity.getEntityManager();
        Passenger passenger = (Passenger) entityManager.createQuery("select Passenger from Passenger ").getResultList();
        passengers.add(passenger);

        return passengers;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        Set<Passenger> passengers = new HashSet<>();
        EntityManager entityManager = GetEntity.getEntityManager();

        Passenger passenger = (Passenger) entityManager.createQuery(
                "select Passenger from Passenger order by " + sort)
                .setMaxResults(page).setFirstResult(perPage).getResultList();
        passengers.add(passenger);

        return passengers;
    }

    @Override
    public Passenger save(Passenger passenger) {

        EntityManager entityManager = GetEntity.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(passenger);
        entityManager.getTransaction().commit();

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {

        EntityManager entityManager = GetEntity.getEntityManager();
        Passenger update_passenger = entityManager.find(Passenger.class, passenger.getId());
        entityManager.getTransaction().begin();
        update_passenger.setName(passenger.getName());
        update_passenger.setPhone(passenger.getPhone());
        update_passenger.setAddress_id(passenger.getAddress_id());
        entityManager.persist(update_passenger);
        entityManager.getTransaction().commit();

        return passenger;
    }

    @Override
    public void delete(long passengerId) {

        EntityManager entityManager = GetEntity.getEntityManager();
        Passenger passenger = entityManager.find(Passenger.class, passengerId);

        entityManager.getTransaction().begin();
        entityManager.remove(passenger);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        List<PassengerTrip> passengers = new ArrayList<>();
        EntityManager entityManager = GetEntity.getEntityManager();
        PassengerTrip passengerTrip = entityManager.find(PassengerTrip.class, tripNumber);
        passengers.add(passengerTrip);

        return null;
    }
}