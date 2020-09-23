package lesson12.airport_management_system.dao;

import lesson12.airport_management_system.entity.Passenger;
import lesson12.airport_management_system.entity.Trip;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PassengerDAO {
    Optional<Passenger> getById(long id);

    Set<Passenger> getAll();

    Set<Passenger> get(int offset, int limit, String sort);

    Optional<Passenger> save(Passenger passenger);

    boolean saveAll(List<String> passengersAndAddresses);

    Optional<Passenger> update(Passenger passenger);

    void delete(long passengerId);

    Set<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);

    void readPassengersTripsInfoAndRegisterAll(List<String> passengersTrips);
}
