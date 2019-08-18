package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EmployeeTest {
    static Employee createTestData(int index) {
        Employee employee = new Employee();
        employee.setDateOfBirth (new Date ());
        employee.setEmpNumber(index);
        employee.setName("Name" + index);
        employee.setTitle("Employee");
        return employee;
    }

    @Test
    public void testSaveOrUpdate() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().getSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(createTestData(1));
            session.saveOrUpdate(createTestData(3));
            session.saveOrUpdate(createTestData(5));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            assertEquals(true, false);
        }
    }

}