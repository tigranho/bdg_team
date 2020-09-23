package updatedjdbctask.model;

/**
 * Created by User on 17.09.2020.
 */
public class PassengerTrip {
    private Long tripId;
    private Long passengerId;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }
}
