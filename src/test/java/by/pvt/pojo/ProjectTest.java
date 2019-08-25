package by.pvt.pojo;

import by.pvt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectTest {

    static Project createTestData(int index) {
        Project project = new Project();
        project.setProjectName("Project" + index);
        project.setStartDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 365);
        project.setEndDate(calendar.getTime());
        return project;
    }

    @Test
    public void testSave() throws Exception {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Project project1 = createTestData(1);
        Project project2 = createTestData(2);

        Employee employee1 = EmployeeTest.createTestData(11);
        Employee employee2 = EmployeeTest.createTestData(12);
        Employee employee3 = EmployeeTest.createTestData(13);

        project1.addEmployee(employee1);
        project1.addEmployee(employee3);

        project2.addEmployee(employee1);
        project2.addEmployee(employee2);
        project2.addEmployee(employee3);

        session.save(project1);
        session.save(project2);

        session.save(employee1);
        session.save(employee2);
        session.save(employee3);

        tx.commit();
        session.close();


        session = HibernateUtil.getInstance().getSession();
        Project savedProject = session.load(Project.class, 1L);
        assertNotNull(savedProject);
        assertEquals(2, savedProject.getEmployees().size());
        session.close();

    }

}