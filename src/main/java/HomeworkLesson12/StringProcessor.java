package HomeworkLesson12;

/*
Задание: Разработайте класс StringProcessor, который будет предоставлять
статические методы для работы со строками:

Метод reverseString, который принимает строку и возвращает её в перевёрнутом виде.
Метод isPalindrome, который проверяет, является ли переданное слово палиндромом
(читается одинаково в обоих направлениях). Метод должен возвращать булево значение.
 */
public class StringProcessor {

    public static String reverseString (String word) {

        String wordReverse = "";
        for (int i = word.length()-1; i >= 0; i--) {
            wordReverse += word.charAt(i);
        }
        return wordReverse;
    }

    public static boolean isPalindrome(String word){

        word.toLowerCase();

        String reverse = reverseString(word);
        return reverse.equals(word);
    }
}
