package home.app;

/*
Проверка Чётных и Нечётных Чисел: Напишите программу, которая запрашивает
у пользователя целое число и сообщает, является ли это число чётным или нечётным.
 */

import java.util.Scanner;

public class HomeworkLesson06Task021 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите целое число: ");
        int number = scan.nextInt();
        scan.close();

        int check = number / 2;

        if (check * 2 == number)
        {
            System.out.println("Введённое число является чётным");
        }

        else
        {
            System.out.println("Введённое число является нечётным");
        }

    }
}