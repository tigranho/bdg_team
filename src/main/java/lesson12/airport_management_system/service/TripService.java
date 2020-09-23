package lesson12.airport_management_system.service;

import lesson12.airport_management_system.entity.Passenger;
import lesson12.airport_management_system.entity.Trip;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TripService {
    Optional<Trip> getTrip(long id);

    Set<Trip> findAll();

    Set<Trip> getTrips(int page, int perPage, String sort);

    Optional<Trip> create(Trip trip);

    Optional<Trip> edit(Trip trip);

    void remove(long tripId);

    Set<Trip> getPassengerTrips(long passengerId);

    List<Trip> getTripsFromCity(String fromCity);

    List<Trip> getTripsToCity(String toCity);

    void loadTripsInfoFromFileAndCreateAll(String path);

    Set<Passenger> getTripPassengers(long tripId);
}
