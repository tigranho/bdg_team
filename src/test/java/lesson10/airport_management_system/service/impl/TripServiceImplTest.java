package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.TripDAOImpl;
import lesson10.airport_management_system.service.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TripServiceImplTest {

    private static TripService service;

    @BeforeAll
    static void setUp() {
        service = new TripServiceImpl(new TripDAOImpl());
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
    void createAll() {
    }

    @Test
    void edit() {
    }

    @Test
    void remove() {
    }

    @Test
    void getTripsFromCity() {
    }

    @Test
    void getTripsToCity() {
    }

    @Test
    void getPassengerTrips() {
    }
}