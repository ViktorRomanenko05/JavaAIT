package homework36;

/*
Задание: Создание и выбрасывание собственного исключения

Описание

Напишите метод, который проверяет длину строки и выбрасывает собственное исключение, если длина строки меньше 5 символов.

Задачи

Создайте класс InvalidStringLengthException, расширяющий Exception.
Создайте класс StringValidator.
Напишите метод validateStringLength, который принимает строку и выбрасывает InvalidStringLengthException,
если длина строки меньше 5 символов.
В методе main вызовите validateStringLength и обработайте исключение с помощью блока try-catch.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StringValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringValidator.class);

    public static void main(String[] args) {

        LOGGER.info("Program was started");

        String one = "one";
        String two = "onetw";
        String three = null;

        try {
            validateStringLength(one); //Строка короче 5 символов
        }
        catch (InvalidStringLengthException exception){
            LOGGER.error("A string is too short.");
            LOGGER.error(exception.getMessage());
        }
        catch (NullPointerException exception){
            LOGGER.error("Input is null");
            LOGGER.error(exception.getMessage());
        }

        System.out.println("\n--------------------------\n");

        try {
            validateStringLength(two); //Строка 5 символов
        }
        catch (InvalidStringLengthException exception){
            LOGGER.error("A string is too short.");
            LOGGER.error(exception.getMessage());
        }
        catch (NullPointerException exception){
            LOGGER.error("Input is null");
            LOGGER.error(exception.getMessage());
        }

        System.out.println("\n--------------------------\n");

        try {
            validateStringLength(three); //Строка = null
        }
        catch (InvalidStringLengthException exception){
            LOGGER.error("A string is too short.");
            LOGGER.error(exception.getMessage());
        }
        catch (NullPointerException exception){
            LOGGER.error("Input is null");
            LOGGER.error(exception.getMessage());
        }
        finally {
            LOGGER.error("Program is finished");
        }

    }

    static void validateStringLength (String string) throws InvalidStringLengthException {
        LOGGER.debug("Length of input string: {}", string.length());
        if(string.length() < 5){
            throw new InvalidStringLengthException("A string shorter than five characters");
        }
        System.out.println("String is OK");
    }
}
