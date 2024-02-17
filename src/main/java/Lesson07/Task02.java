package Lesson07;

import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        System.out.println("Введите ваше имя");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Привет," + name + "!");

    }
}
