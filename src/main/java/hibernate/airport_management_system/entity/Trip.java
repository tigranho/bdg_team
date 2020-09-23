package hibernate.airport_management_system.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalTime time_in;
    private LocalTime time_out;
    private String to_town;
    private String from_town;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "trips")
    private Set<Passenger> passengers;

    public Trip() {
    }

    public Trip(LocalTime time_in, LocalTime time_out, String to_town, String from_town) {
        this.time_in = time_in;
        this.time_out = time_out;
        this.to_town = to_town;
        this.from_town = from_town;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getTime_in() {
        return time_in;
    }

    public void setTime_in(LocalTime time_in) {
        this.time_in = time_in;
    }

    public LocalTime getTime_out() {
        return time_out;
    }

    public void setTime_out(LocalTime time_out) {
        this.time_out = time_out;
    }

    public String getTo_town() {
        return to_town;
    }

    public void setTo_town(String to_town) {
        this.to_town = to_town;
    }

    public String getFrom_town() {
        return from_town;
    }

    public void setFrom_town(String from_town) {
        this.from_town = from_town;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
}
