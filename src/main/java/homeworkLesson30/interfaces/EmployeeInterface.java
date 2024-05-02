package homeworkLesson30.interfaces;

import homeworkLesson30.employee.Employee;

import java.util.ArrayList;

public interface EmployeeInterface {

    public void createEmployee (String name, String position);

    public void addEmployee(Employee employee);

    public void removeEmployeeById(int id);

    public void removeEmployee( Employee employee);

    public ArrayList<Employee> findEmployeeByName(String name);

    public void printAllEmployees();

    public void printEmployeesTasks (Employee employee);
}
