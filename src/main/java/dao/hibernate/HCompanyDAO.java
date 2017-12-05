package dao.hibernate;

import dao.CompanyDAO;
import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HCompanyDAO implements CompanyDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Company getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Company company = session.get(Company.class, id);
        session.close();
        return company;
    }

    @Override
    public List<Company> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Company");
        List<Company> result = query.list();
        session.close();
        return result;
    }

    @Override
    public void save(Company val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(val);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Company val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = session.get(Company.class, val.getId());
        company.setName(val.getName());
        session.update(company);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Company val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = session.get(Company.class, val.getId());
        session.delete(company);
        transaction.commit();
        session.close();
    }
}
