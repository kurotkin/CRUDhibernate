package model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Vitaly on 19.11.2017.
 */

@Entity
@Table(name = "projects")
public class Project {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Project() {
    }

    public Project(Long id, String name, BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Project(String name, BigDecimal cost, Company company, Customer customer) {
        this.name = name;
        this.cost = cost;
        this.company = company;
        this.customer = customer;
    }

    public Project withId(Long id) {
        this.id = id;
        return this;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }

    public Project withCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public Project withCompany(Company company) {
        this.company = company;
        return this;
    }

    public Project withCustomer(Customer customer) {
        this.customer = customer;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
