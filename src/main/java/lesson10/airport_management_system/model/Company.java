package lesson10.airport_management_system.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

public class Company {
    private long id;
    private String name;
    private LocalDate foundingDate;
    private Set<Trip> trips;

    public Company() {
    }

    public Company(String name, LocalDate foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
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
