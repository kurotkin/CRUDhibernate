package dao.hibernate;

import dao.SkillDAO;
import model.Developer;
import model.Project;
import model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class HSkillDAO implements SkillDAO {

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Skill getById(Long id) throws SQLException {
        Session session = this.sessionFactory.openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Skill");
        List<Skill> result = query.list();
        session.close();
        return result;
    }

    @Override
    public void save(Skill val) throws SQLException {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(val);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Skill val) throws SQLException {

    }

    @Override
    public void delete(Skill val) throws SQLException {

    }
}
