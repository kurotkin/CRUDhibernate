package dao.hibernate;

import dao.CustomerDAO;
import model.Company;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HCustomerDAO implements CustomerDAO{

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Customer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Customer");
        List<Customer> result = query.list();
        session.close();
        return result;
    }

    @Override
    public void save(Customer val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(val);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Customer val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, val.getId());
        customer.setFirstName(val.getFirstName());
        customer.setLastName(val.getLastName());
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Customer val) {

    }
}
