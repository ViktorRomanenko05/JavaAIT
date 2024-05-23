package homeworkLesson34;

public enum PrecipitationLevels {
    LOW ("Precipitation level is low"),
    MEDIUM ("Precipitation level is medium"),
    HIGH ("Precipitation level is high"),
    INCORRECT ("Incorrect input data");

    private String description;

    PrecipitationLevels(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
