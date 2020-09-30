package jdbctaskusingjpaproviderhibernate2.entity;

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


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Company company = (Company) o;
//
//        return comapnyId != null ? comapnyId.equals(company.comapnyId) : company.comapnyId == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (comapnyId != null ? comapnyId.hashCode() : 0);
//        return result;
//    }
}
