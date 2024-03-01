package HomeworkLesson14;

/*Создайте два массива: один для хобби (как в задаче 1)
и один для рейтинга каждого хобби (целые числа).
Напишите программу, которая выводит хобби и их рейтинги в виде списка.
 */

public class Task05 {

    public static void main(String[] args) {

        int [] hobbyRating = new int [5];

        System.out.println("\nРейтинг ваших хобби от 1 до 10:\n");
        for (int i=0; i<5; i++ ){

            hobbyRating [i]=(int) (Math.random()*11);

            System.out.println("Хобби: " + Task01.hobby[i] + ", рейтинг: " + hobbyRating [i]);
        }
    }
}
