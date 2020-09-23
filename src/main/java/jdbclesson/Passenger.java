package jdbclesson;

public class Passenger {

    private int id;
    private String name;
    private String phone;
    private Address address;

    public Passenger(int id, String name, String phone, Object address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = (Address) address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}