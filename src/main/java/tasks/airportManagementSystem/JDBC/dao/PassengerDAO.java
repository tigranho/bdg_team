package tasks.airportManagementSystem.JDBC.dao;

import tasks.airportManagementSystem.JDBC.model.Passenger;
import tasks.airportManagementSystem.JDBC.model.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 20-Sep-20
 */
public interface PassengerDAO {

        Passenger getById(int id) throws SQLException;

        Set<Passenger> getAll() throws SQLException;

        Set<Passenger> get(int page, int perPage, String sort);

        Passenger save(Passenger passenger) throws SQLException;

        Passenger update(Passenger passenger) throws SQLException;

        void delete(int passengerId);

        List<Passenger> getPassengersOfTrip(int tripNumber);

        void registerTrip(Trip trip, Passenger passenger);

        void cancelTrip(int passengerId, int tripNumber);

}
