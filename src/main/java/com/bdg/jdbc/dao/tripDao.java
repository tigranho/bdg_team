package com.bdg.jdbc.dao;


import com.bdg.jdbc.models.Trip;

import java.util.List;
import java.util.Set;

public interface tripDao {
    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip passenger);

    Trip update(Trip passenger);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);
}
