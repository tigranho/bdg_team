package jpa.entity;

import jdbclesson.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "passenger_trip")
public class PassengerTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(targetEntity = Passenger.class)
    @JoinTable(name = "passengers")
    private List<Passenger> passenger_id;

    @ManyToMany(targetEntity = Trip.class)
    @JoinTable(name = "trip")
    private List<Trip> trip_id;

    public PassengerTrip(List<Passenger> passenger_id, List<Trip> trip_id) {
        this.passenger_id = passenger_id;
        this.trip_id = trip_id;
    }

    @Override
    public String toString() {
        return "\nPassenger Trip [" +
                " passenger id " + getPassenger_id() +
                ", trip id " + getTrip_id() +
                ", name " + new Passenger().getName() +
                ", phone " + new Passenger().getPhone() +
                ", country " + new Address().getCountry() +
                ", city" + new Address().getCity() +
                ", time in " + new Trip().getTime_in() +
                ", time out " + new Trip().getTime_out() +
                ", town to " + new Trip().getTown_to() +
                ", town from " + new Trip().getTown_from() + "]";
    }
}