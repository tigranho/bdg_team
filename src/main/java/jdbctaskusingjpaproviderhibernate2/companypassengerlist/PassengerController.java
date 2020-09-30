package jdbctaskusingjpaproviderhibernate2.companypassengerlist;



import jdbctaskusingjpaproviderhibernate2.entity.Address;
import jdbctaskusingjpaproviderhibernate2.entity.Passenger;
import jdbctaskusingjpaproviderhibernate2.entity.Trip;
import jdbctaskusingjpaproviderhibernate2.exceptions.DatabaseException;
import jdbctaskusingjpaproviderhibernate2.service.PassengerService;
import jdbctaskusingjpaproviderhibernate2.service.serviceimpl.PassengerServiceImpl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void savePassengersWithTheirAddresses() throws DatabaseException, SQLException {
        List<Passenger> list = companyPassengerList.getPassengersList();
        List<Address> addresses = companyPassengerList.getPassengersAddresesList();
        for(int i = 0; i < list.size(); i++){
            Passenger passenger = new Passenger();
            Address address = new Address();
            Set<Passenger> passengers = new HashSet<>();
            passenger = list.get(i);
            address = addresses.get(i);
            if(address.getPassengers() != null){
                address.getPassengers().add(passenger);
            }else{
                passengers.add(passenger);
                address = new Address(address.getCountry(),
                        address.getCity(), passengers);
            }
            passenger.setAddress(address);
            passengerService.save(passenger);
        }
    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        PassengerController passengerController = new PassengerController();
//      passengerController.addPassengersFromFileList();
//        System.out.println(passengerController.passengerService.getAll());
//        System.out.println(passengerController.passengerService.getById(1L));
 //       passengerController.savePassengersWithTheirAddresses();
//        Passenger p = passengerController.passengerService.getById(509L);
//        System.out.println(p);
//       System.out.println(p.getAddress());
//        Trip trip = new Trip();
//        trip.setTripId(6L);
//        Passenger passenger = new Passenger();
//        passenger.setPassengerId(589l);
//        passengerController.passengerService.registerTrip(trip, passenger);

//        Trip trip = new Trip();
//        trip.setTripId(7L);
//        Passenger passenger = new Passenger();
//        passenger.setPassengerId(589l);
//        passengerController.passengerService.registerTrip(trip, passenger);

                Trip trip = new Trip();
        trip.setTripId(6L);
        Passenger passenger = new Passenger();
        passenger.setPassengerId(587l);
        passengerController.passengerService.registerTrip(trip, passenger);



    }
}

