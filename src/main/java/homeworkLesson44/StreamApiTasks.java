package homeworkLesson44;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class StreamApiTasks {

    public static void main(String[] args) {
        System.out.println("-------- Task 1 --------");
         /*
    Задача 1
    Вывнсти все элементы списка
    Вопрос: дан список строк. Используйте Stream API для вывода всех элементов списка на экран.
     */
        List<String> items1 = Arrays.asList("apple", "banana", "cherry", "date");
        items1.stream().forEach(System.out::println);


        System.out.println("-------- Task 2 --------");
        /*
        Задача 2
        Преобразовать элементы списка в верхний регистр и собрать их в новый список
        Вопрос: дан список строк. Преобразуйте все элементы списка в верхний регистр и соберите
        их в новый список
         */
        List<String> items2 = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> fruitsUpperCase = items2.stream().map(String::toUpperCase).collect(toList());
        fruitsUpperCase.stream().forEach(System.out::println);


        System.out.println("-------- Task 3 --------");
        /*
        Задача 3
        Найти количество элементов в списке которые длиннее трех символов
        Вопрос: Подсчитайте, сколько строк в списке содержат более трех символов
         */
        List<String> items3 = Arrays.asList("apple", "ban", "cherry", "date");
        long counter = items3.stream().filter(x -> x.toString().length() > 3).count();
        System.out.println(counter);


        System.out.println("-------- Task 4 --------");
        /*
        Задача 4
        Найти первый элемент который начинается на "C"
        Вопрос: Найдите первый элемент в списке который начинается на букву "c".
         */
        List<String> items4 = Arrays.asList("apple", "banana", "cherry", "date");
        Optional<String> firstElementC = items4.stream().filter(x -> x.startsWith("c")).findFirst();
        firstElementC.ifPresent(System.out::println);
        System.out.println("Is element found?: " + firstElementC.isPresent());

        System.out.println("-------- Task 5 --------");
        /*
        Задача 5
        Проверить, срдержит ли список хотя бы одно отрицательное число
        Вопрос: проверьте, срдержит ли список целых чисел хотя бы одно отрицательное число
         */
        List<Integer> numbers1 = Arrays.asList(-1, 2, 3, 4);
        boolean hasNegative = numbers1.stream().anyMatch(x -> x < 0);
        System.out.println("Contains negative: " + hasNegative);
        numbers1.stream().filter(x -> x < 0).forEach(System.out::println);

        System.out.println("-------- Task 6 --------");
        /*
        Задача 6
        Отфильтровать и отсортировать список
        Вопрос: отфильтруйте список чисел, оставив только четные
         */
        List<Integer> numbers2 = Arrays.asList(-1, 4, 2, 3);
        List<Integer> filteredNumbers = numbers2.stream().filter(x -> x % 2 == 0).sorted().collect(toList());
        filteredNumbers.stream().forEach(System.out::println);
    }
}
