package jdbclesson.model;

public class TripPassenger {

    private Passenger passenger_id;
    private Trip trip_id;

    public TripPassenger() {
    }

    public TripPassenger(Passenger passenger_id, Trip trip_id) {
        this.passenger_id = passenger_id;
        this.trip_id = trip_id;
    }

    public Passenger getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Passenger passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Trip getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Trip trip_id) {
        this.trip_id = trip_id;
    }

    @Override
    public String toString() {
        return "";
    }
}