package homeworkLesson41;
/*
2. Запись результатов медицинских анализов

Цель:

Создать приложение для ввода и сохранения результатов медицинских анализов пациентов в файл.

Описание задачи:

Формат ввода: Пользователь вводит данные через консоль: ФИО пациента, тип анализа, значение анализа,
дата проведения.
Требования к программе:
Записывать введённые данные в конец существующего файла, не удаляя предыдущую информацию.
Обеспечить возможность добавления нескольких записей подряд.
Используемые технологии:

FileWriter для записи данных в файл.
BufferedWriter для оптимизации процесса записи.
Проверка наличия файла и создание файла, если он не существует.

 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Task2AnalysisWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task1PacientFileReader.class);

    static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        int counter = 0;

        while (true) {
            counter++;

            String name;
            String analysisType;
            String description;
            LocalDate analysisDate = null;

            LOGGER.info("Writing data of patient {} started", counter);

            System.out.println("Введте ФИО пациента");
            name = scanner.nextLine().trim();
            System.out.println("Введите тип анализа");
            analysisType = scanner.nextLine().trim();
            System.out.println("Введите описание результата анализа:");
            description = scanner.nextLine().trim();
            System.out.println("Введите дату анализа в формате дд.мм.гггг");

            while (analysisDate == null) {
                try {
                    analysisDate = LocalDate.parse(scanner.nextLine().trim(), formatter);
                } catch (DateTimeParseException exception) {
                    System.out.println("Дата введена в не верном формате. Пожалуйста, повторите ввод.");
                    LOGGER.error(exception.getMessage());
                }
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson41/task2AnalysisOutput.txt", StandardCharsets.UTF_8, true))) {
                bufferedWriter.write(name + ", " + analysisType + ", " + description + ", " + analysisDate.format(formatter) + "\n");
            } catch (FileNotFoundException exception) {
                LOGGER.error(exception.getMessage());
            } catch (IOException exception) {
                LOGGER.error(exception.getMessage());
            }

            System.out.println("Желаете добавить еще одну запись? (да/нет)");
            String next = scanner.nextLine().trim();
            if (next.equalsIgnoreCase("нет") || next.equalsIgnoreCase("no") || next.equalsIgnoreCase("ytn")) {
                System.out.println("Спасибо, до новых встреч.");
                LOGGER.info("Writing session is over. Number of patients: {}", counter);
                break;
            }


        }
    }
}
