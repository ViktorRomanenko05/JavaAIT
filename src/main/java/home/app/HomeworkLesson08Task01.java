package home.app;

import java.util.Scanner;

public class HomeworkLesson08Task01 {

    public static void main(String[] args) {


        System.out.println("Введите число (например 20)");

        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        System.out.println("Чётные числа от 0 до введённого значения включительно:");

        for (int i = 0; i <= number; i++)
        {
            if (i % 2 ==0)
            {System.out.print(i + " ");}

        }
    }
}