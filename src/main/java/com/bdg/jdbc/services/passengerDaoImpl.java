package com.bdg.jdbc.services;

import com.bdg.jdbc.dao.passengerDao;
import com.bdg.jdbc.models.Passenger;
import com.bdg.jdbc.models.Trip;

import java.util.List;
import java.util.Set;

public class passengerDaoImpl implements passengerDao {
    @Override
    public Passenger getById(long id) {
        return null;
    }

    @Override
    public Set<Passenger> getAll() {
        return null;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Passenger save(Passenger passenger) {
        return null;
    }

    @Override
    public Passenger update(Passenger passenger) {
        return null;
    }

    @Override
    public void delete(long passengerId) {

    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}
