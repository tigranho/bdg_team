package tasks.airportManagementSystem.JDBC.daoImpl;

import tasks.airportManagementSystem.JDBC.dao.TripDAO;
import tasks.airportManagementSystem.JDBC.model.Company;
import tasks.airportManagementSystem.JDBC.model.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static tasks.airportManagementSystem.JDBC.DbConnection.getConnection;


/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class TripImpl implements TripDAO {
    @Override
    public Trip getById(int id) {
        String query = "SELECT * FROM TRIP LEFT JOIN COMPANIES C " +
                "ON C.ID = TRIP.COMPANY_ID WHERE TRIP.ID = ?";
        Trip trip = new Trip();
        Company company;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                company = new CompanyImpl().getById(rs.getInt("company_id"));
                trip.setId(id);
                trip.setTime_in(rs.getTimestamp("Time_in").toLocalDateTime());
                trip.setTime_out(rs.getTimestamp("Time_out").toLocalDateTime());
                trip.setCity_from(rs.getString("City_from"));
                trip.setCity_too(rs.getString("City_too"));
                trip.setCompany(company);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        String query = "SELECT * FROM TRIP " +
                "LEFT JOIN COMPANIES A ON Trip.COMPANY_ID = A.ID";
        Set<Trip> trips = new LinkedHashSet<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trips.add(getById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trips;
    }


    @Override
    public Set<Trip> get(int page, int perPage, String sort) {
        String query = "SELECT * FROM TRIP ORDER BY " + sort + " LIMIT ? OFFSET ?";
        Set<Trip> trips = new LinkedHashSet<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, perPage);
            stmt.setInt(2, page);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trips.add(getById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trips;
    }

    @Override
    public Trip save(Trip trip) {
        String query = "INSERT INTO TRIP (COMPANY_ID, TIME_IN, TIME_OUT, CITY_FROM, CITY_TOO)" +
                " VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, trip.getCompany().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(trip.getTime_in()));
            stmt.setTimestamp(3, Timestamp.valueOf(trip.getTime_out()));
            stmt.setString(4, trip.getCity_from());
            stmt.setString(5, trip.getCity_too());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next())
                trip.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        String query = "UPDATE TRIP " +
                "SET COMPANY_ID = ?,TIME_IN = ?,TIME_OUT = ?, CITY_FROM = ?, CITY_TOO = ? " +
                "WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trip.getCompany().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(trip.getTime_in()));
            stmt.setTimestamp(3, Timestamp.valueOf(trip.getTime_out()));
            stmt.setString(4, trip.getCity_from());
            stmt.setString(5, trip.getCity_too());
            stmt.setInt(6, trip.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trip;
    }

    @Override
    public void delete(int tripId) {
        String query = "DELETE FROM TRIP WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tripId);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM TRIP WHERE TRIP.CITY_FROM = ?";
        Trip trip;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trip = getById(rs.getInt("id"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM TRIP WHERE TRIP.CITY_TOO = ?";
        Trip trip;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trip = getById(rs.getInt("id"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return trips;
    }
}
