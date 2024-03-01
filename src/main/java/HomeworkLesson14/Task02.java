package HomeworkLesson14;

import java.util.Scanner;

/*Дан массив хобби (как в задаче 1).
Напишите программу, которая ищет в массиве заданное хобби
и выводит на экран сообщение о том, есть ли оно в списке.
 */
public class Task02 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean indicator = false;

        System.out.println("Введите название вашего хобби");
        String yourHobby = scanner.nextLine().toLowerCase().trim();
        scanner.close();

        for (int i=0; i <Task01.hobby.length; i++){
            if (yourHobby.equals(Task01.hobby[i])){
                indicator = true;
                break;
            }
        }
        System.out.println(indicator ? "Ваше хобби есть в списке" : "Вашего хобби нет в списке");
    }
}
