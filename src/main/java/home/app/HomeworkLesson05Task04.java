package home.app;

/*
Задание с использованием if и логических операторов:

Напишите программу, которая оценивает результаты теста.
Пусть результат теста хранится в переменной score. Если score больше или равно 90,
выведите "Отлично", если score между 70 и 89 – выведите "Хорошо", если между 50 и 69 –
"Удовлетворительно", и "Неудовлетворительно" во всех остальных случаях.
 */


public class HomeworkLesson05Task04 {

    public static void main(String[] args) {
        byte score = 96;

        if (score >= 90) {
            System.out.println("Отлично");
        } else if (70 <= score && score <= 89) {
            System.out.println("Хорошо");
        } else if (50 <= score && score <= 69) {
            System.out.println("Удовлетворительно");
        } else {
            System.out.println("Неудовлетворительно");
        }

    }
}
