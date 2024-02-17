package polygon;

import java.util.Scanner;

public class fak {


    public static void main(String[] args) {

        int operation;
        int cycle = 1;
        byte counterOne = 0;
        byte counterTwo = 0;
        double numberOne=0;
        double numberTwo=0;
        double result;
        boolean validInputOne = false;
        boolean validInputTwo = false;
        boolean end = false;
        Scanner scanner = new Scanner(System.in);

        while (!end) {
            counterOne = 0; // Счетчик попыток выбора операции
            boolean validOperation = false; // Флаг для проверки корректности ввода операции

            while (!validOperation && counterOne < 10) {
                System.out.println("\nПожалуйста, выберите тип необходимой операции.\n" +
                        "Введите соответствующий номер:\n\n" +
                        "1 - СЛОЖЕНИЕ\n" +
                        "2 - ВЫЧИТАНИЕ\n" +
                        "3 - УМНОЖЕНИЕ\n" +
                        "4 - ДЕЛЕНИЕ\n");

                if (scanner.hasNextInt()) {
                    operation = scanner.nextInt();
                    if (operation >= 1 && operation <= 4) {
                        validOperation = true;
                    } else {
                        System.out.println("\nВы ввели некорректный номер операции.");
                    }
                } else {
                    System.out.println("Ошибка: Введено не число.");
                    scanner.next();
                }
                counterOne++;
                if (!validOperation) {
                    System.out.println("Оставшееся количество попыток ввода: " + (10 - counterOne));
                }
            }

            if (counterOne >= 10) {
                System.out.println("Превышено допустимое количество попыток ввода. Программа будет завершена.");
                break; // Выход из цикла, если превышено количество попыток
            }

            // Далее следует ваш код для ввода чисел, выполнения операций и вывода результатов
            // ...

            if (!end) {
                System.out.println("Желаете ли вы продолжить?\nВведите \"1\" для продолжения или любую другую цифру для выхода:");
                if (!scanner.hasNextInt() || scanner.nextInt() != 1) {
                    System.out.println("Спасибо за использование программы. До свидания!");
                    end = true;
                }
            }
        }
        scanner.close();
    }
}
