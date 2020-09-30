package jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "found_date")
    private String found_date;

    public Company(String name, String found_date) {
        this.name = name;
        this.found_date = found_date;
    }

    @Override
    public String toString() {
        return "\nCompany [" +
                " id " + getId() +
                ", name " + getName() +
                ", found date " + getFound_date() + "]";
    }
}