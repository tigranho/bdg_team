package com.companyandproduct.airportservice.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String foundDate;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "trip_id",nullable = false)
    Trip trip;


    public Company( String name, String foundDate) {

        this.name = name;
        this.foundDate = foundDate;
        this.trip = trip;
    }




}
