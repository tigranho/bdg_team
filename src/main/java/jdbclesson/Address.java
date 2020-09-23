package jdbclesson;

public class Address {

    private int id;
    private String country;
    private String city;

    public Address() {}

    public Address(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}