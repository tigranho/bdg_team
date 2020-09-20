package tasks.airportManagementSystem.model;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class PassengerTrip {
    int id;
    Passenger passenger;
    Trip trip;

    public PassengerTrip() {
    }

    public PassengerTrip(Passenger passenger, Trip trip) {
        this.passenger = passenger;
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PassengerTrip{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", trip=" + trip +
                '}';
    }
}
