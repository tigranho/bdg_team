package jpa_homework.models;

import javax.persistence.*;

@Entity
@Table(name = "passenger_trip")
public class PassengerTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "passengerID")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "tripID")
    private Trip trip;

    public PassengerTrip() {}

    public PassengerTrip(Passenger passenger, Trip trip) {
        this.passenger = passenger;
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
