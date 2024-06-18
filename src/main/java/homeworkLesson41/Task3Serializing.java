package homeworkLesson41;
/*
3. Сериализация списка пациентов

Цель:

Разработать программу для сериализации и десериализации списка объектов,
представляющих пациентов, с последующим выводом информации о них.

Описание задачи:

Класс Patient: Должен содержать поля для имени, возраста, диагноза и даты последнего визита.
Класс должен быть сериализуемым.

Требования к программе:
Сериализовать список объектов Patient в файл.
Десериализовать список из файла и вывести данные о каждом пациенте.
Используемые технологии:

ObjectOutputStream для сериализации объектов в файл.
ObjectInputStream для десериализации объектов из файла.
Обработка исключений, связанных с сериализацией и десериализацией, включая ClassNotFoundException.
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task3Serializing {

    public static void main(String[] args) {


        List<Patient> patients = new ArrayList<>();

        Patient patient1 = new Patient("Иванов Иван Всеволодович", 35, "Бронхит", LocalDate.of(2024, 6, 11));
        Patient patient2 = new Patient("Махалин Алексей Денисович", 30, "Гайморит", LocalDate.of(2024, 6, 9));
        Patient patient3 = new Patient("Аронов Юрий Петрович", 27, "Бронхит", LocalDate.of(2024, 6, 15));
        Patient patient4 = new Patient("Вахрушев Антон Семенович", 49, "Отит", LocalDate.of(2024, 6, 14));
        Patient patient5 = new Patient("Лапотенков Егор Ильич", 21, "COVID-19", LocalDate.of(2024, 6, 1));

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);
        patients.add(patient5);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson41/task3patients.ser"))) {
            objectOutputStream.writeObject(patients);
        } catch (FileNotFoundException exception) {
            exception.getMessage();
        } catch (IOException exception) {
            exception.getMessage();
        }
    }
}
