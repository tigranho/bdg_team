package jdbctaskusingjpaproviderhibernate.service.serviceimpl;


import jdbctaskusingjpaproviderhibernate.dao.AddressDao;
import jdbctaskusingjpaproviderhibernate.dao.daoImpl.AddressDaoImpl;
import jdbctaskusingjpaproviderhibernate.entity.Address;
import jdbctaskusingjpaproviderhibernate.service.AddressService;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 20.09.2020.
 */
public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao;

    public AddressServiceImpl() {
        this.addressDao = new AddressDaoImpl();
    }

    @Override
    public Address getById(long id) throws SQLException {
        return this.addressDao.fetchById(id);
    }

    @Override
    public Set<Address> getAll() throws SQLException {
        return this.addressDao.fetchAll();
    }

    @Override
    public Address save(Address address) throws SQLException {
        return addressDao.save(address);
    }

    @Override
    public Address update(Address address) throws SQLException {
        return addressDao.update(address);
    }

    @Override
    public void delete(long addressId) throws SQLException {
        addressDao.delete(addressId);
    }
}
