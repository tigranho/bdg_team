package airportamangmentsystems.service.implementation;

import airportamangmentsystems.model.Passenger;
import airportamangmentsystems.model.Trip;
import airportamangmentsystems.repository.PassengerRepo;
import airportamangmentsystems.repository.implementation.PassengerRepoImplement;
import airportamangmentsystems.service.PassengerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements PassengerService {
    PassengerRepo passengerRepo;

    public PassengerServiceImpl() {
        this.passengerRepo = new PassengerRepoImplement();
    }

    @Override
    public Passenger getById(long id) throws SQLException {
        return passengerRepo.getById(id);
    }

    @Override
    public Set<Passenger> getAll() throws SQLException {
        return passengerRepo.getAll();
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) throws SQLException {
        return passengerRepo.get(page, perPage, sort);
    }

    @Override
    public Passenger save(Passenger passenger) throws SQLException {
        return passengerRepo.save(passenger);
    }

    @Override
    public Passenger update(Passenger passenger) throws SQLException {
        return passengerRepo.update(passenger);
    }

    @Override
    public void delete(long passengerId) throws SQLException {
        passengerRepo.delete(passengerId);
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException {
        return passengerRepo.getPassengersOfTrip(tripNumber);
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) throws SQLException {
        passengerRepo.registerTrip(trip, passenger);
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) throws SQLException {
        passengerRepo.cancelTrip(passengerId, tripNumber);
    }
}
