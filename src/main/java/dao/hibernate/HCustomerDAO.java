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
        Customer customer = null;
        try(Session session = this.sessionFactory.openSession()){
            customer = session.get(Customer.class, id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer getByName(String name) {
        Customer customer = null;
        try (Session session = this.sessionFactory.openSession()) {
            String query = "select c from Customer c where c.lastName like :name";
            List<Customer> customers = session.createQuery(query).setParameter("name", name).list();
            if (customers.size() != 0) {
                customer = customers.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> result = null;
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from Customer");
            result = query.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Long save(Customer val) {
        Long id = null;
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            id = (Long) session.save(val);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Customer val) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, val.getId());
            customer.setFirstName(val.getFirstName());
            customer.setLastName(val.getLastName());
            session.update(customer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer val) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, val.getId());
            session.delete(customer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
