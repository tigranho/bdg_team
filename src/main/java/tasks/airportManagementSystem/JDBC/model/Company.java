package tasks.airportManagementSystem.JDBC.model;

import java.time.LocalDate;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class Company {
    int id;
    String name;
    LocalDate found_date;

    public Company() {
    }

    public Company(String name, LocalDate found_date) {
        this.name = name;
        this.found_date = found_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFound_date() {
        return found_date;
    }

    public void setFound_date(LocalDate found_date) {
        this.found_date = found_date;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", found_date=" + found_date +
                '}';
    }
}
