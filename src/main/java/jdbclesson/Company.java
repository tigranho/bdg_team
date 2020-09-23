package jdbclesson;

import jdbclesson.implementation.CompanyI;

public class Company {

    private String name;
    private String found_date;

    public Company() {
    }

    public Company(String name, String found_date) {
        this.name = name;
        this.found_date = found_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFound_date() {
        return found_date;
    }

    public void setFound_date(String found_date) {
        this.found_date = found_date;
    }
}
