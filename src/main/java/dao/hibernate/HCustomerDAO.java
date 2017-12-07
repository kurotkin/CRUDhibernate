package dao.hibernate;

import dao.CustomerDAO;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HCustomerDAO implements CustomerDAO{

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Customer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public Customer getByName(String name) {
        Customer customer = null;
        Session session = this.sessionFactory.openSession();
        String query = "select c from Customer c where c.name like :name";
        List<Customer> companies = session.createQuery(query).setParameter("name", name).list();
        if (companies.size() != 0) {
            customer = companies.get(0);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Customer");
        List<Customer> result = query.list();
        session.close();
        return result;
    }

    @Override
    public Long save(Customer val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(val);
        transaction.commit();
        session.close();
        return id;
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
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, val.getId());
        session.delete(customer);
        transaction.commit();
        session.close();
    }
}
