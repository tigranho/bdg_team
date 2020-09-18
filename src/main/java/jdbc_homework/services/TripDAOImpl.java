package jdbc_homework.services;

import jdbc_homework.dao.TripDAO;
import jdbc_homework.models.Trip;

import java.util.List;
import java.util.Set;

public class TripDAOImpl implements TripDAO {

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
}
