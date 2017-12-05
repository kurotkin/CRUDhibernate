package dao.hibernate;

import dao.DeveloperDAO;
import model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HDeveloperDAO implements DeveloperDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Developer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Developer");
        List<Developer> result = query.list();
        session.close();
        return result;
    }

    @Override
    public void save(Developer val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(val);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Developer val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, val.getId());
        developer.setFirstName(val.getFirstName());
        developer.setLastName(val.getLastName());
        session.update(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Developer val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, val.getId());
        session.delete(developer);
        transaction.commit();
        session.close();
    }
}
