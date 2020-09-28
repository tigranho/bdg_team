package tasks.airportManagementSystem.JPA.daoImpl;

import tasks.airportManagementSystem.JPA.model.Passenger;
import tasks.airportManagementSystem.JPA.model.Trip;
import tasks.airportManagementSystem.JPA.GetEntityManager;
import tasks.airportManagementSystem.JPA.dao.PassengerDAO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tatevik Mirzoyan
 * Created on 25-Sep-20
 */
public class PassengerImpl implements PassengerDAO {
    @Override
    public Passenger getById(int id) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        Passenger passenger = entityManager.find(Passenger.class, id);
        entityManager.close();
        return passenger;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Passenger> getAll() {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        return (Set<Passenger>) entityManager
                .createQuery("SELECT p FROM Passenger p ")
                .getResultStream()
                .collect(Collectors.toSet());
    }

    //TODO
    @SuppressWarnings("unchecked")
    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        String query = "SELECT p FROM Passenger p order by p." + sort;
        EntityManager entityManager = GetEntityManager.getEntityManager();
        return (Set<Passenger>) entityManager
                .createQuery(query)
                .setFirstResult(((page - 1) * perPage))
                .setMaxResults(perPage)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Passenger save(Passenger passenger) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(passenger);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Passenger updatedPassenger = entityManager.find(Passenger.class, passenger.getId());
            updatedPassenger.setName(passenger.getName());
            updatedPassenger.setPhone(passenger.getPhone());
            updatedPassenger.setAddress(passenger.getAddress());
            entityManager.persist(updatedPassenger);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return passenger;
    }

    @Override
    public void delete(int passengerId) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Passenger passenger = entityManager.find(Passenger.class, passengerId);
            entityManager.remove(passenger);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }

    //TODO
    @Override
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(int passengerId, int tripNumber) {

    }
}
