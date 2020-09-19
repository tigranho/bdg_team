package pojo;

import java.util.List;

public class Trip {

    private long number;
    private Company company;
    private String timeIn;
    private String timeOut;
    private String destination;
    private String origin;
    private List<Passenger> passengers;

    public Trip(long number, Company company, String timeIn, String timeOut, String destination, String origin, List<Passenger> passengers) {
        this.number = number;
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.destination = destination;
        this.origin = origin;
        this.passengers = passengers;
    }

    public Trip(long number, Company company, String timeIn, String timeOut, String destination, String origin) {
        this.number = number;
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.destination = destination;
        this.origin = origin;
    }

    public long getNumber() {
        return number;
    }

    public Company getCompany() {
        return company;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
