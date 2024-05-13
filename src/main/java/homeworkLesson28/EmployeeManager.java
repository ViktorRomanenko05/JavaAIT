package homeworkLesson28;

import homeworkLesson24.User;

import java.util.HashMap;

public class EmployeeManager {

    private HashMap<Integer, Employee> employees = new HashMap<>();

    //Метод для добавления нового пользователя
    protected boolean addEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("Object employee is null");
            return false;
        } else if (employees.containsKey(employee.getEmployeeId())) {
            System.out.println("User with ID " + employee.getEmployeeId() + " already exists in the system");
            return false;
        } else {
            employees.put(employee.getEmployeeId(), employee);
            System.out.println("Employee " + employee.getName() + " successfully added");
            return true;
        }
    }

    // Метод для обновления данных сотрудника
    protected boolean updateEmployee(int employeeId, String name, String department, double salary) {
        if (employees.containsKey(employeeId)) {
            Employee employeeToUpdate = employees.get(employeeId);

            employeeToUpdate.setName(name);
            employeeToUpdate.setDepartment(department);
            employeeToUpdate.setSalary(salary);

            employees.put(employeeId, employeeToUpdate);
            System.out.println("Employee with ID " + employeeId + " successfully updated");
            return true;
        } else {
            System.out.println("Employee with ID " + employeeId + " not found");
            return false;
        }
    }

    //Удаление сотрудника по ID
    protected boolean removeEmployee(int id) {
        Employee removedEmployee = employees.remove(id);
        if (removedEmployee != null) {
            System.out.println("Employee with id " + id + " was removed");
            return true;
        } else {
            System.out.println("Employee with id " + id + " was not found");
            return false;
        }
    }

    protected Employee findEmployeeById(int id) {
        if (employees.containsKey(id)) {
            System.out.println("Employee with id " + id + " was found");
            return employees.get(id);
        } else {
            System.out.println("Employee with id " + id + " was not found");
            return null;
        }
    }

    protected void displayAllEmployees() {
        System.out.println("\nAll employees");
        line1();
        System.out.printf("%-6s %-10s %-17s %s%n", "ID", "Name", "Department", "Salary");
        line1();
        int counter = 0;
        for (Employee employee : employees.values()) {
            counter++;
            System.out.printf("%-3s %-6s %-10s %-17s %s%n", counter, employee.getEmployeeId(), employee.getName(), employee.getDepartment(), employee.getSalary());
        }
        line1();
    }

    private void line1() {
        System.out.println("-------------------------------------------");
    }
}



