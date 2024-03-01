package HomeworkLesson14;

/*Напишите программу, которая позволяет добавить новое хобби
в существующий массив хобби. Поскольку размер массива в Java фиксирован,
вам нужно будет создать новый массив большего размера и скопировать в него
элементы из старого массива, добавив новое хобби.
 */

import java.util.Scanner;


public class Task04 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Введите название хобби для добавления");
        String yourHobby = scanner.nextLine().toLowerCase().trim();
        scanner.close();

        String [] hobbyBig = new String [6];

        System.arraycopy(Task01.hobby,0,hobbyBig,0,Task01.hobby.length);
        hobbyBig[5] = yourHobby;

        System.out.println("Обновлённый список хобби:\n");

        for (String hobbyName: hobbyBig) {
            System.out.println(hobbyName);
        }
    }
}
