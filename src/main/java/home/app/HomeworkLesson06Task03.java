package home.app;

/*
Калькулятор Оценок: Создайте программу, которая запрашивает
у пользователя оценки по пяти предметам, затем рассчитывает и выводит средний балл.
 */

import java.util.Scanner;

public class HomeworkLesson06Task03 {
    public static void main(String[] args) {

        System.out.println("\nПрограмма для расчёта среднего балла\n");
        System.out.println("Введите Ваши оценки по пяти предметам:\n");

        Scanner scanGrade = new Scanner(System.in);

        System.out.println("Предмет 1: ");
        float sub1 = scanGrade.nextFloat();

        System.out.println("Предмет 2: ");
        float sub2 = scanGrade.nextFloat();

        System.out.println("Предмет 3: ");
        float sub3 = scanGrade.nextFloat();

        System.out.println("Предмет 4: ");
        float sub4 = scanGrade.nextFloat();

        System.out.println("Предмет 5: ");
        float sub5 = scanGrade.nextFloat();

        scanGrade.close();

        float middle = (sub1 + sub2 + sub3 + sub4 + sub5) / 5;

        System.out.println("\n Ваш средний балл: " + middle);


    }
}
