package homeworkLesson41;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task3Deserializing {

    private static List<Patient> deserializedPatients = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson41/task3patients.ser"))) {
            deserializedPatients = (ArrayList<Patient>) objectInputStream.readObject();
        } catch (FileNotFoundException exception) {
            exception.getMessage();
        } catch (IOException exception) {
            exception.getMessage();
        } catch (ClassNotFoundException exception) {
            exception.getMessage();
        }

        for (Patient patient : deserializedPatients) {
            System.out.println(patient.getName() + ", " + patient.getAge() + ", " + patient.getDiagnosis() + ", " + patient.getLastVisitDate().format(formatter));
        }
    }
}
