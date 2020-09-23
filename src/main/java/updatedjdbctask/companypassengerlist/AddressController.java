package updatedjdbctask.companypassengerlist;


import updatedjdbctask.service.AddressService;
import updatedjdbctask.service.serviceimpl.AddressServiceImpl;

/**
 * Created by User on 20.09.2020.
 */
public class AddressController {
    private AddressService addressService;

    public AddressController() {
        this.addressService = new AddressServiceImpl();
    }

}
