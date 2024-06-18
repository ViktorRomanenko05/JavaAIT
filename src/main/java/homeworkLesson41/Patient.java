package homeworkLesson41;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int age;

    private String diagnosis;

    private LocalDate lastVisitDate;

    public Patient(String name, int age, String diagnosis, LocalDate lastVisitDate) {
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.lastVisitDate = lastVisitDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }


}
