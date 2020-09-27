package jdbclesson.model;

public class Address {

    private int id;
    private String country;
    private String city;

    public Address() {
    }

    public Address(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "\nid " + getId() +
                " country " + getCountry() +
                " city " + getCity();
    }
}