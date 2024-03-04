package HomeworkLesson14;

/*Создайте два массива: один для хобби (как в задаче 1)
и один для рейтинга каждого хобби (целые числа).
Напишите программу, которая выводит хобби и их рейтинги в виде списка.
 */

public class Task05Correction {

    public static void main(String[] args) {

        String [] hobby = {"велоспорт", "футбол", "рисование", "программирование", "рыбалка"};

        int [] hobbyRating = new int [hobby.length];

        System.out.println("\nРейтинг ваших хобби от 1 до 10:\n");
        for (int i=0; i < hobby.length; i++ ){

            hobbyRating [i]=(int) (Math.random()*10+1);

            System.out.println("Хобби: " + hobby[i] + ", рейтинг: " + hobbyRating [i]);
        }
    }
}
