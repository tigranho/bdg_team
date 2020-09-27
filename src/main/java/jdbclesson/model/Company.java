package jdbclesson.model;

public class Company {

    private int id;
    private String name;
    private String found_date;

    public Company() {
    }

    public Company(int id, String name, String found_date) {
        this.id = id;
        this.name = name;
        this.found_date = found_date;
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

    public String getFound_date() {
        return found_date;
    }

    public void setFound_date(String found_date) {
        this.found_date = found_date;
    }

    @Override
    public String toString() {
        return "\nid " + getId() +
                ", name " + getName() +
                ", found date " + getFound_date();
    }
}