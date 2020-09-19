package jdbc.airoport_management_system.entity;

import java.util.Set;

public class Passenger {
    private long id;
    private String name;
    private String phone;
    private Address address;
    private Set<Trip> tripsSet;

    public Passenger(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Trip> getTripsSet() {
        return tripsSet;
    }

    public void setTripsSet(Set<Trip> tripsSet) {
        this.tripsSet = tripsSet;
    }
}
