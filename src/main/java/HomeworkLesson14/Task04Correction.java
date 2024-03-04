package HomeworkLesson14;

/*Напишите программу, которая позволяет добавить новое хобби
в существующий массив хобби. Поскольку размер массива в Java фиксирован,
вам нужно будет создать новый массив большего размера и скопировать в него
элементы из старого массива, добавив новое хобби.
 */

import java.util.Scanner;

public class Task04Correction {

        static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {

            System.out.println("Введите название хобби для добавления:");
            String yourHobby = scanner.nextLine().toLowerCase().trim();
            scanner.close();

            String[] hobbyBig = new String[Task01.hobby.length + 1];

            System.arraycopy(Task01.hobby, 0, hobbyBig, 0, Task01.hobby.length);
            hobbyBig[Task01.hobby.length] = yourHobby;

            System.out.println("Обновлённый список хобби:\n");

            for (String hobbyName : hobbyBig) {
                System.out.println(hobbyName);
            }
        }
}
