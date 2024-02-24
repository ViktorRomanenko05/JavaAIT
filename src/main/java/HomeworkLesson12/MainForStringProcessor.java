package HomeworkLesson12;

import java.util.Scanner;

public class MainForStringProcessor {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String word;
        String reverse;
        boolean palindrome;
        String choice = "1";

        do {

            System.out.println("Введите слово");
            word = scanner.nextLine().toLowerCase().trim();

            reverse = StringProcessor.reverseString(word);

            palindrome = StringProcessor.isPalindrome(word);

            System.out.println("Оригинал: " + word + "\n" +
                    "Реверс: " + reverse);

            System.out.println(palindrome == true ? "Является палиндромом" : "Не является палиндромом");

            System.out.println("\nДля продолжения работы введите 1,\n" +
                    "для завершения - любое другое значение");

            choice = scanner.nextLine();

        } while (choice.equals("1"));
          scanner.close();

    }

}
