package jdbc.airoport_management_system.entity;

public class PassengerTrip {
    private Passenger passenger;
    private Trip trip;

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
