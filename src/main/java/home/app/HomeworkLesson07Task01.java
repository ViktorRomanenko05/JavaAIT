package home.app;

/*
Напишите программу, которая запросит ввести ваш возраст, и напишет, кем вы являетесь:
ребенком, подростком, взрослым либо стариком.
 */

import java.util.Scanner;

public class HomeworkLesson07Task01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ваш возраст: ");
        int age = scanner.nextInt();
        scanner.close();

        if (age < 0 || age > 130) {
            System.out.println("Возраст введен не верно");
        } else if (age < 13) {
            System.out.println("Вы ребенок");
        } else if (age < 20) {
            System.out.println("Вы подросток");
        } else if (age < 65) {
            System.out.println("Вы взрослый");
        } else {
            System.out.println("Вы старик");
        }

    }
}