package jdbctaskusingjpaproviderhibernate2.entity;

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


 //   @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)

   @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Passenger> passengers;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public Address(String country, String city, Set<Passenger> passengers) {
        this.country = country;
        this.city = city;
        this.passengers = passengers;
    }


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Address address = (Address) o;
//
//        return addressId != null ? addressId.equals(address.addressId) : address.addressId == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
//        return result;
//    }
}
