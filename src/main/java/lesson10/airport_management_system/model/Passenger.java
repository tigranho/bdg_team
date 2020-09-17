package lesson10.airport_management_system.model;

import java.util.Set;
import java.util.StringJoiner;

public class Passenger {
    private long id;
    private String name;
    private String phone;
    private Address address;
    Set<Trip> trips;

    public Passenger() {
    }

    public Passenger(String name, String phone, Address address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Passenger.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("phone='" + phone + "'")
                .add("address=" + address)
                .toString();
    }
}
