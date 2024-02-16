package home.app;

/*
Напишите программу, которая сравнивает два введенных числа a и b.
Программа должна выводить:

 - "a больше b", если a > b
 - "a меньше b", если a < b
 - "a равно b", если a и b равны.

 */

public class HomeworkLesson05Task01 {

    public static void main(String[] args) {

        double numberA = 10;
        double numberB = 10;

        if (numberA > numberB) {
            System.out.println("a больше b");
        } else if (numberA < numberB) {
            System.out.println("a меньше b");
        } else {
            System.out.println("a равно b");
        }
    }
}