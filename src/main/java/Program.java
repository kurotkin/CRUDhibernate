import dao.DeveloperDAO;
import dao.hibernate.HDeveloperDAO;
import model.Developer;
import model.Project;
import model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitaly Kurotkin on 10.12.2017.
 */
public class Program {

    public static void main(String[] args) {

        DeveloperDAO devDAO = new HDeveloperDAO();

        //Developer 1
        Developer dev1 = new Developer("Ivan", "Ivanov", "super user", new BigDecimal(4000));
        Set<Project> projects1 = new HashSet<>();
        projects1.add(new Project("project 1", new BigDecimal(568)));
        projects1.add(new Project("project 2", new BigDecimal(267)));
        projects1.add(new Project("project 3", new BigDecimal(567)));
        projects1.add(new Project("New great project", new BigDecimal(999)));
        dev1.withProjects(projects1);
        Set<Skill> skills1 = new HashSet<>();
        skills1.add(new Skill("brain"));
        dev1.withSkills(skills1);

        //Developer 2
        Developer dev2 = new Developer("Alexandr", "Sidorov", "java developer", new BigDecimal(4456));
        dev2.withProjects(projects1);
        Set<Skill> skills2 = new HashSet<>();
        skills1.add(new Skill("brain"));
        skills1.add(new Skill("java"));
        dev1.withSkills(skills1);

        //Developer 3
        Developer dev3 = new Developer("Victor", "Petrov", "java developer", new BigDecimal(5668))
                .withProjects(projects1)
                .withSkills(skills1);

        Long dev1Id = 0L;
        Long dev2Id = 0L;
        Long dev3Id = 0L;

        //Save
        try {
            dev1Id = devDAO.save(dev1);
            System.out.println("Сохранение пользователя с id = " + dev1Id);
            dev2Id = devDAO.save(dev2);
            System.out.println("Сохранение пользователя с id = " + dev2Id);
            dev3Id = devDAO.save(dev3);
            System.out.println("Сохранение пользователя с id = " + dev3Id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load
        try {
            System.out.println("===== Загрузка пользователя с id = " + dev1Id + " =====");
            Developer devFromMysql = devDAO.getById(dev1Id);
            System.out.println(devFromMysql);
            System.out.println("================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load all
        try {
            System.out.println("===== Загрузка всех данных =====");
            List<Developer> developers = devDAO.getAll();
            developers.stream().forEach(dev -> System.out.println(dev));
            System.out.println("================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Upload
        try {
            Developer updateDeveloper = new Developer("Petr", "Petrov", "super user", new BigDecimal(4000));
            updateDeveloper.setId(dev1Id);
            devDAO.update(updateDeveloper);
            System.out.println("===== Обновление пользователя с id = " + dev1Id + " =====");
            System.out.println(devDAO.getById(dev1Id));
            System.out.println("================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Delete
        try {
            System.out.println("===== Удаление пользователя с id = " + dev1Id + " =====");
            devDAO.delete(new Developer().withId(dev1Id));
            System.out.println(devDAO.getById(dev1Id));
            System.out.println("================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
