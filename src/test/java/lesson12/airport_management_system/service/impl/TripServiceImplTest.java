package lesson12.airport_management_system.service.impl;

import lesson12.airport_management_system.dao.impl.TripDAOImpl;
import lesson12.airport_management_system.service.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class TripServiceImplTest {

    private static TripService service;

    @BeforeAll
    static void setUp() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate_JPA");
        final EntityManager em = emf.createEntityManager();
        service = new TripServiceImpl(new TripDAOImpl(em));
    }

    @Test
    void getTrip() {
    }

    @Test
    void findAll() {
    }

    @Test
    void getTrips() {
    }

    @Test
    void create() {
    }

    @Test
    void getTripPassengers() {
    }

    @Test
    void edit() {
    }

    @Test
    void remove() {
    }

    @Test
    void getPassengerTrips() {
    }

    @Test
    void getTripsFromCity() {
    }

    @Test
    void getTripsToCity() {
    }
}