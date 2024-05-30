package homework36;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionPropagation {
    /*
    Задание: Проброс исключения через несколько методов

Напишите программу, где одно исключение пробрасывается через несколько методов
и обрабатывается в методе main.

Задачи

Создайте класс ExceptionPropagation.
Напишите метод method1, который вызывает method2.
Напишите метод method2, который вызывает method3.
Напишите метод method3, который выбрасывает IOException.
Объявите throws IOException в сигнатуре методов method1, method2, method3.
В методе main вызовите method1 и обработайте исключение с помощью блока try-catch.
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberChecker.class);

    public static void main(String[] args) {
        LOGGER.info("The program was started");
        try {
            method1();
        }
        catch (IOException exception){
            LOGGER.error("File not found");
            LOGGER.error(exception.getMessage());
        }

        LOGGER.info("The program is finished");
    }

    static void method1 () throws IOException{ //Метод 1
        method2();
    }

    static void method2 () throws IOException{ //Метод 2
        method3();
    }

    static void method3 () throws IOException{  //Метод 3
        File file = new File("testfile.txt");
        FileReader fileReader = new FileReader(file);
    }


}
