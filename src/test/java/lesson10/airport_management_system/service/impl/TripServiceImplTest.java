package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.TripDAOImpl;
import lesson10.airport_management_system.model.Company;
import lesson10.airport_management_system.model.Trip;
import lesson10.airport_management_system.service.TripService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TripServiceImplTest {

    private static TripService service;

    @BeforeAll
    static void setUp() {
        service = new TripServiceImpl(new TripDAOImpl());
    }

    @Test
    void getTrip() {
        Trip trip = new Trip(new Company("HighMind", LocalDate.of(1987, Month.AUGUST, 12)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Braga", "Tallin");
        Optional<Trip> optionalTrip = service.create(trip);
        optionalTrip.ifPresent(t -> service.getTrip(t.getId())
                .ifPresentOrElse(t2 -> assertEquals(t.getId(), t2.getId()), Assertions::fail));
    }

    @Test
    void findAll() {
        Trip trip = new Trip(new Company("Guasa", LocalDate.of(1976, Month.SEPTEMBER, 24)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Turin", "Madrid");
        Optional<Trip> optionalTrip = service.create(trip);
        if (optionalTrip.isPresent()) {
            Set<Trip> trips = service.findAll();
            assertFalse(trips.isEmpty());
        } else Assertions.fail("fail to create a trip");
    }

    @Test
    void getTrips() {
        Set<Trip> trips = service.getTrips(2, 4, "time_in");
        assertFalse(trips.isEmpty());
    }

    @Test
    void create() {
        Trip trip = new Trip(new Company("BestTravel", LocalDate.of(1999, Month.MARCH, 3)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Jerusalim", "Teheran");
        Optional<Trip> optionalTrip = service.create(trip);
        optionalTrip.ifPresentOrElse(t -> assertEquals("Teheran", t.getToCity()), Assertions::fail);
    }

    @Test
    void edit() {
        Trip trip = new Trip(new Company("NewEra", LocalDate.of(2003, Month.DECEMBER, 12)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Dublin", "Paris");
        Optional<Trip> optionalTrip = service.create(trip);
        trip.setFromCity("Seltic");
        optionalTrip.ifPresent(t -> trip.setId(t.getId()));
        optionalTrip = service.edit(trip);
        optionalTrip.ifPresentOrElse(t -> assertEquals(trip.getFromCity(), t.getFromCity()), Assertions::fail);
    }

    @Test
    void remove() {
        Trip trip = new Trip(new Company("JigaSV", LocalDate.of(2004, Month.JANUARY, 3)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(4), "Sietl", "Deli");
        Optional<Trip> optionalTrip = service.create(trip);
        optionalTrip.ifPresentOrElse(t -> service.remove(t.getId()), Assertions::fail);
        optionalTrip.ifPresent(t -> assertNull(service.getTrip(t.getId()).orElse(null)));
    }

    @Test
    void getTripsFromCity() {
        Trip trip = new Trip(new Company("Yelli", LocalDate.of(1996, Month.DECEMBER, 5)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Moscow", "Batum");
        Optional<Trip> optionalTrip = service.create(trip);
        optionalTrip.ifPresent(t -> assertNotEquals(0, service.getTripsFromCity("Batum").size()));
    }

    @Test
    void getTripsToCity() {
        Trip trip = new Trip(new Company("Delta Plan", LocalDate.of(2000, Month.DECEMBER, 7)),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Scopie", "Vilnyus");
        Optional<Trip> optionalTrip = service.create(trip);
        optionalTrip.ifPresent(t -> assertNotEquals(0, service.getTripsToCity("Scopie").size()));
    }

    @Test
    void getPassengerTrips() {
        assertNotEquals(0, service.getPassengerTrips(1));
    }
}