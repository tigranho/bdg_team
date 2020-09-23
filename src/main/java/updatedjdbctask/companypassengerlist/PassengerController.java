package updatedjdbctask.companypassengerlist;


import updatedjdbctask.exceptions.DatabaseException;
import updatedjdbctask.model.Address;
import updatedjdbctask.model.Passenger;
import updatedjdbctask.service.AddressService;
import updatedjdbctask.service.PassengerService;
import updatedjdbctask.service.serviceimpl.AddressServiceImpl;
import updatedjdbctask.service.serviceimpl.PassengerServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 18.09.2020.
 */
public class PassengerController {
    private AddressService addressService;
    private PassengerService passengerService;
    private CompanyPassengerList companyPassengerList;

    public PassengerController() {
        this.passengerService = new PassengerServiceImpl();
        this.companyPassengerList = new CompanyPassengerList();
        this.addressService = new AddressServiceImpl();

    }

    public void addPassengersFromFileList() throws SQLException, DatabaseException {
        List<Passenger> passengerList = companyPassengerList.getPassengersList();
        List<Address> addresses = companyPassengerList.getPassengersAddresesList();
        Address address = new Address();
        Passenger passenger = new Passenger();

        for (int i = 0; i < passengerList.size(); i++) {
            address = addressService.save(addresses.get(i));
            passenger = passengerList.get(i);
            passenger.setAddressId(address.getAddressId());
            passengerService.save(passenger);
        }

    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        PassengerController passengerController = new PassengerController();
        passengerController.addPassengersFromFileList();
    }
}

