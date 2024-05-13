package homeworkLesson30.interfaces;

import homeworkLesson30.employee.Employee;
import homeworkLesson30.project.Project;
import homeworkLesson30.task.Task;

public interface ProjectInterface {

    public void createProject (String name);

    public void addProject(Project project);

    public void removeProjectById(int id);

    public void removeProject(Project project);

    public Project findEmployeeByName(String projectName);

    public void addEmployeeToProject(Project project, Employee employee);

    public void addEmployeeToProjectById(int projectId, int employeeId);

    public void addTaskToProject(Project project, Task task);

    public void createTaskInProject (int implementerId, String description, String deadline, Project project);

    public void printAllTasksInProject (Project project);

    public void printAllEmployeesInProject (Project project);

    public void displayProjectInfo (Project project);

    public void printAllProjects();
}
