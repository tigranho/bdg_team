package lesson10.airport_management_system.model;

public class PassengerTrip {
    private Passenger passenger;
    private Trip trip;

    public PassengerTrip() {
    }

    public PassengerTrip(Passenger passenger, Trip trip) {
        this.passenger = passenger;
        this.trip = trip;
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
