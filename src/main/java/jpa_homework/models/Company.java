package jpa_homework.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate foundingDate;
    @Transient
    private Set<Trip> trips;

    public Company() {}

    public Company(String name, LocalDate foundDate) {
        this.name = name;
        this.foundingDate = foundDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundDate=" + foundingDate +
                ", trips=" + trips +
                '}';
    }
}
