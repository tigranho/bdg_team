package lesson12.airport_management_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "time_in", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timeIn;

    @Column(name = "time_out", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime timeOut;

    @Column(name = "from_city", nullable = false, length = 100)
    private String fromCity;

    @Column(name = "to_city", nullable = false, length = 100)
    private String toCity;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "passenger_trip",
            joinColumns = {
                    @JoinColumn(name = "trip_id", referencedColumnName = "id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    })
    private Set<Passenger> passengers;

    public Trip(Company company, LocalDateTime timeIn, LocalDateTime timeOut, String fromCity, String toCity) {
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        passenger.getTrips().add(this);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        passenger.getTrips().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id &&
                Objects.equals(timeIn, trip.timeIn) &&
                Objects.equals(timeOut, trip.timeOut) &&
                Objects.equals(fromCity, trip.fromCity) &&
                Objects.equals(toCity, trip.toCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeIn, timeOut, fromCity, toCity);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Trip.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("company=" + company)
                .add("timeIn=" + timeIn)
                .add("timeOut=" + timeOut)
                .add("fromCity='" + fromCity + "'")
                .add("toCity='" + toCity + "'")
                .toString();
    }
}
