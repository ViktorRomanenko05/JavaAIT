package homeworkLesson35;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Drink {

    private static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LoggerFactory.getLogger(Drink.class);

    public static void main(String[] args) {
        LOGGER.info("Приложение запущено");

        String[] drink = new String[3];
        drink[0] = "Кофе";
        drink[1] = "Чай";
        drink[2] = "Сок";

        byte choise;
        String cycle = "да";
        int iteration = 0;

        System.out.println("Приветствуем Вас " + "\uD83E\uDD17");

        do {
            iteration ++;
            LOGGER.debug("Номер итерации цикла в текущей сессии работы программы " + iteration);
            System.out.println("\nВведите номер желаемого напитка");
            System.out.println("1 - Кофе\n" +
                               "2 - Чай\n" +
                               "3 - Сок\n");
            try {
                choise = scanner.nextByte();
                LOGGER.debug("Пользователь ввел значение {}", choise);
                System.out.println("\n" + drink[choise - 1] + " готовится для вас\n");
                LOGGER.info("Напиток \"{}\" успешно приготовлен", drink[choise - 1]);
            } catch (ArrayIndexOutOfBoundsException exception) {
                LOGGER.error("Введенное число выходит за пределы массива");
                LOGGER.error(exception.getLocalizedMessage());
                System.out.println("\nВы ввели неверное значение. Попробуйте снова\n");
                scanner.nextLine();//Без этого программа выходит из цикла после ошибки не ожидая ввода от пользователя
            } catch (
                    InputMismatchException exception) {//Выбрасывает эту ошибку при вводе не только дробного числа но и стринг значения
                LOGGER.error("Введено некорректное значение");
                LOGGER.error(exception.getLocalizedMessage());
                System.out.println("\nВы ввели неверное значение. Попробуйте снова\n");
                scanner.nextLine();
            } catch (Exception exception) {
                LOGGER.error("Ошибка выполнения программы");
                LOGGER.error(exception.getLocalizedMessage());
                System.out.println("\nПроизошла ошибка выполнения программы, попробуйте снова.\n");
                scanner.nextLine();
            } finally {
                if(iteration<10) {
                    System.out.println("\nЖелаете ли продолжить?");
                    System.out.println("да - введите \"да\"\n" +
                                       "нет - любое другое значение\n");
                    cycle = scanner.next();
                }
            }

        } while (cycle.equalsIgnoreCase("да") && iteration < 10);
        //Максимальное количество итераций введено для исключения бесконечного ввода неправильных значений

        if (iteration == 10){
            System.out.println("\nДостигнуто максимальное количество заказов");
            LOGGER.debug("Достигнуто максимальное количество заказов за сессию.");
        } else {
            LOGGER.debug("Работа программы завершена пользователем");
        }

        scanner.close();

        System.out.println("\nСпасибо. Приятного аппетита " + "\uD83D\uDE09");

        LOGGER.info("Программа завершена");
    }
}
