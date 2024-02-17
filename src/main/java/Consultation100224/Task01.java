package Consultation100224;

import java.util.Scanner;

public class Task01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        if (s1.length() > s2.length()) {
            System.out.println("Строка \"" + s1 + "\" длиннее!");
        } else if (s1.length() < s2.length()) {
            System.out.println("Строка \"" + s2 + "\" длиннее!");
        } else {
            System.out.println("Строки равны по длине!");
        }

    }

}
