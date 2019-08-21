package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    static Department createTestData(int index) {
        Department department = new Department();
        department.setName("Department" + index);
        department.setLocation("Minsk");
        return department;
    }

    @Test
    public void testSave() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getInstance().getSession()) {
            tx = session.beginTransaction();
            Department dep1 = createTestData(1);

            Employee empl1 = EmployeeTest.createTestData(1);
            empl1.setDepartment(dep1);

            Employee empl2 = EmployeeTest.createTestData(2);
            empl2.setDepartment(dep1);

            session.persist(empl1);
            session.persist(empl2);
            session.save(dep1);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            fail();
        }
    }

    @Test
    public void testDelete() {
        Transaction tx = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            tx = session.beginTransaction();
            Department dep1 = createTestData(2);

            Employee empl1 = EmployeeTest.createTestData(6);
            empl1.setDepartment(dep1);

            Employee empl2 = EmployeeTest.createTestData(7);
            empl2.setDepartment(dep1);

            session.save(empl1);
            session.save(empl2);
            session.save(dep1);
            long id = dep1.getId();
            tx.commit();

            session.close();
            session = HibernateUtil.getInstance().getSession();

            tx = session.beginTransaction();
            Department depSaved = session.load(Department.class, id);
            assertEquals(2, depSaved.getEmployees().size());

            for (Employee employee : depSaved.getEmployees()) {
                session.delete(employee);
            }
            session.delete(depSaved);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            fail();
        }
    }

}