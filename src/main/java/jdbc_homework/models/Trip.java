package jdbc_homework.models;

import java.time.LocalDateTime;
import java.util.Set;

public class Trip {
    private int tripNumber;
    private Company company;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String fromTown;
    private String toTown;
    private Set<Passenger> passengers;

    public Trip(Company company, LocalDateTime timeIn, LocalDateTime timeOut, String fromTown, String toTown) {
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.fromTown = fromTown;
        this.toTown = toTown;
    }

    public int getTripNumber() {
        return tripNumber;
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

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber=" + tripNumber +
                ", company=" + company +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", fromTown='" + fromTown + '\'' +
                ", toTown='" + toTown + '\'' +
                ", passengers=" + passengers +
                '}';
    }
}
