package jdbctaskusingjpaproviderhibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


/**
 * Created by User on 17.09.2020.
 */
@Entity
@Table(name = "address")
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;


//    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Passenger> passengers;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Address address = (Address) o;

        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return passengers != null ? passengers.equals(address.passengers) : address.passengers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        return result;
    }
}
