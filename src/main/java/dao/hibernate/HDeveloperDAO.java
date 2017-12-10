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
        Developer developer = null;
        try (Session session = this.sessionFactory.openSession()) {
            developer = session.get(Developer.class, id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer getByName(String name) {
        Developer developer = null;
        try (Session session = this.sessionFactory.openSession()) {
            String query = "select c from Developer c where c.lastName like :name";
            List<Developer> developers = session.createQuery(query).setParameter("name", name).list();
            if (developers.size() != 0) {
                developer = developers.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> result = null;
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from Developer");
            result = query.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Long save(Developer val) {
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
    public void update(Developer val) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, val.getId());
            developer.setFirstName(val.getFirstName());
            developer.setLastName(val.getLastName());
            session.update(developer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer val) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, val.getId());
            session.delete(developer);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
