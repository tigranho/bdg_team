package lesson12.airport_management_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;


@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"country", "city"}))
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String city;

    @OneToMany(mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Passenger> passengers;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        passenger.setAddress(this);
    }

    public void removeComment(Passenger passenger) {
        passengers.remove(passenger);
        passenger.setAddress(null);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("country='" + country + "'")
                .add("city='" + city + "'")
                .toString();
    }
}
