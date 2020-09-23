package lesson12.airport_management_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "founding_date", columnDefinition = "DATE", nullable = false)
    private LocalDate foundingDate;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Trip> trips;

    public Company(String name, LocalDate foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
        trip.setCompany(this);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        trip.setCompany(null);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Company.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("foundingDate=" + foundingDate)
                .toString();
    }
}
