package homework30new;

import java.util.HashMap;

public class Project {
    private int projectID;

    private String name;

    private HashMap <Integer, Task> projectTasks = new HashMap<>();

    private HashMap<Integer, Employee> projectEmployees = new HashMap<>();

    public Project(int projectID, String name) {
        this.projectID = projectID;
        this.name = name;
    }

    public Project(int projectID, String name, HashMap<Integer, Task> projectTasks) {
        this.projectID = projectID;
        this.name = name;
        this.projectTasks = projectTasks;
    }

    public void addTask (Task task){
        projectTasks.put(task.getTaskId(), task);
    }

    public void addEmployee (Employee employee){
        projectEmployees.put(employee.getEmployeeId(), employee);
    }

    public void removeTask  (int taskId){
        projectTasks.remove(taskId);
    }

    public void removeEmployee (int employeeID){
        projectEmployees.remove(employeeID);
    }

    public int getProjectID() {
        return projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Task> getProjectTasks() {
        return new HashMap<>(projectTasks);
    }

    public HashMap<Integer, Employee> getProjectEmployees() {
        return new HashMap<>(projectEmployees);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", name=" + name +
                ", projectTasks=" + projectTasks +
                ", projectEmployees=" + projectEmployees +
                '}';
    }
}
