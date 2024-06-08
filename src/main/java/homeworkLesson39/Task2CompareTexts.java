package homeworkLesson39;
/*
Задание 2: Сравнение содержимого двух файлов

        Цель задания: Написать программу на Java, которая сравнивает содержимое двух файлов на идентичность, используя FileInputStream.

        Описание задания:

        Создайте два текстовых файла. Первый файл заполните некоторым текстом, а второй файл создайте как копию первого, возможно, с небольшими изменениями.
        Напишите программу, которая открывает оба файла с помощью FileInputStream и сравнивает их содержимое.
        Программа должна выводить сообщение, указывающее, идентичны ли файлы или нет.
        Если файлы не идентичны, программа должна указать первую позицию, в которой файлы различаются.
        Убедитесь, что программа корректно обрабатывает все возможные исключения.
        Пример вывода:

        Файлы идентичны.
        или
        Файлы различаются на позиции 102.

 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Task2CompareTexts {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task1ReadAndCount.class);

    public static void main(String[] args) {
        InputStreamReader inputStream1 = null;
        InputStreamReader inputStream2 = null;
        HashMap<Character, Integer> charCounter = new HashMap<>();

        try {
            inputStream1 = new InputStreamReader(new FileInputStream("task2text1.txt"), StandardCharsets.UTF_8);
            inputStream2 = new InputStreamReader(new FileInputStream("task2text2.txt"), StandardCharsets.UTF_8);
            int data1;
            int data2;
            int counter = 0;
            boolean compare = true;

            while ((data1 = inputStream1.read()) != -1 && (data2 = inputStream2.read()) != -1) {
                counter++;
                if (data1 != data2) {
                    LOGGER.info("Тексты различаются на позиции № {}", counter);
                    compare = false;
                    break;
                }

            }
            if (compare) {
                if (inputStream1.read() != -1 || inputStream2.read() != -1) {
                    LOGGER.info("Тексты имеют разную длину");
                    compare = false;
                }
            }
            if (compare) {
                LOGGER.info("Тексты идентичны");
            }
        } catch (FileNotFoundException exception) {
            LOGGER.error("File not found", exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error("Input / Output error", exception.getMessage());
        } finally {
            try {
                inputStream1.close();
                inputStream2.close();
            } catch (IOException e) {
                LOGGER.error("I/O error", e);
            }
        }
    }
}
