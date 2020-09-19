package jdbc.airoport_management_system.entity;

import java.time.LocalTime;

public class Trip {
    private long id;
    private Company company;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private String fromTown;
    private String toTown;

    public Trip(Company company, LocalTime timeIn, LocalTime timeOut, String fromTown, String toTown) {
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.fromTown = fromTown;
        this.toTown = toTown;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }
}
