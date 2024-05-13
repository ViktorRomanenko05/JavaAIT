package homeworkLesson30.task;

import homeworkLesson30.employee.Employee;

import java.time.LocalDate;
import java.util.Objects;

public class Task {

    private final int taskId;
    private int implementerId;
    private String description;
    private TaskStatus taskStatus = TaskStatus.NEW;
    private String deadline;

    public Task(int taskId, int implementerId, String description, String deadline) {
        this.taskId = taskId;
        this.implementerId = implementerId;
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getImplementerId() {
        return implementerId;
    }

    public void setImplementerId(int implementerId) {
        this.implementerId = implementerId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }



}
