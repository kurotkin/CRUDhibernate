package model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Vitaly on 19.11.2017.
 */
@Entity
@Table(name = "companies")
public class Company {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Company() {
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(Long id, String name, Set<Project> projects) {
        this.id = id;
        this.name = name;
    }

    public Company withId(Long id) {
        this.id = id;
        return this;
    }

    public Company withName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '}';
    }
}
