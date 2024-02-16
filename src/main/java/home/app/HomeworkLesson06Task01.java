package home.app;

/*
Калькулятор Суммы: Напишите программу, которая запрашивает
у пользователя два числа и выводит их сумму.
 */

import java.util.Scanner;

public class HomeworkLesson06Task01 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите число №1: ");
        double numberOne = scan.nextDouble();

        System.out.println("Введите число №2: ");
        double numberTwo = scan.nextDouble();

        scan.close();

        double sum = numberOne + numberTwo;

        System.out.println("Сумма введённых чисел равна " + sum);


    }
}
