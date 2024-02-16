package home.app;

import java.util.Scanner;

public class Lesson08Task05 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите высоту треугольника");
        int alt = scanner.nextInt();

        for (int i = 1; i < alt; i++) {

            System.out.print("*\n");

            for (int j = 0; j < i; j++) {

                System.out.print("*");


            }

        }

        for (int i = alt - 1; i > -2; i--) {

            System.out.print("*\n");

            for (int j = i; j > 0; j--) {

                System.out.print("*");

            }

        }
    }

    public static class HomeworkLesson09Task01 {

        public static void main(String[] args) {

            int operation = 0;
            int cycle = 1;
            double result;

            while (cycle == 1) {
                System.out.println("\nПожалуйста, выберите тип необходимой операции.\n" +
                        "Введите соответствующий номер:\n\n" +
                        "1 - СЛОЖЕНИЕ\n" +
                        "2 - ВЫЧИТАНИЕ\n" +
                        "3 - УМНОЖЕНИЕ\n" +
                        "4 - ДЕЛЕНИЕ\n");

                Scanner scanner = new Scanner(System.in);
                operation = scanner.nextInt();

                if (operation < 1 || operation > 4) {
                    System.out.println("\nВы ввели некорректный номер операции.");
                } else {
                    System.out.println("Через пробел введите два числа для проведения операции и нажмите клавишу \"Enter\":\n");
                    double numberOne = scanner.nextDouble();
                    double numberTwo = scanner.nextDouble();


                    if (operation == 1) {
                        result = numberOne + numberTwo;
                        System.out.println("Результат: " + numberOne + " + " + numberTwo + " = " + result + "\n");

                    } else if (operation == 2) {
                        result = numberOne - numberTwo;
                        System.out.println("Результат: " + numberOne + " - " + numberTwo + " = " + result + "\n");

                    } else if (operation == 3) {
                        result = numberOne * numberTwo;
                        System.out.println("Результат: " + numberOne + " X " + numberTwo + " = " + result + "\n");
                    } else {
                        if (numberTwo != 0) {
                            result = numberOne / numberTwo;
                            System.out.println("Результат: " + numberOne + " : " + numberTwo + " = " + result + "\n");
                        } else {
                            System.out.println("\n!!Нельзя делить на 0\n");
                        }
                    }

                    System.out.println("Желаете ли вы продолжить?\nВведите \"1\" либо \"2\":\n " +
                            "ДА - 1\n" +
                            "НЕТ - 2\n");

                    cycle = scanner.nextInt();

                    if (cycle != 1) {
                        System.out.println(cycle == 2 ? "Спасибо, до новых встречь." : "Некорректный выбор,\n" +
                                "работа программы остановлена.\nЗапустите программу заново при необходимости.");
                    }
                }
            }
        }
    }
}
