package Consultation100224;

import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int res = 1;
        // i Начинаем с 2 потому что на 1 умножать нет смысла
        for (int i = 2; i <= n; i++) {

            res *= i;
        }

        System.out.println(res);
    }
}
