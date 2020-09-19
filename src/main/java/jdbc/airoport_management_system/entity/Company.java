package jdbc.airoport_management_system.entity;

import java.time.LocalDate;
import java.util.Set;

public class Company {
    private long id;
    private String name;
    private LocalDate foundingDate;
    private Set<Trip> tripSet;

    public Company(String name, LocalDate foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
    }

    public long getId() {
        return id;
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

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }
}
