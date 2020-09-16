package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.TripDAO;
import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;
import lesson10.airport_management_system.service.TripService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TripServiceImpl implements TripService {

    private final TripDAO tripDAO;

    public TripServiceImpl(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    @Override
    public Trip getTrip(long id) {
        return tripDAO.getById(id).orElse(null);
    }

    @Override
    public Set<Trip> findAll() {
        return tripDAO.getAll();
    }

    @Override
    public Set<Trip> getTrips(int page, int perPage, String sort) {
        return tripDAO.get(page, perPage, sort);
    }

    @Override
    public Trip create(Trip trip) {
        return tripDAO.save(trip).orElse(null);
    }

    @Override
    public void createAll(String path) {
        List<String> lineData = null;
        try (Stream<String> fileContent = Files.lines(Paths.get(path))) {
            lineData = fileContent.skip(1).map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: message: " + e.getMessage());
        }
        tripDAO.saveAll(lineData);
    }

    @Override
    public Set<Passenger> getTripPassengers(long tripId) {
        return tripDAO.findPassengersByTripId(tripId);
    }

    @Override
    public Trip edit(Trip trip) {
        return tripDAO.update(trip).orElse(null);
    }

    @Override
    public void remove(long tripId) {
        tripDAO.delete(tripId);
    }

    @Override
    public Set<Trip> getPassengerTrips(long passengerId) {
        return tripDAO.findTripsByPassengerId(passengerId);
    }


    @Override
    public List<Trip> getTripsFromCity(String fromCity) {
        return tripDAO.getTripsByFromCity(fromCity);
    }

    @Override
    public List<Trip> getTripsToCity(String toCity) {
        return tripDAO.getTripsByToCity(toCity);
    }

}
