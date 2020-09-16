package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.PassengerTripDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PassengerTripServiceImplTest {

    private static PassengerTripServiceImpl service;

    @BeforeAll
    static void setUp() {
        service = new PassengerTripServiceImpl(new PassengerTripDAOImpl());
    }

    @Test
    void create() {
    }

    @Test
    void createAll() {
    }

    @Test
    void remove() {
    }

    @Test
    void edit() {
    }

    @Test
    void getByPassengerId() {
    }

    @Test
    void getByTripId() {
    }
}