import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trip {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:Mysql:@127.0.0.1:3306";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    private long number;
    private Company company;
    private String timeIn;
    private String timeOut;
    private String destination;
    private String origin;
    private List<Passenger> passengers;

    public long getNumber() {
        return number;
    }

    // TODO
    public Trip getById(long id) {
        Trip trip = new Trip();
        return trip;
    }

    // TODO
    public Set<Trip> getAll() {
        Set<Trip> trips = new HashSet<>();
        return trips;
    }

    // TODO
    public Set<Trip> get(int page, int perPage, String sort) {
        Set<Trip> trips = new HashSet<>();
        return trips;
    }

    // TODO
    public Trip save(Trip passenger) {
        Trip trip = new Trip();
        return trip;
    }

    // TODO
    public Trip update(Trip passenger) {
        Trip trip = new Trip();
        return trip;
    }

    // TODO
    public void delete(long tripId) {
    }

    // TODO
    public List<Trip> getTripsFrom(String city) {
        List<Trip> trips = new ArrayList<>();
        return trips;
    }

    // TODO
    public List<Trip> getTripsTo(String city) {
        List<Trip> trips = new ArrayList<>();
        return trips;
    }
}
