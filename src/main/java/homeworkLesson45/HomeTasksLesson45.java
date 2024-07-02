package homeworkLesson45;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class HomeTasksLesson45 {
    public static void main(String[] args) {

        System.out.println("-------- Task 1 --------");
        /*
        Задание 1: Вывести элементы списка строк
        Задача: Используя Stream API, выведите на экран все строки из списка.
         */
        List<String> fruits = Arrays.asList("apple", "banana", "cherry");
        fruits.stream().forEach(System.out::println);


        System.out.println("-------- Task 2 --------");
        /*
        Задание 2: Преобразовать строки в верхний регистр
        Задача: Используя Stream API, преобразуйте все строки из списка в верхний регистр
        и соберите результат в новый список.
         */
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> upNames = names.stream().map(String::toUpperCase).collect(toList());
        upNames.stream().forEach(System.out::println);


        System.out.println("-------- Task 3 --------");
        /*
        Задание 3: Подсчитать количество строк с длиной больше 4 символов
        Задача: Используя Stream API, подсчитайте количество строк в списке, которые содержат
        более 4 символов.
         */
        List<String> words = Arrays.asList("stream", "api", "java", "list");
        long quantity = words.stream().filter(x -> x.length() > 4).count();
        System.out.println(quantity);


        System.out.println("-------- Task 4 --------");
        /*
        Задание 4: Найти первый элемент, начинающийся на заданную букву
        Задача: Используя Stream API, найдите первый элемент в списке,
        который начинается на букву 'j'.
         */
        List<String> tools = Arrays.asList("hammer", "jack", "wrench");
        Optional<String> found = tools.stream().filter(x -> x.startsWith("j")).findFirst();
        found.ifPresent(System.out::println);


        System.out.println("-------- Task 5 --------");
        /*
        Задание 5: Фильтрация и сортировка числового списка
        Задача: Отфильтруйте список чисел, оставив только те, которые больше 10,
        и затем отсортируйте оставшиеся элементы.
         */
        List<Integer> numbers = Arrays.asList(18, 5, 13, 1, 21, 8);
        numbers.stream().filter(x -> x > 10).sorted().forEach(System.out::println);


        System.out.println("-------- Task 6 --------");
        /*
        Задание 6: Сгруппировать строки по начальной букве
        Задача: Используя Stream API, сгруппируйте строки из списка по начальной букве.
         */
        List<String> animals = Arrays.asList("ant", "bear", "alligator", "bug", "shark");
        Map<Character, List<String>> groupedByChar = animals.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
        groupedByChar.entrySet().stream().forEach(System.out::println);


        System.out.println("-------- Task 7 --------");
        /*
        Задание 7: Суммирование значений в списке
        Задача: Используя Stream API, найдите сумму всех чисел в списке.
         */
        List<Integer> values = Arrays.asList(2, 3, 5, 7, 11);

        //int sum1 = values.stream().reduce(0, Integer::sum);
        int sum = values.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);


        System.out.println("-------- Task 8 --------");
        /*
        Получить статистику по числовому списку
        Задача: Используя Stream API, получите статистику (минимум, максимум) по списку чисел.
         */
        List<Integer> stats = Arrays.asList(10, 20, 30, 40, 50);
        int min = stats.stream().min(Integer::compare).get();
        int max = stats.stream().max(Integer::compare).get();

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);


        System.out.println("-------- Task 9 --------");
        /*
        Задание 9: Объединение всех строк списка в одну строку
        Задача: Используя Stream API, объедините все строки из списка в одну строку, разделяя их пробелом.
         */
        List<String> phrases = Arrays.asList("Java", "Stream", "API");
        String result = phrases.stream().collect(Collectors.joining("_"));
        System.out.println(result);


        System.out.println("-------- Task 10 --------");
        /*
        Задание 10: Маппинг и редукция для получения общей длины строк
        Задача: Используя Stream API, рассчитайте общую длину всех строк в списке.
         */
        List<String> messages = Arrays.asList("hello", "world", "streams", "are", "cool");

        //Мое решение
        int length = messages.stream().collect(Collectors.joining("")).length();
        System.out.println(length);
    }
}
