package com.companyandproduct.airportservice.commonpackage;



import lombok.*;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adress {
    int addressId;
    int phone;
    String city;
    String country;

public Adress(int phone, String city, String country){
    this.phone= phone;
    this.city = city;
    this.country = country;
}



}
