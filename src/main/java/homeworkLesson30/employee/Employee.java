package homeworkLesson30.employee;


import homeworkLesson30.task.Task;

import java.util.HashMap;
import java.util.Objects;


public class Employee {

    private final int id;
    private String name;
    private String position;
    private HashMap <Integer, Task> tasks = new HashMap<>();

    public Employee(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
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

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    //Добавление задачи в список пользователя
    public void addTaskToEmployee(Task task) {
        if (task == null) {
            System.err.println("Object task is null");
        } else {
            Task previousTask = this.tasks.put(task.getTaskId(), task);
            if (previousTask == null) {
                System.out.println("Task " + task.getTaskId() + " successfully added to employee list");
            } else {
                System.out.println("Employee already has task " + task.getTaskId());
            }
        }
    }

    //Удаление задачи из списка пользователя
    public void delTaskFromEmployeeList(Task task) {
        if (task == null) {
            System.err.println("Object task is null");
        } else {
            boolean remove = this.tasks.remove(task.getTaskId(), task);
            if (remove) {
                System.out.println("Task " + task.getTaskId() + " successfully removed from employee list");
            } else {
                System.out.println("Task was not found");
            }
        }
    }

    public void printAllTasksOfEmployee() {
        line();
        System.out.printf("%-3 %-8s %-20s %-15s %s%n", "№", "ID", "Implementer", "Deadline", "Description");
        line();
        int counterNum = 0;
        for (Task task : this.tasks.values()) {
            counterNum++;
            System.out.printf("%-3 %-8s %-20s %-15s %s%n", counterNum, task.getTaskId(), task.getImplementerId(), task.getDeadline(), task.getDescription());
        }
        line();
    }

    public void line() {System.out.println("-----------------------------------------------------------------------------------");}
}
