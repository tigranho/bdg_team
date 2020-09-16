package lesson10.airport_management_system.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.StringJoiner;

public class Trip {
    private long id;
    private Company company;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String fromCity;
    private String toCity;
    private Set<Passenger> passengers;

    public Trip(Company company, LocalDateTime timeIn, LocalDateTime timeOut, String fromCity, String toCity) {
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Trip.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("company=" + company)
                .add("timeIn=" + timeIn)
                .add("timeOut=" + timeOut)
                .add("fromCity='" + fromCity + "'")
                .add("toCity='" + toCity + "'")
                .toString();
    }
}
