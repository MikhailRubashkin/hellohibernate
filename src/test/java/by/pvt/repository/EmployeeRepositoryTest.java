package by.pvt.repository;

import by.pvt.pojo.Employee;
import by.pvt.pojo.EmployeeDetails;
import by.pvt.util.HibernateUtil;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.Session;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class EmployeeRepositoryTest extends DBTestCase {

    private Session testSession;

    public EmployeeRepositoryTest(String name) {
        super(name);
        testSession = HibernateUtil.getInstance().getTestSession();
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/hello_hibernate_junit?useSSL=false");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"pristijpriborF1539249f");
    }

    @Override
    public IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File ("src/test/resources/repository/Employees.xml"));
    }

    @Test
    public void testFindAll() {
        EmployeeRepository employeeRepository = new EmployeeRepository(testSession);
        List<Employee> result = employeeRepository.findAll();
        assertNotNull(result);
        assertTrue(result.size() == 3);
    }

    @Test
    public void testFindEmployeesNames() {
        EmployeeRepository repository = new EmployeeRepository(testSession);
        List<String> names = repository.findEmployeesNames();
        final List<String> savedNames = List.of("Ivanov", "Petrov", "Sidorov");
        assertEquals(savedNames, names);
    }

    @Test
    public void testFindEmployeeDetails() {
        EmployeeRepository repository = new EmployeeRepository(testSession);
        List<EmployeeDetails> details = repository.findEmployeeDetails();
        assertEquals(2, details.size());
    }

    @Test
    public void testGetEmployeeCount() {
        EmployeeRepository employeeRepository = new EmployeeRepository(testSession);
        assertEquals(3L, employeeRepository.getEmployeeCount().longValue());
    }
}