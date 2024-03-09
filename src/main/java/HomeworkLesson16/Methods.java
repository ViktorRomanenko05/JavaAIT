package HomeworkLesson16;

import java.util.Arrays;

public class Methods {

    public static void main(String[] args) {

        Integer resultAdd = sumNumbers();

        System.out.println("\n______________________");
        System.out.println("Метод для сложения:");
        System.out.println("\n1. Без входящих аргументов:");

        System.out.println(resultAdd != null? "Сумма введенных чисел равна " + resultAdd :
                                              "Ошибка! Вы не ввели ни одного значения");

        System.out.println("\n2. Передаем в метод числа: 3, 2, 11, -8, 41 ");
        System.out.println("Результат:" + sumNumbers(3, 2, 11, -8, 41));

        System.out.println("\n______________________");
        System.out.println("Метод для статистики:");
        System.out.println("\n1. Без входящих аргументов:");
                           statistic();

        System.out.println("\n2. Передаем в метод числа: 3, 2, 11, -8, 41");
                           statistic(3, 2, 11, -8, 41);

        System.out.println("\n3. Передаем в метод числа: 3, 2, 11, 8, 41, 15, 22, 0, 9");
                           statistic(3, 2, 11, 8, 41, 15, 22, 0, 9);
    }

    /*
    Причина выбора Integer вместо int в первом методе:
    так как по условию задачи метод должен вернуть результат, то в ситуации
    когда мы не передаём ни одного значения - метод возвращает 0 и на экран
    помимо строки с сообщением об ошибке выводится сумма равная 0.

    Я посчитал это не совсем корректным, но другого варианта кроме как
    использовать Integer вместо int не нашел, что решило данную проблему, но
    при этом сделает невозможным дальнейшее использование полученного результата,
    так как арифметические операции с null невозможны.
    Но в данном случае - это может быть приемлимым вариантом.
     */
    public static Integer  sumNumbers (Integer... numbersInput){

        Integer sum = null;

       if (numbersInput.length > 0) {
           sum =0;
           for (int number: numbersInput) {
               sum += number;
           }
       }
       return sum;
       }

       public static void statistic (double... numbersStatistic) {

           boolean indicator = false;
           double sumStat = 0;
           double average;

           if (numbersStatistic.length > 0) {
               for (double number : numbersStatistic) {
                   if (number < 0) {
                       indicator = true;
                       break;
                   }
                   else {sumStat += number;}
               }
               if (!indicator) {
                   Arrays.sort(numbersStatistic);
                   average = sumStat/numbersStatistic.length;
                   System.out.println("Наименьшее число: " + numbersStatistic[0]);
                   System.out.println("Наибольшее число: " + numbersStatistic[numbersStatistic.length - 1]);
                   System.out.println("Среднее значение: " + average);

               }else System.out.println("ОШИБКА!! Среди введенных чисел есть отрицательное");

           } else {
               System.out.println("ОШИБКА!! Вы не ввели ни одного значения");
           }
       }
    }
