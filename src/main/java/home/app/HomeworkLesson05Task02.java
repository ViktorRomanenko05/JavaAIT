package home.app;

/*
Даны три целых числа x, y и z. Напишите программу, которая проверяет, лежит ли y между x и z.
Ваша программа должна выводить true, если y находится между x и z, и false в противном случае.
 */

public class HomeworkLesson05Task02 {

    public static void main(String[] args) {

        int numberX = 10;
        int numberY = 20;
        int numberZ = 30;

        boolean isNumberYMiddle = (numberY > numberX && numberY < numberZ);

        System.out.println(isNumberYMiddle);

    }
}
