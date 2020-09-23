package updatedjdbctask.model;

/**
 * Created by User on 17.09.2020.
 */
public class Company {
    private Long comapnyId;
    private String name;
    private String foundDate;

    public Long getComapnyId() {
        return comapnyId;
    }

    public void setComapnyId(Long comapnyId) {
        this.comapnyId = comapnyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "comapnyId=" + comapnyId +
                ", name='" + name + '\'' +
                ", foundDate='" + foundDate + '\'' +
                '}';
    }
}