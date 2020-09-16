package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.PassengerDAOImpl;
import lesson10.airport_management_system.model.Address;
import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.service.PassengerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceImplTest {

    private static PassengerService service;

    @BeforeAll
    static void setUp() {
        service = new PassengerServiceImpl(new PassengerDAOImpl());
    }

    @Test
    void getCompanyById() {
        Passenger passenger = service.get(456);
        assertEquals(456, passenger.getId());
    }

    @Test
    void findAll() {
        Set<Passenger> passengers = service.findAll();
        assertFalse(passengers.isEmpty());
    }

    @Test
    void testGetCompanyBySortingAndPaging() {
        Set<Passenger> passengers = service.getPassengers(20, 34, "founding_date");
        assertEquals(20, passengers.size());
        assertEquals(35, passengers.stream().sorted(Comparator.comparingLong(Passenger::getId)).findAny().get().getId());
    }

    @Test
    void create() {
        Passenger passenger = service.create(new Passenger("Teo Walcott", "1-2243-4646-6655", new Address("England", "Chelsea")));
        passenger = service.get(passenger.getId());
        assertEquals("Teo Walcott", passenger.getName());
        assertEquals("Chelsea", passenger.getAddress().getCity());
    }

    @Test
    void edit() {
        final String newName = "Therry Anry";
        final int id = 210;
        Passenger passenger = service.get(id);
        passenger.setName(newName);
        service.edit(passenger);
        assertEquals(newName, service.get(id).getName());
    }


    @Test
    void remove() {
        final int id = 675;
        Passenger passenger = service.get(id);
        assertNotNull(passenger);
        service.remove(id);
        assertNull(service.get(id));
    }

    @Test
    void getPassengers_by_trip_id() {
        Set<Passenger> passengers = service.getPassengersOfTrip(3);
        assertFalse(passengers.isEmpty());
    }

}