package homework30new;

import java.util.Objects;

public class Task {

    private int taskId;

    private String description;

    private TaskStatus taskStatus;

    private String Deadline;

    public Task(int taskId, String description, String deadline) {
        this.taskId = taskId;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
        Deadline = deadline;
    }

    public int getTaskId() {
        return taskId;
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

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", taskStatus=" + taskStatus +
                ", Deadline='" + Deadline + '\'' +
                '}';
    }
}
