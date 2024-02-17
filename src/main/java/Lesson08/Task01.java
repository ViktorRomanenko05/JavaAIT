package Lesson08;

import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double number = scanner.nextDouble();

        System.out.println(number % 2 == 0 ? "Четное" : "Нечетное");
        System.out.println(number % 1 == 0 ? "Целое" : "Дробное");
        System.out.println(number > 0 ? "Положительное" : (number < 0 ? "Отрицательное" : "Ноль"));
        System.out.println(number % 1 == 0 && number >= 0 && number < 9 ? "Цифра" : "Число");


    }
}
//1.Простой if / else (не else if)
//2. Оба блока будут однострочными
//3.Оба блока используют одинаковый код с разными значениями