package homework36;

/*
Задание: Выбрасывание непроверяемого исключения
при некорректном входном значении

Описание

Напишите метод, который проверяет, является ли строка числом,
и выбрасывает NumberFormatException, если строка не может быть
преобразована в число.

Задачи

Создайте класс NumberChecker.
Напишите метод checkNumber, который принимает строку и выбрасывает
NumberFormatException, если строка не является числом.
В методе main вызовите checkNumber и обработайте исключение с помощью блока try-catch.

 */

import homeworkLesson35.Drink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberChecker.class);

    public static void main(String[] args) {
        LOGGER.info("Program was started");

        String one = "not digit";
        String two = " 123  ";

        try{
            checkNumber(one); //Передаем в метод не число
        }
        catch (NumberFormatException exception){
            LOGGER.error("Input is not digit");
            LOGGER.error(exception.getLocalizedMessage());
        }
        catch (NullPointerException exception){ //Также добавим catch на случай передачи в метод null
            LOGGER.error("String is null");
            LOGGER.error(exception.getLocalizedMessage());
        }

        System.out.println("\n----------------------------\n");

        try {
            checkNumber(two); //Передаем в метод число
        }
        catch (NumberFormatException exception){
            LOGGER.error("Input is not digit");
            LOGGER.error(exception.getLocalizedMessage());
        }
        catch (NullPointerException exception){ //Также добавим catch на случай передачи в метод null
            LOGGER.error("String is null");
            LOGGER.error(exception.getLocalizedMessage());
        }
        finally {
            LOGGER.info("Program is finished");
        }
    }

    static void checkNumber(String string){
        Integer number = Integer.parseInt(string.trim());
        LOGGER.debug("Input {} is digit", number);
    }
}
