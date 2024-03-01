package HomeworkLesson14;

/*Создайте массив строк, который содержит список хобби
(например, "рисование", "программирование", "велоспорт").
Выведите все хобби из массива на экран.
 */

public class Task01 {
    public static String [] hobby = {"велоспорт", "футбол", "рисование", "программирование", "рыбалка"};

    public static void main(String[] args) {

       for (String hobbyName: hobby) {
           System.out.println(hobbyName);
       }
    }
}
