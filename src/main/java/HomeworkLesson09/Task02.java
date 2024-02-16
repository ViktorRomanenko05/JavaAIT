package HomeworkLesson09;

import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {

        int secret;
        int number = 0;
        int agree;

        boolean validInput;
        boolean validInput2;
        boolean win = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nЗдравствуйте!\uD83D\uDE09");

        do {
            agree = 0;
            byte counter = 20;
            secret = (int) (Math.random() * 101);

            System.out.println("Попытайтесь угадать число от 0 до 100\n" +
                    "У Вас есть 20 попыток.\nСледуйте подсказкам. Удачи!\n");

            while (counter > 0 && number != secret) {

                counter--;

                validInput = false;

                while (!validInput) {

                    System.out.println("Введите целое число от 0 до 100: ");
                    if (scanner.hasNextInt()) {
                        number = scanner.nextInt();
                        validInput = true;
                    } else {
                        System.out.println("Ошибка: Введено не верное значение.");
                        scanner.next();
                    }

                }// Закрытие контроля ввода 1

                if (number < secret) {
                    System.out.println("\nВведённое число меньше искомого");
                    System.out.println("Количество оставшихся попыток: " + counter + "\n");
                } else if (number > secret) {
                    System.out.println("\nВведённое число больше искомого");
                    System.out.println("Количество оставшихся попыток: " + counter + "\n");
                } else {
                    System.out.println("ПОЗДРАВЛЯЮ! ВЫ ВЫИГРАЛИ!\uD83D\uDE00\n");
                    win = true;
                }

            } // Закрытие while

            if (counter < 1) {
                System.out.println("К сожалению, вы проиграли.\n" +
                        "В следующий раз всё получится!\u2764\n");
            }
            System.out.println("Желаете ли сиграть еще?\n" +
                    "1 - ДА\n2 - НЕТ");

            validInput2 = false;
            number = 0;

            while (!validInput2) {

                System.out.println("\nВведите 1 либо 2 соответственно вашему выбору");
                if (scanner.hasNextInt()) {
                    agree = scanner.nextInt();
                    validInput2 = true;
                } else {
                    System.out.println("Ошибка: Введено не верное значение\n" +
                            "Повторите ввод");
                    scanner.next();
                }
            } // Закрытие контроля ввода 2
            if (agree == 1) {
                System.out.println("\nОтлично! Попробуем еще раз!\n");
            }

        } while (agree == 1); // закрытие do - while

        if (agree == 2) {
            System.out.println("\nДо свидания!");
        } else {
            System.out.println("Введено неверное значение\n" +
                    "Работа программы приостановлена\n" +
                    "При необходимости - перезапустите программу.");
        }
    }
}
