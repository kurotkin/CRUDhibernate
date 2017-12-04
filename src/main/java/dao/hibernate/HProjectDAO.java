package dao.hibernate;

import dao.ProjectDAO;
import model.Developer;
import model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HProjectDAO implements ProjectDAO {

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Project getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Project");
        List<Project> result = query.list();
        session.close();
        return result;
    }

    @Override
    public void save(Project val) throws SQLException {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(val);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Project val) throws SQLException {

    }

    @Override
    public void delete(Project val) throws SQLException {

    }
}
