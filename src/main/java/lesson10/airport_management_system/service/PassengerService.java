package lesson10.airport_management_system.service;

import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;

import java.util.Set;

public interface PassengerService {
    Passenger get(long id);

    Set<Passenger> findAll();

    Set<Passenger> getPassengers(int page, int perPage, String sort);

    Passenger create(Passenger passenger);

    void createAll(String path);

    Passenger edit(Passenger passenger);

    void remove(long passengerId);

    Set<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);

}
