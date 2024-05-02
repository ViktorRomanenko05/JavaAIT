package homeworkLesson30.employee;


import homeworkLesson30.interfaces.EmployeeInterface;
import homeworkLesson30.project.ProjectsManager;
import homeworkLesson30.task.TasksManager;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeesManager implements EmployeeInterface {
    private static HashMap <Integer, Employee> employeesMap = new HashMap<>();

    public static HashMap<Integer, Employee> getEmployeesMap() {
        return employeesMap;
    }

    public static void setEmployeesMap(HashMap<Integer, Employee> employeesMap) {
        EmployeesManager.employeesMap = employeesMap;
    }


    //Метод для создания профиля сотрудника
    @Override
    public void createEmployee (String name, String position){

        int newId;

        do {
            newId = (int) (Math.random() * 1000000);
        } while (employeesMap.containsKey(newId) && ProjectsManager.getProjectsMap().containsKey(newId) && TasksManager.getTasksMap().containsKey(newId));
        Employee newEmployee = new Employee(newId, name, position);
        Employee previousEmployee = employeesMap.put(newId, newEmployee);
        if(previousEmployee == null){
            System.out.println("Employee " + name + " was added with id: " + newId);
        }
        else {
            System.err.println("User was not added");
        }
    }

    //Метод для добавления сотрудника
    @Override
    public void addEmployee(Employee employee) {
        if (employee == null) {
            System.err.println("Object employee is null");
        } else {
            Employee previousEmployee = employeesMap.put(employee.getId(), employee);
            if (previousEmployee == null) {
                System.out.println("Employee " + employee.getName() + " successfully added");
            } else {
                System.out.println("Employee " + employee.getName() + " was updated");
            }
        }
    }

    // Метод для удаления сотрудника по ID
    @Override
    public void removeEmployeeById(int id) {
        if (employeesMap.containsKey(id)) {
            employeesMap.remove(id);
            System.out.println("Employee with ID " + id + " was successfully removed");
        } else {
            System.out.println("Employee with ID " + id + " was not found");
        }
    }

    //Метод для удаления сотрудника
    @Override
    public void removeEmployee( Employee employee) {
        if (employee == null) {
            System.out.println("Object employee is null");
        } else {
            Employee deletedEmployee = employeesMap.remove(employee.getId());
            if (deletedEmployee != null) {
                System.out.println("Employee " + employee.getName() + " was removed");
            } else {
                System.out.println("Employee " + employee.getName() + " not found");
            }
        }
    }

    //Поиск сотрудника по имени
    @Override
    public ArrayList<Employee> findEmployeeByName(String name) {
        ArrayList<Employee> found = new ArrayList<>();
        for (Employee employee : employeesMap.values()) {
            if (employee.getName().equalsIgnoreCase(name)) {
                found.add(employee);
                System.out.println("Employees " + employee.getName() + " was found\n" +
                        "quantity: " + found.size());
            }
        }
        if(found.isEmpty()){
        System.out.println("Employee " + name + " was not found");}
        return found;
    }
    @Override
    public void printAllEmployees() {
        line();
        System.out.printf("%-3s %-8s %-20s %s%n", "№", "ID", "Name", "Position");
        line();
        int counterNum = 0;
        for (Employee employee : employeesMap.values()) {
            counterNum++;
            System.out.printf("%-3s %-8s %-20s %s%n", counterNum, employee.getId(), employee.getName(), employee.getPosition());
        }
        line();
    }

    //Вывод на экран всех задач сотрудника
    @Override
    public void printEmployeesTasks (Employee employee){
        employee.printAllTasksOfEmployee();
    }

    public void line() {System.out.println("-----------------------------------------------------------------------------------");}




}
