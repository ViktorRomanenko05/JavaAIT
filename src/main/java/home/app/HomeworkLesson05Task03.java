package home.app;
/*
Напишите программу, которая проверяет, соответствует ли введенный возраст условиям
для получения водительских прав. В разных странах возраст, когда можно начать учиться
водить машину, может отличаться. Пусть в вашей программе минимальный возраст будет 16 лет,
а максимальный – 75 лет. Программа должна выводить true, если возраст соответствует
условиям, и false – если нет.
 */
public class HomeworkLesson05Task03 {

    public static void main(String[] args)

    {

        byte applicantsAge = 65;

        boolean checkAge = (16 <= applicantsAge && applicantsAge <= 75);

        System.out.println(checkAge);

    }
}
