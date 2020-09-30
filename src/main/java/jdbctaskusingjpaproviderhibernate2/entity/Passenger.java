package jdbctaskusingjpaproviderhibernate2.entity;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Passenger passenger = (Passenger) o;

        return passengerId != null ? passengerId.equals(passenger.passengerId) : passenger.passengerId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (passengerId != null ? passengerId.hashCode() : 0);
        return result;
    }
}
