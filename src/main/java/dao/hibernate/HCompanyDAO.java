package dao.hibernate;

import dao.CompanyDAO;
import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
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
    public Company getByName(String name) {
        Company company = null;
        Session session = this.sessionFactory.openSession();
        String query = "select c from Company c where c.name like :name";
        List<Company> companies = session.createQuery(query).setParameter("name", name).list();
        if (companies.size() != 0) {
            company = companies.get(0);
        }
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
    public Long save(Company val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(val);
        transaction.commit();
        session.close();
        return id;
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
