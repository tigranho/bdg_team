package lesson10.airport_management_system.service;

import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;

import java.util.List;
import java.util.Set;

public interface TripService {
    Trip getTrip(long id);

    Set<Trip> findAll();

    Set<Trip> getTrips(int page, int perPage, String sort);

    Trip create(Trip trip);

    Trip edit(Trip trip);

    void remove(long tripId);

    Set<Trip> getPassengerTrips(long passengerId);

    List<Trip> getTripsFromCity(String fromCity);

    List<Trip> getTripsToCity(String toCity);

    void createAll(String path);

    Set<Passenger> getTripPassengers(long tripId);
}
