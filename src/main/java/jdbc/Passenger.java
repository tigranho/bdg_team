package jdbc;

public class Passenger {
    private String name;
    private String phone;
    private String country;
    private String city;

    public Passenger(String name, String phone, String country, String city) {
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
