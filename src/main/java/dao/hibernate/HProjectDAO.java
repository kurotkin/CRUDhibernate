package dao.hibernate;

import dao.ProjectDAO;
import model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HProjectDAO implements ProjectDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Project getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    @Override
    public Project getByName(String name) {
        Project project = null;
        Session session = this.sessionFactory.openSession();
        String query = "select c from Project c where c.name like :name";
        List<Project> projectes = session.createQuery(query).setParameter("name", name).list();
        if (projectes.size() != 0) {
            project = projectes.get(0);
        }
        return project;
    }

    @Override
    public List<Project> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Project");
        List<Project> result = query.list();
        session.close();
        return result;
    }

    @Override
    public Long save(Project val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(val);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public void update(Project val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, val.getId());
        project.setName(val.getName());
        project.setCost(val.getCost());
        session.update(project);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Project val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, val.getId());
        session.delete(project);
        transaction.commit();
        session.close();
    }
}
