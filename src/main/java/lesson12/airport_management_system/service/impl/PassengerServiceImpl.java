package lesson12.airport_management_system.service.impl;

import lesson12.airport_management_system.dao.PassengerDAO;
import lesson12.airport_management_system.entity.Passenger;
import lesson12.airport_management_system.entity.Trip;
import lesson12.airport_management_system.service.PassengerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerDAO passengerDAO;

    public PassengerServiceImpl(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Override
    public Optional<Passenger> get(long id) {
        return passengerDAO.getById(id);
    }

    @Override
    public Set<Passenger> findAll() {
        return passengerDAO.getAll();
    }

    @Override
    public Set<Passenger> getPassengers(int offset, int limit, String sort) {
        return passengerDAO.get(offset, limit, sort);
    }

    @Override
    public Optional<Passenger> create(Passenger passenger) {
        return passengerDAO.save(passenger);
    }

    @Override
    public Optional<Passenger> edit(Passenger passenger) {
        return passengerDAO.update(passenger);
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
    public void loadPassengersTripsInfoFromFile(String path) {
        List<String> lines = null;
        try (Stream<String> stream = Files.lines(Path.of(path))) {
            lines = stream.skip(1).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        passengerDAO.readPassengersTripsInfoAndRegisterAll(lines);
    }

    @Override
    public void loadPassengersInfoFromFileAndCreateAll(String path) {
        List<String> lineData = null;
        try (Stream<String> fileContent = Files.lines(Paths.get(path))) {
            lineData = fileContent.skip(1).map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: message: " + e.getMessage());
        }
        passengerDAO.saveAll(lineData != null ? lineData : Collections.emptyList());
    }
}
