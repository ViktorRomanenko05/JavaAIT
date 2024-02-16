package home.app;

import java.util.Scanner;

public class HomeworkLesson08Task02 {

    public static void main(String[] args) {

        byte counter = 1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите целое число");

        int number = scanner.nextInt();
        scanner.close();

        while (counter < 11) {
            System.out.println(counter + " X " + number + " = " + counter * number);
            counter++;
        }
    }
}