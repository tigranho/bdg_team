package jdbctaskusingjpaproviderhibernate.companypassengerlist;


import jdbctaskusingjpaproviderhibernate.entity.Address;
import jdbctaskusingjpaproviderhibernate.exceptions.DatabaseException;
import jdbctaskusingjpaproviderhibernate.service.AddressService;
import jdbctaskusingjpaproviderhibernate.service.serviceimpl.AddressServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 20.09.2020.
 */
public class AddressController {
    private AddressService addressService;
    private CompanyPassengerList companyPassengerList;

    public AddressController() {
        this.addressService = new AddressServiceImpl();
        this.companyPassengerList = new CompanyPassengerList();
    }

    public void addPassengersFromFileList() throws SQLException, DatabaseException {
        List<Address> addresses = companyPassengerList.getPassengersAddresesList();
        Address address = new Address();

        for (int i = 0; i < addresses.size(); i++) {
            address = addressService.save(addresses.get(i));
            System.out.println(address);
        }

    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        AddressController addressController = new AddressController();
        addressController.addPassengersFromFileList();
//        System.out.println(addressController.addressService.getAll());
//        System.out.println(addressController.addressService.getById(2));
//        Address address = new Address();
//        address.setAddressId(Long.valueOf(1));
//        address.setCity("Gyumri");
//        address.setCountry("Armenia");
//        System.out.println(addressController.addressService.update(address));
        //  addressController.addressService.delete(2);
    }
}
