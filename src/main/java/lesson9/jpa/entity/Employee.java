package lesson9.jpa.entity;

import javax.persistence.*;
import java.util.StringJoiner;


@NamedQuery(name = "Employee.getAll",
        query = "select e from lesson9.jpa.entity.Employee e")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    private double salary;
    private String deg;

    public Employee(int id, String name, double salary, String deg) {
        super( );
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deg = deg;
    }

    public Employee( ) {
        super();
    }

    public int getId( ) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName( ) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary( ) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg( ) {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("salary=" + salary)
                .add("deg='" + deg + "'")
                .toString();
    }
}
