package lesson10.airport_management_system;

import lesson10.airport_management_system.dao.impl.CompanyDAOImpl;
import lesson10.airport_management_system.dao.impl.PassengerDAOImpl;
import lesson10.airport_management_system.dao.impl.PassengerTripDAOImpl;
import lesson10.airport_management_system.dao.impl.TripDAOImpl;
import lesson10.airport_management_system.service.CompanyService;
import lesson10.airport_management_system.service.PassengerService;
import lesson10.airport_management_system.service.PassengerTripService;
import lesson10.airport_management_system.service.TripService;
import lesson10.airport_management_system.service.impl.CompanyServiceImpl;
import lesson10.airport_management_system.service.impl.PassengerServiceImpl;
import lesson10.airport_management_system.service.impl.PassengerTripServiceImpl;
import lesson10.airport_management_system.service.impl.TripServiceImpl;

public class InsertProjectData {
    public static void main(String[] args) {
        CompanyService companyService = new CompanyServiceImpl(new CompanyDAOImpl());
        PassengerService passengerService = new PassengerServiceImpl(new PassengerDAOImpl());
        TripService tripService = new TripServiceImpl(new TripDAOImpl());
        PassengerTripService passengerTripService = new PassengerTripServiceImpl(new PassengerTripDAOImpl());
        companyService.loadCompaniesInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/companies.txt");
        passengerService.loadPassengersInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/passengers.txt");
        tripService.loadTripsInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/trips.txt");
        passengerTripService.loadPassengersTripsInfoFromFileAndCreateAll("src/main/resources/airport_management_system_source/passengers_trips.txt");
    }
}
