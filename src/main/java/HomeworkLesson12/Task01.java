package HomeworkLesson12;

/*Задание: Напишите программу на Java, которая демонстрирует работу с методами.
Ваша программа должна включать метод calculateArea, который принимает в качестве
аргументов значения типа double: длину и ширину прямоугольника, и возвращает
значение его площади. Также должен быть реализован метод calculatePerimeter,
принимающий те же аргументы и возвращающий периметр прямоугольника. Главный метод
(main) должен считывать значения длины и ширины, переданные пользователем, вызывать
оба эти метода для расчета площади и периметра, и выводить результаты расчетов на экран.
 */

import java.util.Scanner;

public class Task01 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String choice ="1";

        System.out.println("\nЗдравствуйте!\n" +
                "Эта программа поможет Вам рассчитать\n" +
                "площадь и периметр прямоугольника");
        do {
            System.out.println("Пожалуйста, введите номер желаемой операции:\n" +
                    "   1 - рассчитать площадь\n" +
                    "   2 - рассчитать периметр\n" +
                    "   3 - рассчитать оба значения");

            while (!scanner.hasNextInt()) {

                System.out.println("Ошибка, введите цифру");
                scanner.next();
            }
            int operation = scanner.nextInt();

            if (operation != 1 && operation != 2 && operation != 3){
                System.out.println("Введен не верный номер операции.\n");
            }else {

                System.out.println("Введите длину прямоугольника");

                while (!scanner.hasNextDouble()) {

                    System.out.println("Ошибка, введите число");
                    scanner.next();
                }
                double lengthOne = scanner.nextDouble();

                System.out.println("Введите ширину прямоугольника");

                while (!scanner.hasNextDouble()) {

                    System.out.println("Ошибка, введите число");
                    scanner.next();
                }
                double lengthTwo = scanner.nextDouble();

                switch (operation) {
                    case 1 -> System.out.println("Площадь равна " + calculateArea(lengthOne, lengthTwo));
                    case 2 -> System.out.println("Периметр равен " + calculatePerimeter(lengthOne, lengthTwo));
                    case 3 -> System.out.println("Площадь равна " + calculateArea(lengthOne, lengthTwo) + "\n" +
                            "Периметр равен " + calculatePerimeter(lengthOne, lengthTwo));
                }

                System.out.println("\nДля продолжения работы введите 1,\n" +
                        "для завершения - любое другое значение");

                choice = scanner.next().trim();
            }

            }while (choice.equals("1"));

             scanner.close();
        }

    public static double calculateArea (double length, double width){
        return length*width;
    }

    public static double calculatePerimeter (double length, double width){
        return (length + width) * 2;
    }
}
