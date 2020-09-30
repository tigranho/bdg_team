package jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trip_number;
    @ManyToOne
    @JoinTable(name = "companies")
    private Company company_id;
    private Timestamp time_in;
    private Timestamp time_out;
    private String town_to;
    private String town_from;
    private String id;

    public Trip(int trip_number, Company company_id, Timestamp time_in, Timestamp time_out, String town_to, String town_from) {
        this.trip_number = trip_number;
        this.company_id = company_id;
        this.time_in = time_in;
        this.time_out = time_out;
        this.town_to = town_to;
        this.town_from = town_from;
    }

    @Override
    public String toString() {
        return "\nTrip {" +
                " trip number " + getTrip_number() +
                ", company id " + getCompany_id() +
                ", time in " + getTime_in() +
                ", time out " + getTime_out() +
                ", town to " + getTown_to() +
                ", town from " + getTown_from() + "]";
    }
}