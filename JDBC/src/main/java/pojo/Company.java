package pojo;

public class Company {

    private String name;
    private String found_date;

    public Company(String name, String found_date) {
        this.name = name;
        this.found_date = found_date;
    }

    public String getName() {
        return name;
    }

    public String getFound_date() {
        return found_date;
    }
}
