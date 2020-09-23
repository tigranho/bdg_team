package lesson12;

import lesson12.airport_management_system.dao.impl.CompanyDAOImpl;
import lesson12.airport_management_system.dao.impl.PassengerDAOImpl;
import lesson12.airport_management_system.dao.impl.TripDAOImpl;
import lesson12.airport_management_system.service.CompanyService;
import lesson12.airport_management_system.service.PassengerService;
import lesson12.airport_management_system.service.TripService;
import lesson12.airport_management_system.service.impl.CompanyServiceImpl;
import lesson12.airport_management_system.service.impl.PassengerServiceImpl;
import lesson12.airport_management_system.service.impl.TripServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate_JPA");
        final EntityManager em = emf.createEntityManager();
        CompanyService companyService = new CompanyServiceImpl(new CompanyDAOImpl(em));
        PassengerService passengerService = new PassengerServiceImpl(new PassengerDAOImpl(em));
        TripService tripService = new TripServiceImpl(new TripDAOImpl(em));
        companyService.loadCompaniesInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/companies.txt");
        passengerService.loadPassengersInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/passengers.txt");
        tripService.loadTripsInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/trips.txt");
        passengerService.loadPassengersTripsInfoFromFile("src/main/resources/airport_management_system_source/passengers_trips.txt");

    }
}
