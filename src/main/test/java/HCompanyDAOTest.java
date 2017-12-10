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
        String name = "Company name for save test";
        Company company = new Company(name);
        companyDAO.save(company);
        assertEquals(companyDAO.getByName(name), company);
        companyDAO.delete(companyDAO.getByName(name));
    }

    @Test
    public void findById() throws Exception {
        Company company = new Company("Company name for id test");
        Long id = companyDAO.save(company);
        assertEquals(companyDAO.getById(id), company);
        company.setId(id);
        companyDAO.delete(company);
        assertEquals(new Company(""), companyDAO.getById(id));
    }

    @Test
    public void update() throws Exception {
        Company company = new Company("Company name for update test");
        Long id = companyDAO.save(company);

        Company newCompany = new Company("New Company name for update test").withId(id);
        companyDAO.update(newCompany);

        assertEquals(newCompany, companyDAO.getByName("New Company name for update test"));

        companyDAO.delete(companyDAO.getByName("New Company name for update test"));
    }

    @Test
    public void delete() throws Exception {
        String name = "Company name for delete test";
        Company company = new Company(name);
        Long id = companyDAO.save(company);
        assertEquals(company, companyDAO.getByName(name));
        companyDAO.delete(companyDAO.getById(id));
        assertEquals(null, companyDAO.getByName(name));
    }

    @Test
    public void findAll() throws Exception {
        long listSizeBefore = companyDAO.getAll().size();
        Company company1 = new Company("Company1 for test findAll");
        Company company2 = new Company("Company2 for test findAll");
        companyDAO.save(company1);
        companyDAO.save(company2);
        List<Company> listAfter = companyDAO.getAll();
        assertTrue((listAfter.size() - listSizeBefore) == 2);
        assertTrue(listAfter.contains(company1) && listAfter.contains(company2));
        companyDAO.delete(companyDAO.getByName("Company1 for test findAll"));
        companyDAO.delete(companyDAO.getByName("Company2 for test findAll"));
    }
}
