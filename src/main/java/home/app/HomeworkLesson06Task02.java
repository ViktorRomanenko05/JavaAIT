package home.app;

/*
Проверка Чётных и Нечётных Чисел: Напишите программу, которая запрашивает
у пользователя целое число и сообщает, является ли это число чётным или нечётным.
 */
import java.util.Scanner;

public class HomeworkLesson06Task02 {

    public static void main(String[] args) {

        Scanner scan = new Scanner (System.in);

        System.out.println("Введите целое число: ");
        double number = scan.nextDouble();
        scan.close();

        double result = number / 2;

        int resultInt = (int) result;

        double check = result - resultInt;

        if (check / 2 == 0)
        {
        System.out.println("Введённое число является чётным");
        }
        else
        {
            System.out.println("Введённое число является нечётным");
        }

    }
}
