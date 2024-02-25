package HomeworkLesson09;

import java.util.Scanner;

public class Task01new {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int operation = 0;
        String cycle = "1";
        double result;

        while (cycle.equals("1")) {

            byte attempts = 10;

            System.out.println("\nПожалуйста, выберите тип необходимой операции.\n" +
                    "Введите соответствующий номер:\n\n" +
                    "1 - СЛОЖЕНИЕ\n" +
                    "2 - ВЫЧИТАНИЕ\n" +
                    "3 - УМНОЖЕНИЕ\n" +
                    "4 - ДЕЛЕНИЕ\n");


            while (!scanner.hasNextInt() && attempts > 1) {
                attempts--;
                System.out.println("Ошибка, введите цифру");
                System.out.println("Количество оставшихся попыток: " + attempts);
                scanner.next();
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Превышено допустимое количество вводов!\n" +
                        "Пожалуйста, перезапустите программу при необходимости.");
                break;
            }

            operation = scanner.nextInt();
            attempts = 10;

            if (operation < 1 || operation > 4) {
                System.out.println("\nВы ввели некорректный номер операции.\n");
            } else {
                System.out.print("Введите первое число:");

                while (!scanner.hasNextDouble() && attempts > 1) {
                    attempts--;
                    System.out.println("Ошибка, введите цифру");
                    System.out.println("Количество оставшихся попыток: " + attempts);
                    scanner.next();
                }
                if (!scanner.hasNextInt()) {
                    System.out.println("Превышено допустимое количество вводов!\n" +
                            "Пожалуйста, перезапустите программу при необходимости.");
                    break;
                }
                double numberOne = scanner.nextDouble();
                attempts =10;

                System.out.print("Введите второе число:");

                while (!scanner.hasNextInt() && attempts > 1) {
                    attempts--;
                    System.out.println("Ошибка, введите цифру");
                    System.out.println("Количество оставшихся попыток: " + attempts);
                    scanner.next();
                }
                if (!scanner.hasNextInt()) {
                    System.out.println("Превышено допустимое количество вводов!\n" +
                            "Пожалуйста, перезапустите программу при необходимости.");
                    break;
                }
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
            }


            System.out.println("Для продолжения работы введите 1\n" +
                    "Для завершения - любое другое значение");

            cycle = scanner.next().trim();

            if (!cycle.equals("1")) {
                System.out.println("Спасибо, до новых встреч.");
            }
        }scanner.close();
    }
}