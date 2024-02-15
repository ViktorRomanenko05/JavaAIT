package home.app;

import java.util.Scanner;

public class HomeworkLesson08Task04 {
    public static void main(String[] args) {

        int sum = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите целое число");
        int number = scanner.nextInt();
        scanner.close();

        while (number != 0)
        {
            sum = sum + number % 10;
            number /= 10;
        }

        System.out.print("\nСумма цифр введенного числа равна " + sum + "\n");
    }
}