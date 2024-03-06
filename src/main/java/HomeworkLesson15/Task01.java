package HomeworkLesson15;

import java.text.MessageFormat;
import java.util.Scanner;

/*Задание: Учет животных в зоопарке

Цель задания: Практика в использовании массивов и методов в Java для управления
информацией о животных в зоопарке.

Задача:

Вам нужно создать программу для учета животных в зоопарке, используя только
массивы и базовые методы. Программа должна позволять хранить названия видов животных
и соответствующие им звуки, которые они издают.

Шаги:

Создание массивов:

Создайте два массива строк: один для хранения названий видов животных (String[] animalSpecies),
другой — для звуков, которые они издают (String[] animalSounds).
Инициализация массивов:

Инициализируйте массивы с данными нескольких животных. Например, "Лев", "Слон", и "Обезьяна"
для animalSpecies и соответствующие звуки для animalSounds.
Вывод информации о животных:

Реализуйте метод printAnimalSounds, который принимает оба массива и выводит информацию о каждом
животном и звуке, который оно издает.
Поиск животного по названию:

Дополнительно, реализуйте метод findAnimalSound, который принимает название животного и оба массива,
ищет это животное в массиве animalSpecies и выводит соответствующий звук из animalSounds.
 */
public class Task01 {

    static Scanner scanner = new Scanner(System.in);

    public static String [] animalSpecies = {"Лев", "Корова", "Рыба"};

    public static String [] animalSounds = {"рычит", "мычит", "молчит"};

    public static void main(String[] args) {

        System.out.println("\nВведите название животного для поиска:");
        String animal = scanner.next().trim();
        scanner.close();

        printAnimalSounds (animalSpecies, animalSounds);
        findAnimalSound(animal, animalSpecies, animalSounds);
    }

    public static void printAnimalSounds(String [] species, String sounds []){

        if (species.length != sounds.length){
            System.out.println ("Ошибка! Не верные параметры");
        }
        else {
            for (int i = 0; i < species.length; i++){

                System.out.println(MessageFormat.format("{0} - {1}",species[i], sounds[i])); //нашёл аналог placeholder :)
            }
        }
    }

    public static void findAnimalSound (String animalName, String [] species, String [] sounds){

        boolean isFound = false;

        if (species.length != sounds.length){
            System.out.println ("Ошибка! Не верные параметры");
        }
        else {
            for (int i=0; i < species.length; i++){
                if (species [i].equalsIgnoreCase(animalName)){
                    System.out.println("\nРезультат поиска:");
                    System.out.println(MessageFormat.format("{0} - {1}",animalName, sounds[i]));
                    isFound = true;
                    break;
                }
            }
            if (!isFound){
                System.out.println("\nРезультат поиска:\nЖивотного " + animalName + " нет в списке");
            }
        }
    }
}
