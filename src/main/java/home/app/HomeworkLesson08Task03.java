package home.app;

import java.util.Scanner;

public class HomeworkLesson08Task03 {

    public static void main(String[] args) {

        int sum = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите натуральное число");

        int number = scanner.nextInt();
        scanner.close();

        for (int i = 1; i <= number; i++)
        {
                sum += i;
        }

        System.out.println("\nСумма всех натуральных чисел перед " + number + " включительно, равна " + sum);
    }
}