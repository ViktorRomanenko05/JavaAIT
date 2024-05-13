package homework30new;

import java.util.HashMap;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HashMap<Integer, Project> projects = new HashMap<>();
    private static int projectIdCounter = 1;

    public static void main(String[] args) {
        int number;
        do {
            System.out.println("\nPlease select the required action. Enter a number:\n" +
            "1 - Create a project\n"+
            "2 - Add an employee to a project\n"+
            "3 - Remove an employee from a project\n"+
            "4 - Add a task to a project\n"+
            "5 - Assign a task to an employee\n"+
            "6 - Remove a task from a project\n"+
            "7 - Change the task's deadline\n"+
            "8 - Change the task's status\n"+
            "9 - Display project details\n"+
            "0 - Exit the program");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();
            }
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    createProject();
                    break;
                case 2:
                    addEmployeeToProject();
                    break;
                case 3:
                    removeEmployeeFromProject();
                    break;
                case 4:
                    addTaskToProject();
                    break;
                case 5:
                    assignTaskToEmployee();
                    break;
                case 6:
                    removeTaskFromProject();
                    break;
                case 7:
                    changeTaskDeadline();
                    break;
                case 8:
                    changeTaskStatus();
                    break;
                case 9:
                    displayProjectDetails();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid input, please enter a number between 0 and 9.");
            }
        } while (number != 0);
        scanner.close();
    }

    private static void createProject() {
        System.out.println("Enter project name:");
        String name = scanner.next();
        Project project = new Project(projectIdCounter++,name);
        projects.put(project.getProjectID(), project);
        System.out.println("Project created with ID: " + project.getProjectID());
    }

    private static void addEmployeeToProject() {
        int projectId = getValidProjectId();
        System.out.println("Enter employee ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int employeeId = scanner.nextInt();
        System.out.println("Enter employee name:");
        String name = scanner.next();
        System.out.println("Enter employee position:");
        String position = scanner.next();
        Employee employee = new Employee(employeeId, name, position);
        projects.get(projectId).addEmployee(employee);
        System.out.println("Employee added to project.");
    }

    private static void removeEmployeeFromProject() {
        int projectId = getValidProjectId();
        System.out.println("Enter employee ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int employeeId = scanner.nextInt();
        projects.get(projectId).removeEmployee(employeeId);
        System.out.println("Employee removed from project.");
    }

    private static void addTaskToProject() {
        int projectId = getValidProjectId();
        System.out.println("Enter task ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int taskId = scanner.nextInt();
        System.out.println("Enter task description:");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("Enter task deadline:");
        String deadline = scanner.next();
        Task task = new Task(taskId, description, deadline);
        projects.get(projectId).addTask(task);
        System.out.println("Task added to project.");
    }
    private static void assignTaskToEmployee() {
        int projectId = getValidProjectId();
        Project project = projects.get(projectId);
        System.out.println("Enter task ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int taskId = scanner.nextInt();
        System.out.println("Enter employee ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int employeeId = scanner.nextInt();

        if (project.getProjectEmployees().containsKey(employeeId) && project.getProjectTasks().containsKey(taskId)) {
            Employee employee = project.getProjectEmployees().get(employeeId);
            Task task = project.getProjectTasks().get(taskId);
            employee.addTask(task);
            System.out.println("Task assigned to employee.");
        } else {
            System.out.println("Invalid task ID or employee ID.");
        }
    }

    private static void removeTaskFromProject() {
        int projectId = getValidProjectId();
        Project project = projects.get(projectId);
        System.out.println("Enter task ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int taskId = scanner.nextInt();
        if (project.getProjectTasks().containsKey(taskId)) {
            project.removeTask(taskId);
            System.out.println("Task removed from project.");
        } else {
            System.out.println("Task ID not found.");
        }
    }

    private static void changeTaskDeadline() {
        int projectId = getValidProjectId();
        Project project = projects.get(projectId);
        System.out.println("Enter task ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int taskId = scanner.nextInt();
        if (project.getProjectTasks().containsKey(taskId)) {
            Task task = project.getProjectTasks().get(taskId);
            System.out.println("Current deadline: " + task.getDeadline());
            System.out.println("Enter new deadline:");
            scanner.nextLine();
            String newDeadline = scanner.nextLine();
            task.setDeadline(newDeadline);
            System.out.println("Task deadline updated.");
        } else {
            System.out.println("Task ID not found.");
        }
    }

    private static void changeTaskStatus() {
        int projectId = getValidProjectId();
        Project project = projects.get(projectId);
        System.out.println("Enter task ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int taskId = scanner.nextInt();
        if (project.getProjectTasks().containsKey(taskId)) {
            Task task = project.getProjectTasks().get(taskId);
            System.out.println("Current status: " + task.getTaskStatus());
            System.out.println("Select new status (1 - NEW, 2 - IN_PROGRESS, 3 - COMPLETED):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();
            }
            int statusOption = scanner.nextInt();
            switch (statusOption) {
                case 1:
                    task.setTaskStatus(TaskStatus.NEW);
                    break;
                case 2:
                    task.setTaskStatus(TaskStatus.IN_PROGRESS);
                    break;
                case 3:
                    task.setTaskStatus(TaskStatus.COMPLETED);
                    break;
                default:
                    System.out.println("Invalid status option.");
                    return;
            }
            System.out.println("Task status updated to " + task.getTaskStatus());
        } else {
            System.out.println("Task ID not found.");
        }
    }

    private static void displayProjectDetails() {
        int projectId = getValidProjectId();
        Project project = projects.get(projectId);
        System.out.println(project);
    }

    private static int getValidProjectId() {
        System.out.println("Enter project ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please enter a number.");
            scanner.next();
        }
        int projectId = scanner.nextInt();
        while (!projects.containsKey(projectId)) {
            System.out.println("Project not found, please enter a valid project ID:");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();
            }
            projectId = scanner.nextInt();
        }
        return projectId;
    }
}