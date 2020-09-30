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
@Table(name="trip")
@Data
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trip_id")
    private Long tripId;

    @Column(name="trip_number")
    private Long tripNumber;

    @Column(name="time_in")
    private String timeIn;

    @Column(name="time_out")
    private String timeOut;

    @Column(name="town_to")
    private String townTo;

    @Column(name="town_from")
    private String townFrom;


    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")

    @JoinColumn(name = "company_id", nullable = false, insertable = false, updatable = false)
    private Company company;


    @ManyToMany(mappedBy = "trips", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Passenger> passengers = new HashSet<>();

    public Trip(Long tripNumber, Long companyId, String timeIn, String timeOut, String townTo, String townFrom, Company company) {
        this.tripNumber = tripNumber;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;
        this.company = company;
    }

    public Trip(Long tripNumber, Long companyId, String timeIn, String timeOut, String townTo, String townFrom) {
        this.tripNumber = tripNumber;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", tripNumber=" + tripNumber +
                ", timeIn='" + timeIn + '\'' +
                '}';
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Trip trip = (Trip) o;
//
//        return tripId != null ? tripId.equals(trip.tripId) : trip.tripId == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (tripId != null ? tripId.hashCode() : 0);
//        return result;
//    }
}
