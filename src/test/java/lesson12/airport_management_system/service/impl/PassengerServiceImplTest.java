package lesson12.airport_management_system.service.impl;

import lesson12.airport_management_system.dao.impl.CompanyDAOImpl;
import lesson12.airport_management_system.dao.impl.PassengerDAOImpl;
import lesson12.airport_management_system.dao.impl.TripDAOImpl;
import lesson12.airport_management_system.entity.Address;
import lesson12.airport_management_system.entity.Company;
import lesson12.airport_management_system.entity.Passenger;
import lesson12.airport_management_system.entity.Trip;
import lesson12.airport_management_system.service.CompanyService;
import lesson12.airport_management_system.service.PassengerService;
import lesson12.airport_management_system.service.TripService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceImplTest {

    private static PassengerService passengerService;
    private static TripService tripService;
    private static EntityManager em;

    @BeforeAll
    static void setUpDao() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate_JPA");
        em = emf.createEntityManager();
        passengerService = new PassengerServiceImpl(new PassengerDAOImpl(em));
        tripService = new TripServiceImpl(new TripDAOImpl(em));
        passengerService.create(new Passenger("Jane", "+1-123-333-44-55", new Address("England", "Chelsea")));
    }

    @Test
    void get() {
        Passenger passenger = passengerService.get(1).orElse(new Passenger());
        assertEquals("Jane", passenger.getName());
    }

    @Test
    void findAll() {
        Set<Passenger> passengers = passengerService.findAll();
        assertFalse(passengers.isEmpty());
    }

    @Test
    void getPassengers() {
//        service.loadPassengersInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/passengers.txt");
        Set<Passenger> passengerSubSet = passengerService.getPassengers(1, 30, "name");
        assertEquals(30, passengerSubSet.size());
    }

    @Test
    void create() {
        passengerService.create(new Passenger("Harry", "+1-122-453-44-43", new Address("US", "LA")));
        Passenger passenger = passengerService.get(2).orElse(new Passenger());
        assertEquals("Harry", passenger.getName());
    }

    @Test
    void edit() {
        Optional<Passenger> optionalPassenger = passengerService.get(1);
        optionalPassenger.ifPresent(p -> {
            p.setName("Teodor");
            passengerService.edit(p);
        });
        Passenger passenger = passengerService.get(1).orElse(new Passenger());
        assertEquals("Teodor", passenger.getName());
    }

    @Test
    void remove() {
        Optional<Passenger> optionalPassenger = passengerService.get(1);
        optionalPassenger.ifPresent(p -> passengerService.remove(p.getId()));
        assertNull(passengerService.get(1).orElse(null));
    }

    @Test
    void getPassengersOfTrip() {
        CompanyService companyService = new CompanyServiceImpl(new CompanyDAOImpl(em));
        companyService.loadCompaniesInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/companies.txt");
        passengerService.loadPassengersInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/passengers.txt");
        tripService.loadTripsInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/trips.txt");
        passengerService.loadPassengersTripsInfoFromFile("src/main/resources/airport_management_system_source/passengers_trips.txt");
        Trip trip = tripService.getTrip(7).orElse(new Trip());
        assertEquals(5, trip.getPassengers().size());
    }

    @Test
    void registerTrip() {
        Passenger passenger = new Passenger("Adam", "+22-122-45-44-32", new Address("Canada", "Ottava"));
        passenger = passengerService.create(passenger).orElse(new Passenger());
        Company company = new Company("AirLane", LocalDate.of(1996, 4, 6));
        company = new CompanyServiceImpl(new CompanyDAOImpl(em)).create(company).orElse(new Company());
        Trip trip = new Trip(company, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Yalta", "Sofia");
        trip = tripService.create(trip).orElse(new Trip());
        passengerService.registerTrip(trip, passenger);
        passenger = passengerService.get(passenger.getId()).orElse(new Passenger());
        assertEquals("Yalta", passenger.getTrips().stream().findFirst().get().getFromCity());
    }

    @Test
    void cancelTrip() {
        Passenger passenger = new Passenger("Adam", "+22-122-45-44-32", new Address("Canada", "Ottava"));
        passenger = passengerService.create(passenger).orElse(new Passenger());
        Company company = new Company("AirLane", LocalDate.of(1996, 4, 6));
        company = new CompanyServiceImpl(new CompanyDAOImpl(em)).create(company).orElse(new Company());
        Trip trip = new Trip(company, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Yalta", "Sofia");
        trip = tripService.create(trip).orElse(new Trip());
        passengerService.registerTrip(trip, passenger);
        passengerService.cancelTrip(passenger.getId(), trip.getId());
        assertTrue(passengerService.getPassengersOfTrip(trip.getId()).isEmpty());
    }
}