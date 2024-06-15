package homeworkLesson41;

/*
    1. Чтение медицинских записей

    Цель:

    Разработать программу на Java для чтения данных о пациентах из текстового
    файла и вывода этой информации на экран.

    Описание задачи:

    Формат данных в файле: Каждая строка файла содержит данные одного пациента,
    разделённые запятыми: ФИО, возраст, диагноз, дата последнего визита.
    Требования к программе:
    Прочитать данные из файла.
    Отобразить данные в консоли в удобочитаемом формате.
    Обработать возможные ошибки при чтении файла, например, если файл не найден или повреждён.
    Используемые технологии:

    FileReader для построчного чтения файла.
    BufferedReader для буферизации данных, что ускоряет чтение.
    Обработка исключений IOException и FileNotFoundException.

 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Task1PacientFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task1PacientFileReader.class);

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson41/task1HospitalInput.txt", UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            LOGGER.info("Finished reading file");
        } catch (FileNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
