package homeworkLesson29;

import java.util.Objects;

public class WorkOfArt {

    private int artworkId;

    private String author;

    private String name;

    private int yearOfCreation;

    private String description;

    private ObjectType objectType;

    public WorkOfArt(int artworkId, String author, String name, int yearOfCreation, String description, ObjectType objectType) {
        this.artworkId = artworkId;
        this.author = author;
        this.name = name;
        this.yearOfCreation = yearOfCreation;
        this.description = description;
        this.objectType = objectType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public int getArtworkId() {
        return artworkId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkOfArt workOfArt = (WorkOfArt) o;
        return artworkId == workOfArt.artworkId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artworkId);
    }

    @Override
    public String toString() {
        return "WorkOfArt{" +
                "artworkId=" + artworkId +
                ", author='" + author + '\'' +
                ", yearOfCreation=" + yearOfCreation +
                ", description='" + description + '\'' +
                '}';
    }
}
