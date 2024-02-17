package Lesson08;

import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.println("Введите натуральное число");
            n = scanner.nextInt();
        } while (n <= 0);
        System.out.println("Спасибо");
    }
}
