package HomeworkLesson14;

import java.util.Scanner;

/*Используя массив хобби (как в задаче 1),
попросите пользователя ввести номер своего любимого хобби из списка.
Выведите на экран название выбранного хобби.
 */
public class Task03 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        byte number = 0;
        System.out.println("Введите номер вашего хобби из списка:\n");
        for (String hobbyName: Task01.hobby) {
            number++;
            System.out.println(number + " " + hobbyName);
        }
        while (!scanner.hasNextInt()) {

            System.out.println("Ошибка, введите цифру");
            scanner.next();
        }
        int hobbyNumber = scanner.nextInt();
        scanner.close();

        if (hobbyNumber < 1 || hobbyNumber > 5){
            System.out.println("Вы ввели не верный номер");
        }
        else {
        System.out.println("Ваш выбор: " + Task01.hobby[hobbyNumber-1]);
        }
    }
}
