package jdbclesson.dao;

import jdbclesson.model.TripPassenger;

import java.util.Set;

public interface TripPassengerDAO {
    TripPassenger getById(long id);

    Set<TripPassenger> getAll();

    TripPassenger save(TripPassenger address);

    TripPassenger update(TripPassenger address);

    void delete(long addressId);
}
