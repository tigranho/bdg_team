package jdbctaskusingjpaproviderhibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
@Entity
@Table(name = "passenger")
@Data
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "name")
    private String name;

    private String phone;
    //    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "passenger_trip",
            joinColumns = {@JoinColumn(name = "passenger_id")},
            inverseJoinColumns = {@JoinColumn(name = "trip_id")})
    Set<Trip> trips = new HashSet<>();

    public Passenger(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Passenger(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Passenger passenger = (Passenger) o;

        if (name != null ? !name.equals(passenger.name) : passenger.name != null) return false;
        if (phone != null ? !phone.equals(passenger.phone) : passenger.phone != null) return false;
        if (address != null ? !address.equals(passenger.address) : passenger.address != null) return false;
        return trips != null ? trips.equals(passenger.trips) : passenger.trips == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (trips != null ? trips.hashCode() : 0);
        return result;
    }
}
