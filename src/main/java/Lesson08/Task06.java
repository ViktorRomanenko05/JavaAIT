package Lesson08;

import java.util.Scanner;

public class Task06 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        boolean isPrime = true;

        for (int i = 2; i <= number / 2 + 1; i++) {
            if (number % i == 0) {
                isPrime = false;
            }
        }
        if (isPrime) {
            System.out.println("Простое число");
        } else {
            System.out.println("Не простое число");
        }
    }
}