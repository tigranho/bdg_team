package jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @ManyToOne
    @JoinTable(name = "address")
    private Address address_id;

    public Passenger(String name, String phone, Address address_id) {
        this.name = name;
        this.phone = phone;
        this.address_id = address_id;
    }

    @Override
    public String toString() {
        return "\nPassenger [" +
                " id " + getId() +
                ", name " + getName() +
                ", phone " + getPhone() +
                ", country " + new Address().getCountry() +
                ", city" + new Address().getCity() + "]";
    }
}