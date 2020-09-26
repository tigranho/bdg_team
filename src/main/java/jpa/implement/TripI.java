package jpa.implement;

import jpa.entity.Passenger;
import jpa.entity.Trip;
import jpa.dao.TripDao;

import java.util.List;
import java.util.Set;

public class TripI implements TripDao {

    @Override
    public Trip getById(long id) {
        return null;
    }

    @Override
    public Set<Trip> getAll() {
        return null;
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip passenger) {
        return null;
    }

    @Override
    public Trip update(Trip passenger) {
        return null;
    }

    @Override
    public void delete(long tripId) {

    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        return null;
    }

    @Override
    public void registerTrip(TripI trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}