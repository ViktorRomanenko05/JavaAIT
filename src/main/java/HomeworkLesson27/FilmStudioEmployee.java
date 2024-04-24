package HomeworkLesson27;

import java.util.Objects;

public class FilmStudioEmployee {

    private String name;
    private String position;
    private int experienceYears;
    private final int id; //неизменяемая переменная
    private AccessLevel accessLevel;

    //Генерируем конструктор
    public FilmStudioEmployee(String name, String position, int experienceYears, int id, AccessLevel accessLevel) {
        this.name = name;
        this.position = position;
        this.experienceYears = experienceYears;
        this.id = id;
        this.accessLevel = accessLevel;
    }

    //Генерируем геттеры и сеттеры кроме сеттера id. Подразумеваем этот параметр неизменяемым.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getId() {
        return id;
    }


    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    //Генерируем методы equals и hashcode только по параметру id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmStudioEmployee that = (FilmStudioEmployee) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void work(){
        System.out.println("\nAn employee " + name + " on position " + position + " is working on their task");
    }

    @Override
    public String toString() {
        return "FilmStudioEmployee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}
