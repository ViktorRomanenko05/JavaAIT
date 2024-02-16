package home.app;

/*Задача: Объявите две строковые переменные и инициализируйте их
названием песни и названием альбома. Создайте третью строковую переменную,
которая будет содержать полную информацию, путем конкатенации (сложения)
первых двух переменных. Выведите полную информацию на экран.
 */

public class HomeworkLesson04Task03 {
    public static void main(String[] args) {

        String song = "Another Brick In The Wall (Part II)";
        String album = "The Wall";
        String summary = "Name:  " + song + "\nAlbum: " + album;
        System.out.println(summary);

    }
}