package homeworkLesson30.project;


import homeworkLesson30.employee.Employee;
import homeworkLesson30.employee.EmployeesManager;
import homeworkLesson30.interfaces.ProjectInterface;
import homeworkLesson30.task.Task;
import homeworkLesson30.task.TasksManager;

import java.util.HashMap;

public class ProjectsManager implements ProjectInterface {
    private static HashMap <Integer, Project> projectsMap = new HashMap<>();

    public static HashMap<Integer, Project> getProjectsMap() {
        return projectsMap;
    }

    //Метод для создания проекта
    @Override
    public void createProject (String name){

        int newId;

        do {
            newId = (int) (Math.random() * 1000000);
        } while (projectsMap.containsKey(newId)&& EmployeesManager.getEmployeesMap().containsKey(newId)&& TasksManager.getTasksMap().containsKey(newId));

        Project project = new Project(newId, name);
        Project previousProject = projectsMap.put(newId, project);
        if(previousProject == null){
            System.out.println("Project \"" + project.getProjectName() + "\" successfully added with ID" + newId);
        }
        else {
            System.err.println("User was not added");
        }
    }

    //Метод для добавления проекта
    @Override
    public void addProject(Project project) {
        if (project == null) {
            System.err.println("Project is null");
        } else {
            Project previousProject = projectsMap.put(project.getProjectId(), project);
            if (previousProject == null) {
                System.out.println("Project \"" + project.getProjectName() + "\" successfully added");
            } else {
                System.out.println("Employee " + project.getProjectName() + " was updated");
            }
        }
    }

    // Метод для удаления проекта по ID
    @Override
    public void removeProjectById(int id) {
        if (projectsMap.containsKey(id)) {
            projectsMap.remove(id);
            System.out.println("Project with ID " + id + " was successfully removed");
        } else {
            System.out.println("Project with ID " + id + " was not found");
        }
    }

    //Метод для удаления проекта
    @Override
    public void removeProject(Project project) {
        if (project == null) {
            System.out.println("Project is null");
        } else {
            Project deletedProject = projectsMap.remove(project.getProjectId());
            if (deletedProject != null) {
                System.out.println("Project \"" + project.getProjectName() + "\" was removed");
            } else {
                System.out.println("Project \"" + project.getProjectName() + "\" was not found");
            }
        }
    }

    //Поиск проекта по названию
    @Override
    public Project findEmployeeByName(String projectName) {
        for (Project project : projectsMap.values()) {
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                System.out.println("Project " + project.getProjectName() + " was found");
                return project;
            }
        }
        System.out.println("Project \"" + projectName + "\" was not found");
        return null;
    }

    //Метод для добавления сотрудника в проект
    @Override
    public void addEmployeeToProject(Project project, Employee employee) {
        if (project == null|| employee == null) {
            System.err.println("Object is null");
        } else {
            project.addEmployeeToProject(employee);
        }
    }

    //Метод для добавления сотрудника в проект по id
    @Override
    public void addEmployeeToProjectById(int projectId, int employeeId) {
        if (!projectsMap.containsKey(projectId)) {
            System.err.println("Project with ID " + projectId + " was not found");
        }
        else if (!EmployeesManager.getEmployeesMap().containsKey(employeeId)){
            System.out.println("Employee with ID " + employeeId + " was not found");
        }
        else {
            Project project = projectsMap.get(projectId);
            Employee employee = EmployeesManager.getEmployeesMap().get(employeeId);
            project.addEmployeeToProject(employee);
            projectsMap.put(project.getProjectId(), project);
            System.out.println("Employee " + employee.getName() + " with ID " + employee.getId() + " was added to the project" + project.getProjectName() );
        }
    }

    //Метод для добавления задачи в проект
    @Override
    public void addTaskToProject(Project project, Task task) {
        if (project == null|| task == null) {
            System.err.println("Object is null");
        } else {
            project.addTaskToProject(task);
        }
    }

    //Метод для добавления задачи в проект
    @Override
    public void createTaskInProject (int implementerId, String description, String deadline, Project project){
        project.createTaskToProject(implementerId, description, deadline);
    }

    //Вывод всех задач в проекте
    @Override
    public void printAllTasksInProject (Project project){
        project.printAllTasksOfProject();
    }

    //Вывод всех сотрудников в проекте
    @Override
    public void printAllEmployeesInProject (Project project){
        project.printAllEmployeesInProject();
    }

    //Вывод информации о проекте
    @Override
    public void displayProjectInfo (Project project){
        project.displayProjectInfo();
    }

    //Вывод всех проектов
    @Override
    public void printAllProjects() {
        line();
        System.out.printf("%-3 %-8s %s%n", "№", "ID", "Project Name");
        line();
        int counterNum = 0;
        for (Project project : projectsMap.values()) {
            counterNum++;
            System.out.printf("%-3 %-8s %s%n", "№", counterNum, project.getProjectId(), project.getProjectName());
        }
        line();
    }

    public void line() {System.out.println("-----------------------------------------------------------------------------------");}
}
