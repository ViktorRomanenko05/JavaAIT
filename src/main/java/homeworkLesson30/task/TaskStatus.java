package homeworkLesson30.task;

public enum TaskStatus {
    NEW("new task"),
    IN_PROGRESS("task is in progress"),
    COMPLETED("task is completed");

    String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
