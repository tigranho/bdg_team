import dao.AddressDao;
import dao.CompanyDao;
import dao.PassengerDao;
import dao.TripDao;
import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.PassengerDaoImpl;
import dao.impl.TripDaoImpl;
import org.junit.jupiter.api.Test;
import pojo.Address;
import pojo.Company;
import pojo.Passenger;
import pojo.Trip;
import util.DBConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCTest {

    @Test
    public void testDBConnection() {
        Connection conn = DBConnection.connect();
        assertNotNull(conn);
    }

    @Test
    public void testAddressGetByID() {
        AddressDao addressDao = new AddressDaoImpl();
        Address address = addressDao.getById(15);
        assertNotNull(address);
        assertEquals("Belarus", address.getCountry());
        assertEquals("Dashkawka", address.getCity());
    }

    @Test
    public void testGetAddressId() {
        AddressDao addressDao = new AddressDaoImpl();
        Address address = new Address("Italy", "Catania");
        long id = addressDao.getAddressId(address);
        assertEquals(170, id);
    }

    @Test
    public void testCompanyGetByID() {
        CompanyDao companyDao = new CompanyDaoImpl();
        Company company = companyDao.getById(145);
        assertNotNull(company);
        assertEquals("Zazio", company.getName());
        assertEquals("9/23/2009", company.getFound_date());
    }

    @Test
    public void testCompanyGetAll() {
        CompanyDao companyDao = new CompanyDaoImpl();
        Set<Company> companies = companyDao.getAll();
        assertNotNull(companies);
        assertEquals(1000, companies.size());
    }

    @Test
    public void testGetCompanyId() {
        CompanyDao companyDao = new CompanyDaoImpl();
        Company company = new Company("InnoZ", "8/14/1995");
        long id = companyDao.getCompanyId(company);
        assertEquals(30, id);
    }

    @Test
    public void testPassengerGetByID() {
        PassengerDao passengerDao = new PassengerDaoImpl();
        Passenger passenger = passengerDao.getById(41);
        assertNotNull(passenger);
        assertEquals("Salvador Lyburn", passenger.getName());
        assertEquals("184-759-5270", passenger.getPhone());
        assertEquals("Brazil", passenger.getAddress().getCountry());
    }

//    @Test
//    public void testPassengerGetAll() {
//        PassengerDao companyDao = new PassengerDaoImpl();
//        Set<Passenger> passengers = companyDao.getAll();
//        assertNotNull(passengers);
//        assertEquals(1000, passengers.size());
//    }

//    @Test
//    public void testTripSave() {
//        TripDao tripDao = new TripDaoImpl();
//        Company company = new CompanyDaoImpl().getById(5);
//        Company company1 = new CompanyDaoImpl().getById(10);
//        Trip trip = new Trip(115, company, "10:15", "12:30", "Moscow", "Yerevan");
//        Trip trip1 = new Trip(273, company1, "21:15", "24:30", "Yerevan", "Athens");
//        Trip newTrip = tripDao.save(trip);
//        Trip newTrip1 = tripDao.save(trip1);
//        assertNotNull(newTrip);
//        assertNotNull(newTrip1);
//        assertEquals(newTrip.getNumber(), trip.getNumber());
//        assertEquals(newTrip1.getNumber(), trip1.getNumber());
//        assertEquals("Yerevan", newTrip.getOrigin());
//        assertEquals("Athens", newTrip1.getOrigin());
//    }
//
//    @Test
//    public void testTripDelete() {
//        new TripDaoImpl().delete(1);
//    }

    @Test
    public void testTripGetByID() {
        TripDao tripDao = new TripDaoImpl();
        Trip trip = tripDao.getById(10);
        assertNotNull(trip);
        assertEquals("Moscow", trip.getDestination());
        assertEquals(115, trip.getNumber());
    }

    @Test
    public void testGetTripsFrom() {
        List<Trip> trips = new TripDaoImpl().getTripsFrom("Yerevan");
        assertNotNull(trips);
        assertEquals("Yerevan", trips.get(0).getOrigin());
    }

    @Test
    public void testGetTripsTo() {
        List<Trip> trips = new TripDaoImpl().getTripsTo("Yerevan");
        assertNotNull(trips);
        assertEquals("Yerevan", trips.get(0).getDestination());
    }

    @Test
    public void testPassengersOfTrip() {
        Trip trip = new TripDaoImpl().getById(10);
        PassengerDao passengerDao = new PassengerDaoImpl();
        Passenger passenger = passengerDao.getById(10);
        passengerDao.registerTrip(trip, passenger);
        List<Passenger> passengers = passengerDao.getPassengersOfTrip(trip.getNumber());
        assertEquals("Alexis Calladine", passengers.get(0).getName());
    }
}
