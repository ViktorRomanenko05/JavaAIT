package HomeworkLesson09;

import java.util.Scanner;

public class Task01 {
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

            if (operation < 1 || operation > 4)
            {
                System.out.println("\nВы ввели некорректный номер операции.");
            }

            else
            {
                System.out.println("Через пробел введите два числа для проведения операции и нажмите клавишу \"Enter\":\n");
                double numberOne = scanner.nextDouble();
                double numberTwo = scanner.nextDouble();


                if (operation == 1)
                {
                    result = numberOne + numberTwo;
                    System.out.println("Результат: " + numberOne + " + " + numberTwo + " = " + result + "\n");

                } else if (operation == 2) {
                    result = numberOne - numberTwo;
                    System.out.println("Результат: " + numberOne + " - " + numberTwo + " = " + result + "\n");

                } else if (operation == 3) {
                    result = numberOne * numberTwo;
                    System.out.println("Результат: " + numberOne + " X " + numberTwo + " = " + result + "\n");
                }
                else
                {
                    if (numberTwo != 0)
                    {
                        result = numberOne / numberTwo;
                        System.out.println("Результат: " + numberOne + " : " + numberTwo + " = " + result + "\n");
                    }
                    else
                    {
                        System.out.println("\n!!Нельзя делить на 0\n");
                    }
                }

                System.out.println("Желаете ли вы продолжить?\nВведите \"1\" либо \"2\":\n " +
                        "ДА - 1\n" +
                        "НЕТ - 2\n");

                cycle = scanner.nextInt();

                if (cycle != 1) {
                    System.out.println(cycle == 2 ? "Спасибо, до новых встреч." : "Некорректный выбор,\n" +
                            "работа программы остановлена.\nЗапустите программу заново при необходимости.");
                }
            }
        }
    }
}
