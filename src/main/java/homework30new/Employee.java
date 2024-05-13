package homework30new;

import java.util.HashMap;
import java.util.Objects;

public class Employee {
    private int employeeId;

    private String name;

    private String position;

    private HashMap <Integer, Task> EmployeeTask = new HashMap<>();

    public Employee(int employeeId, String name, String position) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
    }

    public void addTask (Task task){
        getEmployeeTask().put(task.getTaskId(), task);
    }

    public void deleteTask(int taskId){
        getEmployeeTask().remove(taskId);
    }

    public void displayEmployeeInfo(){
        System.out.println(toString());
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public HashMap<Integer, Task> getEmployeeTask() {
        return EmployeeTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", EmployeeTask=" + EmployeeTask +
                '}';
    }
}
