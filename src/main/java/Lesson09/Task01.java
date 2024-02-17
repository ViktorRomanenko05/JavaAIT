package Lesson09;


import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int number = scanner.nextInt();
        int big = number;
        int small = number;


        while (number >= 0) {
            if (number > big) {
                big = number;
            } else if (number < small) {
                small = number;
            }
            number = scanner.nextInt();
        }
        System.out.println(big);
        System.out.println(small);
    }
}
