package homeworkLesson30.project;

import homeworkLesson30.employee.EmployeesManager;
import homeworkLesson30.task.Task;
import homeworkLesson30.employee.Employee;
import homeworkLesson30.task.TasksManager;

import java.util.HashMap;
import java.util.Objects;

public class Project {
    private final int projectId;
    private String projectName;

    private HashMap<Integer, Employee> employeesInProject = new HashMap<>();

    private HashMap<Integer, Task> tasksInProject = new HashMap<>();

    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public HashMap <Integer, Employee> getEmployeesInProject() {
        return employeesInProject;
    }

    public HashMap<Integer, Task> getTasksInProject() {
        return tasksInProject;
    }

    public void setTasksInProject(HashMap<Integer, Task> tasksInProject) {
        this.tasksInProject = tasksInProject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId && Objects.equals(projectName, project.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName);
    }

    public void addEmployeeToProject(Employee employee) {
        if (employee == null) {
            System.err.println("Employee is null");
        } else {
            Employee pereviousEmployee = this.employeesInProject.put(employee.getId(), employee);
            if (pereviousEmployee == null) {
                System.out.println("Employee " + employee.getName() + " successfully added to project");
            } else {
                System.out.println("Employee " + employee.getName() + " is already part of the project");
            }
        }
    }

    public void addEmployeeToProjectById(int employeeId) {
        if (!EmployeesManager.getEmployeesMap().containsKey(employeeId)) {
            System.err.println("No employee found with ID: " + employeeId);
        } else {
            Employee employee = EmployeesManager.getEmployeesMap().get(employeeId);
            Employee previousEmployee = this.employeesInProject.put(employeeId, employee);
            if (previousEmployee == null) {
                System.out.println("Employee " + employee.getName() + " successfully added to project");
            } else {
                System.out.println("Employee " + employee.getName() + " is already part of the project");
            }
        }
    }

    public void addTaskToProject(Task task) {
        if (task == null) {
            System.err.println("Object task is null");
        } else {
            Task previousTask = this.tasksInProject.put(task.getTaskId(), task);
            if (previousTask == null) {
                System.out.println("Task " + task.getTaskId() + " successfully added to project");
            } else {
                System.out.println("Task " + task.getTaskId() + " is already in the project");
            }
        }
    }

    public void addTaskToProjectById(int taskId) {
        if (!TasksManager.getTasksMap().containsKey(taskId)) {
            System.err.println("No task found with ID: " + taskId);
        }
        else {
            Task task = TasksManager.getTasksMap().get(taskId);
            Task previousTask = this.tasksInProject.put(taskId, task);
            if (previousTask == null) {
                System.out.println("Task " + taskId + " successfully added to project");
            } else {
                System.out.println("Task " + taskId + " is already part of the project");
            }
        }
    }

    //Метод для создания задачи из проекта
    public void createTaskToProject (int implementerId, String description, String deadline) {

        if (EmployeesManager.getEmployeesMap().get(implementerId) == null) {
            System.out.println("Implementer was not found");
        } else {
            int newId;
            do {
                newId = (int) (Math.random() * 1000000);
            } while (EmployeesManager.getEmployeesMap().containsKey(newId) && ProjectsManager.getProjectsMap().containsKey(newId) && TasksManager.getTasksMap().containsKey(newId));
            Task newTask = new Task(newId, implementerId, description, deadline);
            TasksManager.getTasksMap().put(newTask.getTaskId(), newTask);
            this.tasksInProject.put(newTask.getTaskId(), newTask);
            Employee implementer = EmployeesManager.getEmployeesMap().get(implementerId);
            implementer.addTaskToEmployee(newTask);
            System.out.println("Task created and added to project and employee list");
        }
    }

    public void printAllTasksOfProject() {
        line();
        System.out.printf("%-3s %-8s %-20s %-15s %-15s %s%n", "№", "ID", "Implementer", "Deadline", "Status", "Description");
        line();
        int counterNum = 0;
        for (Task task : this.tasksInProject.values()) {
            counterNum++;
            System.out.printf("%-3s %-8s %-20s %-15s %-15s %s%n", counterNum, task.getTaskId(), task.getImplementerId(), task.getDeadline(), task.getTaskStatus(), task.getDescription());
        }
        line();
    }

    public void printAllEmployeesInProject() {
        line();
        System.out.printf("%-3s %-8s %-20s %s%n", "№", "ID", "Name", "Position");
        line();
        int counterNum = 0;
        for (Employee employee : this.employeesInProject.values()) {
            counterNum++;
            System.out.printf("%-3s %-8s %-20s %s%n", counterNum, employee.getId(), employee.getName(), employee.getPosition());
        }
        line();
    }

    public void displayProjectInfo() {
        System.out.println("Project ID: " + this.projectId + ", Project Name: " + this.projectName);
        line();
        System.out.println("Employees and their tasks:");
        line();

        for (Employee employee : this.getEmployeesInProject().values()) {
            System.out.println("Employee ID: " + employee.getId() + ", Name: " + employee.getName() + ", Position: " + employee.getPosition());
            if (!employee.getTasks().isEmpty()) {
                System.out.println("Tasks assigned:");
                for (Task task : employee.getTasks().values()) {
                    System.out.printf("    Task ID: %d, Description: %s, Task status: %s Deadline: %s\n", task.getTaskId(), task.getDescription(), task.getTaskStatus(), task.getDeadline());
                }
            } else {
                System.out.println("    No tasks assigned.");
            }
            line();
        }
    }


    public void line() {System.out.println("-----------------------------------------------------------------------------------");}


}
