package tasks.airportManagementSystem.daoImpl;

import tasks.airportManagementSystem.dao.TripDAO;
import tasks.airportManagementSystem.model.Trip;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class TripImpl implements TripDAO {
    @Override
    public Trip getById(int id) {
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
    public Trip save(Trip trip) {
        return null;
    }

    @Override
    public Trip update(Trip trip) {
        return null;
    }

    @Override
    public void delete(int tripId) {

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
