package HomeworkLesson14;

/*Напишите программу, которая позволяет добавить новое хобби
в существующий массив хобби. Поскольку размер массива в Java фиксирован,
вам нужно будет создать новый массив большего размера и скопировать в него
элементы из старого массива, добавив новое хобби.
 */

import java.util.Scanner;

public class Task04CorrectionNew {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] hobby = {"велоспорт", "футбол", "рисование", "программирование", "рыбалка"};
        int newHobbyQuantity = 0;
        int counter = 0;

        System.out.println("Здравствуйте!\nВ списке есть следующие хобби:\n");
        for (String hobbyName : hobby) {
            System.out.println(hobbyName);
        }
        System.out.println("\nПожалуйста, введите количество хобби,\n" +
                "которые планируете добавить:");

        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка, введите цифру");
            scanner.next();
        }
        newHobbyQuantity = scanner.nextInt();
        scanner.nextLine();

        if (newHobbyQuantity<0){
            System.out.println("Вы ввели не верное значение");
        }

        else {

            String[] newHobby = new String[newHobbyQuantity + hobby.length];

            System.arraycopy(hobby, 0, newHobby, 0, hobby.length);

            for (int i = 0; i < newHobbyQuantity; i++) {
                counter++;
                System.out.println("Пожалуйста, введите новое хобби № " + counter);
                newHobby[hobby.length + i] = scanner.nextLine();
            }
            scanner.close();

            System.out.println("Обновленный список хобби:\n");

            for (String hobbyName : newHobby) {
                System.out.println(hobbyName);
            }
        }
    }
}