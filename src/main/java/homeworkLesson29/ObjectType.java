package homeworkLesson29;

public enum ObjectType {
    PAINTING ("painting"),
    SCULPTURE("sculpture");

    String description;

    ObjectType(String description) {
        this.description = description;
    }
}
