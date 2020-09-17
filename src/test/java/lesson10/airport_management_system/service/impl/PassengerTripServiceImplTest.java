package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.PassengerTripDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PassengerTripServiceImplTest {

    private static PassengerTripServiceImpl service;

    @BeforeAll
    static void setUp() {
        service = new PassengerTripServiceImpl(new PassengerTripDAOImpl());
    }

    @Test
    void create() {
        assertTrue(service.create(3, 1));
    }

    @Test
    void remove() {
        if (service.create(2, 2))
            assertTrue(service.remove(2, 2));
        else Assertions.fail("failed create a trip");
    }

    @Test
    void edit_passenger_id_by_trip_id() {
        if (service.create(1, 1))
            assertTrue(service.editPassengerId(2, 1));
        else Assertions.fail("failed create a trip");
    }

    @Test
    void edit_trip_id_by_passenger_id() {
        if (service.create(1, 1))
            assertTrue(service.editTripId(1, 2));
        else Assertions.fail("failed create a trip");
    }

}