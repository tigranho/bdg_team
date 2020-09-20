package tasks.airportManagementSystem.daoImpl;


import tasks.airportManagementSystem.dao.PassengerDAO;
import tasks.airportManagementSystem.model.Passenger;
import tasks.airportManagementSystem.model.Trip;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class PassengerImpl implements PassengerDAO {
    @Override
    public Passenger getById(int id) {
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
    public void delete(int passengerId) {

    }

    @Override
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(int passengerId, int tripNumber) {

    }
}
