package home.app;

import java.util.Scanner;

public class Lesson08Task05 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);
        System.out.println("Введите высоту треугольника");
        int alt = scanner.nextInt();

        for (int i = 1; i < alt; i++) {

            System.out.print("*\n");

            for (int j = 0; j < i; j++) {

                System.out.print("*");


            }

        }

        for (int i = alt-1; i > -2 ; i--) {

            System.out.print("*\n");

            for (int j = i; j > 0; j--) {

                System.out.print("*");

            }

        }
    }
}
