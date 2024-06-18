package homeworkLesson39;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
Задание 1: Чтение файла и подсчёт символов

Цель задания: Написать программу на Java, которая использует FileInputStream
для чтения текстового файла и подсчитывает количество вхождений каждого символа.

Описание задания:

Создайте текстовый файл с некоторым содержимым. Например, файл может содержать простой текст,
такой как стихотворение или короткую статью.
Напишите программу, используя FileInputStream, которая открывает этот файл,
читает его содержимое по одному символу за раз и подсчитывает,
сколько раз каждый символ встречается в тексте.
Выведите результаты в консоль в формате: символ и количество его вхождений.
Обработайте возможные исключения, такие как FileNotFoundException и IOException.
Пример вывода:

a - 15
b - 3
c - 7
...
 */
public class Task1ReadAndCount {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task1ReadAndCount.class);

    public static void main(String[] args) {
        InputStreamReader inputStream = null; //Возникли проблемы с распознаванием кириллицы.
        //InputStreamReader позволил настроить необходимую кодировку
        HashMap<Character, Integer> charCounter = new HashMap<>();

        try {
            inputStream = new InputStreamReader(new FileInputStream("task1.txt"), StandardCharsets.UTF_8); //Указываем файл - источник и необходимый набор кодировки
            int data;

            while ((data = inputStream.read()) != -1) {
                char character = (char) data;
                System.out.print(new String(Character.toChars(data)));
                if (charCounter.containsKey(character)) {
                    charCounter.put(character, charCounter.get(character) + 1);
                } else {
                    charCounter.put(character, 1);
                }

            }
        } catch (FileNotFoundException exception) {
            LOGGER.error("File not found", exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error("Input / Output error", exception.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error("I/O error", e);
            }
        }
        System.out.println("\n");
        System.out.println("Результаты подсчета символов в тексте:");
        Iterator<Map.Entry<Character, Integer>> iterator = charCounter.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            Character key = entry.getKey();
            int value = entry.getValue();
            if (key == null) {  //Не понимаю как распознать перенос строки для информативного вывода:
                //null - не заходит в этот if, \n - не выводит количество переносов вообще
                System.out.println("перенос - " + value);
            } else if (key == ' ') {
                System.out.println("пробел - " + value);
            } else {
                System.out.println(key + " - " + value);
            }

        }
    }
}
