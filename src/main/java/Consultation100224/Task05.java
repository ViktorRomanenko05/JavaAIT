package Consultation100224;

// вывести факториалы всех чисел от 1 до 20
public class Task05 {
    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {

            long res = 1;
            for (int j = 2; j <= i; j++) {
                res *= j;

            }
            System.out.println(i + "! = " + res);
        }

    }
}
