package homeworkLesson30.task;


import homeworkLesson30.employee.Employee;
import homeworkLesson30.employee.EmployeesManager;
import homeworkLesson30.interfaces.TaskInterface;
import homeworkLesson30.project.Project;
import homeworkLesson30.project.ProjectsManager;

import java.util.ArrayList;
import java.util.HashMap;

public class TasksManager implements TaskInterface {
    private static HashMap<Integer, Task> tasksMap = new HashMap<>();

    public static HashMap<Integer, Task> getTasksMap() {
        return tasksMap;
    }

    //Добавление задачи в список задач и сотруднику
    @Override
    public void addTask(Task task) {
        if (task == null) {
            System.err.println("Object task is null");
        }
        else if (!EmployeesManager.getEmployeesMap().containsKey(task.getImplementerId())){
            System.out.println("Employee with ID " + task.getImplementerId() + " was not found");
        }
        else {
            Task previousTask = tasksMap.put(task.getTaskId(), task);
            Employee employee = EmployeesManager.getEmployeesMap().get(task.getImplementerId());
            employee.addTaskToEmployee(task);

            if (previousTask == null) {
                System.out.println("Task " + task.getTaskId() + " successfully added");
            } else {
                System.out.println("Task " + task.getTaskId() + " was updated");
            }
        }
    }

    //Добавление задачи в список задач, сотруднику и в проект
    @Override
    public void addTask(Task task, int projectID) {
        if (task == null) {
            System.err.println("Object task is null");
        }
        else if (!EmployeesManager.getEmployeesMap().containsKey(task.getImplementerId())){
            System.out.println("Employee with ID " + task.getImplementerId() + " was not found");
        }
        else if (!ProjectsManager.getProjectsMap().containsKey(projectID)){
            System.out.println("Project with ID: " + projectID + " was not found");
        }
        else {
            Task previousTask = tasksMap.put(task.getTaskId(), task);
            Employee employee = EmployeesManager.getEmployeesMap().get(task.getImplementerId());
            employee.addTaskToEmployee(task);
            EmployeesManager.getEmployeesMap().put(task.getImplementerId(), employee);
            Project project = ProjectsManager.getProjectsMap().get(projectID);
            project.addTaskToProject(task);
            ProjectsManager.getProjectsMap().put(projectID, project);

            if (previousTask == null) {
                System.out.println("Task " + task.getTaskId() + " successfully added");
            } else {
                System.out.println("Task " + task.getTaskId() + " was updated");
            }
        }
    }

    //Метод для изменения задания в задаче
    @Override
    public void updateTask(int taskId, String description) {
        if (tasksMap.containsKey(taskId)) {
            Task taskToUpdate = tasksMap.get(taskId);
            taskToUpdate.setDescription(description);
            tasksMap.put(taskId, taskToUpdate);
            System.out.println("Task with ID " + taskId + " successfully updated");
        } else {
            System.out.println("Task with ID " + taskId + " was not found");
        }
    }

    //Метод для изменения статуса задачи
    @Override
    public void updateTaskStatus(int taskId, TaskStatus status) {
        if (tasksMap.containsKey(taskId)) {
            Task taskToUpdate = tasksMap.get(taskId);
            taskToUpdate.setTaskStatus(status);
            tasksMap.put(taskId, taskToUpdate);
            System.out.println("Task with ID " + taskId + " successfully updated");
        } else {
            System.out.println("Task with ID " + taskId + " was not found");
        }
    }

    //Метод для изменения срока выполнения задачи
    @Override
    public void updateTaskDeadline(int taskId, String deadline) {
        if (tasksMap.containsKey(taskId)) {
            Task taskToUpdate = tasksMap.get(taskId);
            taskToUpdate.setDeadline(deadline);
            tasksMap.put(taskId, taskToUpdate);
            System.out.println("Task with ID " + taskId + " successfully updated");
        } else {
            System.out.println("Task with ID " + taskId + " was not found");
        }
    }

    //Метод для изменения исполнителя задачи
    @Override
    public void updateTaskImplementer(int taskId, int implementerID) {
        if (tasksMap.containsKey(taskId) && EmployeesManager.getEmployeesMap().containsKey(implementerID)) {
            Task taskToUpdate = tasksMap.get(taskId);
            taskToUpdate.setImplementerId(implementerID);
            tasksMap.put(taskId, taskToUpdate);
            System.out.println("Task with ID " + taskId + " successfully updated");
        } else {
            System.out.println("Task`s ID or implementor`s ID was not found");
        }
    }

    //Метод для сортировки задач по исполнителю
    @Override
    public ArrayList<Task> sortTasksByImplementer (int implementerID){

        ArrayList <Task> filtered = new ArrayList<>();

        for (Task task : tasksMap.values()){
            if (task.getImplementerId() == implementerID){
                filtered.add(task);
            }
        }
        if (!filtered.isEmpty()) {
            System.out.println(filtered.size() + "objects was found");
            return filtered;
        }
        else {
            System.out.println("Objects was not found");
            return null;
        }
    }




}
