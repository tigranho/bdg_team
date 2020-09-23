package com.companyandproduct.airportservice.entity;

import com.companyandproduct.airportservice.commonpackage.Adress;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, updatable = false)
    String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "passenger_trip", joinColumns
            = @JoinColumn(columnDefinition = "passenger_id"),
            inverseJoinColumns = @JoinColumn(columnDefinition = "trip_id"))
    Set<Trip> trip = new LinkedHashSet<>();


    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "phone",column = @Column(name = "passangers_phone")),
            @AttributeOverride(name = "city", column = @Column(name = "passangers_city")),
            @AttributeOverride(name = "country", column = @Column(name = "passengers_country"))
    })
    Adress adress;

    public Passenger( String name, Set<Trip> trip, Adress adress) {
        this.name = name;
        this.trip = trip;
        this.adress = adress;
    }


}
