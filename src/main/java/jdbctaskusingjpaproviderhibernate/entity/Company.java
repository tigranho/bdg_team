package jdbctaskusingjpaproviderhibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
@Entity
@Data
@Table(name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long comapnyId;

    @Column(name = "name")
    private String name;

    @Column(name = "found_date")
    private String foundDate;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Trip> trips;

    public Company(String name, String foundDate) {
        this.name = name;
        this.foundDate = foundDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Company company = (Company) o;

        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (foundDate != null ? !foundDate.equals(company.foundDate) : company.foundDate != null) return false;
        return trips != null ? trips.equals(company.trips) : company.trips == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (foundDate != null ? foundDate.hashCode() : 0);
        result = 31 * result + (trips != null ? trips.hashCode() : 0);
        return result;
    }
}
