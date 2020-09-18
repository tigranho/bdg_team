package jdbctask.model;

import java.time.LocalDateTime;

/**
 * Created by User on 17.09.2020.
 */
public class Trip {
    private Long tripId;
    private Long tripNumber;
    private Long companyId;
    private String timeIn;
    private String timeOut;
    private String townTo;
    private String townFrom;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(Long tripNumber) {
        this.tripNumber = tripNumber;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }
}
