package jdbclesson.model;

public class Passenger {

    private int id;
    private String name;
    private String phone;
    private int address;

    public Passenger(int id, String name, String phone, int address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "\nid " + getId() +
                ", name " + getName() +
                ", phone " + getPhone() +
                ", address " + new Address().getCountry() +
                ", " + new Address().getCity();
    }
}