package home.app;

import java.util.Scanner;

public class HomeworkLesson07Task02 {

/*
Напишите программу для тестирования знаний.
Программа должна задать три вопроса связанных с программированием.

Ответом на первый вопрос должно быть да/нет,
на второй вопрос один вариант ответа из трех (a, b, c),
на третий вопрос открытый ответ (надо ввести ответ вручную)

Если пользователь правильно ответил хотябы на два вопроса, вывести сообщение "Вы хорошо знаете программирование!",
в противном случае вывести "Вам нужно учиться больше!".
*/

    public static void main(String[] args) {

        byte score = 0;
        char oneQ = '-';
        char twoQ = '-';
        char thrQ = '-';

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nТЕСТИРОВАНИЕ ЗНАНИЙ JAVA\n");
        System.out.println("Вопрос 1\n" +
                "Является ли Java объектно-ориентированным языком программирования?\n" +
                "Введите ответ в формате \"Да\" либо \"Нет\"");
        String questOne = scanner.next();

        System.out.println("\nВопрос 2\n" +
                "Какой тип переменной в Java используется для хранения целых чисел?\n" +
                " a - boolean\n b - int\n c - String\n" +
                "Введите ответ в формате \"a\", \"b\", либо \"c\"");
        String questTwo = scanner.next();

        System.out.println("\nВопрос 3\n" +
                "Как называется метод, являющийся точкой входа в программу?");
        String questThree = scanner.next();

        scanner.close();

        if ((questOne.equals("Да") || questOne.equals("да") || questOne.equals("нет") || questOne.equals("Нет")) &&
                (questTwo.equals("A") || questTwo.equals("a") || questTwo.equals("А") || questTwo.equals("а") ||
                        questTwo.equals("b") || questTwo.equals("B") || questTwo.equals("В") ||
                        questTwo.equals("C") || questTwo.equals("c") || questTwo.equals("С") || questTwo.equals("с"))) {
            if (questOne.equals("Да") || questOne.equals("да")) {
                score += 1;
                oneQ = '+';
            }

            if (questTwo.equals("b") || questTwo.equals("B") || questTwo.equals("В")) {
                score += 1;
                twoQ = '+';
            }

            if (questThree.equals("main")) {
                score += 1;
                thrQ = '+';
            }

            System.out.println("========================" +
                    "==========================================\n\nВАШ РЕЗУЛЬТАТ:\n");
            System.out.println("Вопрос 1: " + oneQ + "\n" +
                    "Вопрос 2: " + twoQ + "\n" +
                    "Вопрос 3: " + thrQ + "\n");

            if (score >= 2) {
                System.out.println("Вы хорошо знаете программирование");
            } else {
                System.out.println("Вам нужно учиться больше");
            }
        } else {
            System.out.println("==================================================================\n" +
                    "\n!!! Введённые вами ответы не соответствуют необходимому формату.\n" +
                    "Пожалуйста, перезапустите программу и введите ваши ответы корректно.");
        }

    }
}