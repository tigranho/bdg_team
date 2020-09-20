package tasks.airportManagementSystem.dao;

import tasks.airportManagementSystem.model.Passenger;
import tasks.airportManagementSystem.model.Trip;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 20-Sep-20
 */
public interface PassengerDAO {

        Passenger getById(int id);

        Set<Passenger> getAll();

        Set<Passenger> get(int page, int perPage, String sort);

        Passenger save(Passenger passenger);

        Passenger update(Passenger passenger);

        void delete(int passengerId);

        List<Passenger> getPassengersOfTrip(int tripNumber);

        void registerTrip(Trip trip, Passenger passenger);

        void cancelTrip(int passengerId, int tripNumber);

}
