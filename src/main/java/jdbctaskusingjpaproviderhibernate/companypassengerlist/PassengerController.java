package jdbctaskusingjpaproviderhibernate.companypassengerlist;


import jdbctaskusingjpaproviderhibernate.entity.Address;
import jdbctaskusingjpaproviderhibernate.entity.Passenger;
import jdbctaskusingjpaproviderhibernate.entity.Trip;
import jdbctaskusingjpaproviderhibernate.exceptions.DatabaseException;
import jdbctaskusingjpaproviderhibernate.service.PassengerService;
import jdbctaskusingjpaproviderhibernate.service.serviceimpl.PassengerServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 18.09.2020.
 */
public class PassengerController {
    private PassengerService passengerService;
    private CompanyPassengerList companyPassengerList;

    public PassengerController() {
        this.passengerService = new PassengerServiceImpl();
        this.companyPassengerList = new CompanyPassengerList();

    }

    public void addPassengersFromFileList() throws SQLException, DatabaseException {
        List<Passenger> passengerList = companyPassengerList.getPassengersList();
//        Address address = new Address();
        Passenger passenger = new Passenger();

        for (int i = 0; i < passengerList.size(); i++) {
//            address = addressService.save(addresses.get(i));
            passenger = passengerList.get(i);
//            passenger.setAddressId(address.getAddressId());
            passengerService.save(passenger);
        }

    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        PassengerController passengerController = new PassengerController();
//      passengerController.addPassengersFromFileList();
//        System.out.println(passengerController.passengerService.getAll());
        Trip trip = new Trip();
        trip.setTownFrom("jd");
        trip.setTownTo("jdhf");
        trip.setTimeIn("12:25:5");
        trip.setTimeOut("10:25:5");
        Passenger passenger = new Passenger();
        passenger.setName("as");
        passenger.setPhone("sad");
        Address address = new Address();
        address.setCity("as");
        address.setCountry("s");
        passenger.setAddress(address);
        passengerController.passengerService.registerTrip(trip, passenger);
    }
}

