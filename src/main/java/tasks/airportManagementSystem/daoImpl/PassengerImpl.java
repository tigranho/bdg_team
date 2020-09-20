package tasks.airportManagementSystem.daoImpl;

import tasks.airportManagementSystem.dao.Passenger;
import tasks.airportManagementSystem.dao.Trip;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class PassengerImpl implements Passenger {
    @Override
    public Passenger getById(long id) {
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

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}
