package updatedjdbctask.service;



import updatedjdbctask.exceptions.DatabaseException;
import updatedjdbctask.model.Passenger;
import updatedjdbctask.model.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface PassengerService {
    Passenger getById(long id) throws SQLException, DatabaseException;
    Set<Passenger> getAll() throws SQLException, DatabaseException;
    Set<Passenger>get(int page, int perPage, String sort) throws SQLException;
    Passenger save(Passenger passenger) throws SQLException, DatabaseException;
    Passenger update(Passenger passenger) throws SQLException, DatabaseException;
    void delete(long passengerId) throws SQLException, DatabaseException;
    List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException, DatabaseException;
    void registerTrip(Trip trip, Passenger passenger) throws SQLException, DatabaseException;
    void cancelTrip(long passengerId, long tripNumber) throws SQLException, DatabaseException;

}
