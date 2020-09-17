package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.PassengerDAOImpl;
import lesson10.airport_management_system.model.Address;
import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.service.PassengerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Optional;
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
        Optional<Passenger> passenger = service.get(456);
        passenger.ifPresentOrElse(p -> assertEquals(456, p.getId()), Assertions::fail);
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
        passengers.stream().sorted(Comparator.comparingLong(Passenger::getId))
                .forEach(passenger -> assertEquals(35, passenger.getId()));
    }

    @Test
    void create() {
        Optional<Passenger> passenger = service.create(new Passenger("Teo Walcott", "1-2243-4646-6655", new Address("England", "Chelsea")));
        passenger = service.get(passenger.orElse(new Passenger()).getId());
        passenger.ifPresentOrElse(p -> assertEquals("Teo Walcott", p.getName()), Assertions::fail);
        passenger.ifPresentOrElse(p -> assertEquals("Chelsea", p.getAddress().getCity()), Assertions::fail);
        ;
    }

    @Test
    void edit() {
        final String newName = "Therry Anry";
        final int id = 210;
        Optional<Passenger> passenger = service.get(id);
        passenger.ifPresent(p -> {
            p.setName(newName);
            service.edit(p);
        });
        service.get(id).ifPresentOrElse(p -> assertEquals(newName, p.getName()), Assertions::fail);
        ;
    }


    @Test
    void remove() {
        final int id = 675;
        Optional<Passenger> passenger = service.get(id);
        assertNotNull(passenger.orElse(null));
        service.remove(id);
        assertNull(service.get(id).orElse(null));
    }

    @Test
    void getPassengers_by_trip_id() {
        Set<Passenger> passengers = service.getPassengersOfTrip(3);
        assertFalse(passengers.isEmpty());
    }

}