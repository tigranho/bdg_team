package airportamangmentsystems.service.implementation;


import airportamangmentsystems.model.Trip;
import airportamangmentsystems.repository.TripRepo;
import airportamangmentsystems.repository.implementation.TripRepoImplement;
import airportamangmentsystems.service.TripService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class TripServiceImplementation implements TripService {

    TripRepo tripRepo;

    TripServiceImplementation() {
        this.tripRepo = new TripRepoImplement();
    }

    @Override
    public Trip getById(long id) throws SQLException {
        return tripRepo.getById(id);
    }

    @Override
    public Set<Trip> getAll() throws SQLException {
        return tripRepo.getAll();
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        return tripRepo.get(page, perPage, sort);
    }

    @Override
    public Trip save(Trip passenger) throws SQLException {
        return tripRepo.save(passenger);
    }

    @Override
    public Trip update(Trip passenger) throws SQLException {
        return tripRepo.update(passenger);
    }

    @Override
    public void delete(long tripId) throws SQLException {
        tripRepo.delete(tripId);
    }

    @Override
    public List<Trip> getTripsFrom(String city) throws SQLException {
        return tripRepo.getTripsFrom(city);
    }

    @Override
    public List<Trip> getTripsTo(String city) throws SQLException {
        return tripRepo.getTripsTo(city);
    }
}
