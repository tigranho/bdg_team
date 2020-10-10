package com.bdg.hibernate_jpa.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate founding_date;

    @OneToMany(mappedBy = "company")
    private Set<Trip> trips;

    public Company() {
    }

    public Company(String name, LocalDate founding_date) {
        this.name = name;
        this.founding_date = founding_date;
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

    public LocalDate getFounding_date() {
        return founding_date;
    }

    public void setFounding_date(LocalDate founding_date) {
        this.founding_date = founding_date;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
}
