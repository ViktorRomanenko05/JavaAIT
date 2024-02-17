package Lesson07;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя");
        String name = scanner.nextLine();
        System.out.println("Введите день рождения");
        int day = scanner.nextInt();
        System.out.println("Введите месяц рождения");
        int month = scanner.nextInt();
        System.out.println("Введите год рождения");
        int year = scanner.nextInt();

        double result = Math.sin(name.length() + day * month * year);

        result = Math.sin(result);

        if (result < 0.15) {
            System.out.println("У вас очень плохая удача");
        } else if (result < 0.25) {
            System.out.println("У вас будет невеселая жизнь");
        } else if (result < 0.5) {
            System.out.println("У вас будет серая и скучная жизнь");
        } else if (result < 0.7) {
            System.out.println("У вас будет хорошая жизнь");
        } else if (result < 0.95) {
            System.out.println("У вас будет радостная жизнь");
        } else {
            System.out.println("У вас будет шикарная и богатая жизнь");
        }
        ;

        System.out.println(result);


    }
}
