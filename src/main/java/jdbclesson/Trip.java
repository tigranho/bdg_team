package jdbclesson;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.DuplicateFormatFlagsException;

public class Trip {

    private int id;
    private int trip_number;
    private Timestamp time;
    private int address_id;
    private int passenger_id;

    public Trip() {
    }

    public Trip(int id, int trip_number, Timestamp time, int address_id, int passenger_id) {
        this.id = id;
        this.trip_number = trip_number;
        this.time = time;
        this.address_id = address_id;
        this.passenger_id = passenger_id;
    }

    public int getId() {
        return id;
    }

    public int getTrip_number() {
        return trip_number;
    }

    public Timestamp getTime() {
        return time;
    }

    public int getAddress_id() {
        return address_id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }
}