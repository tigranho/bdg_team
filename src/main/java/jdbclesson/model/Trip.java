package jdbclesson.model;

import java.sql.Timestamp;

public class Trip {

    private int trip_number;
    private Company company_id;
    private Timestamp time_in;
    private Timestamp time_out;
    private String town_to;
    private String town_from;

    public Trip() {
    }

    public Trip(int trip_number, Company company_id, Timestamp time_in, Timestamp time_out, String town_to, String town_from) {
        this.trip_number = trip_number;
        this.company_id = company_id;
        this.time_in = time_in;
        this.time_out = time_out;
        this.town_to = town_to;
        this.town_from = town_from;
    }

    public int getTrip_number() {
        return trip_number;
    }

    public void setTrip_number(int trip_number) {
        this.trip_number = trip_number;
    }

    public Company getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Company company_id) {
        this.company_id = company_id;
    }

    public Timestamp getTime_in() {
        return time_in;
    }

    public void setTime_in(Timestamp time_in) {
        this.time_in = time_in;
    }

    public Timestamp getTime_out() {
        return time_out;
    }

    public void setTime_out(Timestamp time_out) {
        this.time_out = time_out;
    }

    public String getTown_to() {
        return town_to;
    }

    public void setTown_to(String town_to) {
        this.town_to = town_to;
    }

    public String getTown_from() {
        return town_from;
    }

    public void setTown_from(String town_from) {
        this.town_from = town_from;
    }

    @Override
    public String toString() {
        return "\ntrip number " + getTrip_number() +
                ", company id " + getCompany_id() +
                ", time in " + getTime_in() +
                ", time out " + getTime_out() +
                ", town to " + getTown_to() +
                ", town from " + getTown_from();
    }
}