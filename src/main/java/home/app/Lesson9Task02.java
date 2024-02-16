package home.app;

import java.util.Scanner;

public class Lesson9Task02 {

    public static void main(String[] args) {

        int secret = (int) (Math.random() * 101);
        int number = 0;
        int agree = 1;
        byte lap = 5;

        System.out.println("Здравствуйте!");

        do {
            System.out.println("Попытайтесь угадать число от 0 до 100\n" +
                    "У Вас есть 50 попыток.\nСледуйте подсказкам. Удачи!\n");

            Scanner scanner = new Scanner(System.in);

            boolean validInput = false;
            boolean validNext = false;
            boolean stop = false;


            while (!validInput || secret != number && lap > 0) {

                System.out.print("Введите целое число: ");
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    validInput = true;
                } else {
                    System.out.println("Ошибка: Введено не верное значение.");
                    scanner.next();
                    stop = true;
                }

                lap--;

                if (number != secret) {
                    if (number < secret && stop == false) {
                        System.out.println("Введенное число меньше.");
                    } else if (number > secret && stop == false) {
                        System.out.println("Введенное число больше.");
                    }
                }

                if (lap < 1) {
                    System.out.println("К сожалению Вы проиграли");
                }


                System.out.println("Пожалуйста, повторите ввод, осталось " + lap + " попыток");
                System.out.println(secret);


            }


            System.out.println("\nПОЗДРАВЛЯЮ! ВЫ ВЫИГРАЛИ!\n");

            System.out.println("Желаете сыграть еще раз?\n" +
                    "1 - Да\n2 - Нет\n");

            while (!validNext) {

                System.out.print("Введите Ваш ответ: ");
                if (scanner.hasNextInt()) {
                    agree = scanner.nextInt();
                    validNext = true;
                } else {
                    System.out.println("Ошибка: Введено не верное значение.");
                    scanner.next();
                }
            }

            if (agree == 1) {
                System.out.println("\nОтлично! Попробуем еще раз!");
            }

        } while (agree == 1);

        if (agree == 2) {
            System.out.println("Спасибо! До встречи!");
        } else {
            System.out.println("Вы ввели неверное значение");
        }

    }

}
