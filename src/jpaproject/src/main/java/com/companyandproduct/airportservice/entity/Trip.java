package com.companyandproduct.airportservice.entity;

import com.sun.istack.NotNull;
import com.sun.xml.fastinfoset.tools.XML_SAX_StAX_FI;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    int number;
    @Column(nullable = false)
    String timeIn;


    @NotNull
    String timeOut;

    @NotNull
    String townTo;
    @Column(nullable = false)
    String townFrom;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "trip")
    Company company;


    public Trip( int number, String timeIn, String timeOut, String townTo, String townFrom) {
        this.number = number;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;

    }


}
