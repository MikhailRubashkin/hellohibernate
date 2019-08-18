package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeDetailsTest {

    private static EmployeeDetails createTestData(int index) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setAddressLine("addressLine" + index);
        employeeDetails.setCity("City" + index);
        employeeDetails.setStreet("Street" + index);
        //employeeDetails.setEmployee(EmployeeTest.createTestData(index));
        return employeeDetails;
    }

    @Test
    public void testSaveOrUpdate() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().getSession()) {
            tx = session.beginTransaction();
            Employee employee = EmployeeTest.createTestData(1);
            session.save(employee);
            EmployeeDetails employeeDetails = createTestData(1);
            employeeDetails.setEmployee(employee);
            session.saveOrUpdate(employeeDetails);

            Employee employee2 = EmployeeTest.createTestData(1);
            session.save(employee2);
            EmployeeDetails employeeDetails2 = createTestData(1);
            employeeDetails2.setEmployee(employee2);
            session.saveOrUpdate(employeeDetails2);

            session.saveOrUpdate(createTestData(3));
            session.saveOrUpdate(createTestData(5));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            assertEquals(true, false);
        }
    }

}