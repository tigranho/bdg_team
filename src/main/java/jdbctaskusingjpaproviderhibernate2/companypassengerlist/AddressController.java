package jdbctaskusingjpaproviderhibernate2.companypassengerlist;



import jdbctaskusingjpaproviderhibernate2.entity.Address;
import jdbctaskusingjpaproviderhibernate2.exceptions.DatabaseException;
import jdbctaskusingjpaproviderhibernate2.service.AddressService;
import jdbctaskusingjpaproviderhibernate2.service.serviceimpl.AddressServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 20.09.2020.
 */
public class AddressController {
    private AddressService addressService;
    private CompanyPassengerList companyPassengerList;

    public AddressController(){
        this.addressService = new AddressServiceImpl();
        this.companyPassengerList = new CompanyPassengerList();
    }

    public void addAddrresesFromFileList() throws SQLException, DatabaseException {
        List<Address> addresses = companyPassengerList.getPassengersAddresesList();
        Address address = new Address();

        for (int i = 0; i < addresses.size(); i++) {
            address = addressService.save(addresses.get(i));
            System.out.println(address);
        }

    }
    public void updateAddressData(Address address) throws SQLException {
        this.addressService.update(address);
    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        AddressController addressController = new AddressController();
     //   addressController.addAddrresesFromFileList();
//        Address address = new Address();
//        address.setAddressId(1L);
//        address.setCity("aasdfhfffa");
//        address.setCountry("ddddddd");
//        addressController.updateAddressData(address);
        //addressController.addressService.delete(78L);
//        System.out.println(addressController.addressService.getById(1L));
//        System.out.println(addressController.addressService.getAll());
        Address address = addressController.addressService.getById(286);
        System.out.println(address);
        System.out.println(address.getPassengers());
    }
}
