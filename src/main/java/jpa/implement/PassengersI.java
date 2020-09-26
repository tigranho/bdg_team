package jpa.implement;

import jpa.entity.Passenger;
import jpa.dao.PassengersDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class PassengersI implements PassengersDao {
    @Override
    public Passenger getById(long id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Set<Passenger> getAll() {
        return null;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Passenger save(Passenger passenger) {
        return null;
    }

    @Override
    public Passenger update(Passenger passenger) {
        return null;
    }

    @Override
    public void delete(long passengerId) {

    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        return null;
    }
}
