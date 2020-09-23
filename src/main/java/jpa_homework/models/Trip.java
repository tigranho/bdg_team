package jpa_homework.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "companyID")
    private Company company;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String townFrom;
    private String townTo;
    @Transient
    private Set<Passenger> passengers;

    public Trip() {}

    public Trip(Company company, LocalDateTime timeIn, LocalDateTime timeOut, String townFrom, String townTo) {
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townFrom = townFrom;
        this.townTo = townTo;
    }

    public int getId() {
        return id;
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

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
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
                "id=" + id +
                ", company=" + company +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", passengers=" + passengers +
                '}';
    }
}
