package tasks.airportManagementSystem.model;

import java.sql.Time;
import java.time.LocalDate;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class Trip {
    int id;
    Company company;
    LocalDate time_in;
    LocalDate time_out;
    String city_from;
    String city_too;

    public Trip(Company company, LocalDate time_in, LocalDate time_out, String city_from, String city_too) {
        this.company = company;
        this.time_in = time_in;
        this.time_out = time_out;
        this.city_from = city_from;
        this.city_too = city_too;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDate getTime_in() {
        return time_in;
    }

    public void setTime_in(LocalDate time_in) {
        this.time_in = time_in;
    }

    public LocalDate getTime_out() {
        return time_out;
    }

    public void setTime_out(LocalDate time_out) {
        this.time_out = time_out;
    }

    public String getCity_from() {
        return city_from;
    }

    public void setCity_from(String city_from) {
        this.city_from = city_from;
    }

    public String getCity_too() {
        return city_too;
    }

    public void setCity_too(String city_too) {
        this.city_too = city_too;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", company=" + company +
                ", time_in=" + time_in +
                ", time_out=" + time_out +
                ", city_from='" + city_from + '\'' +
                ", city_too='" + city_too + '\'' +
                '}';
    }
}
