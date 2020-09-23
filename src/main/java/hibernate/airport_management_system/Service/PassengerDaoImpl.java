package hibernate.airport_management_system.Service;

import hibernate.airport_management_system.dao.PassengerDao;
import hibernate.airport_management_system.entity.Passenger;
import hibernate.airport_management_system.entity.Trip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {

    EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
    EntityManager entityManager = enfactory.createEntityManager();

    @Override
    public Passenger getById(long id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    public Set<Passenger> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT Passenger FROM Passenger ", Passenger.class).getResultList());

    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Passenger save(Passenger passenger) {
        entityManager.persist(passenger);
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger, long id) {
        Passenger prevPassenger = entityManager.find(Passenger.class, id);

        prevPassenger.setName(passenger.getName());
        prevPassenger.setPhone(passenger.getPhone());

        return prevPassenger;
    }

    @Override
    public void delete(long passengerId) {
        Passenger passenger = entityManager.find(Passenger.class, passengerId);

        entityManager.remove(passenger);
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}
