package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.PassengerDAO;
import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;
import lesson10.airport_management_system.service.PassengerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerDAO passengerDAO;

    public PassengerServiceImpl(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Override
    public Passenger get(long id) {
        return passengerDAO.getById(id).orElse(null);
    }

    @Override
    public Set<Passenger> findAll() {
        return passengerDAO.getAll();
    }

    @Override
    public Set<Passenger> getPassengers(int page, int perPage, String sort) {
        return passengerDAO.get(page, perPage, sort);
    }

    @Override
    public Passenger create(Passenger passenger) {
        return passengerDAO.save(passenger).orElse(null);
    }

    @Override
    public Passenger edit(Passenger passenger) {
        return passengerDAO.update(passenger).orElse(null);
    }

    @Override
    public void remove(long passengerId) {
        passengerDAO.delete(passengerId);
    }

    @Override
    public Set<Passenger> getPassengersOfTrip(long tripNumber) {
        return passengerDAO.getPassengersOfTrip(tripNumber);
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        passengerDAO.registerTrip(trip, passenger);
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        passengerDAO.cancelTrip(passengerId, tripNumber);
    }

    @Override
    public void createAll(String path) {
        List<String> lineData = null;
        try (Stream<String> fileContent = Files.lines(Paths.get(path))) {
            lineData = fileContent.skip(1).map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: message: " + e.getMessage());
        }
        passengerDAO.saveAll(lineData);
    }
}
