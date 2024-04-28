package homeworkLesson29;

public enum TypeOfPaint {
    ACRYLIC("acrylic paint"),
    OIL("oil paint"),
    WATERCOLOR("watercolor paint"),
    GOUACHE ("gouache paint"),
    TEMPERA ("tempera paint"),
    ENCAUSTIC ("encaustic paint"),
    OTHER("other");

    String description;

    TypeOfPaint(String description) {
        this.description = description;
    }
}
