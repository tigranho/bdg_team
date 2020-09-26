package lesson12.airport_management_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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

    public static String frequencySort(String s)          // lets assume that input string is "tree"
    {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }                                                       //  now after this my HashMap will have values (t,1),(r,1),(e,2)

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));  //my question is about this constructor in priority queue, There are just two input parameters , how subtracting two things will help in sorting ??

        maxHeap.addAll(map.keySet()); // i guess the keyset is now 't,r,e'  but constructor has only 2 parameters, what is going on ? How will the constructor help in setting the sorting behaviour of prioroty queue?

        StringBuilder result = new StringBuilder();

        while (maxHeap.isEmpty()) {
            char current = maxHeap.remove();
            for (int i = 0; i < map.get(current); i++) {
                result.append(current);
            }
        }
        return result.toString();
    }
}
