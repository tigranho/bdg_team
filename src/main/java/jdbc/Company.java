package jdbc;

import java.sql.Date;

public class Company {
    private String name;
    private Date date;

    public Company(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
