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
        Skill skill = null;
        try (Session session = this.sessionFactory.openSession()) {
            skill = session.get(Skill.class, id);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill getByName(String name) {
        Skill skill = null;
        try (Session session = this.sessionFactory.openSession()) {
            String query = "select c from Skill c where c.name like :name";
            List<Skill> skills = session.createQuery(query).setParameter("name", name).list();
            if (skills.size() != 0) {
                skill = skills.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skill;
    }


    @Override
    public List<Skill> getAll() {
        List<Skill> result = null;
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("from Skill");
            result = query.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Long save(Skill val) {
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
    public void update(Skill val) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Skill skill = session.get(Skill.class, val.getId());
            skill.setName(val.getName());
            session.update(skill);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Skill val) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Skill skill = session.get(Skill.class, val.getId());
            session.delete(skill);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
