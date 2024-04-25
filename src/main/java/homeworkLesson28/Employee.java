package homeworkLesson28;

import HomeworkLesson27.CrewSet;
import HomeworkLesson27.FilmStudioEmployee;

import java.util.HashMap;

public class Employee {

    private final int employeeId;

    private String name;

    private String department;

    private double salary;

    public Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    protected void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    protected void displayInfo() {

        System.out.println("\nInformation about employee:");
        line();
        System.out.printf("%-6s %-10s %-17s %s%n", "ID", "Name", "Department", "Salary");
        line();
        System.out.printf("%-6s %-10s %-17s %s%n", this.getEmployeeId(), this.getName(), this.getDepartment(), this.getSalary());
        line();
    }

    private void line(){
        System.out.println("-------------------------------------------");
    }
}
