package jdbc_homework.models;

import java.time.LocalDate;
import java.util.Set;

public class Company {
    private int id;
    private String name;
    private LocalDate foundDate;
    private Set<Trip> trips;

    public Company(String name, LocalDate foundDate) {
        this.name = name;
        this.foundDate = foundDate;
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

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundDate=" + foundDate +
                ", trips=" + trips +
                '}';
    }
}
