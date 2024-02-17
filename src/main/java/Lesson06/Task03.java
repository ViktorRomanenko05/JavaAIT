package Lesson06;

import java.util.Scanner;

public class Task03 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите Ваш возраст: ");
        int age = scanner.nextInt();
        int minAge = 16;
        String text = null;
        System.out.println("Введите имя: ");
        String name = scanner.next();
        if (age < minAge) {
            System.out.println(name + ", вы несовершеннолетний(/яя)");
        } else {
            System.out.println(name + ", вы совершеннолетний(/яя)");
        }
        scanner.close();
        System.out.println(text);
    }

}
