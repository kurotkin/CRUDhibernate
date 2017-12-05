package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitaly on 19.11.2017.
 */

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "firstName", nullable = false)
    private String firstName;

    @Column (name = "lastName", nullable = false)
    private String lastName;


    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, Set<Project> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer withId(Long id) {
        this.id = id;
        return this;
    }

    public Customer withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '}';
    }
}
