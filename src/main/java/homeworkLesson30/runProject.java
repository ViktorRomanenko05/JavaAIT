package homeworkLesson30;

import homeworkLesson30.employee.Employee;
import homeworkLesson30.employee.EmployeesManager;
import homeworkLesson30.interfaces.EmployeeInterface;
import homeworkLesson30.interfaces.ProjectInterface;
import homeworkLesson30.interfaces.TaskInterface;
import homeworkLesson30.project.Project;
import homeworkLesson30.project.ProjectsManager;
import homeworkLesson30.task.Task;
import homeworkLesson30.task.TaskStatus;
import homeworkLesson30.task.TasksManager;

import java.util.ArrayList;

public class runProject {
    public static void main(String[] args) {

        //Создадим необходимые интерфейсы
        EmployeeInterface employeeInterface = new EmployeesManager();
        ProjectInterface projectInterface = new ProjectsManager();
        TaskInterface taskInterface = new TasksManager();

        //Создадим несколько сотрудников
        Employee employee = new Employee(836526,"Peter Smith", "Developer");
        Employee employee1 = new Employee(187464,"Aisha Mohammed", "Developer");
        Employee employee2 = new Employee(471381,"Chen Wei", "Developer");
        Employee employee3 = new Employee(537645,"Carlos Rivera", "DevOps");
        Employee employee4 = new Employee(937243,"Sophia Rossi", "Administrator");
        Employee employee5 = new Employee(655434,"Sven Eriksson", "Engineer");
        Employee employee6 = new Employee(344343,"Leila Kone", "Engineer");

        //Добавим сотрудников в список
        employeeInterface.addEmployee(employee);
        employeeInterface.addEmployee(employee1);
        employeeInterface.addEmployee(employee2);
        employeeInterface.addEmployee(employee3);
        employeeInterface.addEmployee(employee4);
        employeeInterface.addEmployee(employee5);
        employeeInterface.addEmployee(employee6);

        //Также сотрудников можно добавлять через функционал интерфейса
        //с автоматическим присвоением id:
        employeeInterface.createEmployee("Hiroshi Tanaka", "QA");
        employeeInterface.createEmployee("Maya Dutta", "QA");
        employeeInterface.createEmployee("John Smith", "System Analyst");

        //Выведем на экран список всех сотрудников
        employeeInterface.printAllEmployees();

        //Создадим два проекта
        Project project = new Project(773265, "Pizza Delivery");
        Project project1 = new Project(937355, "Photo App");

        //Также реализован функционал для создания и добавления проекта с использованием метода
        //автоматически присваивающего id и добавляющего проект в список
        projectInterface.createProject("Navigation");

        //Добавим в список объекты созданные вручную
        projectInterface.addProject(project);
        projectInterface.addProject(project1);

        //Добавим сотрудников в проекты
        //Реализованный функционал позволяет сделать это 2 способами:
        //передать как объекты или с использованием ID

        projectInterface.addEmployeeToProject(project, employee);
        projectInterface.addEmployeeToProject(project, employee1);
        projectInterface.addEmployeeToProject(project, employee2);
        projectInterface.addEmployeeToProjectById(773265, 537645);
        projectInterface.addEmployeeToProjectById(773265, 937243);
        projectInterface.addEmployeeToProjectById(773265, 655434);
        projectInterface.addEmployeeToProjectById(773265, 344343);

        /*
        Реализованный функционал позволяет создавать задачи для сотрудников несколькими способами
        Как вручную, так и через функционал интерфейса TaskInterface.
        Также добавлена возможность добавления задачи напрямую из вкладки проекта с автоматическим
        присвоением id и добавлением задачи в список исполнителя и список задач проекта.

        Так как приложение реализовано так, что задача всегда привязана к исполнителю, но не всегда
        принадлежит какому-либо проекту - использована перегрузка методов для добавления задачи
        с различными вариациями аргументов в методе добавления
         */

        Task task = new Task (243298, 537645,"Task1", "22.06.2024");
        Task task1 = new Task (946128, 537645,"Task1", "20.06.2024");
        Task task2= new Task (946353, 537645,"Task1", "22.05.2024");
        Task task3 = new Task (756417, 537645,"Task1", "20.05.2024");
        Task task4 = new Task (814356, 937243,"Task1", "22.06.2024");
        Task task5 = new Task (944622, 937243,"Task1", "22.05.2024");
        Task task6 = new Task (487664, 655434,"Task1", "03.07.2024");
        Task task7 = new Task (438474, 344343,"Task1", "15.07.2024");

        //добавим созданные задачи в список задач, исполнителям и в проект
        taskInterface.addTask(task, 773265);
        taskInterface.addTask(task1,773265);
        taskInterface.addTask(task2,773265);
        taskInterface.addTask(task3,773265);
        taskInterface.addTask(task4,773265);
        taskInterface.addTask(task5,773265);
        taskInterface.addTask(task6,773265);
        taskInterface.addTask(task7,773265);

        //создадим задачи из вкладки проекта
        projectInterface.createTaskInProject(655434,"Task1", "23.07.2024", project);
        projectInterface.createTaskInProject(655434,"Task2", "23.07.2024", project);
        projectInterface.createTaskInProject(655434,"Task3", "23.07.2024", project);
        projectInterface.createTaskInProject(937243,"Task4", "23.07.2024", project);
        projectInterface.createTaskInProject(937243,"Task5", "23.07.2024", project);
        projectInterface.createTaskInProject(937243,"Task6", "23.07.2024", project);

        //Выведем на экран всех сотрудников, задействованных в проекте
        projectInterface.printAllEmployeesInProject(project);

        //Выведем на экран все задачи проекта
        projectInterface.printAllTasksInProject(project);

        //выведем на экран полное состояние проекта
        //Выводит на экран каждого сотрудника и его задачи поочередно
        projectInterface.displayProjectInfo(project);

        //Функционал для поиска всех задач сотрудника
        ArrayList <Task> tasksOfEmployee = taskInterface.sortTasksByImplementer(655434);

        //Функционал для изменения задачи
        taskInterface.updateTask(243298, "NewTask");
        taskInterface.updateTaskDeadline(243298, "22.12.2024");
        taskInterface.updateTaskStatus(243298, TaskStatus.IN_PROGRESS);

        //Метод для поиска сотрудника
        employeeInterface.findEmployeeByName("Chen Wei");

        //Функционал по удалению сотрудника / проекта
        employeeInterface.removeEmployeeById(836526);

        projectInterface.removeProjectById(937355);
    }
}