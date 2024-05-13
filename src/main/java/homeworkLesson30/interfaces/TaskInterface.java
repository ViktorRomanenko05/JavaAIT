package homeworkLesson30.interfaces;

import homeworkLesson30.employee.Employee;
import homeworkLesson30.task.Task;
import homeworkLesson30.task.TaskStatus;

import java.util.ArrayList;

public interface TaskInterface {

    public void addTask(Task task);

    public void addTask(Task task, int projectID);

    public void updateTask(int taskId, String description);

    public void updateTaskStatus(int taskId, TaskStatus status);

    public void updateTaskDeadline(int taskId, String deadline);

    public void updateTaskImplementer(int taskId, int implementerID);

    public ArrayList<Task> sortTasksByImplementer (int implementerID);
}
