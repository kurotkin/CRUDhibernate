package dao.hibernate;

import dao.SkillDAO;
import model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HSkillDAO implements SkillDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Skill getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Skill");
        List<Skill> result = query.list();
        session.close();
        return result;
    }

    @Override
    public void save(Skill val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(val);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Skill val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, val.getId());
        skill.setName(val.getName());
        session.update(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Skill val) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, val.getId());
        session.delete(skill);
        transaction.commit();
        session.close();
    }
}
