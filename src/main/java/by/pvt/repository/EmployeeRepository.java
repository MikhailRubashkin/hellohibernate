package by.pvt.repository;

import by.pvt.pojo.Employee;
import by.pvt.pojo.EmployeeDetails;
import org.hibernate.Session;

import java.util.List;

public class EmployeeRepository {

    Session session;

    public EmployeeRepository(Session session) {
        this.session = session;
    }

    public List<Employee> findAll() {
        List<Employee> employees
                = session.createQuery("from Employee", Employee.class).list();
        return employees;
    }

    public List<String> findEmployeesNames() {
        List<String> employeesNames
                = session.createQuery("select E.name from Employee E", String.class)
                .list();
        return employeesNames;
    }

    public List<EmployeeDetails> findEmployeeDetails() {
        List<EmployeeDetails> employeeDetails =
                session.createQuery("select E.employeeDetails from Employee E"
                        , EmployeeDetails.class)
                        .list();
        return employeeDetails;
    }

    public Long getEmployeeCount() {
        Long count = session.createQuery("select count(id) from Employee", Long.class)
                .getSingleResult();
        return count;
    }

}