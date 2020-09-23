package jpa_homework.services;

import jpa_homework.dao.PassengerDAO;
import jpa_homework.models.Company;
import jpa_homework.models.Passenger;
import jpa_homework.models.PassengerTrip;
import jpa_homework.models.Trip;

import javax.persistence.*;
import java.util.*;

public class PassengerDAOImpl implements PassengerDAO {

    @Override
    public Passenger getById(long id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Passenger> typedQuery
                = entityManager.createQuery("select p from Passenger p where p.id = :id", Passenger.class);
        typedQuery.setParameter("id", id);
        Passenger passenger = typedQuery.getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Passenger> typedQuery
                = entityManager.createQuery("select p from Passenger p", Passenger.class);
        Set<Passenger> set = new HashSet<>(typedQuery.getResultList());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return set;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        int start = (page - 1) * perPage;
        int end = start + perPage;

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Passenger> typedQuery
                = entityManager.createQuery("select p from Passenger p where p.id >= :start and p.id < :end", Passenger.class);

        typedQuery.setParameter("start", start);
        typedQuery.setParameter("end", end);
        Set<Passenger> set = new HashSet<>(typedQuery.getResultList());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return set;
    }

    @Override
    public Passenger save(Passenger passenger) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(passenger);

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Passenger> typedQuery =
                entityManager.createQuery("select p from Passenger p where p.id = :companyID", Passenger.class);
        typedQuery.setParameter("companyID", passenger.getId());
        typedQuery.getSingleResult().setName(passenger.getName());
        typedQuery.getSingleResult().setPhone(passenger.getPhone());
        typedQuery.getSingleResult().setAddress(passenger.getAddress());


        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return passenger;
    }

    @Override
    public void delete(long passengerId) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("delete from Passenger p where p.id = :passengerId");
        query.setParameter("passengerId", passengerId).executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<PassengerTrip> typedQuery =
                entityManager.createQuery("select p from PassengerTrip p where p.trip.id = :tripNumber", PassengerTrip.class);
        List<PassengerTrip> list = typedQuery.getResultList();
        List<Passenger> passengersList = new ArrayList<>();
        while(!list.isEmpty()) {
            passengersList.add(list.remove(0).getPassenger());
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return passengersList;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        PassengerTrip pt = new PassengerTrip(passenger, trip);
        entityManager.persist(pt);

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery
                ("delete from PassengerTrip p where p.passenger.id = :passengerId and p.trip.id = :tripNumber");
        query.setParameter("passengerId", passengerId);
        query.setParameter("tripNumber", tripNumber);
        query.executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }
}