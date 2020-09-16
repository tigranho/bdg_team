package lesson10.airport_management_system.dao;

import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TripDAO {
    Optional<Trip> getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Optional<Trip> save(Trip trip);

    Optional<Trip> update(Trip trip);

    void delete(long tripId);

    Set<Trip> findTripsByPassengerId(long passengerId);

    List<Trip> getTripsByFromCity(String fromCity);

    List<Trip> getTripsByToCity(String toCity);

    boolean saveAll(List<String> trips);

    Set<Passenger> findPassengersByTripId(long tripId);
}
