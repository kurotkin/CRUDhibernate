import dao.CompanyDAO;
import dao.DeveloperDAO;
import dao.ProjectDAO;
import dao.hibernate.HCompanyDAO;
import dao.hibernate.HDeveloperDAO;
import dao.hibernate.HProjectDAO;
import dao.utils.ConnectionUtil;
import model.Company;
import model.Developer;
import model.Project;
import model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitaly Kurotkin on 09.11.2017.
 */
public class Program {

    public static void main(String[] args) {
//        if (ConnectionUtil.USER.equals("") ||  ConnectionUtil.PASS.equals("")){
//            throw new Exception("Введите имя пользователя и пароль в классе " + ConnectionUtil.class.getName());
//        }

        DeveloperDAO devDAO = new HDeveloperDAO();
        CompanyDAO companyDAO = new HCompanyDAO();
        ProjectDAO projectDAO = new HProjectDAO();

        //Developer
        Developer dev1 = new Developer("Ivan", "Ivanov", "super user", new BigDecimal(4000));


        Set<Project> projects1 = new HashSet<>();
        projects1.add(new Project("New great project", new BigDecimal(999)));
        dev1.withProjects(projects1);

        Set<Skill> skills1 = new HashSet<>();
        skills1.add(new Skill("brain"));
        dev1.withSkills(skills1);

        //Save
        try {
            devDAO.save(dev1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load
        try {
            Developer devFromMysql = devDAO.getById(2L);
            System.out.println(devFromMysql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load all
        try {
            List<Developer> developers = devDAO.getAll();
            developers.stream().forEach(dev -> System.out.println(dev));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Upload
        try {
            Developer updateDeveloper = new Developer("Petr", "Petrov", "super user", new BigDecimal(4000));
            updateDeveloper.setId(1L);
            devDAO.update(updateDeveloper);
            System.out.println(devDAO.getById(1L));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Delete
        try {
            devDAO.delete(new Developer().withId(1L));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
