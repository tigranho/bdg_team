package jdbctaskusingjpaproviderhibernate.service.serviceimpl;


import jdbctaskusingjpaproviderhibernate.dao.TripDao;
import jdbctaskusingjpaproviderhibernate.dao.daoImpl.TripDaoImpl;
import jdbctaskusingjpaproviderhibernate.entity.Trip;
import jdbctaskusingjpaproviderhibernate.exceptions.DatabaseException;
import jdbctaskusingjpaproviderhibernate.service.TripService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public class TripServiceImpl implements TripService {

    private TripDao tripDao;

    public TripServiceImpl() {
        this.tripDao = new TripDaoImpl();
    }

    @Override
    public Trip getById(long id) throws DatabaseException {
        try {
            return tripDao.fetchById(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Set<Trip> getAll() throws DatabaseException {
        try {
            return tripDao.fetchAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Set<Trip> get(int page, int perPage, String sort) throws SQLException {
        return this.tripDao.fetch(page, perPage, sort);
    }

    @Override
    public Trip save(Trip trip) throws DatabaseException {
        try {
            return tripDao.save(trip);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Trip update(Trip trip) throws DatabaseException {
        try {
            return tripDao.update(trip);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(long tripId) throws DatabaseException {
        try {
            tripDao.delete(tripId);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) throws DatabaseException {
        try {
            return tripDao.getTripsFrom(city);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Trip> getTripsTo(String city) throws DatabaseException {
        try {
            return tripDao.getTripsTo(city);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
