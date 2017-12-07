import dao.hibernate.HCompanyDAO;
import model.Company;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HCompanyDAOTest {
    private HCompanyDAO companyDAO = new HCompanyDAO();

    @Test
    public void save() throws Exception {
        Company company = new Company("Company name");
        companyDAO.save(company);
        assertEquals(companyDAO.getByName("Company name"), company);
        companyDAO.delete(companyDAO.getByName("Company name"));
    }

    @Test
    public void findById() throws Exception {
        Company company = new Company("companyForTestFindById");
        Long id = companyDAO.save(company);
        assertEquals(companyDAO.findById(id), company);
        company.setId(id);
        companyDAO.delete(company);
        assertEquals(new Company(id, ""), companyDAO.findById(id));
    }

    @Test
    public void update() throws Exception {
        Company company = new Company(-1, "TestUpdate");
        Long id = companyDAO.save(company);
        Company newCompany = new Company(id, "NewTestUpdate");
        companyDAO.update(newCompany);
        assertEquals(new Company(0, "TestUpdate"), companyDAO.findByName("TestUpdate"));
        assertEquals(newCompany, companyDAO.findByName("NewTestUpdate"));
        companyDAO.delete(companyDAO.findByName("NewTestUpdate"));
        Company anotherCompany = new Company(id + 100, "anotherCompany");
        companyDAO.update(anotherCompany);
        assertEquals(new Company(0, "anotherCompany"), companyDAO.findByName("anotherCompany"));
    }

    @Test
    public void delete() throws Exception {
        Company company = new Company(-1, "for test delete");
        companyDAO.delete(company);
        companyDAO.save(company);
        assertEquals(company, companyDAO.findByName("for test delete"));
        companyDAO.delete(companyDAO.findByName("for test delete"));
        assertEquals(new Company(0, "for test delete"), companyDAO.findByName("for test delete"));
    }

    @Test
    public void findAll() throws Exception {
        long listSizeBefore = companyDAO.findAll().size();
        Company company1 = new Company(-1,"company1 for test findAll");
        Company company2 = new Company(-1,"company2 for test findAll");
        companyDAO.save(company1);
        companyDAO.save(company2);
        List<Company> listAfter = companyDAO.findAll();
        assertTrue((listAfter.size() - listSizeBefore) == 2);
        assertTrue(listAfter.contains(company1) && listAfter.contains(company2));
        companyDAO.delete(companyDAO.findByName("company1 for test findAll"));
        companyDAO.delete(companyDAO.findByName("company2 for test findAll"));
    }
}
