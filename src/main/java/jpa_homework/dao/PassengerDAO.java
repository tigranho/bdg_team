package jpa_homework.dao;

import jpa_homework.models.Passenger;
import jpa_homework.models.Trip;

import java.util.List;
import java.util.Set;

public interface PassengerDAO {
    Passenger getById(int id);

    Set<Passenger> getAll();

    Set<Passenger> get(int page, int perPage, String sort);

    Passenger save(Passenger passenger);

    Passenger update(Passenger passenger);

    void delete(long passengerId);

    List<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);

}
